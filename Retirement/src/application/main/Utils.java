package application.main;

import java.util.GregorianCalendar;

import application.system.ApplicationLogger;

/**
 * The utilities class for the inputs view.
 * 
 * @author      Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class Utils {

  /**
   * The class LOGGER (log4j2).
   * 
   * @var ApplicationLogger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();
 
  /** 
   * Get the current Year from the system.
   * 
   * @since  1.0
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
}