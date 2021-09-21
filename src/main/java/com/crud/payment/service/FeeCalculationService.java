package com.crud.payment.service;

import com.crud.payment.domain.Payment;
import com.crud.payment.exception.CanNotCancelPayment;
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
        final double overallCoefficient = Math.min(1.0, duration * feeCoefficient);

        if (overallCoefficient == 1.0) {
            throw new CanNotCancelPayment("Cancellation fee is 100%. Payment can not be canceled.");
        }

        return originAmountDecimal.multiply(BigDecimal.valueOf(overallCoefficient)).toBigInteger();
    }
}
