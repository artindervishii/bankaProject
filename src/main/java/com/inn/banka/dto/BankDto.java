package com.inn.banka.dto;

import com.inn.banka.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDto {

    private Long id;
    private String name;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double flatFeeAmount;
    private double percentFeeValue;
    private List<AccountDTO> accounts;

    public void addAccount(AccountDTO account) {
        this.accounts.add(account);
    }


}
