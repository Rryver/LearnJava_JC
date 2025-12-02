package com.kolosov.multithreadBankAccount;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentBank {
    private static final AtomicInteger idVersion = new AtomicInteger(1);
    private final List<BankAccount> accounts = new CopyOnWriteArrayList<>();


    public BankAccount createAccount(int amount) {
        BankAccount account = new BankAccount(idVersion.getAndIncrement(), amount);
        accounts.add(account);
        return account;
    }

    public void transfer(BankAccount from, BankAccount to, int amount) {
        final BankAccount first = from.getId() < to.getId() ? from : to;
        final BankAccount second = from.getId() < to.getId() ? to : from;

        first.getLock().lock();
        second.getLock().lock();

        final int oldBalanceFrom = first.getBalance();
        final int oldBalanceTo = second.getBalance();
        try {
            from.withdraw(amount);
            to.deposit(amount);
        } catch (Exception e) {
            from.setBalance(oldBalanceFrom);
            to.setBalance(oldBalanceTo);
            System.out.println("Ошибка перевода: " + e.getMessage());
        } finally {
            first.getLock().unlock();
            second.getLock().unlock();
        }
    }

    public int getTotalBalance() {
        return accounts.stream()
                .map(BankAccount::getBalance)
                .reduce(Integer::sum)
                .orElseThrow();
    }
}
