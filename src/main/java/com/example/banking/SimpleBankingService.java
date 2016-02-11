package com.example.banking;

public class SimpleBankingService implements BankingService {

  private final AccountDao accountDao;
  
  public SimpleBankingService(AccountDao accountDao) {
    this.accountDao = accountDao;
  }

  @Override
  public void transfer(long fromAccountId, long toAccountId, double amount) throws AccountNotFoundException {
    // AccountDao accountDao = InMemoryAccountDao.getInstance();
    
    Account fromAccount =  accountDao.getAccount(fromAccountId);
    fromAccount.debit(amount);
    
    Account toAccount = accountDao.getAccount(toAccountId);
    toAccount.credit(amount);

    accountDao.saveAccount(fromAccount);
    accountDao.saveAccount(toAccount);
  }
}
