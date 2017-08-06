package com.codurance.bankKata.valueObject;

public class Balance {
    private int balance;

    public Balance(int balance) {
        this.balance = balance;
    }

    public Balance increaseBy(Integer amount) {
        return new Balance(balance + amount);
    }

    public Balance decreaseBy(Integer amount) {
        return new Balance(balance - amount);
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Balance balance1 = (Balance) o;

        return balance == balance1.balance;
    }

    @Override
    public int hashCode() {
        return balance;
    }
}
