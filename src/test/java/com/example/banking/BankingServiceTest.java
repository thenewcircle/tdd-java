package com.example.banking;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankingServiceTest {

  private AccountDao accountDao;
  private BankingService teller;
  
  @Before
  public void beforeTest() {
    // This code is called before every test.
    accountDao = new InMemoryAccountDao();
    teller = new SimpleBankingService(accountDao);
  }
  
  @Test
  public void testHelloBanking() {
    Assert.assertTrue(true);
  }

  @Test
  public void testCreateAccount() throws Exception {
    long fromAccountId = 1L;
    double fromBalance = 1_100;
    String fromName = "Tom";

    accountDao.createAccount(fromAccountId, fromName, fromBalance);

    Account fromAccount = accountDao.getAccount(fromAccountId);
   
    Assert.assertNotNull(fromAccount);
    Assert.assertEquals(fromAccountId, fromAccount.getAccountId());
    Assert.assertEquals(fromName, fromAccount.getName());
    Assert.assertEquals(fromBalance, fromAccount.getBalance(),  0.00_001);
  }
  
  @Test
  public void testTransfer() throws Exception {
    
    long fromAccountId = 1L;
    double fromBalance = 1_100;
    String fromName = "Tom";
    
    long toAccountId = 2L;
    double toBalance = 5;
    String toName = "Dick";

    accountDao.createAccount(fromAccountId, fromName, fromBalance);
    accountDao.createAccount(toAccountId, toName, toBalance);
    
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
  public void testTransferWithMockito() throws Exception {


    long fromAccountId = 1L;
    double fromBalance = 1_100;
    String fromName = "Tom";
    
    long toAccountId = 2L;
    double toBalance = 5;
    String toName = "Dick";

//    accountDao.createAccount(fromAccountId, fromName, fromBalance);
//    accountDao.createAccount(toAccountId, toName, toBalance);
    AccountDao accountDao = Mockito.mock(AccountDao.class);
    teller = new SimpleBankingService(accountDao);
    
    Account fromAccount = new Account(fromAccountId, fromName, fromBalance);
    // Account fromAccount = accountDao.getAccount(fromAccountId);
    Mockito.when(accountDao.getAccount(fromAccountId)).thenReturn(fromAccount);
    
    Assert.assertEquals(fromBalance, fromAccount.getBalance(), 0.00_001);
    Assert.assertEquals(fromAccountId, fromAccount.getAccountId());
    Assert.assertEquals(fromName, fromAccount.getName());
    
    Account toAccount = new Account(toAccountId, toName, toBalance);
    //Account toAccount = accountDao.getAccount(toAccountId);
    Mockito.when(accountDao.getAccount(toAccountId)).thenReturn(toAccount);
    
    Assert.assertEquals(toBalance, toAccount.getBalance(), 0.00_001);
    Assert.assertEquals(toAccountId, toAccount.getAccountId());
    Assert.assertEquals(toName, toAccount.getName());
    
    double amount = 750;
    
    teller.transfer(fromAccountId, toAccountId, amount);
    
    fromAccount = accountDao.getAccount(1L);
    toAccount = accountDao.getAccount(2L);
    
    Assert.assertEquals(350, fromAccount.getBalance(), 0.00_001);
    Assert.assertEquals(755, toAccount.getBalance(), 0.00_001);

    Mockito.verify(accountDao, Mockito.never()).createAccount(Mockito.anyLong(), Mockito.anyString(), Mockito.anyDouble());
    Mockito.verify(accountDao, Mockito.times(2)).saveAccount(Mockito.any(Account.class));

    Mockito.verify(accountDao).saveAccount(fromAccount);
    Mockito.verify(accountDao).saveAccount(toAccount);
  }

  @Test
  public void testInsufficentFunds() {
    Assume.assumeNoException(new UnsupportedOperationException("Not yet implemented"));
  }
  
  @Test
  public void testZAccountNotFoundInGet() throws Exception {
    //AccountDao accountDao = InMemoryAccountDao.getInstance();
    // AccountDao accountDao = new InMemoryAccountDao();

    try {
      accountDao.getAccount(1L);
      Assert.fail("Expected some exception");
      
    } catch (AccountNotFoundException e) {
      Assert.assertEquals(1L, e.getAccountId());
      Assert.assertEquals("Account #1 was not found.", e.getMessage());
    }
    
    try {
      accountDao.getAccount(2L);
      Assert.fail("Expected some exception");
      
    } catch (AccountNotFoundException e) {
      Assert.assertEquals(2L, e.getAccountId());
      Assert.assertEquals("Account #2 was not found.", e.getMessage());
    }
    
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






