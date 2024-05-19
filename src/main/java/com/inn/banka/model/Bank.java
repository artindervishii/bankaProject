package com.inn.banka.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bank {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private double totalTransactionFeeAmount;

    private double totalTransferAmount;

    private double flatFeeAmount;

    private double percentFeeValue;


    @OneToMany(mappedBy = "bank", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Account> accounts;





}
