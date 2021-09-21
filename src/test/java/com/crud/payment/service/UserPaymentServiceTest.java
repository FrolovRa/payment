package com.crud.payment.service;

import com.crud.payment.SpringTest;
import com.crud.payment.dto.payment.PaymentReadDto;
import com.crud.payment.exception.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserPaymentServiceTest extends SpringTest {

    @Test
    public void shouldThrowException() {
        Throwable ex = Assertions.catchThrowable(() -> userPaymentService.getPayments(55L));

        assertThat(ex).isExactlyInstanceOf(EntityNotFoundException.class);
        assertThat(ex).hasMessageContaining("Entity 'User' with id=55 is not found.");
    }

    @Test
    public void getPaymentsShouldReturnValidData() {
        List<PaymentReadDto> result = userPaymentService.getPayments(1L);

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result).haveExactly(
                1,
                new Condition<>(PaymentReadDto::getIsCanceled, "One payment should be canceled")
        );
    }
}
