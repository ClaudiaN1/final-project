package com.VotingSystem.services;

import com.VotingSystem.entitiesView.entitiesDTO.UserRegistrationDto;
import com.VotingSystem.entitiesView.entitiesSecurity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);

    void updatePassword(String password, Long userId);

}