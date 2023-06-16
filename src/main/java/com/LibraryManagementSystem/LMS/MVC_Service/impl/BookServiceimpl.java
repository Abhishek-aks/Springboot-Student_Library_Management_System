package com.LibraryManagementSystem.LMS.MVC_Service.impl;

import com.LibraryManagementSystem.LMS.DTO.RequestDto.BookRequestDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.AuthorResponseDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.BookResponseDto;
import com.LibraryManagementSystem.LMS.Enum.Genre;
import com.LibraryManagementSystem.LMS.MVC_Dao.BookRepository;
import com.LibraryManagementSystem.LMS.Models.Author;
import com.LibraryManagementSystem.LMS.Models.Book;
import com.LibraryManagementSystem.LMS.MVC_Dao.AuthorRepository;
import com.LibraryManagementSystem.LMS.MVC_Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookServiceimpl implements BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public String addBook(BookRequestDto bookRequestDto) throws Exception {

        // to add abook need to check whether the Author is there or not?
        // And as we need to check - so used try-Catch Block
        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setGenre(bookRequestDto.getGenre());
        book.setEdition(bookRequestDto.getEdition());
        book.setPublication(bookRequestDto.getPublication());
        book.setPrice(bookRequestDto.getPrice());


        Author author;
        try{
            author = authorRepository.findById(bookRequestDto.getAuthorId()).get();

        }
        catch (Exception e){
            throw new Exception("Author doesn't Exist. Add Author First");
        }
// IF the author exists then we need to add the new book at the end of the list
// which could be first also if the list is empty.
// So will the list first and add the new boot to the last.

        author.getBook().add(book);

// Now check all the author attribute are provided by user(Json) and book is also added in above lines.
// so Author Parameters are done.
// Then in book, attribute are provided by user(Json) and Author need to be initialised
// because from the frontend/cliend/userr side will only get author id not the body(json).
// so need to set the author also

        book.setAuthors(author);

        authorRepository.save(author);
        return "Book Added to the List";
    }
    // find all the books

    // find all the book from the particular authorId

    // find the number of book written by an author

    @Override
    public BookResponseDto updateTitle(int id, String title){
        Book book = bookRepository.findById(id).get();
        book.setTitle(title);
        Book updateBook = bookRepository.save(book);

        BookResponseDto updateBookResponseDto = new BookResponseDto();
        updateBookResponseDto.setBookId(updateBook.getId());
        updateBookResponseDto.setTitle(updateBook.getTitle());
        updateBookResponseDto.setGenre(updateBook.getGenre());
        updateBookResponseDto.setPublication(updateBook.getPublication());
        updateBookResponseDto.setPrice(updateBook.getPrice());
        updateBookResponseDto.setAuthor(updateBook.getAuthors().getName());

        return  updateBookResponseDto;
    }

    @Override
    public BookResponseDto updatePrice(int id, int price){
        Book book = bookRepository.findById(id).get();
        book.setPrice(price);
        Book updateBook = bookRepository.save(book);

        BookResponseDto updateBookResponseDto = new BookResponseDto();
        updateBookResponseDto.setBookId(updateBook.getId());
        updateBookResponseDto.setTitle(updateBook.getTitle());
        updateBookResponseDto.setGenre(updateBook.getGenre());
        updateBookResponseDto.setPublication(updateBook.getPublication());
        updateBookResponseDto.setPrice(updateBook.getPrice());
        updateBookResponseDto.setAuthor(updateBook.getAuthors().getName());

        return  updateBookResponseDto;
    }

    @Override
    public  BookResponseDto updateGenre(int id, Genre genre){
        Book book = bookRepository.findById(id).get();
        book.setGenre(genre);
        Book updateBook = bookRepository.save(book);

        BookResponseDto updateBookResponseDto = new BookResponseDto();
        updateBookResponseDto.setBookId(updateBook.getId());
        updateBookResponseDto.setTitle(updateBook.getTitle());
        updateBookResponseDto.setGenre(updateBook.getGenre());
        updateBookResponseDto.setPublication(updateBook.getPublication());
        updateBookResponseDto.setPrice(updateBook.getPrice());
        updateBookResponseDto.setAuthor(updateBook.getAuthors().getName());

        return  updateBookResponseDto;

    }



}
/*

Ques - when we are passing the author id then why we need to set the Author in BookService
Ans- From the postman we are only taking the author id.Also, if we don't set the author in bookService
then all other attribute of author will become null. But we need to map the complete author details with book.
So, in the try block we searched author by its ID and get all the details were saved in author variable
then we set the author object of Author class to fill all the details of the author instead of being null.

 */

