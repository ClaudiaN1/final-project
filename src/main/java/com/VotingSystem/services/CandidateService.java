package com.VotingSystem.services;

import com.VotingSystem.entitiesView.Candidate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CandidateService {

    List<Object[]> getVoterByCandidate();

    Iterable<Candidate> listAllCandidates();

    Candidate getCandidateById(Long id);

    Candidate saveCandidate(Candidate candidate);

    void deleteCandidate(Long id);

    boolean saveDataFromUploadFile(MultipartFile file);


/*
    void saveCandidateData();
*/

}
