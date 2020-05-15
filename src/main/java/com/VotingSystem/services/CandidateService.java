package com.VotingSystem.services;

import com.VotingSystem.entitiesView.Candidate;

import java.util.List;

public interface CandidateService {

    List<Object[]> getVoterByCandidate();

    Iterable<Candidate> listAllCandidates();

    Candidate getCandidateById(Long id);

    Candidate saveCandidate(Candidate candidate);

    void deleteCandidate(Long id);

}
