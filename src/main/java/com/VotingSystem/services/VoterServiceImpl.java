package com.VotingSystem.services;

import com.VotingSystem.entitiesView.Voter;
import com.VotingSystem.repositories.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VoterServiceImpl implements VoterService{

    @Autowired
    private VoterRepository voterRepository;

    public Set<Voter> getVoters() {
        Set<Voter> voterSet = new HashSet<>();
        voterRepository.findAll().iterator().forEachRemaining(voterSet::add);
        return voterSet;
    }
}
