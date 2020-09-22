package com.VotingSystem.services;

import com.VotingSystem.entitiesView.Candidate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CandidateService {

    List<Object[]> getVoterByCandidate();

    String getAll();

    void updateNumara(int numara, Long candidateId);

    Iterable<Candidate> listAllCandidates();

    Candidate getCandidateById(Long id);

    Candidate saveCandidate(Candidate candidate);

    void deleteCandidate(Long id);

    void deleteAllCandidates();

    boolean saveDataFromUploadFile(MultipartFile file);
}