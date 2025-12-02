package com.kolosov.multithreadBankAccount;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final Integer id;
    private final AtomicInteger balance;

    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount(Integer id, int balance) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
