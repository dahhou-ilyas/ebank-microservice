package com.sid.ebankservice.repositories;

import com.sid.ebankservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepositories extends JpaRepository<BankAccount,String> {

}
