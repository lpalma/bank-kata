package com.codurance.bankKata.repository;

import com.codurance.bankKata.valueObject.Transaction;

import java.util.ArrayList;
import java.util.Collection;

public class BalanceRepository {
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public Collection<Transaction> all() {
        return transactions;
    }
}
