package com.sid.ebankservice.entities;

import com.sid.ebankservice.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private Double balance; //on a utiliser Double au lieu de double parce que dans le put endpoint si un utilisateur fait la modification et non pas add balance donc la valeur par defaut est null et si on conserver le type double donc il donne un errur
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @ManyToOne
    private Customer customer;

}
