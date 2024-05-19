package com.inn.banka.controller;

import com.inn.banka.dto.AccountDTO;
import com.inn.banka.dto.BankDto;
import com.inn.banka.model.Bank;
import com.inn.banka.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping("/createBank")
    public Bank createBank(@RequestBody Bank bank) {
        return bankService.createBank(bank);
    }

    @GetMapping("/getBankById/{id}")
    public ResponseEntity<BankDto> getBankById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bankService.getBankById(id)
                    .orElseThrow(() -> new Exception("Bank not found")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/getAllBanks")
    public List<BankDto> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping("/{bankId}/total-transaction-fee")
    public String getTotalTransactionFeeAmount(@PathVariable Long bankId) {
        return bankService.getBankTotalTransactionFeeAmount(bankId);
    }

    @GetMapping("/{bankId}/total-transfer-amount")
    public String getTotalTransferAmount(@PathVariable Long bankId) {
        return bankService.getBankTotalTransferAmount(bankId);
    }

}