package com.crud.payment.dto.payment;

import com.crud.payment.validation.OnlyEur;
import com.crud.payment.validation.OnlyUsd;
import com.crud.payment.validation.group.payment.PaymentTypeOne;
import com.crud.payment.validation.group.payment.PaymentTypeThree;
import com.crud.payment.validation.group.payment.PaymentTypeTwo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PaymentCreateDto {
    @NotNull
    private Long creditorIbanId;
    @NotNull
    private Long debtorIbanId;
    @NotNull
    private Long paymentTypeId;
    @OnlyEur(groups = {PaymentTypeOne.class})
    @OnlyUsd(groups = {PaymentTypeTwo.class})
    private Long currencyId;
    @NotNull(groups = {PaymentTypeThree.class})
    private Long bicCodeId;
    @NotBlank
    private String amount;
    @NotBlank(groups = {PaymentTypeOne.class})
    private String details;
}
