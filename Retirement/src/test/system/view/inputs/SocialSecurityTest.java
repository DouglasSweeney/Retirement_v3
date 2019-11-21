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
import application.view.inputs.SocialSecurity;

public class SocialSecurityTest {
  private static final double EPSILON = 0.01;
  
  /**
   * Declare the Software Under Test (SUT).
   * 
   * @var SocialSecurity socialSecurity
   */

  private transient SocialSecurity socialSecurity;

  /** 
   * Do this method before each test.
   * 
   * @since  1.0
   */
  @Before
  public void setUp() throws InterruptedException {
    final XmlReader xmlReader = new XmlReader(); 
    xmlReader.create("input_xmls/test.xml");
    
    socialSecurity = new SocialSecurity(xmlReader);
  }

 /** 
  * Ensure that the input 401K tab is created.
  * 
  * @since  1.0
  */
  @Test
  public void ensureTheInputTabIsCreated() {
    
    final JPanel jPanel = socialSecurity.createPanel();
    
    assertNotNull("", jPanel);    
  }  
  
  /** 
   * Ensure the name is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void ensureGetNameIsAccurate() {
    
    final String result = socialSecurity.getName();
    
    assertEquals("", result, "Social Security");
    
  }  
  
  /** 
   * Ensure the start age is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetStartAge() {
    
   socialSecurity.createPanel();
   
   final int balance = socialSecurity.getStartAge();
    
    assertEquals("", balance, 70);    
  }
 
  /** 
   * Ensure the monthly amount is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetMonthlyAmount() {
    
   socialSecurity.createPanel();
   
   final double balance = socialSecurity.getMonthlyAmount();
    
    assertEquals("", balance, 2_900.0, EPSILON);    
  }
 
  /** 
   * Ensure the output xml file has the enclosing element.
   * 
   * @since  1.0
   */
  @Test
  public void checkEnclosingElementInXml() {
   socialSecurity.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   socialSecurity.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "SocialSecurity");
   assertTrue("", enclosingElement);
   
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the start age.
   * 
   * @since  1.0
   */
  @Test
  public void saveStartAgeToXml() {
   socialSecurity.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   socialSecurity.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "SocialSecurity");
   assertTrue("", enclosingElement);
   
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the start age.
   * 
   * @since  1.0
   */
  @Test
  public void saveStartAgeToXml2() {
   socialSecurity.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   socialSecurity.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       SocialSecurity.START_AGE_PROPERTY,
       "integerValue", "70");
   assertTrue("", balanceAttribute);
   
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the start age.
   * 
   * @since  1.0
   */
  @Test
  public void saveMonthlyAmountToXml() {
   socialSecurity.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   socialSecurity.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       SocialSecurity.MONTHLY_AMOUNT_PROPERTY,
       "doubleValue", "2900.0");
   assertTrue("", balanceAttribute);
   
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the output xml file has the start age.
   * 
   * @since  1.0
   */
  @Test
  public void saveMonthlyAmountToXml3() {
   socialSecurity.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   socialSecurity.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "SocialSecurity");
   assertTrue("", enclosingElement);
   
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
   
  }
  
  /** 
   * Ensure the output xml file has the start age.
   * 
   * @since  1.0
   */
  @Test
  public void saveMonthlyAmountToXml2() {
   socialSecurity.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   socialSecurity.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       SocialSecurity.MONTHLY_AMOUNT_PROPERTY,
       "doubleValue", "2900.0");
   assertTrue("", balanceAttribute);
   
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
  
  /** 
   * Ensure the field has the verifier in it.
   * 
   * @since  1.0
   */
 @Test
  public void setMonthlyAmountToCheckOutVerifier() {
   socialSecurity.createPanel();
   
   socialSecurity.setMonthlyAmount("0a.00");

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
  public void monthlyAmountHasFocusListener() {
   socialSecurity.createPanel();
   
   assertTrue("", socialSecurity.monthlyAmountHasFocusListener());
  }
}
