package com.crud.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> entityClass, Long id) {
        super(String.format("Entity '%s' with id=%s is not found.", entityClass.getSimpleName(), id));
    }

    public EntityNotFoundException(Class<?> entityClass) {
        super(String.format("Entity '%s' is not found.", entityClass.getSimpleName()));
    }
}