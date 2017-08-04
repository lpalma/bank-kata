package com.codurance.bankKata.repository;

import com.codurance.bankKata.valueObject.Amount;
import com.codurance.bankKata.valueObject.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class BalanceRepositoryShould {

    private BalanceRepository repository;

    @Before
    public void setUp() {
        repository = new BalanceRepository();
    }

    @Test
    public void add_transaction() {
        Transaction transaction = new Transaction(new Amount(1000), LocalDate.of(2014, 4, 2));

        repository.add(transaction);

        assertThat(repository.all(), hasItem(transaction));
    }
}