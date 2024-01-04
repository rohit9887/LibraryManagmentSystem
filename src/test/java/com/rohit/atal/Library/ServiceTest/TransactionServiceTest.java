package com.rohit.atal.Library.ServiceTest;

import com.rohit.atal.Library.Model.Book;
import com.rohit.atal.Library.Model.Transaction;
import com.rohit.atal.Library.Model.User;
import com.rohit.atal.Library.service.BookServiceImpl;
import com.rohit.atal.Library.service.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TransactionServiceTest {
    @Autowired
    private TransactionServiceImpl transactionServiceimpl;
    @Autowired
    private BookServiceImpl bookServiceimpl;
    List<Transaction> Transactionlist = new ArrayList<>();
    List<Book> Booklist = new ArrayList<>();

    public TransactionServiceTest(){
        this.transactionServiceimpl = new TransactionServiceImpl();
        Book book = new Book(12,"physics","lovebabbar",true);
        User user = new User(22,"Rohit");
        Transactionlist.add(new Transaction(31L,book,user,LocalDate.now(),LocalDate.now()));
        transactionServiceimpl.setTransactionData(Transactionlist);

        this.bookServiceimpl = new BookServiceImpl();
        Booklist.add(book);
        bookServiceimpl.setBookCollection(Booklist);
    }


    @Test
    public void getAllTransactionsTest(){
        List<Transaction> Expected = Transactionlist;
        List<Transaction> Actual = transactionServiceimpl.getAllTransactions();
        Assertions.assertEquals(Expected,Actual);
    }


    @Test
    public void getTransactionByIdTest(){
        Optional<Transaction> Expected = Optional.ofNullable(Transactionlist.get(0));
        Optional<Transaction> Actual = transactionServiceimpl.getTransactionById(31L);

        Assertions.assertEquals(Expected,Actual);
    }

    @Test
    public void borrowBookTest(){
        Book book = new Book(12,"physics","lovebabbar",false);
        User user = new User(21,"Rohit");
        Transaction Actual = transactionServiceimpl.borrowBook(user,12);
        Transaction Expected = new Transaction(1L,book, user, LocalDate.now(), LocalDate.now().plusWeeks(1));
//        System.out.println("Actual : ");
//        System.out.println(Actual.getId() + " " + Actual.getBook() + " " + Actual.getUser() + " " + Actual.getBorrowdate() + " " + Actual.getReturndate());
//        System.out.println("Expected : ");
//        System.out.println(Expected.getId() + " " + Expected.getBook() + " " + Expected.getUser() + " " + Expected.getBorrowdate() + " " + Expected.getReturndate());
//
//        System.out.println(Actual.getBook().getBookid() + " " + Actual.getBook().getBookName() + " " + Actual.getBook().getAuthor() + " " + Actual.getBook().isAvailable());
//        System.out.println(Expected.getBook().getBookid() + " " + Expected.getBook().getBookName() + " " + Expected.getBook().getAuthor() + " " + Expected.getBook().isAvailable());
          Assertions.assertEquals(Expected,Actual);
    }

    @Test
    public void returnBookTest(){
        Transaction Expected = Transactionlist.get(0);
        Transaction Actual = transactionServiceimpl.returnBook(31L);
        Assertions.assertEquals(Expected,Actual);
    }

    @Test
    public void renewBookTest(){
        Transaction Expected = Transactionlist.get(0);
        Transaction Actual = transactionServiceimpl.renewBook(31L);
        Assertions.assertEquals(Expected,Actual);
    }
}
