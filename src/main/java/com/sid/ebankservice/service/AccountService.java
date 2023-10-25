package com.sid.ebankservice.service;

import com.sid.ebankservice.dto.BankAccountDTOForAdd;
import com.sid.ebankservice.dto.BankAccountRequestDTO;
import com.sid.ebankservice.dto.BankAccountResponseDTO;
import com.sid.ebankservice.entities.BankAccount;

public interface AccountService {
    BankAccountResponseDTO addAccount(BankAccountDTOForAdd bankAccountDTO);
    BankAccountResponseDTO updateAccount(String id,BankAccountRequestDTO bankAccountDTO);
}
