package com.LibraryManagementSystem.LMS.DTO.ResponseDto;

import com.LibraryManagementSystem.LMS.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentCardResponseDto {
// no annotation to form a relationship
    private int StudentId;
    private String name;
    private int age;
    private Department department;
    private String mobileNo;
    private String email;


    //    LibraryCard card; cannot use this because It's not a good practice to involve entities in  DTO.
    // better is to create your cardResponseDTO.
    CardResponseDto cardResponseDto;
}
