package org.test.bank.service;

import com.google.common.util.concurrent.Striped;
import org.springframework.stereotype.Service;
import org.test.bank.concurrent.LockTuple;

import java.util.concurrent.locks.Lock;

@Service
public class LockService {

    private static final int size = 1024;
    private final Striped<Lock> striped = Striped.lock(size);

    public Lock getLock(Object key) {
        return striped.getAt(getIndex(key));
    }

    // using this we can always get locks in strict order, so no deadlock is possible
    public LockTuple getLockTuple(Object key1, Object key2) {
        int index1 = getIndex(key1);
        int index2 = getIndex(key2);
        if (index1 < index2) {
            return new LockTuple(striped.getAt(index1), striped.getAt(index2));
        } else {
            return new LockTuple(striped.getAt(index2), striped.getAt(index1));
        }
    }

    private int getIndex(Object key) {
        return Math.abs(key.hashCode()) % size;
    }
}
