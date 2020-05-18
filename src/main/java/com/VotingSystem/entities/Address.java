package com.VotingSystem.entities;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {

    public String street;
    public String buildingNumber;
    public String stairCase;
    public String apartment;

    public Address(String street, String buildingNumber, String stairCase, String apartment) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.stairCase = stairCase;
        this.apartment = apartment;
    }

    public Address() {
    }
}
