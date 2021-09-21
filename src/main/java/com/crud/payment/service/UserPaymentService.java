package com.crud.payment.service;

import com.crud.payment.domain.Payment;
import com.crud.payment.domain.User;
import com.crud.payment.dto.payment.PaymentReadDto;
import com.crud.payment.exception.EntityNotFoundException;
import com.crud.payment.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.List;

@Service
public class UserPaymentService {

    private final UserRepository userRepository;
    private final ObjectMapperService mapperService;

    @PersistenceContext
    private EntityManager entityManager;

    public UserPaymentService(UserRepository userRepository,
                              ObjectMapperService mapperService) {
        this.userRepository = userRepository;
        this.mapperService = mapperService;
    }

    @Transactional(readOnly = true)
    public List<PaymentReadDto> getPaymentsFiltered(Long userId,
                                                    String amountFrom,
                                                    String amountTo,
                                                    List<Long> paymentTypes) {
        final User user = findUserByIdHelper(userId);
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT p FROM Payment p WHERE (p.debtorIban.id = :id or p.creditorIban.id = :id)");

        if (amountFrom != null && amountTo != null) {
            sb.append(" and p.amount between :amountFrom and :amountTo");
        }
        if (paymentTypes != null) {
            sb.append(" and p.paymentType.id in :paymentTypes");
        }

        TypedQuery<Payment> query = entityManager.createQuery(sb.toString(), Payment.class);

        query.setParameter("id", user.getIban().getId());
        if (amountFrom != null && amountTo != null) {
            query.setParameter("amountFrom", new BigInteger(amountFrom));
            query.setParameter("amountTo", new BigInteger(amountTo));
        }
        if (paymentTypes != null) {
            query.setParameter("paymentTypes", paymentTypes);
        }

        return mapperService.toDtoList(query.getResultList());
    }

    private User findUserByIdHelper(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));
    }
}
