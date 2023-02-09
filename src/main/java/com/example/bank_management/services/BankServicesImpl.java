package com.example.bank_management.services;

import com.example.bank_management.entities.*;
import com.example.bank_management.repositories.AccountRepository;
import com.example.bank_management.repositories.CustomerRepository;
import com.example.bank_management.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class BankServicesImpl implements BankServices {
    AccountRepository accountRepository;
    CustomerRepository customerRepository;
    OperationRepository operationRepository;

    public BankServicesImpl(AccountRepository accountRepository, CustomerRepository customerRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public Account displayAccount(String codeAccount) {
        Optional<Account> optionalAccount= accountRepository.findById(codeAccount);
        if(!optionalAccount.isPresent())
            throw new RuntimeException("account not found");
        return optionalAccount.get();
    }

    @Override
    public void deposit(String codeAccount, double amount) {
        Account account= accountRepository.getOne(codeAccount);

        account.setBalance(account.getBalance()+amount);
        System.out.println(account.getBalance());
        accountRepository.save(account);
        Operation operation = new Deposit(0, new Date(), amount, account);
        operationRepository.save(operation);
    }

    @Override
    public void withdraw(String codeAccount, double amount) {
        Account account= accountRepository.findById(codeAccount).get();
        double overdraft=(account instanceof CurrentAccount)? ((CurrentAccount)account).getOverdraft(): 0;
        if(account.getBalance() + overdraft < amount)
            throw new RuntimeException("it's not possible !");
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
        Operation operation = new Withdrawal(0, new Date(), amount, account);
        operationRepository.save(operation);
    }

    @Override
    public void bankTransfer(String codeAccount1, String codeAccount2, double amount) {
        if(codeAccount1.equals(codeAccount2))
            throw new RuntimeException("It's the same account");
        withdraw(codeAccount1,  amount);
        deposit(codeAccount2, amount);
    }

    @Override
    public Page<Operation> listOfOperations(String codeAccount, int page, int size) {

        return operationRepository.listOfOperations(codeAccount,PageRequest.of(page, size));
    }

}

