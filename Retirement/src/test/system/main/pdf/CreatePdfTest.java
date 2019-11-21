package test.system.main.pdf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import application.main.ExecuteObject;
import application.main.pdf.CreatePdf;
import application.view.inputs.Entry;
import application.view.inputs.Header;
import application.view.inputs.InputsTabbedPane;
import application.view.outputs.ResultsTabbedPane;
import test.system.view.inputs.TestUtils;

// Doesn't test for all the values!

public class CreatePdfTest {
  final static String DIRECTORY = "logs/pdf/";
  
  private static transient String filename;
  private static transient String textFilename;
  private static transient String fileContents;
  
  private static void createTextFile() {
	final CreatePdf createPdf = new CreatePdf();
	@SuppressWarnings("unused")
	final InputsTabbedPane inputsTabbedPane = new InputsTabbedPane("input_xmls/test.xml");
	final ResultsTabbedPane resultsTabbedPane = new ResultsTabbedPane();
	final ExecuteObject executeObject = new ExecuteObject();
	
    filename = createPdf.getPdfFilename(CreatePdf.DIRECTORY, "input_xmls/test.xml");
    textFilename = filename.replace(".pdf", ".txt");
    final String command = "pdftotext " + filename + " " + textFilename;
	final StartProcess startProcess = new StartProcess(command);
		  
    try {
		createPdf.create("input_xmls/test.xml");
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	    
    executeObject.draw(resultsTabbedPane);

    try {
		startProcess.start();
	} catch (IOException | IllegalMonitorStateException | InterruptedException e) {
		e.printStackTrace();
	}
  }
  
  private static void cleanup(final String filename) {
	if (filename != null) {
	  final File file = new File(filename);

	  if (file.exists()) { 
		file.delete();
	  }
	}
	
	try {
		Thread.sleep(200);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}
  
  private static void cleanup(final String filename1, final String filename2) {
	cleanup(filename1);
	cleanup(filename2);
  }
  
  @BeforeClass
  public static void beforeClass() {
	final TestUtils testUtils = new TestUtils();
	
    createTextFile();
  
    // Read what was converted into a String
    fileContents = testUtils.readFileIntoString(textFilename);
      
    cleanup(filename, textFilename);
  }

  @Test
  public void testHappyCase() {
    final CreatePdf createPdf = new CreatePdf();
    final String filename = createPdf.getPdfFilename(CreatePdf.DIRECTORY, "input_xmls/test.xml");
  
    cleanup(filename);
  
    assertEquals("Ensure filename generated is correct.", DIRECTORY + "test0000000000.pdf", filename);
}

  @Test
  public void testTwoFiles() {
    final CreatePdf createPdf = new CreatePdf();
    final String filename = createPdf.getPdfFilename(CreatePdf.DIRECTORY, "input_xmls/test.xml");
    final File file1 = new File(filename);
    try {
      file1.createNewFile();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    final String filename2 = createPdf.getPdfFilename(CreatePdf.DIRECTORY, "input_xmls/test.xml");
        
    cleanup(filename, filename2);
    
    assertEquals("E	final TestUtils testUtils = new TestUtils();\n" + 
    		"nsure filename generated is correct", DIRECTORY + "test0000000001.pdf", filename2);
  }
  
  @Test
  public void testRead401kInput() {
    final boolean element = fileContents.contains(Header.ACCOUNT_401K.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
    
  @Test
  public void testRead401kBalance() {
    final boolean element = fileContents.contains(Entry.CURRENT_BALANCE.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testRead401kBalanceValue() {
    final boolean element = fileContents.contains("354,001.00");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testRead401kGrowthRate() {
    final boolean element = fileContents.contains(Entry.ANNUAL_GROWTH_RATE.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testRead401kAnnualContributions() {
    final boolean element = fileContents.contains(Entry.ANNUAL_CONTRIBUTIONS.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testRead401kOnlyWithASalary() {
    final boolean element = fileContents.contains("Only With A Salary");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testRead401kStartWithdrawalAge() {
    final boolean element = fileContents.contains(Entry.START_WITHDRAWL_AGE.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testRead401kNumberOfWithdrawals() {
    final boolean element = fileContents.contains(Entry.NUMBER_OF_WITHDRAWALS.toString());
    assertTrue("Didn't find text", element);
  }
    
  @Test
  public void testRead403bInput() {
    final boolean element = fileContents.contains(Header.ACCOUNT_403B.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadBrokerageInput() {
    final boolean element = fileContents.contains(Header.BROKERAGE.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadBrokerageBalance() {
    final boolean element = fileContents.contains(Entry.CURRENT_BALANCE.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadBrokerageBalanceValue() {
    final boolean element = fileContents.contains("24,000.00");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadBrokerageGrowthRate() {
    final boolean element = fileContents.contains(Entry.ANNUAL_GROWTH_RATE.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadBrokerageGrowthRateValue() {
    final boolean element = fileContents.contains("4.0%");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadCashBalanceInput() {
    final boolean element = fileContents.contains(Header.CASH_BALANCE.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadDeductionsInput() {
    final boolean element = fileContents.contains(Header.DEDUCTIONS.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
    
  @Test
  public void testReadDeductionsDeductions() {
    final boolean element = fileContents.contains(Entry.ANNUAL_DEDUCTIONS.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadDeductionsDeductionsValue() {
    final boolean element = fileContents.contains("6,500.00");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadExpensesInput() {
    final boolean element = fileContents.contains(Header.EXPENSES.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadExpensesExpenses() {
    final boolean element = fileContents.contains(Entry.ANNUAL_EXPENSES.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadExpensesExpensesVale() {
    final boolean element = fileContents.contains("40,000.00");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadRothIraInput() {
    final boolean element = fileContents.contains(Header.ROTH.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadTraditionalIraInput() {
    final boolean element = fileContents.contains(Header.IRA.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadPensionInput() {
    final boolean element = fileContents.contains(Header.PENSION.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadPensionStartingAge() {
    final boolean element = fileContents.contains(Entry.STARTING_AGE.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadPensionMonthlyAmount() {
    final boolean element = fileContents.contains(Entry.MONTHLY_AMOUNT.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadPensionInflationAdjusted() {
    final boolean element = fileContents.contains(Entry.INFLATION_ADJUSTED.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadPersonalInput() {
    final boolean element = fileContents.contains(Header.PERSONAL.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
    
  @Test
  public void testReadPersonalSimulationDate() {
    final boolean element = fileContents.contains(Entry.SIMULATION_DATE.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadPersonalBirthDate() {
    final boolean element = fileContents.contains(Entry.BIRTH_DATE.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadPersonalRetirementAge() {
    final boolean element = fileContents.contains(Entry.RETIREMENT_AGE.toString());
    assertTrue("Didn't find text", element);
  }
    
  @Test
  public void testReadPersonalLifeExpectancyAge() {
    final boolean element = fileContents.contains(Entry.LIFE_EXPECTANCY_AGE.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadPersonalInflation() {
    final boolean element = fileContents.contains(Entry.INFLATION.toString());
    assertTrue("Didn't find text", element);
  }
 
  @Test
  public void testReadSalaryInput() {
    final boolean element = fileContents.contains(Header.SALARY.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
    
  @Test
  public void testReadSalaryCurrentSalary() {
    final boolean element = fileContents.contains(Entry.CURRENT_SALARY.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadSalaryAverageMeritIncrease() {
    final boolean element = fileContents.contains(Entry.AVERAGE_MERIT_INCREASE.toString());
    assertTrue("Didn't find text", element);
  }

  @Test
  public void testReadSavingsInput() {
    final boolean element = fileContents.contains(Header.SAVINGS.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadSavingsBalance() {
    final boolean element = fileContents.contains(Entry.CURRENT_BALANCE.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadSavingsGrowthRate() {
    final boolean element = fileContents.contains(Entry.ANNUAL_GROWTH_RATE.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadSocialSecurityInput() {
    final boolean element = fileContents.contains(Header.SOCIAL_SECURITY.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadSocialSecurityStartingAge() {
    final boolean element = fileContents.contains(Entry.STARTING_AGE.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadSocialSecurityMonthlyAmount() {
    final boolean element = fileContents.contains(Entry.MONTHLY_AMOUNT.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadTaxesInput() {
    final boolean element = fileContents.contains(Header.TAXES.toString() + " Input");
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadTaxesFederalTaxRate() {
    final boolean element = fileContents.contains(Entry.EFFECTIVE_FEDERAL_TAX_RATE.toString());
    assertTrue("Didn't find text", element);
  }
  
  @Test
  public void testReadTaxesStateTaxRate() {
    final boolean element = fileContents.contains(Entry.EFFECTIVE_STATE_TAX_RATE.toString());
    assertTrue("Didn't find text", element);
  }
 }
