package com.rohit.atal.Library.Model;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    private Long id;
    private Book book;
    private User user;

    private LocalDate borrowdate;
    private LocalDate returndate;

    public Transaction(Long id, Book book, User user, LocalDate borrowdate, LocalDate returndate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.borrowdate = borrowdate;
        this.returndate = returndate;
    }

    public Transaction() {
        super();
    }

    public Transaction(int i, Book book) {
    }

    public Transaction(int i, Book book, User devendra) {
    }

    public Transaction(int i, Book book, User rohit, LocalDate now) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(LocalDate borrowdate) {
        this.borrowdate = borrowdate;
    }

    public LocalDate getReturndate() {
        return returndate;
    }

    public void setReturndate(LocalDate returndate) {
        this.returndate = returndate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(book, that.book) && Objects.equals(user, that.user) && Objects.equals(borrowdate, that.borrowdate) && Objects.equals(returndate, that.returndate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, user, borrowdate, returndate);
    }
}
