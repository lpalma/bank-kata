package com.codurance.bankKata;

import com.codurance.bankKata.exception.NegativeAmountException;
import com.codurance.bankKata.repository.TransactionRepository;
import com.codurance.bankKata.valueObject.Amount;
import com.codurance.bankKata.valueObject.Transaction;

public class BankAccount {
    private Clock clock;
    private TransactionRepository transactionRepository;

    public BankAccount(Clock clock, TransactionRepository transactionRepository) {
        this.clock = clock;
        this.transactionRepository = transactionRepository;
    }

    public void deposit(Amount amount) throws NegativeAmountException {
        Transaction transaction = createTransaction(amount);

        transactionRepository.addDeposit(transaction);
    }

    public void withdraw(Amount amount) throws NegativeAmountException {
        Transaction transaction = createTransaction(amount);

        transactionRepository.addWithdrawal(transaction);
    }

    private Transaction createTransaction(Amount amount) throws NegativeAmountException {
        if (amount.isNegative()) {
            throw new NegativeAmountException();
        }

        return new Transaction(amount, clock.now());
    }
}
