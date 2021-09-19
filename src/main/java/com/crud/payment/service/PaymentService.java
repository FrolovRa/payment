package com.crud.payment.service;

import com.crud.payment.domain.Payment;
import com.crud.payment.dto.payment.PaymentCreateDto;
import com.crud.payment.dto.payment.PaymentReadDto;
import com.crud.payment.exception.EntityNotFoundException;
import com.crud.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final FeeCalculationService feeCalculationService;
    private final ObjectMapperService mapperService;

    public PaymentService(PaymentRepository paymentRepository,
                          FeeCalculationService feeCalculationService,
                          ObjectMapperService mapperService) {
        this.paymentRepository = paymentRepository;
        this.feeCalculationService = feeCalculationService;
        this.mapperService = mapperService;
    }

    @Transactional(readOnly = true)
    public PaymentReadDto findById(Long id) {
        return paymentRepository
                .findById(id)
                .map(mapperService::toDto)
                .orElseThrow(() -> new EntityNotFoundException(Payment.class, id));
    }

    @Transactional
    public PaymentReadDto save(PaymentCreateDto paymentDto) {
        Payment entity = mapperService.toEntity(paymentDto);
        entity = paymentRepository.refresh(paymentRepository.save(entity));
        return mapperService.toDto(entity);
    }

    @Transactional
    public PaymentReadDto cancel(Long id) {
        Payment payment = paymentRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Payment.class, id));

        Payment cancelPayment = new Payment();
        cancelPayment.setCurrency(payment.getCurrency());
        cancelPayment.setDebtorIban(payment.getCreditorIban());
        cancelPayment.setCreditorIban(payment.getDebtorIban());
        cancelPayment.setBicCode(payment.getBicCode());
        cancelPayment.setPaymentType(payment.getPaymentType());
        cancelPayment.setCancelForPayment(payment);
        cancelPayment.setAmount(feeCalculationService.calculateFee(payment));
        cancelPayment = paymentRepository.save(cancelPayment);
        return mapperService.toDto(cancelPayment);
    }
}
