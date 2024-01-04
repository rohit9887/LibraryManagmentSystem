package com.rohit.atal.Library.service;

import com.rohit.atal.Library.Model.Book;
import com.rohit.atal.Library.Model.Transaction;
import com.rohit.atal.Library.Model.User;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    public abstract List<Transaction> getAllTransactions();
    public abstract Optional<Transaction> getTransactionById(Long id);
    public abstract Transaction borrowBook(User user, int Bookid);
    public abstract Transaction returnBook(Long id);
    public abstract Transaction renewBook(Long id);
}
