package com.sid.ebankservice.web;

import com.sid.ebankservice.entities.BankAccount;
import com.sid.ebankservice.repositories.BankAccountRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BanckAccountGraphQlController {
    @Autowired
    private BankAccountRepositories bankAccountRepositories;

    @QueryMapping
    public List<BankAccount> accounts(){ //il faut donné le meme le noms dans le query graphql
        return bankAccountRepositories.findAll();
    }
}
