package com.sid.ebankservice.web;

import com.sid.ebankservice.dto.BankAccountRequestDTO;
import com.sid.ebankservice.dto.BankAccountResponseDTO;
import com.sid.ebankservice.entities.BankAccount;
import com.sid.ebankservice.enums.AccountType;
import com.sid.ebankservice.repositories.BankAccountRepositories;
import com.sid.ebankservice.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BanckAccountGraphQlController {
    @Autowired
    private BankAccountRepositories bankAccountRepositories;
    @Autowired
    private AccountServiceImpl accountService;
    //tout les requet de graphql est de type post
    @QueryMapping
    public List<BankAccount> accounts(){ //il faut donné le meme le noms dans le query graphql
        return bankAccountRepositories.findAll();
    }
    @QueryMapping
    public BankAccount bankAccountById(@Argument String id){ //il faut donné le meme le noms dans le query graphql
        return bankAccountRepositories.findById(id)
                .orElseThrow(()->new RuntimeException("Account not found"));
    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccountDTO){
        return accountService.addAccount(bankAccountDTO);
    }
}
