package test.system.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import application.main.ValidateInputs;
import application.model.Brokerage;

public class BrokerageTest {
  private static final double EPSILON = 1e-2;
  private transient Brokerage brokerage;
  private transient double balance;
  private transient double growthRate;
  private transient int    currentAge;
  private transient int    currentYear;
  private transient int    deathAge;
  private final transient UtilsTest utilTest = new UtilsTest();
    
  @Before
  public void setUp() {
      balance = 100.0;
      growthRate = 0.05;
      currentAge = 57;
      currentYear = 2016;
      deathAge = 95;
  } 
  
  @Test  
  public void testHappyCaseCurrentAge() {
      brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge); 
      
      final int numberOfYears = 1;
      assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), brokerage.getEndingValue(currentYear), EPSILON);
  }

  @Test
  public void testCurrentAgePlusOne() {
      brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge); 

      final int numberOfYears = 2;
      assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), brokerage.getEndingValue(currentYear+1), EPSILON);
  }
  
  @Test
  public void testDeathAge() {
      brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge); 

      final int numberOfYears = deathAge - currentAge - 1;
      assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), brokerage.getEndingValue(currentYear+numberOfYears-1), EPSILON);
  }

  @Test
  public void testDeathAgePlusOne() {
      brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge); 

      final int numberOfYears = deathAge - currentAge + 1;
      assertEquals("getEndingValue()", 0.0, brokerage.getEndingValue(currentYear+numberOfYears), EPSILON);
  }

  @Test
  public void testWithdraw() {
      brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge);
      
      brokerage.withdraw(currentAge, 10.0);
      
      assertEquals("getWithdrawals()", 10.0, brokerage.getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void testWithdrawInvalidBalance() {
      brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge); 
      
      // Withdraw the account balance instead
      brokerage.withdraw(currentAge, balance+10.0);
      
      assertEquals("getWithdrawals", balance, brokerage.getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void testDeposit() {
      brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge); 
      
      brokerage.deposit(currentAge, 10.0);
      
      assertEquals("deposit()", 10.0, brokerage.getDeposits(currentYear), EPSILON);
  }
    
  @Test
  public void testIsTaxable() {
      brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge); 
      
      assertFalse("brokerate.isTaxable() is false", brokerage.isTaxable());
  }
  
  @Test
  public void testVerifyMinAge() {
    currentAge = ValidateInputs.MIN_AGE;
    
    brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), brokerage.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxAge() {
    currentAge = ValidateInputs.MAX_AGE-2;
    deathAge = ValidateInputs.MAX_AGE;
    
    brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), brokerage.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }

  @Test
  public void testVerifyMinGrowthRate() {
    growthRate = ValidateInputs.MIN_GROWTH_RATE;
    
    brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), brokerage.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxGrowthRate() {
    growthRate = ValidateInputs.MAX_GROWTH_RATE;
    
    brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge); 
     
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), brokerage.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  } 
  
  @Test
  public void testVerifyMinDeathAge() {
    currentAge = ValidateInputs.MIN_AGE;
    deathAge = ValidateInputs.MIN_AGE+2;
    
    brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), brokerage.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxDeathAge() {
    currentAge = ValidateInputs.MAX_AGE-2;
    deathAge = ValidateInputs.MAX_AGE;
    
    brokerage = new Brokerage(balance, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), brokerage.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
}
