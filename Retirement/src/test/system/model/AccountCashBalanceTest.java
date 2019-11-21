package test.system.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import application.main.ValidateInputs;
import application.model.AccountCashBalance;
import application.model.Salary;

public class AccountCashBalanceTest {
  private static final double EPSILON = 1e-2;
  private transient AccountCashBalance accountCashBalance;
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
  private transient boolean onlyWhileSalary;
    
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
    onlyWhileSalary = true;
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
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution,
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge,
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        accountCashBalance.getEndingValue(currentYear), EPSILON);
  }

  @Test
  public void testNumberOfYearsEqualsTwo() {
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution,
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 

    final int numberOfYears = 2;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        accountCashBalance.getEndingValue(currentYear + 1), EPSILON);
  } 
  
  @Test
  public void testDeathAge() {
    final int numberOfWithdrawals = 0;
    final double annualContribution = 0.0;
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 

    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        accountCashBalance.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }

  @Test
  public void testDeathAgePlusOne() {
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 

    final int numberOfYears = deathAge - currentAge + 1;
    assertEquals("deathAgePlusOne()", 0.0, accountCashBalance.getEndingValue(currentYear + numberOfYears), EPSILON);
  }

  @Test
  public void testNumberOfWithdrawals() {
    final double annualContribution = 0.0;
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 

    final int numberOfYears = startWithdrawalsAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        accountCashBalance.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }

  @Test
  public void testWithdraw() {
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution,
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary);
      
    accountCashBalance.withdraw(currentAge, 10.0);
      
    assertEquals("withdraw", 10.0, accountCashBalance.getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void withdrawInvalidBalance() {
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution,
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge,
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    // Withdraw the account balance instead
    accountCashBalance.withdraw(currentAge, balance + 40.0);
      
    final int numberOfYears = 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        accountCashBalance.getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void testDeposit() {
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    accountCashBalance.deposit(currentAge, 10.0);
      
    assertEquals("deposit()", annualContribution, accountCashBalance.getDeposits(currentYear), EPSILON);
  }
    
  @Test
  public void testIsTaxable() {
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution,
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    assertTrue("accountCashBalance.isTaxable() is true", accountCashBalance.isTaxable());
  }
  
  @Test
  public void testVerifyMinAge() {
    currentAge = ValidateInputs.MIN_AGE;
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        accountCashBalance.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxAge() {
    currentAge = ValidateInputs.MAX_AGE - 2;
    deathAge = ValidateInputs.MAX_AGE;
    
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution,
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        accountCashBalance.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }

  @Test
  public void testVerifyMinGrowthRate() {
    growthRate = ValidateInputs.MIN_GROWTH_RATE;
    
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution,
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        accountCashBalance.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxGrowthRate() {
    growthRate = ValidateInputs.MAX_GROWTH_RATE;
    
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution,
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        accountCashBalance.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMinAnnualContribution() {
    annualContribution = ValidateInputs.MIN_ANNUAL_CONTRIBUTION;
    
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        accountCashBalance.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxAnnualContribution() {
    annualContribution = ValidateInputs.MAX_ANNUAL_CONTRIBUTION;
    
    numberOfWithdrawals = 0;
    annualContribution = 0.0;
    
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution,
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        accountCashBalance.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMinWithdrawalAge() {
    currentAge = ValidateInputs.MIN_AGE;
    startWithdrawalsAge = ValidateInputs.MIN_AGE + 1;
    
    numberOfWithdrawals = 1;
    
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge,
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = 1;      
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        accountCashBalance.getWithdrawals(currentYear + numberOfWithdrawals), EPSILON);
  }
  
  @Test
  public void testVerifyMaxWithdrawalAge() {
    startWithdrawalsAge = ValidateInputs.MAX_AGE - 1;
    deathAge = ValidateInputs.MAX_AGE;    
    numberOfWithdrawals = 1;
    annualContribution = 0.0;

    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution,
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(computeEndingValue(balance, growthRate, numberOfYears, annualContribution, SALARY, onlyWhileSalary), 
        accountCashBalance.getWithdrawals(currentYear + numberOfYears), EPSILON);
  }
  
  @Test
  public void testVerifyMinNumberOfWithdrawal() {
    numberOfWithdrawals = ValidateInputs.MIN_NUMBER_OF_WITHDRAWALS;
    
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution, 
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
      
    assertEquals("accountCashBalance.getWithdrawals()", 0.0,  accountCashBalance.getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void testVerifyMaxNumberOfWithdrawal() {
    numberOfWithdrawals = ValidateInputs.MAX_NUMBER_OF_WITHDRAWALS;
    currentAge = 18;
    deathAge = ValidateInputs.MAX_AGE;
    
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution,
        currentAge, currentYear, retirementAge, deathAge, startWithdrawalsAge, 
        numberOfWithdrawals, SALARY, onlyWhileSalary); 
            
    assertEquals("accountCashBalance.getWithdrawals()", 23.15, 
        accountCashBalance.getWithdrawals(currentYear + numberOfWithdrawals - 1), 
        EPSILON);
  }
}
