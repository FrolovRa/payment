package com.crud.payment.service;

import com.crud.payment.SpringTest;
import com.crud.payment.dto.payment.PaymentCreateDto;
import com.crud.payment.dto.payment.PaymentReadDto;
import com.crud.payment.exception.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentServiceTest extends SpringTest {

    @Test
    public void findByIdShouldThrowEntityNotFoundException() {
        Throwable ex = Assertions.catchThrowable(() -> paymentService.findById(9924L));

        assertThat(ex).isExactlyInstanceOf(EntityNotFoundException.class);
        assertThat(ex).hasMessageContaining("Entity 'Payment' with id=9924 is not found.");
    }

    @Test
    public void shouldSavePayment() {
        PaymentCreateDto dto = new PaymentCreateDto();
        dto.setAmount("1000");
        dto.setBicCodeId(1L);
        dto.setPaymentTypeId(1L);
        dto.setCreditorIbanId(1L);
        dto.setDebtorIbanId(2L);
        dto.setDetails("description");
        dto.setCurrencyId(1L);

        PaymentReadDto result = paymentService.save(dto);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getAmount()).isEqualTo("1000");
        assertThat(result.getPaymentType()).isEqualTo("type1");
        assertThat(result.getCurrency()).isEqualTo("USD");
        assertThat(result.getDetails()).isEqualTo("description");
        assertThat(result.getCreditorIban()).isEqualTo("DE75512108001245126199");
        assertThat(result.getDebtorIban()).isEqualTo("JO71CBJO0000000000001234567890");
        assertThat(result.getBicCode()).isEqualTo("AE35177424");
    }

    @Test
    public void shouldNotSavePaymentWithAmountZero() {
        PaymentCreateDto dto = new PaymentCreateDto();
        dto.setAmount("0");
        dto.setBicCodeId(1L);
        dto.setPaymentTypeId(1L);
        dto.setCreditorIbanId(1L);
        dto.setDebtorIbanId(2L);
        dto.setDetails("description");
        dto.setCurrencyId(1L);

        ConstraintViolationException ex = Assertions.catchThrowableOfType(
                () -> paymentService.save(dto),
                ConstraintViolationException.class
        );

        assertThat(ex.getConstraintViolations())
                .extracting("message")
                .contains("amount must be greater than zero");
    }

    @Test
    public void shouldCancelPayment() {
        PaymentCreateDto dto = new PaymentCreateDto();
        dto.setAmount("2500");
        dto.setBicCodeId(1L);
        dto.setPaymentTypeId(1L);
        dto.setCreditorIbanId(1L);
        dto.setDebtorIbanId(2L);
        dto.setDetails("description");
        dto.setCurrencyId(1L);

        PaymentReadDto paymentToCancel = paymentService.save(dto);
        PaymentReadDto result  = paymentService.cancel(paymentToCancel.getId());

        assertThat(result.getId()).isNotNull();
        assertThat(result.getId()).isNotEqualTo(paymentToCancel.getId());
        assertThat(result.getAmount()).isEqualTo("2500");
        assertThat(result.getPaymentType()).isEqualTo("type1");
        assertThat(result.getCurrency()).isEqualTo("USD");
        assertThat(result.getDetails()).isNull();
        assertThat(result.getDebtorIban()).isEqualTo("DE75512108001245126199");
        assertThat(result.getCreditorIban()).isEqualTo("JO71CBJO0000000000001234567890");
        assertThat(result.getBicCode()).isEqualTo("AE35177424");
    }
}
