package com.weverse.voteprocessor.service.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FanVoteProcessDto {
    private UUID id;
    private String userId;
    private LocalDateTime createdAt;
    private String jsonData;
}
