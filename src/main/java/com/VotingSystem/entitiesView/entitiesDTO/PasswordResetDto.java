package com.VotingSystem.entitiesView.entitiesDTO;

import com.VotingSystem.constraint.FieldMatch;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@FieldMatch(first = "password", second = "confirmPassword", message = "Password does not match, please enter again")
public class PasswordResetDto {

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String token;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}