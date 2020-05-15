package com.VotingSystem.services;

import com.VotingSystem.entitiesView.entitiesDTO.UserRegistrationDto;
import com.VotingSystem.entitiesView.entitiesSecurity.Role;
import com.VotingSystem.entitiesView.entitiesSecurity.Users;
import com.VotingSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Users findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Users save(UserRegistrationDto registration){
        Users users = new Users();
        users.setFirstName(registration.getFirstName());
        users.setLastName(registration.getLastName());
        users.setEmail(registration.getEmail());
        users.setPassword(passwordEncoder.encode(registration.getPassword()));
        users.setRoles(asList(new Role("ROLE_USER")));
        return userRepository.save(users);
    }

    @Override
    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(email);
        if (users == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(users.getEmail(),
                users.getPassword(),
                mapRolesToAuthorities(users.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
