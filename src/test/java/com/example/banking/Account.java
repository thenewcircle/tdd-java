package com.example.banking;

public class Account {

  private final long accountId;
  private final String name;
  private double balance;

  public Account(long accountId, String name, double balance) {
    this.accountId = accountId;
    this.name = name;
    this.balance = balance;
  }

  public long getAccountId() {
    return accountId;
  }

  public String getName() {
    return name;
  }
  
  public double getBalance() {
    return balance;
  }

  public void debit(double amount) {
    balance -= amount;
  }

  public void credit(double amount) {
    balance += amount;
  }

}
