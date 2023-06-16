package com.LibraryManagementSystem.LMS.DTO.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorResponseDto {
    private int AuthorId;
    private String name;
    private String mobileNo;
    private String email;

}
