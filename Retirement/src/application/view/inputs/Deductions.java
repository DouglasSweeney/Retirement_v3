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
* Creates the GUI tab for the annual tax deduction inputs.
* 
* @author Doug Sweeney
* @version     1.0
* @since       1.0
*/
public class Deductions extends InputObject {
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
   * Set the deductions property to its value.
   * 
   * @var String DEDUCTIONS_PROPERTY
   */
  public static final String DEDUCTIONS_PROPERTY = "currentDeductions";

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
  private static final String NAME = Header.DEDUCTIONS.toString();

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
   * Set the input for the deductions..
   * 
   * @var JFormattedTextField deductions
   */
  private static JFormattedTextField deductions;
  
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
   * @since  1.0
   */
  public Deductions() {
    super();

    LOGGER.trace("Called the Deductions constructor with no parameters.");

    LOGGER.trace("Leaving the Deductions constructor.");
  }

  /** 
   * Remember the properties.
   * 
   * @param  props     The properties for the application
   * @since  1.0
   */
  public Deductions(final XmlReader xmlReader) {
    super();

    LOGGER.trace("Called the Deductions constructor.");
    LOGGER.trace(XMLREADER_EQUALS + xmlReader + ">.");

    this.xmlReader = xmlReader;

    LOGGER.trace("Leaving the Deductions constructor.");
  }

  /** 
   * Get the input tab panel for the tab.
   * 
   * @since  1.0
   * 
   * @return JPanel
   */
  public JPanel createPanel() {
    LOGGER.trace("Entering the Deductions createPanel() with no parameters.");

    final JPanel jPanel = new JPanel(new GridLayout(1, 2));
    final JLabel label = new JLabel(Entry.ANNUAL_DEDUCTIONS.toString());
    jPanel.add(label);
    deductions = new JFormattedTextField();
    deductions.setText(UTILS.getDollarFormat(xmlReader.getDoubleProperty(DEDUCTIONS_PROPERTY)));
    deductions.setName(UTILS.getCurrencyName());
    deductions.setInputVerifier(VERIFIER);
    deductions.addFocusListener(FOCUS);
    jPanel.add(deductions);

    LOGGER.trace("Leaving the Deductions createPanel().");
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
    LOGGER.trace("Entering the Deductions getName() with no parameters.");

    LOGGER.trace("Leaving the Deductions getName().");
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
  public double getDeductions() {
    LOGGER.trace("Entering the Deductions getDeductions() with no parameters.");
    String inputString = deductions.getText();
    double value = -987.0; // -987.0 for invalid field

    // valid input field
    inputString = UTILS.removeDollarFormatSpecialCharacters(inputString);
    if (VERIFIER.checkField(inputString, UTILS.getCurrencyName())) {
      try {
        value = new Double(inputString);
      } catch (NumberFormatException e) {
        UTILS.invalidNumber("Invalid Deductions deduction(" + inputString + ").");
      }
    }

    LOGGER.trace("Leaving the Deductions getDeductions().");
    LOGGER.trace("     returning <" + value + ">");

    return value;
  }

  /** 
   * Save the properties of this object.
   * 
   * @since  1.0
   *  
   */
  public void saveProperties(final XmlWriter xmlWriter) {
    LOGGER.trace("Entering the Deductions saveProperties() with no parameters.");
    
    xmlWriter.putNode("Deductions");
    
    xmlWriter.putDoubleAttribute(DEDUCTIONS_PROPERTY, getDeductions());   

    xmlWriter.putClosingNode("Deductions");
      
    LOGGER.trace("Leaving the Deductions saveProperties().");
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
  public void setDeductions(final String input) {
    LOGGER.trace("Entering the Deductions setDeductions() with input parameter.");
    
    deductions.setText(input);
    final InputVerifier verifier = deductions.getInputVerifier();
    verifier.verify(deductions);
  }
  
  /** 
   * Check the user input for a focus listener.
   * 
   * @since  1.0
   * @return double
   */
  public boolean deductionsHasFocusListener() {
    LOGGER.trace("Entering the Deductions deductionsHasFocusListener() with no parameters.");
    
    boolean hasFocusListener = false;
    
    final FocusListener[] focusListeners = deductions.getFocusListeners();
    if (focusListeners.length == NUMBER_OF_FOCUS_LISTENERS) { // 2 standard focus listerns + ours
      hasFocusListener = true;
    }
 
    return hasFocusListener;
  } 
  
  public void writePdf(final String name, final PDDocument doc) {

	  writePdf(doc, Entry.ANNUAL_DEDUCTIONS.toString(), UTILS.getDollarFormat(getDeductions()));
  }
}
