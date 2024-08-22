package com.weverse.votestorage.service;

import com.weverse.votestorage.repository.FanVotesAnalyzeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;

@Slf4j
@Service
public class VoteKafkaSubscribeServiceImpl implements KafkaSubscribe {

    @Autowired
    private KafkaReceiver<String, String> kafkaReceiver;

    @Autowired
    private FanVotesAnalyzeRepository fanVotesAnalyzeRepository;

    @Override
    public void consumeAndSave() {
        kafkaReceiver.receive()
                .doOnNext(record -> log.info("VoteKafkaSubscribeServiceImpl.consumeAndSave: {}", record.value()))
                .subscribe();
    }
}
