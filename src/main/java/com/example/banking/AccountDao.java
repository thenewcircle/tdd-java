package com.example.banking;

public interface AccountDao {

  public Account getAccount(long accountId);

  public void saveAccount(Account fromAccount);

}
