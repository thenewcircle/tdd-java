package com.example.banking;

public class InMemoryAccountDao implements AccountDao {

  private static final AccountDao DAO = new InMemoryAccountDao();
  
  public static AccountDao getInstance() {
    return DAO;
  }

//  private final Map<Long,Account> accounts = new HashMap<>();
  
  private Account accountA = new Account(1L, "Tom", 1_100);
  private Account accountB = new Account(2L, "Dick", 5);
  
  private InMemoryAccountDao() {
  }
  
  @Override
  public Account getAccount(long id) throws AccountNotFoundException {
    if (id == 1L) {
      // accountA = new Account(id, "Tom", 1_100);
      return accountA;

    } else if (id == 2L){
      // accountB = new Account(id, "Dick", 5);
      return accountB;

    } else {
      throw new AccountNotFoundException(id);
    }
  }

  @Override
  public Account saveAccount(Account account) throws AccountNotFoundException {
    if (1L == account.getAccountId()) {
      accountA = account;
      return account;

    } else if (2L == account.getAccountId()) {
      accountB = account;
      return account;
    
    } else {
      throw new AccountNotFoundException(account.getAccountId());
    }
  }
}






