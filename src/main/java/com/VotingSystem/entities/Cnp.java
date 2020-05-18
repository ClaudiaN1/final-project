package com.VotingSystem.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Cnp {

    private String cnp;

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Cnp(String cnp) {
        this.cnp = cnp;
    }

    public Cnp() {
    }
}
