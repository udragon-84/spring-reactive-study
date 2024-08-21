package com.weverse.voteprocessor.api;

import com.weverse.voteprocessor.api.response.VoteProcessorResponse;
import com.weverse.voteprocessor.service.VoteProcessorService;
import com.weverse.voteprocessor.service.dto.FanVoteDto;
import com.weverse.voteprocessor.service.dto.FanVoteProcessDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api")
public class VoteProcessorController {

    @Autowired
    private VoteProcessorService voteProcessorService;

    @PostMapping("/vote/processor")
    public Mono<VoteProcessorResponse<FanVoteProcessDto>> voteProcessor(@Valid @RequestBody FanVoteDto fanVoteDto) {
        return this.voteProcessorService.handleVoteProcessor(fanVoteDto)
                .map(dto -> VoteProcessorResponse.<FanVoteProcessDto>builder()
                        .message("fan vote success").result(true)
                        .httpStatus(HttpStatus.OK)
                        .data(dto)
                        .build());
    }

}
