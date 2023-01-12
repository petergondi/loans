package com.project.loans.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "sms")
@Data
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String message;
    private int contactId;
    @CreationTimestamp
    private Timestamp created_at;
}
