package com.example.banking;

public class AccountNotFoundException extends Exception {

  private final long accountId;

  public AccountNotFoundException(long accountId) {
    super("Account #" + accountId + " was not found.");
    this.accountId = accountId;
  }

  public long getAccountId() {
    return accountId;
  }
}
