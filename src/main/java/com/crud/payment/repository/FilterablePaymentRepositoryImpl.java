package com.crud.payment.repository;

import com.crud.payment.domain.Payment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.List;

public class FilterablePaymentRepositoryImpl implements FilterablePaymentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Payment> getPaymentsFiltered(Long ibanId, String amountFrom, String amountTo, List<Long> paymentTypes) {
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT p FROM Payment p WHERE (p.debtorIban.id = :id or p.creditorIban.id = :id)");

        if (amountFrom != null && amountTo != null) {
            sb.append(" and p.amount between :amountFrom and :amountTo");
        }
        if (paymentTypes != null) {
            sb.append(" and p.paymentType.id in :paymentTypes");
        }

        TypedQuery<Payment> query = entityManager.createQuery(sb.toString(), Payment.class);

        query.setParameter("id", ibanId);
        if (amountFrom != null && amountTo != null) {
            query.setParameter("amountFrom", new BigInteger(amountFrom));
            query.setParameter("amountTo", new BigInteger(amountTo));
        }
        if (paymentTypes != null) {
            query.setParameter("paymentTypes", paymentTypes);
        }

        return query.getResultList();
    }
}
