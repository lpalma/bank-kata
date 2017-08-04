package com.codurance.bankKata.valueObject;

public class Amount  {
    private int amount;

    public Amount(int amount) {
        this.amount = amount;
    }

    public boolean isNegative() {
        return amount < 0;
    }

    public int value() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Amount amount1 = (Amount) o;

        return amount == amount1.amount;
    }

    @Override
    public int hashCode() {
        return amount;
    }
}
