package com.VotingSystem.entitiesView.entitiesSecurity;

import lombok.Data;

import java.util.Map;

@Data
public class Email {

    private String from;
    private String to;
    private String subject;
    private Map<String, Object> model;
}
