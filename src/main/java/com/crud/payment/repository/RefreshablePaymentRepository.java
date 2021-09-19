package com.crud.payment.repository;

import com.crud.payment.domain.Payment;

public interface RefreshablePaymentRepository {
    Payment refresh(Payment payment);
}
