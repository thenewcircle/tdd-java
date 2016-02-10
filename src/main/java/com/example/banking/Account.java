package com.example.banking;

public class Account {

  private final long accountId;
  private String name;
  private double balance;

  public Account(long accountId, String name, double balance) {
    this.accountId = accountId;
    this.balance = balance;
    this.name = name;
  }

  public long getAccountId() {
    return accountId;
  }

  public double getBalance() {
    return balance;
  }

  public String getName() {
    return name;
  }
}
