package com.inn.banka.controller;


import com.inn.banka.dto.AccountDTO;
import com.inn.banka.model.Account;
import com.inn.banka.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/createAccount")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }


    @GetMapping("/getAccountById/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(accountService.getAccountById(id)
                    .orElseThrow(() -> new Exception("Account not found")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/getAllAccounts")
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
