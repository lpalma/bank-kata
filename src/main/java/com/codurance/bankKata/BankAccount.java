package com.codurance.bankKata;

import com.codurance.bankKata.exception.InsufficientBalanceException;
import com.codurance.bankKata.exception.NegativeAmountException;
import com.codurance.bankKata.repository.TransactionRepository;
import com.codurance.bankKata.valueObject.Amount;
import com.codurance.bankKata.valueObject.Balance;
import com.codurance.bankKata.valueObject.Transaction;

import java.util.Collection;

public class BankAccount {
    private Clock clock;
    private TransactionRepository transactionRepository;
    private Balance balance;

    public BankAccount(Clock clock, TransactionRepository transactionRepository, Balance balance) {
        this.clock = clock;
        this.transactionRepository = transactionRepository;
        this.balance = balance;
    }

    public Balance balance() {
        return balance;
    }

    public void deposit(Amount amount) throws NegativeAmountException {
        if (amount.isNegative()) {
            throw new NegativeAmountException();
        }

        increaseBalance(amount);

        transactionRepository.addDeposit(amount, clock.now(), balance);
    }

    public void withdraw(Amount amount) throws NegativeAmountException, InsufficientBalanceException {
        if (amount.isNegative()) {
            throw new NegativeAmountException();
        }

        if (balance.getBalance() < amount.getAmount()) {
            throw new InsufficientBalanceException();
        }

        decreaseBalance(amount);

        transactionRepository.addWithdrawal(amount, clock.now(), balance);
    }

    public Collection<Transaction> transactions() {
        return transactionRepository.all();
    }

    private void decreaseBalance(Amount amount) {
        balance = balance.decreaseBy(amount.getAmount());
    }

    private void increaseBalance(Amount amount) {
        balance = balance.increaseBy(amount.getAmount());
    }
}
