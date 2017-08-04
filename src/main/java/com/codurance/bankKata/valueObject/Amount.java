package com.codurance.bankKata.valueObject;

public class Amount  {
    private int amount;

    public Amount(int amount) {
        this.amount = amount;
    }

    public boolean isNegative() {
        return amount < 0;
    }

    public boolean isPositive() {
        return amount > 0;
    }
}
