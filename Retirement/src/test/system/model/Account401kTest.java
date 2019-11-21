package test.system.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import application.main.ValidateInputs;
import application.model.Account;
import application.model.Account401K;
import application.model.Salary;

public class Account401kTest extends Account {
  private static final double EPSILON = 0.01;
  private transient Account401K account401k;
  private static final transient Salary SALARY = new Salary(50_000.0, .025, 60, 2018, 95, 65);
  private transient double balance;
  private transient double growthRate;
  private transient double annualContribution;
  private transient int    currentAge;
  private transient int    currentYear;
  private transient int    retirementAge;
  private transient int    deathAge;
  private transient int    startWithdrawalsAge;
  private transient int    numberOfWithdrawals;
    
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
	final boolean onlyWhileSalary = true;
    account401k = new Account401K(balance, growthRate, annualContribution,
                                  currentAge, currentYear, retirementAge,
                                  deathAge, startWithdrawalsAge, numberOfWithdrawals,
                                  SALARY, onlyWhileSalary);
      
    final int numberOfYears = 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
                                    account401k.getEndingValue(currentYear), EPSILON);
  }

  @Test
  public void testNumberOfYearsEqualsTwo() {
	final boolean onlyWhileSalary = false;
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 

    final int numberOfYears = 2;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        account401k.getEndingValue(currentYear + 1), EPSILON);
  }
  
  @Test
  public void testDeathAge() {
	final boolean onlyWhileSalary = true;
    final int numberOfWithdrawals = 0;
    final double annualContribution = 0.0;

    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 

    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        account401k.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }

  @Test
  public void testDeathAgePlusOne() {
    final boolean onlyWhileSalary = true;
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 

    final int numberOfYears = deathAge - currentAge + 1;
    assertEquals("testDeathAgepLusOne", 0.0, account401k.getEndingValue(currentYear + numberOfYears), EPSILON);
  }

  @Test
  public void testNumberOfWithdrawals() {
    final boolean onlyWhileSalary = true;
    final double annualContribution = 0.0;
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 

    final int numberOfYears = startWithdrawalsAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        account401k.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }

  @Test
  public void testWithdraw() {
	final boolean onlyWhileSalary = true;
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary);
      
    account401k.withdraw(currentAge, 10.0);
      
    assertEquals("Withdraw()", 10.0, account401k.getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void withdrawInvalidBalance() {
	final boolean onlyWhileSalary = true;
    account401k = new Account401K(balance, growthRate, annualContribution,
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    account401k.withdraw(currentAge, balance + 40.0);
      
    final int numberOfYears = 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        account401k.getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void testDeposit() {
	final boolean onlyWhileSalary = false;
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    account401k.deposit(currentAge, 10.0);
      
    assertEquals("deposit()", 10.0 + annualContribution, account401k.getDeposits(currentYear), EPSILON);
  }
    
  @Test
  public void testIsTaxable() {
	final boolean onlyWhileSalary = true;
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    assertTrue("Ensure that account401K.isTaxable() is true", account401k.isTaxable());
  }
  
  @Test
  public void testVerifyMinAge() {
	final boolean onlyWhileSalary = true;
    final int numberOfYears;
    currentAge = ValidateInputs.MIN_AGE;
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        account401k.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxAge() {
	final boolean onlyWhileSalary = true;
    currentAge = ValidateInputs.MAX_AGE - 2;
    deathAge = ValidateInputs.MAX_AGE;
    
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        account401k.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }

  @Test
  public void testVerifyMinGrowthRate() {
	final boolean onlyWhileSalary = true;
    growthRate = ValidateInputs.MIN_GROWTH_RATE;
    
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        account401k.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxGrowthRate() {
	final boolean onlyWhileSalary = true;
    growthRate = ValidateInputs.MAX_GROWTH_RATE;
    
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        account401k.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMinAnnualContribution() {
	final boolean onlyWhileSalary = true;
    annualContribution = ValidateInputs.MIN_ANNUAL_CONTRIBUTION;
    
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        account401k.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxAnnualContribution() {
	final boolean onlyWhileSalary = true;
    annualContribution = ValidateInputs.MAX_ANNUAL_CONTRIBUTION;
    
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        account401k.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMinWithdrawalAge() {
	final boolean onlyWhileSalary = true;
    currentAge = ValidateInputs.MIN_AGE;
    startWithdrawalsAge = ValidateInputs.MIN_AGE + 1;
    
    numberOfWithdrawals = 1;
    
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = 1;      
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        account401k.getWithdrawals(currentYear + numberOfWithdrawals), EPSILON);
  }
  
  @Test
  public void testVerifyMaxWithdrawalAge() {
	final boolean onlyWhileSalary = true;
    startWithdrawalsAge = ValidateInputs.MAX_AGE - 1;
    deathAge = ValidateInputs.MAX_AGE;    
    numberOfWithdrawals = 1;
    annualContribution = 0.0;

    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution,
    		SALARY, onlyWhileSalary), 
        account401k.getWithdrawals(currentYear + numberOfYears), EPSILON);
  }
  
  @Test
  public void testVerifyMinNumberOfWithdrawal() {
	final boolean onlyWhileSalary = true;
    numberOfWithdrawals = ValidateInputs.MIN_NUMBER_OF_WITHDRAWALS;
    
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    assertEquals("Ensure minimum number of withdrawals works", 0.0,  account401k.getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void testVerifyMaxNumberOfWithdrawal() {
	final boolean onlyWhileSalary = true;
    numberOfWithdrawals = ValidateInputs.MAX_NUMBER_OF_WITHDRAWALS;
    currentAge = 18;
    deathAge = ValidateInputs.MAX_AGE;
    
    account401k = new Account401K(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
            
    assertEquals("Ensure account401k.getwithdrawals() works", 23.15, 
        account401k.getWithdrawals(currentYear + numberOfWithdrawals - 1), 
        EPSILON);
  }
}