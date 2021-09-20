package com.crud.payment.service;

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
    public List<PaymentReadDto> getPayments(Long userId) {
        final User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, userId));

        return mapperService.toDtoList(paymentRepository.findAllForIbanId(user.getIban().getId()));
    }
}
