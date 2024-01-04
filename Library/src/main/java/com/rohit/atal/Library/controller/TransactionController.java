package com.rohit.atal.Library.controller;

import com.rohit.atal.Library.Model.Book;
import com.rohit.atal.Library.Model.Transaction;
import com.rohit.atal.Library.Model.User;
import com.rohit.atal.Library.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return  transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Optional<Transaction> getTransactionById(@PathVariable String id) {
        return transactionService.getTransactionById(Long.valueOf(id));
    }

    @PostMapping(path = "/borrow/{id}", consumes = "application/json")
    public Transaction borrowBook(@RequestBody User user, @PathVariable int id) {
        return transactionService.borrowBook(user, id);
    }
    @PostMapping("/return/{id}")
    public Transaction returnBook(@PathVariable String id) {
        return transactionService.returnBook(Long.valueOf(id));
    }
    @PostMapping("/renew/{id}")
    public Transaction renewBook(@PathVariable String id) {
        return transactionService.renewBook(Long.valueOf(id));
    }
}
