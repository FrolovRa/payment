package com.crud.payment.controller;

import com.crud.payment.dto.user.UserAuthDto;
import com.crud.payment.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public String auth(@RequestBody UserAuthDto user) {
        return authService.generateToken(user);
    }
}