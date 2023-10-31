package com.sid.ebankservice.service;

import com.sid.ebankservice.dto.BankAccountDTOForAdd;
import com.sid.ebankservice.dto.BankAccountRequestDTO;
import com.sid.ebankservice.dto.BankAccountResponseDTO;
import com.sid.ebankservice.entities.BankAccount;
import com.sid.ebankservice.entities.Customer;
import com.sid.ebankservice.mappers.AccountMapper;
import com.sid.ebankservice.repositories.BankAccountRepositories;
import com.sid.ebankservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;
// le fait d'utiliser la transaction de spring ca veut dire que touts les méthode soit transactionnelle

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private static Long count=7L;
    private BankAccountRepositories bankAccountRepositories;
    private AccountMapper accountMapper;
    private CustomerRepository customerRepository;
    public AccountServiceImpl(BankAccountRepositories bankAccountRepositories,AccountMapper accountMapper,CustomerRepository customerRepository){
        this.bankAccountRepositories=bankAccountRepositories;
        this.accountMapper=accountMapper;
        this.customerRepository=customerRepository;
    }
    @Override
    public BankAccountResponseDTO addAccount(BankAccountDTOForAdd bankAccountDTO) {

        bankAccountDTO.getCustomer().setId(count++);
        BankAccount bankAccount=BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .customer(bankAccountDTO.getCustomer())
                .build();

        customerRepository.save(bankAccountDTO.getCustomer());
        BankAccount savedBankAccount= bankAccountRepositories.save(bankAccount);
        BankAccountResponseDTO responseDTO=accountMapper.fromBankAccount(savedBankAccount);
        return responseDTO;
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id,BankAccountRequestDTO bankAccountDTO) {
        Customer customer=bankAccountRepositories.findById(id).orElseThrow(() -> new RuntimeException("account not found")).getCustomer();
        BankAccount bankAccount=BankAccount.builder()
                .id(id)
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .customer(customer)
                .build();
        BankAccount savedBankAccount= bankAccountRepositories.save(bankAccount);
        BankAccountResponseDTO responseDTO=accountMapper.fromBankAccount(savedBankAccount);
        return responseDTO;
    }
}
