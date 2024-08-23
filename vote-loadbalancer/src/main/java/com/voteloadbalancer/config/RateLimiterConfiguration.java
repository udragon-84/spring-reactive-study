package com.voteloadbalancer.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import java.util.Objects;


@Configuration
public class RateLimiterConfiguration {
    @Bean
    public KeyResolver userKeyResolver() {
        // ip를 기준으로 요청을 제한한다.
        // return exchange -> Mono.just("anonymous");
        // return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress());
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }
}
