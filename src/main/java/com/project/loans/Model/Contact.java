package com.project.loans.Model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "contacts")
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fullName;
    private String MISDN;
}
