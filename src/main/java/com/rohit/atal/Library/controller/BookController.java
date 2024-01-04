package com.rohit.atal.Library.controller;

import com.rohit.atal.Library.Model.Book;
import com.rohit.atal.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/books")
@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable String id) {
        return bookService.getBookById(Integer.parseInt(id));
    }

    @PostMapping(consumes = "application/json")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @DeleteMapping("/{Bookid}")
    public Book deleteBook(@PathVariable String Bookid){
        return bookService.deletebook(Integer.parseInt(Bookid));
    }
    @PutMapping(path = "/{Bookid}", consumes = "application/json")
    public Book updatebook(@PathVariable String Bookid, @RequestBody Book updatedBook) {
        return bookService.updatebook(Integer.parseInt(Bookid), updatedBook);
    }
}
