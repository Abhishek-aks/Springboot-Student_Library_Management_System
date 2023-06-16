package com.LibraryManagementSystem.LMS.MVC_Service;

import com.LibraryManagementSystem.LMS.DTO.RequestDto.StudentRequestDto;
import com.LibraryManagementSystem.LMS.DTO.RequestDto.UpdateStudentMobNoRequestDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.BookResponseDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.StudentCardResponseDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.StudentResponseDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.UpdateStudentMobNoResponseDto;
import com.LibraryManagementSystem.LMS.Enum.Department;
import com.LibraryManagementSystem.LMS.Exceptions.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

     public String addStudent(StudentRequestDto studentRequestDto);

     public StudentCardResponseDto getStudentbyId(int id);

     List<StudentCardResponseDto> getStudentByName(String name);

     List<StudentCardResponseDto> getStudentByBranch(Department department);

     List<BookResponseDto> getBooksofStudent(int id);
     public List<StudentCardResponseDto> getAllStudents();



     StudentResponseDto updateName(int id, String name);

     StudentResponseDto updateBranch(int id, Department department);

     public UpdateStudentMobNoResponseDto updateMobileNo(UpdateStudentMobNoRequestDto updateStudentMobNoRequestDto) throws StudentNotFoundException;

     String BlockCard(int id);

     String deleteStudentById(int id) throws StudentNotFoundException;

}