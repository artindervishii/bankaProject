package com.inn.banka.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double balance;


    @ManyToOne
    @JoinColumn(name = "bank_id")
    @JsonIgnoreProperties("accounts")
    private Bank bank;

    @OneToMany(mappedBy = "originatingAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactions;


}