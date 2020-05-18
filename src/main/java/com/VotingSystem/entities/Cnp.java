package com.VotingSystem.entities;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Cnp {

    private String cnp;

    public Cnp(String cnp) {
        this.cnp = cnp;
    }

    public Cnp() {
    }
}
