package com.crud.payment.service;

import com.crud.payment.domain.Payment;
import com.crud.payment.domain.PaymentType;
import com.crud.payment.exception.CanNotCancelPayment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FeeCalculationServiceUnitTest {

    private final Clock clock = mock(Clock.class);
    private final FeeCalculationService feeCalculationService = new FeeCalculationService(clock);

    @Test
    public void shouldCalculateFee() {
        Instant moment = Instant.parse("2021-12-03T10:15:30.00Z");
        Payment payment = new Payment();
        payment.setAmount(new BigInteger("10000"));
        PaymentType type = new PaymentType();
        type.setFeeCoefficient(0.15);
        payment.setPaymentType(type);
        payment.setCreatedAt(moment);
        when(clock.instant()).thenReturn(moment.plus(2, ChronoUnit.HOURS));

        BigInteger result = feeCalculationService.calculateFee(payment);

        assertThat(result).isEqualTo("3000");
    }

    @Test
    public void feeShouldBeZero() {
        Instant moment = Instant.parse("2021-12-03T10:15:30.00Z");
        Payment payment = new Payment();
        payment.setAmount(new BigInteger("10000"));
        PaymentType type = new PaymentType();
        type.setFeeCoefficient(0.15);
        payment.setPaymentType(type);
        payment.setCreatedAt(moment);
        when(clock.instant()).thenReturn(moment.plus(45, ChronoUnit.MINUTES));

        BigInteger result = feeCalculationService.calculateFee(payment);

        assertThat(result).isEqualTo("0");
    }

    @Test
    public void shouldThrowException() {
        Instant moment = Instant.parse("2021-12-03T10:15:30.00Z");
        Payment payment = new Payment();
        payment.setAmount(new BigInteger("10000"));
        PaymentType type = new PaymentType();
        type.setFeeCoefficient(0.55);
        payment.setPaymentType(type);
        payment.setCreatedAt(moment);
        when(clock.instant()).thenReturn(moment.plus(5, ChronoUnit.HOURS));

        Throwable ex = Assertions.catchThrowable(() -> feeCalculationService.calculateFee(payment));

        assertThat(ex).isExactlyInstanceOf(CanNotCancelPayment.class);
        assertThat(ex).hasMessageContaining("Cancellation fee is 100%. Payment can not be canceled.");
    }
}
