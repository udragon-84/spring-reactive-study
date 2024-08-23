package com.voteloadbalancer.api;

import com.voteloadbalancer.api.dto.FanVoteDto;
import com.voteloadbalancer.api.response.VoteLoadBalancingResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api")
public class VoteLoadBalancerController {

    private final WebClient.Builder loadBalancedWebClientBuilder;
    private final ReactorLoadBalancerExchangeFilterFunction lbFunction;

    public VoteLoadBalancerController(WebClient.Builder webClientBuilder, ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        this.loadBalancedWebClientBuilder = webClientBuilder;
        this.lbFunction = lbFunction;
    }

    @GetMapping("/{uriTail}")
    public Mono<String> healthCheck(@PathVariable String uriTail) {
        log.info("healthCheck http://localhost:0000/api/{}", uriTail);

        return loadBalancedWebClientBuilder.build()
                .get()
                .uri("http://vote-service/" + uriTail)
                .retrieve()
                .bodyToMono(String.class);
    }

    @PostMapping("/{uriTail}")
    public Mono<VoteLoadBalancingResponse<FanVoteDto>> voteHandleLoadbalancing(
            @PathVariable String uriTail,
            @Valid @RequestBody FanVoteDto fanVoteDto) {
        log.info("voteHandleLoadbalancing http://localhost:0000/api/{}", uriTail);

        return loadBalancedWebClientBuilder.build()
                .post()
                .uri("http://vote-service/api/" + uriTail)
                .bodyValue(fanVoteDto)
                .retrieve()
                .bodyToMono(FanVoteDto.class)
                .map(dto -> VoteLoadBalancingResponse.<FanVoteDto>builder()
                        .data(dto)
                        .httpStatus(HttpStatus.OK)
                        .message("success")
                        .result(true)
                        .build())
                .onErrorResume(e -> {
                    log.error("VoteLoadBalancerController.voteHandleLoadbalancing Exception", e);
                    return Mono.just( VoteLoadBalancingResponse.<FanVoteDto>builder()
                            .data(fanVoteDto)
                            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message("fail")
                            .result(false)
                            .build());
                });
    }

}
