package com.sid.ebankservice.entities;

import com.sid.ebankservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAccount.class)
public interface AccountProjection {
    public String getId();
    public AccountType getType();
}
