package com.inn.banka.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @ManyToOne
    @JoinColumn(name="originating_account_id")
    private Account originatingAccount;

    @ManyToOne
    @JoinColumn(name="resulting_account_id")
    private Account resultingAccount;

    private String reason;

    private boolean isFlatFee;

    private double feeAmount;

}
