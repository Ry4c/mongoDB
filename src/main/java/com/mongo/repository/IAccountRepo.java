package com.mongo.repository;

import com.mongo.dto.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IAccountRepo extends MongoRepository<Account, String> {
    Optional<Account> findByUsername(String username);

    Optional<Account> findByUsernameAndPassword(String username, String password);
}
