package com.LibraryManagementSystem.LMS.MVC_Controller;

import com.LibraryManagementSystem.LMS.DTO.RequestDto.StudentRequestDto;
import com.LibraryManagementSystem.LMS.DTO.RequestDto.UpdateStudentMobNoRequestDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.BookResponseDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.StudentCardResponseDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.StudentResponseDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.UpdateStudentMobNoResponseDto;
import com.LibraryManagementSystem.LMS.Enum.Department;
import com.LibraryManagementSystem.LMS.Exceptions.StudentNotFoundException;
import com.LibraryManagementSystem.LMS.MVC_Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")                        // http://localhost:8080/student/add
    public String addStudent (@RequestBody StudentRequestDto studentRequestDto){
        return studentService.addStudent(studentRequestDto);
    }

    @PutMapping("/updateMob")                   // http://localhost:8080/student/updateMob
     public UpdateStudentMobNoResponseDto updateMobileNo(@RequestBody UpdateStudentMobNoRequestDto updateStudentMobNoRequestDto) throws StudentNotFoundException {
        return studentService.updateMobileNo(updateStudentMobNoRequestDto);
    }


    // find a student by Id
    @GetMapping("/getbyid")                  // http://localhost:8080/student/getstudentbyid
    public StudentCardResponseDto getStudentbyId(@RequestParam("id") int id) {
        return studentService.getStudentbyId(id);
        // when you run this API in the POSTMAN you will get an error - "Could not get Response "
        // if you check the log of springboot - there will be "STACKOVERFLOWERROR" : "null" - this is so
        // because
    }


    @GetMapping("/getByName")
    public List<StudentCardResponseDto> getstudentByName(@RequestParam("name") String name)
    {
        return studentService.getStudentByName(name);
    }

    @GetMapping("/getByBranch/{branch}")
    public List<StudentCardResponseDto> getStudentbyBranch(@PathVariable("branch") Department department)
    {
        return studentService.getStudentByBranch(department);
    }
    @GetMapping("/getall")
    public List<StudentCardResponseDto> getAllStudents()
    {
        return studentService.getAllStudents();
    }

    @PutMapping("/getBooksbyID")    // Book List Issued by Student
    public List<BookResponseDto> getBooksofStudent(@RequestParam int id)
    {
        return studentService.getBooksofStudent(id);
    }

    @PutMapping("/updateName")
    public StudentResponseDto updateName(@RequestParam int id, String name)
    {
        return studentService.updateName(id,name);
    }

    @PutMapping("/updateBranch")
    public StudentResponseDto updateBranch(@RequestParam("id") int id, Department department)
    {
        return studentService.updateBranch(id,department);
    }

    @PutMapping("/CardStatus")
    public String BlockCard(@RequestParam("id") int id)
    {
        return studentService.BlockCard(id);
    }


    @DeleteMapping("/deleteStudentById")
    public String deleteStudentById(@RequestParam("id") int id) throws StudentNotFoundException {
        return studentService.deleteStudentById(id);
    }

}

