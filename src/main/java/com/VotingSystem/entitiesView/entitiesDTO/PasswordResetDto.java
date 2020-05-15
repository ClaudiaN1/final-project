package com.VotingSystem.entitiesView.entitiesDTO;

import com.VotingSystem.constraint.FieldMatch;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class PasswordResetDto {

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String token;
}
