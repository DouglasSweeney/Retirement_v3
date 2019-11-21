package test.system.view.inputs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import application.main.XmlReader;
import application.main.XmlWriter;
import application.view.inputs.MyJDialog;
import application.view.inputs.Savings;

public class SavingsTest {
  private static final double EPSILON = 0.01;
  
  /**
   * Declare the Software Under Test (SUT).
   * 
   * @var Savings savings
   */

  private transient Savings savings;
  
   /** 
   * Ensure the field has the verifier in it.
   * 
   * @since  1.0
   */
  @Test
  public void setGrowthRateToCheckOutVerifier() {
   savings.createPanel();
   
   savings.setBalance("0b.00");

   final MyJDialog myJDialog = new MyJDialog();
   assertTrue("", myJDialog.isInvalidNumberDialogShown());
   
   myJDialog.doClick();
  }

  /** 
   * Do this method before each test.
   * 
   * @since  1.0
   */
  @Before
  public void setUp() throws InterruptedException {
    final XmlReader xmlReader = new XmlReader(); 

    xmlReader.create("input_xmls/test.xml");
    savings = new Savings(xmlReader);
  }

 /** 
  * Ensure that the input 401K tab is created.
  * 
  * @since  1.0
  */
  @Test
  public void ensureTheInputTabIsCreated() {
    
    final JPanel jPanel = savings.createPanel();
    
    assertNotNull("", jPanel);    
  }  
  /** 
   * Ensure the name is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void ensureGetNameIsAccurate() {
    
    final String result = savings.getName();
    
    assertEquals("", result, "Savings");
    
  }  
  
  /** 
   * Ensure the balance is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetBalance() {
    
   savings.createPanel();
   
   final double balance = savings.getBalance();
    
    assertEquals("", balance, 55_556.00, EPSILON);    
  }
 
  /** 
   * Ensure the growth rate is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetGrowthRate() {
    
    savings.createPanel();
    
    final double growthRate = savings.getGrowthRate();
    
    assertEquals("", growthRate, 0.01, EPSILON);    
  }
  
  /** 
   * Ensure the output xml file has the enclosing element.
   * 
   * @since  1.0
   */
  @Test
  public void checkEnclosingElementInXml() {
   savings.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   savings.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Savings");
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
   savings.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   savings.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Savings");
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
   savings.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   savings.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       Savings.BALANCE_PROPERTY,
       "doubleValue", "55556.0");
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
   savings.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   savings.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
    
   // Check for the attribute
   final boolean growthRateAttribute = testUtils.findElementAndAttribute(fileContents, 
       Savings.GROWTH_RATE_PROPERTY,
       "doubleValue", "0.01");
   assertTrue("", growthRateAttribute);

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
   savings.createPanel();
   
   savings.setBalance("0a.00");

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
   savings.createPanel();
   
   assertTrue("", savings.balanceHasFocusListener());
  }
}
