package com.codurance.bankKata;

import com.codurance.bankKata.repository.BalanceRepository;
import com.codurance.bankKata.valueObject.Amount;

public class BankAccount {
    private Clock clock;
    private BalanceRepository balanceRepository;

    public BankAccount(Clock clock, BalanceRepository balanceRepository) {
        this.clock = clock;
        this.balanceRepository = balanceRepository;
    }

    public void deposit(Amount amount) {
        balanceRepository.add(amount, clock.now());
    }

    public void withdraw(int amount) {
        throw new UnsupportedOperationException();
    }
}
