package test.system.view.inputs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.GridLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import application.main.XmlReader;
import application.main.XmlWriter;
import application.view.inputs.Account403B;
import application.view.inputs.Focus;
import application.view.inputs.MyJDialog;
import application.view.inputs.Utils;
import application.view.inputs.Verifier;

@RunWith(MockitoJUnitRunner.class)
public class Account403bTest {
  private static final double EPSILON = 0.01;
  /**
   * Declare the Software Under Test (SUT).
   * 
   * @var Account403B Account403B
   */

  private transient Account403B account403b;
  
  /**
   * Declare a mock variable.
   * 
   * @var XmlReader xmlReader
   */
//  private static XmlReader mockXmlReader = mock(XmlReader.class);
  
  /**
   * Declare a xml reader.
   * 
   * @var XmlReader xmlReader
   */
  private static XmlReader xmlReader;
  
  /**
   * Declare a mock variable.
   * 
   * @var XmlWriter xmlWriter
   */
//  private static Account403B mock403b = mock(Account403B.class);
  
  /**
   * Declare a mock variable.
   * 
   * @var JFormattedTextField mock401kBalance
   */  
//  private static JFormattedTextField mock401kBalance = 
//      mock(JFormattedTextField.class);
  private static JFormattedTextField account403bBalance; 
 
  /**
   * Create an instance of the UTILS.
   * 
   * @var Utils UTILS
   */
//  private static final Utils UTILS = mock(Utils.class);
  private static final Utils UTILS = new Utils();


  /**
   * Set the input field verifier.
   * 
   * @var Verifier verifier
   */
  private static final Verifier VERIFIER = new Verifier();
    
  /**
   * Create an instance of FOCUS.
   * 
   * @var Focus FOCUS
   */
  private static final Focus FOCUS = new Focus();

  /** 
   * Do this method before each test.
   * 
   * @since  1.0
   */
  @Before
  public void setUp() throws InterruptedException {
//    initMocks(this);

//    when(mock403b.getName()).thenReturn("403B");
    
    xmlReader = new XmlReader(); 
    xmlReader.create("input_xmls/test.xml");
    
    account403b = new Account403B(xmlReader);
  }

  private void createPanel() {
    account403b.createPanel(xmlReader, Account403B.BALANCE_PROPERTY, 
        Account403B.GROWTH_RATE_PROPERTY, Account403B.ANNUAL_CONTRIBUTIONS_PROPERTY, 
        Account403B.ONLY_WHILE_SALARY_PROPERTY, Account403B.START_WITHDRAWAL_AGE_PROPERTY, 
        Account403B.NUMBER_OF_WITHDRAWALS_PROPERTY);
  }
  
  private void saveProperties() {
    final XmlWriter xmlWriter = new XmlWriter(); 
    xmlWriter.save(TestUtils.FILENAME_TEMP99);
    
    account403b.saveProperties(xmlWriter, Account403B.BALANCE_PROPERTY, 
        Account403B.GROWTH_RATE_PROPERTY, Account403B.ANNUAL_CONTRIBUTIONS_PROPERTY, 
        Account403B.ONLY_WHILE_SALARY_PROPERTY, Account403B.START_WITHDRAWAL_AGE_PROPERTY, 
        Account403B.NUMBER_OF_WITHDRAWALS_PROPERTY);
  }
 /** 
  * Ensure that the input 401K tab is created.
  * 
  * @since  1.0
  */
  @Test
  public void ensureTheInputTabIsCreated() {
    
    createPanel();
    
    assertNotNull("assetNull(account403b)", account403b);    
  }  

  /** 
   * Example on how to use mocks.
   * 
   * @since  1.0
   */
    @Test
  public void ensureTheBalanceFieldIsCreated() {
//    when (xmlReader.getDoubleProperty(anyString())).thenReturn(1.00);
//    when (UTILS.getDollarFormat(anyInt())).thenReturn("1.00");
//    verify (mock401kBalance, times(1)).setText(anyString());
    
    final JPanel jPanel = new JPanel(new GridLayout(1, 2));
    
    account403bBalance = new JFormattedTextField();
    account403bBalance.setText(UTILS.getDollarFormat(xmlReader.getDoubleProperty("Test")));
    account403bBalance.setName(UTILS.getCurrencyName());
    account403bBalance.setInputVerifier(VERIFIER);
    account403bBalance.addFocusListener(FOCUS);
    jPanel.add(account403bBalance);
 
    assertNotNull("ensureTheBalanceFieldIsCreated()", jPanel);
    
  }  
  
  /** 
   * Ensure the name is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void ensureGetNameIsAccurate() {
    
    final String result = account403b.getName();
    
    assertEquals("ensureTGetNameIsAccurate()", "403B", result);
    
  }  
  
  /** 
   * Ensure the balance is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetBalance() {
    
   createPanel();
   
   final double balance = account403b.getBalance();
    
    assertEquals("", balance, 255_000.00, EPSILON);    
  }
 
  /** 
   * Ensure the growth rate is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetGrowthRate() {
    
    createPanel();
    
    final double growthRate = account403b.getGrowthRate();
    
    assertEquals("", growthRate, 0.04, EPSILON);    
  }
  
  /** 
   * Ensure the annual contributions are retrieved properly.
   * 
   * @since  1.0
   */
 @Test
  public void testGetAnnualContributions() {
    
    createPanel();
    
    final double growthRate = account403b.getAnnualContributions();
    
    assertEquals("", growthRate, 0.0, EPSILON);    
  }

 /** 
  * Ensure the start withdrawal age is retrieved properly.
  * 
  * @since  1.0
  */
  @Test
  public void testGetStartWithdrawalAge() {
    
    createPanel();
    
    final int age = account403b.getStartWithdrawalAge();
    
    assertEquals("", age, 59);
  }

  /** 
   * Ensure the number of withdrawals are retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetNumberOfWithdrawals() {
    
    createPanel();
    
    final int withdrawals = account403b.getNumberOfWithdrawals();
    
    assertEquals("", withdrawals, 0);    
  }

  /** 
   * Ensure the output xml file has the enclosing element.
   * 
   * @since  1.0
   */
  @Test
  public void checkEnclosingElementInXml() {
   createPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Account403B");
   assertTrue("", enclosingElement);
   
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the balance.
   * 
   * @since  1.0
   */
  @Test
  public void saveBalanceToXml() {
   createPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Account403B");
   assertTrue("", enclosingElement);

   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the balance.
   * 
   * @since  1.0
   */
  @Test
  public void saveBalanceToXml2() {
   createPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       Account403B.BALANCE_PROPERTY,
       "doubleValue", "255000.0");
   assertTrue("assetTrue", balanceAttribute);
   
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the growth rate.
   * 
   * @since  1.0
   */
  @Test
  public void saveGrowthRateToXml() {
   createPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
    
   // Check for the attribute
   final boolean growthRateAttribute = testUtils.findElementAndAttribute(fileContents, 
       Account403B.GROWTH_RATE_PROPERTY,
       "doubleValue", "0.04");
   assertTrue("", growthRateAttribute);

   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the annual contributions.
   * 
   * @since  1.0
   */
  @Test
  public void saveAnnualContributionToXml() {
   createPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean annualContributionsAttribute = testUtils.findElementAndAttribute(fileContents, 
       Account403B.ANNUAL_CONTRIBUTIONS_PROPERTY,
       "doubleValue", "0.0");
   assertTrue("", annualContributionsAttribute);
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the check box.
   * 
   * @since  1.0
   */
  @Test
  public void saveOnlyWhileSalaryToXml() {
   createPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean onlyWhileSalaryAttribute = testUtils.findElementAndAttribute(fileContents, 
       Account403B.ONLY_WHILE_SALARY_PROPERTY,
       "booleanValue", "false");
   assertTrue("", onlyWhileSalaryAttribute);
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the start withdrawal age.
   * 
   * @since  1.0
   */
  @Test
  public void saveStartWithdrawalAgeToXml() {
   createPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean startWithdrawalAgeAttribute = testUtils.findElementAndAttribute(fileContents, 
       Account403B.START_WITHDRAWAL_AGE_PROPERTY,
       "integerValue", "59");
   assertTrue("", startWithdrawalAgeAttribute);

   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the number of withdawals.
   * 
   * @since  1.0
   */
  @Test
  public void saveNumberOfWithdrawalsToXml() {
   createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean numberOfWithdrawalsAttribute = testUtils.findElementAndAttribute(fileContents, 
       Account403B.NUMBER_OF_WITHDRAWALS_PROPERTY,
       "integerValue", "0");
   assertTrue("", numberOfWithdrawalsAttribute);
   
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
/** 
   * Ensure the field has the verifier in it.
   * 
   * @since  1.0
   */
 @Test
  public void setBalanceToCheckOutVerifier() {
   createPanel();
   
   account403b.setBalance("0a.00");

   final MyJDialog myJDialog = new MyJDialog();
   assertTrue("", myJDialog.isInvalidNumberDialogShown());
   
   // Remove the invalid Number Dialog
   myJDialog.doClick();
  }   

 /** 
  * Ensure the field has the focus listener in it.
  * 
  * @since  1.0
  */   
  @Test
  public void balanceHasFocusListener() {
   createPanel();
   
   assertTrue("", account403b.balanceHasFocusListener());
  }
   
  /** 
   * Ensure the field has the verifier in it.
   * 
   * @since  1.0
   */
  @Test
  public void setGrowthRateToCheckOutVerifier() {
   createPanel();
   
   account403b.setBalance("0b.00");

   final MyJDialog myJDialog = new MyJDialog();
   assertTrue("", myJDialog.isInvalidNumberDialogShown());
   
   myJDialog.doClick();
  }
   
  /** 
   * Ensure the field has the verifier in it.
   * 
   * @since  1.0
   */
  @Test
  public void setAnnualContributionsToCheckOutVerifier() {
   createPanel();
   
   account403b.setAnnualContribution("0c.00");

   final MyJDialog myJDialog = new MyJDialog();
   assertTrue("", myJDialog.isInvalidNumberDialogShown());
   
   myJDialog.doClick();
  }
   
  /** 
   * Ensure the field has the focus listener in it.
   * 
   * @since  1.0
   */
  @Test
  public void annualContributionHasFocusListener() {
   createPanel();
   
   assertTrue("", account403b.annualContributionHasFocusListener());
  }
}
