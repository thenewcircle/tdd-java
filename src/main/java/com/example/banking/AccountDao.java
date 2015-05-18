package com.example.banking;

public interface AccountDao {

  public Account getAccount(long accountId) throws AccountNotFoundException;

  public void saveAccount(Account fromAccount);

  public void createAccount(long accountId, String name, double balance);
}
