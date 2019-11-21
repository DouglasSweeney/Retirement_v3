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
* Creates the GUI tab for the annual Expenses inputs.
* 
* @author Doug Sweeney
* @version 1.0
* @since 1.0
*/
public class Expenses extends InputObject {
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
  private static final String RETURNING = " returning <";
  
  /**
   * Set the string for logging.
   * 
   * @var String XMLREADER_EQUALS
   */
  private static final String XMLREADER_EQUALS = "xmlReader=<";

  /**
   * Set the expenses property to its value.
   * 
   * @var String EXPENSES_PROPERTY
   */
  public static final String EXPENSES_PROPERTY = "currentExpenses";

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
  private static final String NAME = Header.EXPENSES.toString();;

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
   * @var XMLReader xmlReader
   */
  private transient XmlReader xmlReader;

  /**
   * Set the input for the expenses..
   * 
   * @var JFormattedTextField expenses
   */
  private static JFormattedTextField expenses;

  /**
   * Set the input field VERIFIER.
   * 
   * @var VERIFIER VERIFIER
   */
  private static final Verifier VERIFIER = new Verifier();

  /** 
   * No properties are passed in(as normal). This allows
   * the model to get access to the inputs.
   * 
   * @since1.0
   */
  public Expenses() {
    super();

    LOGGER.trace("Called the Expenses constructor with no parameters.");

    LOGGER.trace("Leaving the Expenses constructor.");
  }

  /** 
   * Remember the properties.
   * 
   * @paramprops The properties for the application
   * @since1.0
   */
  public Expenses(final XmlReader xmlReader) {
    super();

    LOGGER.trace("Called the Expenses constructor.");
    LOGGER.trace(XMLREADER_EQUALS + xmlReader + ">.");

    this.xmlReader = xmlReader;

    LOGGER.trace("Leaving the Expenses constructor.");
  }

  /** 
   * Get the input tab panel for the tab.
   * 
   * @since1.0
   * 
   * @return JPanel
   */
  public JPanel createPanel() {
    LOGGER.trace("Entering the Expenses createPanel() with no parameters.");

    final JPanel jPanel = new JPanel(new GridLayout(1, 2));
    final JLabel label = new JLabel(Entry.ANNUAL_EXPENSES.toString());
    jPanel.add(label);
    expenses = new JFormattedTextField();
    expenses.setText(UTILS.getDollarFormat(xmlReader.getDoubleProperty(EXPENSES_PROPERTY)));
    expenses.setName(UTILS.getCurrencyName());
    expenses.setInputVerifier(VERIFIER);
    expenses.addFocusListener(FOCUS);
    jPanel.add(expenses);

    LOGGER.trace("Leaving the Expenses createPanel().");
    LOGGER.trace(RETURNING + jPanel + ">.");

    return jPanel;
  }

  /** 
   * Get the name of the tab.
   * 
   * @since1.0
   * 
   * @return String
   */
  public String getName() {
    LOGGER.trace("Entering the Expenses getName() with no parameters.");

    LOGGER.trace("Leaving the Expenses getName().");
    LOGGER.trace(RETURNING + NAME + ">");

    return NAME;
  }

  /** 
   * Get the user input.
   * 
   * @since1.0
   * 
   * @return double
   */
  public double getExpenses() {
    LOGGER.trace("Entering the Expenses getExpenses() with no parameters.");
    String inputString = expenses.getText();
    double value = -987.0; // -987.0 for invalid field

    // valid input field
    inputString = UTILS.removeDollarFormatSpecialCharacters(inputString);
    
    if (VERIFIER.checkField(inputString, UTILS.getCurrencyName())) {
      try {
        value = new Double(inputString);
      } catch (NumberFormatException e) {
        UTILS.invalidNumber("Invalid Expenses expenses(" + inputString + ").");
      }
    }

    LOGGER.trace("Leaving the Expenses getExpenses().");
    LOGGER.trace(" returning <" + value + ">");

    return value;
  }

  /** 
   * Save the properties of this object.
   * 
   * @since1.0
   * 
   */
  public void saveProperties(final XmlWriter xmlWriter) {
    LOGGER.trace("Entering the Expenses saveProperties() with no parameters.");
    
    xmlWriter.putNode("Expenses");
    
    xmlWriter.putDoubleAttribute(EXPENSES_PROPERTY, getExpenses());   

    xmlWriter.putClosingNode("Expenses");
      
    LOGGER.trace("Leaving the Expenses saveProperties().");
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
  public void setExpenses(final String input) {
    LOGGER.trace("Entering the Expenses setExpenses() with input parameter.");
    
    expenses.setText(input);
    final InputVerifier verifier = expenses.getInputVerifier();
    verifier.verify(expenses);
  }
  
  /** 
   * Check the user input for a focus listener.
   * 
   * @since  1.0
   * @return double
   */
  public boolean expensesHasFocusListener() {
    LOGGER.trace("Entering the Expenses expensesHasFocusListener() with no parameters.");
    
    boolean hasFocusListener = false;
    
    final FocusListener[] focusListeners = expenses.getFocusListeners();
    if (focusListeners.length == NUMBER_OF_FOCUS_LISTENERS) { // 2 standard focus listerns + ours
      hasFocusListener = true;
    }
 
    return hasFocusListener;
  }
  
  public void writePdf(final String name, final PDDocument doc) {

	  writePdf(doc, Entry.ANNUAL_EXPENSES.toString(), UTILS.getDollarFormat(getExpenses()));
  }
}
