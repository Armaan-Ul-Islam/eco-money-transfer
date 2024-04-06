package com.aui.service;

import com.aui.model.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionService {
    private final CopyOnWriteArrayList<Transaction> transactions = new CopyOnWriteArrayList<>();

    public List<Transaction> getAllTransactions(){
        return List.copyOf(transactions);
    }

    public Optional<Transaction> getTransaction(Long id){
        return transactions
                .stream()
                .filter(transaction -> transaction.getId().equals(id))
                .findFirst();
    }

    private void postTransaction(Transaction transaction){
        transactions.add(transaction);
    }

}
