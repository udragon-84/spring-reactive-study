package com.voteloadbalancer.config;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VoteServerInstanceConfiguration {
    @Bean
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new VoteInstanceSupplier("vote-service");
    }
}
