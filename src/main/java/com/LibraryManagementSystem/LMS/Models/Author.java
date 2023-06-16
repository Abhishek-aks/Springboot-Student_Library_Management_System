package com.LibraryManagementSystem.LMS.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor      // part of lombok-Dependency
@AllArgsConstructor     // part of lombok-Dependency
@Getter                         // part of lombok-Dependency
@Setter                          // part of lombok-Dependency
@Table(name = "author")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

     String name;
     String mobileNo;
     String email;

 // whenever a new Author gets registered, list of book he/she has published or not must be initialised. initially can be no list also.
    @OneToMany (mappedBy ="authors",cascade = CascadeType.ALL)
    List<Book> book = new ArrayList<>();    // its initialised with empty array list
 // if there will be no "new ArrayList<>();" then this will give you null pointer exception
 // when value of book not provided here and not provided by client(Postman-json)

}
