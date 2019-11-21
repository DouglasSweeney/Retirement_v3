package application.main;

import java.util.ArrayList;
import java.util.List;

import application.view.inputs.Account;
import application.view.inputs.Account401K;
import application.view.inputs.Account403B;
import application.view.inputs.AccountCashBalance;
import application.view.inputs.AccountIra;
import application.view.inputs.AccountRoth;
import application.view.inputs.Brokerage;
import application.view.inputs.Deductions;
import application.view.inputs.Expenses;
import application.view.inputs.InputObject;
import application.view.inputs.Pension;
import application.view.inputs.Personal;
import application.view.inputs.Salary;
import application.view.inputs.Savings;
import application.view.inputs.SocialSecurity;
import application.view.inputs.Taxes;

public class InputsLab {
  private static InputsLab sInputLab;
  
  /**
   * Will create the user input object.
   * 
   * @var Account401K account401k
   */
  private static Account401K account401k;
  
  /**
   * Will create the user input object.
   * 
   * @var Account403B account403B
   */
  private static Account403B account403b;

  /**
   * Will create the user input object.
   * 
   * @var AccountCashBalance accountCashBalance
   */
  private static AccountCashBalance accountCashBalance;

  /**
   * Will create the user input object.
   * 
   * @var AccountRoth accountRoth
   */
  private static AccountRoth accountRoth;

  /**
   * Will create the user input object.
   * 
   * @var AccountIra accoutIra
   */
  private static AccountIra accountIra;

  /**
   * Will create the user input object.
   * 
   * @var Brokerage brokerage
   */
  private static Brokerage brokerage;

  /**
   * Will create the user input object.
   * 
   * @var Deductions deductions
   */
  private static Deductions deductions;

  /**
   * Will create the user input object.
   * 
   * @var Expenses expenses
   */
  private static Expenses expenses;

  /**
   * Will create the user input object.
   * 
   * @var Pension pension
   */
  private static Pension pension;

  /**
   * Will create the user input object.
   * 
   * @var Personal personal
   */
  private static Personal personal; 
 
  /**
   * Will create the user input object.
   * 
   * @var Salary salary
   */
  private static Salary salary;

  /**
   * Will create the user input object.
   * 
   * @var Savings
   */
  private static Savings savings;
  
  /**
   * Will create the user input object.
   * 
   * @var SocialSecurity socialSecurity
   */
  private static SocialSecurity socialSecurity;

  /**
   * Will create the user input object.
   * 
   * @var Taxes taxes
   */
  private static Taxes taxes;
   
  /**
   * A collection of all user input object (alphabetically).
   * 
   * @var List allInputs
   */
  private static List<Account401K> account401kInput = new ArrayList<>();
  private static List<Account> account403bInput = new ArrayList<>();
  private static List<InputObject> brokerageInput = new ArrayList<>();
  private static List<Account> cashBalanceInput = new ArrayList<>();
  private static List<InputObject> deductionsAndExpensesInputs = new ArrayList<>();
  private static List<Account> iraInput = new ArrayList<>();
  private static List<InputObject> pensionAndPersonalInputs = new ArrayList<>();
  private static List<Account> rothInput = new ArrayList<>();
  private static List<InputObject> restInputs = new ArrayList<>();
      
  public static InputsLab getInstance() {
    if (sInputLab == null) {
        sInputLab = new InputsLab();
    }
    
    return sInputLab;
  }

  private InputsLab() {
    final XmlReader xmlReader = new XmlReader();
    
    account401k = new Account401K(xmlReader);
    account401kInput.add(account401k);
    
    account403b = new Account403B(xmlReader);
    account403bInput.add(account403b);
    
    brokerage = new Brokerage(xmlReader);
    brokerageInput.add(brokerage);
    
    accountCashBalance = new AccountCashBalance(xmlReader);
    cashBalanceInput.add(accountCashBalance);
    
    deductions = new Deductions(xmlReader);
    deductionsAndExpensesInputs.add(deductions);
    expenses = new Expenses(xmlReader);
    deductionsAndExpensesInputs.add(expenses);
        
    accountIra = new AccountIra(xmlReader);
    iraInput.add(accountIra);
   
    pension = new Pension(xmlReader);
    pensionAndPersonalInputs.add(pension);
    personal = new Personal(xmlReader);
    pensionAndPersonalInputs.add(personal);
    
    accountRoth = new AccountRoth(xmlReader);
    rothInput.add(accountRoth);
    
    salary = new Salary(xmlReader);
    restInputs.add(salary);
    savings = new Savings(xmlReader);
    restInputs.add(savings);
    socialSecurity = new SocialSecurity(xmlReader);
    restInputs.add(socialSecurity);
    taxes = new Taxes(xmlReader);
    restInputs.add(taxes);
  }

  public Account401K getAccount401K() {
    return account401k;
  }

  public Account403B getAccount403B() {
    return account403b;
  }

  public AccountCashBalance getAccountCashBalance() {
    return accountCashBalance;
  }

  public AccountRoth getAccountRoth() {
    return accountRoth;
  }

  public AccountIra getAccountIra() {
    return accountIra;
  }

  public Brokerage getBrokerage() {
    return brokerage;
  }

  public Deductions getDeductions() {
    return deductions;
  }

  public Expenses getExpenses() {
    return expenses;
  }

  public Pension getPension() {
    return pension;
  }

  public Personal getPersonal() {
    return personal;
  }

  public Salary getSalary() {
    return salary;
  }

  public Savings getSavings() {
    return savings;
  }

  public SocialSecurity getSocialSecurity() {
    return socialSecurity;
  }

  public Taxes getTaxes() {
    return taxes;
  }
  
  public List<Account401K> getAccount401kInput() {
    return account401kInput;
  }
  
  public List<Account> getAccount403bInput() {
    return account403bInput;
  }
  
  public List<InputObject> getBrokerageInputs() {
    return brokerageInput;
  }
  
  public List<Account> getAccountCashBalanceInput() {
    return cashBalanceInput;
  }
  
  public List<InputObject> getDeductionsAndExpensesInputs() {
    return deductionsAndExpensesInputs;
  }
  
  public List<Account> getAccountIraInput() {
    return iraInput;
  }
  
  public List<InputObject> getPensionAndPersonalInputs() {
    return pensionAndPersonalInputs;
  }
  
  public List<Account> getAccountRothInput() {
    return rothInput;
  }
  
  public List<InputObject> getRestInputs() {
    return restInputs;
  }
  
  public void setAccount401K(final Account401K account401k) {
	InputsLab.account401k = account401k;
  }

  public void setAccount403B(final Account403B account403b) {
	InputsLab.account403b = account403b;
  }

  public void setAccountCashBalance(final AccountCashBalance accountCashBalance) {
	InputsLab.accountCashBalance = accountCashBalance;
  }

  public void setAccountRoth(final AccountRoth accountRoth) {
	InputsLab.accountRoth = accountRoth;
  }

  public void setAccountIra(final AccountIra accountIra) {
	InputsLab.accountIra = accountIra;
  }

  public void setBrokerage(final Brokerage brokerage) {
	InputsLab.brokerage = brokerage;
  }
  
  public void setDeductions(final Deductions deductions) {
	InputsLab.deductions = deductions;
  }

  public void setExpenses(final Expenses expenses) {
	InputsLab.expenses = expenses;
  }

  public void setPension(final Pension pension) {
	InputsLab.pension = pension;
  }

  public void setPersonal(final Personal personal) {
	InputsLab.personal = personal;
  }

  public void setSalary(final Salary salary) {
	InputsLab.salary = salary;
  }

  public void setSavings(final Savings savings) {
	InputsLab.savings = savings;
  }

  public void setSocialSecurity(final SocialSecurity socialSecurity) {
	InputsLab.socialSecurity = socialSecurity;
  }

  public void setTaxes(final Taxes taxes) {
	InputsLab.taxes = taxes;
  }
}
