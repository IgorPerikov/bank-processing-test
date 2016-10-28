package org.test.bank.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.test.bank.entity.Account;

import java.util.List;
import java.util.UUID;

@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Account createNewAccount() {
        Account account = new Account();
        jdbcTemplate.update("INSERT INTO mysql.accounts(id, cash) values (?, ?)",
                account.getId().toString(), account.getCash());
        return account;
    }

    public void deleteAccount(UUID id) {
        jdbcTemplate.update("DELETE FROM mysql.accounts where id = ?", id.toString());
    }

    public List<Account> getAccounts() {
        return jdbcTemplate.query("SELECT * FROM mysql.accounts",
                (rs, rowNum) -> new Account(UUID.fromString(rs.getString(1)), rs.getBigDecimal(2)));
    }
}
