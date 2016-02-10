package com.example.banking;

import org.junit.Assert;
import org.junit.Test;

public class BankingServiceTest {

  @Test
  public void testHelloBanking() {
    Assert.assertTrue(true);
  }

  @Test
  public void testTransfer() {
    
    AccountDao accountDao = new InMemoryAccountDao();
    
    BankingService teller = new SimpleBankingService();

    long fromAccountId = 1L;
    double fromBalance = 1_100;
    String fromName = "Tom";
    
    long toAccountId = 2L;
    double toBalance = 5;
    String toName = "Dick";

    // Account fromAccount = new Account(fromAccountId, fromName, fromBalance);
    Account fromAccount = accountDao.getAccount(fromAccountId);
    
    Assert.assertEquals(fromBalance, fromAccount.getBalance(), 0.00_001);
    Assert.assertEquals(fromAccountId, fromAccount.getAccountId());
    Assert.assertEquals(fromName, fromAccount.getName());
    
    // Account toAccount = new Account(toAccountId, toName, toBalance);
    Account toAccount = accountDao.getAccount(toAccountId);
    
    Assert.assertEquals(toBalance, toAccount.getBalance(), 0.00_001);
    Assert.assertEquals(toAccountId, toAccount.getAccountId());
    Assert.assertEquals(toName, toAccount.getName());
    
    double amount = 750;
    
    teller.transfer(fromAccountId, toAccountId, amount);
  }
}
