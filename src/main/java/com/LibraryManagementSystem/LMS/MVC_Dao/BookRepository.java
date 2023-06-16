package com.LibraryManagementSystem.LMS.MVC_Dao;

import com.LibraryManagementSystem.LMS.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
