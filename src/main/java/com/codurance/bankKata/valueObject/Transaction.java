package com.codurance.bankKata.valueObject;

import java.time.LocalDate;

public class Transaction {
    private Amount amount;
    private LocalDate date;
    private Balance balance;

    public Transaction(Amount amount, LocalDate date, Balance balance) {
        this.amount = amount;
        this.date = date;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (!amount.equals(that.amount)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
