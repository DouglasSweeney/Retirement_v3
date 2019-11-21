package application.model;

import application.system.ResultsDataNode;

/**
 * Implementation of a Taxes object.
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 4/27/2016
 *
 */
public class Taxes extends Account {
  /**
   * The constant double zero.
   * 
   * @var double federalTaxRate
   */
  private static final double ZERO = 0.0;
  
  /**
   * The federal tax rate that is passed into the constructor.
   * 
   * @var double federalTaxRate
   */
  private final transient double federalTaxRate;
    
  /**
   * The state tax rate that is passed into the constructor.
   * 
   * @var double stateTaxRate 
   */
  private final transient double stateTaxRate;
    
  /**
   * This constructor builds the initial data structure.
   *
   * @param inflation         The expected yearly growth rate of the account.
   * @param currentAge        The current age of the person
   * @param currentYear       The current year of the person owning the account
   * @param deathAge          The expected death age of the person
   * @param federalTaxRate    The effective tax rate for computation of federal taxes
   * @param stateTaxRate      The tax rate for computation of state taxes
   */
  public Taxes(final double inflation, final int currentAge, 
               final int currentYear, final int deathAge,
               final double federalTaxRate, final double stateTaxRate) {
    super();
    
    this.federalTaxRate = federalTaxRate;
    this.stateTaxRate = stateTaxRate;
        
    initialize(0.0, 0.0, currentAge, currentYear, deathAge);        
  }
    
  /**
   * Calculate the yearly federal and state taxes.
   *
   * @param age           The current age
   * @param taxableAmount Amount that is taxable
   *
   * @return The total taxes due
   */
  public double compute(final int age, final double taxableAmount) {
    double federalTaxes = taxableAmount * federalTaxRate;
    double stateTaxes = taxableAmount * stateTaxRate;
    double totalTaxes;
                                      
    if (federalTaxes < ZERO) {
      federalTaxes = ZERO;
    }
        
    if (stateTaxes < ZERO) {
      stateTaxes = ZERO;
    }

    totalTaxes = federalTaxes + stateTaxes;
//System.out.println("taxableAmount: " + taxableAmount);        
//System.out.println("federalTax: " + federalTaxes);        
//System.out.println("stateTax: " + stateTaxes);        
//System.out.println("totalTax: " + totalTaxes);        
    set(age, taxableAmount, federalTaxes, stateTaxes, totalTaxes);
        
    return totalTaxes;
  }
    
  /**
   * Set the yearly federal & state taxes in the data structure.
   *
   * @param age           The current age
   * @param taxableAmount The amount that is taxable
   * @param federalTaxes  The federal taxes for current age
   * @param stateTaxes    The state taxes for current age
   * @param totalTaxes    The addition of federal and state taxes
   *
   */
  private void set(final int age, final double taxableAmount, 
                   final double federalTaxes, final double stateTaxes, 
                   final double totalTaxes) {
    ResultsDataNode node;
        
    node = getNodeBasedOnAge(age);
    if (node != null) {
      node.setTaxableWithdrawal(taxableAmount);
      node.setFederalTaxes(federalTaxes);
      node.setStateTaxes(stateTaxes);
      node.setEndingValue(totalTaxes);
    }
  }
}
