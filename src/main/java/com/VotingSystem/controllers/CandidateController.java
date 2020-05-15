package com.VotingSystem.controllers;

import com.VotingSystem.entitiesView.Candidate;
import com.VotingSystem.repositories.CandidateRepository;
import com.VotingSystem.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/candidate")
public class CandidateController {

    private CandidateService candidateService;

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayCandidates(Model model) {
        model.addAttribute("candidates", candidateService.listAllCandidates());
        return "candidate/list-candidates";
    }

    @RequestMapping("/{candidateId}")
    public String showCandidate(@PathVariable Long candidateId, Model model){
        model.addAttribute("candidate", candidateService.getCandidateById(candidateId));
        return "candidate/show-candidate";
    }

    @RequestMapping("/edit/{candidateId}")
    public String edit(@PathVariable Long candidateId, Model model){
        model.addAttribute("candidate", candidateService.getCandidateById(candidateId));
        return "candidate/new-candidate";
    }

    @RequestMapping("/new")
    public String displayCandidateForm(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "candidate/new-candidate";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveCandidate(Candidate candidate) {
        candidateService.saveCandidate(candidate);
        return "redirect:/candidate/" + candidate.getCandidateId();
    }

    @RequestMapping("/delete/{candidateId}")
    public String delete(@PathVariable Long candidateId){
        candidateService.deleteCandidate(candidateId);
        return "redirect:/candidate";
    }


}