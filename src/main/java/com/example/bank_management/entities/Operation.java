package com.example.bank_management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "OPERATIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Operation {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long number;
    @Column(
            name = "date",
            nullable = false

    )
    private Date date;
    @Column(
            name = "amount"
    )
    private double amount;
    @ManyToOne
    @JoinColumn(
            name = "account_id",
            referencedColumnName = "code",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "account_operation_foreignKey"
            )
    )
    private Account account;
}
