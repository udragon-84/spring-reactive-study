package com.weverse.voteprocessor.service.convert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weverse.voteprocessor.repository.entity.FanVoteEventsEntity;
import com.weverse.voteprocessor.service.dto.FanVoteDto;
import com.weverse.voteprocessor.service.dto.FanVoteProcessDto;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
public class FanVoteProcessConvert {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static FanVoteEventsEntity convertToFanVotoEventsEntity(FanVoteDto fanVoteDto) {
        try {
            return FanVoteEventsEntity.builder()
                    .id(UUID.randomUUID())
                    .userId(fanVoteDto.getUserId())
                    .jsonData(objectMapper.writeValueAsString(fanVoteDto))
                    .createdAt(LocalDateTime.now())
                    .build();
        } catch (Exception e) {
            log.info("convertToFanVoteProcessDto Exception: ", e);
            throw new RuntimeException(e);
        }
    }

    public static FanVoteProcessDto convertToFanVoteProcessDto(FanVoteEventsEntity fanVoteEventsEntity) {
        return FanVoteProcessDto.builder()
                .id(fanVoteEventsEntity.getId())
                .userId(fanVoteEventsEntity.getUserId())
                .jsonData(fanVoteEventsEntity.getJsonData())
                .createdAt(fanVoteEventsEntity.getCreatedAt())
                .build();
    }
}
