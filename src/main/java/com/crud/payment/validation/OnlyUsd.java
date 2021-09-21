package com.crud.payment.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { OnlyUsdValidator.class,})
public @interface OnlyUsd {
    String message() default "only USD currency is valid";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}