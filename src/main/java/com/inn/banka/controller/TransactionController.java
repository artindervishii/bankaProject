package com.inn.banka.controller;

import com.inn.banka.dto.TransactionDTO;
import com.inn.banka.model.Account;
import com.inn.banka.model.Transaction;
import com.inn.banka.service.AccountService;
import com.inn.banka.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/performTransaction")
    public String performTransaction(@RequestBody Transaction transaction,
                                     @RequestParam double flatFee,
                                     @RequestParam double percentFee) {
        try {
            transactionService.performTransaction(transaction, flatFee, percentFee);
            return "Transaction has been successful ";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositMoney(@RequestParam Long accountId, @RequestParam double amount) {
        try {
            String response = transactionService.depositMoney(accountId, amount);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestParam Long accountId, @RequestParam double amount) {
        try {
            String response = transactionService.withdrawMoney(accountId, amount);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/getTransactionsForAccount/{id}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsForAccount(@PathVariable Long id) {
        try {
            List<TransactionDTO> transactions = transactionService.getTransactionsForAccount(id);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping("/checkAccountBalance/{id}")
    public String checkAccountBalance(@PathVariable Long id) {
        double balance = transactionService.checkAccountBalance(id);
        return "Your balance is " + balance;
    }

}
