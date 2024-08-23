package com.voteloadbalancer.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        log.info("Address http://localhost:0000/api/{}", uriTail);
        return loadBalancedWebClientBuilder.build()
                .get()
                .uri("http://vote-service/" + uriTail)
                .retrieve()
                .bodyToMono(String.class);
    }

}
