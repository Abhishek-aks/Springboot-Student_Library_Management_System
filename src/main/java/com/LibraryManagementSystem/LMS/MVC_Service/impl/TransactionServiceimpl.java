package com.LibraryManagementSystem.LMS.MVC_Service.impl;

import com.LibraryManagementSystem.LMS.DTO.RequestDto.BookTransactionRequestDto;
import com.LibraryManagementSystem.LMS.DTO.ResponseDto.BookTransactionResponseDto;
import com.LibraryManagementSystem.LMS.Enum.CardStatus;
import com.LibraryManagementSystem.LMS.Enum.TransactionStatus;
import com.LibraryManagementSystem.LMS.Exceptions.BookNotFoundException;
import com.LibraryManagementSystem.LMS.Exceptions.InvalidCardException;
import com.LibraryManagementSystem.LMS.MVC_Dao.BookRepository;
import com.LibraryManagementSystem.LMS.MVC_Dao.CardRepository;
import com.LibraryManagementSystem.LMS.MVC_Dao.TransactionRepository;
import com.LibraryManagementSystem.LMS.MVC_Service.TransactionService;
import com.LibraryManagementSystem.LMS.Models.Book;
import com.LibraryManagementSystem.LMS.Models.LibraryCard;
import com.LibraryManagementSystem.LMS.Models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.UUID;


@Service
public class TransactionServiceimpl implements TransactionService {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public BookTransactionResponseDto issuebook(BookTransactionRequestDto issueBookRequestDto) throws Exception {
        // no matter the transaction gets failed or succesfull or pending that
        // transaction will have the Transaction Number and transaction Date
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setTransactionDate(new Date());
        transaction.setIsIssueOperation(true);      // API is to issue the book so true
        // transaction Status may get failed. or get successfull. so this cannot be predict here, instead of in try -catch block

/* conditions to be checked -
        1. if cardId and BookId are Invalid
        2. if both cardId and BookId are valid
        3. if the card status is activated or not
        4. if the book is already issued or not
 */

        // 1. check whether cardId exist(valid) or not
        LibraryCard card;
        try{
            card = cardRepository.findById(issueBookRequestDto.getCardId()).get();
        }
        catch(Exception e){ //custom exception are made to make developer life easy (to understand the point of logic-failure)
            // after throw statement control will go to Controller. so before throw statement-needed to save it in the database also

            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setIsIssueOperation(false); // initialsed the parameter of Transaction
            transactionRepository.save(transaction);

            // before throwing exception it has to be saved in data based so create TransactionRepository
            throw new Exception("Invalid cardId provided");
        }

        // 2. CardId is valid
        transaction.setCards(card);

        // 1. check the bookId exist or not .i.e, invalid bookId or not
        Book book;
        try{
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setIsIssueOperation(false);
            transactionRepository.save(transaction);

            throw new Exception("Invalid BookId provided");
        }
        //2. BookId is valid;
        transaction.setBooks(book);


        // 3. if the card status is activated or not
        if(card.getStatus()!= CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("card is not Acticated");
        }

        // 4. Whether book is already issued or not
        if(book.getIsBookIssued()== true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available to issue");
        }
// always check any parameters/attributes are left for the API you are coding
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        book.setIsBookIssued(true);
        book.setCards(card);
        book.getTransactionList().add(transaction);

        // now in card section all attributes are not relivent to this API except
        // list of book issued and list of transaction made by the card.
        card.getBookIssuedList().add(book);
        card.getTransactionList().add(transaction);

        // Optimal way to save this 3 details -can be done if parent get saved.
        // so parent for  transaction and book is card. so will save card
        cardRepository.save(card);



        // respond to client
        BookTransactionResponseDto issueBookResponseDto =new BookTransactionResponseDto();
        issueBookResponseDto.setBookTitle(book.getTitle());
        issueBookResponseDto.setTransactionNumber(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(transaction.getTransactionStatus());

        IssueBooksendMail(transaction, card, book);

        return issueBookResponseDto;

    }



    @Override
    public String removeAllTransaction(){
        transactionRepository.deleteAll();
        return "All Transactions are now Deleted";
    }



    @Override
    public BookTransactionResponseDto returnBook(@RequestBody BookTransactionRequestDto returnBookRequestDto) throws BookNotFoundException, InvalidCardException {

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setTransactionDate(new Date());
        transaction.setIsIssueOperation(false);

    /* conditions to be checked -
        1. if cardId and BookId are Invalid
        2. if both cardId and BookId are valid
        3. if the card status is activated or not (not Applicable)
        4. if the book is already issued or not (not Applicable)
    */

        // 1. check whether cardId exist(valid) or not
        LibraryCard card;
        try{
            card = cardRepository.findById(returnBookRequestDto.getCardId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setIsIssueOperation(true);
            transactionRepository.save(transaction);
            throw new InvalidCardException("Invalid cardId provided");
        }

        // 2. CardId is valid
        transaction.setCards(card);

        // 1. check the bookId exist or not .i.e, invalid bookId or not
        Book book;
        try{
            book = bookRepository.findById(returnBookRequestDto.getBookId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setIsIssueOperation(true);
            transactionRepository.save(transaction);

            throw new BookNotFoundException("Invalid BookId provided");
        }
        //2. BookId is valid;
        transaction.setBooks(book);

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        book.setIsBookIssued(false);
        book.setCards(card);
        book.getTransactionList().add(transaction);

        // now in card section all attributes are not relivent to this API except
        // list of book issued and list of transaction made by the card.
        card.getBookIssuedList().remove(book);
        card.getTransactionList().add(transaction);

        // Optimal way to save this 3 details -can be done if parent get saved.
        // so parent for  transaction and book is card. so will save card
        cardRepository.save(card);

        // respond to client
        BookTransactionResponseDto returnBookResponseDto =new BookTransactionResponseDto();
        returnBookResponseDto.setBookTitle(book.getTitle());
        returnBookResponseDto.setTransactionNumber(transaction.getTransactionNumber());
        returnBookResponseDto.setTransactionStatus(transaction.getTransactionStatus());

        returnBookSendMail(transaction, card, book);

        return returnBookResponseDto;
    }


        //Mail Function
        public void IssueBooksendMail(Transaction transaction, LibraryCard cards,Book book)
        {
            String text="Hi "+cards.getStudents().getName()
                    + "!\nCongratulations! You have been issued a book: "+book.getTitle()
                    + ", which has been written by "+book.getAuthors().getName()
                    + ". Hope you will enjoy it! \nHappy Learning! \n \n"
                    + "Here is your transaction Details: \ntransaction no: "+transaction.getTransactionNumber()
                    +"\ntransaction status: " +transaction.getTransactionStatus()
                    +"\nDate: "+transaction.getTransactionDate().toString();


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("thinkbiggetbigger.01@gmail.com");         //email via library MailId
            message.setTo(cards.getStudents().getEmail());
            message.setSubject("Congrats! book Issued Successfully! ");
            message.setText(text);
            emailSender.send(message);
        }

        public void returnBookSendMail(Transaction transaction, LibraryCard cards, Book book)
        {
            String text="Hi "+cards.getStudents().getName()
                    + "! \n You are returning a book: "+book.getTitle()
                    + ", which has been written by, "+book.getAuthors().getName()
                    + ". Hope you have enjoyed it! \n Take new book & Gain more Knowledge! \nThank You! \n \n"
                    + "Here is your transaction Details: \ntransaction no: "+transaction.getTransactionNumber()
                    +"\ntransaction status: " +transaction.getTransactionStatus()
                    +"\nDate: "+transaction.getTransactionDate().toString();


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("thinkbiggetbigger.01@gmail.com");            //email via library MailId
            message.setTo(cards.getStudents().getEmail());
            message.setSubject("Congrats! Book returned Successfully! ");
            message.setText(text);
            emailSender.send(message);
        }

}
