package com.LibraryManagementSystem.LMS.DTO.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateStudentMobNoRequestDto {
    private int id;
    private String mobileNo;
}
