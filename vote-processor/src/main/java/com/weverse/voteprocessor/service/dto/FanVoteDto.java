package com.weverse.voteprocessor.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FanVoteDto {
    private UUID id;
    @NotNull
    private String userId;
    @NotNull
    private String artistName;
    private LocalDateTime createdAt;
}
