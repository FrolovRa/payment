package com.crud.payment.controller;

import com.crud.payment.dto.iban.IbanCreateDto;
import com.crud.payment.dto.iban.IbanReadDto;
import com.crud.payment.service.IbanService;
import org.springframework.web.bind.annotation.*;

@RestController
public class IbanController {

    private final IbanService ibanService;

    public IbanController(IbanService ibanService) {
        this.ibanService = ibanService;
    }

    @GetMapping("/iban/{id}")
    public IbanReadDto findById(@PathVariable Long id) {
        return ibanService.findById(id);
    }

    @PostMapping("/iban")
    public IbanReadDto save(@RequestBody IbanCreateDto iban) {
        return ibanService.save(iban);
    }
}
