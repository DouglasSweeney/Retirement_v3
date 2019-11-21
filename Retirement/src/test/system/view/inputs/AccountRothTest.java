package test.system.view.inputs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.GridLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import application.main.XmlReader;
import application.main.XmlWriter;
import application.view.inputs.AccountRoth;
import application.view.inputs.Focus;
import application.view.inputs.MyJDialog;
import application.view.inputs.Utils;
import application.view.inputs.Verifier;

public class AccountRothTest {
  private static final double EPSILON = 0.01;
  
  /**
   * Declare the Software Under Test (SUT).
   * 
   * @var AccountRoth accountRoth
   */

  private transient AccountRoth accountRoth;
  
  private transient XmlReader xmlReader;
  /**
   * Declare a mock variable.
   * 
   * @var XmlWriter xmlWriter
   */
//  private static AccountRoth mock401k = mock(AccountRoth.class);
  
  /**
   * Declare a mock variable.
   * 
   * @var JFormattedTextField mock401kBalance
   */  
//  private static JFormattedTextField mock401kBalance = 
//     mock(JFormattedTextField.class);
  private static JFormattedTextField account401kBalance; 
  
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

//    when(mock401k.getName()).thenReturn("401K");
    
    xmlReader = new XmlReader(); 
    xmlReader.create("input_xmls/test.xml");
    
    accountRoth = new AccountRoth(xmlReader);
  }

  private void createPanel() {
    accountRoth.createPanel(xmlReader, AccountRoth.BALANCE_PROPERTY, 
      AccountRoth.GROWTH_RATE_PROPERTY, AccountRoth.ANNUAL_CONTRIBUTIONS_PROPERTY, 
      AccountRoth.ONLY_WHILE_SALARY_PROPERTY, AccountRoth.START_WITHDRAWAL_AGE_PROPERTY, 
      AccountRoth.NUMBER_OF_WITHDRAWALS_PROPERTY);
  }

  private void saveProperties() {
    final XmlWriter xmlWriter = new XmlWriter(); 
    xmlWriter.save(TestUtils.FILENAME_TEMP99);
  
    accountRoth.saveProperties(xmlWriter, AccountRoth.BALANCE_PROPERTY, 
      AccountRoth.GROWTH_RATE_PROPERTY, AccountRoth.ANNUAL_CONTRIBUTIONS_PROPERTY, 
      AccountRoth.ONLY_WHILE_SALARY_PROPERTY, AccountRoth.START_WITHDRAWAL_AGE_PROPERTY, 
      AccountRoth.NUMBER_OF_WITHDRAWALS_PROPERTY);
  }
  
  /** 
  * Ensure that the input 401K tab is created.
  * 
  * @since  1.0
  */
  @Test
  public void ensureTheInputTabIsCreated() {
    
    createPanel();
    
    assertNotNull("", accountRoth);    
  }  

  /** 
   * Example on how to use mocks.
   * 
   * @since  1.0
   */
    @Test
  public void ensureTheBalanceFieldIsCreated() {
    final JPanel jPanel = new JPanel(new GridLayout(1, 2));
    
    account401kBalance = new JFormattedTextField();
    account401kBalance.setText(UTILS.getDollarFormat(xmlReader.getDoubleProperty("Test")));
    account401kBalance.setName(UTILS.getCurrencyName());
    account401kBalance.setInputVerifier(VERIFIER);
    account401kBalance.addFocusListener(FOCUS);
    jPanel.add(account401kBalance);
 
    assertEquals("", jPanel.getComponentCount(), 1);
    
  }  
  
  /** 
   * Ensure the name is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void ensureGetNameIsAccurate() {
    
    final String result = accountRoth.getName();
    
    assertEquals("", "Roth", result);
    
  }  
  
  /** 
   * Ensure the balance is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetBalance() {
    
   createPanel();
   
   final double balance = accountRoth.getBalance();
    
    assertEquals("", balance, 188_000.00, EPSILON);    
  }
 
  /** 
   * Ensure the growth rate is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetGrowthRate() {
    
    createPanel();
    
    final double growthRate = accountRoth.getGrowthRate();
    
    assertEquals("", growthRate, 0.05, EPSILON);    
  }
  
  /** 
   * Ensure the annual contributions are retrieved properly.
   * 
   * @since  1.0
   */
 @Test
  public void testGetAnnualContributions() {
    
    createPanel();
    
    final double growthRate = accountRoth.getAnnualContributions();
    
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
    
    final int age = accountRoth.getStartWithdrawalAge();
    
    assertEquals("", age, 70);
  }

  /** 
   * Ensure the number of withdrawals are retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetNumberOfWithdrawals() {
    
    createPanel();
    
    final int withdrawals = accountRoth.getNumberOfWithdrawals();
    
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
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "AccountRoth");
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
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "AccountRoth");
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
       AccountRoth.BALANCE_PROPERTY,
       "doubleValue", "188000.0");
   assertTrue("", balanceAttribute);
   
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
       AccountRoth.GROWTH_RATE_PROPERTY,
       "doubleValue", "0.05");
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
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean annualContributionsAttribute = testUtils.findElementAndAttribute(fileContents, 
       AccountRoth.ANNUAL_CONTRIBUTIONS_PROPERTY,
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
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean onlyWhileSalaryAttribute = testUtils.findElementAndAttribute(fileContents, 
       AccountRoth.ONLY_WHILE_SALARY_PROPERTY,
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
       AccountRoth.START_WITHDRAWAL_AGE_PROPERTY,
       "integerValue", "70");
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
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean numberOfWithdrawalsAttribute = testUtils.findElementAndAttribute(fileContents, 
       AccountRoth.NUMBER_OF_WITHDRAWALS_PROPERTY,
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
   
   accountRoth.setBalance("0a.00");

   final MyJDialog myJDialog = new MyJDialog();
   assertTrue("myJDialog", myJDialog.isInvalidNumberDialogShown());
   
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
   
   assertTrue("", accountRoth.balanceHasFocusListener());
  }
  /** 
   * Ensure the field has the verifier in it.
   * 
   * @since  1.0
   */
  @Test
  public void setAnnualContributionsToCheckOutVerifier() {
   createPanel();
   
   accountRoth.setAnnualContribution("0c.00");

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
   
   assertTrue("", accountRoth.annualContributionHasFocusListener());
  }
 }
