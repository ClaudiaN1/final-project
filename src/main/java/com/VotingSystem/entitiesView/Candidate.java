package com.VotingSystem.entitiesView;

import com.VotingSystem.entities.Cnp;
import com.VotingSystem.entities.Name;
import com.VotingSystem.entities.SeriesNumbers;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long candidateId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "FIRST_NAME")),
            @AttributeOverride(name = "lastName", column = @Column(name = "LAST_NAME"))
    })
    private Name candidateName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "cnp", column = @Column(name = "CNP"))
    })
    private Cnp candidateCnp;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "series", column = @Column(name = "SERIES")),
            @AttributeOverride(name = "numbers", column = @Column(name = "NUMBERS"))

    })
    private SeriesNumbers candidateSeriesNumbers;

    private String partitionName;

    private String description;

    @Lob
    private Byte[] image;

    @Transient
    private MultipartFile file;

    private int numara;

   /* @OneToMany(mappedBy = "candidate", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Voter> voters;*/

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "candidate_voter",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "voter_id"))
    private List<Voter> voters;

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(long candidateId) {
        this.candidateId = candidateId;
    }

    public Name getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(Name candidateName) {
        this.candidateName = candidateName;
    }

    public Cnp getCandidateCnp() {
        return candidateCnp;
    }

    public void setCandidateCnp(Cnp candidateCnp) {
        this.candidateCnp = candidateCnp;
    }

    public SeriesNumbers getCandidateSeriesNumbers() {
        return candidateSeriesNumbers;
    }

    public void setCandidateSeriesNumbers(SeriesNumbers candidateSeriesNumbers) {
        this.candidateSeriesNumbers = candidateSeriesNumbers;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }

    public String getPartitionName() {
        return partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumara() {
        return numara;
    }

    public void setNumara(int numara) {
        this.numara = numara;
    }

  /* @Embedded
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "STREET")),
            @AttributeOverride(name = "buildingNumber", column = @Column(name = "BUILDING")),
            @AttributeOverride(name = "stairCase", column = @Column(name = "STAIR_CASE")),
            @AttributeOverride(name = "apartment", column = @Column(name = "APARTMENT"))
    })
    private Address candidateAddress;*/

    public Candidate() {
    }

    public Candidate(Name candidateName, Cnp candidateCnp, SeriesNumbers candidateSeriesNumbers, String partitionName, String description) {
        this.candidateName = candidateName;
        this.candidateCnp = candidateCnp;
        this.candidateSeriesNumbers = candidateSeriesNumbers;
        this.partitionName = partitionName;
        this.description = description;
        this.numara = 8;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "candidateId=" + candidateId +
                ", candidateName=" + candidateName +
                ", candidateCnp=" + candidateCnp +
                ", candidateSeriesNumbers=" + candidateSeriesNumbers +
                ", partitionName='" + partitionName + '\'' +
                ", description='" + description + '\'' +
                ", image=" + Arrays.toString(image) +
                ", file=" + file +
                ", numara=" + numara +
                ", voters=" + voters +
                '}';
    }
}