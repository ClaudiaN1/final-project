package com.VotingSystem.services;

import com.VotingSystem.entities.Address;
import com.VotingSystem.entities.Cnp;
import com.VotingSystem.entities.Name;
import com.VotingSystem.entities.SeriesNumbers;
import com.VotingSystem.entitiesView.Voter;
import com.VotingSystem.repositories.VoterRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class VoterServiceImpl implements VoterService {

    private VoterRepository voterRepository;

    @Autowired
    public void setVoterRepository(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    public Set<Voter> getVoters() {
        Set<Voter> voterSet = new HashSet<>();
        voterRepository.findAll().iterator().forEachRemaining(voterSet::add);
        return voterSet;
    }

    @Override
    public Iterable<Voter> listAllVoters() {
        return voterRepository.findAll();
    }

    @Override
    public Voter findByVoterCnp(Cnp cnp) {
        return voterRepository.findByVoterCnp(cnp);
    }

   /* @Override
    public boolean findByVoterCnp(Cnp cnp) {
        *//*fun <Voter, Cnp> CrudRepository<Voter, Cnp>.findByVoterCnp(cnp: Cnp): Voter? = findByVoterCnp(cnp).orElse(null);*//*
        return voterRepository.findByVoterCnp(cnp);
    }*/

    @Override
    public Voter getVoterById(Long id) {
        return voterRepository.findById(id).get();
    }

    @Override
    public Voter saveVoter(Voter voter) {
        return voterRepository.save(voter);
    }

    @Override
    public void deleteVoter(Long id) {
        voterRepository.deleteById(id);
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
                voterRepository.save(new Voter(
                        (new Name(row[0], row[1])),
                        (new Cnp(row[2])),
                        (new SeriesNumbers(row[3], row[4])),
                        (new Address(row[5], row[6], row[7], row[8], row[9], row[10]))
                ));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}