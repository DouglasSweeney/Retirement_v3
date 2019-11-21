package application.model;

/**
 * Implementation of an 403B account.
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 5/14/2016
 *
 */
public class Account403B extends Account implements Accounts {
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
   * This variable keeps track of the current annual contributions.
   *
   * @var annualContributions          The expected yearly contributions to the account.
   */
  private final transient double annualContributions;

  /**
   * This variable keeps track of the current age of the user.
   *
   * @var currentAge          The current age of the person
   */
  private final transient int    currentAge;

  /**
   * This variable keeps track of the current year.
   *
   * @var currentYear         The current year of the person owning the account
   */
  private final transient int    currentYear;

  /**
   * This variable keeps track of the death age of the user.
   *
   * @var deathAge            The expected death age of the person
   */
  private final transient int    deathAge;

  /**
   * This variable keeps track of the starting withdrawls age of the 401K user.
   *
   * @var startWithdrawalsAge The age to start taking withdrawals
   */
  private final transient int    startWithdrawalsAge;

  /**
   * This variable keeps track of the number of withdrawls.
   *
   * @var numberOfWithdrawals The number of withdrawals to make 
   *
   */
  private final transient int    numberOfWithdrawals;
    
  /**
   * This method builds the initial data structure.
   *
   * @param balance             The current account balance.
   * @param growthRate          The expected yearly growth rate of the account.
   * @param annualContribution  The annual contribution to the account
   * @param currentAge          The current age of the person
   * @param currentYear         The current year of the person owning the account
   * @param retirementAge       The age that contributions will stop
   * @param deathAge            The expected death age of the person
   * @param startWithdrawalsAge The age to start taking withdrawals
   * @param numberOfWithdrawals The number of withdrawals to make 
   *
   */
  public Account403B(final double balance, final double growthRate, 
                      final double annualContribution, final int currentAge, 
                      final int currentYear, final int retirementAge, final int deathAge,
                      final int startWithdrawalsAge, final int numberOfWithdrawals,
                      final Salary salary, final boolean onlyWhileSalary) {
    super();
    
    int year;
    
    this.balance = balance;
    this.growthRate = growthRate;
    this.annualContributions = annualContribution;
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
   * @return the balance
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Get the expected growth rate.
   * 
   * @return the growthRate
   */
  public double getGrowthRate() {
    return growthRate;
  }

  /**
   * Get the expected annual contributions.
   * 
   * @return the annual contribution
   */
  public double getAnnualContributions() {
    return annualContributions;
  }

  /**
   * Get the current age.
   * 
   * @return the currentAge
   */
  public int getCurrentAge() {
    return currentAge;
  }

  /**
   * Get the current year.
   * 
   * @return the currentYear
   */
  public int getCurrentYear() {
    return currentYear;
  }

  /**
   * Get the death age.
   * 
   * @return the deathAge
   */
  public int getDeathAge() {
    return deathAge;
  }
  
  /**
   * Get the starting withdrawals age.
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
