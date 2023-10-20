package com.sid.ebankservice.service;

import com.sid.ebankservice.dto.BankAccountRequestDTO;
import com.sid.ebankservice.dto.BankAccountResponseDTO;
import com.sid.ebankservice.entities.BankAccount;
import com.sid.ebankservice.repositories.BankAccountRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;
// le fait d'utiliser la transaction de spring ca veut dire que touts les méthode soit transactionnelle

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private BankAccountRepositories bankAccountRepositories;
    public AccountServiceImpl(BankAccountRepositories bankAccountRepositories){
        this.bankAccountRepositories=bankAccountRepositories;
    }
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount=BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount savedBankAccount= bankAccountRepositories.save(bankAccount);
        BankAccountResponseDTO responseDTO=BankAccountResponseDTO.builder()
                .balance(bankAccount.getBalance())
                .createdAt(bankAccount.getCreatedAt())
                .currency(bankAccount.getCurrency())
                .type(bankAccount.getType())
                .id(bankAccount.getId())
                .build();
        return responseDTO;
    }
}
