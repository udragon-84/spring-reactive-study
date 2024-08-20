package com.weverse.votegateway.repository;

import com.weverse.votegateway.repository.entity.FanVotesEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanVotesRepository extends ReactiveCrudRepository<FanVotesEntity, Long> {
}
