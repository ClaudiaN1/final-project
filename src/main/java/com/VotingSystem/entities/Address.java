package com.VotingSystem.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    public String country;
    public String locality;
    public String street;
    public String buildingNumber;
    public String stairCase;
    public String apartment;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

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

    public Address(String country, String locality, String street, String buildingNumber, String stairCase, String apartment) {
        this.country = country;
        this.locality = locality;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.stairCase = stairCase;
        this.apartment = apartment;
    }

    public Address() {
    }
}