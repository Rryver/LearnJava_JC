package com.kolosov.multithreadBankAccount;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
    private final AtomicInteger balance;

    public BankAccount(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    public void deposit(int amount) {
        balance.addAndGet(amount);
    }

    public void withdraw(int amount) {
        if (getBalance() < amount) {
            throw new RuntimeException("На счете нет достаточной суммы для совершения операции");
        }
        balance.addAndGet(amount * -1);
    }

    public int getBalance() {
        return balance.get();
    }

    public void setBalance(int balance) {
        this.balance.set(balance);
    }
}
