package application.main;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import application.system.ApplicationLogger;

/**
 * Gets the inputs from the XML file.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class XmlReader {
  
  /**
   * Get an instance of the DocumentBuilderFactory.
   * 
   * @var DocumentBuilderFactory FACTORY
   */
  private static final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();
  
  /**
   * Write out trace execution methods via this variable.
   * 
   * @var ApplicationLogger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();
  
  /**
   * Create a variable of the Document (based on DOM).
   * 
   * @var Document document
   */

  private static Document document;
  /** 
   * Create a DOM tree.
   * 
   * @param  String filename
   * @since  1.0
   * 
   * @return validity
   */
  public boolean create(final String filename) {
    DocumentBuilder parser = null;
    boolean valid = false;
    try {
      parser = FACTORY.newDocumentBuilder();
      valid = true;
    } catch (ParserConfigurationException e) {
       LOGGER.trace(e.getMessage());
    }
    
    if (valid && parser != null) {
      try {
        document = parser.parse(filename);
        valid = true;
      } catch (SAXException | IOException e) {
        valid = false;
        LOGGER.trace(e.getMessage());
      } 
    }
    
    return valid;
  }
    
  /** 
   * Get the doubleValue attribute.
   * 
   * @param  String fieldText
   * @since  1.0
   * 
   * @return The value in the XML
   */
  public double getDoubleProperty(final String fieldText) {
    Double returnValue = 987.0;
    
    final NodeList inventory2 = document.getElementsByTagName(fieldText);
    if (inventory2.getLength() == 1) {
      final NamedNodeMap map = inventory2.item(0).getAttributes();
      for (int l = 0; l < map.getLength(); l++) {
        if (map.item(l).getNodeName().equals("doubleValue")) {
          returnValue = Double.valueOf(map.item(l).getNodeValue());          
        }
      }
    }
    
    return returnValue;
  }
  
  /** 
   * Get the integerValue attribute.
   * 
   * @param  String fieldText
   * @since  1.0
   * 
   * @return The value in the XML
   */  
  public int getIntegerProperty(final String fieldText) {
    int returnValue = 987;
    final NodeList inventory2 = document.getElementsByTagName(fieldText);
    if (inventory2.getLength() == 1) {
      final NamedNodeMap map = inventory2.item(0).getAttributes();
      for (int l = 0; l < map.getLength(); l++) {
        if (map.item(l).getNodeName().equals("integerValue")) {
          returnValue = Integer.valueOf(map.item(l).getNodeValue());
        }
      }
    }
    
    return returnValue;
  }
  
  /** 
   * Get the booleanValue attribute.
   * 
   * @param  String fieldText
   * @since  1.0
   * 
   * @return The value in the XML
   */  
  public boolean getBooleanProperty(final String fieldText) {
    boolean returnValue = false;
    final NodeList inventory2 = document.getElementsByTagName(fieldText);
    
    if (inventory2.getLength() == 1) {
      final NamedNodeMap map = inventory2.item(0).getAttributes();
      for (int l = 0; l < map.getLength(); l++) {
        if (map.item(l).getNodeName().equals("booleanValue")) {
          returnValue = Boolean.valueOf(map.item(l).getNodeValue());
        }
      }
    }
    
    return returnValue;
  }
  
  /** 
   * Get all the attributes for the object
   * 
   * @since  1.0
   */  
  public void readAccount401k() {
    double doubleValue;
    int    integerValue;
    
    System.out.println("401K");
    
    doubleValue = getDoubleProperty("account401kAnnualContributions");
    System.out.println("doubleValue (SB 0.0): " + doubleValue);
    
    doubleValue = getDoubleProperty("account401kBalance");
    System.out.println("doubleValue (SB 354000.0): " + doubleValue);
    
    doubleValue = getDoubleProperty("account401kGrowthRate");
    System.out.println("doubleValue (SB 0.04): " + doubleValue);
    
    integerValue = getIntegerProperty("account401kNumberOfWithdrawals");
    System.out.println("integerValue (SB 12): " + integerValue);
    
    integerValue = getIntegerProperty("account401kStartWithdrawalAge");
    System.out.println("integerValue (SB 58): " + integerValue);
  
  }
  
  /** 
   * Get all the attributes for the object
   * 
   * @since  1.0
   */  
  public void readAccount403b() {    
    System.out.println("403B");
    
    double doubleValue = getDoubleProperty("account403BAnnualContributions");
    System.out.println("doubleValue (SB 0.0): " + doubleValue);
    
    doubleValue = getDoubleProperty("account403BBalance");
    System.out.println("doubleValue (SB 255000.0): " + doubleValue);
    
    doubleValue = getDoubleProperty("account403BGrowthRate");
    System.out.println("doubleValue (SB 0.04): " + doubleValue);
    
    int integerValue = getIntegerProperty("account403BNumberOfWithdrawals");
    System.out.println("integerValue (SB 0): " + integerValue);
    
    integerValue = getIntegerProperty("account403BStartWithdrawalAge");
    System.out.println("integerValue (SB 59): " + integerValue);  
  }

  /** 
   * Get all the attributes for the object
   * 
   * @since  1.0
   */  
  public void readAccountCashBalance() {
    double doubleValue;
    int    integerValue;
    
    System.out.println("CashBalance");
    
    doubleValue = getDoubleProperty("accountCashBalanceAnnualContributions");
    System.out.println("doubleValue (SB 0.0): " + doubleValue);
    
    doubleValue = getDoubleProperty("accountCashBalanceBalance");
    System.out.println("doubleValue (SB 105000.0): " + doubleValue);
    
    doubleValue = getDoubleProperty("accountCashBalanceGrowthRate");
    System.out.println("doubleValue (SB 0.04): " + doubleValue);
    
    integerValue = getIntegerProperty("accountCashBalanceNumberOfWithdrawals");
    System.out.println("integerValue (SB 0): " + integerValue);
    
    integerValue = getIntegerProperty("accountCashBalanceStartWithdrawalAge");
    System.out.println("integerValue (SB 59): " + integerValue);
  
  }
  
  /** 
   * Get all the attributes for the object
   * 
   * @since  1.0
   */  
  public void readAccountIra() {
    double doubleValue;
    int    integerValue;
    
    System.out.println("Ira");
    
    doubleValue = getDoubleProperty("accountIraAnnualContributions");
    System.out.println("doubleValue (SB 5500.0): " + doubleValue);
    
    doubleValue = getDoubleProperty("accountIraBalance");
    System.out.println("doubleValue (SB 35000.0): " + doubleValue);
    
    doubleValue = getDoubleProperty("accountIraGrowthRate");
    System.out.println("doubleValue (SB 0.05): " + doubleValue);
    
    integerValue = getIntegerProperty("accountIraNumberOfWithdrawals");
    System.out.println("integerValue (SB 0): " + integerValue);
    
    integerValue = getIntegerProperty("accountIraStartWithdrawalAge");
    System.out.println("integerValue (SB 70): " + integerValue);
  }
  
  /** 
   * Get all the attributes for the object
   * 
   * @since  1.0
   */  
  public void readAccountRoth() {
    double doubleValue;
    int    integerValue;
    
    System.out.println("Roth");
    
    doubleValue = getDoubleProperty("accountRothAnnualContributions");
    System.out.println("doubleValue (SB 0.0): " + doubleValue);
    
    doubleValue = getDoubleProperty("accountRothBalance");
    System.out.println("doubleValue (SB 188000.0): " + doubleValue);
    
    doubleValue = getDoubleProperty("accountRothGrowthRate");
    System.out.println("doubleValue (SB 0.05): " + doubleValue);
    
    integerValue = getIntegerProperty("accountRothNumberOfWithdrawals");
    System.out.println("integerValue (SB 0): " + integerValue);
    
    integerValue = getIntegerProperty("accountRothStartWithdrawalAge");
    System.out.println("integerValue (SB 70): " + integerValue);
  
  } 
   
  /** 
   * Get all the attributes for the object
   * 
   * @since  1.0
   */  
  public void readBrokerage() {
    double doubleValue;
   
    System.out.println("Brokerage");
  
    doubleValue = getDoubleProperty("brokerageGrowthRate");
    System.out.println("doubleValue (SB 0.04): " + doubleValue);
    
    doubleValue = getDoubleProperty("brokerageStartingBalance");
    System.out.println("doubleValue (SB 24000.0): " + doubleValue);
  }
  
  /** 
   * Get all the attributes for the object
   * 
   * @since  1.0
   */  
  public void readDeductions() {
    double doubleValue;
  
    System.out.println("Deductions");
 
    doubleValue = getDoubleProperty("currentDeductions");
    System.out.println("doubleValue (SB 6500.0): " + doubleValue);
  }
 
  /** 
   * Get all the attributes for the object
   * 
   * @since  1.0
   */  
  public void readExpenses() {
    double doubleValue;
 
    System.out.println("Expenses");

    doubleValue = getDoubleProperty("currentExpenses");
    System.out.println("doubleValue (SB 40000.0): " + doubleValue);
  }
 
  /** 
   * Get all the attributes for the object
   * 
   * @since  1.0
   */  
  public void readPension() {
    double  doubleValue;
    int     integerValue;
    boolean booleanValue;
    
    System.out.println("Pension");
    
    doubleValue = getDoubleProperty("pensionAmount");
    System.out.println("doubleValue (SB 20400.0): " + doubleValue);
    
    doubleValue = getDoubleProperty("pensionGrowthRate");
    System.out.println("doubleValue (SB 0.0): " + doubleValue);
    
    booleanValue = getBooleanProperty("pensionInflationAdjusted");
    System.out.println("doubleValue (false): " + booleanValue);
    
    doubleValue = getDoubleProperty("pensionMonthlyAmount");
    System.out.println("integerValue (SB 1760.0): " + doubleValue);
    
    integerValue = getIntegerProperty("pensionStartAge");
    System.out.println("integerValue (SB 55): " + integerValue);  
  } 
  
  /** 
   * Get all the attributes for the object
   * 
   * @since  1.0
   */  
  public void readPersonal() {
    double  doubleValue;
    int     integerValue;
    
    System.out.println("Personal");
    
    integerValue = getIntegerProperty("personalBirthDay");
    System.out.println("integerValue (SB 6): " + integerValue);
    
    integerValue = getIntegerProperty("personalBirthMonth");
    System.out.println("integerValue (SB 9): " + integerValue);
    
    integerValue = getIntegerProperty("personalBirthYear");
    System.out.println("integerValue (SB 1958): " + integerValue);
    
    integerValue = getIntegerProperty("personalCurrentYear");
    System.out.println("integerValue (SB 2016): " + integerValue);
    
    integerValue = getIntegerProperty("personalDeathAge");
    System.out.println("integerValue (SB 95): " + integerValue);
    
    doubleValue = getDoubleProperty("personalInflation");
    System.out.println("integerValue (SB 0.028): " + doubleValue);
    
    integerValue = getIntegerProperty("personalRetirementAge");
    System.out.println("integerValue (SB 55): " + integerValue);    
  } 
  
  /** 
   * Get all the attributes for the object
   * 
   * @since  1.0
   */  
  public void readSalary() {
    double doubleValue;
 
    System.out.println("Salary");

    doubleValue = getDoubleProperty("salaryAnnualSalary");
    System.out.println("doubleValue (SB 50000.0): " + doubleValue);

    doubleValue = getDoubleProperty("salaryMeritIncrease");
    System.out.println("doubleValue (SB 0.025): " + doubleValue);
  }
 
  /** 
   * Get all the attributes for the object
   * 
   * @since  1.0
   */  
  public void readSocialSecurity() {
    double  doubleValue;
    int     integerValue;
    
    System.out.println("Social Security");
    
    doubleValue = getDoubleProperty("socialSecurityMonthlyAmount");
    System.out.println("integerValue (SB 2900.0): " + doubleValue);
    
    integerValue = getIntegerProperty("socialSecurityStartAge");
    System.out.println("integerValue (SB 70): " + integerValue);  
  } 

  /** 
   * Get all the attributes for the object
   * 
   * @since  1.0
   */  
  public void readTaxes() {
    double  doubleValue;
    
    System.out.println("Taxes");
    
    doubleValue = getDoubleProperty("taxRateFederal");
    System.out.println("integerValue (SB 0.16): " + doubleValue);
    
    doubleValue = getDoubleProperty("taxRateState");
    System.out.println("integerValue (SB 0.04): " + doubleValue);
   } 

  /** 
   * The entry point to test this module.
   * 
   * @param String[] args
   * @throws Exception
   * @since  1.0
   */  
  public static void main(final String [] args)
  {
    final XmlReader xmlReader = new XmlReader();
    
    xmlReader.create("input_xmls/demo.xml");
    
    xmlReader.readAccount401k();
    xmlReader.readAccount403b();
    xmlReader.readAccountCashBalance();
    xmlReader.readAccountIra();
    xmlReader.readAccountRoth();
    xmlReader.readBrokerage();
    xmlReader.readDeductions();
    xmlReader.readExpenses();
    xmlReader.readPension();
    xmlReader.readPersonal();
    xmlReader.readSalary();
    xmlReader.readSocialSecurity();
    xmlReader.readTaxes();
  }
}
