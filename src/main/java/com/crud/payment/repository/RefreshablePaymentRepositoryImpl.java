package com.crud.payment.repository;

import com.crud.payment.domain.Payment;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class RefreshablePaymentRepositoryImpl implements RefreshablePaymentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Payment refresh(Payment payment) {
        entityManager.refresh(payment);
        return payment;
    }
}
