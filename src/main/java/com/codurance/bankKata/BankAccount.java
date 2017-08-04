package com.codurance.bankKata;

import com.codurance.bankKata.exception.NegativeDepositException;
import com.codurance.bankKata.exception.PositiveWithdrawalException;
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

    public void deposit(Amount amount) throws NegativeDepositException {
        if (amount.isNegative()) {
            throw new NegativeDepositException();
        }

        Transaction transaction = createTransaction(amount);

        balanceRepository.add(transaction);
    }

    public void withdraw(Amount amount) throws PositiveWithdrawalException {
        if (amount.isPositive()) {
            throw new PositiveWithdrawalException();
        }

        Transaction transaction = createTransaction(amount);

        balanceRepository.add(transaction);
    }

    private Transaction createTransaction(Amount amount) {
        return new Transaction(amount, clock.now());
    }
}
