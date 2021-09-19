package com.crud.payment.repository;

import com.crud.payment.domain.Iban;
import org.springframework.data.repository.CrudRepository;

public interface IbanRepository extends CrudRepository<Iban, Long> {
}
