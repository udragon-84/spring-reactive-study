package com.weverse.voteprocessor.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Table("fan_vote_events")
public class FanVoteEventsEntity {
    @Id
    @PrimaryKey
    private UUID id;

    @Column("userid")
    private String userId;

    @Column("createdat")
    private LocalDateTime createdAt;

    @Column("jsondata")
    private String jsonData;
}