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
  public void testTransfer() throws Exception {
    
    AccountDao accountDao = InMemoryAccountDao.getInstance();
    
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
    
    fromAccount = accountDao.getAccount(1L);
    toAccount = accountDao.getAccount(2L);
    
    Assert.assertEquals(350, fromAccount.getBalance(), 0.00_001);
    Assert.assertEquals(755, toAccount.getBalance(), 0.00_001);
  }

  @Test
  public void testInsufficentFunds() {
    Assume.assumeNoException(new UnsupportedOperationException("Not yet implemented"));
  }
  
  @Test
  // @Test(expected=AccountNotFoundException.class)
  public void testAccountNotFoundInGet() throws Exception {
    AccountDao accountDao = InMemoryAccountDao.getInstance();
    
//    Account a1 = accountDao.getAccount(1L);
//    Assert.assertNotNull(a1);
//    
//    Account a2 = accountDao.getAccount(2L);
//    Assert.assertNotNull(a2);
    
    try {
      accountDao.getAccount(3L);
      Assert.fail("Expected some exception");
      
    } catch (AccountNotFoundException e) {
      Assert.assertEquals(3L, e.getAccountId());
      Assert.assertEquals("Account #3 was not found.", e.getMessage());
    }
    
    
    // aDD "@FixMethodOrder(MethodSorters.NAME_ASCENDING)"
    // 1 test fails if no exception is throw
    // 2 Make sure account AccountNotFoundExcepiton is throw
    // 3 Make sure the AccountNotFoundException.getAccountId() == 3L
    // 4 Make sure the exception's message reads "Account #3 was not found"
  }
}






