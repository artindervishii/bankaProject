package com.inn.banka.service;


import com.inn.banka.dto.AccountDTO;
import com.inn.banka.dto.BankDto;
import com.inn.banka.dto.TransactionDTO;
import com.inn.banka.model.Account;
import com.inn.banka.model.Bank;
import com.inn.banka.model.Transaction;
import com.inn.banka.repository.AccountRepository;
import com.inn.banka.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BankService {


    @Autowired
    private BankRepository bankRepository;


    public Bank createBank(Bank bank){
        return  bankRepository.save(bank);
    }

    public Optional<BankDto> getBankById(Long bankId) {
        Optional<Bank> bankOptional = bankRepository.findById(bankId);
        if (bankOptional.isPresent()) {
            Bank bank = bankOptional.get();
            List<AccountDTO> accounts = bank.getAccounts().stream()
                    .map(account -> new AccountDTO(
                            account.getId(),
                            account.getName(),
                            account.getBalance(),
                            account.getTransactions().stream()
                                    .map(transaction -> new TransactionDTO(
                                            transaction.getId(),
                                            transaction.getAmount(),
                                            transaction.getOriginatingAccount().getId(),
                                            transaction.getResultingAccount() != null ? transaction.getResultingAccount().getId() : null,
                                            transaction.getReason()
                                    ))
                                    .collect(Collectors.toList())
                    ))
                    .collect(Collectors.toList());

            return Optional.of(new BankDto(
                    bank.getId(),
                    bank.getName(),
                    bank.getTotalTransactionFeeAmount(),
                    bank.getTotalTransferAmount(),
                    bank.getFlatFeeAmount(),
                    bank.getPercentFeeValue(),
                    accounts
            ));
        }
        return Optional.empty();
    }

    public List<BankDto> getAllBanks() {
        List<Bank> banks = bankRepository.findAllWithAccounts();
        List<BankDto> bankDTOs = new ArrayList<>();

        for (Bank bank : banks) {
            BankDto bankDTO = new BankDto(
                    bank.getId(),
                    bank.getName(),
                    bank.getTotalTransactionFeeAmount(),
                    bank.getTotalTransferAmount(),
                    bank.getFlatFeeAmount(),
                    bank.getPercentFeeValue(),
                    new ArrayList<>()
            );

            for (Account account : bank.getAccounts()) {
                List<TransactionDTO> transactions = account.getTransactions().stream()
                        .map(transaction -> new TransactionDTO(
                                transaction.getId(),
                                transaction.getAmount(),
                                transaction.getOriginatingAccount().getId(),
                                transaction.getResultingAccount() != null ? transaction.getResultingAccount().getId() : null,
                                transaction.getReason()
                        ))
                        .collect(Collectors.toList());

                AccountDTO accountDTO = new AccountDTO(
                        account.getId(),
                        account.getName(),
                        account.getBalance(),
                        transactions
                );

                bankDTO.addAccount(accountDTO);
            }

            bankDTOs.add(bankDTO);
        }

        return bankDTOs;
    }


    public String getBankTotalTransactionFeeAmount(Long bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));
        double totalFeeAmount = bank.getTotalTransactionFeeAmount();
        return "Total transaction fee amount for bank " + bank.getName() + " is " + totalFeeAmount;
    }

    public String getBankTotalTransferAmount(Long bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));
        double totalTransferAmount = bank.getTotalTransferAmount();
        return "Total transfer amount for bank " + bank.getName() + " is " + totalTransferAmount;
    }



}
