package application.view.inputs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import javax.swing.InputVerifier;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;

import application.main.ExecuteObject;
import application.main.XmlReader;
import application.main.XmlWriter;
import application.system.ApplicationLogger;

/**
 * Creates the GUI tab for the Pension inputs.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class Pension extends InputObject implements ActionListener {
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
   * Set the string for logging.
   * 
   * @var String XMLREADER_EQUALS
   */
  private static final String XMLREADER_EQUALS = "    XmlReader=<";
  
  /**
   * Set the start age property to its value.
   * 
   * @var String START_AGE_PROPERTY
   */
  public static final String START_AGE_PROPERTY = "pensionStartAge";
  
  /**
   * Set the monthly amount property to its value.
   * 
   * @var String MONTHLY_AMOUNT_PROPERTY
   */
  public static final String MONTHLY_AMOUNT_PROPERTY = "pensionMonthlyAmount";
  
  /**
   * Set the inflation adjusted property to its value.
   * 
   * @var String INFLATION_ADJUSTED
   */
  public static final String INFLATION_ADJUSTED_PROPERTY = "pensionInflationAdjusted";

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
  private static final String NAME = Header.PENSION.toString();
  
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
   * Set the input for the monthly amount.
   * 
   * @var JFormattedTextField inflationAdjusted
   */
  private static JCheckBox inflationAdjusted;

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
  public Pension() {
    super();

    LOGGER.trace("Called the Pension constructor with no parameters.");

    LOGGER.trace("Leaving the Pension constructor.");
  }

  /** 
   * Get the properties for the Pension account.
   * 
   * @param  props     The properties for the application
   * @since  1.0
   */
  public Pension(final XmlReader xmlReader) {
    super();

    LOGGER.trace("Called the Pension constructor.");
    LOGGER.trace(XMLREADER_EQUALS + xmlReader + ">.");

    this.xmlReader = xmlReader;
    LOGGER.trace("Leaving the Pension constructor.");
  }

  /** 
   * Get the input tab panel for the tab.
   * 
   * @since  1.0
   * 
   * @return JPanel
   */
  public JPanel createPanel() {
    LOGGER.trace("Entering the Pension createPanel() with no parameters.");

    final JPanel jPanel = new JPanel(new GridLayout(3, 2));

    JLabel label = new JLabel(Entry.STARTING_AGE.toString());
    jPanel.add(label);
    startAge = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_95, UTILS.getWholeIntegerFormat(xmlReader.getIntegerProperty(START_AGE_PROPERTY)));
    jPanel.add(startAge.getComboBox());

    label = new JLabel(Entry.MONTHLY_AMOUNT.toString());
    jPanel.add(label);
    monthlyAmount = new JFormattedTextField();
    monthlyAmount.setText(UTILS.getDollarFormat(xmlReader.getDoubleProperty(MONTHLY_AMOUNT_PROPERTY)));
    monthlyAmount.setName(UTILS.getCurrencyName());
    monthlyAmount.setInputVerifier(VERIFIER);
    monthlyAmount.addFocusListener(FOCUS);
    jPanel.add(monthlyAmount);

    label = new JLabel(Entry.INFLATION_ADJUSTED.toString());
    jPanel.add(label);
    inflationAdjusted = new JCheckBox();
    inflationAdjusted.setSelected(xmlReader.getBooleanProperty(INFLATION_ADJUSTED_PROPERTY));
    inflationAdjusted.addActionListener(this);
    jPanel.add(inflationAdjusted);

    LOGGER.trace("Leaving the Pension createPanel().");
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
    LOGGER.trace("Entering the Pension getName() with no parameters.");

    LOGGER.trace("Leaving the Pension getName().");
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
    LOGGER.trace("Entering the Pension getStartAge() with no parameters.");

    String inputString = startAge.getValue();
    int value = -987;  // -987 for invalid field

    // valid input field
    inputString = inputString.replaceAll(",", "");
    if (VERIFIER.checkField(inputString, UTILS.getIntegerName())) {
      try {
        value = Integer.valueOf(inputString);
      } catch (NumberFormatException e) {
        UTILS.invalidNumber("Invalid Pension Start Age(" + inputString + ").");
      }
    }

    LOGGER.trace("Leaving the Pension getStartAge().");
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
    LOGGER.trace("Entering the Pension getMonthlyAmount() with no parameters.");

    String inputString = monthlyAmount.getText();
    double value = -987.0; // -987.0 for invalid field

    // valid input field
    inputString = UTILS.removeDollarFormatSpecialCharacters(inputString);
    
    if (VERIFIER.checkField(inputString, UTILS.getCurrencyName())) {
      try {
        value = new Double(inputString);
      } catch (NumberFormatException e) {
        UTILS.invalidNumber("Invalid Pension Monthly Amount(" + inputString + ").");
      }
    }

    LOGGER.trace("Leaving the Pension getMonthlyAmount().");
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
  public boolean isInflationAdjusted() {
    boolean value = false;

    LOGGER.trace("Entering the Pension getInflationAdjusted() with no parameters.");

    value = inflationAdjusted.isSelected();

    LOGGER.trace("Leaving the Pension getMonthlyAmount().");
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
    LOGGER.trace("Entering the Pension saveProperties() with no parameters.");
    
    xmlWriter.putNode("Pension");
    
    xmlWriter.putIntegerAttribute(START_AGE_PROPERTY, getStartAge());   
    xmlWriter.putDoubleAttribute(MONTHLY_AMOUNT_PROPERTY, getMonthlyAmount());
    xmlWriter.putBooleanAttribute(INFLATION_ADJUSTED_PROPERTY, isInflationAdjusted());

    xmlWriter.putClosingNode("Pension");

    LOGGER.trace("Leaving the Pension saveProperties().");
  }
  
  /** 
   * Perform the button clicked action.
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
			        Entry.START_WITHDRAWL_AGE.toString(), UTILS.getIntegerFormat(getStartAge()),
			        Entry.INFLATION_ADJUSTED.toString(), isInflationAdjusted() ? "true" : "false");
  }
}
