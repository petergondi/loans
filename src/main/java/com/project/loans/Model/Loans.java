package com.project.loans.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "loans")
@Data
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Double amount;
    private Double runningBalance;
    private int contactId;
    private String status;
    private int smsId;
    @CreationTimestamp
    private Timestamp createdAt;
}
