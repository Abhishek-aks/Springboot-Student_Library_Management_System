package com.LibraryManagementSystem.LMS.DTO.RequestDto;

import com.LibraryManagementSystem.LMS.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequestDto {

    private String title;
    private int edition;
    private Genre genre;
    private String publication;
    private int authorId;
    private int price;

}
