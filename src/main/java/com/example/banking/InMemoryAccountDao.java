package com.example.banking;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAccountDao implements AccountDao {

  private static final InMemoryAccountDao INSTANCE = new InMemoryAccountDao();
  
  public static final AccountDao getInstance() {
    return INSTANCE;
  }
  
  private final Map<Long,Account> accounts = new HashMap<>();

  private InMemoryAccountDao() {
    accounts.put(1L, new Account(1L, "Tom", 1_000));
    accounts.put(2L, new Account(2L, "Dick", 5));
  }

  @Override
  public Account getAccount(long accountId) throws AccountNotFoundException {
    if (accounts.containsKey(accountId) == false) {
      throw new AccountNotFoundException(accountId);
    }
    return accounts.get(accountId);
  }

  @Override
  public void saveAccount(Account account) {
    accounts.put(account.getAccountId(), account);
  }
}
