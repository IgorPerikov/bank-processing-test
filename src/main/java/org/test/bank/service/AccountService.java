package org.test.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.bank.entity.Account;
import org.test.bank.repository.AccountRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LockService lockService;

    public Account createNewAccount() {
        return accountRepository.createNewAccount(new Account());
    }

    public Account createNewAccount(UUID id) {
        return accountRepository.createNewAccount(new Account(id));
    }

    public void deleteAccount(UUID id) {
        Lock lock = lockService.getLock(id);
        lock.lock();
        accountRepository.deleteAccount(id);
        lock.unlock();
    }

    public List<Account> getAccounts() {
        return accountRepository.getAccounts();
    }
}
