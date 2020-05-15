package com.VotingSystem.repositories;

import com.VotingSystem.entitiesView.Candidate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

    @Query(value = "SELECT  candidate_id, COUNT(*) AS Number_of_votes FROM candidate_voter GROUP BY candidate_id ;  ",
            nativeQuery = true)
    List<Object[]> findByNumberOfVoters();

}
