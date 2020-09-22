package com.VotingSystem.services;

import com.VotingSystem.entities.Cnp;
import com.VotingSystem.entities.Name;
import com.VotingSystem.entities.SeriesNumbers;
import com.VotingSystem.entitiesView.Candidate;
import com.VotingSystem.repositories.CandidateRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository candidateRepository;

    @Autowired
    public void setCandidateRepository(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Object[]> getVoterByCandidate() {
        return candidateRepository.findByNumberOfVoters();
    }

    @Override
    public String getAll() {
        return candidateRepository.countAll();
    }

    @Override
    public void updateNumara(int numara, Long candidateId) {
        candidateRepository.updateCountVote(numara, candidateId);
    }

    @Override
    public Iterable<Candidate> listAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id).get();
    }

    @Override
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllCandidates() {
        candidateRepository.deleteAll();
    }

    @Override
    public boolean saveDataFromUploadFile(MultipartFile file) {
        boolean isFlag = false;
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension.equalsIgnoreCase("csv")) {
            isFlag = readDataFromCsv(file);
        }
        return isFlag;
    }

    private boolean readDataFromCsv(MultipartFile file) {
        try {
            InputStreamReader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> rows = csvReader.readAll();
            for (String[] row : rows) {
                candidateRepository.save(new Candidate(
                        (new Name(row[0], row[1])),
                        (new Cnp(row[2])),
                        (new SeriesNumbers(row[3], row[4])),
                        (row[5]), (row[6])
                ));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}