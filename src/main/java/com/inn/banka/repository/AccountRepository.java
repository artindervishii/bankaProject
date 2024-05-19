package com.inn.banka.repository;

import com.inn.banka.dto.AccountDTO;
import com.inn.banka.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {



}
