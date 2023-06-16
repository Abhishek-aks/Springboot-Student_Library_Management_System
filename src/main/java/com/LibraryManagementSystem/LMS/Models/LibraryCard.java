package com.LibraryManagementSystem.LMS.Models;


import com.LibraryManagementSystem.LMS.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor      // part of lombok-Dependency
@AllArgsConstructor
@Getter
@Setter
@Table(name = "libraryCard")
public class LibraryCard {
// attibutes - cardNo, IssuedDate,ValidTill, status
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private LocalDate issuedDate;

    private LocalDate validTill;

    @Enumerated(EnumType.STRING)
    private CardStatus status;

    // connection for the card table with student table
    // OR - linking with parent class-Student class
    @OneToOne       // Cardinality Relation
    @JoinColumn     // introduce foreign key in the table (based on primary key of Student)
    Student students;    // Basicallly "student" variable connect with Student class

    @OneToMany(mappedBy = "cards",cascade = CascadeType.ALL)
    List<Book> bookIssuedList;

    @OneToMany(mappedBy = "cards", cascade = CascadeType.ALL)
    List<Transaction> transactionList = new ArrayList<>();
    // when the library card issued there will be no transaction list so empty list.
    // you can define it at  the time of new card generation also - POST mapping

}
