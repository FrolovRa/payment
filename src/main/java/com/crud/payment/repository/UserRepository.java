package com.crud.payment.repository;

import com.crud.payment.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByName(String username);

    Optional<User> findByNameAndPassword(String username, String password);
}
