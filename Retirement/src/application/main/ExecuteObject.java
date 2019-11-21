package application.main;

import application.model.Account;
import application.model.Account401K;
import application.model.Account403B;
import application.model.AccountCashBalance;
import application.model.AccountIra;
import application.model.AccountRoth;
import application.model.Brokerage;
import application.model.Deductions;
import application.model.Expenses;
import application.model.Pension;
import application.model.Salary;
import application.model.SavingsModel;
import application.model.SocialSecurity;
import application.model.Taxes;
import application.system.ApplicationLogger;
import application.system.ResultsDataNode;
import application.view.inputs.InputsTabbedPane;
import application.view.inputs.MyJDialog;
import application.view.outputs.ResultsTabbedPane;

/**
 * Process the inputs to get the results.
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 1/21/2016
 *
 */
public class ExecuteObject extends Account {

  /**
   * The class LOGGER (log4j2).
   * 
   * @var ApplicationLogger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();
  
  /**
   * Has the simulation been run?.
   * 
   * @var boolean ranSimulation
   */
  private transient boolean ranSimulation;
  
  /**
   * The personal inputs.
   * 
   * @var view.inputs.Personal INPUTS_PERSONAL
   */
  public static final application.view.inputs.Personal INPUTS_PERSONAL = InputsLab.getInstance().getPersonal();
    
  /**
   * The expenses model.
   * 
   * @var Expenses expenses
   */
  private final transient Expenses expenses;
  
  /**
   * The expenses input.
   * 
   * @var view.inputs.Expenses INPUTS_EXPENSES
   */
  private static final application.view.inputs.Expenses INPUTS_EXPENSES = InputsLab.getInstance().getExpenses();
  
  /**
   * The expenses output.
   * 
   * @var view.results.Expenses resultsExpenses
   */
  private static application.view.outputs.Expenses resultsExpenses = new application.view.outputs.Expenses();
  
  /**
   * The brokerage model.
   * 
   * @var Expenses expenses
   */
  private final transient Brokerage brokerage;
  
  /**
   * The brokerage inputs.
   * 
   * @var view.inputs.Brokerage INPUTS_BROKERAGE
   */
  private static final application.view.inputs.Brokerage INPUTS_BROKERAGE = 
      InputsLab.getInstance().getBrokerage();
  
  /**
   * The brokerage output.
   * 
   * @var view.results.Brokerage resultsBrokerage
   */
  private static application.view.outputs.Brokerage resultsBrokerage = new application.view.outputs.Brokerage();
  
  /**
   * The salary model.
   * 
   * @var Salary salary
   */
  private final transient Salary salary;
  
  /**
   * The salary inputs.
   * 
   * @var view.inputs.Salary INPUTS_SALARY
   */
  public static final application.view.inputs.Salary INPUTS_SALARY = InputsLab.getInstance().getSalary();
  
  /**
   * The salary output.
   * 
   * @var view.results.Salary resultsSalary
   */
  private static application.view.outputs.Salary resultsSalary = new application.view.outputs.Salary();
  
  /**
   * The 401K model.
   * 
   * @var Account401K account401K
   */
  private final transient  Account401K account401K;
  
  /**
   * The 401K inputs.
   * 
   * @var view.inputs.Account401K INPUTS_401K
   */
  public static final application.view.inputs.Account401K INPUTS_401K = 
      InputsLab.getInstance().getAccount401K(); 
  
  /**
   * The 401K output.
   * 
   * @var view.results.Account401K results401K
   */
  private static application.view.outputs.Account401K results401K = new application.view.outputs.Account401K();
  
  /**
   * The 403B model.
   * 
   * @var Account403B account403B
   */
  private final transient Account403B account403B;
  
  /**
   * The 4013B inputs.
   * 
   * @var view.inputs.Account403B INPUTS_403B
   */
  private static final application.view.inputs.Account403B INPUTS_403B = 
      InputsLab.getInstance().getAccount403B();
  
  /**
   * The 403B output.
   * 
   * @var view.results.Account403B results403B
   */
  private static application.view.outputs.Account403B results403B = new application.view.outputs.Account403B();
  
  /**
   * The cash balance model.
   * 
   * @var AccountCashBalance accountCashBalance
   */
  private final transient AccountCashBalance accountCashBalance;
  
  /**
   * The Cash Balance inputs.
   * 
   * @var view.inputs.AccountCashBalance INPUTS_CASH_BALANCE
   */
  private static final application.view.inputs.AccountCashBalance INPUTS_CASH_BALANCE = 
      InputsLab.getInstance().getAccountCashBalance();
  
  /**
   * The Cash Balance output.
   * 
   * @var view.results.AccountCashBalance resultsCashBalance
   */
  private static application.view.outputs.AccountCashBalance resultsCashBalance = 
      new application.view.outputs.AccountCashBalance();
  
  /**
   * The ROTH IRA model.
   * 
   * @var AccountRoth accountRoth
   */
  private final transient AccountRoth accountRoth;
  
  /**
   * The ROTH IRA inputs.
   * 
   * @var view.inputs.AccountRoth INPUTS_ROTH
   */
  private static final application.view.inputs.AccountRoth INPUTS_ROTH = 
      InputsLab.getInstance().getAccountRoth();
  
  /**
   * The ROTH IRA output.
   * 
   * @var view.results.AccountRoth resultsRoth
   */
  private static application.view.outputs.AccountRoth resultsRoth = new application.view.outputs.AccountRoth();
  
  /**
   * The Traditional IRA model.
   * 
   * @var AccountIra accountIra
   */
  private final transient AccountIra accountIra;
  
  /** 
   * The Traditional IRA inputs.
   * 
   * @var view.inputs.AccountIra INPUTS_IRA
   */
  private static final application.view.inputs.AccountIra INPUTS_IRA = InputsLab.getInstance().getAccountIra();
  
  /**
   * The Tradtional IRA output.
   * 
   * @var view.results.AccountIra resultsIra
   */
  private static application.view.outputs.AccountIra resultsIra = new application.view.outputs.AccountIra();
    
  /**
   * The pension model.
   * 
   * @var Pension pension
   */
  private final transient Pension pension;
  
  /**
   * The pension inputs.
   * 
   * @var view.inputs.Pension INPUTS_PENSION
   */
  private static final application.view.inputs.Pension INPUTS_PENSION = InputsLab.getInstance().getPension();
  
  /**
   * The pension output.
   * 
   * @var view.results.Pension resultsPension
   */
  private static application.view.outputs.Pension resultsPension = new application.view.outputs.Pension();
  
  /**
   * The savings model.
   * 
   * @var Savings savings
   */
  private final transient SavingsModel savingsModel;
  
  /**
   * The savings inputs.
   * 
   * @var view.inputs.Savings
   */
  public static final application.view.inputs.Savings INPUTS_SAVINGS = InputsLab.getInstance().getSavings();

  /**
   * The pension output.
   * 
   * @var view.results.Pension resultsPension
   */
  private static application.view.outputs.Savings resultsSavings = new application.view.outputs.Savings();
  
  
  /**
   * The social security model.
   * 
   * @var SocialSecurity socialSecurity
   */
  private final transient SocialSecurity socialSecurity;
  
  /**
   * The social security inputs.
   * 
   * @var view.inputs.SocialSecurity INPUTS_SOCIAL_SECURITY
   */
  private static final application.view.inputs.SocialSecurity INPUTS_SOCIAL_SECURITY = 
      InputsLab.getInstance().getSocialSecurity();
  
  /**
   * The social security output.
   * 
   * @var view.results.SocialSecurity resultsSocialSecurity
   */
  private static application.view.outputs.SocialSecurity resultsSocialSecurity = 
      new application.view.outputs.SocialSecurity();
  
  /**
   * The deductions model.
   * 
   * @var Deductions deductions
   */
  private final transient Deductions deductions;
  
  /**
   * The deductions inputs.
   * 
   * @var view.inputs.Deductions INPUTS_DEDUCTIONS
   */
  private static final application.view.inputs.Deductions INPUTS_DEDUCTIONS = 
      InputsLab.getInstance().getDeductions();
  
  /**
   * The deductions output.
   * 
   * @var view.results.Deductions resultsDeductions
   */
  private static application.view.outputs.Deductions resultsDeductions = new application.view.outputs.Deductions();

  /**
   * The taxes model.
   * 
   * @var Taxes taxes
   */
  private final transient Taxes taxes;
  
  /**
   * The taxes inputs.
   * 
   * @var view.inputs.Taxes INPUTS_TAXES
   */
  public static final application.view.inputs.Taxes INPUT_TAXES = InputsLab.getInstance().getTaxes();
  
  /**
   * The taxes output.
   * 
   * @var view.results.Taxes resultsTaxes
   */
  private static application.view.outputs.Taxes resultsTaxes = new application.view.outputs.Taxes();

  /**
   * The amount of taxable withdrawals.
   * 
   * @var double taxableWithdrawals
   */
  private transient double taxableWithdrawals;
  
  /**
   * Create the models.
   */
  public ExecuteObject() {
    super();
    
    LOGGER.trace("Calling the second Execute constructor.");
        
    expenses = new Expenses(INPUTS_EXPENSES.getExpenses(), INPUTS_PERSONAL.getInflationRate(), 
                            INPUTS_PERSONAL.getCurrentAge(), INPUTS_PERSONAL.getCurrentYear(), 
                            INPUTS_PERSONAL.getDeathAge());
    
    brokerage = new Brokerage(INPUTS_BROKERAGE.getBalance(), INPUTS_BROKERAGE.getGrowthRate(), 
                              INPUTS_PERSONAL.getCurrentAge(), INPUTS_PERSONAL.getCurrentYear(), 
                              INPUTS_PERSONAL.getDeathAge());
    
    salary = new Salary(INPUTS_SALARY.getSalary(), INPUTS_SALARY.getMeritIncrease(), 
                        INPUTS_PERSONAL.getCurrentAge(), INPUTS_PERSONAL.getCurrentYear(), 
                        INPUTS_PERSONAL.getDeathAge(), INPUTS_PERSONAL.getRetirementAge());
    
    savingsModel = new SavingsModel(INPUTS_SAVINGS.getBalance(), INPUTS_SAVINGS.getGrowthRate(), 
        INPUTS_PERSONAL.getCurrentAge(), INPUTS_PERSONAL.getCurrentYear(), 
        INPUTS_PERSONAL.getDeathAge());

    account401K = new Account401K(INPUTS_401K.getBalance(), INPUTS_401K.getGrowthRate(), 
                                INPUTS_401K.getAnnualContributions(),
                                INPUTS_PERSONAL.getCurrentAge(), INPUTS_PERSONAL.getCurrentYear(), 
                                INPUTS_PERSONAL.getRetirementAge(),
                                INPUTS_PERSONAL.getDeathAge(), INPUTS_401K.getStartWithdrawalAge(),
                                INPUTS_401K.getNumberOfWithdrawals(), salary, 
                                INPUTS_401K.isOnlyWhileSalary());
    
    account403B = new Account403B(INPUTS_403B.getBalance(), INPUTS_403B.getGrowthRate(), 
                             INPUTS_403B.getAnnualContributions(), INPUTS_PERSONAL.getCurrentAge(), 
                             INPUTS_PERSONAL.getCurrentYear(), INPUTS_PERSONAL.getRetirementAge(),
                             INPUTS_PERSONAL.getDeathAge(), INPUTS_403B.getStartWithdrawalAge(),
                             INPUTS_403B.getNumberOfWithdrawals(), salary,
                             INPUTS_403B.isOnlyWhileSalary());
    
    accountCashBalance = new AccountCashBalance(INPUTS_CASH_BALANCE.getBalance(), 
                                                INPUTS_CASH_BALANCE.getGrowthRate(), 
                                                INPUTS_CASH_BALANCE.getAnnualContributions(),
                                                INPUTS_PERSONAL.getCurrentAge(), 
                                                INPUTS_PERSONAL.getCurrentYear(), 
                                                INPUTS_PERSONAL.getRetirementAge(),
                                                INPUTS_PERSONAL.getDeathAge(), 
                                                INPUTS_CASH_BALANCE.getStartWithdrawalAge(),
                                                INPUTS_CASH_BALANCE.getNumberOfWithdrawals(),
                                                salary, 
                                                INPUTS_CASH_BALANCE.isOnlyWhileSalary());
    
    accountRoth = new AccountRoth(INPUTS_ROTH.getBalance(), 
                                  INPUTS_ROTH.getGrowthRate(), 
                                  INPUTS_ROTH.getAnnualContributions(),
                                  INPUTS_PERSONAL.getCurrentAge(), 
                                  INPUTS_PERSONAL.getCurrentYear(), 
                                  INPUTS_PERSONAL.getRetirementAge(),
                                  INPUTS_PERSONAL.getDeathAge(), 
                                  INPUTS_ROTH.getStartWithdrawalAge(),
                                  INPUTS_ROTH.getNumberOfWithdrawals(),
                                  salary, 
                                  INPUTS_ROTH.isOnlyWhileSalary());
    
    accountIra = new AccountIra(INPUTS_IRA.getBalance(), INPUTS_IRA.getGrowthRate(), 
                                INPUTS_IRA.getAnnualContributions(),
                                INPUTS_PERSONAL.getCurrentAge(), 
                                INPUTS_PERSONAL.getCurrentYear(), 
                                INPUTS_PERSONAL.getRetirementAge(),
                                INPUTS_PERSONAL.getDeathAge(), 
                                INPUTS_IRA.getStartWithdrawalAge(),
                                INPUTS_IRA.getNumberOfWithdrawals(),
                                salary, 
                                INPUTS_IRA.isOnlyWhileSalary());

    pension = new Pension(INPUTS_PENSION.getMonthlyAmount(), INPUTS_PENSION.getStartAge(),
                          INPUTS_PENSION.isInflationAdjusted(), 
                          INPUTS_PERSONAL.getInflationRate(), 
                          INPUTS_PERSONAL.getCurrentAge(), INPUTS_PERSONAL.getCurrentYear(), 
                          INPUTS_PERSONAL.getDeathAge());
    
    socialSecurity = new SocialSecurity(INPUTS_SOCIAL_SECURITY.getMonthlyAmount(), 
                                        INPUTS_SOCIAL_SECURITY.getStartAge(),
                                        INPUTS_PERSONAL.getInflationRate(), 
                                        INPUTS_PERSONAL.getCurrentAge(), 
                                        INPUTS_PERSONAL.getCurrentYear(),
                                        INPUTS_PERSONAL.getDeathAge());

    deductions = new Deductions(INPUTS_DEDUCTIONS.getDeductions(), 
                                INPUTS_PERSONAL.getInflationRate(), 
                                INPUTS_PERSONAL.getCurrentAge(), 
                                INPUTS_PERSONAL.getCurrentYear(), 
                                INPUTS_PERSONAL.getDeathAge());

    taxes = new Taxes(INPUTS_PERSONAL.getInflationRate(), 
                      INPUTS_PERSONAL.getCurrentAge(), 
                      INPUTS_PERSONAL.getCurrentYear(), 
                      INPUTS_PERSONAL.getDeathAge(), 
                      INPUT_TAXES.getFederalTaxRate(), 
                      INPUT_TAXES.getStateTaxRate());
      
    LOGGER.trace("Leaving the Execute constructor.");
  }
  

  /**
   * Create the income based on the year.
   * 
   * @since 1.0
   */
  private double computeTaxableIncomeValue(final int year) {
	  double incomeValue;
	  
	  incomeValue = 0.0;
	  if (salary != null) {
		  incomeValue += salary.getEndingValue(year);             
	  }

	  if (pension != null) {
		  incomeValue += pension.getEndingValue(year);
	  }
  
	  if (socialSecurity != null) {
		  incomeValue += socialSecurity.getEndingValue(year);
	  }
  
	  if (account401K != null) {
		  incomeValue += account401K.getWithdrawals(year);
	  }
  
	  if (brokerage != null) {
		  incomeValue += brokerage.getWithdrawals(year) / 4;
	  }
  
	  if (account403B != null) {
		  incomeValue += account403B.getWithdrawals(year);
	  }
  
	  if (savingsModel != null) {
		  incomeValue += savingsModel.getBeginningValue(year)
				                     * INPUTS_SAVINGS.getGrowthRate();
	  }
  
	  if (accountCashBalance != null) {
		  incomeValue += accountCashBalance.getWithdrawals(year);
	  }

	  if (accountIra != null) {
		  incomeValue += accountIra.getWithdrawals(year);
	  }
	  
	  return incomeValue;
  }

  /**
   * Create the results based on the inputs.
   * 
   * @since 1.0
   */
  private void run() {
    int    currentAge;
    double deposits;
    double expense; 
    double partialExpense;
    double incomeValue;
    double tax;
    double amount;
    ResultsDataNode node;
              
    for (int year = expenses.getSmallestYear(); year <= expenses.getLargestYear(); year++) {
      taxableWithdrawals = 0.0;
            
      expense = expenses.getBeginningValue(year);

      incomeValue = computeTaxableIncomeValue(year);
      
      taxableWithdrawals = incomeValue - deductions.getEndingValue(year);      
      currentAge = expenses.convertYearToAge(year);
      tax = taxes.compute(currentAge, taxableWithdrawals);
                    
      partialExpense = incomeValue - deductions.getEndingValue(year) - expense - tax;
      
      deposits = 0;
      if (partialExpense < 0) {
        amount = savingsModel.withdraw(currentAge, -partialExpense);
        partialExpense += amount;
        if (brokerage.getBeginningValue(year) > -partialExpense) { 
          amount = brokerage.withdraw(currentAge, -partialExpense);
        }
        if (accountRoth.getNumberOfWithdrawals() == 0 
            && currentAge >= accountRoth.getStartWithdrawalsAge() 
            && accountRoth.getBeginningValue(year) > -partialExpense) { 
          amount = accountRoth.withdraw(currentAge, -partialExpense);
        }
        if (account401K.getNumberOfWithdrawals() == 0 
            && currentAge >= account401K.getStartWithdrawalsAge() 
            && account401K.getBeginningValue(year) > -partialExpense) { 
          amount = account401K.withdraw(currentAge, -partialExpense);
        }
        if (account403B.getNumberOfWithdrawals() == 0 
            && currentAge >= account403B.getStartWithdrawalsAge() 
            && account403B.getBeginningValue(year) > -partialExpense) { 
          amount = account403B.withdraw(currentAge, -partialExpense);
        }
        if (accountCashBalance.getNumberOfWithdrawals() == 0
            && currentAge >= accountCashBalance.getStartWithdrawalsAge()
            && accountCashBalance.getBeginningValue(year) > -partialExpense) { 
          amount = accountCashBalance.withdraw(currentAge, -partialExpense);
        }
        if (accountIra.getNumberOfWithdrawals() == 0 
            && currentAge >= accountIra.getStartWithdrawalsAge() 
            && accountIra.getBeginningValue(year) > -partialExpense) { 
          amount = accountIra.withdraw(currentAge, -partialExpense);
        }
      }
      
      if (partialExpense > 0) {
        brokerage.deposit(currentAge, partialExpense);
      }
                      
      node = expenses.getNodeBasedOnYear(year);
      if (node != null) {
        node.setValues(year, currentAge, incomeValue, deposits, 
                       incomeValue, tax, expense);           
      }
    }
  }
  
  /**
   * Run the simulation.
   */
  public void runSimulation(final InputsTabbedPane inputsTabbedPane,
                            final ResultsTabbedPane resultsTabbedPane) {
    LOGGER.trace("Entering the Main runSimulation().");
    
    ranSimulation = false;
    final MyJDialog myJDialog = new MyJDialog();
    if (!myJDialog.isInvalidNumberDialogShown()) {
      final ValidateInputs validateInputs = new ValidateInputs(inputsTabbedPane);
    
      if (validateInputs.checkInputs(INPUTS_PERSONAL, INPUTS_401K, INPUTS_EXPENSES, 
          INPUTS_BROKERAGE,
          INPUTS_SALARY, INPUTS_403B, INPUTS_CASH_BALANCE, INPUTS_IRA, INPUTS_ROTH, INPUTS_PENSION,
          INPUTS_SOCIAL_SECURITY, INPUTS_DEDUCTIONS, INPUTS_SAVINGS)) {
        ranSimulation = true;
        
        run(); 
    
        draw(resultsTabbedPane);
      }
    }
    
    LOGGER.trace("Leaving the Main runSimulation().");        
  }
  
  /** 
   * Draw the results.
   * 
   * @param resultsTabbedPane Draw the results 
   */
  public void draw(final ResultsTabbedPane resultsTabbedPane) {
    LOGGER.trace("Calling the Execute draw().");
    LOGGER.trace("    resultsTabbedPane = <" + resultsTabbedPane + ">");
    
    final int currentIndex = resultsTabbedPane.getSelectedIndex();
    
    resultsTabbedPane.setSelectedIndex(resultsTabbedPane.indexOfTab(resultsExpenses.getName()));
    resultsExpenses.clear();
    resultsExpenses.writeListOfData(expenses.getListOfValues());
    OutputsLab.setExpenses(resultsExpenses);
    
    resultsTabbedPane.setSelectedIndex(resultsTabbedPane.indexOfTab(resultsBrokerage.getName()));
    resultsBrokerage.clear();
    resultsBrokerage.writeListOfData(brokerage.getListOfValues()); 
    OutputsLab.setBrokerage(resultsBrokerage);

    resultsTabbedPane.setSelectedIndex(resultsTabbedPane.indexOfTab(resultsSavings.getName()));
    resultsSavings.clear();
    resultsSavings.writeListOfData(savingsModel.getListOfValues()); 
    OutputsLab.setSavings(resultsSavings);

    resultsTabbedPane.setSelectedIndex(resultsTabbedPane.indexOfTab(resultsSalary.getName()));
    resultsSalary.clear();
    resultsSalary.writeListOfData(salary.getListOfValues()); 
    OutputsLab.setSalary(resultsSalary);
    
    resultsTabbedPane.setSelectedIndex(resultsTabbedPane.indexOfTab(results401K.getName()));
    results401K.clear();
    results401K.writeListOfData(account401K.getListOfValues()); 
    OutputsLab.setAccount401K(results401K);
    
    resultsTabbedPane.setSelectedIndex(resultsTabbedPane.indexOfTab(results403B.getName()));
    results403B.clear();
    results403B.writeListOfData(account403B.getListOfValues()); 
    OutputsLab.setAccount403B(results403B);
    
    resultsTabbedPane.setSelectedIndex(resultsTabbedPane.indexOfTab(resultsCashBalance.getName()));
    resultsCashBalance.clear();
    resultsCashBalance.writeListOfData(accountCashBalance.getListOfValues()); 
    OutputsLab.setAccountCashBalance(resultsCashBalance);
    
    resultsTabbedPane.setSelectedIndex(resultsTabbedPane.indexOfTab(resultsRoth.getName()));
    resultsRoth.clear();
    resultsRoth.writeListOfData(accountRoth.getListOfValues()); 
    OutputsLab.setAccountRoth(resultsRoth);
    
    resultsTabbedPane.setSelectedIndex(resultsTabbedPane.indexOfTab(resultsIra.getName()));
    resultsIra.clear();
    resultsIra.writeListOfData(accountIra.getListOfValues()); 
    OutputsLab.setAccountIra(resultsIra);
    
    resultsTabbedPane.setSelectedIndex(resultsTabbedPane.indexOfTab(resultsPension.getName()));
    resultsPension.clear();
    resultsPension.writeListOfData(pension.getListOfValues()); 
    OutputsLab.setPension(resultsPension);
    
    resultsTabbedPane.setSelectedIndex(resultsTabbedPane.indexOfTab(
                                       resultsSocialSecurity.getName()));
    resultsSocialSecurity.clear();
    resultsSocialSecurity.writeListOfData(socialSecurity.getListOfValues()); 
    OutputsLab.setSocialSecurity(resultsSocialSecurity);
    
    resultsTabbedPane.setSelectedIndex(resultsTabbedPane.indexOfTab(resultsDeductions.getName()));
    resultsDeductions.clear();
    resultsDeductions.writeListOfData(deductions.getListOfValues()); 
    OutputsLab.setDeductions(resultsDeductions);
    
    resultsTabbedPane.setSelectedIndex(resultsTabbedPane.indexOfTab(resultsTaxes.getName()));
    resultsTaxes.clear();
    resultsTaxes.writeListOfData(taxes.getListOfValues()); 
    OutputsLab.setTaxes(resultsTaxes);
    
    resultsTabbedPane.setSelectedIndex(currentIndex);
    
    LOGGER.trace("Leaving the Execute draw().");
  }
  
  /** 
   * Get the amount of taxable withdrawals.
   * 
   * @return The amount of taxable withdrawals 
   */
  public double getTaxableWithdrawals() {
    LOGGER.trace("Calling the Execute getTaxableWithdrawals().");
        
    LOGGER.trace("Leaving the Execute getTaxableWithdrawals().");
    LOGGER.trace("           Returning " + taxableWithdrawals);
        
    return taxableWithdrawals;
  }
  
  public boolean isRanSimulation() {
    return ranSimulation;
  }
  
  public static application.view.outputs.Taxes getOutputTaxes() {
    return resultsTaxes;
  }
  
  public static application.view.outputs.Salary getOutputSalary() {
    return resultsSalary;
  }
    
  public static application.view.outputs.Savings getOutputSavings() {
    return resultsSavings;
  }
  
  public static application.view.outputs.Brokerage getOutputBrokerage() {
    return resultsBrokerage;
  }
  
  public static application.view.outputs.Deductions getOutputDeductions() {
    return resultsDeductions;
  }
  
  public static application.view.outputs.Pension getOutputPension() {
    return resultsPension;
  }
  
  public static application.view.outputs.Account401K getOutputAccount401K() {
    return results401K;
  }
  
  public static application.view.outputs.Account403B getOutputAccount403B() {
    return results403B;
  }
  
  public static application.view.outputs.AccountCashBalance getOutputAccountCashBalance() {
    return resultsCashBalance;
  }
  
  public static application.view.outputs.AccountIra getOutputAccountIra() {
    return resultsIra;
  }
  
  public static application.view.outputs.SocialSecurity getOutputSocialSecurity() {
    return resultsSocialSecurity;
  }
}