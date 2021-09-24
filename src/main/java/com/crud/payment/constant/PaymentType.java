package com.crud.payment.constant;

import com.crud.payment.validation.group.payment.PaymentTypeOne;
import com.crud.payment.validation.group.payment.PaymentTypeThree;
import com.crud.payment.validation.group.payment.PaymentTypeTwo;

public enum PaymentType {
    TYPE1(1L, PaymentTypeOne.class),
    TYPE2(2L, PaymentTypeTwo.class),
    TYPE3(3L, PaymentTypeThree.class);

    public final long id;
    public final Class<?> validationGroup;

    PaymentType(long id, Class<?> validationGroup) {
        this.id = id;
        this.validationGroup = validationGroup;
    }
}
