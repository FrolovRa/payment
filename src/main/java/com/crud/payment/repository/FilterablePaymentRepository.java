package com.crud.payment.repository;

import com.crud.payment.domain.Payment;

import java.util.List;

public interface FilterablePaymentRepository {
    List<Payment> getPaymentsFiltered(Long ibanId,
                                      String amountFrom,
                                      String amountTo,
                                      List<Long> paymentTypes);
}
