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
@DiscriminatorValue("ca")
@Data
@NoArgsConstructor @AllArgsConstructor
public class CurrentAccount extends Account{
    @Column(
            name = "overdraft"
    )
    private double overdraft;

    public CurrentAccount(String code, double balance, Date creationDate, Customer customer, Collection<Operation> operations, double overdraft) {
        super(code, balance, creationDate, customer, operations);
        this.overdraft = overdraft;
    }


}
