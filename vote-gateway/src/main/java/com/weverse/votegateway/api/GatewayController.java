package com.weverse.votegateway.api;

import com.weverse.votegateway.api.response.VoteResponse;
import com.weverse.votegateway.service.FanVoteService;
import com.weverse.votegateway.service.dto.FanVoteDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
public class GatewayController {

    @Autowired
    private FanVoteService fanVoteService;

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081").build();

    @PostMapping(value = "/vote")
    public Mono<VoteResponse<FanVoteDto>> vote(@Valid @RequestBody FanVoteDto fanVoteDto) {
        log.info("GatewayController.vote: {}", fanVoteDto);

        // 팬 투표 비동기 저장 후 비즈니스 처리 서버로 전송
        fanVoteService.saveFanVote(fanVoteDto)
                .doOnError(e -> log.error("fanVote SaveError: {}", e.getMessage(), e))
                .subscribe();

        // 비동기로 vote-processor 비즈니스 처리 서버로 데이터 전송(응답은 필요 없음 가정)
        webClient.post()
                .uri("/api/vote/processor")
                .bodyValue(fanVoteDto)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(e -> log.error("vote-processor server error: {}", e.getMessage(), e))
                .subscribe();

        // 응답은 바로 처리하여 부하를 줄인다.
        return Mono.just(new VoteResponse<>(fanVoteDto));
    }

}
