package org.test.bank.concurrent;

import java.util.concurrent.locks.Lock;

public class LockTuple {

    private Lock firstLock;
    private Lock secondLock;

    public LockTuple(Lock firstLock, Lock secondLock) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
    }

    public Lock getFirstLock() {
        return firstLock;
    }

    public Lock getSecondLock() {
        return secondLock;
    }
}
