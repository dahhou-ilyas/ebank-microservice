package com.sid.ebankservice;

import com.sid.ebankservice.entities.BankAccount;
import com.sid.ebankservice.enums.AccountType;
import com.sid.ebankservice.repositories.BankAccountRepositories;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepositories bankAccountRepositories){
        return args -> {
            for (int i=0;i<10;i++){
                BankAccount bankAccount=BankAccount.builder()
                        .id(UUID.randomUUID().toString())
                        .type(Math.random()>0.5? AccountType.SAVING_ACCOUNT:AccountType.CURRENT_ACCOUNT)
                        .balance(20020+Math.random()*90000)
                        .createdAt(new Date())
                        .currency("USD")
                        .build();
                bankAccountRepositories.save(bankAccount);
            }
        };
    }

    // swagger configuration
    @Bean
    public OpenAPI usersMicroserviceOpenAPI(){
        return new OpenAPI().info(new Info().title("microservice for product")
                .description("this is microservide for product application")
                .version("1.0"));
    }

}
