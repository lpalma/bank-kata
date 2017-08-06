package com.codurance.bankKata;

import com.codurance.bankKata.exception.InsufficientBalanceException;
import com.codurance.bankKata.exception.NegativeAmountException;
import com.codurance.bankKata.repository.TransactionRepository;
import com.codurance.bankKata.valueObject.Amount;
import com.codurance.bankKata.valueObject.Balance;

public class Main {

    public static void main(String[] args) throws NegativeAmountException, InsufficientBalanceException {
        Clock clock = new Clock();
        TransactionRepository transactionRepository = new TransactionRepository();
        BankAccount account = new BankAccount(clock, transactionRepository, new Balance(0));
        StatementService statementService = new StatementService(account, new Console());

        account.deposit(new Amount(1000));
        account.deposit(new Amount(200));
        account.withdraw(new Amount(300));
        account.deposit(new Amount(50));
        account.withdraw(new Amount(240));

        statementService.printStatement();
    }
}
