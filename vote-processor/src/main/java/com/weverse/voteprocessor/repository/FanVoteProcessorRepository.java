package com.weverse.voteprocessor.repository;

import com.weverse.voteprocessor.repository.entity.FanVoteEventsEntity;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FanVoteProcessorRepository extends ReactiveCassandraRepository<FanVoteEventsEntity, Long>{
}
