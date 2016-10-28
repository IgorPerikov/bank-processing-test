package org.test.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.test.bank.entity.Account;
import org.test.bank.service.AccountService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseEntity<Account> createAccount() {
        return ResponseEntity.ok(accountService.createNewAccount());
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }
}