package com.crud.payment.controller;

import com.crud.payment.dto.payment.PaymentReadDto;
import com.crud.payment.service.UserPaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserPaymentController {

    private final UserPaymentService userPaymentService;

    public UserPaymentController(UserPaymentService userPaymentService) {
        this.userPaymentService = userPaymentService;
    }

    @GetMapping("/users/{userId}/payments")
    public List<PaymentReadDto> getPayments(@PathVariable Long userId) {
        return userPaymentService.getPayments(userId);
    }
}
