package application.view.inputs;

import java.awt.GridLayout;
import java.awt.event.FocusListener;

import javax.swing.InputVerifier;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;

import application.main.XmlReader;
import application.main.XmlWriter;
import application.system.ApplicationLogger;

/**
 * Creates the GUI tab for the Savings inputs.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class Savings extends InputObject {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Set the integer for     final JPanel jPanel = new JPanel(new GridLayout(2, 2));

    JLabel label = new JL    final JPanel jPanel = new JPanel(new GridLayout(2, 2));

    JLabel label = new JLabel(ENTRY.Current_Balance.getName());
    jPanel.add(label);
    balance = new JFormattedTextField();
    balance.setText(utils.getDollarFormat(xmlReader.getDoubleProperty(BALANCE_PROPERTY)));
    balance.setName(utils.getCurrencyName());
    balance.setInputVerifier(VERIFIER);
    balance.addFocusListener(FOCUS);
    jPanel.add(balance);

    label = new JLabel(ENTRY.Annual_Growth_Rate.getName());
abel(ENTRY.Current_Balance.getName());
    jPanel.add(label);
    balance = new JFormattedTextField();
    balance.setText(utils.getDollarFormat(xmlReader.getDoubleProperty(BALANCE_PROPERTY)));
    balance.setName(utils.getCurrencyName());
    balance.setInputVerifier(VERIFIER);
    balance.addFocusListener(FOCUS);
    jPanel.add(balance);

    label = new JLabel(ENTRY.Annual_Growth_Rate.getName());
the number of valid focus listeners.
   * 
   * @var Integer NUMBER_OF_FOCUS_LISTENERS
   */
  private static final Integer NUMBER_OF_FOCUS_LISTENERS = 3;

  /**
   * Set the string for logging.
   * 
   * @var String RETURNING
   */
  private static final String RETURNING = "     returning <";
  /**
   * Set the string for logging.
   * 
   * @var String XMLREADER_EQUALS
   */
  private static final String XMLREADER_EQUALS = "    xmlReader=<";
  /**
   * Set the balance property to its value.
   * 
   * @var String BALANCE_PROPERTY
   */
  public static final String BALANCE_PROPERTY = "savingsStartingBalance";
  
  /**
   * Set the growth rate property to its value.
   * 
   * @var String GROWTH_RATE_PROPERTY
   */
  public static final String GROWTH_RATE_PROPERTY = "savingsGrowthRate";
  

  /**
   * Set the LOGGER property to its value.
   * 
   * @var Application_LOGGER LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();

  /**
   * Set the name of the tab.
   * 
   * @var String NAME
   */
  private static final String NAME = Header.SAVINGS.toString();;

  /**
   * Create an instance of the utils.
   * 
   * @var Utils utils
   */
  private static final Utils UTILS = new Utils();

  /**
   * Create an instance of FOCUS.
   * 
   * @var Focus FOCUS
   */
  private static final Focus FOCUS = new Focus();

  /**
   * Get an instance of the Property.
   * 
   * @var XmlReader xmlReader
   */
  private transient XmlReader xmlReader;

  /**
   * Set the input for the balance.
   * 
   * @var JFormattedTextField balance
   */
  private static JFormattedTextField balance;
  
  /**
   * Set the input for the growth rate.
   * 
   * @var JFormattedTextField growthRate
   */
  private static ComboBoxList growthRate;

  /**
   * Set the input field verifier.
   * 
   * @var Verifier verifier
   */
  private static final Verifier VERIFIER = new Verifier();
    
  /** 
   * No properties are passed in(as normal). This allows
   * the model to get access to the inputs.
   * 
   * @since  1.0
   */
  public Savings() {
    super();    
    
    final JPanel jPanel = new JPanel(new GridLayout(2, 2));

    JLabel label = new JLabel(Entry.CURRENT_BALANCE.toString());
    jPanel.add(label);
    
    balance = new JFormattedTextField();
    balance.setText(UTILS.getDollarFormat(xmlReader.getDoubleProperty(BALANCE_PROPERTY)));
    balance.setName(UTILS.getCurrencyName());
    balance.setInputVerifier(VERIFIER);
    balance.addFocusListener(FOCUS);
    jPanel.add(balance);

    label = new JLabel(Entry.ANNUAL_GROWTH_RATE.toString());

    LOGGER.trace("Called the Savings constructor with no parameters.");

    LOGGER.trace("Leaving the Savings constructor.");
  }

  /** 
   * Get the properties for the Savings account.
   * 
   * @param  props     The properties for the application
   * @since  1.0
   */
  public Savings(final XmlReader xmlReader) {
    super();

    LOGGER.trace("Called the Savings constructor.");
    LOGGER.trace(XMLREADER_EQUALS + xmlReader + ">.");

    this.xmlReader = xmlReader;

    LOGGER.trace("Leaving the Savings constructor.");
  }

  /** 
   * Get the input tab panel for the tab.
   * 
   * @since  1.0
   * 
   * @return JPanel
   */
  public JPanel createPanel() {
    LOGGER.trace("Entering the Savings createPanel() with no parameters.");

    final JPanel jPanel = new JPanel(new GridLayout(2, 2));

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

    LOGGER.trace("Leaving the Savings createPanel().");
    LOGGER.trace("    returning jPanel=<" + jPanel + ">.");

    return jPanel;
  }

  /** 
   * Get the name of the tab.
   * 
   * @since  1.0
   * 
   * @return String
   */
  public String getName() {
    LOGGER.trace("Entering the Savings getName() with no parameters.");

    LOGGER.trace(RETURNING + NAME + ">");

    return NAME;
  }

  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return double
   */
  public double getBalance() {
    LOGGER.trace("Entering the Savings getBalance() with no parameters.");

    String inputString = balance.getText();
    double value = -987.0;  // -987.0 for invalid field

    // valid input field
    inputString = UTILS.removeDollarFormatSpecialCharacters(inputString);
    if (VERIFIER.checkField(inputString, UTILS.getCurrencyName())) {
      try {
        value = new Double(inputString);
      } catch (NumberFormatException e) {
        UTILS.invalidNumber("Invalid Savings balance(" + inputString + ").");
      }
    }

    LOGGER.trace("Leaving the Savings getBalance().");
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
    LOGGER.trace("Entering the Savings getGrowthRate() with no parameters.");

    final String inputString = growthRate.getValue();
    final String inputString2 = inputString.replaceAll("%", "");
    final String inputString3 = inputString2.replaceAll(",", "");
    double value = -987.0;  // -987.0 for invalid field

    // valid input field
    if (VERIFIER.checkField(inputString3, UTILS.getPercentName())) {
      try {
        value = new Double(inputString3);
      } catch (NumberFormatException e) {
        UTILS.invalidNumber("Invalid Savings Growth Rate(" + inputString + ").");
      }
      value /= 100;
    }

    LOGGER.trace("Leaving the Savings getGrowthRate().");
    LOGGER.trace(RETURNING + value + ">");

    return value;
  }

  /** 
   * Save the properties of this object.
   * 
   * @since  1.0
   * 
   */
  public void saveProperties(final XmlWriter xmlWriter) {
    LOGGER.trace("Entering the Savings saveProperties() with no parameters.");
    
    xmlWriter.putNode("Savings");
    
    xmlWriter.putDoubleAttribute(BALANCE_PROPERTY, getBalance());   
    xmlWriter.putDoubleAttribute(GROWTH_RATE_PROPERTY, getGrowthRate());

    xmlWriter.putClosingNode("Savings");
      

    LOGGER.trace("Leaving the Savings saveProperties().");
  }
  /**
   * The below methods are needed for testing only; not used in the application
   */
  
  /** 
   * Verify the user input.
   * 
   * @since  1.0
   * @return double
   */
  public void setBalance(final String input) {
    LOGGER.trace("Entering the Account401K setBalance() with input parameter.");
    
    balance.setText(input);
    final InputVerifier verifier = balance.getInputVerifier();
    verifier.verify(balance);
  }
  
  /** 
   * Verify the user input.
   * 
   * @since  1.0
   * @return double
   */
  public void setGrowthRate(final String input) {
    LOGGER.trace("Entering the Account401K setBalance() with input parameter.");
    
    growthRate.setValue(input);
  }
   
  /** 
   * Check the user input for a focus listener.
   * 
   * @since  1.0
   * @return double
   */
  public boolean balanceHasFocusListener() {
    LOGGER.trace("Entering the Account401K setBalance2() with no parameters.");
    
    boolean hasFocusListener = false;
    
    final FocusListener[] focusListeners = balance.getFocusListeners();
    if (focusListeners.length == NUMBER_OF_FOCUS_LISTENERS) { // 2 standard focus listerns + ours
      hasFocusListener = true;
    }
 
    return hasFocusListener;
  }
  
  public void writePdf(final String name, final PDDocument doc) {

	  writePdf(doc, Entry.CURRENT_BALANCE.toString(), UTILS.getDollarFormat(getBalance()),
			        Entry.ANNUAL_GROWTH_RATE.toString(), UTILS.getPercentFormat(getGrowthRate()));
  }
}
