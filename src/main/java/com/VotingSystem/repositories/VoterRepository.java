package com.VotingSystem.repositories;

import com.VotingSystem.entities.Cnp;
import com.VotingSystem.entitiesView.Voter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoterRepository extends CrudRepository<Voter, Long> {

    Voter findByVoterCnp(Cnp cnp);

    @Override
    List<Voter> findAll();
}