package com.weverse.voteprocessor.service;

import com.weverse.voteprocessor.service.dto.FanVoteDto;
import com.weverse.voteprocessor.service.dto.FanVoteProcessDto;
import reactor.core.publisher.Mono;

public interface VoteProcessorService {
    Mono<FanVoteProcessDto> handleVoteProcessor(FanVoteDto fanVoteDto);
}
