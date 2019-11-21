package application.view.outputs;

import java.text.DecimalFormat;

import application.system.ApplicationLogger;

/**
 * Implementation of an utilities class.
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 1/21/2016
 *
 */
public class Utils {
  /**
   * The class LOGGER (log4j2).
   * 
   * @var ApplicationLogger LOGGER
   */
    private transient final ApplicationLogger logger = new ApplicationLogger();
    
  /** 
     * Convert an integer that is based on the specified format.
     * 
     * @since  1.0
     * @param  input    The value to format
     * @return A String of the integer input
     */
  public String getIntegerFormat(final int input) {
    logger.trace("Entering the Utils getIntegerFormat().");
    logger.trace("    Parameter input=<" + input + ">.");

    final DecimalFormat defaultFormat = new DecimalFormat("###,###,###");

    defaultFormat.setMaximumIntegerDigits(25);

    logger.trace("Leaving the Utils getIntegerFormat().");
    
    return defaultFormat.format(input);
  }
  
  /** 
     * Convert an double that is based on the specified format.
     * 
     * @since  1.0
     * 
     * @param input  The value to format
     * /CommentSize" />
     * @return A String
     */
  public String getDollarFormat(final double input) {
    logger.trace("Entering the Utils getDollarFormat().");
    logger.trace("    Parameter input=<" + input + ">.");

    final DecimalFormat defaultFormat = new DecimalFormat("###,###,##0.00");

    defaultFormat.setMaximumFractionDigits(2);
    defaultFormat.setMinimumFractionDigits(2);

    logger.trace("Leaving the Utils getDollarFormat().");
    
    return defaultFormat.format(input);
  }

  /** 
     * Convert an percentage that is based on the specified format.
     * 
     * @since  1.0
     * 
     * @param input The double to format
     * 
     * @return A string of the passed in value
     */
  public String getPercentFormat(final double input) {
    logger.trace("Entering the Utils getPercentFormat().");
    logger.trace("    Parameter input=<" + input + ">.");

    final DecimalFormat defaultFormat = new DecimalFormat("##%");

    logger.trace("Leaving the Utils getPercentFormat().");

    return defaultFormat.format(input);
  }
}