package com.example.bank_management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

@Entity
@DiscriminatorValue("sa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingAccount extends Account{
    @Column(
            name = "interest_rate")
    private double interestRate= 0;

    public SavingAccount(String code, double balance, Date creationDate, Customer customer, Collection<Operation> operations, double interestRate) {
        super(code, balance, creationDate, customer, operations);
        this.interestRate = interestRate;
    }
}
