package com.weverse.votegateway.api;

import com.weverse.votegateway.api.response.VoteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class HealthCheckController {

    @GetMapping(value="/hello")
    public Mono<VoteResponse<String>> healthCheck() {
        log.info("HealthCheckController.healthCheck");
        return Mono.just(new VoteResponse<>("Ok"));
    }

}
