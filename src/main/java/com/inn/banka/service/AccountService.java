package com.inn.banka.service;

import com.inn.banka.dto.AccountDTO;
import com.inn.banka.dto.TransactionDTO;
import com.inn.banka.model.Account;
import com.inn.banka.model.Transaction;
import com.inn.banka.repository.AccountRepository;
import com.inn.banka.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public Account createAccount(Account account){
        return  accountRepository.save(account);
    }

    public Optional<AccountDTO> getAccountById(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return Optional.of(new AccountDTO(
                    account.getId(),
                    account.getName(),
                    account.getBalance(),
                    getTransactionsForAccount(account.getId())
            ));
        }
        return Optional.empty();
    }

    private List<TransactionDTO> getTransactionsForAccount(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        return transactions.stream()
                .map(transaction -> new TransactionDTO(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getOriginatingAccount().getId(),
                        transaction.getResultingAccount().getId(),
                        transaction.getReason()
                ))
                .collect(Collectors.toList());
    }

    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream().map(account -> {
            List<Transaction> transactions = transactionRepository.findByAccountId(account.getId());

            List<TransactionDTO> transactionDTOs = transactions.stream()
                    .map(transaction -> new TransactionDTO(
                            transaction.getId(),
                            transaction.getAmount(),
                            transaction.getOriginatingAccount().getId(),
                            transaction.getResultingAccount().getId(),
                            transaction.getReason()
                    ))
                    .collect(Collectors.toList());

            return new AccountDTO(account.getId(), account.getName(), account.getBalance(), transactionDTOs);
        }).collect(Collectors.toList());
    }


}
