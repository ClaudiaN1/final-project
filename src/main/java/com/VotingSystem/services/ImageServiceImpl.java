package com.VotingSystem.services;

import com.VotingSystem.entitiesView.Candidate;
import com.VotingSystem.repositories.CandidateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private CandidateRepository candidateRepository;

    @Autowired
    public void setCandidateRepository(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

  /*  public ImageServiceImpl(CandidateRepository candidateService) {
        this.candidateRepository = candidateService;
    }*/

    @Override
    @Transactional
    public void saveImageFile(Long candidateId, MultipartFile file) {

        try {
            Candidate candidate = candidateRepository.findById(candidateId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            candidate.setImage(byteObjects);

            candidateRepository.save(candidate);
        } catch (IOException e) {
            //log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}