package com.LibraryManagementSystem.LMS.DTO.ResponseDto;

import com.LibraryManagementSystem.LMS.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookTransactionResponseDto {
    private String transactionNumber;

    private TransactionStatus transactionStatus;
    // TransactionStatus is an enum and will not write @enumerated here
    // because this is not an Entity and the data will not go to databases.

    private String bookTitle;

}
