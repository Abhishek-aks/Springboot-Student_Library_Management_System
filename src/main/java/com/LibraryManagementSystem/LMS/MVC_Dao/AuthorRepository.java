package com.LibraryManagementSystem.LMS.MVC_Dao;

import com.LibraryManagementSystem.LMS.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Author findByEmail(String email);       // for unique email
    // by now there no declaration of annotation-column(unique = true)
    // so email attribute can be duplicate.
    // there for return tupe will be list of author.
    // implementation will be given by ORM, you(programmer) just need to define the method only.
    // for  List<Author> findByEmail(String email); - sevice wil have  List<Author> author = authorRepository.findByEmail(email);

}
