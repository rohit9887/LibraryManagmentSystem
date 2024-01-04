package com.rohit.atal.Library.ServiceTest;

import com.rohit.atal.Library.Model.Book;
import com.rohit.atal.Library.service.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceTest {

    @Autowired
    private BookServiceImpl bookServiceimpl;

    List<Book> Booklist = new ArrayList<>();

    public BookServiceTest() {
        this.bookServiceimpl = new BookServiceImpl();

        Booklist.add(new Book(12,"java8","M.K.",true));
        Booklist.add(new Book(13,"python","Suresh",true));

        this.bookServiceimpl.setBookCollection(Booklist);
    }



    @Test
    public void getAllBooksTest(){

        List<Book> Expected = Booklist;
        List<Book> Actual = bookServiceimpl.getAllBooks();

        Assertions.assertArrayEquals(Expected.toArray(), Actual.toArray());
        //Assertions.assertIterableEquals(Expected,Actual);
    }

    @Test
    public void getBookById(){
        Optional<Book> Expected = Optional.ofNullable(Booklist.get(0));
        Optional<Book> Actual = bookServiceimpl.getBookById(12);
        Assertions.assertEquals(Expected,Actual);
    }

    @Test
    public void addBook(){
        Book book = new Book(14,"c++","ram",true);
        Book Expected = book;
        Book Actual = bookServiceimpl.addBook(book);
        Assertions.assertEquals(Expected,Actual);
    }

    @Test
    public void deleteBook(){
        Book Expected = Booklist.get(0);
        Book Actual = bookServiceimpl.deletebook(12);
        Assertions.assertEquals(Expected,Actual);
    }

    @Test
    public void updateBook(){
        Book book = new Book(12,"data science","devendra",true);

        //Book Actual = Booklist.get(0);
        //System.out.println("Booklist.get(0) : " + Booklist.get(0).getBookid() + " " + Booklist.get(0).getBookName() + " " + Booklist.get(0).getAuthor());

        Book Expected = book;

        //System.out.println("Booklist.get(0) : " + Booklist.get(0).getBookid() + " " + Booklist.get(0).getBookName() + " " + Booklist.get(0).getAuthor());
        Book Actual = bookServiceimpl.updatebook(12,book);

//        System.out.println("book : " + book.getBookid() + " " + book.getBookName() + " " + book.getAuthor());
//        System.out.println("Expected : " + Expected.getBookid() + " " + Expected.getBookName() + " " + Expected.getAuthor());
//        System.out.println("Actual : " + Actual.getBookid() + " " + Actual.getBookName() + " " + Actual.getAuthor());
        Assertions.assertEquals(Expected,Actual);
    }
}
