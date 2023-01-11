package com.project.loans.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "loans")
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Double amount;
    private Double runningBalance;
    private int contactId;
    private int status;
}
