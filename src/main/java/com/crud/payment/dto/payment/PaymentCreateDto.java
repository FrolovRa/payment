package com.crud.payment.dto.payment;

import lombok.Data;

@Data
public class PaymentCreateDto {
    private Long creditorIbanId;
    private Long debtorIbanId;
    private Long paymentTypeId;
    private Long currencyId;
    private Long bicCodeId;
    private String amount;
    private String details;
}
