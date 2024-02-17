package com.rohit.atal.Library.service;

import com.rohit.atal.Library.Model.Book;
import com.rohit.atal.Library.Model.Transaction;
import com.rohit.atal.Library.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private BookServiceImpl bookServiceimpl;
    List<Transaction> TransactionData = new ArrayList<>();

    public List<Transaction> getTransactionData() {
        return TransactionData;
    }

    public void setTransactionData(List<Transaction> transactionData) {
        TransactionData = transactionData;
    }

    public TransactionServiceImpl() {
        this.bookServiceimpl = new BookServiceImpl();
        Book book = new Book(16, "maths", "sitaram", true);
        User user = new User(101, "rohit");
        Transaction transaction = new Transaction(12L, new Book(16, "maths", "sita", true),
                new User(14, "rohit"), LocalDate.now(), LocalDate.now());
        TransactionData.add(transaction);
    }


    @Override
    public List<Transaction> getAllTransactions() {
        return TransactionData;
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        Optional<Transaction> reault = Optional.empty();
        for (Transaction obj : TransactionData) {
            if (Objects.equals(obj.getId(), id)) {
                reault = Optional.of(obj);
                break;
            }
        }
        return reault;
    }

    @Override
    public Transaction borrowBook(User user, int Bookid) {
        Transaction transaction = new Transaction();
        //System.out.println("size : " + bookServiceimpl.BookCollection.size());
        for (Book obj : bookServiceimpl.BookCollection) {
            if (obj.getBookid() == Bookid) {
                if (obj.isAvailable()) {
                    obj.setAvailable(false);
                    transaction.setId(1L);
                    transaction.setUser(user);
                    transaction.setBook(obj);
                    transaction.setBorrowdate(LocalDate.now());
                    transaction.setReturndate(LocalDate.now().plusWeeks(1));
                    TransactionData.add(transaction);
                    //transaction_id+=1L;
                    return transaction;
                }
            }
        }
        return new Transaction();
    }

    @Override
    public Transaction returnBook(Long id) {
        Transaction transaction = new Transaction();
        for (Transaction obj : TransactionData) {
            if (Objects.equals(obj.getId(), id)) {
                obj.setReturndate(LocalDate.now());
                obj.getBook().setAvailable(true);
                TransactionData.add(obj);
                return obj;
            }
        }
        return transaction;
    }

    @Override
    public Transaction renewBook(Long id) {
        Transaction transaction = new Transaction();
        for (Transaction obj : TransactionData) {
            if (Objects.equals(obj.getId(), id)) {
                //obj.setBorrowdate(LocalDate.now());
                obj.setReturndate(LocalDate.now().plusWeeks(1));
                TransactionData.add(obj);
                return obj;
            }
        }
        return transaction;
    }
}
