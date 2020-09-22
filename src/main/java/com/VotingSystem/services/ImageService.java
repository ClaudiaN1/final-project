package com.VotingSystem.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long candidateId, MultipartFile file);
}