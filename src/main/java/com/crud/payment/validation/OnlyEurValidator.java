package com.crud.payment.validation;

import com.crud.payment.domain.Currency;
import com.crud.payment.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyEurValidator implements ConstraintValidator<OnlyEur, Long> {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public boolean isValid(Long currencyId, ConstraintValidatorContext constraintValidatorContext) {
        return currencyRepository
                .findById(currencyId)
                .map(Currency::getCurrency)
                .map("EUR"::equals)
                .orElse(false);
    }
}
