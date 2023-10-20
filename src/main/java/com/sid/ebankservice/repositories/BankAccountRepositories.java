package com.sid.ebankservice.repositories;

import com.sid.ebankservice.entities.BankAccount;
import com.sid.ebankservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface BankAccountRepositories extends JpaRepository<BankAccount,String> {

    List<BankAccount> findByType(AccountType type);
}
