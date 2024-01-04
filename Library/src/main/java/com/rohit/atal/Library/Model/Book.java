package com.rohit.atal.Library.Model;

import java.util.Objects;

public class Book {
    private int Bookid;
    private String BookName;
    private String Author;
    private boolean available;

    public boolean isAvailable() {
        return available;
    }

    public Book() {
        super();
    }

    public Book(int bookid, String bookName, String author, boolean available) {
        Bookid = bookid;
        BookName = bookName;
        Author = author;
        this.available = available;
    }

    public int getBookid() {
        return Bookid;
    }

    public void setBookid(int bookid) {
        Bookid = bookid;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getBookid() == book.getBookid() &&
                Objects.equals(getBookName(), book.getBookName()) &&
                Objects.equals(getAuthor(), book.getAuthor()) &&
                isAvailable() == book.isAvailable();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookid(), getBookName(), getAuthor(), isAvailable());
    }
}
