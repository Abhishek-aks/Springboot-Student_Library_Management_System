package com.LibraryManagementSystem.LMS.MVC_Controller;

import com.LibraryManagementSystem.LMS.DTO.RequestDto.AuthorRequestDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.AuthorResponseDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.StudentResponseDto;
import com.LibraryManagementSystem.LMS.Models.Author;
import com.LibraryManagementSystem.LMS.MVC_Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")                // http://localhost:8080/author/add
    public String addAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        return authorService.addAuthor(authorRequestDto);
    }

    @GetMapping("/author/{id}")
    public AuthorResponseDto getauthor(@PathVariable("id") int id) {
        return authorService.getAuthorById(id);
    }


    @GetMapping("/getby_email")         // http://localhost:8080/author/getby_email?email=chetan@gmail.com
    public AuthorResponseDto getAuthorBYEmail(@RequestParam("email") String email){
        return authorService.getAuthorByEmail(email);
    }

    @PutMapping("/updateName")
    public AuthorResponseDto updateName(@RequestParam int id, String name)
    {
        return authorService.updateName(id,name);
    }

    @PutMapping("/updateMobile")
    public AuthorResponseDto updateMobileNo(@RequestParam int id, String mobileNo)
    {
        return authorService.updateMobile(id,mobileNo);
    }

    @PutMapping("/updateEmail")
    public AuthorResponseDto updateEmail(@RequestParam int id, String email)
    {
        return authorService.updateEmail(id,email);
    }


}
