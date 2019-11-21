package application.view.inputs;

import application.main.XmlReader;
import application.system.ApplicationLogger;

/**
 * Creates the GUI tab for the Roth inputs.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class AccountRoth extends Account {
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
   * Set the balance property to its value.
   * 
   * @var String BALANCE_PROPERTY
   */
  public static final String BALANCE_PROPERTY = "accountRothBalance";
 
  /**
   * Set the growth rate property to its value.
   * 
   * @var String GROWTH_RATE_PROPERTY
   */
  public static final String GROWTH_RATE_PROPERTY = "accountRothGrowthRate";
  
  /**
   * Set the annual contributions property to its value.
   * 
   * @var String ANNUAL_CONTRIBUTIONS_PROPERTY
   */
  public static final String ANNUAL_CONTRIBUTIONS_PROPERTY = "accountRothAnnualContributions";

  /**
   * Set annual contributions if the checkbox is set property.
   * 
   * @var String ONLY_WHILE_SALARY_PROPERTY
   */
  public static final String ONLY_WHILE_SALARY_PROPERTY = "accountRothOnlyWhileSalary";
   
  /**
   * Set the start withdrawal age property to its value.
   * 
   * @var String START_WITHDRAWAL_AGE_PROPERTY
   */
  public static final String START_WITHDRAWAL_AGE_PROPERTY = "accountRothStartWithdrawalAge";
  
  /**
   * Set the number of withdrawals property to its value.
   * 
   * @var String NUMBER_OF_WITH_WITHDRAWALS_PROPERTY
   */
  public static final String NUMBER_OF_WITHDRAWALS_PROPERTY = "accountRothNumberOfWithdrawals";

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
  private static final String NAME = Header.ROTH.toString();

  /** 
   * No properties are passed in(as normal). This allows
   * the model to get access to the inputs.
   * 
   * @since  1.0
   */
  public AccountRoth() {
    super();

    LOGGER.trace("Called the Roth constructor with no parameters.");

    LOGGER.trace("Leaving the Roth constructor.");
  }


  /** 
   * Get the properties for the Roth account.
   * 
   * @param  props     The properties for the application
   * @since  1.0
   */
  public AccountRoth(final XmlReader xmlReader) {
    super();

    LOGGER.trace("Called the Roth constructor.");
    LOGGER.trace(XMLREADER_EQUALS + xmlReader + ">.");

    LOGGER.trace("Leaving the Roth constructor.");
  }
  /** 
   * Get the name of the tab.
   * 
   * @since  1.0
   * 
   * @return String
   */
  public String getName() {
    LOGGER.trace("Entering the Roth getName() with no parameters.");

    LOGGER.trace("Leaving the Roth getName().");
    LOGGER.trace(RETURNING + NAME + ">");

    return NAME;
  }
}
