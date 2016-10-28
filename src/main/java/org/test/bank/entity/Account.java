package org.test.bank.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {

    private UUID id;
    private BigDecimal cash;

    public Account(UUID id, BigDecimal cash) {
        this.id = id;
        this.cash = cash;
    }

    public Account(UUID id) {
        this.id = id;
        this.cash = new BigDecimal(0);
    }

    public Account() {
        this.id = UUID.randomUUID();
        this.cash = new BigDecimal(0);
    }

    public UUID getId() {
        return id;
    }

    public Account setId(UUID id) {
        this.id = id;
        return this;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public Account setCash(BigDecimal cash) {
        this.cash = cash;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id != null ? id.equals(account.id) : account.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
