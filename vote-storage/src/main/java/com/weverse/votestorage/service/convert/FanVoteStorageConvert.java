package com.weverse.votestorage.service.convert;

import com.weverse.votestorage.repository.entity.FanVotesAnalyzeEntity;
import com.weverse.votestorage.service.dto.FanVoteDto;

public class FanVoteStorageConvert {
    public static FanVotesAnalyzeEntity toEntity(FanVoteDto fanVoteDto) {
        return FanVotesAnalyzeEntity.builder()
                .kafkaSendId(fanVoteDto.getId())
                .userId(fanVoteDto.getUserId())
                .artistName(fanVoteDto.getArtistName())
                .build();
    }

    public static FanVoteDto toDto(FanVotesAnalyzeEntity fanVoteEntity) {
        return FanVoteDto.builder()
                .id(fanVoteEntity.getKafkaSendId())
                .userId(fanVoteEntity.getUserId())
                .artistName(fanVoteEntity.getArtistName())
                .createdAt(fanVoteEntity.getCreatedAt())
                .build();
    }
}
