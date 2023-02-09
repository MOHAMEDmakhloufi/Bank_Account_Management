package com.example.bank_management.services;

import com.example.bank_management.entities.Account;
import com.example.bank_management.entities.Customer;
import com.example.bank_management.entities.Operation;
import org.springframework.data.domain.Page;

public interface BankServices {
    public Account displayAccount(String codeAccount);
    public void deposit(String codeAccount, double amount);
    public void withdraw(String codeAccount, double amount);
    public void bankTransfer(String codeAccount1, String codeAccount2, double amount);
    public Page<Operation> listOfOperations(String  codeAccount, int page, int size);
}
