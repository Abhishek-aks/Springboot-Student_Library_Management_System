package com.LibraryManagementSystem.LMS.MVC_Controller;


import com.LibraryManagementSystem.LMS.DTO.RequestDto.BookTransactionRequestDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.BookTransactionResponseDto;
import com.LibraryManagementSystem.LMS.Exceptions.BookNotFoundException;
import com.LibraryManagementSystem.LMS.Exceptions.InvalidCardException;
import com.LibraryManagementSystem.LMS.Exceptions.StudentNotFoundException;
import com.LibraryManagementSystem.LMS.MVC_Service.TransactionService;
import com.LibraryManagementSystem.LMS.Models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;



    // bare min parameter required to issue the book- bookid andn cardid
    // not the studentid because card is the child of studemt and student data can be accessed by using foreign key
    // that how basically relationship reduces the redendency in your code. thus logic and database are clean instead of mess having same info in other tables
    @PostMapping("/issue")
    public BookTransactionResponseDto issueBook(@RequestBody BookTransactionRequestDto issueBookRequestDto) throws Exception {
        return transactionService.issuebook(issueBookRequestDto);
    }



    @PutMapping("/return")
    public BookTransactionResponseDto returnBook(@RequestBody BookTransactionRequestDto returnBookRequestDto) throws BookNotFoundException, InvalidCardException {
//        Transaction transaction = new Transaction();
//        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
//        transaction.setTransactionDate(new Date());
//        transaction.setIsIssueOperation(false);
//
//        BookTransactionResponseDto bookTransactionResponseDto=new BookTransactionResponseDto();
//
//        try
//        {
//            transactionResponseDto=transactionService.returnBooks(returnBookRequestDto,transaction);
//        }
//        catch (Exception e)
//        {
//            transactionResponseDto.setMessage(e.getMessage());
//            transactionResponseDto.setTransactionNo(transaction.getTransactionId());
//            transactionResponseDto.setDate(transaction.getDate().toString());
//            transactionResponseDto.setStatus(transaction.getStatus());

            return transactionService.returnBook(returnBookRequestDto);
        }


        @DeleteMapping("/deleteAll")
        public String removeAllTransaction() {
            return transactionService.removeAllTransaction();

        }
}
