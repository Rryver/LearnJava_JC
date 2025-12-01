package com.kolosov.multithreadBankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {
    private final List<BankAccount> accounts = new CopyOnWriteArrayList<>();

    private final ReentrantLock lock = new ReentrantLock();

    private final Object transferMonitor = new Object();

    public BankAccount createAccount(int amount) {
        BankAccount account = new BankAccount(amount);
        accounts.add(account);
        return account;
    }

    public void transfer(BankAccount from, BankAccount to, int amount) {
        lock.lock();
        final int oldBalanceFrom = from.getBalance();
        final int oldBalanceTo = to.getBalance();
        try {
            from.withdraw(amount);
            to.deposit(amount);
        } catch (Exception e) {
            from.setBalance(oldBalanceFrom);
            to.setBalance(oldBalanceTo);
        } finally {
            lock.unlock();
        }
    }

    public int getTotalBalance() {
        return accounts.stream()
                .map(BankAccount::getBalance)
                .reduce(Integer::sum)
                .orElseThrow();
    }
}
