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
import application.view.inputs.Deductions;
import application.view.inputs.Focus;
import application.view.inputs.MyJDialog;
import application.view.inputs.Utils;
import application.view.inputs.Verifier;

public class DeductionsTest {
	private static final double EPSILON = 0.01;
  
  /**
   * Declare the Software Under Test (SUT).
   * 
   * @var Deductions deductions
   */

  private transient Deductions deductions;
  
  private transient XmlReader xmlReader;
  
  
  /**
   * Declare a variable.
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
     
    xmlReader = new XmlReader(); 

    xmlReader.create("input_xmls/test.xml");
    deductions = new Deductions(xmlReader);
  }

 /** 
  * Ensure that the input 401K tab is created.
  * 
  * @since  1.0
  */
  @Test
  public void ensureTheInputTabIsCreated() {
    
    final JPanel jPanel = deductions.createPanel();
    
    assertNotNull("", jPanel);    
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
    
    final String result = deductions.getName();
    
    assertEquals("", "Deductions", result);
    
  }  
  
  /** 
   * Ensure the balance is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetDeductions() {
    
   deductions.createPanel();
   
   final double balance = deductions.getDeductions();
    
    assertEquals("", balance, 6_500.00, EPSILON);    
  }
 
  /** 
   * Ensure the output xml file has the enclosing element.
   * 
   * @since  1.0
   */
  @Test
  public void checkEnclosingElementInXml() {
   deductions.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   deductions.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Deductions");
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
   deductions.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   deductions.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Deductions");
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
   deductions.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   deductions.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       Deductions.DEDUCTIONS_PROPERTY,
       "doubleValue", "6500.0");
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
  public void setDeductionsToCheckOutVerifier() {
   deductions.createPanel();
   
   deductions.setDeductions("0a.00");

   final MyJDialog myJDialog = new MyJDialog();
   assertTrue("JDialog6", myJDialog.isInvalidNumberDialogShown());
   
   // Remove the invalid Number Dialog
   myJDialog.doClick();
  }   

 /** 
  * Ensure the field has the focus listener in it.
  * 
  * @since  1.0
  */   
  @Test
  public void deductionsHasFocusListener() {
   deductions.createPanel();
   
   assertTrue("", deductions.deductionsHasFocusListener());
  }
}
