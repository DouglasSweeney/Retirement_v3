package application.view.inputs;

import application.main.XmlReader;
import application.system.ApplicationLogger;

/**
 * Creates the GUI tab for the 401K inputs.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class AccountIra extends Account {
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
  public static final String BALANCE_PROPERTY = "accountIraBalance";
  
  /**
   * Set the growth rate property to its value.
   * 
   * @var String GROWTH_RATE_PROPERTY
   */  
  public static final String GROWTH_RATE_PROPERTY = "accountIraGrowthRate";
  
  /**
   * Set the annual contributions property to its value.
   * 
   * @var String ANNUAL_CONTRIBUTIONS_PROPERTY
   */
  public static final String ANNUAL_CONTRIBUTIONS_PROPERTY = "accountIraAnnualContributions";

  /**
   * Set annual contributions if the checkbox is set property.
   * 
   * @var String ONLY_WHILE_SALARY_PROPERTY
   */
  public static final String ONLY_WHILE_SALARY_PROPERTY = "accountIraOnlyWhileSalary";
  
  /**
   * Set the start withdrawal age property to its value.
   * 
   * @var String START_WITHDRAWAL_AGE_PROPERTY
   */
  public static final String START_WITHDRAWAL_AGE_PROPERTY = "accountIraStartWithdrawalAge";
  
  /**
   * Set the number of withdrawals property to its value.
   * 
   * @var String NUMBER_OF_WITH_WITHDRAWALS_PROPERTY
   */
  public static final String NUMBER_OF_WITHDRAWALS_PROPERTY = "accountIraNumberOfWithdrawals";
  
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
  private static final String NAME = Header.IRA.toString();
  /** 
   * No properties are passed into this constructor.
   * 
   * @since  1.0
   */
  public AccountIra() {
    super();

    LOGGER.trace("Entering the AccountIRA constructor with no parameters.");

    LOGGER.trace("Leaving the AccountIRA constructor.");
  }

  /** 
   * Get the properties for the account.
   * 
   * @param  props     The properties for the application
   * @since  1.0
   */
  public AccountIra(final XmlReader xmlReader) {
    super();

    LOGGER.trace("Entering the AccountIRA constructor.");
    LOGGER.trace(XMLREADER_EQUALS + xmlReader + ">.");

    LOGGER.trace("Leaving the AccountIRA constructor.");
  }


  /** 
   * Get the name of the tab.
   * 
   * @since  1.0
   * 
   * @return String
   */
  public String getName() {
    LOGGER.trace("Called the AccountIRA getName() with no parameters.");

    LOGGER.trace("Leaving the AccountIRA getName().");
    LOGGER.trace(RETURNING + NAME + ">");

    return NAME;
  }
  
}
