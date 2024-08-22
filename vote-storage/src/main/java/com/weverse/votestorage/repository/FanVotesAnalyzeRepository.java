package com.weverse.votestorage.repository;

import com.weverse.votestorage.repository.entity.FanVotesAnalyzeEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanVotesAnalyzeRepository extends ReactiveCrudRepository<FanVotesAnalyzeEntity, Long> {
}
