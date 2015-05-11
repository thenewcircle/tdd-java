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
    
    long toAccountId = 1L;
    double toBalance = 5;
    
    Account fromAccount = new Account(fromAccountId, 1_000);
    Account toAccount = new Account(toAccountId, 5);

    Assert.assertEquals(fromBalance, fromAccount.getBalance(), 0.00_001);
    Assert.assertEquals(toBalance, toAccount.getBalance(), 0.00_001);
    
    Assert.assertEquals(fromAccountId, fromAccount.getAccountId());
    Assert.assertEquals(toAccountId, toAccount.getAccountId());
    
    BankingService teller = new SimpleBankingService();

    double amount = 750;
    teller.transfer(fromAccountId, toAccountId, amount);
  }
}
