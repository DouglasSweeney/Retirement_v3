package application.view.inputs;

import application.main.XmlReader;
import application.system.ApplicationLogger;

/**
 * Creates the GUI tab for the 403B inputs.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class Account403B extends Account {
  /**
   * Set the string for logging.
   * 
   * @var String RETURNING
   */
  public static final String RETURNING = "     returning <";
  /**
   * Set the balance property to its value.
   * 
   * @var String BALANCE_PROPERTY
   */
  public static final String BALANCE_PROPERTY = "account403BBalance";
  
  /**
   * Set the growth rate property to its value.
   * 
   * @var String GROWTH_RATE_PROPERTY
   */
  public static final String GROWTH_RATE_PROPERTY = "account403BGrowthRate";
  
  /**
   * Set the annual contributions property to its value.
   * 
   * @var String ANNUAL_CONTRIBUTIONS_PROPERTY
   */
  public static final String ANNUAL_CONTRIBUTIONS_PROPERTY =
      "account403BAnnualContributions";
  
  /**
   * Set annual contributions if the checkbox is set property.
   * 
   * @var String ONLY_WHILE_SALARY_PROPERTY
   */
  public static final String ONLY_WHILE_SALARY_PROPERTY = "account403BOnlyWhileSalary";

  /**
   * Set the start withdrawal age property to its value.
   * 
   * @var String START_WITHDRAWAL_AGE_PROPERTY
   */
  public static final String START_WITHDRAWAL_AGE_PROPERTY =
      "account403BStartWithdrawalAge";

  /**
   * Set the number of withdrawals property to its value.
   * 
   * @var String NUMBER_OF_WITH_WITHDRAWALS_PROPERTY
   */
  public static final String NUMBER_OF_WITHDRAWALS_PROPERTY =
      "account403BNumberOfWithdrawals";

  /**
   * Set the logger property to its value.
   * 
   * @var Application_Logger LOGGER
   */  
  private static final ApplicationLogger LOGGER = new ApplicationLogger();
  
  /**
   * Set the name of the tab.
   * 
   * @var String NAME
   */
  private static final String NAME = Header.ACCOUNT_403B.toString();
 /**
   * No properties are passed into this constructor.
   * 
   * @since  1.0
   */
  public Account403B() {
    super();

    LOGGER.trace("Entering the Account403B constructor with no parameters.");
  
    LOGGER.trace("Leaving the Account403B constructor.");
  }

  /**
 * Get the properties for the account.
 * 
 * @param  props     The properties for the application
 * @since  1.0
 */
  public Account403B(final XmlReader xmlReader) {
    super();

    LOGGER.trace("Entering the Account403B constructor.");
    LOGGER.trace("    xmlReader=<" + xmlReader + ">.");

    LOGGER.trace("Leaving the Account403B constructor.");
  }

  /**
   * Get the name of the tab.
   * 
   * @since  1.0
   * 
   * @return String
   */
  public String getName() {
    LOGGER.trace("Called the Account403B getName() with no parameters.");

    LOGGER.trace("Leaving the Account403B getName().");
    LOGGER.trace(RETURNING + NAME + ">");

    return NAME;
  }
  
  }
