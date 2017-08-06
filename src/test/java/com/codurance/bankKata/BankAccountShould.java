package com.codurance.bankKata;

import com.codurance.bankKata.exception.NegativeAmountException;
import com.codurance.bankKata.repository.BalanceRepository;
import com.codurance.bankKata.valueObject.Amount;
import com.codurance.bankKata.valueObject.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountShould {

    @Mock
    private Clock clock;

    @Mock
    private BalanceRepository balanceRepository;

    private BankAccount account;

    @Before
    public void setUp() {
        account = new BankAccount(clock, balanceRepository);
    }

    @Test
    public void accept_deposit() throws NegativeAmountException {
        LocalDate date = LocalDate.of(2014, 4, 2);
        Amount amount = new Amount(1000);

        given(clock.now()).willReturn(date);

        account.deposit(amount);

        verify(balanceRepository).add(new Transaction(amount, date));
    }

    @Test
    public void accept_withdrawal() throws NegativeAmountException {
        LocalDate date = LocalDate.of(2014, 4, 2);
        Amount amount = new Amount(1000);

        given(clock.now()).willReturn(date);

        account.withdraw(amount);

        verify(balanceRepository).add(new Transaction(amount, date));
    }

    @Test
    public void not_accept_withdrawal_of_negative_amount() {
        Amount amount = new Amount(-1000);

        try {
            account.withdraw(amount);
        } catch (NegativeAmountException e) {
            //
        } finally {
            verifyZeroInteractions(balanceRepository);
        }
    }

    @Test(expected = NegativeAmountException.class)
    public void throw_exception_if_withdrawal_is_negative() throws NegativeAmountException {
        Amount amount = new Amount(-1000);

        account.withdraw(amount);
    }

    @Test
    public void not_accept_deposit_of_negative_amount() {
        Amount amount = new Amount(-1000);

        try {
            account.deposit(amount);
        } catch (NegativeAmountException e) {
            //
        } finally {
            verifyZeroInteractions(balanceRepository);
        }
    }

    @Test(expected = NegativeAmountException.class)
    public void throw_exception_if_deposit_is_negative() throws NegativeAmountException {
        Amount amount = new Amount(-1000);

        account.deposit(amount);
    }
}