package com.LibraryManagementSystem.LMS.DTO.RequestDto;


import com.LibraryManagementSystem.LMS.Enum.Department;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequestDto {
    private String name;
    private int age;
    private String mobileNo;
    private String email;
    private Department department;
}
