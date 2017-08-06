package com.codurance.bankKata.repository;

import com.codurance.bankKata.valueObject.Amount;
import com.codurance.bankKata.valueObject.Balance;
import com.codurance.bankKata.valueObject.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class TransactionRepositoryShould {

    private TransactionRepository repository;

    @Before
    public void setUp() {
        repository = new TransactionRepository();
    }

    @Test
    public void add_deposit() {
        Amount amount = new Amount(1000);
        LocalDate date = LocalDate.of(2014, 4, 2);
        Balance balance = new Balance(1000);
        Transaction transaction = new Transaction(amount, date, balance);

        repository.addDeposit(amount, date, balance);

        assertThat(repository.all(), hasItem(transaction));
    }

    @Test
    public void add_withdrawal_as_negative_transaction() {
        LocalDate date = LocalDate.of(2014, 4, 2);
        Amount amount = new Amount(-1000);
        Balance balance = new Balance(0);

        Transaction transaction = new Transaction(amount, date, balance);

        repository.addWithdrawal(amount, date, balance);

        assertThat(repository.all(), hasItem(transaction));
    }
}