package com.weverse.votestorage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weverse.votestorage.repository.FanVotesAnalyzeRepository;
import com.weverse.votestorage.service.convert.FanVoteStorageConvert;
import com.weverse.votestorage.service.dto.FanVoteDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;

@Slf4j
@Service
public class VoteKafkaSubscribeServiceImpl implements KafkaSubscribe<Void> {

    @Autowired
    private KafkaReceiver<String, String> kafkaReceiver;

    @Autowired
    private FanVotesAnalyzeRepository fanVotesAnalyzeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> consumeAndSave() {
        return kafkaReceiver.receive()
                .map(record -> this.convertToFanVoteDto(record.value()))
                .flatMap(dto -> this.fanVotesAnalyzeRepository.save(FanVoteStorageConvert.toEntity(dto)))
                .map(FanVoteStorageConvert::toDto)
                .doOnNext(dto -> log.info("VoteKafkaSubscribeServiceImpl.consumeAndSave FanVoteDto: {}", dto))
                .onErrorContinue((e, obj) -> log.error("Fail Kafka Consumer Exception: ", e))
                .then();
    }

    private FanVoteDto convertToFanVoteDto(String value) {
        try {
            return this.objectMapper.readValue(value, FanVoteDto.class);
        } catch (Exception e) {
            log.error("VoteKafkaSubscribeServiceImpl.convertToFanVoteDto: {}", e.getMessage());
            throw new RuntimeException("VoteKafkaSubscribeServiceImpl.convertToFanVoteDto:", e);
        }
    }
}
