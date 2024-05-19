package com.inn.banka.repository;

import com.inn.banka.dto.TransactionDTO;
import com.inn.banka.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query("SELECT t FROM Transaction t WHERE t.originatingAccount.id = :accountId OR t.resultingAccount.id = :accountId")
    List<Transaction> findByAccountId(@Param("accountId") Long accountId);


}
