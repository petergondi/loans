package com.project.loans.Model;

import jakarta.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "contacts")
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String fullName;
    @NotNull
    private String MSISDN;
    @CreationTimestamp
    private Timestamp createdAt;
}
