package com.crud.payment.repository;

import com.crud.payment.domain.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long>, RefreshablePaymentRepository {
}
