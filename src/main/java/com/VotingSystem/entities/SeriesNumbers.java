package com.VotingSystem.entities;

import javax.persistence.Embeddable;

@Embeddable
public class SeriesNumbers {

    private String series;
    private String numbers;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public SeriesNumbers(String series, String numbers) {
        this.series = series;
        this.numbers = numbers;
    }

    public SeriesNumbers() {
    }
}