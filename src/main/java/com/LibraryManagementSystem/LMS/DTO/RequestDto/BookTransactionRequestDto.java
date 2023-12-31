package com.LibraryManagementSystem.LMS.DTO.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookTransactionRequestDto {
    private int cardId;
    private int bookId;

}
