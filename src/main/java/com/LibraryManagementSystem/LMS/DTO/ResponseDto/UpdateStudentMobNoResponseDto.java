package com.LibraryManagementSystem.LMS.DTO.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateStudentMobNoResponseDto {
    private String Name;
    private String MobileNo;
    private String msg;
}

