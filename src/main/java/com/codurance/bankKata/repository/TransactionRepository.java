package com.codurance.bankKata.repository;

import com.codurance.bankKata.valueObject.Amount;
import com.codurance.bankKata.valueObject.Balance;
import com.codurance.bankKata.valueObject.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class TransactionRepository {
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Collection<Transaction> all() {
        return transactions;
    }

    public void addDeposit(Amount amount, LocalDate date, Balance balance) {
        transactions.add(new Transaction(amount, date, balance));
    }

    public void addWithdrawal(Amount amount, LocalDate date, Balance balance) {
        transactions.add(new Transaction(amount.asNegative(), date, balance));
    }
}
