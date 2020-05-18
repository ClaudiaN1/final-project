package com.VotingSystem.services;

import com.VotingSystem.entities.Cnp;
import com.VotingSystem.entities.Name;
import com.VotingSystem.entities.SeriesNumbers;
import com.VotingSystem.entitiesView.Candidate;
import com.VotingSystem.repositories.CandidateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Object[]> getVoterByCandidate() {
        return candidateRepository.findByNumberOfVoters();
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
    public boolean saveDataFromUploadFile(MultipartFile file) {
        boolean isFlag = false;
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension.equalsIgnoreCase("json")) {
            isFlag = readDataFromJson(file);
        } else if (extension.equalsIgnoreCase("csv")) {
            isFlag = readDataFromCsv(file);
        } else if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
            isFlag = readDataFromExcel(file);
        }
        return isFlag;
    }

    private boolean readDataFromJson(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            List<Candidate> candidates = Arrays.asList(mapper.readValue(inputStream, Candidate.class));
            if (candidates != null && candidates.size() > 0) {
                for (Candidate candidate : candidates) {
                    candidate.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
                    candidateRepository.save(candidate);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
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
                        FilenameUtils.getExtension(file.getOriginalFilename())
                ));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean readDataFromExcel(MultipartFile file) {
        Workbook workbook = getWorkBook(file);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();
        rows.next();
        while (rows.hasNext()) {
            Row row = rows.next();
            Candidate candidate = new Candidate();
            if (row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING) {
                candidate.setCandidateName(new Name(row.getCell(0).getStringCellValue(), row.getCell(0).getStringCellValue()));
            }
            if (row.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                String cnp = NumberToTextConverter.toText(row.getCell(2).getNumericCellValue());
                candidate.setCandidateCnp(new Cnp(cnp));
            }
            if (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
                candidate.setCandidateSeriesNumbers(new SeriesNumbers(row.getCell(2).getStringCellValue(), row.getCell(2).getStringCellValue()));
            }
            candidate.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
            candidateRepository.save(candidate);
        }
        return true;
    }

    private Workbook getWorkBook(MultipartFile file) {
        Workbook workbook = null;
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            if (extension.equalsIgnoreCase("xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());

            } else if (extension.equalsIgnoreCase("xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
/*    String line = "";

    public void saveCandidateData() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/candidate.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Candidate candidate = new Candidate();
                candidate.setCandidateName(new Name(data[0], data[1]));
                candidate.setCandidateCnp(new Cnp(data[2]));
                candidate.setCandidateSeriesNumbers(new SeriesNumbers(data[3], data[4]));
                candidateRepository.save(candidate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}
