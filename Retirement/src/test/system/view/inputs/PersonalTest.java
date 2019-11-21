package test.system.view.inputs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import application.main.XmlReader;
import application.main.XmlWriter;
import application.view.inputs.Personal;

public class PersonalTest {
  private static final double EPSILON = 0.01;
  
  /**
   * Declare the Software Under Test (SUT).
   * 
   * @var Personal personal
   */

  private transient Personal personal;
  
  /** 
   * Do this method before each test.
   * 
   * @since  1.0
   */
  @Before
  public void setUp() throws InterruptedException {
    final XmlReader xmlReader = new XmlReader(); 

    xmlReader.create("input_xmls/test.xml");
    personal = new Personal(xmlReader);
  }

 /** 
  * Ensure that the input 401K tab is created.
  * 
  * @since  1.0
  */
  @Test
  public void ensureTheInputTabIsCreated() {
    
    final JPanel jPanel = personal.createPanel();
    
    assertNotNull("", jPanel);    
  }  

   /** 
   * Ensure the name is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void ensureGetNameIsAccurate() {
    
    final String result = personal.getName();
    
    assertEquals("", result, "Personal");
    
  }  
  
  /** 
   * Ensure the current year is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetCurrentYear() {
    
   personal.createPanel();
   
   final int balance = personal.getCurrentYear();
    
   final Date date = new Date();
   final Calendar cal = Calendar.getInstance();
   cal.setTime(date);
   
    assertEquals("", balance, cal.get(Calendar.YEAR));    
  }
  
  /** 
   * Ensure the birth day is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetBirthDay() {
    
   personal.createPanel();
   
   final int balance = personal.getBirthDay();
    
    assertEquals("", balance, 6);    
  }
  
  /** 
   * Ensure the birth month is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetBirthMonth() {
    
   personal.createPanel();
   
   final int balance = personal.getBirthMonth();
    
    assertEquals("", balance, 9);    
  }
  
  /** 
   * Ensure the birth year is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetBirthYear() {
    
   personal.createPanel();
   
   final int balance = personal.getBirthYear();
    
    assertEquals("", balance, 1958);    
  }
  
   /** 
   * Ensure the retirement age is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetRetirementAge() {
    
   personal.createPanel();
   
   final int balance = personal.getRetirementAge();
    
    assertEquals("", balance, 65);    
  }
 
  /** 
   * Ensure the life expectancy age is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetLifeExpectancyAge() {
    
   personal.createPanel();
   
   final int balance = personal.getDeathAge();
    
    assertEquals("", balance, 95);    
  }
 
  /** 
   * Ensure the monthly amount is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void testGetInflation() {
    
   personal.createPanel();
   
   final double balance = personal.getInflationRate();
    
    assertEquals("", balance, 0.03, EPSILON);    
  }
 
  /** 
   * Ensure the output xml file has the enclosing element.
   * 
   * @since  1.0
   */
  @Test
  public void checkEnclosingElementInXml() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Personal");
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
  public void saveCurrentYearToXml() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Personal");
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
  public void saveCurrentYearToXml2() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   final Date date = new Date();
   final Calendar cal = Calendar.getInstance();
   cal.setTime(date);
   
   final Integer currentYear = cal.get(Calendar.YEAR);
   // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       Personal.CURRENT_YEAR_PROPERTY,
       "integerValue", currentYear.toString());
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
  public void saveBirthDayToXml() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Personal");
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
  public void saveBirthDayToXml2() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       Personal.BIRTH_DAY_PROPERTY,
       "integerValue", "6");
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
  public void saveBirthMonthToXml() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Personal");
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
  public void saveBirthMonthToXml2() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       Personal.BIRTH_MONTH_PROPERTY,
       "integerValue", "9");
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
  public void saveBirthYearToXml() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Personal");
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
  public void saveBirthYearToXml2() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       Personal.BIRTH_YEAR_PROPERTY,
       "integerValue", "1958");
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
  public void saveRetirementAgeToXml() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Personal");
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
  public void saveRetirementAgeToXml2() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       Personal.RETIREMENT_AGE_PROPERTY,
       "integerValue", "65");
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
  public void saveLifeExpectancyAgeToXml() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Personal");
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
  public void saveLifeExpectancyAgeToXml2() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
    // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       Personal.DEATH_AGE_PROPERTY,
       "integerValue", "95");
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
  public void saveInflationRateToXml() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the enclosing elements
   final boolean enclosingElement = testUtils.findEnclosingElements(fileContents, "Personal");
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
  public void saveInflationRateToXml2() {
   personal.createPanel();
   final TestUtils testUtils = new TestUtils();
   
   final XmlWriter xmlWriter = new XmlWriter(); 
   xmlWriter.save(TestUtils.FILENAME_TEMP99);
   
   // Save the application properties
   personal.saveProperties(xmlWriter);
   
   // Read what was save into a String
   final String fileContents = testUtils.readFileIntoString(TestUtils.FILENAME_TEMP99);
   
   // Check for the attribute
   final boolean balanceAttribute = testUtils.findElementAndAttribute(fileContents, 
       Personal.INFLATION_PROPERTY,
       "doubleValue", "0.03");
   assertTrue("", balanceAttribute);
   
   // Delete the file that was written to
   testUtils.delete(TestUtils.FILENAME_TEMP99);
  }
}
