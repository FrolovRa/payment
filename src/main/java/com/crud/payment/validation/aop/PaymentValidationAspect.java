package com.crud.payment.validation.aop;

import com.crud.payment.constant.PaymentType;
import com.crud.payment.dto.payment.PaymentCreateDto;
import com.crud.payment.exception.PaymentValidationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
public class PaymentValidationAspect {

    private final Validator validator;

    public PaymentValidationAspect(Validator validator) {
        this.validator = validator;
    }

    @Before("@annotation(com.crud.payment.validation.aop.ValidatePayment)")
    public void validatePaymentByPaymentType(JoinPoint joinPoint) {
        PaymentCreateDto payment = Arrays
                .stream(joinPoint.getArgs())
                .filter(PaymentCreateDto.class::isInstance)
                .findFirst()
                .map(PaymentCreateDto.class::cast)
                .orElseThrow(() -> new IllegalArgumentException("there is no PaymentCreateDto"));

        Arrays
                .stream(PaymentType.values())
                .filter(pt -> Objects.equals(pt.id, payment.getPaymentTypeId()))
                .findFirst()
                .map(pt -> pt.validationGroup)
                .map(c -> validator.validate(payment, c))
                .filter(s -> !s.isEmpty())
                .ifPresent(s -> {
                    throw new PaymentValidationException(s);
                });
    }
}
