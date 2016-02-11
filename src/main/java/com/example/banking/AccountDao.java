package com.example.banking;

public interface AccountDao {

  Account getAccount(long id) throws AccountNotFoundException;

  Account saveAccount(Account account) throws AccountNotFoundException;

  void createAccount(long fromAccountId, String fromName, double fromBalance);

}
