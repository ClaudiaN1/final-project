package com.VotingSystem.services;

import com.VotingSystem.entities.Cnp;
import com.VotingSystem.entitiesView.Voter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface VoterService {

    Set<Voter> getVoters();

    Iterable<Voter> listAllVoters();

    /*boolean findByVoterCnp(Cnp cnp);*/

    Voter findByVoterCnp(Cnp cnp);

    Voter getVoterById(Long id);

    Voter saveVoter(Voter voter);

    void deleteVoter(Long id);

    boolean saveDataFromUploadFile(MultipartFile file);
}