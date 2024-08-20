package com.weverse.votegateway.service.dto;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Builder
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FanVoteDto {
    private Long id;
    @NotNull
    private String userId;
    @NotNull
    private String artistName;
    private LocalDateTime createdAt;
}
