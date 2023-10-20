package com.sid.ebankservice.service;

import com.sid.ebankservice.dto.BankAccountRequestDTO;
import com.sid.ebankservice.dto.BankAccountResponseDTO;
import com.sid.ebankservice.entities.BankAccount;
import com.sid.ebankservice.mappers.AccountMapper;
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
    private AccountMapper accountMapper;
    public AccountServiceImpl(BankAccountRepositories bankAccountRepositories,AccountMapper accountMapper){
        this.bankAccountRepositories=bankAccountRepositories;
        this.accountMapper=accountMapper;
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
        BankAccountResponseDTO responseDTO=accountMapper.fromBankAccount(savedBankAccount);
        return responseDTO;
    }
}
