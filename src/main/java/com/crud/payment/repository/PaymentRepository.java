package com.crud.payment.repository;

import com.crud.payment.domain.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Long>, RefreshablePaymentRepository {

    @Query("select p from Payment p where p.debtorIban.id = :id or p.creditorIban.id = :id")
    List<Payment> findAllForIbanId(Long id);
}
