package test.system.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import application.main.ValidateInputs;
import application.model.Account;
import application.model.AccountRoth;
import application.model.Salary;

public class AccountRothTest extends Account {
  private static final double EPSILON = 1e-2;
  private static final transient Salary SALARY = new Salary(50_000.0, .025, 60, 2018, 95, 65);
  private transient AccountRoth accountRoth;
  private transient double balance;
  private transient double growthRate;
  private transient double annualContribution;
  private transient int    currentAge;
  private transient int    currentYear;
  private transient int    retirementAge;
  private transient int    deathAge;
  private transient int    startWithdrawalsAge;
  private transient int    numberOfWithdrawals;
  private transient boolean onlyWithSalary;
  
  @Before
  public void setUp() {
      balance = 100.0;
      growthRate = 0.05;
      annualContribution = 10.0;
      currentAge = 57;
      currentYear = 2016;
      retirementAge = 62;
      deathAge = 95;
      startWithdrawalsAge = 62;
      numberOfWithdrawals = 10;
      onlyWithSalary = true;
   } 
  
  private double computeEndingValue(final double balance, 
                                final double growthRate, 
                                final int numberOfYears,
                                final double annualContribution, 
                                final Salary SALARY,
                                final boolean onlyWhileSalary) {
    double value = balance;
    
    for (int i = 0; i < numberOfYears; i++) {
      if (onlyWhileSalary && SALARY.getEndingValue(currentYear + i) > 0.0) {
         value += annualContribution;
      }
      else {
      if (!onlyWhileSalary) {
        value += annualContribution;
      }
      }
      value += value * growthRate;
   }
    return value;
  }
  
  @Test
  public void testHappyCase() {
      accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, 
                                    currentYear, retirementAge, deathAge, startWithdrawalsAge, 
                                    numberOfWithdrawals, SALARY, onlyWithSalary); 
      
      final int numberOfYears = 1;
      assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, 
          SALARY, onlyWithSalary), accountRoth.getEndingValue(currentYear), EPSILON);
  }

  @Test
  public void testNumberOfYearsEqualsTwo() {
      accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, 
          retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 

      final int numberOfYears = 2;
      assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWithSalary), accountRoth.getEndingValue(currentYear+1), EPSILON);
  }
  
  @Test
  public void testDeathAge() {
    final int numberOfWithdrawals = 0;
    final double annualContribution = 0.0;
    
    accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 

    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWithSalary), accountRoth.getEndingValue(currentYear+numberOfYears-1), EPSILON);
  }

  @Test
  public void testDeathAgePlusOne() {
      accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 

      final int numberOfYears = deathAge - currentAge + 1;
      assertEquals("DeathAgePlusOne", 0.0, accountRoth.getEndingValue(currentYear+numberOfYears), EPSILON);
  }

  @Test
  public void testNumberOfWithdrawals() {
    final double annualContribution = 0.0;
    accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 

    final int numberOfYears = startWithdrawalsAge - currentAge - 1;
    
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWithSalary), accountRoth.getEndingValue(currentYear+numberOfYears-1), EPSILON);
  }

  @Test
  public void testWithdraw() {
      accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary);
      
      accountRoth.withdraw(currentAge, 10.0);
      
      assertEquals("withdraw()", 10.0, accountRoth.getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void withdrawInvalidBalance() {
      accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 
      
      // Withdraw the account balance instead
      accountRoth.withdraw(currentAge, balance+40.0);
      
      final int numberOfYears = 1;
      assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWithSalary), accountRoth.getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void testDeposit() {
      accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 
      
      accountRoth.deposit(currentAge, 10.0);
      
      assertEquals("deposit()", annualContribution, accountRoth.getDeposits(currentYear), EPSILON);
  }
    
  @Test
  public void testIsTaxable() {
      accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 
      
      assertFalse("accountRoth.isTaxble() is false", accountRoth.isTaxable());
  }
  
  @Test
  public void testVerifyMinAge() {
    currentAge = ValidateInputs.MIN_AGE;
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWithSalary), accountRoth.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxAge() {
    currentAge = ValidateInputs.MAX_AGE-2;
    deathAge = ValidateInputs.MAX_AGE;
    
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWithSalary), accountRoth.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }

  @Test
  public void testVerifyMinGrowthRate() {
    growthRate = ValidateInputs.MIN_GROWTH_RATE;
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWithSalary), accountRoth.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxGrowthRate() {
    growthRate = ValidateInputs.MAX_GROWTH_RATE;
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWithSalary), accountRoth.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMinAnnualContribution() {
    annualContribution = ValidateInputs.MIN_ANNUAL_CONTRIBUTION;
    
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 
     
    final int numberOfYears = 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWithSalary), accountRoth.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxAnnualContribution() {
    annualContribution = ValidateInputs.MAX_ANNUAL_CONTRIBUTION;
    
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWithSalary), accountRoth.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMinWithdrawalAge() {
    currentAge = ValidateInputs.MIN_AGE;
    startWithdrawalsAge = ValidateInputs.MIN_AGE+1;
    
    numberOfWithdrawals = 1;
    
    accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 
      
    final int numberOfYears = 1;      
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWithSalary), 
             accountRoth.getWithdrawals(currentYear + numberOfWithdrawals), EPSILON);
  }
  
  @Test
  public void testVerifyMaxWithdrawalAge() {
    startWithdrawalsAge = ValidateInputs.MAX_AGE-1;
    deathAge = ValidateInputs.MAX_AGE;    
    numberOfWithdrawals = 1;
    annualContribution = 0.0;

    accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWithSalary), 
             accountRoth.getWithdrawals(currentYear + numberOfYears), EPSILON);
  }
  
  @Test
  public void testVerifyMinNumberOfWithdrawals() {
    numberOfWithdrawals = ValidateInputs.MIN_NUMBER_OF_WITHDRAWALS;
    
      accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 
      
      assertEquals("MinNumberOfWithdrawals", 0.0,  accountRoth.getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void testVerifyMaxNumberOfWithdrawals() {
    numberOfWithdrawals = ValidateInputs.MAX_NUMBER_OF_WITHDRAWALS;
    currentAge = 18;
    deathAge = ValidateInputs.MAX_AGE;
    
    accountRoth = new AccountRoth(balance, growthRate, annualContribution, currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, numberOfWithdrawals, SALARY, onlyWithSalary); 
            
    assertEquals("MaxNumberOfWithdrawals", 23.15, accountRoth.getWithdrawals(currentYear + numberOfWithdrawals - 1), EPSILON);
  }
}