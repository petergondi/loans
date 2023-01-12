package com.project.loans.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "repayment")
@Data
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Double amount;
    private int loanId;
    private int smsId;
    private int contactId;
    @CreationTimestamp
    private Timestamp createdAt;
}
