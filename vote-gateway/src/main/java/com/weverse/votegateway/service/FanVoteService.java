package com.weverse.votegateway.service;

import com.weverse.votegateway.service.dto.FanVoteDto;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

public interface FanVoteService {
    Mono<FanVoteDto> saveFanVote(@Valid FanVoteDto fanVoteDto);
}
