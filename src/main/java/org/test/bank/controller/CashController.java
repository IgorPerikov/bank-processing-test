package org.test.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.bank.service.CashService;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
public class CashController {

    @Autowired
    private CashService cashService;

    @RequestMapping(value = "/account/{id}/cash", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> getCash(@PathVariable UUID id) {
        return ResponseEntity.ok(cashService.getCash(id));
    }

    @RequestMapping(value = "/account/{id}/cash/add", method = RequestMethod.PATCH)
    public ResponseEntity<BigDecimal> addCash(@PathVariable UUID id, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(cashService.addCash(id, amount));
    }

    @RequestMapping(value = "/account/{id}/cash/withdraw", method = RequestMethod.PATCH)
    public ResponseEntity<BigDecimal> withdrawCash(@PathVariable UUID id, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(cashService.withdrawCash(id, amount));
    }

    @RequestMapping(value = "/account/{id}/cash/transfer", method = RequestMethod.PATCH)
    public ResponseEntity<BigDecimal> transferCash(@PathVariable(value = "id") UUID senderId,
                                                   @RequestParam(value = "to") UUID recipientId,
                                                   @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(cashService.transferCash(senderId, recipientId, amount));
    }
}
