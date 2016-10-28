package org.test.bank.handler;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class DuplicateAccountCreation {

    @ExceptionHandler(MySQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handle(MySQLIntegrityConstraintViolationException exception) {
        return new ResponseEntity<>("Account with this ID already exists", HttpStatus.BAD_REQUEST);
    }
}
