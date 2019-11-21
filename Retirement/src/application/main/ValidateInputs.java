package application.main;

import javax.swing.JOptionPane;

import application.system.ApplicationLogger;
import application.view.inputs.Account401K;
import application.view.inputs.Account403B;
import application.view.inputs.AccountCashBalance;
import application.view.inputs.AccountIra;
import application.view.inputs.AccountRoth;
import application.view.inputs.Brokerage;
import application.view.inputs.ComboBoxItems;
import application.view.inputs.Deductions;
import application.view.inputs.Expenses;
import application.view.inputs.InputsTabbedPane;
import application.view.inputs.Pension;
import application.view.inputs.Personal;
import application.view.inputs.Salary;
import application.view.inputs.Savings;
import application.view.inputs.SocialSecurity;

public class ValidateInputs {
  private static final double ZERO_DOUBLE = 0.0;
  
  private static ComboBoxItems comboBoxItems = new ComboBoxItems();
  
  private static String[] items = comboBoxItems.getItems(ComboBoxItems.ITEMS.ITEMS_1_TO_95);
  public static final int        MIN_AGE = Integer.parseInt(items[0]);
  public static final int        MAX_AGE = Integer.parseInt(items[items.length - 1]);
  
  public static final int        MIN_SOCIAL_SECURITY_AGE = 62;
  public static final int        MAX_SOCIAL_SECURITY_AGE = 70;
  
  public static final double     MIN_INFLATION_RATE = -2.0;
  public static final double     MAX_INFLATION_RATE =  2.0;
  
  public static final double     MIN_GROWTH_RATE = 0;
  public static final double     MAX_GROWTH_RATE =  30;
      
  public static final double     MIN_MERIT_INCREASE = 0;
  public static final double     MAX_MERIT_INCREASE =  30;
      
  public static final double     MIN_ANNUAL_CONTRIBUTION = 0.0;
  public static final double     MAX_ANNUAL_CONTRIBUTION = 20_000.0;
  
  private static String[] items3 = comboBoxItems.getItems(ComboBoxItems.ITEMS.ITEMS_1_TO_50);
  public static final int        MIN_NUMBER_OF_WITHDRAWALS = Integer.parseInt(items3[0]);
  public static final int        MAX_NUMBER_OF_WITHDRAWALS = Integer.parseInt(items3[items3.length - 1]);

  public static final int        MIN_YEAR = 0;
    
  public static final int        MIN_MONTH = 0;
  public static final int        MAX_MONTH = 11;
    
  public static final int        MIN_DAY = 1;
  public static final int        MAX_DAY = 31;
    
  /**
   * The class LOGGER (log4j2).
   * 
   * @var ApplicationLogger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();
    
  private static final String   POPUP_DIALOG_TITLE = "Input Error";
  private transient InputsTabbedPane inputsTabbedPane;
  private static final String AND = " and ";
  private static final String MUST_BE_BETWEEN = ") must be between ";
  private static final String VALID_EQUALS = "            valid = ";  
  private static final String IS_NOT_POSITIVE = ") is not positive.";
  private static final String IS_NOT_VALID = ") is not valid.";
  private transient boolean  valid;
  /**
   * Constructor - remember the input JTabbedPane.
   * 
   * @param inputsTabbedPane The inputs 
   */
  public ValidateInputs(final InputsTabbedPane inputsTabbedPane) {
    LOGGER.trace("Calling the ValidateInputs constructor.");
        
    this.inputsTabbedPane = inputsTabbedPane;
      
    LOGGER.trace("Leaving the ValidateInputs constructor.");
  }
  
  /**
   * Constructor - remember the input JTabbedPane.
   * 
   */
  public ValidateInputs() {
    LOGGER.trace("Calling the ValidateInputs constructor.");
        
    LOGGER.trace("Leaving the ValidateInputs constructor.");
  }
  
  /**
   * Verify the input.
   * 
   * @return true or false
   */
  private boolean showMessageDialog(final String message) {
    JOptionPane.showMessageDialog(null, message, POPUP_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
   
    return true;
  }
  
  /**
   * Verify the input.
   * 
   * @return true or false
   */
  private boolean personalCurrentAge(final Personal personal) {
    LOGGER.trace("Calling the ValidateInputs personalCurrentAge().");
        
    final int currentAge = personal.getCurrentAge();
//    final int currentAge = inputsTabbedPane.personal.getCurrentAge();
    boolean notValid = false;
    
    if (currentAge < MIN_AGE || currentAge > MAX_AGE) {
      notValid = !showMessageDialog("Personal Current Age (" 
                                    + currentAge 
                                    + MUST_BE_BETWEEN
                                    + MIN_AGE + AND + MAX_AGE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs personalCurrentAge().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean personalDeathAge(final Personal personal) {
    LOGGER.trace("Calling the Validate personalDeathAge().");
        
    final int deathAge = personal.getDeathAge();
    boolean notValid = false;
    
    if (deathAge < MIN_AGE || deathAge > MAX_AGE) {
      notValid = showMessageDialog("Life Expectancy Age (" 
                                   + deathAge 
                                   + ")"
                                   + MUST_BE_BETWEEN
                                   + MIN_AGE + AND + MAX_AGE + ".");
    }
    
    LOGGER.trace("Leaving the ValidateInputs personalDeathAge().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean personalRetirementAge(final Personal personal) {
    LOGGER.trace("Calling the Validate personalRetirementAge().");
        
    final int retirementAge = personal.getRetirementAge();
    boolean notValid = false;
    
    if (retirementAge < MIN_AGE || retirementAge > MAX_AGE) {
      notValid = showMessageDialog("Personal Retirement Age (" 
                                       + retirementAge
                                       + ")"
                                       + MUST_BE_BETWEEN 
                                       + MIN_AGE + AND + MAX_AGE +  ".");
    }
    
    LOGGER.trace("Leaving the ValidateInputs personalRetirementAge().");
    LOGGER.trace(VALID_EQUALS + notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean ages(final Personal personal, final Account401K account401k) {
    LOGGER.trace("Calling the ValidateInputs ages().");
        
    final int currentAge = personal.getCurrentAge();
    final int deathAge = personal.getDeathAge();
    final int withdrawalsAge401k = account401k.getStartWithdrawalAge();
    final int numberOfWithdrawals401k = account401k.getNumberOfWithdrawals();
    
    boolean notValid = false;
    
    if (currentAge > deathAge) {
      notValid = !showMessageDialog("Current Age (" 
                                 + currentAge 
                                 + ") is greater than the Life Expectancy Age (" + deathAge + ")"); 
    }
    
    if (withdrawalsAge401k > deathAge) {
      notValid = !showMessageDialog("401K Withdrawals Age ("
                                 + withdrawalsAge401k 
                                 + ") is greater than the Life Expectancy Age (" + deathAge + ")"); 
    }
    
    if (currentAge + numberOfWithdrawals401k - 1 > deathAge) {
      notValid = !showMessageDialog("401K Number of withdrawals (" 
                                 + numberOfWithdrawals401k 
                                 + ") is greater than the Life Expectancy Age (" + deathAge + ")"); 
    }

        
    LOGGER.trace("Leaving the ValidateInputs ages().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean personalCurrentYear(final Personal personal) {    
    LOGGER.trace("Calling the ValidateInputs personalCurrentYear().");
        
    final int currentYear = personal.getCurrentYear();
    boolean notValid = false;
    
    if (currentYear < MIN_YEAR) {
      notValid = showMessageDialog("Current Year (" + currentYear + IS_NOT_POSITIVE); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs personalCurrentYear().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean personalCurrentMonth(final Personal personal) {   
    LOGGER.trace("Calling the ValidateInputs personalCurrentMonth().");
        
    final int currentMonth = personal.getCurrentMonth();
    boolean notValid = false;
    
    if (currentMonth < MIN_MONTH || currentMonth > MAX_MONTH) {
      notValid = !showMessageDialog("Current Month (" + currentMonth + IS_NOT_VALID); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs personalCurrentMonth().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean personalCurrentDay(final Personal personal) {   
    LOGGER.trace("Calling the ValidateInputs personalCurrentDay().");
        
    final int currentDay = personal.getCurrentDay();
    boolean notValid = false;
    
    if (currentDay < MIN_DAY || currentDay > MAX_DAY) {
      notValid = !showMessageDialog("Current Day (" + currentDay + IS_NOT_VALID); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs personalCurrentDay().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean personalBirthYear(final Personal personal) {    
    LOGGER.trace("Calling the ValidateInputs personalBirthYear().");
        
    final int currentYear = personal.getBirthYear();
    boolean notValid = false;
    
    if (currentYear < MIN_YEAR) {
      notValid = !showMessageDialog("Birth Year (" + currentYear + IS_NOT_POSITIVE); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs personalBirthYear().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean personalBirthMonth(final Personal personal) {   
    LOGGER.trace("Calling the ValidateInputs personalBirthMonth().");
        
    final int currentMonth = personal.getBirthMonth();
    boolean notValid = false;
    
    if (currentMonth < MIN_MONTH || currentMonth > MAX_MONTH) {
      notValid = !showMessageDialog("Birth Month (" + currentMonth + IS_NOT_VALID);
    }
    
    LOGGER.trace("Leaving the ValidateInputs personalBirthMonth().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean personalBirthDay(final Personal personal) {   
    LOGGER.trace("Calling the ValidateInputs personalBirthDay().");
        
    final int currentDay = personal.getBirthDay();
    boolean notValid = false;
    
    if (currentDay < MIN_DAY || currentDay > MAX_DAY) {
      notValid = !showMessageDialog("Birth Day (" + currentDay + IS_NOT_VALID);
    }
    
    LOGGER.trace("Leaving the ValidateInputs personalBirthDay().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean personalInflationRate(final Personal personal) {
    LOGGER.trace("Calling the ValidateInputs personalInflationRate().");
        
    final double inflationRate = personal.getInflationRate();
    boolean notValid = false;
    
    if (inflationRate < MIN_INFLATION_RATE || inflationRate > MAX_INFLATION_RATE) {
      notValid = !showMessageDialog("Inflation Rate (" + inflationRate 
                                    + MUST_BE_BETWEEN 
                                    + MIN_INFLATION_RATE + AND + MAX_INFLATION_RATE + ".");
    }
    
    LOGGER.trace("Leaving the ValidateInputs personalInflationRate().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean expenseExpenses(final Expenses expenses) {    
    LOGGER.trace("Calling the ValidateInputs expenseExpenses().");
        
    final double expense = expenses.getExpenses();
    boolean notValid = false;
    
    if (expense < ZERO_DOUBLE) {
      notValid = !showMessageDialog("Expenses (" + expense + ")" + IS_NOT_POSITIVE);
    }
    
    LOGGER.trace("Leaving the ValidateInputs expensesExpenses().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean brokerageBalance(final Brokerage brokerage) {   
    LOGGER.trace("Calling the ValidateInputs brokerageBalance().");
        
    final double balance = brokerage.getBalance();
    boolean notValid = false;
    
    if (balance < ZERO_DOUBLE) {
      notValid = !showMessageDialog("Brokerage Balance (" + balance + IS_NOT_POSITIVE); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs brokerageBalance().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean brokerageGrowthRate(final Brokerage brokerage) {    
    LOGGER.trace("Calling the ValidateInputs brokerageGrowthRate().");
        
    final double growthRate = brokerage.getGrowthRate();
    boolean notValid = false;
    
    if (growthRate < MIN_GROWTH_RATE || growthRate > MAX_GROWTH_RATE) {
      notValid = !showMessageDialog("Brokerage Growth Rate (" + growthRate + MUST_BE_BETWEEN 
                                    + MIN_GROWTH_RATE + AND + MAX_GROWTH_RATE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs brokerageGrowthRate().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean savingsBalance(final Savings savings) {   
    LOGGER.trace("Calling the ValidateInputs savingsBalance().");
        
    final double balance = savings.getBalance();
    boolean notValid = false;
    
    if (balance < ZERO_DOUBLE) {
      notValid = !showMessageDialog("Savings Balance (" + balance + IS_NOT_POSITIVE); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs savingsBalance().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean savingsGrowthRate(final Savings savings) {    
    LOGGER.trace("Calling the ValidateInputs savingsGrowthRate().");
        
    final double growthRate = savings.getGrowthRate();
    boolean notValid = false;
    
    if (growthRate < MIN_GROWTH_RATE || growthRate > MAX_GROWTH_RATE) {
      notValid = !showMessageDialog("Savings Growth Rate (" + growthRate + MUST_BE_BETWEEN 
                                    + MIN_GROWTH_RATE + AND + MAX_GROWTH_RATE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs savingsGrowthRate().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean salarySalary(final Salary salary) {   
    LOGGER.trace("Calling the ValidateInputs salarySalary().");
        
    final double annualSalary = salary.getSalary();
    boolean notValid = false;
    
    if (annualSalary < ZERO_DOUBLE) {
      notValid = !showMessageDialog("Salary (" + annualSalary + IS_NOT_POSITIVE); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs salarySalary().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean salaryMeritIncrease(final Salary salary) {    
    LOGGER.trace("Calling the ValidateInputs salaryMeritIncrease().");
        
    final double meritIncrease = salary.getMeritIncrease();
    boolean notValid = false;
    
    if (meritIncrease < MIN_MERIT_INCREASE || meritIncrease > MAX_MERIT_INCREASE) {
      notValid = !showMessageDialog("Merit Increase (" + meritIncrease 
                                    + MUST_BE_BETWEEN + MIN_MERIT_INCREASE 
                                    + AND + MAX_MERIT_INCREASE + "."); 
          
      notValid = false;
    }
    
    LOGGER.trace("Leaving the ValidateInputs salaryMeritIncrease().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean account401kBalance(final Account401K account401k) {   
    LOGGER.trace("Calling the ValidateInputs account401kBalance ().");
        
    final double balance = account401k.getBalance();
    boolean notValid = false;
    
    if (balance < ZERO_DOUBLE) {
      notValid = !showMessageDialog("401K Balance (" + balance + IS_NOT_POSITIVE); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs account401kBalance().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean account401kGrowthRate(final Account401K account401k) {    
    LOGGER.trace("Calling the ValidateInputs account401kGrowthRate ().");
        
    final double growthRate = account401k.getGrowthRate();
    boolean notValid = false;
    
    if (growthRate < MIN_GROWTH_RATE || growthRate > MAX_GROWTH_RATE) {
      notValid = !showMessageDialog("401K Growth Rate (" + growthRate 
                                 + MUST_BE_BETWEEN + MIN_GROWTH_RATE + AND + MAX_GROWTH_RATE + ".");
    }
    
    LOGGER.trace("Leaving the ValidateInputs account401kGrowthRate().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean account401kAnnualContribution(final Account401K account401k) {    
    LOGGER.trace("Calling the ValidateInputs account401kAnnualContribution ().");
        
    final double contribution = account401k.getAnnualContributions();
    boolean notValid = false;
    
    if (contribution < ZERO_DOUBLE) {
      notValid = !showMessageDialog("401K Annual Contribution (" + contribution + IS_NOT_POSITIVE);
    }
    
    LOGGER.trace("Leaving the ValidateInputs account401kAnnualContribution().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean account401kStartRetirementAge(final Account401K account401k) {
    LOGGER.trace("Calling the ValidateInputs account401kStartRetirementAge ().");
        
    final int startAge = account401k.getStartWithdrawalAge();
    boolean notValid = false;
    
    if (startAge < MIN_AGE || startAge > MAX_AGE) {
      notValid = !showMessageDialog("401K Start Withdrawals Age (" + startAge 
                                    + MUST_BE_BETWEEN + MIN_AGE + AND + MAX_AGE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs account401kStartRetirementAge().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean account401kNumberOfWithdrawals(final Account401K account401k) {
    LOGGER.trace("Calling the ValidateInputs account401kNumberOfWithdrawals().");
        
    final int numberOfWithdrawals = account401k.getNumberOfWithdrawals();
    boolean notValid = false;
    
    if (numberOfWithdrawals < MIN_NUMBER_OF_WITHDRAWALS 
        || numberOfWithdrawals > MAX_NUMBER_OF_WITHDRAWALS) {
      notValid = !showMessageDialog("401K Number Of Withdrawals (" 
                                    + numberOfWithdrawals 
                                    + MUST_BE_BETWEEN + MIN_NUMBER_OF_WITHDRAWALS 
                                    + AND + MAX_NUMBER_OF_WITHDRAWALS + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs account401kNumberOfWithdrawals().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean account403bBalance(final Account403B account403B) {   
    LOGGER.trace("Calling the ValidateInputs account403bBalance ().");
        
    final double balance = account403B.getBalance();
    boolean notValid = false;
    
    if (balance < ZERO_DOUBLE) {
      notValid = !showMessageDialog("403B Balance (" + balance + IS_NOT_POSITIVE); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs account403bBalance().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean account403bGrowthRate(final Account403B account403B) {    
    LOGGER.trace("Calling the ValidateInputs account403bGrowthRate ().");
        
    final double growthRate = account403B.getGrowthRate();
    boolean notValid = false;
    
    if (growthRate < MIN_GROWTH_RATE || growthRate > MAX_GROWTH_RATE) {
      notValid = !showMessageDialog("403B Growth Rate (" + growthRate 
                                    + MUST_BE_BETWEEN 
                                    + MIN_GROWTH_RATE + AND + MAX_GROWTH_RATE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs account403bGrowthRate().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean account403bAnnualContribution(final Account403B account403B) {    
    LOGGER.trace("Calling the ValidateInputs account403bAnnualContribution ().");
        
    final double contribution = account403B.getAnnualContributions();
    boolean notValid = false;
    
    if (contribution < ZERO_DOUBLE) {
      notValid = !showMessageDialog("403B Annual Contribution (" + contribution + IS_NOT_POSITIVE);
    }
    
    LOGGER.trace("Leaving the ValidateInputs account403bAnnualContribution().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean account403bStartRetirementAge(final Account403B account403B) {
    LOGGER.trace("Calling the ValidateInputs account403bStartRetirementAge ().");
        
    final int startAge = account403B.getStartWithdrawalAge();
    boolean notValid = false;
    
    if (startAge < MIN_AGE || startAge > MAX_AGE) {
      notValid = !showMessageDialog("403B Start Withdrawals Age (" + startAge 
                                    + MUST_BE_BETWEEN + MIN_AGE + AND + MAX_AGE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs account403bStartRetirementAge().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean account403bNumberOfWithdrawals(final Account403B account403B) {
    LOGGER.trace("Calling the ValidateInputs account403bNumberOfWithdrawals().");
        
    final int numberOfWithdrawals = account403B.getNumberOfWithdrawals();
    boolean notValid = false;
    
    if (numberOfWithdrawals < MIN_NUMBER_OF_WITHDRAWALS 
        || numberOfWithdrawals > MAX_NUMBER_OF_WITHDRAWALS) {
      notValid = !showMessageDialog("403B Number Of Withdrawals (" + numberOfWithdrawals 
                                    + MUST_BE_BETWEEN + MIN_NUMBER_OF_WITHDRAWALS
                                    + AND + MAX_NUMBER_OF_WITHDRAWALS + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs account403bNumberOfWithdrawals().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountCashBalanceBalance(final AccountCashBalance cashBalance) {    
    LOGGER.trace("Calling the ValidateInputs accountCashBalanceBalance ().");
        
    final double balance = cashBalance.getBalance();
    boolean notValid = false;
    
    if (balance < ZERO_DOUBLE) {
      notValid = !showMessageDialog("Cash Balance Balance (" + balance + IS_NOT_POSITIVE); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountCashBalanceBalance().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountCashBalanceGrowthRate(final AccountCashBalance cashBalance) {   
    LOGGER.trace("Calling the ValidateInputs accountCashBalanceGrowthRate ().");
        
    final double growthRate = cashBalance.getGrowthRate();
    boolean notValid = false;
    
    if (growthRate < MIN_GROWTH_RATE || growthRate > MAX_GROWTH_RATE) {
      notValid = !showMessageDialog("Cash Balance Growth Rate (" + growthRate 
                                     + MUST_BE_BETWEEN + MIN_GROWTH_RATE 
                                     + AND + MAX_GROWTH_RATE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountCashBalance GrowthRate().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountCashBalanceAnnualContribution(final AccountCashBalance cashBalance) {   
    LOGGER.trace("Calling the ValidateInputs accountCashBalance AnnualContribution ().");
        
    final double contribution = cashBalance.getAnnualContributions();
    boolean notValid = false;
    
    if (contribution < ZERO_DOUBLE) {
      notValid = !showMessageDialog("Cash Balance Annual Contribution (" + contribution 
                                    + IS_NOT_POSITIVE);
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountCashBalanceAnnualContribution().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountCashBalanceStartRetirementAge(final AccountCashBalance cashBalance) {
    LOGGER.trace("Calling the ValidateInputs accountCashBalanceStartRetirementAge ().");
        
    final int startAge = cashBalance.getStartWithdrawalAge();
    boolean notValid = false;
    
    if (startAge < MIN_AGE || startAge > MAX_AGE) {
      notValid = !showMessageDialog("Cash Balance Start Withdrawals Age (" + startAge 
                                    + MUST_BE_BETWEEN + MIN_AGE + AND + MAX_AGE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountCashBalanceStartRetirementAge().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountCashBalanceNumberOfWithdrawals(final AccountCashBalance cashBalance) {
    LOGGER.trace("Calling the ValidateInputs accountCashBalanceNumberOfWithdrawals().");
        
    final int numberOfWithdrawals = cashBalance.getNumberOfWithdrawals();
    boolean notValid = false;
    
    if (numberOfWithdrawals < MIN_NUMBER_OF_WITHDRAWALS 
        || numberOfWithdrawals > MAX_NUMBER_OF_WITHDRAWALS) {
      notValid = !showMessageDialog("Cash Balance Number Of Withdrawals ("   
                                    + numberOfWithdrawals + MUST_BE_BETWEEN 
                                    + MIN_NUMBER_OF_WITHDRAWALS + AND 
                                    + MAX_NUMBER_OF_WITHDRAWALS + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountCashBalanceNumberOfWithdrawals().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountIraBalance(final AccountIra ira) {    
    LOGGER.trace("Calling the ValidateInputs accountIraBalance ().");
        
    final double balance = ira.getBalance();
    boolean notValid = false;
    
    if (balance < ZERO_DOUBLE) {
      notValid = !showMessageDialog("IRA Balance (" + balance + IS_NOT_POSITIVE); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountIraBalance().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountIraGrowthRate(final AccountIra ira) {   
    LOGGER.trace("Calling the ValidateInputs accountIraGrowthRate ().");
        
    final double growthRate = ira.getGrowthRate();
    boolean notValid = false;
    
    if (growthRate < MIN_GROWTH_RATE || growthRate > MAX_GROWTH_RATE) {
      notValid = !showMessageDialog("IRA Growth Rate (" + growthRate 
                                    + MUST_BE_BETWEEN + MIN_GROWTH_RATE 
                                    + AND + MAX_GROWTH_RATE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountIra GrowthRate().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountIraAnnualContribution(final AccountIra ira) {   
    LOGGER.trace("Calling the ValidateInputs accountIraAnnualContribution ().");
        
    final double contribution = ira.getAnnualContributions();
    boolean notValid = false;
    
    if (contribution < ZERO_DOUBLE) {
      notValid = !showMessageDialog("IRA Annual Contribution (" + contribution + IS_NOT_POSITIVE);
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountIraAnnualContribution().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountIraStartRetirementAge(final AccountIra ira) {
    LOGGER.trace("Calling the ValidateInputs accountIraStartRetirementAge ().");
        
    final int startAge = ira.getStartWithdrawalAge();
    boolean notValid = false;
    
    if (startAge < MIN_AGE || startAge > MAX_AGE) {
      notValid = !showMessageDialog("IRA Start Withdrawals Age (" + startAge 
                                    + MUST_BE_BETWEEN + MIN_AGE + AND + MAX_AGE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountIraStartRetirementAge().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountIraNumberOfWithdrawals(final AccountIra ira) {
    LOGGER.trace("Calling the ValidateInputs accountIraNumberOfWithdrawals().");
        
    final int numberOfWithdrawals = ira.getNumberOfWithdrawals();
    boolean notValid = false;
    
    if (numberOfWithdrawals < MIN_NUMBER_OF_WITHDRAWALS 
        || numberOfWithdrawals > MAX_NUMBER_OF_WITHDRAWALS) {
      notValid = !showMessageDialog("IRA Number Of Withdrawals (" 
                                    + numberOfWithdrawals + MUST_BE_BETWEEN 
                                    + MIN_NUMBER_OF_WITHDRAWALS + AND 
                                    + MAX_NUMBER_OF_WITHDRAWALS + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountIraNumberOfWithdrawals().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountRothBalance(final AccountRoth roth) {   
    LOGGER.trace("Calling the ValidateInputs accountRothBalance ().");
        
    final double balance = roth.getBalance();
    boolean notValid = false;
    
    if (balance < ZERO_DOUBLE) {
      notValid = !showMessageDialog("Roth Balance (" + balance + IS_NOT_POSITIVE);
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountRothBalance().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountRothGrowthRate(final AccountRoth roth) {    
    LOGGER.trace("Calling the ValidateInputs accountRothGrowthRate ().");
        
    final double growthRate = roth.getGrowthRate();
    boolean notValid = false;
    
    if (growthRate < MIN_GROWTH_RATE || growthRate > MAX_GROWTH_RATE) {
      notValid = !showMessageDialog("Roth Growth Rate (" + growthRate 
                                    + MUST_BE_BETWEEN + MIN_GROWTH_RATE 
                                    + AND + MAX_GROWTH_RATE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountRothGrowthRate().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountRothAnnualContribution(final AccountRoth roth) {    
    LOGGER.trace("Calling the ValidateInputs accountRothAnnualContribution ().");
        
    final double contribution = roth.getAnnualContributions();
    boolean notValid = false;
    
    if (contribution < ZERO_DOUBLE) {
      notValid = !showMessageDialog("Roth Annual Contribution (" + contribution + IS_NOT_POSITIVE); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountRothAnnualContribution().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountRothStartWithdrawalsAge(final AccountRoth roth) {
    LOGGER.trace("Calling the ValidateInputs accountRothStartRetirementAge ().");
        
    final int startAge = roth.getStartWithdrawalAge();
    boolean notValid = false;
    
    if (startAge < MIN_AGE || startAge > MAX_AGE) {
      notValid = !showMessageDialog("Roth Start Withdrawals Age (" + startAge 
                                    + MUST_BE_BETWEEN + MIN_AGE + AND + MAX_AGE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountRothStartRetirementAge().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean accountRothNumberOfWithdrawals(final AccountRoth roth) {
    LOGGER.trace("Calling the ValidateInputs accountRothNumberOfWithdrawals().");
        
    final int numberOfWithdrawals = roth.getNumberOfWithdrawals();
    boolean notValid = false;
    
    if (numberOfWithdrawals < MIN_NUMBER_OF_WITHDRAWALS 
        || numberOfWithdrawals > MAX_NUMBER_OF_WITHDRAWALS) {
      notValid = !showMessageDialog("Roth Number Of Withdrawals (" + numberOfWithdrawals 
                                    + MUST_BE_BETWEEN + MIN_NUMBER_OF_WITHDRAWALS 
                                    + AND + MAX_NUMBER_OF_WITHDRAWALS + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs accountRothNumberOfWithdrawals().");
    LOGGER.trace(VALID_EQUALS + !notValid);
       
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean pensionMonthlyAmount(final Pension pension) {
    LOGGER.trace("Calling the ValidateInputs pensionStartAge().");
        
    final double monthlyAmount = pension.getMonthlyAmount();
    boolean notValid = false;
    
    if (monthlyAmount < ZERO_DOUBLE) {
      notValid = !showMessageDialog("Pension MonthlyAmount (" + monthlyAmount 
                                    + IS_NOT_POSITIVE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs pensionStartAge().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean pensionStartAge(final Pension pension) {
    LOGGER.trace("Calling the ValidateInputs pensionStartAge().");
        
    final int startAge = pension.getStartAge();
    boolean notValid = false;
    
    if (startAge < MIN_AGE || startAge > MAX_AGE) {
      notValid = !showMessageDialog("Pension Start Age (" + startAge + MUST_BE_BETWEEN 
                                    + MIN_AGE + AND + MAX_AGE + "."); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs pensionStartAge().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean socialSecurityMonthlyAmount(final SocialSecurity socialSecurity) {    
    LOGGER.trace("Calling the ValidateInputs socialSecurityMonthlyAmount().");
        
    final double monthlyAmount = socialSecurity.getMonthlyAmount();
    boolean notValid = false;
    
    if (monthlyAmount < ZERO_DOUBLE) {
      notValid = !showMessageDialog("Social Security Monthly Amount (" + monthlyAmount 
                                    + IS_NOT_POSITIVE); 
          
      notValid = false;
    }
    
    LOGGER.trace("Leaving the ValidateInputs socialSecurityMonthlyAmount().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }

  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean socialSecurityStartAge(final SocialSecurity socialSecurity) {
    LOGGER.trace("Calling the ValidateInputs socialSecurityStartAge().");
        
    final int startAge = socialSecurity.getStartAge();
    boolean notValid = false;
    
    if (startAge < MIN_SOCIAL_SECURITY_AGE || startAge > MAX_SOCIAL_SECURITY_AGE) {
      notValid = !showMessageDialog("Social Security Start Age (" + startAge 
                                    + MUST_BE_BETWEEN + MIN_SOCIAL_SECURITY_AGE 
                                    + AND + MAX_SOCIAL_SECURITY_AGE + ".");  
    }
    
    LOGGER.trace("Leaving the ValidateInputs socialSecurityStartAge().");
    LOGGER.trace(VALID_EQUALS + !notValid);
        
    return !notValid;
  }
  
  /**
   * Verify the input.
   * 
   * @return boolean
   */
  private boolean deductionsDeductions(final Deductions deductions) {   
    LOGGER.trace("Calling the ValidateInputs deductionsDeductions().");
        
    final double deduction = deductions.getDeductions();
    boolean notValid = false;
    
    if (deduction < ZERO_DOUBLE) {
      notValid = !showMessageDialog("Deductions (" + deduction + IS_NOT_POSITIVE); 
    }
    
    LOGGER.trace("Leaving the ValidateInputs deductionsDeductions().");
    LOGGER.trace(VALID_EQUALS + !notValid);
    
    return !notValid;
  }
  
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  private boolean  checkPersonalInputs(final Personal personal, final Account401K account401k) {
        
    final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);

    if (!validateInputs.personalCurrentAge(personal) 
        || !validateInputs.personalDeathAge(personal)
        || !validateInputs.personalRetirementAge(personal)
        || !validateInputs.ages(personal, account401k)
        || !validateInputs.personalCurrentYear(personal)  
        || !validateInputs.personalCurrentMonth(personal)
        || !validateInputs.personalCurrentDay(personal)
        || !validateInputs.personalBirthYear(personal)
        || !validateInputs.personalBirthMonth(personal)
        || !validateInputs.personalBirthDay(personal)
        || !validateInputs.personalInflationRate(personal)) {
      valid = false;
    }
    
    return valid;
  }         
  
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  private boolean  checkExpensesInputs(final Expenses expenses) {
        
    final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);

    if (!validateInputs.expenseExpenses(expenses)) {
      valid = false;
    }
  
    return valid;
  }         
  
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  private boolean  checkBrokerageInputs(final Brokerage brokerage) {
        
    final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);

    if (!validateInputs.brokerageBalance(brokerage)
        || !validateInputs.brokerageGrowthRate(brokerage)) {
      valid = false;
    }
  
  
    return valid;
  }         
  
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  private boolean  checkSalaryInputs(final Salary salary) {
        
    final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);

    if (!validateInputs.salarySalary(salary)
        || !validateInputs.salaryMeritIncrease(salary)) {
      valid = false;
    }
   
    return valid;
  }         
  
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  private boolean check401KInputs(final Account401K account401k) {
        
    final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);

    if (!validateInputs.account401kBalance(account401k)
        || !validateInputs.account401kGrowthRate(account401k)
        || !validateInputs.account401kAnnualContribution(account401k)
        || !validateInputs.account401kStartRetirementAge(account401k)
        || !validateInputs.account401kNumberOfWithdrawals(account401k)) {
      valid = false;
    }
     
    return valid;
  }         
  
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  private boolean check403BInputs(final Account403B account403B) {
        
    final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);

    if (!validateInputs.account403bBalance(account403B)
        || !validateInputs.account403bGrowthRate(account403B)
        || !validateInputs.account403bAnnualContribution(account403B)
        || !validateInputs.account403bStartRetirementAge(account403B)
        || !validateInputs.account403bNumberOfWithdrawals(account403B)) {
      valid = false;
    }
     
    return valid;
  }         
  
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  private boolean checkCashBalanceInputs(final AccountCashBalance cashBalance) {
        
    final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);
 
    if (!validateInputs.accountCashBalanceBalance(cashBalance)
        || !validateInputs.accountCashBalanceGrowthRate(cashBalance)
        || !validateInputs.accountCashBalanceAnnualContribution(cashBalance)
        || !validateInputs.accountCashBalanceStartRetirementAge(cashBalance)
        || !validateInputs.accountCashBalanceNumberOfWithdrawals(cashBalance)) {
      valid = false;
    }
     
    return valid;
  }         
  
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  private boolean checkIraInputs(final AccountIra ira) {
        
    final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);

    if (!validateInputs.accountIraBalance(ira)
        || !validateInputs.accountIraGrowthRate(ira)
        || !validateInputs.accountIraAnnualContribution(ira)
        || !validateInputs.accountIraStartRetirementAge(ira)
        || !validateInputs.accountIraNumberOfWithdrawals(ira)) {
      valid = false;
    }
     
    return valid;
  }   
  
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  private boolean checkRothInputs(final AccountRoth roth) {
        
    final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);

    if (!validateInputs.accountRothBalance(roth)
        || !validateInputs.accountRothGrowthRate(roth)
        || !validateInputs.accountRothAnnualContribution(roth)
        || !validateInputs.accountRothStartWithdrawalsAge(roth)
        || !validateInputs.accountRothNumberOfWithdrawals(roth)) {
      valid = false;
    }
     
    return valid;
  }   
  
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  private boolean checkPensionInputs(final Pension pension) {
        
    final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);

    if (!validateInputs.pensionMonthlyAmount(pension)
        || !validateInputs.pensionStartAge(pension)) {
      valid = false;
    }

    return valid;
  }  
  
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  private boolean checkSocialSecurityInputs(final SocialSecurity socialSecurity) {
        
    final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);

    if (!validateInputs.socialSecurityMonthlyAmount(socialSecurity)
        || !validateInputs.socialSecurityStartAge(socialSecurity)) {
      valid = false;
    }
  

    return valid;
  }  
  
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  private boolean checkDeductionsInputs(final Deductions deductions) {
        
    final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);
        
    if (!validateInputs.deductionsDeductions(deductions)) {
      valid = false;
    }
  
    return valid;
  }  
  
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  private boolean  checkSavingsInputs(final Savings savings) {
        
    final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);

    if (!validateInputs.savingsBalance(savings)
        || !validateInputs.savingsGrowthRate(savings)) {
      valid = false;
    }
  
  
    return valid;
  }         
  /**
   * Verify that the inputs are valid.
   * 
   * @return true or false
   */
  public boolean checkInputs(final Personal personal, final Account401K account401k,
      final Expenses expenses, final Brokerage brokerage, final Salary salary,
      final Account403B account403B, final AccountCashBalance cashBalance,
      final AccountIra ira, final AccountRoth roth, final Pension pension,
      final SocialSecurity socialSecurity, final Deductions deductions,
      final Savings savings) {
    LOGGER.trace("Entering the Main checkInputs().");
 
    valid = true;
    
    if (!checkPersonalInputs(personal, account401k)) {
      valid = false;
    }
           
    if (!checkExpensesInputs(expenses)) {
      valid = false;
    }
           
    if (!checkBrokerageInputs(brokerage)) {
      valid = false;
    }
           
    if (!checkSalaryInputs(salary)) {
      valid = false;
    }
           
    if (!check401KInputs(account401k)) {
      valid = false;
    }
           
    if (!check403BInputs(account403B)) {
      valid = false;
    }
           
    if (!checkCashBalanceInputs(cashBalance)) {
      valid = false;
    }
           
    if (!checkIraInputs(ira)) {
      valid = false;
    }
           
    if (!checkRothInputs(roth)) {
      valid = false;
    }
           
    if (!checkPensionInputs(pension)) {
      valid = false;
    }
           
    if (!checkSocialSecurityInputs(socialSecurity)) {
      valid = false;
    }
           
    if (!checkDeductionsInputs(deductions)) {
      valid = false;
    }
           
    if (!checkSavingsInputs(savings)) {
      valid = false;
    }
    
    LOGGER.trace("Leaving the Main checkInputs().");
    LOGGER.trace("        valid: " + valid);
    
    return valid;
  }
  }
