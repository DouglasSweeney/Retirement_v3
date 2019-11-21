package test.system.main;

import static org.junit.Assert.assertThat;

import org.hamcrest.number.IsCloseTo;
import org.junit.Before;
import org.junit.Test;

import application.main.ExecuteObject;
import application.main.InputsLab;
import application.view.inputs.Account401K;
import application.view.inputs.Account403B;
import application.view.inputs.AccountCashBalance;
import application.view.inputs.AccountIra;
import application.view.inputs.Brokerage;
import application.view.inputs.Expenses;
import application.view.inputs.InputsTabbedPane;
import application.view.inputs.Pension;
import application.view.inputs.Savings;
import application.view.outputs.ResultsTabbedPane;

public class ExecuteObjectTest {
  private static final double EPSILON = 0.01;
  private static final int LIST_NUMBER = 0;
  
  private transient InputsTabbedPane inputsTabbedPane;
  private transient ResultsTabbedPane resultsTabbedPane;
  
  private final transient Expenses expense = InputsLab.getInstance().getExpenses();
  private final transient Pension pension = InputsLab.getInstance().getPension();
  private final transient Account401K account401k = InputsLab.getInstance().getAccount401K();
  private final transient Account403B account403b = InputsLab.getInstance().getAccount403B();
  private final transient AccountCashBalance accountCashBalance = InputsLab.getInstance().getAccountCashBalance();
  private final transient AccountIra accountIra = InputsLab.getInstance().getAccountIra();
  private final transient Savings savings = InputsLab.getInstance().getSavings();
  private final transient Brokerage brokerage = InputsLab.getInstance().getBrokerage();
  
  private final transient application.view.outputs.Deductions   resultsDeductions = ExecuteObject.getOutputDeductions();
  private       transient application.view.outputs.Taxes   resultsTaxes = ExecuteObject.getOutputTaxes();
  private final transient application.view.outputs.Salary   resultsSalary = ExecuteObject.getOutputSalary();
  private final transient application.view.outputs.Savings   resultsSavings = ExecuteObject.getOutputSavings();
  private       transient application.view.outputs.Brokerage   resultsBrokerage = ExecuteObject.getOutputBrokerage();
  private final transient application.view.outputs.Pension   resultsPension = ExecuteObject.getOutputPension();
  private final transient application.view.outputs.Account401K   results401k = ExecuteObject.getOutputAccount401K();
  private final transient application.view.outputs.Account403B   results403b = ExecuteObject.getOutputAccount403B();
  private final transient application.view.outputs.AccountCashBalance   resultsCashBalance = ExecuteObject.getOutputAccountCashBalance();
  private final transient application.view.outputs.AccountIra   resultsIra = ExecuteObject.getOutputAccountIra();
  private final transient application.view.outputs.SocialSecurity   resultsSS = ExecuteObject.getOutputSocialSecurity();

  
  private double computeTaxes(double taxableIncome, final int nodeIndex, 
		        final double effectiveFederalTaxRate, final double effectiveStateTaxRate) {
    taxableIncome = taxableIncome - resultsDeductions.getList().get(nodeIndex).getEndingValue(); // 6695.0; //deductions.getDeductions();
    final double federalTaxes = taxableIncome * effectiveFederalTaxRate;
    final double stateTaxes = taxableIncome * effectiveStateTaxRate;
    
    return federalTaxes + stateTaxes;
  }
  
  @Before
  public void setUp() {
    inputsTabbedPane = new InputsTabbedPane("input_xmls/test.xml");
    resultsTabbedPane = new ResultsTabbedPane();
  }
  
  @Test
  public void testHappyCase() {
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    resultsBrokerage = ExecuteObject.getOutputBrokerage();

    assertThat("The brokerage deposit doesn't match the GUI ouptut list",
    		    resultsBrokerage.getList().get(0).getDeposit(), 
    		    new IsCloseTo(44_451.20, EPSILON));
  }
  
  @Test
  public void testHappyCaseTaxes() {
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    resultsTaxes = ExecuteObject.getOutputTaxes();
    
    assertThat("The brokerage deposit doesn't match the GUI ouptut list",
    		   resultsTaxes.getList().get(0).getEndingValue(),
    		   new IsCloseTo(21_112.80, EPSILON));
  }
  
  @Test
  public void testJustSalaryForTaxes() {
    account401k.setNumberOfWithdrawals("0");
    savings.setGrowthRate("0.0%");
    pension.setMonthlyAmount("0.00");
    brokerage.setGrowthRate("0.0%");
     
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    final double effectiveFederalTax = ExecuteObject.INPUT_TAXES.getFederalTaxRate(); 
    final double effectiveStateTax = ExecuteObject.INPUT_TAXES.getStateTaxRate(); 
    final double taxes = computeTaxes(resultsSalary.getList().get(0).getEndingValue(), 
                                      LIST_NUMBER, effectiveFederalTax, effectiveStateTax);
    resultsTaxes = ExecuteObject.getOutputTaxes();
    assertThat("SUT Taxes doesn't equal computed taxes", 
    		   resultsTaxes.getList().get(0).getEndingValue(), 
    		   new IsCloseTo(taxes, EPSILON));
  }
  
  @Test
  public void testJustSalaryAndPensionForTaxes() {
    account401k.setNumberOfWithdrawals("0");
    savings.setGrowthRate("0.0%");
    brokerage.setGrowthRate("0.0%");
     
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    double income = resultsSalary.getList().get(0).getEndingValue();
           income += resultsPension.getList().get(0).getEndingValue();
    
    final double effectiveFederalTax = ExecuteObject.INPUT_TAXES.getFederalTaxRate(); 
    final double effectiveStateTax = ExecuteObject.INPUT_TAXES.getStateTaxRate(); 
    final double taxes = computeTaxes(income, LIST_NUMBER, effectiveFederalTax, effectiveStateTax);
    
    resultsTaxes = ExecuteObject.getOutputTaxes();
    assertThat("SUT Taxes doesn't equal computed taxes",
    		   resultsTaxes.getList().get(0).getEndingValue(), 
    		   new IsCloseTo(taxes, EPSILON));
  }
  
  @Test
  public void testJustSalaryPensionAnd401KForTaxes() {
    savings.setGrowthRate("0.0%");
    brokerage.setGrowthRate("0.0%");
     
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    double income = resultsSalary.getList().get(0).getEndingValue();
           income += resultsPension.getList().get(0).getEndingValue();
           income += results401k.getList().get(0).getWithdrawal();
            
    final double effectiveFederalTax = ExecuteObject.INPUT_TAXES.getFederalTaxRate(); 
    final double effectiveStateTax = ExecuteObject.INPUT_TAXES.getStateTaxRate(); 
    final double taxes = computeTaxes(income, LIST_NUMBER, effectiveFederalTax, effectiveStateTax);
    
    resultsTaxes = ExecuteObject.getOutputTaxes();
    assertThat("SUT Taxes doesn't equal computed taxes",
    		   resultsTaxes.getList().get(0).getEndingValue(), 
    		   new IsCloseTo(taxes, EPSILON));
  }
  
  @Test
  public void testJustSalaryPension401kAndSavingsForTaxes() {
    brokerage.setGrowthRate("0.0%");
     
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    double income = resultsSalary.getList().get(0).getEndingValue();
           income += resultsPension.getList().get(0).getEndingValue();
           income += results401k.getList().get(0).getWithdrawal();
           income += resultsSavings.getList().get(0).getBeginningValue() *
                     ExecuteObject.INPUTS_SAVINGS.getGrowthRate();
    final double effectiveFederalTax = ExecuteObject.INPUT_TAXES.getFederalTaxRate(); 
    final double effectiveStateTax = ExecuteObject.INPUT_TAXES.getStateTaxRate(); 
    final double taxes = computeTaxes(income, LIST_NUMBER, effectiveFederalTax, effectiveStateTax);
    
    resultsTaxes = ExecuteObject.getOutputTaxes();
    assertThat("SUT Taxes doesn't equal computed taxes",
    		   resultsTaxes.getList().get(0).getEndingValue(), 
    		   new IsCloseTo(taxes, EPSILON));
  }
  
  @Test
  public void testJustSalaryPension401kAndSavingsForBrokerageDeposit() {
    brokerage.setGrowthRate("0.0%");
     
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    double income = resultsSalary.getList().get(0).getEndingValue();
           income += resultsPension.getList().get(0).getEndingValue();
           income += results401k.getList().get(0).getWithdrawal();
           income += resultsSavings.getList().get(0).getBeginningValue() *
               ExecuteObject.INPUTS_SAVINGS.getGrowthRate();
    final double effectiveFederalTax = ExecuteObject.INPUT_TAXES.getFederalTaxRate(); 
    final double effectiveStateTax = ExecuteObject.INPUT_TAXES.getStateTaxRate(); 
    final double taxes = computeTaxes(income, LIST_NUMBER, effectiveFederalTax, effectiveStateTax);
    
    income -= resultsDeductions.getList().get(0).getEndingValue();
    income -= taxes;
    income -= expense.getExpenses();
    
    resultsTaxes = ExecuteObject.getOutputTaxes();
    assertThat("SUT Taxes doesn't equal computed taxes",
    		   resultsBrokerage.getList().get(0).getDeposit(), 
    		   new IsCloseTo(income, EPSILON));
  }
  
  @Test
  public void testJustSalaryPension401kSavingsAndBrokerageForTaxes() {
    brokerage.setGrowthRate("0.0%");
     
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    double income = resultsSalary.getList().get(0).getEndingValue();
           income += resultsPension.getList().get(0).getEndingValue();
           income += results401k.getList().get(0).getWithdrawal();
           income += resultsSavings.getList().get(0).getBeginningValue() *
               ExecuteObject.INPUTS_SAVINGS.getGrowthRate();
    final double endingValue = resultsBrokerage.getList().get(0).getEndingValue(); 
    final double deposit = resultsBrokerage.getList().get(0).getDeposit();
    final double withdrawal = resultsBrokerage.getList().get(0).getWithdrawal();
    final double beginningValue = resultsBrokerage.getList().get(0).getBeginningValue();
    final double taxable = endingValue - deposit - beginningValue + withdrawal;
           income += taxable;
           
    final double effectiveFederalTax = ExecuteObject.INPUT_TAXES.getFederalTaxRate(); 
    final double effectiveStateTax = ExecuteObject.INPUT_TAXES.getStateTaxRate(); 
    final double taxes = computeTaxes(income, LIST_NUMBER, effectiveFederalTax, effectiveStateTax);
    
    resultsTaxes = ExecuteObject.getOutputTaxes();
    assertThat("SUT Taxes doesn't equal computed taxes",
    		   resultsTaxes.getList().get(0).getEndingValue(), 
    		   new IsCloseTo(taxes, EPSILON));
  }
  
  @Test
  public void testJustSalaryPension401kSavingsBrokerageAnd403bForTaxes() {
    brokerage.setGrowthRate("0.0%");
     
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    double income = resultsSalary.getList().get(0).getEndingValue();
           income += resultsPension.getList().get(0).getEndingValue();
           income += results401k.getList().get(0).getWithdrawal();
           income += results403b.getList().get(0).getWithdrawal();
           income += resultsSavings.getList().get(0).getBeginningValue() *
               ExecuteObject.INPUTS_SAVINGS.getGrowthRate();
    final double endingValue = resultsBrokerage.getList().get(0).getEndingValue(); 
    final double deposit = resultsBrokerage.getList().get(0).getDeposit();
    final double withdrawal = resultsBrokerage.getList().get(0).getWithdrawal();
    final double beginningValue = resultsBrokerage.getList().get(0).getBeginningValue();
    final double taxable = endingValue - deposit - beginningValue + withdrawal;
           income += taxable;
           
    final double effectiveFederalTax = ExecuteObject.INPUT_TAXES.getFederalTaxRate(); 
    final double effectiveStateTax = ExecuteObject.INPUT_TAXES.getStateTaxRate(); 
    final double taxes = computeTaxes(income, LIST_NUMBER, effectiveFederalTax, effectiveStateTax);
    
    resultsTaxes = ExecuteObject.getOutputTaxes();
    assertThat("SUT Taxes doesn't equal computed taxes",
    		   resultsTaxes.getList().get(0).getEndingValue(), 
    		   new IsCloseTo(taxes, EPSILON));
  }
  
  @Test
  public void testJustSalaryPension401kSavingsBrokerageAnd403bWithdrawalsForTaxes() {
    brokerage.setGrowthRate("0.0%");
    account403b.setNumberOfWithdrawals("10");
    
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    double income = resultsSalary.getList().get(0).getEndingValue();
           income += resultsPension.getList().get(0).getEndingValue();
           income += results401k.getList().get(0).getWithdrawal();
           income += results403b.getList().get(0).getWithdrawal();
           income += resultsSavings.getList().get(0).getBeginningValue() *
               ExecuteObject.INPUTS_SAVINGS.getGrowthRate();
    final double endingValue = resultsBrokerage.getList().get(0).getEndingValue(); 
    final double deposit = resultsBrokerage.getList().get(0).getDeposit();
    final double withdrawal = resultsBrokerage.getList().get(0).getWithdrawal();
    final double beginningValue = resultsBrokerage.getList().get(0).getBeginningValue();
    final double taxable = endingValue - deposit - beginningValue + withdrawal;
           income += taxable;
           
    final double effectiveFederalTax = ExecuteObject.INPUT_TAXES.getFederalTaxRate(); 
    final double effectiveStateTax = ExecuteObject.INPUT_TAXES.getStateTaxRate(); 
    final double taxes = computeTaxes(income, LIST_NUMBER, effectiveFederalTax, effectiveStateTax);
    
    resultsTaxes = ExecuteObject.getOutputTaxes();
    assertThat("SUT Taxes doesn't equal computed taxes",
    		   resultsTaxes.getList().get(0).getEndingValue(), 
    		   new IsCloseTo(taxes, EPSILON));
  }
  
  @Test
  public void testJustSalaryPension401kSavingsBrokerage403bAndCashBalanceWithdrawalsForTaxes() {
    brokerage.setGrowthRate("0.0%");
    accountCashBalance.setNumberOfWithdrawals("5");
    accountCashBalance.setGrowthRate("0.0%");
    
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    double income = resultsSalary.getList().get(0).getEndingValue();
           income += resultsPension.getList().get(0).getEndingValue();
           income += results401k.getList().get(0).getWithdrawal();
           income += results403b.getList().get(0).getWithdrawal();
           income += resultsSavings.getList().get(0).getBeginningValue() *
               ExecuteObject.INPUTS_SAVINGS.getGrowthRate();
    final double endingValue = resultsBrokerage.getList().get(0).getEndingValue(); 
    final double deposit = resultsBrokerage.getList().get(0).getDeposit();
    final double withdrawal = resultsBrokerage.getList().get(0).getWithdrawal();
    final double beginningValue = resultsBrokerage.getList().get(0).getBeginningValue();
    final double taxable = endingValue - deposit - beginningValue + withdrawal;
           income += taxable;
           income += resultsCashBalance.getList().get(0).getWithdrawal();
           
    final double effectiveFederalTax = ExecuteObject.INPUT_TAXES.getFederalTaxRate(); 
    final double effectiveStateTax = ExecuteObject.INPUT_TAXES.getStateTaxRate(); 
    final double taxes = computeTaxes(income, LIST_NUMBER, effectiveFederalTax, effectiveStateTax);
    
    resultsTaxes = ExecuteObject.getOutputTaxes();
    assertThat("SUT Taxes doesn't equal computed taxes",
    		   resultsTaxes.getList().get(0).getEndingValue(), 
    		   new IsCloseTo(taxes, EPSILON));
  }
  
  @Test
  public void testJustSalaryPension401kSavingsBrokerage403bCashBalanceAndIraWithdrawalsForTaxes() {
    brokerage.setGrowthRate("0.0%");
    accountCashBalance.setNumberOfWithdrawals("5");
    accountCashBalance.setGrowthRate("0.0%");
    
    accountIra.setStartWithdrawalAge("58");
    accountIra.setAnnualContribution("0.0");
    accountIra.setGrowthRate("0.0%");
    accountIra.setNumberOfWithdrawals("5");
    
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    double income = resultsSalary.getList().get(0).getEndingValue();
           income += resultsPension.getList().get(0).getEndingValue();
           income += results401k.getList().get(0).getWithdrawal();
           income += results403b.getList().get(0).getWithdrawal();
           income += resultsSavings.getList().get(0).getBeginningValue() *
                      ExecuteObject.INPUTS_SAVINGS.getGrowthRate();
    final double endingValue = resultsBrokerage.getList().get(0).getEndingValue(); 
    final double deposit = resultsBrokerage.getList().get(0).getDeposit();
    final double withdrawal = resultsBrokerage.getList().get(0).getWithdrawal();
    final double beginningValue = resultsBrokerage.getList().get(0).getBeginningValue();
    final double taxable = endingValue - deposit - beginningValue + withdrawal;
           income += taxable;
           income += resultsCashBalance.getList().get(0).getWithdrawal();
           income += resultsIra.getList().get(0).getWithdrawal();
                      
    final double effectiveFederalTax = ExecuteObject.INPUT_TAXES.getFederalTaxRate(); 
    final double effectiveStateTax = ExecuteObject.INPUT_TAXES.getStateTaxRate(); 
    final double taxes = computeTaxes(income, LIST_NUMBER, effectiveFederalTax, effectiveStateTax);
    
    resultsTaxes = ExecuteObject.getOutputTaxes();
    assertThat("SUT Taxes doesn't equal computed taxes",
    		   resultsTaxes.getList().get(0).getEndingValue(), 
    		   new IsCloseTo(taxes, EPSILON));
  }
  
  @Test
  public void testJustSalaryPension401kSavingsBrokerage403bCashBalanceIraAndSocialSecurityIraWithdrawalsForTaxes() {
    final int nodeNumber = 10;
    brokerage.setGrowthRate("0.0%");
    savings.setGrowthRate("0.0%");
    accountCashBalance.setGrowthRate("0.0%");
    
    accountIra.setStartWithdrawalAge("59");
    accountIra.setAnnualContribution("0.0");
    accountIra.setGrowthRate("0.0%");
    accountIra.setNumberOfWithdrawals("5");
    
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    double income = resultsSalary.getList().get(nodeNumber).getEndingValue();
    income += resultsPension.getList().get(nodeNumber).getEndingValue();
    income += results401k.getList().get(nodeNumber).getWithdrawal();
    income += results403b.getList().get(nodeNumber).getWithdrawal();

    final double endingValue = resultsBrokerage.getList().get(nodeNumber).getEndingValue(); 
    final double deposit = resultsBrokerage.getList().get(nodeNumber).getDeposit();
    final double withdrawal = resultsBrokerage.getList().get(nodeNumber).getWithdrawal();
    final double beginningValue = resultsBrokerage.getList().get(nodeNumber).getBeginningValue();
    final double taxable = endingValue - deposit + withdrawal - beginningValue;
    income += taxable;
    income += resultsCashBalance.getList().get(nodeNumber).getWithdrawal();
    income += resultsIra.getList().get(nodeNumber).getWithdrawal();
    income += resultsSS.getList().get(nodeNumber).getEndingValue();
           
    final double effectiveFederalTax = ExecuteObject.INPUT_TAXES.getFederalTaxRate(); 
    final double effectiveStateTax = ExecuteObject.INPUT_TAXES.getStateTaxRate(); 
    final double taxes = computeTaxes(income, nodeNumber, effectiveFederalTax, effectiveStateTax);
    
    resultsTaxes = ExecuteObject.getOutputTaxes();
    assertThat("SUT Taxes doesn't equal computed taxes",
    		   resultsTaxes.getList().get(nodeNumber).getEndingValue(), 
    		   new IsCloseTo(taxes, EPSILON));
  }
  @Test
  public void testAllIndex10() {
    final int nodeNumber = 10;
    brokerage.setGrowthRate("0.0%");
    savings.setGrowthRate("0.0%");
    accountCashBalance.setGrowthRate("0.0%");
    
    accountIra.setStartWithdrawalAge("59");
    accountIra.setAnnualContribution("0.0");
    accountIra.setGrowthRate("0.0%");
    accountIra.setNumberOfWithdrawals("5");
    
    final ExecuteObject execute = new ExecuteObject();
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);

    double income = resultsSalary.getList().get(nodeNumber).getEndingValue();
    income += resultsPension.getList().get(nodeNumber).getEndingValue();
    income += results401k.getList().get(nodeNumber).getWithdrawal();
    income += results403b.getList().get(nodeNumber).getWithdrawal();
    final double endingValue = resultsBrokerage.getList().get(nodeNumber).getEndingValue(); 
    final double deposit = resultsBrokerage.getList().get(nodeNumber).getDeposit(); 
    final double withdrawal = resultsBrokerage.getList().get(nodeNumber).getWithdrawal();
    final double beginningValue = resultsBrokerage.getList().get(nodeNumber).getBeginningValue();
    final double taxable = endingValue - deposit + withdrawal - beginningValue;
    income += taxable;
    income += resultsCashBalance.getList().get(nodeNumber).getWithdrawal();
    income += resultsIra.getList().get(nodeNumber).getWithdrawal();
    income += resultsSS.getList().get(nodeNumber).getEndingValue();

           
    final double effectiveFederalTax = ExecuteObject.INPUT_TAXES.getFederalTaxRate(); 
    final double effectiveStateTax = ExecuteObject.INPUT_TAXES.getStateTaxRate(); 
    final double taxes = computeTaxes(income, nodeNumber, effectiveFederalTax, effectiveStateTax);
    
    resultsTaxes = ExecuteObject.getOutputTaxes();
    assertThat("SUT Taxes doesn't equal computed taxes",
    		   resultsTaxes.getList().get(nodeNumber).getEndingValue(), 
    		   new IsCloseTo(taxes, EPSILON));
  }
}