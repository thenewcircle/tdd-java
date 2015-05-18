package com.example.banking;

public class AccountNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  private final long accountId;

  public AccountNotFoundException(long accountId) {
    this.accountId = accountId;
  }

  public long getAccountId() {
    return accountId;
  }
  
}
