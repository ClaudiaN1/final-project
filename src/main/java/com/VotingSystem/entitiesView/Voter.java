package com.VotingSystem.entitiesView;

import com.VotingSystem.entities.Address;
import com.VotingSystem.entities.Cnp;
import com.VotingSystem.entities.Name;
import com.VotingSystem.entities.SeriesNumbers;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;

@Data
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
            @AttributeOverride(name = "street", column = @Column(name = "STREET")),
            @AttributeOverride(name = "buildingNumber", column = @Column(name = "BUILDING")),
            @AttributeOverride(name = "stairCase", column = @Column(name = "STAIR_CASE")),
            @AttributeOverride(name = "apartment", column = @Column(name = "APARTMENT"))
    })
    private Address voterAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    private Candidate candidate;

}
