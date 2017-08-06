package com.codurance.bankKata.repository;

import com.codurance.bankKata.valueObject.Amount;
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
        Transaction transaction = new Transaction(new Amount(1000), LocalDate.of(2014, 4, 2));

        repository.addDeposit(transaction);

        assertThat(repository.all(), hasItem(transaction));
    }

    @Test
    public void add_withdrawal_as_negative_transaction() {
        LocalDate date = LocalDate.of(2014, 4, 2);
        Transaction transaction = new Transaction(new Amount(-1000), date);

        repository.addWithdrawal(new Transaction(new Amount(1000), date));

        assertThat(repository.all(), hasItem(transaction));
    }
}