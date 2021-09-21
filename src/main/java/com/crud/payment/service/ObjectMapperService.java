package com.crud.payment.service;

import com.crud.payment.domain.*;
import com.crud.payment.dto.iban.IbanCreateDto;
import com.crud.payment.dto.iban.IbanReadDto;
import com.crud.payment.dto.payment.PaymentCreateDto;
import com.crud.payment.dto.payment.PaymentReadDto;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObjectMapperService {

    public PaymentReadDto toDto(Payment entity) {
        PaymentReadDto dto = new PaymentReadDto();
        dto.setId(entity.getId());
        dto.setCreditorIban(entity.getCreditorIban().getIban());
        dto.setDebtorIban(entity.getDebtorIban().getIban());
        dto.setCurrency(entity.getCurrency().getCurrency());
        dto.setPaymentType(entity.getPaymentType().getPaymentType());
        dto.setAmount(entity.getAmount().toString());
        dto.setDetails(entity.getDetails());
        if (entity.getBicCode() != null) {
            dto.setBicCode(entity.getBicCode().getBicCode());
        }
        if (entity.getCancelForPayment() != null) {
            BigInteger returned = entity.getAmount();
            BigInteger canceled = entity.getCancelForPayment().getAmount();
            BigInteger fee = canceled.subtract(returned);

            dto.setCancellationFee(fee.toString());
            dto.setIsCanceled(true);
        } else {
            dto.setIsCanceled(false);
        }
        return dto;
    }

    public List<PaymentReadDto> toDtoList(List<Payment> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public IbanReadDto toDto(Iban entity) {
        IbanReadDto dto = new IbanReadDto();
        dto.setId(entity.getId());
        dto.setIban(entity.getIban());
        return dto;
    }

    public Payment toEntity(PaymentCreateDto dto) {
        Payment entity = new Payment();
        entity.setCreditorIban(new Iban(dto.getCreditorIbanId()));
        entity.setDebtorIban(new Iban(dto.getDebtorIbanId()));
        entity.setBicCode(new BicCode(dto.getBicCodeId()));
        entity.setPaymentType(new PaymentType(dto.getPaymentTypeId()));
        entity.setAmount(new BigInteger(dto.getAmount()));
        entity.setCurrency(new Currency(dto.getCurrencyId()));
        entity.setDetails(dto.getDetails());
        return entity;
    }

    public Iban toEntity(IbanCreateDto dto) {
        Iban entity = new Iban();
        entity.setIban(dto.getIban());
        return entity;
    }
}
