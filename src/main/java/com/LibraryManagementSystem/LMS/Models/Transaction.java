package com.LibraryManagementSystem.LMS.Models;

import com.LibraryManagementSystem.LMS.Enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor @AllArgsConstructor  //lombok-Dependency
@Getter @Setter                         //lombok-Dependency
@Table(name = "transaction")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String TransactionId;      //    = String.valueOf(UUID.randomUUID());

    String transactionNumber;

    @CreationTimestamp
    Date transactionDate;

    // transaction will be of 2 type -issue or return the book so enum work but will use boolean variable;
    Boolean isIssueOperation = false;

    @Enumerated(EnumType.STRING)
    TransactionStatus transactionStatus;

    @ManyToOne
    @JoinColumn
    LibraryCard cards;

    @ManyToOne
    @JoinColumn
    Book books;

}
