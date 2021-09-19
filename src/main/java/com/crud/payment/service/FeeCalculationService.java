package com.crud.payment.service;

import com.crud.payment.domain.Payment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

@Service
public class FeeCalculationService {

    private final Clock clock;

    public FeeCalculationService(Clock clock) {
        this.clock = clock;
    }

    public BigInteger calculateFee(Payment canceledPayment) {
        final BigInteger originAmount = canceledPayment.getAmount();
        final BigDecimal originAmountDecimal = new BigDecimal(originAmount);
        final Double feeCoefficient = canceledPayment.getPaymentType().getFeeCoefficient();
        final Instant createdAt = canceledPayment.getCreatedAt();
        final Instant now = clock.instant();

        final long duration = Duration.between(createdAt, now).toHours();

        return originAmountDecimal
                .subtract(originAmountDecimal.multiply(BigDecimal.valueOf(duration * feeCoefficient)))
                .toBigInteger();
    }
}
