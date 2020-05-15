package com.VotingSystem.entities;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class SeriesNumbers {

    private String series;
    private String numbers;
}
