package com.inn.banka.service;

import com.inn.banka.dto.TransactionDTO;
import com.inn.banka.model.Account;
import com.inn.banka.model.Transaction;
import com.inn.banka.repository.AccountRepository;
import com.inn.banka.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public String performTransaction(Transaction transaction, double flatFee, double percentFee) {


        Account originatingAccount = accountRepository.findById(transaction.getOriginatingAccount().getId())
                .orElseThrow(() -> new IllegalArgumentException("Originating account not found"));


        Account resultingAccount = null;
        if (transaction.getResultingAccount() != null) {
            resultingAccount = accountRepository.findById(transaction.getResultingAccount().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Resulting account not found"));
        }

        double fee = transaction.isFlatFee() ? flatFee : transaction.getAmount() * percentFee / 100;
        double totalAmount = transaction.getAmount() + fee;

        if (originatingAccount.getBalance() < totalAmount) {
            throw new IllegalArgumentException("Insufficient funds");
        }


        originatingAccount.setBalance(originatingAccount.getBalance() - totalAmount);
        if (resultingAccount != null) {
            resultingAccount.setBalance(resultingAccount.getBalance() + transaction.getAmount());
            accountRepository.save(resultingAccount);
        }

        accountRepository.save(originatingAccount);

        transaction.setFeeAmount(fee);
        transaction.setOriginatingAccount(originatingAccount);
        transaction.setResultingAccount(resultingAccount);

        transactionRepository.save(transaction);

        return "Transaction has been successful";
    }

    @Transactional
    public String depositMoney(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));


        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);


        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setOriginatingAccount(account);
        transaction.setResultingAccount(account);
        transaction.setReason("Deposit");

        transactionRepository.save(transaction);

        return "Deposit successful. New balance: " + account.getBalance();
    }

    @Transactional
    public String withdrawMoney(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }


        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);


        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setOriginatingAccount(account);
        transaction.setResultingAccount(account);
        transaction.setReason("Withdrawal");

        transactionRepository.save(transaction);

        return "Withdrawal successful. New balance: " + account.getBalance();
    }



    public List<TransactionDTO> getTransactionsForAccount(Long accountId) {
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


    @Transactional
    public double checkAccountBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        return account.getBalance();
    }

}
