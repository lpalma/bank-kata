package com.codurance.bankKata.repository;

import com.codurance.bankKata.valueObject.Transaction;

import java.util.ArrayList;
import java.util.Collection;

public class TransactionRepository {
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public void addDeposit(Transaction transaction) {
        transactions.add(transaction);
    }

    public Collection<Transaction> all() {
        return transactions;
    }

    public void addWithdrawal(Transaction transaction) {
        transactions.add(transaction.asNegative());
    }
}
