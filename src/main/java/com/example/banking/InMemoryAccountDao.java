package com.example.banking;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAccountDao implements AccountDao {

  // private static final AccountDao DAO = new InMemoryAccountDao();
//  public static AccountDao getInstance() {
//    return DAO;
//  }

  private final Map<Long,Account> accounts = new HashMap<>();
  
  public InMemoryAccountDao() {
  }
  
  @Override
  public Account getAccount(long id) throws AccountNotFoundException {
    if (accounts.containsKey(id) == false) {
      throw new AccountNotFoundException(id);
    }
    return accounts.get(id);
  }

  @Override
  public Account saveAccount(Account account) throws AccountNotFoundException {
    if (accounts.containsKey(account.getAccountId()) == false) {
      throw new AccountNotFoundException(account.getAccountId());
    }
    return accounts.put(account.getAccountId(), account);
  }

  @Override
  public void createAccount(long id, String name, double balance) {
    Account account = new Account(id, name, balance);
    accounts.put(id, account);
  }
}






