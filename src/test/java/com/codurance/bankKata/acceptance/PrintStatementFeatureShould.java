package com.codurance.bankKata.acceptance;

import com.codurance.bankKata.BankAccount;
import com.codurance.bankKata.Clock;
import com.codurance.bankKata.Console;
import com.codurance.bankKata.StatementService;
import com.codurance.bankKata.exception.InsufficientBalanceException;
import com.codurance.bankKata.exception.NegativeAmountException;
import com.codurance.bankKata.repository.TransactionRepository;
import com.codurance.bankKata.valueObject.Amount;
import com.codurance.bankKata.valueObject.Balance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeatureShould {

    @Mock
    private Console console;

    @Mock
    private Clock clock;

    private StatementService statementService;

    private BankAccount account;

    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        transactionRepository = new TransactionRepository();
        account = new BankAccount(clock, transactionRepository, new Balance(0));
        statementService = new StatementService(account, console);
    }

    @Test
    public void print_statement_in_reverse_chronological_order() throws NegativeAmountException, InsufficientBalanceException {
        given(clock.now()).willReturn(
                date(2014, 4, 1),
                date(2014, 4, 2),
                date(2014, 4, 10));

        account.deposit(new Amount(1000));
        account.withdraw(new Amount(100));
        account.deposit(new Amount(500));

        statementService.printStatement();

        InOrder inOrder = inOrder(console);

        inOrder.verify(console).printLn("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLn("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLn("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLn("01/04/2014 | 1000.00 | 1000.00");
    }

    private LocalDate date(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }
}
