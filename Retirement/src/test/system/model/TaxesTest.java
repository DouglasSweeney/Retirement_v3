package test.system.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import application.main.ValidateInputs;
import application.model.Taxes;

public class TaxesTest {
  private static final double EPSILON = 1e-2;
  private transient Taxes taxes;
  private transient double inflation;
  private transient int currentAge;
  private transient int currentYear;
  private transient int deathAge;
  private transient double federalTaxRate;
  private transient double stateTaxRate;

  private transient final UtilsTest utils = new UtilsTest();
  private transient double taxableIncome;
  private transient double tax;
    
  @Before
  public void setUp() {
    inflation = 0.0;
    currentAge = 57;
    currentYear = 2016;
    deathAge = 95;
    federalTaxRate = 0.28;
    stateTaxRate = 0.05;
  }
  
  @Test
  public void testHappyCase() {
    taxes = new Taxes(inflation, currentAge, currentYear, deathAge, federalTaxRate, stateTaxRate);
    taxableIncome = 100.0;
      
    tax = utils.computeTaxes(taxableIncome, federalTaxRate, stateTaxRate);
    assertEquals("compute()", tax, taxes.compute(currentAge, taxableIncome), EPSILON);
  }
  
  @Test
  public void testClearList() {
    taxes = new Taxes(inflation, currentAge, currentYear, deathAge, federalTaxRate, stateTaxRate);
    taxableIncome = 100.0;
      
    tax = utils.computeTaxes(taxableIncome, federalTaxRate, stateTaxRate);
    taxes.deposit(currentAge, tax);
    taxes.clearListOfValues();
    assertEquals("getEndingValue()", 0.0, taxes.getEndingValue(currentYear), EPSILON);
  }
  
  @Test
  public void testVerifyMinCurrentAge() {
    currentAge = ValidateInputs.MIN_AGE + 1;
    deathAge = ValidateInputs.MIN_AGE + 3;
    
    taxes = new Taxes(inflation, currentAge, currentYear, deathAge, federalTaxRate, stateTaxRate); 
  
    tax = utils.computeTaxes(taxableIncome, federalTaxRate, stateTaxRate);
    taxes.deposit(currentAge, tax);
      
    assertEquals("getDeposits()", tax, taxes.getDeposits(currentYear), EPSILON);
  }
  
  @Test
  public void testVerifyMaxCurrentAge() {
    currentAge = ValidateInputs.MAX_AGE;
    deathAge = ValidateInputs.MAX_AGE+2;
    
    taxes = new Taxes(inflation, currentAge, currentYear, deathAge, federalTaxRate, stateTaxRate); 
    
    tax = utils.computeTaxes(taxableIncome, federalTaxRate, stateTaxRate);
    taxes.deposit(currentAge, tax);
      
    assertEquals("getDeposits()", tax, taxes.getDeposits(currentYear), EPSILON);
  }

  @Test
  public void testVerifyCurrentYear() {
    currentYear = 3000;
    
    taxes = new Taxes(inflation, currentAge, currentYear, deathAge, federalTaxRate, stateTaxRate); 
    
    tax = utils.computeTaxes(taxableIncome, federalTaxRate, stateTaxRate);
    taxes.deposit(currentAge, tax);
      
    assertEquals("getDeposits()", tax, taxes.getDeposits(currentYear), EPSILON);
  } 

  @Test
  public void testVerifyMinDeathAge() {
    currentAge = ValidateInputs.MIN_AGE;
    deathAge = ValidateInputs.MIN_AGE + 2;
    
    taxes = new Taxes(inflation, currentAge, currentYear, deathAge, federalTaxRate, stateTaxRate); 
    
    final int age = (deathAge-currentAge)-1;
    tax = utils.computeTaxes(taxableIncome, federalTaxRate, stateTaxRate);
    taxes.deposit(age, tax);
      
    assertEquals("getDeposits()", tax, taxes.getDeposits(currentYear+age-1), EPSILON);
  }

  @Test
  public void testVerifyMaxDeathAge() {
    currentAge = ValidateInputs.MAX_AGE;
    deathAge = ValidateInputs.MAX_AGE+1;
    
    taxes = new Taxes(inflation, currentAge, currentYear, deathAge, federalTaxRate, stateTaxRate); 
    
    final int age = (deathAge-currentAge)-1;
    tax = utils.computeTaxes(taxableIncome, federalTaxRate, stateTaxRate);
    taxes.deposit(age, tax);
      
    assertEquals("getDeposits()", tax, taxes.getDeposits(currentYear+age-1), EPSILON);
  }

  @Test
  public void testVerifyInvalidYear() {
    taxes = new Taxes(inflation, currentAge, currentYear, deathAge, federalTaxRate, stateTaxRate); 
          
    assertEquals("getEndingValue()", 0.0, taxes.getEndingValue(currentYear+3000), EPSILON);
  }
  
  @Test
  public void testMinStateRate() {
    stateTaxRate = 0.0;
    
    taxes = new Taxes(inflation, currentAge, currentYear, deathAge, federalTaxRate, stateTaxRate);
    taxableIncome = 100.0;
      
    assertEquals("compute()", 28.0, taxes.compute(currentAge, taxableIncome), EPSILON);
  }
  
  @Test
  public void testMaxStateRate() {
    federalTaxRate = 0.0;
    stateTaxRate = 1.00; // 100%
    
    taxes = new Taxes(inflation, currentAge, currentYear, deathAge, federalTaxRate, stateTaxRate);
    taxableIncome = 100.0;
      
    assertEquals("compute()", 100.0, taxes.compute(currentAge, taxableIncome), EPSILON);
  }
  
  @Test
  public void testMinFederalRate() {
    federalTaxRate = 0.0;
    
    taxes = new Taxes(inflation, currentAge, currentYear, deathAge, federalTaxRate, stateTaxRate);
    taxableIncome = 100.0;
      
    assertEquals("compute()", 5.0, taxes.compute(currentAge, taxableIncome), EPSILON);
  }
  
  @Test
  public void testMaxFederalRate() {
    stateTaxRate = 0.0;
    federalTaxRate = 1.00; // 100%
    
    taxes = new Taxes(inflation, currentAge, currentYear, deathAge, federalTaxRate, stateTaxRate);
    taxableIncome = 100.0;
      
    assertEquals("compute()", 100.0, taxes.compute(currentAge, taxableIncome), EPSILON);
  }
}