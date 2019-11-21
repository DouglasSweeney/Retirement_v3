package application.model;

/**
 * Implementation of a yearly expenses. 
 * 
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 10/29/2014
 *
 */
public class Expenses extends Account {
    
  /**
   * This method builds the initial data structure.
   *
   * @param expense      The current yearly expenses.
   * @param growthRate   The expected yearly growth rate of the expenses.
   * @param currentAge   The current age of the person
   * @param currentYear  The current year of the person
   * @param deathAge     The expected death age of the person
   *
   */
  public Expenses(final double expense, final double growthRate, 
                  final int currentAge, final int currentYear, 
                  final int deathAge) {
  
    super();
    
    initialize(expense, growthRate, currentAge, currentYear, deathAge); 
  }
}
