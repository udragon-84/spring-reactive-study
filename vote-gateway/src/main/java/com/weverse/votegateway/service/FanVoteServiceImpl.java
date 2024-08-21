package com.weverse.votegateway.service;

import com.weverse.votegateway.repository.FanVoteRepository;
import com.weverse.votegateway.service.convert.FanVoteConverter;
import com.weverse.votegateway.service.dto.FanVoteDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class FanVoteServiceImpl implements FanVoteService {

    @Autowired
    private FanVoteRepository fanVoteRepository;

    @Override
    @Transactional(readOnly = false)
    public Mono<FanVoteDto> saveFanVote(FanVoteDto fanVoteDto) {
        return this.fanVoteRepository.save(FanVoteConverter.toEntity(fanVoteDto))
                .map(FanVoteConverter::toDto);
    }
}
