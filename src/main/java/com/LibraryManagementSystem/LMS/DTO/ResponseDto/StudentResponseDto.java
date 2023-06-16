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
public class StudentResponseDto {
// no annotation to form a relationship
    private int studentId;
    private String name;
    private int age;
    private Department department;
    private String mobileNo;
    private String email;




}
