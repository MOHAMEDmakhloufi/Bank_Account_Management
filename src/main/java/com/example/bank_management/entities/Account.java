package com.example.bank_management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Entity
@Table(name = "ACCOUNTS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor @AllArgsConstructor @ToString
public class Account {
    @Id
    @Column(
            name = "code",
            nullable = false
    )
    protected String code;
    @Column(
            name = "balance",
            nullable = false
    )
    protected double balance;
    @Column(
            name = "creation_date",
            nullable = false
    )
    protected Date creationDate;

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "customer_account_foregnKey"
            )
    )
    protected Customer customer;

    @OneToMany(
            mappedBy = "account",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    protected Collection<Operation> operations;
}
