package com.example.banking;

public class InMemoryAccountDao implements AccountDao {

  @Override
  public Account getAccount(long accountId) {
    if (1L == accountId) {
      return new Account(accountId, "Tom", 1_000);
    } else if (2L == accountId) {
      return new Account(accountId, "Dick", 5);
    } else {
      return null;
    }
  }
}
