package com.crud.payment.service;

import com.crud.payment.SpringTest;
import com.crud.payment.dto.payment.PaymentReadDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserPaymentServiceTest extends SpringTest {

    @Test
    public void test() {
        List<PaymentReadDto> result = userPaymentService.getPayments(1L);

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
    }
}
