package com.example.banking;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankingServiceTest {

  @Test
  public void testHelloBanking() {
    Assert.assertTrue(true);
  }
  
  @Test
  public void testTransfer() throws AccountNotFoundException {

    long fromAccountId = 1L;
    double fromBalance = 1_000;
    String fromName = "Tom";
    
    long toAccountId = 2L;
    double toBalance = 5;
    String toName = "Dick";
    
    AccountDao accountDao = InMemoryAccountDao.getInstance();
    
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

    Assert.assertEquals(250, fromAccount.getBalance(), 0.00_001);
    Assert.assertEquals(755.00, toAccount.getBalance(), 0.00_001);  
  }

  @Test
  public void testInsufficientFunds() throws Exception {
    Assume.assumeNoException(new UnsupportedOperationException("Not yet implemented"));
  }

  @Test
  public void testAccountNotFoundInGet() throws Exception {
    AccountDao accountDao = InMemoryAccountDao.getInstance();

    Account a1 = accountDao.getAccount(1L);
    Assert.assertNotNull(a1);
    
    Account a2 = accountDao.getAccount(2L);
    Assert.assertNotNull(a2);

    try {
      accountDao.getAccount(3L);
      Assert.fail("Expected some exception");

    } catch (AccountNotFoundException e) {
      Assert.assertEquals(3L, e.getAccountId());
      Assert.assertEquals("Account #3 was not found.", e.getMessage());
    }
  }

  @Test
  public void testAccountNotFoundWhenSaving() throws Exception {
    Assume.assumeNoException(new UnsupportedOperationException("Not yet implemented"));
  }
}
