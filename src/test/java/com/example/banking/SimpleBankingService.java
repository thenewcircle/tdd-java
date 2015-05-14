package com.example.banking;

public class SimpleBankingService implements BankingService {

  @Override
  public void transfer(long fromAccountId, long toAccountId, double amount) {
    AccountDao accountDao = new InMemoryAccountDao();

    Account fromAccount = accountDao.getAccount(fromAccountId);
    fromAccount.debit(amount);
    accountDao.saveAccount(fromAccount);

    Account toAccount = accountDao.getAccount(toAccountId);
    toAccount.credit(amount);
    accountDao.saveAccount(toAccount);
  }
}
