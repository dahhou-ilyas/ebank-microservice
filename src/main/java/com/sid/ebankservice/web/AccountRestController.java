package com.sid.ebankservice.web;


import com.sid.ebankservice.entities.BankAccount;
import com.sid.ebankservice.repositories.BankAccountRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class AccountRestController {
    private BankAccountRepositories bankAccountRepositories;
    public AccountRestController(BankAccountRepositories bankAccountRepositories){
        this.bankAccountRepositories=bankAccountRepositories;

    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepositories.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepositories.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account not found")));
    }

    @PostMapping("/bankAccounts")
    public BankAccount save(@RequestBody BankAccount bankAccount){
        return bankAccountRepositories.save(bankAccount);
    }

    //on a fait put et patch dans le meme temps
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount bankAccount1=bankAccountRepositories.findById(id).orElseThrow(()->new RuntimeException(String.format("Account not found")));
        if (bankAccount.getBalance()!=null) bankAccount1.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt()!=null) bankAccount1.setCreatedAt(new Date());
        if (bankAccount.getType()!=null) bankAccount1.setType(bankAccount.getType());
        if (bankAccount.getCurrency()!=null) bankAccount1.setCurrency(bankAccount.getCurrency());
        return bankAccountRepositories.save(bankAccount);
    }

    @GetMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        bankAccountRepositories.deleteById(id);
    }


}
