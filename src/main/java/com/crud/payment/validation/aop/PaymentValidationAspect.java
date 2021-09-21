package com.crud.payment.validation.aop;

import com.crud.payment.dto.payment.PaymentCreateDto;
import com.crud.payment.exception.PaymentValidationException;
import com.crud.payment.validation.group.PaymentTypeOne;
import com.crud.payment.validation.group.PaymentTypeThree;
import com.crud.payment.validation.group.PaymentTypeTwo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Set;

@Aspect
@Component
public class PaymentValidationAspect {

    private final Validator validator;

    public PaymentValidationAspect(Validator validator) {
        this.validator = validator;
    }

    @Before("@annotation(PaymentValidation)")
    public void validatePaymentByPaymentType(JoinPoint joinPoint) {
        PaymentCreateDto payment = Arrays
                .stream(joinPoint.getArgs())
                .filter(PaymentCreateDto.class::isInstance)
                .findFirst()
                .map(PaymentCreateDto.class::cast)
                .orElseThrow(() -> new IllegalArgumentException("There is no PaymentCreateDto"));

        final Long typeId = payment.getPaymentTypeId();
        if (1L == typeId) {
            Set<ConstraintViolation<PaymentCreateDto>> violations = validator.validate(payment, PaymentTypeOne.class);
            if (!violations.isEmpty()) {
                throw new PaymentValidationException(violations);
            }
        }
        if (2L == typeId) {
            Set<ConstraintViolation<PaymentCreateDto>> violations = validator.validate(payment, PaymentTypeTwo.class);
            if (!violations.isEmpty()) {
                throw new PaymentValidationException(violations);
            }
        }
        if (3L == typeId) {
            Set<ConstraintViolation<PaymentCreateDto>> violations = validator.validate(payment, PaymentTypeThree.class);
            if (!violations.isEmpty()) {
                throw new PaymentValidationException(violations);
            }
        }
    }
}
