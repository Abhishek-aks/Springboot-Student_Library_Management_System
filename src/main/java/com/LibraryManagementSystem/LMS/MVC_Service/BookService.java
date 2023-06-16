package com.LibraryManagementSystem.LMS.MVC_Service;

import com.LibraryManagementSystem.LMS.DTO.RequestDto.BookRequestDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.AuthorResponseDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.BookResponseDto;
import com.LibraryManagementSystem.LMS.Enum.Genre;
import com.LibraryManagementSystem.LMS.Models.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    public String addBook(BookRequestDto bookRequestDto) throws Exception;

    public BookResponseDto updateTitle(int id, String title);

    public BookResponseDto updatePrice(int id, int price);

    public BookResponseDto updateGenre(int id, Genre genre);
}
