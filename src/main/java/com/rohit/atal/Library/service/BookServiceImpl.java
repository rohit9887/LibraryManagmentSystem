package com.rohit.atal.Library.service;

import com.rohit.atal.Library.Model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    List<Book> BookCollection = new ArrayList<Book>();


    public List<Book> getBookCollection() {
        return BookCollection;
    }

    public void setBookCollection(List<Book> bookCollection) {
        this.BookCollection = bookCollection;
        //System.out.println("size : " + BookCollection.size());
    }

    public BookServiceImpl() {
        BookCollection.add(new Book(12, "physics", "lovebabbar", true));
    }

    @Override
    public List<Book> getAllBooks() {
        return BookCollection;
    }

    @Override
    public Optional<Book> getBookById(int Bookid) {
        Optional<Book> result = Optional.empty();
        for (Book obj : BookCollection) {
            if (obj.getBookid() == Bookid) {
                result = Optional.of(obj);
                break;
            }
        }
        //System.out.println("Book not found ");
        return result;
    }

    @Override
    public Book addBook(Book book) {
        BookCollection.add(book);
        return book;
    }

    @Override
    public Book deletebook(int Bookid) {
        //BookCollection.removeIf(obj -> obj.getBookid() == Bookid);
        for (Book obj : BookCollection) {
            if (obj.getBookid() == Bookid) {
                BookCollection.remove(obj);
                return obj;
            }
        }
        return new Book();
    }

    @Override
    public Book updatebook(int Bookid, Book updatedbook) {
        for (Book obj : BookCollection) {
            if (obj.getBookid() == Bookid) {
                obj.setBookName(updatedbook.getBookName());
                obj.setAuthor(updatedbook.getAuthor());
                return obj;
            }
        }
        return new Book();
    }
}
