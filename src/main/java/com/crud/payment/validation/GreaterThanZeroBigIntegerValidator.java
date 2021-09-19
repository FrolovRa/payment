package com.crud.payment.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigInteger;

public class GreaterThanZeroBigIntegerValidator implements ConstraintValidator<GreaterThanZeroBigInteger, BigInteger> {
    @Override
    public boolean isValid(BigInteger amount, ConstraintValidatorContext context) {
        return amount.compareTo(BigInteger.ZERO) > 0;
    }
}
