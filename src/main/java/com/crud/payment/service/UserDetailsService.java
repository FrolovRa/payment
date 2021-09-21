package com.crud.payment.service;

import com.crud.payment.domain.User;
import com.crud.payment.exception.EntityNotFoundException;
import com.crud.payment.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails getUserDetails(String username) {
        Optional<User> optional = userRepository.findByName(username);
        if (!optional.isPresent()) {
            throw new EntityNotFoundException(User.class);
        }
        User user = optional.get();
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), new HashSet<>());
    }
}
