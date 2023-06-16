package com.LibraryManagementSystem.LMS.MVC_Controller;

import com.LibraryManagementSystem.LMS.DTO.RequestDto.BookRequestDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.AuthorResponseDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.BookResponseDto;
import com.LibraryManagementSystem.LMS.Enum.Genre;
import com.LibraryManagementSystem.LMS.Models.Book;
import com.LibraryManagementSystem.LMS.MVC_Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")                // http://localhost:8080/book/add
    public String addBook(@RequestBody BookRequestDto bookRequestDto) throws Exception {
        return bookService.addBook(bookRequestDto);
    }


    @PutMapping("/updateTitle")
    public BookResponseDto updateTitle(@RequestParam int id, String title) {
        return bookService.updateTitle(id,title);
    }

    @PutMapping("/updatePrice")
    public BookResponseDto updatePrice(@RequestParam int id, int price) {
        return bookService.updatePrice(id,price);
    }

    @PutMapping("/updateGenre")
    public BookResponseDto updateGenre(@RequestParam int id, Genre genre) {
        return bookService.updateGenre(id,genre);
    }


}
/* @GetMapping("/getbyid")                  // http://localhost:8080/student/getstudentbyid
    public StudentCardResponseDto getStudentbyId(@RequestParam("id") int id) {
        return studentService.getStudentbyId(id);
        // when you run this API in the POSTMAN you will get an error - "Could not get Response "
        // if you check the log of springboot - there will be "STACKOVERFLOWERROR" : "null" - this is so
        // because
    }


    @GetMapping("/getBytitle")
    public List<StudentCardResponseDto> getstudentByName(@RequestParam("name") String name)
    {
        return studentService.getStudentByName(name);
    }

    @GetMapping("/getByGenre/{Genre}")
    public List<StudentCardResponseDto> getStudentbyBranch(@PathVariable("branch") Department department)
    {
        return studentService.getStudentByBranch(department);
    }
    @GetMapping("/getall")
    public List<StudentCardResponseDto> getAllStudents()
    {
        return studentService.getAllStudents();
    }

    // find all the books
    // find all the book from the particular authorId
    // find the number of book written by an author
 */