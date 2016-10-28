package org.test.bank.handler;

import java.math.BigDecimal;
import java.util.UUID;

public class NotEnoughCashException extends RuntimeException {

    public NotEnoughCashException(UUID account, BigDecimal need, BigDecimal found) {
        super("Not enough cash on account " + account + ", need at least " + need + " found " + found);
    }
}
