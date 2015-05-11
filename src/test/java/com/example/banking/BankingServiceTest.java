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
    long toAccountId = 1L;
    
    BankingService teller = new SimpleBankingService();

    double amount = 750;
    teller.transfer(fromAccountId, toAccountId, amount);
  }
}
