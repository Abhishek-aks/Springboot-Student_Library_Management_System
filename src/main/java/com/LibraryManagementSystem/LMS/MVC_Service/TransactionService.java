package com.LibraryManagementSystem.LMS.MVC_Service;

import com.LibraryManagementSystem.LMS.DTO.RequestDto.BookTransactionRequestDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.BookTransactionResponseDto;
import com.LibraryManagementSystem.LMS.Exceptions.BookNotFoundException;
import com.LibraryManagementSystem.LMS.Exceptions.InvalidCardException;
import com.LibraryManagementSystem.LMS.Exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    BookTransactionResponseDto issuebook(BookTransactionRequestDto issueBookRequestDto) throws Exception;

    BookTransactionResponseDto returnBook(BookTransactionRequestDto returnBookRequestDto) throws BookNotFoundException, InvalidCardException;

    String removeAllTransaction();
}
