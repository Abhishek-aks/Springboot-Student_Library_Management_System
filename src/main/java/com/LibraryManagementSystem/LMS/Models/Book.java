package com.LibraryManagementSystem.LMS.Models;

import com.LibraryManagementSystem.LMS.Enum.Genre;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor      // part of lombok-Dependency
@AllArgsConstructor     // part of lombok-Dependency
@Getter                 // part of lombok-Dependency
@Setter                 // part of lombok-Dependency
@Table(name = "book")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;

    @Enumerated(EnumType.STRING)
    Genre genre;

    int price;
    int edition;
    String publication;

    Boolean  isBookIssued = false;


    // Cardinal Relationship
    @ManyToOne
    @JoinColumn
    Author authors;

    @ManyToOne
    @JoinColumn
    LibraryCard cards;

    @OneToMany (mappedBy =  "books",cascade = CascadeType.ALL)
    List<Transaction> transactionList = new ArrayList<>();
    // when the library card issued there will be no transaction list so empty list.
    // you can define it at  the time of new card generation also - POST mapping
}
