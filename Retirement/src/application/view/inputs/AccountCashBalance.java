package application.view.inputs;

import application.main.XmlReader;
import application.system.ApplicationLogger;

/**
 * Creates the GUI tab for the CashBalance inputs.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class AccountCashBalance extends Account {
  /**
   * Set the string for logging.
   * 
   * @var String RETURNING
   */
  public static final String RETURNING = "     returning <";
  /**
   * Set the string for logging.
   * 
   * @var String PROPS_EQUALS
   */
  private static final String XMLREADER_EQUALS = "    xmlReader=<";
  
  /**
   * Set the balance property to its value.
   * 
   * @var String BALANCE_PROPERTY
   */
  public static final String BALANCE_PROPERTY = "accountCashBalanceBalance";

  /**
   * Set the growth rate property to its value.
   * 
   * @var String GROWTH_RATE_PROPERTY
   */
  public static final String GROWTH_RATE_PROPERTY = "accountCashBalanceGrowthRate";
  
  /**
   * Set the annual contributions property to its value.
   * 
   * @var String ANNUAL_CONTRIBUTIONS_PROPERTY
   */
  public static final String ANNUAL_CONTRIBUTIONS_PROPERTY =
      "accountCashBalanceAnnualContributions";
  
  /**
   * Set the annual contributions property to its value.
   * 
   * @var String ANNUAL_CONTRIBUTIONS_PROPERTY
   */
  public static final String ONLY_WHILE_SALARY_PROPERTY =
      "accountCashBalanceOnlyWhileSalary";
  
  /**
   * Set the start withdrawal age property to its value.
   * 
   * @var String START_WITHDRAWAL_AGE_PROPERTY
   */
  public static final String START_WITHDRAWAL_AGE_PROPERTY =
      "accountCashBalanceStartWithdrawalAge";
  
  /**
   * Set the number of withdrawals property to its value.
   * 
   * @var String NUMBER_OF_WITH_WITHDRAWALS_PROPERTY
   */
  public static final String NUMBER_OF_WITHDRAWALS_PROPERTY =
      "accountCashBalanceNumberOfWithdrawals";


  /**
   * Set the LOGGER property to its value.
   * 
   * @var Application_Logger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();

  /**
   * Set the NAME of the tab.
   * 
   * @var String NAME
   */
  private static final String NAME = Header.CASH_BALANCE.toString();
  /** 
   * No properties are passed into this constructor.
   * 
   * @since  1.0
   */
  public AccountCashBalance() {
    super();

    LOGGER.trace("Entering the AccountCashBalance constructor with no parameters.");

    LOGGER.trace("Leaving the AccountCashBalance constructor.");
  }

  /** 
   * Get the properties for the account.
   * 
   * @param  props     The properties for the application
   * @since  1.0
   */
  public AccountCashBalance(final XmlReader xmlReader) {
    super();

    LOGGER.trace("Entering the AccountCashBalance constructor.");
    LOGGER.trace(XMLREADER_EQUALS + xmlReader + ">.");

    LOGGER.trace("Leaving the AccountCashBalance constructor.");
  }

  /** 
   * Get the NAME of the tab.
   * 
   * @since  1.0
   * 
   * @return String
   */
  public String getName() {
    LOGGER.trace("Called the AccountCashBalance getName() with no parameters.");

    LOGGER.trace("Leaving the AccountCashBalance getName().");
    LOGGER.trace(RETURNING + NAME + ">");

    return NAME;
  }
  }
