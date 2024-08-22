package com.weverse.voteprocessor.service.convert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.weverse.voteprocessor.repository.entity.FanVoteEventsEntity;
import com.weverse.voteprocessor.service.dto.FanVoteDto;
import com.weverse.voteprocessor.service.dto.FanVoteProcessDto;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
public class FanVoteProcessConvert {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        // Java 8 날짜/시간 타입을 처리하기 위해 모듈을 등록
        objectMapper.registerModule(new JavaTimeModule());
        // LocalDateTime을 타임스탬프 대신 ISO-8601 형식으로 직렬화하도록 설정
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static FanVoteEventsEntity convertToFanVotoEventsEntity(FanVoteDto fanVoteDto) {
        try {
            UUID uuid = UUID.randomUUID();
            LocalDateTime localDateTime = LocalDateTime.now();
            fanVoteDto.setId(uuid);
            fanVoteDto.setCreatedAt(localDateTime);

            return FanVoteEventsEntity.builder()
                    .id(uuid)
                    .userId(fanVoteDto.getUserId())
                    .jsonData(objectMapper.writeValueAsString(fanVoteDto))
                    .createdAt(localDateTime)
                    .build();
        } catch (Exception e) {
            log.error("convertToFanVoteProcessDto Exception: {}", e.getMessage(), e);
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
