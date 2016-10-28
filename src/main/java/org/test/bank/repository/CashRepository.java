package org.test.bank.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public class CashRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BigDecimal getCash(UUID id) {
        return jdbcTemplate.queryForObject("SELECT cash FROM mysql.accounts where id = ?", new Object[]{ id.toString() }, (rs, rowNum) -> rs.getBigDecimal(1));
    }

    public BigDecimal setCash(UUID id, BigDecimal amount) {
        jdbcTemplate.update("UPDATE mysql.accounts SET cash = ? where id = ?", amount, id.toString());
        return amount;
    }
}
