package com.crud.payment.controller;

import com.crud.payment.dto.payment.PaymentCreateDto;
import com.crud.payment.dto.payment.PaymentReadDto;
import com.crud.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments/{id}")
    public PaymentReadDto findById(@PathVariable Long id) {
        return paymentService.findById(id);
    }

    @PostMapping("/payments")
    public PaymentReadDto save(@RequestBody PaymentCreateDto payment) {
        return paymentService.save(payment);
    }

    @PostMapping("/payments/{id}/cancel")
    public PaymentReadDto cancel(@PathVariable Long id) {
        return paymentService.cancel(id);
    }
}
