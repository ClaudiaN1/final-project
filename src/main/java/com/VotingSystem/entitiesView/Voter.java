package com.VotingSystem.entitiesView;

import com.VotingSystem.entities.Address;
import com.VotingSystem.entities.Cnp;
import com.VotingSystem.entities.Name;
import com.VotingSystem.entities.SeriesNumbers;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long voterId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "FIRST_NAME")),
            @AttributeOverride(name = "lastName", column = @Column(name = "LAST_NAME"))
    })
    private Name voterName;

    @Embedded
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "cnp", column = @Column(name = "CNP"))
    })
    private Cnp voterCnp;

    @Embedded
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "series", column = @Column(name = "SERIES")),
            @AttributeOverride(name = "numbers", column = @Column(name = "NUMBERS"))

    })
    private SeriesNumbers voterSeriesNumbers;

    @Embedded
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "COUNTRY")),
            @AttributeOverride(name = "locality", column = @Column(name = "LOCALITY")),
            @AttributeOverride(name = "street", column = @Column(name = "STREET")),
            @AttributeOverride(name = "buildingNumber", column = @Column(name = "BUILDING")),
            @AttributeOverride(name = "stairCase", column = @Column(name = "STAIR_CASE")),
            @AttributeOverride(name = "apartment", column = @Column(name = "APARTMENT"))
    })
    private Address voterAddress;

    @Transient
    private MultipartFile file;

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "candidate_voter",
            joinColumns = @JoinColumn(name = "voter_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id"))
    private List<Candidate> candidates;

    public long getVoterId() {
        return voterId;
    }

    public void setVoterId(long voterId) {
        this.voterId = voterId;
    }

    public Name getVoterName() {
        return voterName;
    }

    public void setVoterName(Name voterName) {
        this.voterName = voterName;
    }

    public Cnp getVoterCnp() {
        return voterCnp;
    }

    public void setVoterCnp(Cnp voterCnp) {
        this.voterCnp = voterCnp;
    }

    public SeriesNumbers getVoterSeriesNumbers() {
        return voterSeriesNumbers;
    }

    public void setVoterSeriesNumbers(SeriesNumbers voterSeriesNumbers) {
        this.voterSeriesNumbers = voterSeriesNumbers;
    }

    public Address getVoterAddress() {
        return voterAddress;
    }

    public void setVoterAddress(Address voterAddress) {
        this.voterAddress = voterAddress;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public Voter() {
    }

    public Voter(Name voterName, Cnp voterCnp,
                 SeriesNumbers voterSeriesNumbers,
                 Address voterAddress
    ) {
        this.voterName = voterName;
        this.voterCnp = voterCnp;
        this.voterSeriesNumbers = voterSeriesNumbers;
        this.voterAddress = voterAddress;
    }
}