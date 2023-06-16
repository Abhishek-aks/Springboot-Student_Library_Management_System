package com.LibraryManagementSystem.LMS.MVC_Dao;

import com.LibraryManagementSystem.LMS.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
