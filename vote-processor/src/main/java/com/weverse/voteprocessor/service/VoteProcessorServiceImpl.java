package com.weverse.voteprocessor.service;

import com.weverse.voteprocessor.repository.FanVoteProcessorRepository;
import com.weverse.voteprocessor.service.convert.FanVoteProcessConvert;
import com.weverse.voteprocessor.service.dto.FanVoteDto;
import com.weverse.voteprocessor.service.dto.FanVoteProcessDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class VoteProcessorServiceImpl implements VoteProcessorService {

    @Autowired
    private FanVoteProcessorRepository fanVoteProcessorRepository;

    @Override
    public Mono<FanVoteProcessDto> handleVoteProcessor(FanVoteDto fanVoteDto) {
        // kafka 세팅 후 전송
        return Mono.just(fanVoteDto)
                .map(FanVoteProcessConvert::convertToFanVotoEventsEntity)
                .flatMap(dto -> this.fanVoteProcessorRepository.save(dto))
                .map(FanVoteProcessConvert::convertToFanVoteProcessDto)
                .doOnNext(dto -> log.info("fanVoteProcessDto: {}", dto));
    }

}
