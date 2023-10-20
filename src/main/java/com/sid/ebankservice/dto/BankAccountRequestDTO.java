package com.sid.ebankservice.dto;

import com.sid.ebankservice.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//pour le crée un compte il faut just en voyer le balance et currency et le type

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class BankAccountRequestDTO {
    private Double balance; //on a utiliser Double au lieu de double parce que dans le put endpoint si un utilisateur fait la modification et non pas add balance donc la valeur par defaut est null et si on conserver le type double donc il donne un errur
    private String currency;
    private AccountType type;
}
