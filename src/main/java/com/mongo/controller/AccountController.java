package com.mongo.controller;

import com.mongo.config.exception.AccountCollectionException;
import com.mongo.dto.Account;
import com.mongo.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountServiceImpl accountService;

    @PostMapping("/create")
    public ResponseEntity<?> CreateAccount(@RequestBody Account account){
        try {
            accountService.CreateAccount(account);
            return new ResponseEntity<>("created account succeed", HttpStatus.CREATED);

        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (AccountCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody Account account){
        try {
            accountService.Login(account);
            return  new ResponseEntity<>("Login succeed", HttpStatus.ACCEPTED);
        }catch (AccountCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
