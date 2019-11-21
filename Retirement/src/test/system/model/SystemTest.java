package test.system.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import application.model.Account401K;
import application.model.Account403B;
import application.model.AccountCashBalance;
import application.model.AccountIra;
import application.model.Pension;
import application.model.Salary;
import application.model.SocialSecurity;
import application.model.Taxes;

public class SystemTest {
  private static final double EPSILON = 1e-2;
  private transient Salary salary;
  private transient Account401K account401k;
  private transient Account403B account403b;
  private transient AccountCashBalance accountCashBalance;
  private transient AccountIra accountIra;
  private transient Pension pension;
  private transient SocialSecurity socialSecurity;
  private transient Taxes taxes;

  private transient double  balance;
  private transient double  inflation;
  private transient double  growthRate;
  private transient double  annualContribution;
  private transient int     currentAge;
  private transient int     currentYear;
  private transient int     retirementAge;
  private transient int     deathAge;
  private transient int     startWithdrawalsAge;
  private transient int     numberOfWithdrawals;
  private transient double  annualSalary;
  private transient double  monthlyAmount;
  private transient boolean inflationAdjusted;
  private transient double  meritIncrease;
  private transient double  federalTaxRate;
  private transient double  stateTaxRate;
   
  @Before
  public void setUp() {
    balance = 100.00;
    inflation = 0.03;
    growthRate = 0.05;
    annualContribution = 10.0;
    currentAge = 57;
    currentYear = 2016;
    retirementAge = 62;
    deathAge = 95;
    startWithdrawalsAge = 59;
    numberOfWithdrawals = 0;
    monthlyAmount = 1550.0;
    inflationAdjusted = true;
    meritIncrease = 0.02;
    federalTaxRate = 0.28;
    stateTaxRate = 0.05;
   } 
  

  @Test
  public void testHappyCase() {
    int    year;
    double totalIncome;
    double tax;
    
    salary = new Salary(annualSalary, meritIncrease, currentAge,
                            currentYear, deathAge, retirementAge);
    account401k = new Account401K(balance, growthRate, annualContribution,
                    currentAge, currentYear, retirementAge,
                    deathAge, startWithdrawalsAge, numberOfWithdrawals, salary, true);
    account403b = new Account403B(balance, growthRate, annualContribution,
                      currentAge, currentYear, retirementAge,
                      deathAge, startWithdrawalsAge, numberOfWithdrawals, salary, true);
    accountCashBalance = new AccountCashBalance(balance, growthRate, annualContribution,
                      currentAge, currentYear, retirementAge,
                      deathAge, startWithdrawalsAge, numberOfWithdrawals, salary, true);
    accountIra = new AccountIra(balance, growthRate, annualContribution,
                      currentAge, currentYear, retirementAge,
                      deathAge, startWithdrawalsAge, numberOfWithdrawals, salary, true);
    pension = new Pension(monthlyAmount, startWithdrawalsAge, inflationAdjusted, 
                            inflation, currentAge, currentYear, deathAge);
    socialSecurity = new SocialSecurity(monthlyAmount, startWithdrawalsAge, inflation, 
                            currentAge, currentYear, deathAge);
    taxes = new Taxes(inflation, currentAge, currentYear, deathAge, federalTaxRate, 
                    stateTaxRate);
        
    year = currentYear; // get to startWithdrawslsAge
    totalIncome = account401k.getEndingValue(year) + account403b.getEndingValue(year) +
                  accountCashBalance.getEndingValue(year) + accountIra.getEndingValue(year) +
                  pension.getEndingValue(year) + socialSecurity.getEndingValue(year);
    tax = taxes.compute(currentAge, totalIncome);

    assertEquals("getEndingValue()", tax, taxes.getEndingValue(currentYear), EPSILON);
  }
}
