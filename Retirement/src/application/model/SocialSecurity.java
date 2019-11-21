package application.model;


/**
 * Implementation of a pension account.
 * Don't implement deposit() or withdraw(); don't need to add implements Accounts.
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 2/5/2016
 *
 */
public class SocialSecurity extends Account {

  /**
   * The amount of taxable deposits.
   * 
   * @var double taxableAmount 
   */
  @SuppressWarnings("unused")
  private double taxableAmount;

  /**
   * This method builds the initial data structure. 
   *
   * @param monthlyAmount The current yearly expenses.
   * @param startAge      The age that social security starts
   * @param inflation     The expected annual inflation rate 
   * @param currentAge    The current age of the person
   * @param currentYear   The current year of the person
   * @param deathAge      The expected death age of the person
   *
   */
  public SocialSecurity(final double monthlyAmount, final int startAge, 
                        final double inflation, final int currentAge, 
                        final int currentYear, final int deathAge) {
    super();
        
    initialize(monthlyAmount * 12, inflation, currentAge, currentYear, deathAge);

    
    /* Modify the Social Security startAge->startAge + currentAge values */
    int age2 = startAge;
    for (int i =  currentAge; i < startAge; age2++, i++) {
      setEndingValue(age2, getEndingValueBasedOnAge(i));
    }
    for (int i = startAge; i < deathAge - 1; i++) {
      computeEndingValue(i + 1, getEndingValueBasedOnAge(i), inflation);
    }

    /* Modify the first years til collection */
    for (int i = currentAge; i < startAge; i++) {
      setZeroEndingValue(i);
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
