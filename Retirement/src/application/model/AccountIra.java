package application.model;

/**
 * Implementation of an IRA account.
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 1/26/2016
 *
 */
public class AccountIra extends Account implements Accounts {
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
   * @var annualContributions The expected yearly contributions to the account.
   */
  private final transient double annualContribution;

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
   * @param balance             The current yearly expenses
   * @param growthRate          The expected yearly growth rate of the expenses
   * @param annualContribution  The contribution to this account
   * @param currentAge          The current age of the person
   * @param currentYear         The current year of the person
   * @param retirementAge       The retirement age of the person
   * @param deathAge            The expected death age of the person
   * @param startWithdrawalsAge The age to start periodic withdrawals from this account
   * @param numberOfWithdrawals The number of years to take the periodic withdrawals
   */
  public AccountIra(final double balance, final double growthRate, 
                    final double annualContribution, final int currentAge, 
                    final int currentYear, final int retirementAge, 
                    final int deathAge, final int startWithdrawalsAge, 
                    final int numberOfWithdrawals, final Salary salary, 
                    final boolean onlyWhileSalary) {
    super();
    
    int year;
    
    this.balance = balance;
    this.growthRate = growthRate;
    this.annualContribution = annualContribution;
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
   * Get the taxability of this account.
   *
   * @return boolean
   */
  public boolean isTaxable() {
    return true;
  }
    
  /**
   * Get the balance.
   * 
   * @return double
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Get the growth Rate.
   * 
   * @return double
   */
  public double getGrowthRate() {
    return growthRate;
  }

  /**
   * Get the expected annual contributions.
   * 
   * @return the annual contribution
   */
  public double getAnnualContribution() {
    return annualContribution;
  }


  /**
   * Get the current age.
   * 
   * @return int
   */
  public int getCurrentAge() {
    return currentAge;
  }

  /**
   * Get the current year. 
   * 
   * @return int
   */
  public int getCurrentYear() {
    return currentYear;
  }

  /**
   * Get the death age.
   * 
   * @return int
   */
  public int getDeathAge() {
    return deathAge;
  }
  
  /**
   * Get the starting age for withdrawal.
   * 
   * @return int
   */
  public int getStartWithdrawalsAge() {
    return startWithdrawalsAge;
  }
  
  /**
   * Get the number of withdrawals.
   * 
   * @return int
   */
  public int getNumberOfWithdrawals() {
    return numberOfWithdrawals;
  }
  
  /*  
    public static void main(String[] args) {

    AccountIra accountIra = new AccountIra(354000.0, 0.04, 0.0, 57, 2016, 62, 95, 59, 5);
    ResultsDataNode node;

      for (int i=0; i<accountIra.getList().size(); i++) {
        node = accountIra.getList().get(i);
        
        System.out.print(node.getAge() + " ");
        System.out.print(node.getBeginningValue() + " ");
        System.out.print(node.getWithdrawal() + " ");
        System.out.println(node.getEndingValue());
      }
    }
  */
}
