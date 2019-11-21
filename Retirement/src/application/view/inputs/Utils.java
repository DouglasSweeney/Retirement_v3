package application.view.inputs;

import java.text.DecimalFormat;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

import application.system.ApplicationLogger;

/**
 * The utilities class for the inputs view.
 * 
 * @author      Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class Utils {
  private transient MyJDialog dialog;
  
  /**
   * Set the LOGGER property to its value.
   * 
   * @var Application_Logger LOGGER
   */
  private static final String INPUT_EQUALS = "    parameter input=<";
  /**
   * Set the LOGGER property to its value.
   * 
   * @var Application_Logger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();

  /**
   * Set the property of the input.
   * 
   * @var String CURRENCY_NAME
   */
  private static final String CURRENCY_NAME = "currency";

  /**
   * Set the property of the input.
   * 
   * @var String INTEGER_NAME
   */
  private static final String INTEGER_NAME = "integer";

  /**
   * Set the property of the input.
   * 
   * @var String PERCENT_NAME
   */
  private static final String PERCENT_NAME = "percent";

  /** 
   * Get the value for some of the JFormattedTextField's name.
   * 
   * @since  1.0
   * @return String
   */
  public String getCurrencyName() {
    LOGGER.trace("Entering the Utils getCurrencyName() with no parameters.");

    LOGGER.trace("Leaving the Utils getCurrencyName().");
    LOGGER.trace("    returning CURRENCY_NAME = <" + CURRENCY_NAME + ">.");

    return CURRENCY_NAME;
  }

  /** 
   * Get the value for some of the JFormattedTextField's name.
   * 
   * @since  1.0
   * @return String
   */
  public String getIntegerName() {
    LOGGER.trace("Entering the Utils getIntegerName() with no parameters.");

    LOGGER.trace("Leaving the Utils getIntegerName().");
    LOGGER.trace("    returning INTEGER_NAME = <" + INTEGER_NAME + ">.");

    return INTEGER_NAME;
  }

  /** 
   * Get the value for some of the JFormattedTextField's name.
   * 
   * @since  1.0
   * 
   * @return String
   */
  public String getPercentName() {
    LOGGER.trace("Entering the Utils getPercentName() with no parameters.");

    LOGGER.trace("Leaving the Utils getPercentName().");
    LOGGER.trace("    returning PERCENT_NAME = <" + PERCENT_NAME + ">.");

    return PERCENT_NAME;
  }

  /** 
   * Get the current Year.
   * 
   * @since  1.0
   * 
   * @return Integer
   */
  public Integer getCurrentYear() {
    Integer year;
    final GregorianCalendar calendar = new GregorianCalendar();
    LOGGER.trace("Entering the Utils getCurrentYear with no parameters.");

    year = calendar.get(GregorianCalendar.YEAR);

    LOGGER.trace("Leaving the Utils getCurrentYear().");
    LOGGER.trace("    returning year = <" + year.toString() + ">.");

    return year;
  }

  /**
   * Get the current Month.
   * 
   * @since  1.0
   * 
   * @return Integer
   */
  public Integer getCurrentMonth() {
    Integer month;
    final GregorianCalendar calendar = new GregorianCalendar();
    LOGGER.trace("Entering the Utils getCurrentMonth with no parameters.");

    month = calendar.get(GregorianCalendar.MONTH);
    month++;

    LOGGER.trace("Leaving the Utils getCurrentMonth().");
    LOGGER.trace("    returning year = <" + month.toString() + ">.");

    return month;
  }

  /** 
   * Convert an integer that is based on the specified format.
   * 
   * @since  1.0
   * 
   * @param  input The integer to format
   * 
   * @return String
   */
  public String getIntegerFormat(final int input) {
    LOGGER.trace("Entering the Utils getIntegerFormat().");
    LOGGER.trace(INPUT_EQUALS + input + ">.");

    final DecimalFormat defaultFormat = new DecimalFormat("###,###,###");

    defaultFormat.setMaximumIntegerDigits(25);

    LOGGER.trace("Leaving the Utils getIntegerFormat().");

    return defaultFormat.format(input);
  }

  /** 
   * Convert an integer that is based on the specified format.
   * 
   * @param  input The integer to format
   * 
   * @return The integer formated
   */
  public String getWholeIntegerFormat(final int input) {
    LOGGER.trace("Entering the Utils getIntegerFormat().");
    LOGGER.trace(INPUT_EQUALS + input + ">.");

    final DecimalFormat defaultFormat = new DecimalFormat("#########");

    defaultFormat.setMaximumIntegerDigits(25);

    LOGGER.trace("Leaving the Utils getIntegerFormat().");

    return defaultFormat.format(input);
  }

  /** 
   * Convert an double that is based on the specified format.
   * 
   * @param  input The double to format
   * 
   * @return String
   */
  public String removeDollarFormatSpecialCharacters(final String input) {
	  String numberString;
	
	  numberString = input.replace("$",  "");
	  numberString = numberString.replace(",", "");
	  
	  return numberString;
  }
  /** 
   * Convert an double that is based on the specified format.
   * 
   * @param  input The double to format
   * 
   * @return String
   */
  public String getDollarFormat(final double input) {
    LOGGER.trace("Entering the Utils getDollarFormat().");
    LOGGER.trace(INPUT_EQUALS + input + ">.");

    final DecimalFormat defaultFormat = new DecimalFormat("$###,###,##0.00");

    defaultFormat.setMaximumFractionDigits(2);
    defaultFormat.setMinimumFractionDigits(2);

    LOGGER.trace("Leaving the Utils getDollarFormat().");

    return defaultFormat.format(input);
  }

  /** 
   * Convert an percentage that is based on the specified format.
   * 
   * @param  input The double to format to a percentage
   * 
   * @return String
   */
  public String getPercentFormat(final double input) {
    LOGGER.trace("Entering the Utils getPercentFormat().");
    LOGGER.trace(INPUT_EQUALS + input + ">.");

    final DecimalFormat defaultFormat = new DecimalFormat("##.#%");
    
    defaultFormat.setMinimumFractionDigits(1);

    LOGGER.trace("Leaving the Utils getPercentFormat().");

    return defaultFormat.format(input);
  }

  /** 
     * Show a message about an invalid integer.
     * 
     * @param message The message to display
     * 
     * @since  1.0
     * 
     */
  public void invalidNumber(final String message) {
    LOGGER.trace("Entering the Utils invalidNumber().");
    LOGGER.trace(INPUT_EQUALS + message + ">.");
    
    //JOptionPane.showMessageDialog(null, "Invalid Number", message, JOptionPane.ERROR_MESSAGE);
    dialog = new MyJDialog(new JFrame(), "Invalid Number", message);
    // set the size of the window
    dialog.setSize(300, 150);

    LOGGER.trace("Leaving the Utils invalidNumber().");
  }
  
  public void invalidNumberDoClick() {
    dialog.doClick();
  }
}
