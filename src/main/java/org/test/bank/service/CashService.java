package org.test.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.bank.concurrent.LockTuple;
import org.test.bank.handler.NotEnoughCashException;
import org.test.bank.repository.CashRepository;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.Lock;

@Service
public class CashService {

    @Autowired
    private CashRepository cashRepository;

    @Autowired
    private LockService lockService;

    public BigDecimal addCash(UUID id, BigDecimal amount) {
        Lock lock = lockService.getLock(id);
        lock.lock();
        BigDecimal cashAfterOperation = rawAdd(id, amount);
        lock.unlock();
        return cashAfterOperation;
    }

    public BigDecimal withdrawCash(UUID id, BigDecimal amount) {
        Lock lock = lockService.getLock(id);
        lock.lock();
        BigDecimal cashAfterOperation;
        try {
            cashAfterOperation = rawWithdraw(id, amount);
        } finally {
            lock.unlock();
        }
        return cashAfterOperation;
    }

    private BigDecimal rawAdd(UUID id, BigDecimal amount) {
        BigDecimal currentCash = cashRepository.getCash(id);
        return cashRepository.setCash(id, currentCash.add(amount));
    }

    private BigDecimal rawWithdraw(UUID id, BigDecimal amount) {
        BigDecimal currentCash = cashRepository.getCash(id);
        if (currentCash.compareTo(amount) >= 0) {
            return cashRepository.setCash(id, currentCash.subtract(amount));
        } else {
            throw new NotEnoughCashException(id, amount, currentCash);
        }
    }

    public BigDecimal transferCash(UUID senderId, UUID recipientId, BigDecimal amount) {
        LockTuple lockTuple = lockService.getLockTuple(senderId, recipientId);
        Lock firstLock = lockTuple.getFirstLock();
        Lock secondLock = lockTuple.getSecondLock();
        firstLock.lock();
        secondLock.lock();

        BigDecimal cashAfterOperation;
        try {
            cashAfterOperation = rawWithdraw(senderId, amount);
            rawAdd(recipientId, amount);
        } finally {
            firstLock.unlock();
            secondLock.unlock();
        }

        return cashAfterOperation;
    }

    public BigDecimal getCash(UUID id) {
        return cashRepository.getCash(id);
    }
}
