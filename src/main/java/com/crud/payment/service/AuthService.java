package com.crud.payment.service;

import com.crud.payment.domain.User;
import com.crud.payment.dto.user.UserAuthDto;
import com.crud.payment.exception.EntityNotFoundException;
import com.crud.payment.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProviderService jwtProviderService;

    public AuthService(UserRepository userRepository,
                       JwtProviderService jwtProviderService) {
        this.userRepository = userRepository;
        this.jwtProviderService = jwtProviderService;
    }

    @Transactional(readOnly = true)
    public String generateToken(UserAuthDto dto) {
        User user = userRepository
                .findByNameAndPassword(dto.getName(), dto.getPassword())
                .orElseThrow(() -> new EntityNotFoundException(User.class));

        return jwtProviderService.generateToken(user.getName());
    }
}
