package com.weverse.votestorage.service;

import reactor.core.publisher.Mono;

public interface KafkaSubscribe<T> {
    Mono<T> consumeAndSave();
}
