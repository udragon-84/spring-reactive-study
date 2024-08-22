package com.weverse.voteprocessor.service;

import com.weverse.voteprocessor.repository.FanVoteProcessorRepository;
import com.weverse.voteprocessor.service.convert.FanVoteProcessConvert;
import com.weverse.voteprocessor.service.dto.FanVoteDto;
import com.weverse.voteprocessor.service.dto.FanVoteProcessDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service
public class VoteProcessorServiceImpl implements VoteProcessorService {

    @Autowired
    private FanVoteProcessorRepository fanVoteProcessorRepository;

    @Autowired
    private KafkaSender<String, String> kafkaSender;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Override
    public Mono<FanVoteProcessDto> handleVoteProcessor(FanVoteDto fanVoteDto) {
        // TODO 이 부분에 인기 가수 투표 비즈니스 로직이 존재

        // CassandraDB에 카프카 전송 데이터 이력을 남긴 후 최종적으로 데이터 저장 서버로 kafka 퍼블리셔
        return Mono.just(fanVoteDto)
                .map(FanVoteProcessConvert::convertToFanVotoEventsEntity)
                .flatMap(dto -> this.fanVoteProcessorRepository.save(dto))
                .map(FanVoteProcessConvert::convertToFanVoteProcessDto)
                .doOnNext(dto -> this.kafkaSender.send(Mono.just(
                        SenderRecord.create(topic, null, null, null, dto.getJsonData(), null)))
                        .doOnError(e -> log.error("Vote KafkaMessage Send Fail", e))
                        .subscribe())
                .doOnSuccess(result -> log.info("Vote KafkaMessage Send Success {}", result));
    }
}
