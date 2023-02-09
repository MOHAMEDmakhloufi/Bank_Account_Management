package com.example.bank_management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("deposit")
public class Deposit extends Operation{
    public Deposit(long number, Date date, double amount, Account account) {
        super(number, date, amount, account);
    }

    public Deposit() {
    }
}
