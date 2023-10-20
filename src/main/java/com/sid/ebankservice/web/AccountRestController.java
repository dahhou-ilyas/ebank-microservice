package com.sid.ebankservice.web;


import com.sid.ebankservice.dto.BankAccountRequestDTO;
import com.sid.ebankservice.dto.BankAccountResponseDTO;
import com.sid.ebankservice.entities.BankAccount;
import com.sid.ebankservice.mappers.AccountMapper;
import com.sid.ebankservice.repositories.BankAccountRepositories;
import com.sid.ebankservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepositories bankAccountRepositories;
    private AccountService accountService;
    private AccountMapper accountMapper;
    public AccountRestController(BankAccountRepositories bankAccountRepositories,AccountService accountService,AccountMapper accountMapper){
        this.bankAccountRepositories=bankAccountRepositories;
        this.accountService=accountService;
        this.accountMapper=accountMapper;
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
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO bankAccountRequestDTO){
        return accountService.addAccount(bankAccountRequestDTO);
    }

    //on a fait put et patch dans le meme temps
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount bankAccount1=bankAccountRepositories.findById(id).orElseThrow(()->new RuntimeException(String.format("Account not found")));
        if (bankAccount.getBalance()!=null) bankAccount1.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt()==null) bankAccount1.setCreatedAt(new Date());
        if (bankAccount.getType()!=null) bankAccount1.setType(bankAccount.getType());
        if (bankAccount.getCurrency()!=null) bankAccount1.setCurrency(bankAccount.getCurrency());
        return bankAccountRepositories.save(bankAccount);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
        bankAccountRepositories.deleteById(id);
    }


}
