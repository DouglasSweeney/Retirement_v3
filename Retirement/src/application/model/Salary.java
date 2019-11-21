package application.model;

/**
 * Implementation of a salary account. 
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 1/19/2016
 *
 */
public class Salary extends Account {
    
  /**
   * This method builds the initial data structure.
   *
   * @param salary        The current salary.
   * @param meritIncrease The expected yearly growth rate of the salary.
   * @param currentAge    The current age of the person
   * @param currentYear   The current year of the person owning the account
   * @param deathAge      The expected death age of the person
   * @param retirementAge The expected age that the owner retires
   */
  public Salary(final double salary, final double meritIncrease, 
                final int currentAge, final int currentYear, 
                final int deathAge, final int retirementAge) {
    super();
    
    int age;
  
    initialize(salary, meritIncrease, currentAge, currentYear, deathAge);        
        
    age = retirementAge;
    while (age < deathAge) {
      zeroBeginningValues(age);
      zeroEndingValues(age);
      age++;
    }
  }
}