package com.VotingSystem.repositories;

import com.VotingSystem.entitiesView.Voter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface VoterRepository extends CrudRepository<Voter, Long> {

    @Override
    List<Voter> findAll();
}
