package test.system.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import application.main.ValidateInputs;
import application.model.SavingsModel;

public class SavingsTest {
  private static final double EPSILON = 1e-2;
  private transient SavingsModel savings;
  private transient double balance;
  private transient double growthRate;
  private transient int    currentAge;
  private transient int    currentYear;
  private transient int    deathAge;
  private transient final  UtilsTest utilTest = new UtilsTest();
    
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
      savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge); 
      
      final int numberOfYears = 1;
      assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), savings.getEndingValue(currentYear), EPSILON);
  }

  @Test
  public void testCurrentAgePlusOne() {
      savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge); 

      final int numberOfYears = 2;
      assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), savings.getEndingValue(currentYear+1), EPSILON);
  }
  
  @Test
  public void testDeathAge() {
      savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge); 

      final int numberOfYears = deathAge - currentAge - 1;
      assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), savings.getEndingValue(currentYear+numberOfYears-1), EPSILON);
  }

  @Test
  public void testDeathAgePlusOne() {
      savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge); 

      final int numberOfYears = deathAge - currentAge + 1;
      assertEquals("getEndingValue()", 0.0, savings.getEndingValue(currentYear+numberOfYears), EPSILON);
  }

  @Test
  public void testWithdraw() {
      savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge);
      
      savings.withdraw(currentAge, 10.0);
      
      assertEquals("getWithdrawals()", 10.0, savings.getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void testWithdrawInvalidBalance() {
      savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge); 
      
      // Withdraw the account balance instead
      savings.withdraw(currentAge, balance+10.0);
      
      assertEquals("getWithdrawals()", balance, savings.getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void testDeposit() {
      savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge); 
      
      savings.deposit(currentAge, 10.0);
      
      assertEquals("getDeposits()", 10.0, savings.getDeposits(currentYear), EPSILON);
  }
    
  @Test
  public void testIsTaxable() {
      savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge); 
      
      assertTrue("savings.isTaxable is true.", savings.isTaxable());
  }
  
  @Test
  public void testVerifyMinAge() {
    currentAge = ValidateInputs.MIN_AGE;
    
    savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), savings.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxAge() {
    currentAge = ValidateInputs.MAX_AGE-2;
    deathAge = ValidateInputs.MAX_AGE;
    
    savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), savings.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }

  @Test
  public void testVerifyMinGrowthRate() {
    growthRate = ValidateInputs.MIN_GROWTH_RATE;
    
    savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), savings.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxGrowthRate() {
    growthRate = ValidateInputs.MAX_GROWTH_RATE;
    
    savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge); 
     
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), savings.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  } 
  
  @Test
  public void testVerifyMinDeathAge() {
    currentAge = ValidateInputs.MIN_AGE;
    deathAge = ValidateInputs.MIN_AGE+2;
    
    savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), savings.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxDeathAge() {
    currentAge = ValidateInputs.MAX_AGE-2;
    deathAge = ValidateInputs.MAX_AGE;
    
    savings = new SavingsModel(balance, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utilTest.computeEndingValue(balance, growthRate, numberOfYears), savings.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }

}
