package com.VotingSystem.controllers;

import com.VotingSystem.entitiesView.Candidate;
import com.VotingSystem.entitiesView.Voter;
import com.VotingSystem.repositories.CandidateRepository;
import com.VotingSystem.repositories.VoterRepository;
import com.VotingSystem.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
@RequestMapping("/voter")
public class VoterController {

    @Autowired
    VoterRepository voterRepository;

    @Autowired
    CandidateService candidateService;

    @GetMapping
    public String displayVoters(Model model) {
        List<Voter> voters = voterRepository.findAll();
        model.addAttribute("voters", voters);
        return "voter/list-voters";
    }

    @GetMapping("/new")
    public String displayVoterForm(Model model) {

        Voter voter = new Voter();
        Iterable<Candidate> candidates = candidateService.listAllCandidates();
        model.addAttribute("voter", voter);
        model.addAttribute("allCandidates", candidates);
        return "voter/new-voter";
    }

    // hasRole, hasAuthority, hasAnyRole, hasAnyAuthority
    @PostMapping("/save")
    public String createVoter(Voter voter, Model model) {
        voterRepository.save(voter);
        return "redirect:/voter";
    }
}
