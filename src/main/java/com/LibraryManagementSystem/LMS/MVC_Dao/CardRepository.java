package com.LibraryManagementSystem.LMS.MVC_Dao;

import com.LibraryManagementSystem.LMS.Models.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<LibraryCard, Integer> {

}
