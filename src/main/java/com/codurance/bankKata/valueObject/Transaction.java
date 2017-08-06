package com.codurance.bankKata.valueObject;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    public static final String DD_MM_YYYY = "dd/MM/YYYY";
    public static final String SEPARATOR = " | ";
    public static final String DECIMAL_PLACES = ".00";
    private Amount amount;
    private LocalDate date;
    private Balance balance;

    public Transaction(Amount amount, LocalDate date, Balance balance) {
        this.amount = amount;
        this.date = date;
        this.balance = balance;
    }

    public LocalDate date() {
        return date;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        return date().format(DateTimeFormatter.ofPattern(DD_MM_YYYY))
                + SEPARATOR
                + decimalFormat.format(amount.getAmount())
                + SEPARATOR
                + decimalFormat.format(balance.getBalance());
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
