package com.LibraryManagementSystem.LMS.DTO.ResponseDto;

import com.LibraryManagementSystem.LMS.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookResponseDto {

    private int BookId;
    private String title;
    private String publication;
    private int price;
    private Genre genre;
    private String author;
}
