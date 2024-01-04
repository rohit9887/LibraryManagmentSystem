package com.rohit.atal.Library.service;

import com.rohit.atal.Library.Model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public abstract List<Book> getAllBooks();
    public abstract Optional<Book> getBookById(int Boookid);
    public abstract Book addBook(Book book);
    public abstract Book deletebook(int Bookid);
    public abstract Book updatebook(int bookid, Book updatedbook) ;
}
