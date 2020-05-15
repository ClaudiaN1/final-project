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
}
