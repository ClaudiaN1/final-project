package com.VotingSystem.controllers.userControllers;

import com.VotingSystem.entitiesView.Candidate;
import com.VotingSystem.entitiesView.Voter;
import com.VotingSystem.services.CandidateService;
import com.VotingSystem.services.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/userVote")
public class UserVoterController {

    private VoterService voterService;
    private CandidateService candidateService;

    @Autowired
    public void setVoterService(VoterService voterService) {
        this.voterService = voterService;
    }

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayVoters(Model model) {
        model.addAttribute("voter", new Voter());
        return "users/register-voter";
    }

    @RequestMapping(value = "verify")
    public String verifyUserCnp(@Valid @ModelAttribute("verify") Voter voter,
                                BindingResult result) {

        Voter existing = voterService.findByVoterCnp(voter.getVoterCnp());
        if (existing != null) {
            return "redirect:/userVote/choo";
        }
        return "redirect:/userVote?error";
    }

    @RequestMapping(value = "choose", method = RequestMethod.GET)
    public String displayVoterForm(Model model) {
        Iterable<Candidate> candidates = candidateService.listAllCandidates();
        model.addAttribute("candidateList", candidates);
        return "users/chooseCandidate";
    }

    @GetMapping("/choo")
    public String displayCandidates(Model model) {
        Voter voter = new Voter();
        model.addAttribute("voter", voter);
        model.addAttribute("candidate", new Candidate());
        model.addAttribute("mere", candidateService.listAllCandidates());
        Iterable<Candidate> candidates = candidateService.listAllCandidates();
        model.addAttribute("allCandidates", candidates);
        return "users/chooseCandidate";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveVoter(Voter voter) {
        voterService.saveVoter(voter);
        return "redirect:/utilizator/";

    }

   /* @RequestMapping("/count")
    public String countVoter(Model model){
        model.addAttribute("candidates",
                candidateService.getVoterByCandidate());
        return
    }*/

    @RequestMapping("/numara/{candidateId}")
    public String countVote(@PathVariable Long candidateId, Candidate candidate) {
        candidateService.updateNumara(4, candidateId);
        return "security/sectionsUser";
    }

    @GetMapping("/")
    public String done() {
        return "users/choose";
    }
}