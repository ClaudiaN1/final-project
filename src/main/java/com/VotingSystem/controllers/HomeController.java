package com.VotingSystem.controllers;

import com.VotingSystem.entitiesView.Candidate;
import com.VotingSystem.entitiesView.Voter;
import com.VotingSystem.repositories.CandidateRepository;
import com.VotingSystem.services.CandidateService;
import com.VotingSystem.services.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    CandidateService candidateService;

    @Autowired
    private VoterService voterService;

    public HomeController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/view")
    public String displayHome(Model model) {
        Iterable<Candidate> candidates = candidateService.listAllCandidates();
        model.addAttribute("candidateList", candidates);

        model.addAttribute("candidates",
                candidateService.getVoterByCandidate());

        Set<Voter> candidateSet = voterService.getVoters();
        int voterCount = candidateSet.size();
        model.addAttribute("voterCount",voterCount);

        return "main/home";
    }

}