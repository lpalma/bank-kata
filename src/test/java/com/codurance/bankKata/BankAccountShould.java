package com.codurance.bankKata;

import com.codurance.bankKata.exception.InsufficientBalanceException;
import com.codurance.bankKata.exception.NegativeAmountException;
import com.codurance.bankKata.repository.TransactionRepository;
import com.codurance.bankKata.valueObject.Amount;
import com.codurance.bankKata.valueObject.Balance;
import com.codurance.bankKata.valueObject.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountShould {

    @Mock
    private Clock clock;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    public void accept_deposit_and_increase_balance() throws NegativeAmountException {
        BankAccount account = accountWithBalance(0);
        LocalDate date = LocalDate.of(2014, 4, 2);
        Amount amount = new Amount(1000);
        Balance balance = new Balance(1000);

        given(clock.now()).willReturn(date);

        account.deposit(amount);

        verify(transactionRepository).addDeposit(amount, date, balance);

        assertThat(account.balance(), equalTo(balance));
    }

    @Test
    public void accept_withdrawal_and_decrease_balance() throws NegativeAmountException, InsufficientBalanceException {
        BankAccount account = accountWithBalance(1000);
        LocalDate date = LocalDate.of(2014, 4, 2);
        Amount amount = new Amount(500);
        Balance balance = new Balance(500);

        given(clock.now()).willReturn(date);

        account.withdraw(amount);

        verify(transactionRepository).addWithdrawal(amount, date, balance);

        assertThat(account.balance(), equalTo(balance));
    }

    @Test
    public void not_accept_withdraw_when_balance_is_insufficient() throws NegativeAmountException {
        BankAccount account = accountWithBalance(0);

        Amount amount = new Amount(10);

        try {
            account.withdraw(amount);
        } catch (InsufficientBalanceException e) {
            //
        } finally {
            verifyZeroInteractions(transactionRepository);
        }
    }

    @Test
    public void not_accept_withdrawal_of_negative_amount() throws InsufficientBalanceException {
        BankAccount account = accountWithBalance(0);

        Amount amount = new Amount(-1000);

        try {
            account.withdraw(amount);
        } catch (NegativeAmountException e) {
            //
        } finally {
            verifyZeroInteractions(transactionRepository);
        }
    }

    @Test
    public void not_accept_deposit_of_negative_amount() {
        BankAccount account = accountWithBalance(0);

        Amount amount = new Amount(-1000);

        try {
            account.deposit(amount);
        } catch (NegativeAmountException e) {
            //
        } finally {
            verifyZeroInteractions(transactionRepository);
        }
    }

    private BankAccount accountWithBalance(int balance) {
        return new BankAccount(clock, transactionRepository, new Balance(balance));
    }
}
