package com.VotingSystem.services;

import com.VotingSystem.entitiesView.entitiesDTO.UserRegistrationDto;
import com.VotingSystem.entitiesView.entitiesSecurity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Users findByEmail(String email);

    Users save(UserRegistrationDto registration);

    void updatePassword(String password, Long userId);
}
