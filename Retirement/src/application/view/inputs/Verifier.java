package application.view.inputs;

import java.awt.Toolkit;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import application.system.ApplicationLogger;

/**
 * Verifies the user input.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class Verifier extends InputVerifier {
  private transient MyJDialog dialog;

  /**
   * Set the LOGGER property to its value.
   * 
   * @var Application_Logger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();
  
  /**
   * Create an instance of the UTILS.
   * 
   * @var Utils UTILS
   */ 
  private  static final Utils UTILS = new Utils();

  /** 
   * Checks the validity of the input & beeps if invalid input.
   * 
   * @param input      the value of the user input
   * @param formatType specifies the format for the field - double or integer.
   * @return           boolean
   */
  private boolean shouldYieldFocus(final String input, final String formatType) {
    LOGGER.trace("Entering the InputVerifier shouldYieldFocus.");
    LOGGER.trace("    input=<" + input + ">.");
    LOGGER.trace("    formatType=<" + formatType + ">.");

    final boolean inputOk = checkField(input, formatType);

    LOGGER.trace("Leaving the InputVerifier shouldYieldFous().");
    LOGGER.trace("    returning inputOK = <" + inputOk + ">.");
    
    return inputOk;
  }

  /** 
   * Called to check the validity of the user input when the field starts 
   * to lose focus (i.e. the TAB key is depressed).
   * 
   * @param input      the field of the user input
   * @return           boolean
   */
  public boolean verify(final JComponent input) {
    LOGGER.trace("Entering the InputVerifier verify().");

    boolean valid;
    final JFormattedTextField source = (JFormattedTextField) input;
    final String formatType = input.getName();
    String inputString = source.getText();
    
    if (formatType.equals("currency")) {
        inputString = UTILS.removeDollarFormatSpecialCharacters(inputString);
    }
    
    valid = shouldYieldFocus(inputString, formatType);
    if (!valid) {
      Toolkit.getDefaultToolkit().beep();
      dialog = new MyJDialog(new JFrame(), "Invalid Number", "Invalid Number(2)");
      dialog.setSize(200, 75);
      source.selectAll();
    }
    LOGGER.trace("Leaving the InputVerifier verify().");
    LOGGER.trace("    returning valid = <" + valid + ">.");
    return valid;
  }

  /** 
   * Verify the format of the field input and call the appropriate method.
   * 
   * @param input      the String representation of the field value.
   * @param formatType the field type(double or integer)
   * @return           boolean
   */
  public boolean checkField(final String input, final String formatType) {
    LOGGER.trace("Entering the InputVerifier checkField().");
    LOGGER.trace("    input=<" + input + ">.");
    LOGGER.trace("    formatType=<" + formatType + ">.");

    boolean valid = false;

    if (formatType.equals(UTILS.getCurrencyName())) {
      valid = checkCurrencyField(input);
    } else {
      if (formatType.equals(UTILS.getIntegerName())) {
        valid = checkIntegerField(input);
      } else {
        if (formatType.equals(UTILS.getPercentName())) {
          valid = checkPercentField(input);
        }
      }
    }

    LOGGER.trace("Leaving the InputVerifier checkField().");
    LOGGER.trace("    returning valid = <" + valid + ">.");

    return valid;
  }

  /** 
   * Verify the field value. It was previously verified to be a double.
   * 
   * 
   * @param input      the String representation of the field value.
   * @return           boolean
   */
  private boolean checkCurrencyField(final String input) {
    LOGGER.trace("Entering the InputVerifier checkCurrencyField().");

    boolean wasValid = false;
    @SuppressWarnings("unused") double amount;

    try {
      amount = Double.valueOf(input.replaceAll(",", ""));  // Try to create a double
      wasValid = true;
    } catch (NumberFormatException e) {
      wasValid = false;
    }

    LOGGER.trace("Leaving the InputVerifier checkCurrencyField().");
    LOGGER.trace("    returning wasValid = <" + wasValid + ">.");

    return wasValid;    // Return the validity of the field input
  }

  /** 
   * Verify the field value.
   * 
   * @param input      the String representation of the field value.
   * @return           boolean
   */
  private boolean checkIntegerField(final String input) {
    LOGGER.trace("Entering the InputVerifier checkIntegerField().");

    boolean wasValid = false;
    String input2 = "";
    @SuppressWarnings("unused") double amount;

    try {
      input2 = input.replaceAll(",", "");  // Removed the field representation of the commas
      amount = Integer.valueOf(input2);  // Try to create a double
      wasValid = true;
    } catch (NumberFormatException e) {
      wasValid = false;
    }

    LOGGER.trace("Leaving the InputVerifier checkIntegerField().");
    LOGGER.trace("    returning wasValid = <" + wasValid + ">.");

    return wasValid;    // Return the validity of the field input
  }

  /** 
   * Verify the field value.
   * 
   * 
   * @param input      the String representation of the field value.
   * @return           boolean
   */
  private boolean checkPercentField(final String input) {
    LOGGER.trace("Entering the InputVerifier checkPercentField().");

    boolean wasValid = false;
    @SuppressWarnings("unused") double amount;

    try {

      amount = new Double(input.replaceAll("%", ""));  // Try to create a double
      wasValid = true;
    } catch (NumberFormatException e) {
      wasValid = false;
    }

    LOGGER.trace("Leaving the InputVerifier checkPercetField().");
    LOGGER.trace("    returning wasValid = <" + wasValid + ">.");

    return wasValid;    // Return the validity of the field input
  }
}
