package application.system;


/**
 * Implementation of a node in the data structure. 
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 10/30/2014
 *
 */

public class ResultsDataNode {

  /**
   * A member of a node..
   * 
   * @var int age 
   */
  private transient int age;
    
  /**
   * A member of a node..
   * 
   * @var int year 
   */
  private transient int year;
          
  /**
   * A member of a node.
   * 
   * @var int month 
   */
  private transient int month;
          
  /**
   * A member of a node..
   * 
   * @var double pension 
   */
  private transient double pension;
          
  /**
   * A member of a node.
   * 
   * @var double salary 
   */
  private transient double salary;
          
  /**
   * A member of a node.
   * 
   * @var double value401K 
   */
  private transient double value401K;
          
  /**
   * A member of a node.
   * 
   * @var double value403B 
   */
  private transient double value403B;
          
  /**
   * A member of a node.
   * 
   * @var double valueRoth 
   */
  private transient double valueRoth;
          
  /**
   * A member of a node.
   * 
   * @var double valueCashBalance 
   */
  private transient double valueCashBalance;
          
  /**
   * A member of a node.
   * 
   * @var double ssDougsValue 
   */
  private transient double ssDougsValue;
          
  /**
   * A member of a node.
   * 
   * @var double ssLauriesValue 
   */
  private transient double ssLauriesValue;
         
  /**
   * A member of a node.
   * 
   * @var double socialSecurity 
   */
  private transient double socialSecurity;
          
  /**
   * A member of a node.
   * 
   * @var double beginningValue 
   */
  private transient double beginningValue;
          
  /**
   * A member of a node.
   * 
   * @var double deposit 
   */
  private transient double deposit;
          
  /**
   * A member of a node.
   * 
   * @var double withdrawal 
   */
  private transient double withdrawal;
          
  /**
   * A member of a node.
   * 
   * @var double endingValue
   */
  private transient double endingValue;
          
  /**
   * A member of a node.
   * 
   * @var double dougsIncome 
   */
  private transient double dougsIncome;
          
  /**
   * A member of a node.
   * 
   * @var double lauriesIncome 
   */
  private transient double lauriesIncome;
          
  /**
   * A member of a node.
   * 
   * @var double taxableWithdrawal 
   */
  private transient double taxableWithdrawal;
          
  /**
   * A member of a node.
   * 
   * @var double federalTaxes 
   */
  private transient double federalTaxes;
          
  /**
   * A member of a node.
   * 
   * @var double stateTaxes 
   */
  private transient double stateTaxes;
          
  /**
   * A member of a node.
   * 
   * @var double $taxes 
   */
  private transient double taxes;
    
  /**
   * Sets some data in the node.
   *
   * @param aaYear Year of simulation
   * @param aaMonth Month of simulation
   * @param aaAge Age of simulation
   * @param aaBeginningValue Beginning (start of year) value of the account
   * @param aaEndingValue Ending (end of year) value of the account
   */
  public void aSet1(final int aaYear, final int aaMonth, 
                    final int aaAge, final double aaBeginningValue, 
                    final double aaEndingValue) {
    year = aaYear;
    month = aaMonth;
    age = aaAge;
    beginningValue = aaBeginningValue;
    withdrawal = 0.0;
    endingValue = aaEndingValue;
  }
          
  /**
   * Sets some data in the node.
   *
   * @param aaYear Year of simulation
   * @param aaAge  Age of simulation
   * @param aaBeginningValue Beginning (start of year) value of the account
   * @param aaEndingValue Ending (end of year) value of the account
   *
   */
  public void aSet2(final int aaYear, final int aaAge, 
                    final double aaBeginningValue, final double aaEndingValue) {
    year = aaYear;
    month = 0;
    age = aaAge;
    beginningValue = aaBeginningValue;
    withdrawal = 0.0;
    endingValue += aaEndingValue;
  }
          
  /**
   * Sets some data in the node.
   *
   * @param aaYear           Year of simulation 
   * @param aaDougsAge       Dougs age  
   * @param aaLauriesAge     Lauries age
   * @param aaSsDougsValue   Value of the account
   * @param aaSsLauriesValue Value of the account
   * @param aaEndingValue    Ending value (end of year) of the account
   *
   */
  
  public void aSet3(final int aaYear, final int aaDougsAge, 
                    final int aaLauriesAge, final double aaSsDougsValue, 
                    final double aaSsLauriesValue, final double aaEndingValue) {
    year = aaYear;
    month = 0;
    age = aaDougsAge;
    withdrawal = 0.0;
    ssDougsValue = aaSsDougsValue;
    ssLauriesValue = aaSsLauriesValue;
    endingValue += aaEndingValue;
  }
          
  /**
   * Sets some data in the node.
   *
   * @param aYear           Year of simulation
   * @param aAge            Age at year of simulation
   * @param aPension        Yearly Amount of received pension
   * @param aSocialSecurity Yearly amount of received social security
   * @param aAmount401K     Yearly amount of received 401K 
   *
   */
  public void aSet4(final int aYear, final int aAge, 
                    final double aPension, final double aSocialSecurity, 
                    final double aAmount401K) {
    year = aYear;
    month = 0;
    age = aAge;
    pension = aPension;
    socialSecurity = aSocialSecurity;
    value401K = aAmount401K;
  }
          
  /**
   * Sets some data in the node.
   *
   * @param aYear        Year of simulation
   * @param aAge         Age at year of simulation
   * @param aIncome      Yearly amount of received income
   * @param aDeposits    Yearly amount of received income above expenses
   * @param aWithdrawals Yearly amount withdrawn to meet expenses
   * @param aTaxes       Yearly amount of taxes
   * @param aExpenses    Yearly amount of expenses
   *
   */
  public void setValues(final int aYear, final int aAge, final double aIncome, final double aDeposits, 
                        final double aWithdrawals, final double aTaxes, final double aExpenses) {
    year = aYear;
    month = 0;
    age = aAge;
    dougsIncome = aIncome;
    deposit += aDeposits;
    withdrawal = aWithdrawals;
    taxes = aTaxes;
    endingValue = aExpenses;
  }
                        
  /**
   * Sets data in the node.
   *
   * @param value  Yearly amount at the start of the year
   *
   */
  public void setBeginningValue(final double value) {
    beginningValue = value;
  }
          
  /**
   * Sets data in the node.
   *
   * @param value  Yearly amount at the end of the year
   *
   */
  public void setEndingValue(final double value) {
    endingValue = value;
  }
          
  /**
   * Sets data (running total) in the node.
   *
   * @param aaDeposit  The amount of the deposit
   *
   */
  public void addDeposit(final double aaDeposit) {
    deposit += aaDeposit;
  }
          
  /**
   * Sets data in the node.
   *
   * @param value  Set the yearly pension in the account
   *
   */
  public void setPension(final double value) {
    pension = value;
  }
          
  /**
   * Sets data in the node.
   *
   * @param value  Set the yearly salary
   *
   */
  public void setSalary(final double value) {
    salary = value;
  }
          
  /**
   * Sets data in the node.
   *
   * @param value  Set the yearly 401K value
   *
   */
  public void set401K(final double value) {
    value401K = value;
  }
          
  /**
   * Sets data in the node.
   *
   * @param value Set the yearly social security value
   *
   */
  public void setSocialSecurity(final double value) {
    socialSecurity = value;
  }
          
  /**
   * Sets data in the node.
   *
   * @param value  Set the yearly 403B value
   *
   */
  public void set403B(final double value) {
    value403B = value;
  }
          
  /**
   * Sets data in the node.
   *
   * @param value  Set the yearly Roth IRA value
   *
   */
  public void setRoth(final double value) {
    valueRoth = value;
  }
          
  /**
   * Sets data in the node.
   *
   * @param value  Set the yearly Cash Balance saving plan value
   *
   */
  public void setCashBalance(final double value) {
    valueCashBalance = value;
  }
          
  /**
   * Adds data to the node.
   *
   * @param aaWithdrawal  Keep a running total of the withdrawals
   *
   */
  public void addWithdrawal(final double aaWithdrawal) {
    withdrawal += aaWithdrawal;
  }
          
  /**
   * Sets data in the node.
   *
   * @param aaTaxableWithdrawal  Remember the yearly taxable withdrawal
   *
   */
  public void setTaxableWithdrawal(final double aaTaxableWithdrawal) {
    taxableWithdrawal = aaTaxableWithdrawal;
  }
          
  /**
   * Sets data in the node.
   *
   * @param taxes  Remember the yearly estimated federal taxes
   *
   */
  public void setFederalTaxes(final double taxes) {
    federalTaxes = taxes;
  }
          
  /**
   * Sets data in the node.
   *
   * @param taxes  Remember the yearly estimated state taxes
   *
   */
  public void setStateTaxes(final double taxes) {
    stateTaxes = taxes;
  }
          
  /**
   * Sets data in the node.
   *
   * @param aaDeposit Remember the yearly deposits
   *
   */
  public void setDeposit(final double aaDeposit) {
    deposit = aaDeposit;
  }
          
  /**
   * Sets data in the node.
   *
   * @param aaWithdrawal Remember the yearly withdrawals
   *
   */
  public void setWithdrawal(final double aaWithdrawal) {
    withdrawal = aaWithdrawal;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The year of the node
   */
  public int getYear() {
    return year;
  }
          
  /**
   * Gets data from the node.
   *
   * @return Month of the node
   */
  public int getMonth() {
    return month;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The value (at the beginning of the year)
   */
  public double getBeginningValue() {
    return beginningValue;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The value (at the end of the year)
   */
  public double getEndingValue() {
    return endingValue;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The age of the person
   */
  public int getAge() {
    return age;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The value of deposits
   */
  public double getDeposit() {
    return deposit;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The amount of the withdrawal
   */
  public double getWithdrawal() {
    return withdrawal;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The yearly amount of the pension
   */
  public double getPension() {
    return pension;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The yearly amount of social security
   */
  public double getSocialSecurity() {
    return socialSecurity;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The yearly amount of the salary
   */
  public double getSalary() {
    return salary;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The withdrawn amount
   */
  public double get401K() {
    return value401K;
  }
  
  /**
   * Gets data from the node.
   *
   * @return The withdrawn amount
   */
  public double get403B() {
    return value403B;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The withdrawn amount
   */
  public double getRoth() {
    return valueRoth;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The withdrawn amount
   */
  public double getCashBalance() {
    return valueCashBalance;
  }
  
  /**
   * Gets data from the node.
   *
   * @return Dougs Yearly Income
   */
  public double getDougsIncome() {
    return dougsIncome;
  }
          
  /**
   * Gets data from the node.
   *
   * @return Lauries Yearly Income
   */
  public double getLauriesIncome() { 
    return lauriesIncome;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The amount of taxable withdrawals
   */
  public double getTaxableWithdrawal() {
    return taxableWithdrawal;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The amount of federal taxes
   */
  public double getFederalTaxes() {
    return federalTaxes;
  }
          
  /**
   * Gets data from the node.
   *
   * @return The amount of state taxes
   */
  public double getStateTaxes() {
    return stateTaxes;
  }
          
  /**
   * Gets data from the node.
   *
   * @return Amount of federal and state taxes
   */
  public double getTaxes() {
    return taxes;
  }
          
  /**
   * Gets data from the node.
   *
   * @return Get Dougs social security yearly value
   */
  public double getSsDoug() {
    return ssDougsValue;
  }
          
  /**
   * Gets data from the node.
   *
   * @return Get Lauries social security yearly value
   */
  public double getSsLaurie() {
    return ssLauriesValue;
  }
}
