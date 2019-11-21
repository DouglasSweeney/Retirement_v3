package application.model;

/**
 * Implementation of a savings account.
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 1/12/2016
 *
 */
public class SavingsModel extends Account implements Accounts {
  
 /**
  * The amount of taxable deposits.
  * 
  * @var double _taxableAmount 
  */
 @SuppressWarnings("unused")
 private double taxableAmount;
   
 /**
  * This method builds the initial data structure.
  *
  * @param balance      The current yearly expenses.
  * @param growthRate   The expected yearly growth rate of the expenses.
  * @param currentAge   The current age of the person
  * @param currentYear  The current year of the person
  * @param deathAge     The expected death age of the person
  */
 public SavingsModel(final double balance, final double growthRate, 
                  final int currentAge, final int currentYear, 
                  final int deathAge) {
   super();
   
   initialize(balance, growthRate, currentAge, currentYear, deathAge);        
 }
 
 /**
  * Deposit an amount and recompute the balance.
  * 
  * @param currentAge    The age of the person at the deposit year
  * @param depositAmount The amount of the deposit 
  */
 public void deposit(final int currentAge, final double depositAmount) {
   recomputeGrowthWithDeposit(currentAge, depositAmount);
 }
   
 /**
  * Withdraw an amount and recompute the balance.
  * 
  * @param currentAge       The age of the person at the withdrawal year
  * @param withdrawalAmount The amount of the withdrawal 
  *
  * @return Actual withdrawal amount
  */
 public double withdraw(final int currentAge, final double withdrawalAmount) {
   final int currentYear = convertAgeToYear(currentAge);
   double value;
   double amount;
   final double beginningValue = getBeginningValue(currentYear);
   final double deposits = getDeposits(currentYear);
   final double withdrawals = getWithdrawals(currentYear);
       
   value = beginningValue + deposits - withdrawals;
       
   if (withdrawalAmount <= value) {
     amount = withdrawalAmount; 
   } else {
     amount = value;
   }

   recomputeGrowthWithWithdrawal(currentAge, amount);

   return amount;
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

