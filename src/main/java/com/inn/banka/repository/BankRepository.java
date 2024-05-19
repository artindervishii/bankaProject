package com.inn.banka.repository;

import com.inn.banka.dto.BankDto;
import com.inn.banka.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank,Long> {

    @Query("SELECT b FROM Bank b LEFT JOIN FETCH b.accounts")
    List<Bank> findAllWithAccounts();


}
