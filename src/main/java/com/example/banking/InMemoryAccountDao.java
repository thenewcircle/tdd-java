package com.example.banking;

public class InMemoryAccountDao implements AccountDao {

  @Override
  public Account getAccount(long id) {
    if (id == 1L) {
      return new Account(id, "Tom", 1_100);
    } else if (id == 2L){
      return new Account(id, "Dick", 5);
    }
    return null;
  }
}
