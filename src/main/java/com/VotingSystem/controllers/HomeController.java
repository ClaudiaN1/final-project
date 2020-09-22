package com.VotingSystem.controllers;

import com.VotingSystem.entitiesView.Candidate;
import com.VotingSystem.services.CandidateService;
import com.VotingSystem.services.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private CandidateService candidateService;
    private VoterService voterService;

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Autowired
    public void setVoterService(VoterService voterService) {
        this.voterService = voterService;
    }

    @GetMapping("/view")
    public String displayHome(Model model) {
        Iterable<Candidate> candidates = candidateService.listAllCandidates();
        model.addAttribute("candidateList", candidates);

        model.addAttribute("candidates",
                candidateService.getVoterByCandidate());

        String countVoter = candidateService.getAll();
        model.addAttribute("voterCount", countVoter);

        return "main/home";
    }
}