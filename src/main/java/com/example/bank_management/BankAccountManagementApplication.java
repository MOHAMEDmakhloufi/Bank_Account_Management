package com.example.bank_management;

import com.example.bank_management.entities.*;
import com.example.bank_management.repositories.AccountRepository;
import com.example.bank_management.repositories.CustomerRepository;
import com.example.bank_management.repositories.OperationRepository;
import com.example.bank_management.services.BankServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import java.util.Date;

@SpringBootApplication
public class BankAccountManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountManagementApplication.class, args);
    }
    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository,
                                        CustomerRepository customerRepository,
                                        OperationRepository operationRepository,
                                        BankServices bankServices){
        return args -> {
            Customer c1= customerRepository.save(new Customer(0, "houssem", null));
            Customer c2= customerRepository.save(new Customer(0, "mohamed ", null));

            Account a1= accountRepository.save(new CurrentAccount("c1", 9000, new Date(), c1, null, 800));
            Account a2= accountRepository.save(new SavingAccount("c2", 5800, new Date(), c2, null, 670));



            operationRepository.save(new Deposit(0, new Date(), 9000, a1));
            operationRepository.save(new Deposit(0, new Date(), 6882, a1));
            operationRepository.save(new Deposit(0, new Date(), 2380, a1));
            operationRepository.save(new Withdrawal(0, new Date(), 9882, a1));

            bankServices.bankTransfer("c1", "c2", 450);
        };
    }
}
