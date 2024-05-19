package com.inn.banka.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionDTO {


    private Long id;
    private double amount;
    private Long originatingAccountId;
    private Long resultingAccountId;
    private String reason;

    public TransactionDTO(Long id, double amount, Long originatingAccountId, Long resultingAccountId, String reason) {
        this.id = id;
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.reason = reason;
    }
}
