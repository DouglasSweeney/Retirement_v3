package application.main;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.JFreeChart;

import application.view.outputs.Account401K;
import application.view.outputs.Account403B;
import application.view.outputs.AccountCashBalance;
import application.view.outputs.AccountIra;
import application.view.outputs.AccountRoth;
import application.view.outputs.Brokerage;
import application.view.outputs.Deductions;
import application.view.outputs.Expenses;
import application.view.outputs.OutputObject;
import application.view.outputs.Pension;
import application.view.outputs.Salary;
import application.view.outputs.Savings;
import application.view.outputs.SocialSecurity;
import application.view.outputs.Taxes;

public class OutputsLab {  
  /**
   * Will create the user output object.
   * 
   * @var Account401K account401k
   */
  private static Account401K account401k = new Account401K();
  
  /**
   * Will create the user output object.
   * 
   * @var Account403B account403B
   */
  private static Account403B account403b = new Account403B();

  /**
   * Will create the user output object.
   * 
   * @var AccountCashBalance accountCashBalance
   */
  private static AccountCashBalance accountCashBalance = new AccountCashBalance();

  /**
   * Will create the user output object.
   * 
   * @var AccountRoth accountRoth
   */
  private static AccountRoth accountRoth = new AccountRoth();

  /**
   * Will create the user output object.
   * 
   * @var AccountIra accoutIra
   */
  private static AccountIra accountIra = new AccountIra();

  /**
   * Will create the user output object.
   * 
   * @var Brokerage brokerage
   */
  private static Brokerage brokerage = new Brokerage();

  /**
   * Will create the user output object.
   * 
   * @var Deductions deductions
   */
  private static Deductions deductions = new Deductions();

  /**
   * Will create the user output object.
   * 
   * @var Expenses expenses
   */
  private static Expenses expenses = new Expenses();

  /**
   * Will create the user output object.
   * 
   * @var Pension pension
   */
  private static Pension pension = new Pension();
 
  /**
   * Will create the user output object.
   * 
   * @var Salary salary
   */
  private static Salary salary = new Salary();

  /**
   * Will create the user output object.
   * 
   * @var Savings
   */
  private static Savings savings = new Savings();
  
  /**
   * Will create the user output object.
   * 
   * @var SocialSecurity socialSecurity
   */
  private static SocialSecurity socialSecurity = new SocialSecurity();

  /**
   * Will create the user output object.
   * 
   * @var Taxes taxes
   */
  private static Taxes taxes = new Taxes();
   
  private static JFreeChart incomeChart;
  private static JFreeChart savingChart;
  
  /**
   * A collection of 401K->Expenses user output objects (alphabetically).
   * 
   * @var List leftOutputs
   */
  private final List<OutputObject> leftOutputs = new ArrayList<>();
      
  /**
   * A collection of Ira->Savings user output objects (alphabetically).
   * 
   * @var List middleOutputs
   */
  private final List<OutputObject> middleOutputs = new ArrayList<>();
      
  /**
   * A collection of Social Security->Taxes user output objects (alphabetically).
   * 
   * @var List rightOutputs
   */
  private final List<OutputObject> rightOutputs = new ArrayList<>();
      
  /**
   * Blank construcor.
   */
  public OutputsLab() {
    super();
    
    leftOutputs.add(account401k);
    
    leftOutputs.add(account403b);
    
    leftOutputs.add(brokerage);
    
    leftOutputs.add(accountCashBalance);
    
    leftOutputs.add(deductions);
    
    leftOutputs.add(expenses);
        
    middleOutputs.add(accountIra);
   
    middleOutputs.add(pension);
    
    middleOutputs.add(accountRoth);
    
    middleOutputs.add(salary);
    
    middleOutputs.add(savings);
    
    rightOutputs.add(socialSecurity);
    
    rightOutputs.add(taxes);

  }
  


  public static Account401K getAccount401K() {
    return account401k;
  }

  
  public static void setAccount401K(final Account401K results) {
    account401k = results;
  }

  public static Account403B getAccount403B() {
    return account403b;
  }

  public static void setAccount403B(final Account403B results) {
    account403b = results;
  }

  public static AccountCashBalance getAccountCashBalance() {
    return accountCashBalance;
  }

  public static void setAccountCashBalance(final AccountCashBalance results) {
    accountCashBalance = results;
  }

  public static AccountRoth getAccountRoth() {
    return accountRoth;
  }

  public static void setAccountRoth(final AccountRoth results) {
    accountRoth = results;
  }

  public static AccountIra getAccountIra() {
    return accountIra;
  }
  
  public static void setAccountIra(final AccountIra results) {
    accountIra = results;
  }

  public static Brokerage getBrokerage() {
    return brokerage;
  }

  public static void setBrokerage(final Brokerage results) {
    brokerage = results;
  }

  public static Deductions getDeductions() {
    return deductions;
  }

  public static void setDeductions(final Deductions results) {
    deductions = results;
  }

  public static Expenses getExpenses() {
    return expenses;
  }

  public static void setExpenses(final Expenses resultExpenses) {
    expenses = resultExpenses;
  }

  public static Pension getPension() {
    return pension;
  }

  public static void setPension(final Pension results) {
    pension = results;
  }

  public static Salary getSalary() {
    return salary;
  }

  public static void setSalary(final Salary results) {
    salary = results;
  }

  public static Savings getSavings() {
    return savings;
  }

  public static void setSavings(final Savings results) {
    savings = results;
  }

  public static SocialSecurity getSocialSecurity() {
    return socialSecurity;
  }

  public static void setSocialSecurity(final SocialSecurity results) {
    socialSecurity = results;
  }

  public static Taxes getTaxes() {
    return taxes;
  }
  
  public static void setTaxes(final Taxes results) {
    taxes = results;
  }

  public List<OutputObject> getLeftOutputs() {
    return leftOutputs;
  }
  
  public List<OutputObject> getMiddleOutputs() {
    return middleOutputs;
  }
  
  public List<OutputObject> getRightOutputs() {
    return rightOutputs;
  }
  
  public static void setIncomeChart(final JFreeChart chart) {
    incomeChart = chart;
  }
  
  public static void setSavingChart(final JFreeChart chart) {
    savingChart = chart;
  }
  
  public static JFreeChart getIncomeChart() {
    return incomeChart;
  }
  
  public static JFreeChart getSavingChart() {
    return savingChart;
  }
}
