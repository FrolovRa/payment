package com.crud.payment.service;

import com.crud.payment.BaseTest;
import com.crud.payment.domain.Payment;
import com.crud.payment.domain.PaymentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigInteger;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class FeeCalculationServiceTest extends BaseTest {

    @MockBean
    private Clock clock;

    @Test
    public void testFeeCalculation() {
        Instant moment = Instant.parse("2021-12-03T10:15:30.00Z");
        Payment payment = new Payment();
        payment.setAmount(new BigInteger("10000"));
        PaymentType type = new PaymentType();
        type.setFeeCoefficient(0.15);
        payment.setPaymentType(type);
        payment.setCreatedAt(moment);
        when(clock.instant()).thenReturn(moment.plus(2, ChronoUnit.HOURS));

        BigInteger result = feeCalculationService.calculateFee(payment);

        assertThat(result).isEqualTo("7000");
    }
}
