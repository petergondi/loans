package com.project.loans.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "sms")
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String message;
    private int contactId;
}
