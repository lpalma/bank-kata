package com.codurance.bankKata;

import com.codurance.bankKata.valueObject.Transaction;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class StatementService {
    public static final String HEADER = "DATE | AMOUNT | BALANCE";
    private BankAccount bankAccount;
    private Console console;

    public StatementService(BankAccount bankAccount, Console console) {
        this.bankAccount = bankAccount;
        this.console = console;
    }

    public void printStatement() {
        console.printLn(HEADER);

        bankAccount.transactions()
                .stream()
                .map(Transaction::toString)
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(line -> console.printLn(line));
    }
}
