package com.weverse.votegateway.service.convert;

import com.weverse.votegateway.repository.entity.FanVotesEntity;
import com.weverse.votegateway.service.dto.FanVoteDto;


public class FanVoteConverter {

    public static FanVotesEntity toEntity(FanVoteDto fanVoteDto) {
        return FanVotesEntity.builder()
                .userId(fanVoteDto.getUserId())
                .artistName(fanVoteDto.getArtistName())
                .build();
    }

    public static FanVoteDto toDto(FanVotesEntity fanVotesEntity) {
        return FanVoteDto.builder()
                .id(fanVotesEntity.getId())
                .userId(fanVotesEntity.getUserId())
                .artistName(fanVotesEntity.getArtistName())
                .createdAt(fanVotesEntity.getCreatedAt())
                .build();
    }
}
