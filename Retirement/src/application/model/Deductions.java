package application.model;

/**
 * Implementation of a yearly deductions.
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 4/26/2016
 *
 */
public class Deductions extends Account {

  /**
   * The constructor builds the initial data structure.
   *
   * @param deductions   The current deductions.
   * @param growthRate   The expected yearly growth rate of the deductions.
   * @param currentAge   The current age of the deductions
   * @param currentYear  The current year of the person owning the account
   * @param deathAge     The expected death age of the person
   *
   */
  public Deductions(final double deductions, final double growthRate, 
                    final int currentAge, final int currentYear, 
                    final int deathAge) {
    super();
    
    initialize(deductions, growthRate, currentAge, currentYear, deathAge);  
  }
}
