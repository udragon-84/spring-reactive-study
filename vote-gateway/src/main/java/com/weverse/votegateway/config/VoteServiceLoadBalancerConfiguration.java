package com.weverse.votegateway.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
public class VoteServiceLoadBalancerConfiguration {

    @Bean
    @Primary
    public ServiceInstanceListSupplier serviceInstanceListSupplier(Environment environment) {
        return new ServiceInstanceListSupplier() {
            @Override
            public Flux<List<ServiceInstance>> get() {
                return Flux.just(Arrays.asList(
                        new DefaultServiceInstance("vote-service1", "vote-service", "localhost", 8080, false),
                        new DefaultServiceInstance("vote-service2", "vote-service", "localhost", 8081, false)
                ));
            }
            @Override
            public String getServiceId() {
                return "vote-service";
            }
        };
    }

}
