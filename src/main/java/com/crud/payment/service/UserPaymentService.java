package com.crud.payment.service;

import com.crud.payment.domain.Payment;
import com.crud.payment.domain.User;
import com.crud.payment.dto.payment.PaymentReadDto;
import com.crud.payment.exception.EntityNotFoundException;
import com.crud.payment.repository.PaymentRepository;
import com.crud.payment.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserPaymentService {

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final ObjectMapperService mapperService;

    public UserPaymentService(UserRepository userRepository,
                              PaymentRepository paymentRepository,
                              ObjectMapperService mapperService) {
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.mapperService = mapperService;
    }

    @Transactional(readOnly = true)
    public List<PaymentReadDto> getPaymentsFiltered(Long userId,
                                                    String amountFrom,
                                                    String amountTo,
                                                    List<Long> paymentTypes) {
        final Long ibanId = findUserByIdHelper(userId).getIban().getId();
        List<Payment> payments = paymentRepository.getPaymentsFiltered(ibanId, amountFrom, amountTo, paymentTypes);
        return mapperService.toDtoList(payments);
    }

    private User findUserByIdHelper(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));
    }
}
