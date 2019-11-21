package application.model;

/**
 * Implementation of a pension account.
 * Don't implement deposit() or withdraw(); don't need to add implements Accounts.
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 2/5/2016
 *
 */
public class Pension extends Account {


  /**
   * This method builds the initial data structure. 
   * It assumes that the monthly Amount doesn't increase.
   *
   * @param monthlyAmount The current yearly expenses.
   * @param startAge      The age that the pension starts
   * @param inflationAdjusted Is the yearly amount adjusted for inflation
   * @param inflation     The expected value of inflation
   * @param currentAge    The current age of the person
   * @param currentYear   The current year of the person
   * @param deathAge      The expected death age of the person
   */
  public Pension(final double monthlyAmount, final int startAge, 
                 final boolean inflationAdjusted, final double inflation,
                 final int currentAge, final int currentYear, 
                 final int deathAge) {
    super();
    
    if (inflationAdjusted) {
      initialize(monthlyAmount * 12, inflation, currentAge, currentYear, deathAge);
    } else {    
      initialize(monthlyAmount * 12, 0.0, currentAge, currentYear, deathAge);
    }
    
    /* Show pension as income from currentAge to startAge */
    for (int i  = currentAge; i < startAge; i++) {
      setZeroBeginningValue(i);
    }        
  }
  
  /**
   * Get the taxability of this account.
   *
   * @return boolean
   */
  public boolean isTaxable() {
    return true;
  }
}
