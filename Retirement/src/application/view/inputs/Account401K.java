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
public class Account401K extends Account {
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
  public static final String BALANCE_PROPERTY = "account401kBalance";
  
  /**
   * Set the growth rate property to its value.
   * 
   * @var String GROWTH_RATE_PROPERTY
   */
  public static final String GROWTH_RATE_PROPERTY = "account401kGrowthRate";
  
  /**
   * Set the annual contributions property to its value.
   * 
   * @var String ANNUAL_CONTRIBUTIONS_PROPERTY
   */
  public static final String ANNUAL_CONTRIBUTIONS_PROPERTY = "account401kAnnualContributions";

  /**
   * Set annual contributions if the checkbox is set property.
   * 
   * @var String ONLY_WHILE_SALARY_PROPERTY
   */
  public static final String ONLY_WHILE_SALARY_PROPERTY = "account401kOnlyWhileSalary";
  
  /**
   * Set the start  public abstract void writePdf(PDDocument doc); 
 withdrawal age property to its value.
   * 
   * @var String START_WITHDRAWAL_AGE_PROPERTY
   */
  public static final String START_WITHDRAWAL_AGE_PROPERTY = "account401kStartWithdrawalAge";
  
  /**
   * Set the number of withdrawals property to its value.
   * 
   * @var String NUMBER_OF_WITH_WITHDRAWALS_PROPERTY
   */
  public static final String NUMBER_OF_WITHDRAWALS_PROPERTY = "account401kNumberOfWithdrawals";

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
  private static final String NAME = Header.ACCOUNT_401K.toString();
   
  /** 
   * No properties are passed into this constructor.
   * 
   * @since  1.0
   */
  public Account401K() {
    super();

    LOGGER.trace("Entering the Account401K constructor with no parameters.");

    LOGGER.trace("Leaving the Account401K constructor.");
  }
  

  /**    public abstract void writePdf(PDDocument doc); 

   * Get the properties for the account.
   * 
   * @param  props     The properties for the application
   * @since  1.0ACCOUNT_401K
   */
  public Account401K(final XmlReader xmlReader) {
    super();
        
    LOGGER.trace("Entering the Account401K constructor.");
    LOGGER.trace(XMLREADER_EQUALS + xmlReader + ">.");
        
    LOGGER.trace("Leaving the Account401K constructor.");
  }
 
  /** 
   * Get the name of the tab.
   * 
   * @since  1.0
   * @return String
   */
  public String getName() {
    LOGGER.trace("Called the Account401K getName() with no parameters.");
        
    LOGGER.trace("Leaving the Account401K getName().");
    LOGGER.trace(RETURNING + NAME + ">");
        
    return NAME;
  }
 }