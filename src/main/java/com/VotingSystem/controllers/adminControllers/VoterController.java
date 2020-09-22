package com.VotingSystem.controllers.adminControllers;

import com.VotingSystem.entitiesView.Candidate;
import com.VotingSystem.entitiesView.Voter;
import com.VotingSystem.services.CandidateService;
import com.VotingSystem.services.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/voter")
public class VoterController {

    private VoterService voterService;
    private CandidateService candidateService;

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Autowired
    public void setVoterService(VoterService voterService) {
        this.voterService = voterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayVoters(Model model) {
        model.addAttribute("voter", new Voter());
        model.addAttribute("voters", voterService.listAllVoters());
        return "voter/list-voters";
    }

    @RequestMapping("/{voterId}")
    public String showVoter(@PathVariable Long voterId, Model model) {
        model.addAttribute("voter", voterService.getVoterById(voterId));
        return "voter/show-voter";
    }

    @RequestMapping("/edit/{voterId}")
    public String edit(@PathVariable Long voterId, Model model) {
        model.addAttribute("voter",
                voterService.getVoterById(voterId));
        return "voter/new-voter";
    }

    @RequestMapping("/new")
    public String displayVoterForm(Model model) {
        model.addAttribute("voter", new Voter());
        Iterable<Candidate> candidates = candidateService.listAllCandidates();
        model.addAttribute("allCandidates", candidates);
        return "voter/new-voter";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveVoter(Voter voter) {
        voterService.saveVoter(voter);
        return "redirect:/voter";
    }

    @RequestMapping("/delete/{voterId}")
    public String delete(@PathVariable Long voterId) {
        voterService.deleteVoter(voterId);
        return "redirect:/voter";
    }

    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String displayUploadFileVoters(Model model) {
        model.addAttribute("voter", new Voter());
        Iterable<Voter> voters = voterService.listAllVoters();
        model.addAttribute("voters", voters);
        return "voter/uploadFile-voters";
    }

    @RequestMapping(value = "fileupload", method = RequestMethod.POST)
    public String uploadFile(@ModelAttribute Voter voter, RedirectAttributes redirectAttributes) {
        boolean isFlag = voterService.saveDataFromUploadFile(voter.getFile());
        if (isFlag) {
            redirectAttributes.addFlashAttribute("successMessage", "File Upload Successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "File Upload not done, Please try again!");
        }
        return "redirect:/voter";
    }

    /*@GetMapping("/new")
    public String displayVoterForm(Model model) {

        Voter voter = new Voter();
        Iterable<Candidate> candidates = candidateService.listAllCandidates();
        model.addAttribute("voter", voter);
        model.addAttribute("allCandidates", candidates);
        return "voter/new-voter";
    }*/
}
