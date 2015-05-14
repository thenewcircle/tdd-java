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

    long fromAccountId = 1L;
    double fromBalance = 1_000;
    String fromName = "Tom";
    
    long toAccountId = 2L;
    double toBalance = 5;
    String toName = "Dick";
    
    AccountDao accountDao = new InMemoryAccountDao();
    
    Account fromAccount = accountDao.getAccount(fromAccountId);
    Account toAccount = accountDao.getAccount(toAccountId);

    Assert.assertEquals(fromBalance, fromAccount.getBalance(), 0.00_001);
    Assert.assertEquals(toBalance, toAccount.getBalance(), 0.00_001);
    
    Assert.assertEquals(fromAccountId, fromAccount.getAccountId());
    Assert.assertEquals(toAccountId, toAccount.getAccountId());
    
    Assert.assertEquals(fromName, fromAccount.getName());
    Assert.assertEquals(toName, toAccount.getName());
    
    BankingService teller = new SimpleBankingService();

    double amount = 750;
    teller.transfer(fromAccountId, toAccountId, amount);
  }
}
