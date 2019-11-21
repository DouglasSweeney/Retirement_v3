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
 * Creates the GUI tab for the Salary inputs.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class Salary extends InputObject {
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
  private static final String XMLREADER_EQUALS = "    xmlReader=<";
  
  /**
   * Set the annual salary property to its value.
   * 
   * @var String SALARY_PROPERTY
   */
  public static final String SALARY_PROPERTY = "salaryAnnualSalary";
  
  /**
   * Set the salary merit increase property to its value.
   * 
   * @var String MERIT_INCREASE_PROPERTY
   */
  public static final String MERIT_INCREASE_PROPERTY = "salaryMeritIncrease";

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
  private static final String NAME = Header.SALARY.toString();
  
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
   * Set the input for the salary.
   * 
   * @var JFormattedTextField salary
   */
  private static JFormattedTextField salary;

  /**
   * Set the input for the merit increase.
   * 
   * @var JFormattedTextField merit increase
   */
  private static ComboBoxList meritIncrease;

  /**
   * Set the input field VERIFIER.
   * 
   * @var Verifier VERIFIER
   */
  private static final Verifier VERIFIER = new Verifier();

  /** 
  * No properties are passed in (as normal). This allows
  * the model to get access to the inputs.
  * 
  * @since  1.0
  */
  public Salary() {
    super();

    LOGGER.trace("Called the Salary constructor with no parameters.");

    LOGGER.trace("Leaving the Salary constructor.");
  }

  /** 
   * Get the properties for the Salary.
   * 
   * @param  props     The properties for the application
   * @since  1.0
   */
  public Salary(final XmlReader xmlReader) {
    super();

    LOGGER.trace("Called the Salary constructor.");
    LOGGER.trace(XMLREADER_EQUALS + xmlReader + ">.");

    this.xmlReader = xmlReader;

    LOGGER.trace("Leaving the Salary constructor.");
  }

  /** 
   * Get the input tab panel for the tab.
   * 
   * @since  1.0
   * 
   * @return JPanel
   */
  public JPanel createPanel() {
    LOGGER.trace("Entering the Salary createPanel() with no parameters.");

    final JPanel jPanel = new JPanel(new GridLayout(2, 2));

    JLabel label = new JLabel(Entry.CURRENT_SALARY.toString());
    jPanel.add(label);
    salary = new JFormattedTextField();
    salary.setText(UTILS.getDollarFormat(xmlReader.getDoubleProperty(SALARY_PROPERTY)));
    salary.setName(UTILS.getCurrencyName());
    salary.setInputVerifier(VERIFIER);
    salary.addFocusListener(FOCUS);
    jPanel.add(salary);

    label = new JLabel(Entry.AVERAGE_MERIT_INCREASE.toString());
    jPanel.add(label);
    meritIncrease = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_30_BY_ZERO_POINT_FIVE,
        UTILS.getPercentFormat(xmlReader.getDoubleProperty(MERIT_INCREASE_PROPERTY)));
    jPanel.add(meritIncrease.getComboBox());

    LOGGER.trace("Leaving the Salary createPanel().");
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
    LOGGER.trace("Entering the Salary getName() with no parameters.");
    
    LOGGER.trace("Leaving the Salary getName().");
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
  public double getSalary() {
    LOGGER.trace("Entering the Salary getSalary() with no parameters.");

    String inputString = salary.getText();
    double value = -987.0; // -987.0 for invalid field

    // valid input field
    inputString = UTILS.removeDollarFormatSpecialCharacters(inputString);
    
    if (VERIFIER.checkField(inputString, UTILS.getCurrencyName())) {
      try {
        value = new Double(inputString);
      } catch (NumberFormatException e) {
        UTILS.invalidNumber("Invalid Salary (" + inputString + ").");
      }
    }

    LOGGER.trace("Leaving the Salary getSalary().");
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
  public double getMeritIncrease() {
    LOGGER.trace("Entering the Salary getMeritIncrease() with no parameters.");

    final String inputString = meritIncrease.getValue();
    final String inputString2 = inputString.replaceAll("%", "");
    final String inputString3 = inputString2.replaceAll(",", "");
    double value = -987.0; // -987.0 for invalid field

    // valid input field
    if (VERIFIER.checkField(inputString3, UTILS.getPercentName())) {
      try {
        value = Double.valueOf(inputString3);
      } catch (NumberFormatException e) {
        UTILS.invalidNumber("Invalid Salary Monthly Amount(" + inputString + ").");
      }
      value /= 100;
    }

    LOGGER.trace("Leaving the Salary getMeritIncrease().");
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
    LOGGER.trace("Entering the Salary saveProperties() with no parameters.");

    xmlWriter.putNode("Salary");
    
    xmlWriter.putDoubleAttribute(SALARY_PROPERTY, getSalary());   
    xmlWriter.putDoubleAttribute(MERIT_INCREASE_PROPERTY, getMeritIncrease());

    xmlWriter.putClosingNode("Salary");
      
    LOGGER.trace("Leaving the Salary saveProperties().");
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
  public void setSalary(final String input) {
    LOGGER.trace("Entering the Pension setStartingAge() with input parameter.");
    
    salary.setText(input);
    final InputVerifier verifier = salary.getInputVerifier();
    verifier.verify(salary);
  }
  
  /** 
   * Check the user input for a focus listener.
   * 
   * @since  1.0
   * @return double
   */
  public boolean salaryHasFocusListener() {
    LOGGER.trace("Entering the Pension startingAgeHasFocusListener() with no parameters.");
    
    boolean hasFocusListener = false;
    
    final FocusListener[] focusListeners = salary.getFocusListeners();
    if (focusListeners.length == NUMBER_OF_FOCUS_LISTENERS) { // 2 standard focus listerns + ours
      hasFocusListener = true;
    }
 
    return hasFocusListener;
  }
  
  public void writePdf(final String name, final PDDocument doc) {

	  writePdf(doc, Entry.CURRENT_SALARY.toString(), UTILS.getDollarFormat(getSalary()),
			        Entry.AVERAGE_MERIT_INCREASE.toString(), UTILS.getPercentFormat(getMeritIncrease()));
  }
}
