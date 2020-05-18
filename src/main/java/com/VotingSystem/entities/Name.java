package com.VotingSystem.entities;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Name {

    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Name() {
    }
}
