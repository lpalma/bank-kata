package com.codurance.bankKata;

import com.codurance.bankKata.exception.NegativeAmountException;
import com.codurance.bankKata.repository.BalanceRepository;
import com.codurance.bankKata.valueObject.Amount;
import com.codurance.bankKata.valueObject.Transaction;

public class BankAccount {
    private Clock clock;
    private BalanceRepository balanceRepository;

    public BankAccount(Clock clock, BalanceRepository balanceRepository) {
        this.clock = clock;
        this.balanceRepository = balanceRepository;
    }

    public void deposit(Amount amount) throws NegativeAmountException {
        if (amount.isNegative()) {
            throw new NegativeAmountException();
        }

        Transaction transaction = createTransaction(amount);

        balanceRepository.add(transaction);
    }

    public void withdraw(Amount amount) throws NegativeAmountException {
        if (amount.isNegative()) {
            throw new NegativeAmountException();
        }

        Transaction transaction = createTransaction(amount);

        balanceRepository.add(transaction);
    }

    private Transaction createTransaction(Amount amount) {
        return new Transaction(amount, clock.now());
    }
}
