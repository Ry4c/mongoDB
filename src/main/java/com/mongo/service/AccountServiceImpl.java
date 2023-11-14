package com.mongo.service;

import com.mongo.config.exception.AccountCollectionException;
import com.mongo.dto.Account;
import com.mongo.repository.IAccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl {
    private final IAccountRepo accountRepo;
    private final int ROLE_USER = 2;

    public void CreateAccount(Account account) throws AccountCollectionException, ConstraintViolationException {
        Optional<Account> accountOptional = accountRepo.findByUsername(account.getUsername());
        if (accountOptional.isPresent())
            throw new AccountCollectionException(AccountCollectionException.AccountAlreadyExists(account.getUsername()));
        List<Integer> roles = new ArrayList<>();
        roles.add(ROLE_USER);
        account.setRoleId(roles);
        accountRepo.save(account);
    }

    public void Login(Account account) throws AccountCollectionException, ConstraintViolationException{
        Optional<Account> accountOptional = accountRepo.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (!accountOptional.isPresent()) throw new AccountCollectionException(AccountCollectionException.LoginFail());
    }

}
