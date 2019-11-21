package application.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import application.system.ApplicationLogger;

/**
 * Create the the XML file from the input values.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class XmlWriter {
  private static final int NEWLINE = 13;
  
  /**
   * Set the string for indents of the XML file.
   * 
   * @var String INDENT
   */
  private static final String INDENT = "  ";
  
  /**
   * Write out trace execution methods via this variable.
   * 
   * @var ApplicationLogger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();

  /**
   * Set the stream for the XML file.
   * 
   * @var FileOutputStream fileOutputStream
   */
  private transient FileOutputStream fileOutputStream;

  /** 
   * Save the properties to the given filename.
   * 
   * @param  filename     The filename to save
   * 
   * @since  1.0
   * 
   */
  public void save(final String filename) {    
    File file;
    
    try {
      file = new File(filename);
      fileOutputStream = new FileOutputStream(file);
    } catch (FileNotFoundException e) {
        LOGGER.trace(e.getMessage());
    }
  }  

  private void putString(final String element, final boolean printNewlineCharacter) {
    for (int i = 0; i < element.length(); i++) {
      try {
        fileOutputStream.write(element.charAt(i));
      } catch (IOException e) {
          LOGGER.trace(e.getMessage());
      }
    }
    
    if (printNewlineCharacter) {
      try {
        fileOutputStream.write(NEWLINE);
      } catch (IOException e) {
          LOGGER.trace(e.getMessage());
      }
    }
  }
 
  /** 
   * Write the header of the XML output file.
   * 
   * @since  1.0
   * 
   */
  public void putHeader() {
    putString("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", true);
  }
  
  /** 
   * Write the root text of the XML output file.
   * 
   * @since  1.0
   * 
   */
  public void putTopLevelNode() {
    putString("<TopLevel>", true);
  }
    
  
  /** 
   * Write the closing root text of the XML output file.
   * 
   * @since  1.0
   * 
   */
  public void putCloseTopLevelNode() {
    putString("</TopLevel>", true);
  }
  
  /** 
   * Write the node of the XML output file.
   * 
   * @since  1.0
   * 
   */
  public void putNode(final String name) {
    
    for (int i = 0; i < 1; i++) {
      putString(INDENT, false);
    }
    
    putString("<" + name + " name=\"" + name + "\">", true);
  }
  
  /** 
   * Write the closing node of the XML output file.
   * 
   * @since  1.0
   * 
   */
  public void putClosingNode(final String name) {
    for (int i = 0; i < 1; i++) {
      putString(INDENT, false);
    }
    
    putString("</" + name + ">", true);
  }
  
  /** 
   * Write the leaf text (the attribute) of the XML output file.
   * 
   * @param String name
   * @param double value
   * 
   * @since  1.0
   * 
   */
  public void putDoubleAttribute(final String name, final double value) {
    for (int i = 0; i < 2; i++) {
      putString(INDENT, false);
    }
    
    putString("<" + name + " name=\"" + name + "\" doubleValue=\"" + value + "\"/>", true);   
  }
  
  /** 
   * Write the leaf text (the attribute) of the XML output file.
   * 
   * @param String name
   * @param double value
   * 
   * @since  1.0
   * 
   */
  public void putIntegerAttribute(final String name, final int value) {
    for (int i = 0; i < 2; i++) {
      putString(INDENT, false);
    }
    
    putString("<" + name + " name=\"" + name + "\" integerValue=\"" + value + "\"/>", true);   
  }
  
  /** 
   * Write the leaf text (the attribute) of the XML output file.
   * 
   * @param String name
   * @param double value
   * 
   * @since  1.0
   * 
   */
  public void putBooleanAttribute(final String name, final Boolean value) {
    for (int i = 0; i < 2; i++) {
      putString(INDENT, false);
    }
    
    putString("<" + name + " name=\"" + name + "\" booleanValue=\"" + value.toString() 
              + "\"/>", true);   
  }

  /** 
   * Write the attributes of the object.
   * 
   * @param XmlWriterTest xmlWriter
   * 
   * @since  1.0
   * 
   */
  public void saveAccount401k(final XmlWriter xmlWriter) {
 
    xmlWriter.putNode("Account401K");
   
    xmlWriter.putDoubleAttribute("account401kAnnualContributions", 0.0);   
    xmlWriter.putDoubleAttribute("account401kBalance", 354_000.0);
    xmlWriter.putDoubleAttribute("account401kGrowthRate", 0.04);
    xmlWriter.putIntegerAttribute("account401kNumberOfWithdrawals", 12);
    xmlWriter.putIntegerAttribute("account401kStartWithdrawalAge", 58);

    xmlWriter.putClosingNode("Account401K");
  }

  /** 
   * Write the attributes of the object.
   * 
   * @param XmlWriterTest xmlWriter
   * 
   * @since  1.0
   * 
   */
  public void saveAccount403b(final XmlWriter xmlWriter) {
    xmlWriter.putNode("Account403B");
    
    xmlWriter.putDoubleAttribute("account403BAnnualContributions", 0.0);
    xmlWriter.putDoubleAttribute("account403BBalance", 255_000.0);
    xmlWriter.putDoubleAttribute("account403BGrowthRate", 0.04);
    xmlWriter.putIntegerAttribute("account403BNumberOfWithdrawals", 0);
    xmlWriter.putIntegerAttribute("account403BStartWithdrawalAge", 59);
    
    xmlWriter.putClosingNode("Account403B");    
  }
  
  /** 
   * Write the attributes of the object.
   * 
   * @param XmlWriterTest xmlWriter
   * 
   * @since  1.0
   * 
   */
  public void saveCashBalance(final XmlWriter xmlWriter) {
    xmlWriter.putNode("CashBalance");
    
    xmlWriter.putDoubleAttribute("accountCashBalanceAnnualContributions", 0.0);
    xmlWriter.putDoubleAttribute("accountCashBalanceBalance", 105_000.0);
    xmlWriter.putDoubleAttribute("accountCashBalanceGrowthRate", 0.04);
    xmlWriter.putIntegerAttribute("accountCashBalanceNumberOfWithdrawals", 0);
    xmlWriter.putIntegerAttribute("accountCashBalanceStartWithdrawalAge", 59);
    
    xmlWriter.putClosingNode("CashBalance");    
  }
  
  /** 
   * Write the attributes of the object.
   * 
   * @param XmlWriterTest xmlWriter
   * 
   * @since  1.0
   * 
   */
  public void saveIra(final XmlWriter xmlWriter) {
    xmlWriter.putNode("Ira");
    
    xmlWriter.putDoubleAttribute("accountIraAnnualContributions", 5500.0);
    xmlWriter.putDoubleAttribute("accountIraBalance", 35_000.0);
    xmlWriter.putDoubleAttribute("accountIraGrowthRate", 0.05);
    xmlWriter.putIntegerAttribute("accountIraNumberOfWithdrawals", 0);
    xmlWriter.putIntegerAttribute("accountIraStartWithdrawalAge", 70);
    
    xmlWriter.putClosingNode("Ira");    
  }

  /** 
   * Write the attributes of the object.
   * 
   * @param XmlWriterTest xmlWriter
   * 
   * @since  1.0
   * 
   */
  public void saveRoth(final XmlWriter xmlWriter) {
    xmlWriter.putNode("Roth");
    
    xmlWriter.putDoubleAttribute("accountRothAnnualContributions", 0.0);
    xmlWriter.putDoubleAttribute("accountRothBalance", 188_000.0);
    xmlWriter.putDoubleAttribute("accountRothGrowthRate", 0.05);
    xmlWriter.putIntegerAttribute("accountRothNumberOfWithdrawals", 0);
    xmlWriter.putIntegerAttribute("accountRothStartWithdrawalAge", 70);
    
    xmlWriter.putClosingNode("Roth");    
  }

  /** 
   * Write the attributes of the object.
   * 
   * @param XmlWriterTest xmlWriter
   * 
   * @since  1.0
   * 
   */
  public void saveBrokerage(final XmlWriter xmlWriter) {
    xmlWriter.putNode("Brokerage");
     
    xmlWriter.putDoubleAttribute("brokerageStartingBalance", 24_000.0);
    xmlWriter.putDoubleAttribute("brokerageGrowthRate", 0.04);
    
    xmlWriter.putClosingNode("Brokerage");    
  }
  
  /** 
   * Write the attributes of the object.
   * 
   * @param XmlWriterTest xmlWriter
   * 
   * @since  1.0
   * 
   */
  public void saveDeductions(final XmlWriter xmlWriter) {
    xmlWriter.putNode("Deductions");
     
    xmlWriter.putDoubleAttribute("currentDeductions", 6500.0);
    
    xmlWriter.putClosingNode("Deductions");    
  }
  
  /** 
   * Write the attributes of the object.
   * 
   * @param XmlWriterTest xmlWriter
   * 
   * @since  1.0
   * 
   */
  public void saveExpenses(final XmlWriter xmlWriter) {
    xmlWriter.putNode("Expenses");
     
    xmlWriter.putDoubleAttribute("currentExpenses", 40_000.0);
    
    xmlWriter.putClosingNode("Expenses");    
  }  
  
  /** 
   * Write the attributes of the object.
   * 
   * @param XmlWriterTest xmlWriter
   * 
   * @since  1.0
   * 
   */
  public void savePension(final XmlWriter xmlWriter) {
    xmlWriter.putNode("Pension");
     
    xmlWriter.putDoubleAttribute("pensionAmount", 24_000.0);
    xmlWriter.putDoubleAttribute("pensionGrowthRate", 0.0);
    xmlWriter.putBooleanAttribute("pensionInflationAdjusted", false);
    xmlWriter.putDoubleAttribute("pensionMonthlyAmount", 1760.0);
    xmlWriter.putIntegerAttribute("pensionStartAge", 55);

    xmlWriter.putClosingNode("Pension");    
  }
  
  /** 
   * Write the attributes of the object.
   * 
   * @param XmlWriterTest xmlWriter
   * 
   * @since  1.0
   * 
   */
  public void savePersonal(final XmlWriter xmlWriter) {
    xmlWriter.putNode("Personal");
     
    xmlWriter.putIntegerAttribute("personalBirthDay", 6);
    xmlWriter.putIntegerAttribute("personalBirthMonth", 9);
    xmlWriter.putIntegerAttribute("personalBirthYear", 1958);
    xmlWriter.putIntegerAttribute("personalCurrentYear", 2018);
    xmlWriter.putIntegerAttribute("personalDeathAge", 95);
    xmlWriter.putDoubleAttribute("personalInflation", 0.028);
    xmlWriter.putIntegerAttribute("personalRetirementAge", 59);
    
    xmlWriter.putClosingNode("Personal");    
  }
  
  /** 
   * Write the attributes of the object.
   * 
   * @param XmlWriterTest xmlWriter
   * 
   * @since  1.0
   * 
   */
  public void saveSalary(final XmlWriter xmlWriter) {
    xmlWriter.putNode("Salary");
     
    xmlWriter.putDoubleAttribute("salaryAnnualSalary", 50_000.0);
    xmlWriter.putDoubleAttribute("salaryMeritIncrease", 0.025);
   
    xmlWriter.putClosingNode("Salary");    
  }  

  /** 
   * Write the attributes of the object.
   * 
   * @param XmlWriterTest xmlWriter
   * 
   * @since  1.0
   * 
   */
  public void saveSocialSecurity(final XmlWriter xmlWriter) {
    xmlWriter.putNode("SocialSecurity");
     
    xmlWriter.putDoubleAttribute("socialSecurityMonthlyAmount", 2900.0);
    xmlWriter.putIntegerAttribute("socialSecurityStartAge", 70);

    xmlWriter.putClosingNode("SocialSecurity");    
  }
 
  /** 
   * Write the attributes of the object.
   * 
   * @param XmlWriterTest xmlWriter
   * 
   * @since  1.0
   * 
   */
  public void saveTaxes(final XmlWriter xmlWriter) {
    xmlWriter.putNode("Taxes");
     
    xmlWriter.putDoubleAttribute("taxRateFederal", 0.16);
    xmlWriter.putDoubleAttribute("taxRateState", 0.04);

    xmlWriter.putClosingNode("Taxes");    
  }
 
  /** 
   * Close the output file method.
   * 
   * @since  1.0
   * 
   */
  public void close() {
    try {
      fileOutputStream.close();
    } catch (IOException e) {
        LOGGER.trace(e.getMessage());
    }
  }
  
  /** 
   * Entry point to test the class.
   * 
   * @param String[] args
   * 
   */
  public static void main(final String[] args) {
    final XmlWriter xmlWriter = new XmlWriter();
   
    xmlWriter.save("temp.xml");
    xmlWriter.putHeader();
    xmlWriter.putTopLevelNode();
   
    xmlWriter.saveAccount401k(xmlWriter);
    xmlWriter.saveAccount403b(xmlWriter);
    xmlWriter.saveCashBalance(xmlWriter);
    xmlWriter.saveIra(xmlWriter);
    xmlWriter.saveRoth(xmlWriter);
    xmlWriter.saveBrokerage(xmlWriter);
    xmlWriter.saveDeductions(xmlWriter);
    xmlWriter.saveExpenses(xmlWriter);
    xmlWriter.savePension(xmlWriter);
    xmlWriter.savePersonal(xmlWriter);
    xmlWriter.saveSalary(xmlWriter);
    xmlWriter.saveSocialSecurity(xmlWriter);
    xmlWriter.saveTaxes(xmlWriter);
 
    xmlWriter.putCloseTopLevelNode();
    xmlWriter.close();
  }
}
