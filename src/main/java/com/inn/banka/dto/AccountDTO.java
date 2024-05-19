package com.inn.banka.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private String name;
    private double balance;

    private List<TransactionDTO> transactions;

    public AccountDTO(Long id, String name, double balance, List<TransactionDTO> transactions) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.transactions = transactions;
    }

}
