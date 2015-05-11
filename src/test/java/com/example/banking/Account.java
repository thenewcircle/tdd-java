package com.example.banking;

public class Account {

  private final long accountId;
  private double balance;


  public Account(long accountId, double balance) {
    this.accountId = accountId;
    this.balance = balance;
  }

  public long getAccountId() {
    return accountId;
  }

  public double getBalance() {
    return balance;
  }

}
