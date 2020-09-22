package com.VotingSystem.controllers.adminControllers;

import com.VotingSystem.entitiesView.Candidate;
import com.VotingSystem.services.CandidateService;
import com.VotingSystem.services.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/candidate")
public class CandidateController {

    private CandidateService candidateService;
    private ImageService imageService;

    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayCandidates(Model model) {
        model.addAttribute("candidate", new Candidate());
        model.addAttribute("candidates", candidateService.listAllCandidates());
        return "candidate/list-candidates";
    }

    @RequestMapping("/{candidateId}")
    public String showCandidate(@PathVariable Long candidateId, Model model) {
        model.addAttribute("candidate",
                candidateService.getCandidateById(candidateId));
        return "candidate/show-candidate";
    }

    @RequestMapping("/edit/{candidateId}")
    public String edit(@PathVariable Long candidateId, Model model) {
        model.addAttribute("candidate",
                candidateService.getCandidateById(candidateId));
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
        return "redirect:/candidate";
    }

    @RequestMapping("/delete/{candidateId}")
    public String delete(@PathVariable Long candidateId) {
        candidateService.deleteCandidate(candidateId);
        return "redirect:/candidate";
    }

    @RequestMapping(value = "deleteAll", method = RequestMethod.DELETE)
    public String deleteAll(@PathVariable String allCandidates) {
        candidateService.deleteAllCandidates();
        return "redirect:/candidate";
    }

    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String displayUploadFileCandidates(Model model) {
        model.addAttribute("candidate", new Candidate());
        Iterable<Candidate> candidates = candidateService.listAllCandidates();
        model.addAttribute("candidates", candidates);
        return "candidate/uploadFile-candidates";
    }

    @RequestMapping(value = "fileupload", method = RequestMethod.POST)
    public String uploadFile(@ModelAttribute Candidate candidate, RedirectAttributes redirectAttributes) {
        boolean isFlag = candidateService.saveDataFromUploadFile(candidate.getFile());
        if (isFlag) {
            redirectAttributes.addFlashAttribute("successMessage", "File Upload Successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "File Upload not done, Please try again!");
        }
        return "redirect:/candidate";
    }

    @RequestMapping("/{candidateId}/image")
    public String showUploadForm(@PathVariable String candidateId, Model model) {
        model.addAttribute("candidate",
                candidateService.getCandidateById(Long.valueOf(candidateId)));
        return "candidate/imageUploadForm";
    }

    @RequestMapping(value = "/{candidateId}/image", method = RequestMethod.POST)
    public String handleImagePost(@PathVariable String candidateId,
                                  @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(candidateId), file);
        return "redirect:/candidate/";
    }

    @RequestMapping("/{candidateId}/candidateImage")
    public void renderImageFromDB(@PathVariable String candidateId, HttpServletResponse response) throws IOException {
        Candidate candidate = candidateService.getCandidateById(Long.valueOf(candidateId));

        if (candidate.getImage() != null) {
            byte[] byteArray = new byte[candidate.getImage().length];
            int i = 0;

            for (Byte wrappedByte : candidate.getImage()) {
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}