package com.crud.payment.service;

import com.crud.payment.SpringTest;
import com.crud.payment.dto.iban.IbanCreateDto;
import com.crud.payment.dto.iban.IbanReadDto;
import com.crud.payment.exception.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IbanServiceTest extends SpringTest {

    @Test
    public void shouldFindIbanById()  {
        IbanReadDto iban = ibanService.findById(1L);

        assertThat(iban).isNotNull();
        assertThat(iban.getId()).isEqualTo(1L);
        assertThat(iban.getIban()).isEqualTo("DE75512108001245126199");
    }

    @Test
    public void findByIdShouldThrowEntityNotFoundException() {
        Throwable ex = Assertions.catchThrowable(() -> ibanService.findById(885L));

        assertThat(ex).isExactlyInstanceOf(EntityNotFoundException.class);
        assertThat(ex).hasMessageContaining("Entity 'Iban' with id=885 is not found.");
    }

    @Test
    public void shouldSaveIban() {
        IbanCreateDto dto = new IbanCreateDto();
        dto.setIban("346gs31dSSG");

        IbanReadDto result = ibanService.save(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getIban()).isEqualTo(dto.getIban());
    }
}
