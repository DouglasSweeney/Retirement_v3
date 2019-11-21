package application.model;

import java.util.ArrayList;
import java.util.List;

import application.system.ApplicationLogger;
import application.system.ResultsDataNode;

/**
 * Helper routines for the accounts.
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 1/12/2016
 *
 */
public class Account {
  
  /**
   * The constant zero.
   * 
   * @var int ZERO_INTEGER
   */
  private static final int ZERO_INTEGER = 0;

  /**
   * The constant zero.
   * 
   * @var double ZERO_DOUBLE
   */
  private static final double ZERO_DOUBLE = 0.0;
  
  /**
   * Write out trace execution methods via this variable.
   * 
   * @var ApplicationLogger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();
    
  /**
   * The initial (starting) balance.
   * 
   * @var double balance 
   */
  private transient double balance;
  
  /**
   * The expected yearly growth rate. (i.e. 0.06 or 6%) 
   * 
   * @var double growthRate
   */
  private transient double growthRate;
    
  /**
   * The current age of a person. 
   * 
   * @var int currentAge
   */
  private transient int currentAge;
  
  /**
   * The current year.
   * 
   * @var int currentYear.
   */
  private transient int currentYear;
  
  /**
   * The death age a person. 
   * 
   * @var int currentYear
   */
  private transient int deathAge;
  
  /**
   * The data structure of WorksheetDataStructure items.
   * 
   * @var List values
   */
  protected transient List<ResultsDataNode> values = new ArrayList<>();
  
  /**
   * This method builds the initial data structure.
   * @param balance    The current account balance
   * @param growthRate   The expected yearly growth rate of the account
   * @param currentAge   The current age of the person
   * @param currentYear  The current year of the person owning the account
   * @param deathAge   The expected death age of the person
   */
  public void initialize(final double balance, final double growthRate, 
                         final int currentAge, final int currentYear, final int deathAge) {
    this.balance = balance;
    this.growthRate = growthRate;
    this.currentAge = currentAge;
    this.currentYear = currentYear;
    this.deathAge = deathAge;
    
    clearListOfValues();
    computeGrowth(currentYear, currentAge, deathAge - currentAge);
  }
 
  /**
   * Clears the data structure.
   */
  public void clearListOfValues() {
    values.clear();
  }
  
  /**
   * Get the smallest (first) year of the data structure.
   *
   * @var    returnValue The return value
   * 
   * @return returnValue
   */
  public int getSmallestYear() {
    int returnValue = ZERO_INTEGER;
    if (!values.isEmpty()) {
      returnValue = ((ResultsDataNode) values.get(0)).getYear();
    }
   
    return returnValue;
  }
  
  /**
   * Get the largest (last) year of the data structure.
   *  
   * @var    returnValue The return value
   * 
   * @return The year
   */
  public int getLargestYear() {
    int returnValue = ZERO_INTEGER;
    if (!values.isEmpty()) {
      returnValue = ((ResultsDataNode) values.get(values.size() - 1)).getYear();
    }
     
    return returnValue;
  }
  
  /**
   * Get the year that corresponds to the age (from the data structure).
   *
   * @param targetAge The age to convert
   *
   * @return The corresponding year
   */
  public int convertAgeToYear(final int targetAge) {
    int newYear = 0;
    boolean found = false;
    ResultsDataNode node;
    
    for (int i = 0; i < values.size() && !found; i++) {
      node = (ResultsDataNode) values.get(i);      
      if (node.getAge() == targetAge) {
        found = true;
        newYear = node.getYear();
      }
    }
    
    return newYear;
  }
  
  /**
   * Get the age that corresponds to the year (from the data structure).
   *
   * @param currentYear The year to convert
   *
   * @return The corresponding age
   */
  public int convertYearToAge(final int currentYear) {
    int newAge = 0;
    boolean found = false;
    ResultsDataNode node;
    
    for (int i = 0; i < values.size() && !found; i++) {
      node = (ResultsDataNode)(values.get(i));
      if (node.getYear() == currentYear) {
        found = true;
        newAge = node.getAge();
      }
    }
    
    return newAge;
  }
  
  /**
   * Build the data structure.
   *
   * @param currentYear The simulation year
   * @param currentAge  The age at each year
   * @param numberOfYears The number of years in the data structure
   *
   */
  public void computeGrowth(final int currentYear, final int currentAge, final int numberOfYears) {
    double beginningValue = balance;
    double value = balance;
    int year = currentYear;
    int age = currentAge;
    
    for (int i = 0; i < numberOfYears; i++) {
      value += value * growthRate; // value at the end of the year
       
      final ResultsDataNode node = new ResultsDataNode();
      
      node.aSet2(year, age, beginningValue, value);
      values.add(node);
      
      year++;
      age++;
      beginningValue = value; // Set for the following year
    }
  }
   
  /**
   * Recalculates the values in the data structure based on the deposit.
   * Assumes that the deposit is in the middle of the year.
   *
   * @param currentAge Value to compute from 
   * @param deposit  Dollar amount
   *
   */
  public void recomputeGrowthWithDeposit(final int currentAge, final double deposit) {
    int age = currentAge;
    double value = 0.0;
    double beginningValue = 0.0;
    ResultsDataNode node;
    double deposits = deposit;
    
    node = getNodeBasedOnAge(currentAge);
    if (node != null) {
      beginningValue = node.getBeginningValue();
      value = beginningValue;
      while (node != null) {
        node.addDeposit(deposits);      
        deposits += deposits * (growthRate); // assumes deposit at beginning of year
        value += value * growthRate + deposits; 
        deposits = 0.0; // set for following nodes/years
       
        node.setBeginningValue(beginningValue);
        beginningValue = value; // set for following nodes/years 
        node.setEndingValue(value);
       
        age++;
        node = getNodeBasedOnAge(age);
      }
    }
  }
  
  /**
   * Recalculates the values in the data structure based on the withdrawal.
   * Assumes that the withdrawal is at the beginning of the year.
   *
   * @param currentAge Value to compute from
   * @param withdrawal Dollar amount
   *
   */
  public void recomputeGrowthWithWithdrawal(final int currentAge, final double withdrawal) {
    int age = currentAge;
    double value;
    double beginningValue;
    double withdrawals = withdrawal;
    
    ResultsDataNode node = getNodeBasedOnAge(currentAge);
    if (node != null ) {
      beginningValue = node.getBeginningValue();
      value = beginningValue - node.getWithdrawal();
      while (node != null) {
        node.addWithdrawal(withdrawals); 
      
        value += node.getDeposit();
        value -= withdrawals;
        withdrawals = 0.0; // Reset for following nodes
        if (value > ZERO_DOUBLE) { 
          value += value * growthRate;
        } else { 
          value = 0.0; 
        }
      
        node.setBeginningValue(beginningValue);
        beginningValue = value;
        node.setEndingValue(value);
      
        age++; 
        node = getNodeBasedOnAge(age);
        if (node != null && value <= 0.0) {
          node.setBeginningValue(0.0); 
          beginningValue = 0.0;
        }
      }
    }
  }
  
  /**
   * Sets the a value in each node of the data structure to zero.
   *
   * @param currentAge Set the nodes starting at age
   *
   */
  public void zeroBeginningValues(final int currentAge) {
    int age = currentAge;

    ResultsDataNode node = getNodeBasedOnAge(age);
    while (node != null) {
      node.setBeginningValue(0.0);
      
      age++;
      node = getNodeBasedOnAge(age);
    }
  }
    
  /**
   * Sets the a value in each node of the data structure to zero.
   *
   * @param currentAge Set the nodes starting at age
   *
   */
  public void zeroEndingValues(final int currentAge) {
    int age = currentAge;
    ResultsDataNode node = getNodeBasedOnAge(age);

    while (node != null) {
      node.setEndingValue(0.0);
      
      age++;
      node = getNodeBasedOnAge(age);
    }
  }
  
  /**
   * Sets the a value in each node of the data structure to zero.
   *
   * @param currentAge Set the nodes starting at age
   *
   */
  public void setEndingValue(final int age, final double value) {
    final ResultsDataNode node = getNodeBasedOnAge(age);

    if (node != null) {
      node.setEndingValue(value);
    }
  }
  
  /**
   * Sets the a value in each node of the data structure to zero.
   *
   * @param currentAge Set the nodes starting at age
   *
   */
  public void computeEndingValue(final int age, final double value, final double inflation) {
    final ResultsDataNode node = getNodeBasedOnAge(age);

    if (node != null) {
      node.setEndingValue(value + (value * inflation));
    }
  }
  
  /**
   * Gets the a value in each node of the data structure to zero.
   *
   * @param age Set the nodes value at age
   *
   */
  public double getEndingValueBasedOnAge(final int age) {
    final ResultsDataNode node = getNodeBasedOnAge(age);
    double value = 0.0;
    
    if (node != null) {
      value = node.getEndingValue();
    }
    
    return value;
  }
    
  /**
   * Gets a value in each node of the data structure based on the year.
   *
   * @param  year The location in the data structure
   *
   * @return The value at the beginning of the year
   */
  public double getBeginningValue(final int year) {
    boolean found = false;
    ResultsDataNode node = null;
    double returnValue;
    
    for (int i = 0; i < values.size() && !found; i++) {
      node = (ResultsDataNode)(values.get(i));
      if (node.getYear() == year) { 
        found = true;
      }
    }
      
    if (found) { 
      returnValue =  node.getBeginningValue(); 
    }
    else { 
      returnValue = 0.0;
    }
    
    return returnValue;
  }
     
  /**
   * Gets a value in each node of the data structure based on the year.
   *
   * @param  year The location in ther data structure
   *
   * @return float
   */
  public double getDeposits(final int year) {
    boolean found = false;
    ResultsDataNode node = null;
    double returnValue;
    
    for (int i = 0; i < values.size() && !found; i++) {
      node = (ResultsDataNode)values.get(i);
      if (node.getYear() == year) { 
        found = true; 
      }
    }
    
    if (found) { 
      returnValue = node.getDeposit();
    }
    else { 
      returnValue =  0.0; 
    }
    
    return returnValue;
  }
     
  /**
   * Gets a value in each node of the data structure based on the year.
   *
   * @param  year The location in the data structure
   *
   * @return The dollar amount
   */
  public double getWithdrawals(final int year) {
    boolean found = false;
    ResultsDataNode node = null;
    double returnValue;
    
    for (int i = 0; i < values.size() && !found; i++) {
      node = (ResultsDataNode)values.get(i);
      if (node.getYear() == year) { 
        found = true;
      }
    }
    
    if (found) { 
      returnValue = node.getWithdrawal();
    } else { 
      returnValue = 0.0;
    }
    
    return returnValue;
  }
     
  /**
   * Gets a value in each node of the data structure based on the year.
   *
   * @param  year The location in the data structure
   *
   * @return float
   */
  public double getEndingValue(final int year) {
    boolean found = false;
    ResultsDataNode node = null;
    double returnValue;
    
    for (int i = 0; i < values.size() && !found; i++) {
      node = (ResultsDataNode)values.get(i);
      if (node.getYear() == year) { 
        found = true; 
      }
    }
    
    if (found) { 
      returnValue = node.getEndingValue(); 
    } else { 
      returnValue = 0.0;
    }
    
    return returnValue;
  }
   
  /**
   * Sets a value in each node of the data structure based on the year.
   *
   * @param  age The location in the data structure
   *
   */
  public void setZeroEndingValue(final int age) {
    final ResultsDataNode node = getNodeBasedOnAge(age);
    
    if (node != null) { 
      node.setEndingValue(0); 
    }
  }
  
  /**
   * Sets a value in each node of the data structure based on the year.
   *
   * @param  age The location in the data structure
   *
   */
  public void setZeroBeginningValue(final int age) {
    final ResultsDataNode node = getNodeBasedOnAge(age);
    
    if (node != null) { 
      node.setBeginningValue(0); 
    }
  }
     
  /**
   * Gets a node of the data structure based on the age.
   *
   * @param  age The location in the data structure
   *
   * @return WorkSheetDataStructure
   */
  public ResultsDataNode getNodeBasedOnAge(final int age) {
    ResultsDataNode returnValue = null;
    
    for (final ResultsDataNode node : values) {
      if (node.getAge() == age) { 
        returnValue = node; 
      }
    }
    
    return returnValue;
  }
  
  /**
   * Gets a node of the data structure based on the year.
   *
   * @param  year The location in the data structure
   *
   * @return The node
   */
  public ResultsDataNode getNodeBasedOnYear(final int year) {
    ResultsDataNode returnValue = null;
    
    for (final ResultsDataNode node : values) {
      if (node.getYear() == year) {
        returnValue =  node;
      }
    }
    
    return returnValue;
  }
  
  /**
   * Sets all the nodes in the data structure to zero.
   *
   */
  public void zeroAllEndingValues() {
    for (final ResultsDataNode node : values) {
      node.setEndingValue(0.0);
    }
  }
  
  /**
   * Withdraw a certain amount. 
   *
   * @param currentAge The age of the person at the withdrawal year
   * @param amount     The requested amount of the withdrawal 
   *
   * @return The amount actually withdrawn
   */
  public double withdraw(final int currentAge, final double amount) {
    double withdrawal;
    final int year = convertAgeToYear(currentAge);
    
    final double accountValue = getEndingValue(year);
    
    if (accountValue >= amount) {
      withdrawal = amount;
    } else {
      withdrawal = accountValue;
    }
    
    recomputeGrowthWithWithdrawal(currentAge, withdrawal);
    
    return withdrawal;    
  }
  
  /**
   * Deposit an amount and recompute the balance.
   *   
   * @param currentAge The age of the person at the deposit year
   * @param deposit    The amount of the deposit 
   *
   */
  public void deposit(final int currentAge, final double deposit) {    
    recomputeGrowthWithDeposit(currentAge, deposit);
  }
  
  /**
   * Set withdrawals based on age and numberOfwithdrawals.
   *
   * @param currentAge       The age of the person at the start of the simulation
   * @param withdrawalNumber The current withdrawal number
   * @param numberOfWiths    The number of withdrawals specified
   *
   */
  public void computePeriodicWithdrawalsRegardlessOfAge(final int currentAge,
                                                        final int withdrawalNumber, 
                                                        final int numberOfWiths) {
    int age = currentAge;
    int number = withdrawalNumber;
    final int numberOfWith = numberOfWiths;
    int yearsRemaining; 
    double withdrawal;
    ResultsDataNode node = null;
    
    for (; number <= numberOfWith; number++) {
      yearsRemaining = numberOfWith - number;
      node = getNodeBasedOnAge(age);
      if (node != null) {
        withdrawal = node.getBeginningValue() / (yearsRemaining + 1); 
        withdraw(age, withdrawal);
      }
      age++;
    }
  }
  
  /**
   * Set withdrawals based on age and numberOfwithdrawals.
   *
   * @param currentAge      The age of the person at the start of the simulation
   * @param growthRate      The of the account
   * @param startWithdrawalsAge The age to start the withdrawals
   * @param numberOfWithdrawals The number of years
   * 
   */
  public void computePeriodicWithdrawals(int currentAge, final double growthRate, 
                                         int startWithdrawalsAge, final int numberOfWithdrawals) {
    int withdrawalNumber = 0;

    if (numberOfWithdrawals > 0) {
      if (currentAge == startWithdrawalsAge) {
        withdrawalNumber = 1;
        computePeriodicWithdrawalsRegardlessOfAge(currentAge, 
                                                  withdrawalNumber, 
                                                  numberOfWithdrawals);
      } else if (currentAge < startWithdrawalsAge) { 
        while (currentAge < startWithdrawalsAge) {
          currentAge++;
        }

        withdrawalNumber = 1;
        computePeriodicWithdrawalsRegardlessOfAge(currentAge, 
                                                    withdrawalNumber, 
                                                    numberOfWithdrawals);
      } else if (currentAge > startWithdrawalsAge) {
        withdrawalNumber = 1;
        while (currentAge > startWithdrawalsAge) {
          withdrawalNumber++;
          startWithdrawalsAge++;
        }
        computePeriodicWithdrawalsRegardlessOfAge(currentAge, 
                                                  withdrawalNumber, 
                                                  numberOfWithdrawals);
      }
    }
  }
  
  /**
   * Gets the initial value.
   *
   * @return The balance
   */
  public double getBalance() {
    return balance;
  }
  
  /**
   * Gets the initial value.
   *
   * @return The growth rate
   */
  public double getGrowthRate() {
    return growthRate;
  }

  /**
   * Gets the initial value.
   *
   * @return The age
   */
  public int getCurrentAge() {
    return currentAge;
  }

  /**
   * Gets the initial value.
   *
   * @return The year
   */
  public int getCurrentYear() {
    return currentYear;
  }

  /**
   * Gets the initial value.
   *
   * @return The age 
   */
  public int getDeathAge() {
    return deathAge;
  }

  /**
   * Get the data array.
   * 
   * @return values
   */
  public List<ResultsDataNode> getListOfValues() {
    LOGGER.trace("Entering the Account getListOfValues().");
    LOGGER.trace("    ArrayList = " + values);
    
    for (final ResultsDataNode node: values) {
      LOGGER.trace("    ArrayList{} = " + node.getBeginningValue());      
    }
    LOGGER.trace("Leaving the Account getListOfValues().");
    return (ArrayList<ResultsDataNode>) values;
  }
 
  /**
   * Gets the number of the nodes in the list.
   *
   * @return The size of the data structure 
   */
  public int getSize() {
    return values.size();    
  }
  
  /**
   * Gets the data list.
   *
   * @return The list of values
   */
  public List<ResultsDataNode> getList() {
    return (ArrayList<ResultsDataNode>) values;    
  }  
}