package application.view.inputs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.IOException;

import javax.swing.InputVerifier;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import application.main.ExecuteObject;
import application.main.XmlReader;
import application.main.XmlWriter;
import application.main.pdf.CreatePdf;
import application.system.ApplicationLogger;

/**
 * 401K Input class.
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 1/12/2016
 *
 */
public abstract class Account implements ActionListener {
  /**
   * Set the integer for the number of valid focus listeners.
   * 
   * @var Integer NUMBER_OF_FOCUS_LISTENERS
   */
  private static final Integer NUMBER_OF_FOCUS_LISTENERS = 3;

  /**
   * Set the constant to its value.
   * 
   * @var String RETURNING
   */
  private static final String RETURNING = "           returning <";

  /**
   * The class to write out the trace.
   * 
   * @var ApplicationLogger logger
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();

  /**
   * Create an instance of FOCUS.
   * 
   * @var Focus FOCUS
   */
  private static final Focus FOCUS = new Focus();


  /**
   * The utility class.
   * 
   * @var Utils UTILS
   */
  private static final Utils UTILS = new Utils();

  /**
   * The user entered field.
   * 
   * @var JFormattedTextField balance
   */
  private transient JFormattedTextField balance;

  /**
   * The user entered field.
   * 
   * @var JFormattedTextField growthRatstatic e
   */
  private ComboBoxList growthRate;

  /**
   * The user entered field.
   * 
   * @var JFormattedTextField annualContribution
   */
  private transient JFormattedTextField annualContributions;

  /**
   * The user entered field.
   * 
   * @var JCheckBox onlyWhileSalary
   */
  private transient JCheckBox onlyWhileSalary;
    
  /**
   * The user entered field.
   * 
   * @var JFormattedTextField startWthdrawalAge
   */
  private ComboBoxList startWithdrawalAge;

  /**
   * The user entered field.
   * 
   * @var JFormattedTextField numberOfWithdrawals
   */
  private ComboBoxList numberOfWithdrawals;
    
  /**
   * Verifys the user input.
   * 
   * @var Verifier VERIFIER
   */
  private static final Verifier VERIFIER = new Verifier();

  /** 
   * Get the input tab panel for the tab.
   * 
   * @since  1.0
   * 
   * @return JPanel
   */
  public JPanel createPanel(final XmlReader xmlReader, 
                            final String BALANCE_PROPERTY, 
                            final String GROWTH_RATE_PROPERTY,
                            final String ANNUAL_CONTRIBUTIONS_PROPERTY, 
                            final String ONLY_WHILE_SALARY_PROPERTY,
                            final String START_WITHDRAWAL_AGE_PROPERTY, 
                            final String NUMBER_OF_WITHDRAWALS_PROPERTY) {
    LOGGER.trace("Entering the Account createPanel().");
        
    final JPanel jPanel = new JPanel(new GridLayout(6, 2));
         
    JLabel label = new JLabel(Entry.CURRENT_BALANCE.toString());
    jPanel.add(label);
    balance = new JFormattedTextField();
    balance.setText(UTILS.getDollarFormat(xmlReader.getDoubleProperty(BALANCE_PROPERTY)));
    balance.setName(UTILS.getCurrencyName());
    balance.setInputVerifier(VERIFIER);
    balance.addFocusListener(FOCUS);
    jPanel.add(balance);
  
    label = new JLabel(Entry.ANNUAL_GROWTH_RATE.toString());
    jPanel.add(label);
    growthRate = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_30_BY_ZERO_POINT_FIVE,
        UTILS.getPercentFormat(xmlReader.getDoubleProperty(GROWTH_RATE_PROPERTY)));
    jPanel.add(growthRate.getComboBox());

    label = new JLabel(Entry.ANNUAL_DEDUCTIONS.toString());
    jPanel.add(label);
    annualContributions = new JFormattedTextField();
    annualContributions.setText(UTILS.getDollarFormat(
        xmlReader.getDoubleProperty(ANNUAL_CONTRIBUTIONS_PROPERTY)));
    annualContributions.setName(UTILS.getCurrencyName());
    annualContributions.setInputVerifier(VERIFIER);
    annualContributions.addFocusListener(FOCUS);
    jPanel.add(annualContributions);
     
    label = new JLabel(Entry.ONLY_WITH_A_SALARY.toString());
    jPanel.add(label);
    onlyWhileSalary = new JCheckBox();
    onlyWhileSalary.setSelected(xmlReader.getBooleanProperty(ONLY_WHILE_SALARY_PROPERTY));
    onlyWhileSalary.addActionListener(this);
    jPanel.add(onlyWhileSalary);
  
    label = new JLabel(Entry.START_WITHDRAWL_AGE.toString());
    jPanel.add(label);
    startWithdrawalAge = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_95,
        UTILS.getWholeIntegerFormat(
            xmlReader.getIntegerProperty(START_WITHDRAWAL_AGE_PROPERTY)));
    jPanel.add(startWithdrawalAge.getComboBox());

    label = new JLabel(Entry.NUMBER_OF_WITHDRAWALS.toString());
    jPanel.add(label);
    numberOfWithdrawals = new ComboBoxList(
        ComboBoxItems.ITEMS.ITEMS_1_TO_50, UTILS.getWholeIntegerFormat(
            xmlReader.getIntegerProperty(NUMBER_OF_WITHDRAWALS_PROPERTY)));
    jPanel.add(numberOfWithdrawals.getComboBox());

    LOGGER.trace("Leaving the Account createPanel().");
    LOGGER.trace("    returning jPanel=<" + jPanel + ">.");
    jPanel.setFocusable(false);  
    return jPanel;
  }
   
  /** 
   * Perform the button clicked checkbox action.
   * 
   * @since  1.0
   * 
   */
  @Override
  public void actionPerformed(final ActionEvent arg0) {
    final ExecuteObject execute = new ExecuteObject();
     
    execute.runSimulation(Focus.getInputsTabbedPane(), Focus.getResultsTabbedPane());
  }
   
  /** 
   * Get the user input.
   * 
   * @since  1.0
   * @return double
   */
  public double getBalance() {
    LOGGER.trace("Entering the Account getBalance() with no parameters.");
         
    String inputString = balance.getText();
    double value = -987.0; // -987.0 for invalid field
         
    // valid input field
    inputString = UTILS.removeDollarFormatSpecialCharacters(inputString);
    
    if (VERIFIER.checkField(inputString, UTILS.getCurrencyName())) {
      try {
        value = new Double(inputString);
      } catch (NumberFormatException e) {
        UTILS.invalidNumber("Invalid 401K Balance (" + inputString + ")."); 
      }
    }
         
    LOGGER.trace("Leaving the Account getBalance().");
    LOGGER.trace(RETURNING + value + ">");
         
    return value;
  }
   
  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return double
   */
  public double getGrowthRate() {
    LOGGER.trace("Entering the Account getGrowthRate() with no parameters.");
         
    final String inputString = growthRate.getValue();
    final String inputString2 = inputString.replaceAll("%", "");
    double value = -987.0; // -987.0 for invalid field
         
    value = Double.valueOf(inputString2);
    value /= 100;
    LOGGER.trace("Leaving the Account getGrowthRate().");
    LOGGER.trace(RETURNING + value + ">");
         
    return value;
  }
   
  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return double
   */
  public double getAnnualContributions() {
    LOGGER.trace("Entering the Account getAnnualContributions() with no parameters.");
         
    String inputString = annualContributions.getText();
    double value = -987.0; // -987.0 for invalid field
         
    // valid input field
    inputString = UTILS.removeDollarFormatSpecialCharacters(inputString);
    
    if (VERIFIER.checkField(inputString, UTILS.getCurrencyName())) {
      try {
        value = new Double(inputString);
      } catch (NumberFormatException e) {
        UTILS.invalidNumber("Invalid " + this.getClass().getSimpleName() 
                            + " Annual Contribution (" + inputString + ")."); 
      }
    }
         
    LOGGER.trace("Leaving the Account getAnnualContribution().");
    LOGGER.trace(RETURNING + value + ">");
         
    return value;
  }
      
  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return boolean
   */
  public boolean isOnlyWhileSalary() {
    boolean value = false;

    LOGGER.trace("Entering the Account getOnlyWhileSalary() with no parameters.");

    value = onlyWhileSalary.isSelected();

    LOGGER.trace("Leaving the Account getOnlyWhileSalary().");
    LOGGER.trace(RETURNING + value + ">");

    return value;
  }
   
  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return double
   */
  public int getStartWithdrawalAge() {
    LOGGER.trace("Entering the Account getStartWithdrawalAge() with no parameters.");
         
    final String inputString = startWithdrawalAge.getValue();
    Integer value = Integer.valueOf("-987"); // -987 for invalid field
    
    value = Integer.valueOf(inputString);

    LOGGER.trace("Leaving the Account getStartWithdrawalAge().");
    LOGGER.trace(RETURNING + value + ">");
         
    return value;
  }
     
  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return int
   */
  public int getNumberOfWithdrawals() {
    LOGGER.trace("Entering the Account getNumberOfWithdrawals() with no parameters.");
         
    final String inputString = numberOfWithdrawals.getValue();
    Integer value = Integer.valueOf("-987"); // -987 for invalid field
         
    value = Integer.valueOf(inputString);
     
    LOGGER.trace("Leaving the Account getNumberOfWithdrawals().");
    LOGGER.trace(RETURNING + value + ">");
         
    return value;
  }
   
  /** 
   * Save the properties of this object.
   * 
   * @since  1.0
   * 
   */
  public void saveProperties(final XmlWriter xmlWriter, final String BALANCE_PROPERTY, 
      final String GROWTH_RATE_PROPERTY, final String ANNUAL_CONTRIBUTIONS_PROPERTY, 
      final String ONLY_WHILE_SALARY_PROPERTY, final String START_WITHDRAWAL_AGE_PROPERTY,
      final String NUMBER_OF_WITHDRAWALS_PROPERTY) {
    LOGGER.trace("Entering the Account saveProperties() with property parameters.");
 
    xmlWriter.putNode(this.getClass().getSimpleName());
    
    xmlWriter.putDoubleAttribute(BALANCE_PROPERTY, getBalance());   
    xmlWriter.putDoubleAttribute(GROWTH_RATE_PROPERTY, getGrowthRate());
    xmlWriter.putDoubleAttribute(ANNUAL_CONTRIBUTIONS_PROPERTY, getAnnualContributions());
    xmlWriter.putBooleanAttribute(ONLY_WHILE_SALARY_PROPERTY, isOnlyWhileSalary());
    xmlWriter.putIntegerAttribute(START_WITHDRAWAL_AGE_PROPERTY, getStartWithdrawalAge());
    xmlWriter.putIntegerAttribute(NUMBER_OF_WITHDRAWALS_PROPERTY, getNumberOfWithdrawals());

    xmlWriter.putClosingNode(this.getClass().getSimpleName());
      
    LOGGER.trace("Leaving the Account saveProperties().");        
  }
 
  /**
   * The below methods are needed for testing only; not used in the application
   */
  
  /** 
   * Verify the user input.
   */
  public void setBalance(final String input) {
    LOGGER.trace("Entering the Account setBalance() with input parameter.");
    
    balance.setText(input);
    final InputVerifier verifier = balance.getInputVerifier();
    verifier.verify(balance);
  }
  
  /** 
   * Check the user input for a focus listener.
   * 
   * @since  1.0
   * @return boolean
   */
  public boolean balanceHasFocusListener() {
    LOGGER.trace("Entering the Account balanceHasFocusListener() with no parameters.");
    
    boolean hasFocusListener = false;
    
    final FocusListener[] focusListeners = balance.getFocusListeners();
    if (focusListeners.length == NUMBER_OF_FOCUS_LISTENERS) { // 2 standard focus listerns + ours
      hasFocusListener = true;
    }
 
    return hasFocusListener;
  }
  
  /** 
   * Verify the user input.
   * 
   * @since  1.0
   */
  public void setGrowthRate(final String input) {
    LOGGER.trace("Entering the Account setGrowthRate() with input parameters.");
    
    growthRate.setValue(input);
  }
    
  /** 
   * Verify the user input.
   * 
   * @since  1.0
   */
  public void setAnnualContribution(final String input) {
    LOGGER.trace("Entering the Account annual Contributions() with input parameters.");
    
    annualContributions.setText(input);
    final InputVerifier verifier = annualContributions.getInputVerifier();
    verifier.verify(annualContributions);
  }
  
  /** 
   * Check the user input for a focus listener.
   * 
   * @since  1.0
   * @return boolean
   */
  public boolean annualContributionHasFocusListener() {
    LOGGER.trace("Entering the Account annualContributionHasFocusListener() with no parameters.");
    
    boolean hasFocusListener = false;
    
    final FocusListener[] focusListeners = annualContributions.getFocusListeners();
    if (focusListeners.length == NUMBER_OF_FOCUS_LISTENERS) { // 2 standard focus listerns + ours
      hasFocusListener = true;
    }

    return hasFocusListener;
  } 
  
  /** 
   * Verify the user input.
   * 
   * @since  1.0
   */
  public void setStartWithdrawalAge(final String input) {
    LOGGER.trace("Entering the Account setStartWithdrawalAge() with input parameters.");
    
    startWithdrawalAge.setValue(input);
  }

  /** 
   * Verify the user input.
   * 
   * @since  1.0
   */
  public void setNumberOfWithdrawals(final String input) {
    LOGGER.trace("Entering the Account setNumberOfWithdrawals() with input parameters.");
     
    numberOfWithdrawals.setValue(input);
  }
   
  /** 
   * Ensures that the input objects has a writePdf().
   * 
   * @since  1.0
   */
  public void writePdf(final String name, final PDDocument doc) {
    final CreatePdf createPdf = new CreatePdf();
     
    final PDPage page = new PDPage();
    doc.addPage(page);
            
    try {
      final PDPageContentStream contents = new PDPageContentStream(doc, page);
      createPdf.inputHeader(contents, name);
  
      // Print out the balance
      createPdf.entry(contents, Entry.CURRENT_BALANCE.toString(), 
                      UTILS.getDollarFormat(getBalance()), 650);
       
      // Print out the growth rate
      createPdf.entry(contents, Entry.ANNUAL_GROWTH_RATE.toString(), 
                      UTILS.getPercentFormat(getGrowthRate()), 625);

      // Print out the annual contributions
      createPdf.entry(contents, Entry.ANNUAL_CONTRIBUTIONS.toString(), 
                      UTILS.getDollarFormat(getAnnualContributions()), 600);
    
      // Print out the only with a salary contributions (true/false)
      createPdf.entry(contents, Entry.ONLY_WITH_A_SALARY.toString(),
                      isOnlyWhileSalary() ? "true" : "false", 575);
      
      // Print out the starting withdrawal age
      createPdf.entry(contents, Entry.START_WITHDRAWL_AGE.toString(), 
                      UTILS.getWholeIntegerFormat(getStartWithdrawalAge()), 550);
       
      // Print out the starting withdrawal age
      createPdf.entry(contents, Entry.NUMBER_OF_WITHDRAWALS.toString(), 
                      UTILS.getWholeIntegerFormat(getNumberOfWithdrawals()), 525);
      contents.close();
       
   } catch (IOException e) {
    	LOGGER.trace(e.getMessage());
    }
  }
}