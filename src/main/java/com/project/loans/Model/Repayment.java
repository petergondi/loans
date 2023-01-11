package com.project.loans.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "repayment")
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Double amount;
    private int loanId;
    private int status;
}
