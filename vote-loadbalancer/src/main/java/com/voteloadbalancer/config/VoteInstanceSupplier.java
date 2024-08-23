package com.voteloadbalancer.config;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class VoteInstanceSupplier implements ServiceInstanceListSupplier {

    private final String serviceId;

    public VoteInstanceSupplier(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getServiceId() {
        return this.serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays.asList(
                new DefaultServiceInstance(this.serviceId + "1", serviceId, "localhost", 8080, false),
                new DefaultServiceInstance(this.serviceId + "2", serviceId, "localhost", 8081, false)
        ));
    }
}
