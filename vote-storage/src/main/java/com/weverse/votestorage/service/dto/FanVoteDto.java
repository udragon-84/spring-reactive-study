package com.weverse.votestorage.service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FanVoteDto {
    private String id; // kafka send uuid
    private String userId;
    private String artistName;
    private LocalDateTime createdAt;
}
