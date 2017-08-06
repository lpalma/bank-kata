package com.codurance.bankKata;

import com.codurance.bankKata.valueObject.Amount;
import com.codurance.bankKata.valueObject.Balance;
import com.codurance.bankKata.valueObject.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class StatementServiceShould {

    @Mock
    Console console;

    @Mock
    private BankAccount bankAccount;

    private StatementService statementService;

    @Before
    public void setUp() {
        statementService = new StatementService(bankAccount, console);
    }

    @Test
    public void print_statement_in_reverse_chronological_order() {
        given(bankAccount.transactions()).willReturn(asList(
                transaction(amount(1000), date(2014, 4, 1), balance(1000)),
                transaction(amount(-100), date(2014, 4, 2), balance(900)),
                transaction(amount(500), date(2014, 4, 10), balance(1400))
        ));

        statementService.printStatement();

        InOrder inOrder = inOrder(console);

        inOrder.verify(console).printLn("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLn("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLn("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLn("01/04/2014 | 1000.00 | 1000.00");
    }

    private Balance balance(int balance) {
        return new Balance(balance);
    }

    private Transaction transaction(Amount amount, LocalDate date, Balance balance) {
        return new Transaction(amount, date, balance);
    }

    private LocalDate date(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    private Amount amount(int value) {
        return new Amount(value);
    }
}