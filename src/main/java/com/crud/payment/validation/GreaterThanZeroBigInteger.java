package com.crud.payment.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { GreaterThanZeroBigIntegerValidator.class})
public @interface GreaterThanZeroBigInteger {
    String message() default "Amount can't be less or equal to zero";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
