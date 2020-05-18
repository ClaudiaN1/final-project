package com.VotingSystem.entitiesView;

import com.VotingSystem.entities.Cnp;
import com.VotingSystem.entities.Name;
import com.VotingSystem.entities.SeriesNumbers;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Data
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

    private String fileType;

    @Transient
    private MultipartFile file;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Voter> voters;




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

    public Candidate(Name candidateName, Cnp candidateCnp, SeriesNumbers candidateSeriesNumbers, String fileType) {
        this.candidateName = candidateName;
        this.candidateCnp = candidateCnp;
        this.candidateSeriesNumbers = candidateSeriesNumbers;
        this.fileType = fileType;
    }
}
