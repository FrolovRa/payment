package com.crud.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CanNotCancelPayment extends RuntimeException {

    public CanNotCancelPayment(String message) {
        super(message);
    }
}
