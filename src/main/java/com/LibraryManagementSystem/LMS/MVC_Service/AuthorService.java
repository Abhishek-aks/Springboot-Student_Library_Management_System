package com.LibraryManagementSystem.LMS.MVC_Service;

import com.LibraryManagementSystem.LMS.DTO.RequestDto.AuthorRequestDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.AuthorResponseDto;
import com.LibraryManagementSystem.LMS.Models.Author;

public interface AuthorService {
   public String addAuthor(AuthorRequestDto authorRequestDto);

   public AuthorResponseDto getAuthorByEmail(String email);

   public AuthorResponseDto getAuthorById(int id);

   public  AuthorResponseDto updateName(int id, String name);

   AuthorResponseDto updateMobile(int id, String mobileNo);

   AuthorResponseDto updateEmail(int id, String email);
}
