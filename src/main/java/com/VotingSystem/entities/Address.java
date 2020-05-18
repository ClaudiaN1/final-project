package com.VotingSystem.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    public String street;
    public String buildingNumber;
    public String stairCase;
    public String apartment;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getStairCase() {
        return stairCase;
    }

    public void setStairCase(String stairCase) {
        this.stairCase = stairCase;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public Address(String street, String buildingNumber, String stairCase, String apartment) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.stairCase = stairCase;
        this.apartment = apartment;
    }

    public Address() {
    }
}
