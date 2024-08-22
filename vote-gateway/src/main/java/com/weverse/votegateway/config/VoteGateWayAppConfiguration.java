package com.weverse.votegateway.config;

import com.weverse.votegateway.config.properties.ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class VoteGateWayAppConfiguration {

    @Bean
    public WebClient webClient(ClientProperties clientProperties, WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(clientProperties.processServiceUri().toString())
                .build();
    }

}
