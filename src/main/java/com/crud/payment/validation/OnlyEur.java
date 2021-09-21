package com.crud.payment.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { OnlyEurValidator.class,})
public @interface OnlyEur {
    String message() default "only EUR currency is valid";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}