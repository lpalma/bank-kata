package com.codurance.bankKata;

import com.codurance.bankKata.repository.BalanceRepository;
import com.codurance.bankKata.valueObject.Amount;
import com.codurance.bankKata.valueObject.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountShould {

    @Mock
    private Clock clock;

    @Mock
    private BalanceRepository balanceRepository;

    @Test
    public void accept_deposit_and_increase_balance() {
        LocalDate date = LocalDate.of(2014, 4, 2);
        BankAccount account = new BankAccount(clock, balanceRepository);
        Amount amount = new Amount(1000);

        given(clock.now()).willReturn(date);

        account.deposit(amount);

        verify(balanceRepository).add(new Transaction(amount, date));
    }
}