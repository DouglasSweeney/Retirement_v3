package test.system.view.inputs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.awt.GridLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import org.hamcrest.number.IsCloseTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import application.main.XmlReader;
import application.main.XmlWriter;
import application.view.inputs.Account401K;
import application.view.inputs.Focus;
import application.view.inputs.MyJDialog;
import application.view.inputs.Utils;
import application.view.inputs.Verifier;

@RunWith(MockitoJUnitRunner.class)
public class Account401kTest {
  
	private static final double EPSILON = 0.01;
  /**
   * Declare the Software Under Test (SUT).
   * 
   * @var Account401K account401k
   */

  private transient Account401K account401k;
  private transient XmlReader xmlReader;
  
  /**
   * Declare a mock variable.
   * 
   * @var XmlReader xmlReader
   */
//  private static XmlReader mockXmlReader = mock(XmlReader.class);
  
  /**
   * Declare a mock variable.
   * 
   * @var Account401K mock401k 
   */
//  private static Account401K mock401k = mock(Account401K.class);
  
  /**
   * Declare a mock variable.
   * 
   * @var JFormattedTextField mock401kBalance
   */  
private static JFormattedTextField account401kBalance;
  /**
   * Create an instance of the UTILS.
   * 
   * @var Utils UTILS
   */
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
    account401k = new Account401K(xmlReader);
  }

  private void createJPanel() {
    account401k.createPanel(xmlReader, Account401K.BALANCE_PROPERTY, 
        Account401K.GROWTH_RATE_PROPERTY, Account401K.ANNUAL_CONTRIBUTIONS_PROPERTY, 
        Account401K.ONLY_WHILE_SALARY_PROPERTY, Account401K.START_WITHDRAWAL_AGE_PROPERTY, 
        Account401K.NUMBER_OF_WITHDRAWALS_PROPERTY);
  }
  
  private void saveProperties() {
    final XmlWriter xmlWriter = new XmlWriter(); 
    xmlWriter.save(TestUtils.FILENAME_TEMP99);
    
    account401k.saveProperties(xmlWriter, Account401K.BALANCE_PROPERTY, 
        Account401K.GROWTH_RATE_PROPERTY, Account401K.ANNUAL_CONTRIBUTIONS_PROPERTY, 
        Account401K.ONLY_WHILE_SALARY_PROPERTY, Account401K.START_WITHDRAWAL_AGE_PROPERTY, 
        Account401K.NUMBER_OF_WITHDRAWALS_PROPERTY);
  }
 /** 
  * Ensure that the input 401K tab is created.
  * 
  * @since  1.0
  */
  @Test
  public void ensureTheInputTabIsCreated() {
    
    createJPanel();
    
    assertNotNull("ensureTheInputTabIsCreated()", account401k);    
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
    
    assertEquals("ensureTheBalanceFieldIsCreated()", jPanel.getComponentCount(), 1);
    
  }  
  
  /** 
   * Ensure the name is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void ensureGetNameIsAccurate() {
    
    final String result = account401k.getName();
    
    assertEquals("ensureGetNameIsAccurate()", "401K", result);
    
  }  
  
  /** 
   * Ensure the balance is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetBalance() {
    
    createJPanel();
   
    final double balance = account401k.getBalance();
    
    assertThat("testGetBalance()", balance, new IsCloseTo(354_001.00, EPSILON));    
  }
 
  /** 
   * Ensure the growth rate is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetGrowthRate() {
    
    createJPanel();
    
    final double growthRate = account401k.getGrowthRate();
    
    assertThat("testGetGrowthRate()", growthRate, new IsCloseTo(0.04, EPSILON));    
  }
  
  /** 
   * Ensure the annual contributions are retrieved properly.
   * 
   * @since  1.0
   */
 @Test
  public void testGetAnnualContributions() {
    
   createJPanel();
    
    final double growthRate = account401k.getAnnualContributions();
    
    assertThat("testGetAnnualContributions()", growthRate, new IsCloseTo(5.0, EPSILON));    
  }

 /** 
  * Ensure the start withdrawal age is retrieved properly.
  * 
  * @since  1.0
  */
  @Test
  public void testGetStartWithdrawalAge() {
    
    createJPanel();
    
    final int age = account401k.getStartWithdrawalAge();
    
    assertEquals("testGetStartWithdrawalsAge()", age, 58);
  }

  /** 
   * Ensure the number of withdrawals are retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetNumberOfWithdrawals() {
    
    createJPanel();
    
    final int withdrawals = account401k.getNumberOfWithdrawals();
    
    assertEquals("testGetNumberOfWithdrawls|", withdrawals, 12);    
  }

  /** 
   * Ensure the output xml file has the enclosing element.
   * 
   * @since  1.0
   */
  @Test
  public void checkEnclosingElementInXml() {
   createJPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Account401K");
   assertTrue("checkEnclosingElementInXml()", enclosingElement);
   
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the balance.
   * 
   * @since  1.0
   */
  @Test
  public void testSaveBalanceToXml() {
   createJPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Account401K");
   assertTrue("testSaveBalanceToXml()", enclosingElement);
  }
  
 
  /** 
   * Ensure the output xml file has the balance.
   * 
   * @since  1.0
   */
  @Test
  public void testSaveBalanceToXml2() {
   createJPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   

   // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       Account401K.BALANCE_PROPERTY,
       "doubleValue", "354001.0");
   assertTrue("testSaveBalanceToXml2()", balanceAttribute);
   
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the growth rate.
   * 
   * @since  1.0
   */
  @Test
  public void testSsaveGrowthRateToXml() {
   createJPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
    
   // Check for the attribute
   final boolean growthRateAttribute = testUtils.findElementAndAttribute(fileContents, 
       Account401K.GROWTH_RATE_PROPERTY,
       "doubleValue", "0.04");
   assertTrue("testSaveGrowthToXml()", growthRateAttribute);


   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the annual contributions.
   * 
   * @since  1.0
   */
  @Test
  public void testSaveAnnualContributionToXml() {
   createJPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean annualContributionsAttribute = testUtils.findElementAndAttribute(fileContents, 
       Account401K.ANNUAL_CONTRIBUTIONS_PROPERTY,
       "doubleValue", "5.0");
   assertTrue("testSaveAnnualContributions()", annualContributionsAttribute);
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the check box.
   * 
   * @since  1.0
   */
  @Test
  public void testSaveOnlyWhileSalaryToXml() {
   createJPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean onlyWhileSalaryAttribute = testUtils.findElementAndAttribute(fileContents, 
       Account401K.ONLY_WHILE_SALARY_PROPERTY,
       "booleanValue", "true");
   assertTrue("testSaveOnlyWhileSalaryToXml()", onlyWhileSalaryAttribute);
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the start withdrawal age.
   * 
   * @since  1.0
   */
  @Test
  public void testSaveStartWithdrawalAgeToXml() {
   createJPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean startWithdrawalAgeAttribute = testUtils.findElementAndAttribute(fileContents, 
       Account401K.START_WITHDRAWAL_AGE_PROPERTY,
       "integerValue", "58");
   assertTrue("testSaveStartWithdrawlAgeToXml()", startWithdrawalAgeAttribute);

   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the number of withdawals.
   * 
   * @since  1.0
   */
  @Test
  public void testSaveNumberOfWithdrawalsToXml() {
   createJPanel();
   final TestUtils testUtils = new TestUtils();
   
   // Save the application properties
   saveProperties();
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean numberOfWithdrawalsAttribute = testUtils.findElementAndAttribute(fileContents, 
       Account401K.NUMBER_OF_WITHDRAWALS_PROPERTY,
       "integerValue", "12");
   assertTrue("testSaveNumberOfWithdrawalsToXml()", numberOfWithdrawalsAttribute);
   
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the field has the verifier in it.
   * 
   * @since  1.0
   */
 @Test
  public void testSetBalanceToCheckOutVerifier() {
   createJPanel();
   
   account401k.setBalance("0a.00");

   final MyJDialog myJDialog = new MyJDialog();
   assertTrue("testSetBalanceCheckoutVerifier()", myJDialog.isInvalidNumberDialogShown());
   
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
   createJPanel();
   
   assertTrue("balanceHasFocusListener()", account401k.balanceHasFocusListener());
  }
   
  /** 
   * Ensure the field has the verifier in it.
   * 
   * @since  1.0
   */
  @Test
  public void testSetGrowthRateToCheckoutVerifier() {
   createJPanel();
   
   account401k.setBalance("0b.00");

   final MyJDialog myJDialog = new MyJDialog();
   assertTrue("testSetGrowthRateToCheckoutVerifier()", myJDialog.isInvalidNumberDialogShown());
   
   myJDialog.doClick();
  }
   
  /** 
   * Ensure the field has the verifier in it.
   * 
   * @since  1.0
   */
  @Test
  public void setAnnualContributionsToCheckOutVerifier() {
   createJPanel();
   
   account401k.setAnnualContribution("0c.00");

   final MyJDialog myJDialog = new MyJDialog();
   assertTrue("setAnnualContributionsToCheckOutVerifier()", myJDialog.isInvalidNumberDialogShown());
   
   myJDialog.doClick();
  }
   
  /** 
   * Ensure the field has the focus listener in it.
   * 
   * @since  1.0
   */
  @Test
  public void annualContributionHasFocusListener() {
   createJPanel();
   
   assertTrue("annualContributionHasFocusListener()", account401k.annualContributionHasFocusListener());
  }
}
