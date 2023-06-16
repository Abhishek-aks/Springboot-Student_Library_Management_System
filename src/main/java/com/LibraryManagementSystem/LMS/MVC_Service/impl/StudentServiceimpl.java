package com.LibraryManagementSystem.LMS.MVC_Service.impl;

import com.LibraryManagementSystem.LMS.DTO.RequestDto.StudentRequestDto;
import com.LibraryManagementSystem.LMS.DTO.RequestDto.UpdateStudentMobNoRequestDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.*;
import com.LibraryManagementSystem.LMS.Enum.CardStatus;
import com.LibraryManagementSystem.LMS.Enum.Department;
import com.LibraryManagementSystem.LMS.Exceptions.StudentNotFoundException;
import com.LibraryManagementSystem.LMS.MVC_Dao.CardRepository;
import com.LibraryManagementSystem.LMS.Models.Book;
import com.LibraryManagementSystem.LMS.Models.LibraryCard;
import com.LibraryManagementSystem.LMS.Models.Student;
import com.LibraryManagementSystem.LMS.MVC_Dao.StudentRepository;
import com.LibraryManagementSystem.LMS.MVC_Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class StudentServiceimpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CardRepository cardRepository;



    // int he begining if it shows Redline- its because by then there will be no logic implementation of the API
    @Override
    public String addStudent (StudentRequestDto studentRequestDto) {
    // conversion of DTO ot entity
                 Student student = new Student();
                 student.setName(studentRequestDto.getName());
                 student.setAge(studentRequestDto.getAge());
                 student.setMobileNo(studentRequestDto.getMobileNo());
                 student.setEmail(studentRequestDto.getEmail());
                 student.setDepartment(studentRequestDto.getDepartment());

    // as soon the student gets added, New  card gets generated automatically
                LibraryCard card = new LibraryCard();
                card.setStatus(CardStatus.ACTIVATED);

    // issuedDate is current date and  valid date will be 4yr+local date(issued date of card)
                card.setIssuedDate(LocalDate.now());
                card.setValidTill(LocalDate.now().plusYears(4));
    // to define value of the student for card. since the card belongs to student to be added.
                 card.setStudents(student);

    // now we need to set the card of student,  since card need to generate 1st and then given to student.
    // Now In student class all attribute will be given by user but card will be allocated once card generate and not given by the user(i.e student)
    // this means we need to allocate card now to the student. So -  SET CARD TO THE STUDENT
                student.setCards(card);
    // this student is the student for which the card need ti be issued(Student student)

    //now all the attitube are assigned/ initialised for card and student
    // so now will save the student's data into database
                studentRepository.save(student);
    //since cascadeType.ALL=> and above live save the (parent)student's data. So, it save the (parent)Student's data and (child)card's data also.
    // thus bi-directional Mapping is done.
                return "student Added and Card Issued";
    }


    @Override
    public UpdateStudentMobNoResponseDto updateMobileNo(UpdateStudentMobNoRequestDto updateStudentMobNoRequestDto) throws StudentNotFoundException {
        // conversion from RequestDto to StudentEntity check student is present (by try block)
        Student existingStudent;
        Student updatedStudent;
        UpdateStudentMobNoResponseDto updateStudentMobNoResponseDto = new UpdateStudentMobNoResponseDto();
        try{
            existingStudent = studentRepository.findById(updateStudentMobNoRequestDto.getId()).get();   // if student exists
            existingStudent.setMobileNo(updateStudentMobNoRequestDto.getMobileNo());                //mobileNo changed
            // set the mobileNo of existing Student from attribute(mobileNo) of updateStudetMobNoRequestDto
            updatedStudent = studentRepository.save(existingStudent);                   // conversion done
            // since there is no child entity, so it's ok if you don't save it bcoz
            // this update will happen in student anyway and child is not related to this API
            // good practice whenever you change parameter in Entity in a backend-project -then save it
        }
        catch (Exception e){                                        // if studen't doent exists
            throw new StudentNotFoundException("Invalid Student Id"); // custom Exceptions

        }

         // conversion from StudentEntity to ResponseDto
            updateStudentMobNoResponseDto.setName(updatedStudent.getName());
            updateStudentMobNoResponseDto.setMobileNo(updateStudentMobNoRequestDto.getMobileNo());
            updateStudentMobNoResponseDto.setMsg("Mobile Number Updated");
            return updateStudentMobNoResponseDto;

    // since conversion done so control shifted to controller(API) layer
    }


    // Find a student by Id
     public StudentCardResponseDto getStudentbyId(int id){
        Student student =  studentRepository.findById(id).get();

            StudentCardResponseDto studentcardResponseDto = new StudentCardResponseDto();
            studentcardResponseDto.setStudentId(student.getId());
            studentcardResponseDto.setName(student.getName());
            studentcardResponseDto.setAge(student.getAge());
            studentcardResponseDto.setDepartment(student.getDepartment());
            studentcardResponseDto.setMobileNo(student.getMobileNo());
            studentcardResponseDto.setEmail(student.getEmail());

            CardResponseDto cardResponseDto = new CardResponseDto();
            cardResponseDto.setId(student.getCards().getId());
            cardResponseDto.setStatus(student.getCards().getStatus());
            cardResponseDto.setIssuedDate(student.getCards().getIssuedDate());
            cardResponseDto.setValidTill(student.getCards().getValidTill());

         // cardResponseDto and studentResponseDto are set, but now we need to set cardResponseDto to studentResponseDto
            studentcardResponseDto.setCardResponseDto(cardResponseDto);

            return studentcardResponseDto;
     }

    @Override
    public List<StudentCardResponseDto> getStudentByName(String name) {
        List<Student> studentList=studentRepository.findByName(name);
        return studentToStudentDTOs(studentList);
    }

    @Override
    public List<StudentCardResponseDto> getStudentByBranch(Department department) {
        List<Student> studentList=studentRepository.findByDepartment(department);

        return studentToStudentDTOs(studentList);
    }


    @Override
    public List<StudentCardResponseDto> getAllStudents() {
        List<Student> studentList=studentRepository.findAll();
        return studentToStudentDTOs(studentList);
    }


    @Override
    public StudentResponseDto updateName(int id, String name) {
        Student student = studentRepository.findById(id).get();
        student.setName(name);
        Student updatedStudent=studentRepository.save(student);

        StudentResponseDto studentUpdateNameDto=new StudentResponseDto();
        studentUpdateNameDto.setStudentId(student.getId());
        studentUpdateNameDto.setName(updatedStudent.getName());
        studentUpdateNameDto.setAge(updatedStudent.getAge());
        studentUpdateNameDto.setDepartment(updatedStudent.getDepartment());
        studentUpdateNameDto.setMobileNo(updatedStudent.getMobileNo());
        studentUpdateNameDto.setEmail(updatedStudent.getEmail());


        return  studentUpdateNameDto;
    }

    @Override
    public StudentResponseDto updateBranch(int id, Department department) {
        Student student= studentRepository.findById(id).get();
        student.setDepartment(department);
        Student updatedStudent= studentRepository.save(student);

        StudentResponseDto studentupdateBranchDto=new StudentResponseDto();
        studentupdateBranchDto.setStudentId(student.getId());
        studentupdateBranchDto.setName(updatedStudent.getName());
        studentupdateBranchDto.setDepartment(updatedStudent.getDepartment());
        studentupdateBranchDto.setAge(updatedStudent.getAge());
        studentupdateBranchDto.setDepartment(updatedStudent.getDepartment());
        studentupdateBranchDto.setMobileNo(updatedStudent.getMobileNo());
        studentupdateBranchDto.setEmail(updatedStudent.getEmail());

        return studentupdateBranchDto;
    }
    // delete a student by Id

    @Override
    public String BlockCard(int id) {
        Student student=studentRepository.findById(id).get();
        student.getCards().setStatus(CardStatus.BLOCKED);
        studentRepository.save(student);

        return "BLOCKED ...!!!";
    }


    @Override
    public String deleteStudentById(int id) throws StudentNotFoundException {

        Student student;
        try{
            student = studentRepository.findById(id).get();
            studentRepository.deleteById(id);
        }
        catch (Exception e){
            throw new StudentNotFoundException("Student ID is invalid");
        }

        return student.getName()+" - Student Record  Deleted Successfully";
    }



    @Override // list of Book issued By Student
    public List<BookResponseDto> getBooksofStudent(int id) {
        LibraryCard cards=cardRepository.findById(id).get();
        List<Book> bookList=cards.getBookIssuedList();
        List<BookResponseDto> bookResponseDTOList=new ArrayList<>();
        for(Book book: bookList)
        {
            BookResponseDto bookResponseDTO=new BookResponseDto();
            bookResponseDTO.setAuthor(book.getAuthors().getName());
            bookResponseDTO.setTitle(book.getTitle());
            bookResponseDTO.setGenre(book.getGenre());
            bookResponseDTO.setPublication(book.getPublication());
            bookResponseDTO.setPrice(book.getPrice());
            bookResponseDTOList.add(bookResponseDTO);
        }
        return bookResponseDTOList;
    }


    public List<StudentCardResponseDto> studentToStudentDTOs(List<Student> studentList)
    {
        List<StudentCardResponseDto> studentResponseDTOList=new ArrayList<>();

        for(Student student: studentList)
        {
            StudentCardResponseDto studentCardResponseDTO=new StudentCardResponseDto();
            studentCardResponseDTO.setStudentId(student.getId());
            studentCardResponseDTO.setName(student.getName());
            studentCardResponseDTO.setAge(student.getAge());
            studentCardResponseDTO.setDepartment(student.getDepartment());
            studentCardResponseDTO.setMobileNo(student.getMobileNo());
            studentCardResponseDTO.setEmail(student.getEmail());

            studentResponseDTOList.add(studentCardResponseDTO);

            CardResponseDto cardResponseDto = new CardResponseDto();
            cardResponseDto.setId(student.getCards().getId());
            cardResponseDto.setStatus(student.getCards().getStatus());
            cardResponseDto.setIssuedDate(student.getCards().getIssuedDate());
            cardResponseDto.setValidTill(student.getCards().getValidTill());

            // cardResponseDto and studentResponseDto are set, but now we need to set cardResponseDto to studentResponseDto
            studentCardResponseDTO.setCardResponseDto(cardResponseDto);
        }
        return studentResponseDTOList;
    }

}



