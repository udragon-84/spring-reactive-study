package com.weverse.votestorage.repository.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Builder
@Table("fan_votes_analyze")
public class FanVotesAnalyzeEntity {

    @Id
    private Long id;

    @Column("kafkaSendId")
    private String kafkaSendId;

    @Column("userId")
    private String userId;

    @Column("artistName")
    private String artistName;

    @CreatedDate
    @Column("createdAt")
    private LocalDateTime createdAt;
}
