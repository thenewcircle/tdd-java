package com.example.banking;

public class InMemoryAccountDao implements AccountDao {

  private static final InMemoryAccountDao INSTANCE = new InMemoryAccountDao();
  
  public static final AccountDao getInstance() {
    return INSTANCE;
  }
  
  private Account accountA;
  private Account accountB;

  private InMemoryAccountDao() {
    accountA = new Account(1L, "Tom", 1_000);
    accountB = new Account(2L, "Dick", 5);
  }
  
  @Override
  public Account getAccount(long accountId) throws AccountNotFoundException {
    if (1L == accountId) {
      return accountA;
    } else if (2L == accountId) {
      return accountB;
    } else {
      throw new AccountNotFoundException();
    }
  }

  @Override
  public void saveAccount(Account account) {
    if (1L == account.getAccountId()) {
      accountA = account;
    } else if (2L == account.getAccountId()) {
      accountB = account;
    }
  }
}
