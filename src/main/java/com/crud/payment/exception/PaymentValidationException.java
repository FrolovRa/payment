package com.crud.payment.exception;

import com.crud.payment.dto.payment.PaymentCreateDto;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class PaymentValidationException extends RuntimeException {

    private final Set<ConstraintViolation<PaymentCreateDto>> violations;

    public PaymentValidationException(Set<ConstraintViolation<PaymentCreateDto>> violations) {
        this.violations = violations;
    }

    public Set<ConstraintViolation<PaymentCreateDto>> getViolations() {
        return violations;
    }
}
