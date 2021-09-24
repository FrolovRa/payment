package com.crud.payment.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { GreaterThanZeroBigIntegerValidator.class})
public @interface GreaterThanZeroBigInteger {
    String message() default "amount must be greater than zero";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
