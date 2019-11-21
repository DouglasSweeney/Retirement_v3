package application.view.inputs;

import java.awt.GridLayout;
import java.awt.event.FocusListener;

import javax.swing.InputVerifier;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;

import application.main.XmlReader;
import application.main.XmlWriter;
import application.system.ApplicationLogger;

/**
 * Creates the GUI tab for the Social Security inputs.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class SocialSecurity extends InputObject {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Set the integer for the number of valid focus listeners.
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
   * Set the start age property to its value.
   * 
   * @var String START_AGE_PROPERTY
   */
  public static final String START_AGE_PROPERTY = "socialSecurityStartAge";
  
  /**
   * Set the monthly property to its value.
   * 
   * @var String MONTHLY_AMOUNT_PROPERTY
   */
  public static final String MONTHLY_AMOUNT_PROPERTY = "socialSecurityMonthlyAmount";

  /**
   * Set the LOGGER property to its value.
   * 
   * @var Application_Logger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();

  /**
   * Set the name of the tab.
   * 
   * @var String NAME
   */
  private static final String NAME = Header.SOCIAL_SECURITY.toString();;
  
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
   * Set the input for the start age.
   * 
   * @var JFormattedTextField startAge
   */
  private static ComboBoxList startAge;

  /**
   * Set the input for the monthly amount.
   * 
   * @var JFormattedTextField monthlyAmount
   */
  private static JFormattedTextField monthlyAmount;

  /**
   * Set the input field VERIFIER.
   * 
   * @var Verifier VERIFIER
   */
  private static final Verifier VERIFIER = new Verifier();

  /** 
   * No properties are passed in(as normal). This allows
   * the model to get access to the inputs.
   * 
   * @since  1.0
   */
  public SocialSecurity() {
    super();

    LOGGER.trace("Called the Social Security constructor with no parameters.");

    LOGGER.trace("Leaving the Social Security constructor.");
  }

  /** 
   * Get the properties for the Social Security account.
   * 
   * @param  props     The properties for the application
   * @since  1.0
   */
  public SocialSecurity(final XmlReader xmlReader) {
    super();

    LOGGER.trace("Called the Social Security constructor.");
    LOGGER.trace("    xmlReader=<" + xmlReader + ">.");

    this.xmlReader = xmlReader;

    LOGGER.trace("Leaving the Social Security constructor.");
  }

  /** 
   * Get the input tab panel for the tab.
   * 
   * @since  1.0
   * 
   * @return JPanel
   */
  public JPanel createPanel() {
    LOGGER.trace("Entering the Social Security createPanel() with no parameters.");

    final JPanel jPanel = new JPanel(new GridLayout(2, 2));

    JLabel label = new JLabel(Entry.STARTING_AGE.toString());
    jPanel.add(label);
    startAge = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_95,
        UTILS.getWholeIntegerFormat(xmlReader.getIntegerProperty(START_AGE_PROPERTY)));
    jPanel.add(startAge.getComboBox());

    label = new JLabel(Entry.MONTHLY_AMOUNT.toString());
    jPanel.add(label);
    monthlyAmount = new JFormattedTextField();
    monthlyAmount.setText(UTILS.getDollarFormat(xmlReader.getDoubleProperty(MONTHLY_AMOUNT_PROPERTY)));
    monthlyAmount.setName(UTILS.getCurrencyName());
    monthlyAmount.setInputVerifier(VERIFIER);
    monthlyAmount.addFocusListener(FOCUS);
    jPanel.add(monthlyAmount);

    LOGGER.trace("Leaving the Social Security createPanel().");
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
    LOGGER.trace("Entering the Social Security getName() with no parameters.");

    LOGGER.trace("Leaving the Social Security getName().");
    LOGGER.trace(RETURNING + NAME + ">");

    return NAME;
  }

  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return int
   */
  public int getStartAge() {
    LOGGER.trace("Entering the Social Security getStartAge() with no parameters.");

    String inputString = startAge.getValue();
    int value = -987; // -987 for invalid field

    // valid input field
    inputString = inputString.replaceAll(",", "");
    value = Integer.valueOf(inputString);


    LOGGER.trace("Leaving the Social Security getStartAge().");
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
  public double getMonthlyAmount() {
    LOGGER.trace("Entering the Social Security getMonthlyAmount() with no parameters.");

    String inputString = monthlyAmount.getText();
    double value = -987.0;  // -987.0 for invalid field

    // valid input field
    inputString = UTILS.removeDollarFormatSpecialCharacters(inputString);
    if (VERIFIER.checkField(inputString, UTILS.getCurrencyName())) {
      try {
        value = new Double(inputString);
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid Social Security Monthly Amount("
                      + inputString + ").", "Invalid Number", JOptionPane.ERROR_MESSAGE);
      }
    }

    LOGGER.trace("Leaving the Social Security getMonthlyAmount().");
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
    LOGGER.trace("Entering the SocialSecurity saveProperties() with no parameters.");
    
    xmlWriter.putNode("SocialSecurity");
    
    xmlWriter.putIntegerAttribute(START_AGE_PROPERTY, getStartAge());
    xmlWriter.putDoubleAttribute(MONTHLY_AMOUNT_PROPERTY, getMonthlyAmount());   
    
    xmlWriter.putClosingNode("SocialSecurity");
      

    LOGGER.trace("Leaving the SocialSecurity saveProperties().");
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
  public void setMonthlyAmount(final String input) {
    LOGGER.trace("Entering the Pension setMonthlyAmount() with input parameter.");
    
    monthlyAmount.setText(input);
    final InputVerifier verifier = monthlyAmount.getInputVerifier();
    verifier.verify(monthlyAmount);
  }
  
  /** 
   * Check the user input for a focus listener.
   * 
   * @since  1.0
   * @return double
   */
  public boolean monthlyAmountHasFocusListener() {
    LOGGER.trace("Entering the Pension monthlyAmountHasFocusListener() with no parameters.");
    
    boolean hasFocusListener = false;
    
    final FocusListener[] focusListeners = monthlyAmount.getFocusListeners();
    if (focusListeners.length == NUMBER_OF_FOCUS_LISTENERS) { // 2 standard focus listerns + ours
      hasFocusListener = true;
    }
 
    return hasFocusListener;
  }
  
  public void writePdf(final String name, final PDDocument doc) {
	  writePdf(doc, Entry.MONTHLY_AMOUNT.toString(), UTILS.getDollarFormat(getMonthlyAmount()),
		        Entry.STARTING_AGE.toString(), UTILS.getWholeIntegerFormat(getStartAge()));
  }
}
