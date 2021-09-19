package com.crud.payment.dto.payment;

import lombok.Data;

import java.time.Instant;

@Data
public class PaymentReadDto {
    private Long id;
    private String creditorIban;
    private String debtorIban;
    private String paymentType;
    private String currency;
    private String bicCode;
    private Boolean isCanceled;
    private String cancellationFee;
    private String amount;
    private String details;
    private Instant createdAt;
}
