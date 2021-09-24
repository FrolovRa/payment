package com.crud.payment.controller.advice;

import com.crud.payment.dto.payment.PaymentCreateDto;
import com.crud.payment.exception.PaymentValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handle(PaymentValidationException ex) {
        HashMap<String, String> response = new HashMap<>();
        for (ConstraintViolation<PaymentCreateDto> violation : ex.getViolations()) {
            response.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return response;
    }
}
