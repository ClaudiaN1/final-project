package com.VotingSystem.repositories;

import com.VotingSystem.entitiesView.Candidate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Candidate c set c.numara= 'numara' where c.candidateId= candidateId")
    void updateCountVote(@Param("numara") int numara, @Param("candidateId") Long candidateId);

    @Query(value = "SELECT candidate_id, COUNT(*) AS Number_of_votes FROM candidate_voter GROUP BY candidate_id ;  ",
            nativeQuery = true)
    List<Object[]> findByNumberOfVoters();

    @Query(value = "SELECT  COUNT(candidate_id) FROM candidate_voter;", nativeQuery = true)
    String countAll();
}