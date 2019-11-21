package application.model;

/**
 * Define an interface for an Account object.
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 1/12/2016
 *
 */
public interface Accounts {    
  /**
   * Deposit an amount and recompute the balance for the remaining years.
   * 
   * @param currentAge The age of the person at the deposit year
   * @param deposit    The amount of the deposit 
   *
   */
  void deposit(int currentAge, double deposit);
    
  /**
   * Withdraw a certain amount if the account has amount in it. 
   *
   * @param currentAge The age of the person at the withdrawal year
   * @param amount The requested amount of the withdrawal 
   *
   * @return The amount actually withdrawn
   */
  double withdraw(int currentAge, double amount);
    
  /**
   * Returns the taxable status of the account.
   * 
   * @return boolean
   */
  boolean isTaxable();
}
