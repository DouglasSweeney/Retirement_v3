package application.model;

import application.system.ApplicationLogger;
import application.system.ResultsDataNode;

/**
 * Implementation of an 401K account.
 *
 * @version release-1.0.0 Initial version
 * @author  D.K.Sweeney 1/22/2016
 *
 */
public class Account401K extends Account implements Accounts {
  /**
   * Write out trace execution methods via this variable.
   * 
   * @var ApplicationLogger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();
    
  /**
   * This variable keeps track of the current balance.
   *
   * @var balance             The current account balance.
   */
  private final transient double balance;

  /**
   * This variable keeps track of the current growth rate.
   *
   * @var growthRate          The expected yearly growth rate of the account.
   */
  private final transient double growthRate;

  /**
   * This variable keeps track of the current age of the user.
   *
   * @var currentAge          The current age of the person
   */
  private final transient int currentAge;

  /**
   * This variable keeps track of the current year.
   *
   * @var currentYear         The current year of the person owning the account
   */
  private final transient int currentYear;

  /**
   * This variable keeps track of the death age of the user.
   *
   * @var deathAge            The expected death age of the person
   */
  private final transient int deathAge;

  /**
   * This variable keeps track of the starting withdrawls age of the 401K user.
   *
   * @var startWithdrawalsAge The age to start taking withdrawals
   */
  private final transient int startWithdrawalsAge;

  /**
   * This variable keeps track of the number of withdrawls.
   *
   * @var numberOfWithdrawals The number of withdrawals to make 
   *
   */
  private final transient int numberOfWithdrawals;
    
  /**
   * This method builds the initial data structure.
   *
   * @param balance             The current account balance.
   * @param growthRate          The expected yearly growth rate of the account.
   * @param annualContribution  The annual contribution to the account
   * @param currentAge          The current age of the person
   * @param currentYear         The current year of the person owning the account
   * @param retirementAge       The age at which contributions are stopped 
   * @param deathAge            The expected death age of the person
   * @param startWithdrawalsAge The age to start taking withdrawals
   * @param numberOfWithdrawals The number of withdrawals to make 
   *
   */
  public Account401K(final double balance, final double growthRate, 
                     final double annualContribution, final int currentAge, 
                     final int currentYear, final int retirementAge, final int deathAge,
                     final int startWithdrawalsAge, final int numberOfWithdrawals,
                     final Salary salary, final boolean onlyWhileSalary) {
    super();
    
    int year;
    
    this.balance = balance;
    this.growthRate = growthRate;
    this.currentAge = currentAge;
    this.currentYear = currentYear;
    this.deathAge = deathAge;
    this.startWithdrawalsAge = startWithdrawalsAge;
    this.numberOfWithdrawals = numberOfWithdrawals;
        
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
        
    // Add in the annual contributions
    for (int age = currentAge; age < deathAge; age++) {
      year = salary.convertAgeToYear(age);
      if (onlyWhileSalary && salary.getEndingValue(year) > 0.0) {
        recomputeGrowthWithDeposit(age, annualContribution);
      } else {
        if (!onlyWhileSalary) {
          recomputeGrowthWithDeposit(age, annualContribution);
        }
      }
    }
                
    // Compute the withdrawals
    if (numberOfWithdrawals > 0) {
      computePeriodicWithdrawals(currentAge, growthRate, startWithdrawalsAge, numberOfWithdrawals);
    }
    
    LOGGER.trace("Leaving the model Account401K Constructor().");
    LOGGER.trace("    ArrayList=" + values);
    for (final ResultsDataNode node: values) {
      LOGGER.trace("    ArrayList<>=" + node.getBeginningValue());      
    }
  }

  /**
   * Returns the taxable status of the account.
   *
   * @return true
   */
  public boolean isTaxable() {
    return true;
  }
    
  /**
   * Get the balance.
   * 
   * @return The balance
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Get the expected growthRate.
   * 
   * @return the growthRate
   */
  public double getGrowthRate() {
    return growthRate;
  }

  /**
   * Get the currentAge.
   * 
   * @return the currentAge
   */
  public int getCurrentAge() {
    return currentAge;
  }

  /**
   * Get the current Year.
   * 
   * @return the currentYear
   */
  public int getCurrentYear() {
    return currentYear;
  }

  /**
   * Get the death age of the user.
   * 
   * @return the deathAge
   */
  public int getDeathAge() {
    return deathAge;
  }
  
  /**
   * Get the starting age of the withdrawals.
   * 
   * @return the startWithdrawalsAge
   */
  public int getStartWithdrawalsAge() {
    return startWithdrawalsAge;
  }
  
  /**
   * Get the number of withdrawals.
   * 
   * @return the numberOfWithdrawals
   */
  public int getNumberOfWithdrawals() {
    return numberOfWithdrawals;
  }
}