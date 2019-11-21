package test.system.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import application.main.ValidateInputs;
import application.model.Pension;

public class PensionTest {
  private static final double EPSILON = 1e-2;
  private static final int    MONTHS_IN_YEAR = 12;
  
  private transient Pension pension;
  private transient double        monthlyAmount;
  private transient int           startAge;
  private transient boolean       inflationAdjusted;
  private transient double        inflation;
  private transient int           currentAge;
  private transient int           currentYear;
  private transient int           deathAge;
  private transient final UtilsTest     utils = new UtilsTest();
  private transient double        annualAmount;
    
  @Before
  public void setUp() {
    monthlyAmount = 100.0;
    startAge = 62;
    inflationAdjusted = false;
    inflation = 0.05;
    currentAge = 57;
    currentYear = 2016;
    deathAge = 95;
  } 
  
  @Test
  public void testHappyCase() {
    pension = new Pension(monthlyAmount, startAge, inflationAdjusted, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = 10;
    annualAmount = utils.computeEndingValue(monthlyAmount, inflationAdjusted, inflation, numberOfYears) * MONTHS_IN_YEAR;
    assertEquals("getBegginningValue()", annualAmount, pension.getBeginningValue(currentYear+numberOfYears), EPSILON);
  }
  
  @Test
  public void testVerifyMinStartAge() {
    currentAge = ValidateInputs.MIN_AGE -2;
    startAge = ValidateInputs.MIN_AGE;
    deathAge = ValidateInputs.MIN_AGE + 2;
    
    pension = new Pension(monthlyAmount, startAge, inflationAdjusted, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(monthlyAmount, inflationAdjusted, inflation, numberOfYears) * MONTHS_IN_YEAR;
    assertEquals("MinStartAge", annualAmount, pension.getBeginningValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxStartAge() {
    currentAge = ValidateInputs.MAX_AGE - 2;
    startAge = ValidateInputs.MAX_AGE;
    deathAge = ValidateInputs.MAX_AGE + 2;
    
    pension = new Pension(monthlyAmount, startAge, inflationAdjusted, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(monthlyAmount, inflationAdjusted, inflation, numberOfYears) * MONTHS_IN_YEAR;
    assertEquals("MaxStartAge", annualAmount, pension.getBeginningValue(currentYear + numberOfYears - 1), EPSILON);
  }

  @Test
  public void testVerifyMinInflation() {
    inflationAdjusted = true;
    inflation  = ValidateInputs.MIN_GROWTH_RATE;
    
    pension = new Pension(monthlyAmount, startAge, inflationAdjusted, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(monthlyAmount, inflationAdjusted, inflation, numberOfYears) * MONTHS_IN_YEAR;
    assertEquals("minInflation", annualAmount, pension.getBeginningValue(currentYear + numberOfYears), EPSILON);
  }
  
  @Test
  public void testVerifyMaxInflation() {
    inflationAdjusted = true;
    inflation = ValidateInputs.MAX_GROWTH_RATE;
    
    pension = new Pension(monthlyAmount, startAge, inflationAdjusted, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(monthlyAmount, inflationAdjusted, inflation, numberOfYears) * MONTHS_IN_YEAR;
    assertEquals("maxInflation", annualAmount, pension.getBeginningValue(currentYear + numberOfYears), EPSILON);
  }
  
  @Test
  public void testVerifyMinCurrentAge() {
    currentAge = ValidateInputs.MIN_AGE;
    
    pension = new Pension(monthlyAmount, startAge, inflationAdjusted, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(monthlyAmount, inflationAdjusted, inflation, numberOfYears) * MONTHS_IN_YEAR;
    assertEquals("getBeginingValue()", annualAmount, pension.getBeginningValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxCurrentAge() {
    currentAge = ValidateInputs.MAX_AGE-2;
    deathAge = ValidateInputs.MAX_AGE;
    
    pension = new Pension(monthlyAmount, startAge, inflationAdjusted, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(monthlyAmount, inflationAdjusted, inflation, numberOfYears) * MONTHS_IN_YEAR;
    assertEquals("getBeginningValue()", annualAmount, pension.getBeginningValue(currentYear + numberOfYears - 1), EPSILON);
  }
  @Test
  public void testVerifyCurrentYear() {
    currentYear = 3000;
    
    pension = new Pension(monthlyAmount, startAge, inflationAdjusted, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(monthlyAmount, inflationAdjusted, inflation, numberOfYears) * MONTHS_IN_YEAR;
    assertEquals("getBeginningValue()", annualAmount, pension.getBeginningValue(currentYear + numberOfYears - 1), EPSILON);
  } 
  
  @Test
  public void testVerifyMinDeathAge() {
    currentAge = ValidateInputs.MIN_AGE;
    deathAge = ValidateInputs.MIN_AGE + 2;
    
    pension = new Pension(monthlyAmount, startAge, inflationAdjusted, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(monthlyAmount, inflationAdjusted, inflation, numberOfYears) * MONTHS_IN_YEAR;
    assertEquals("minDeathAge", annualAmount, pension.getEndingValue(currentYear + numberOfYears), EPSILON);
  }
  
  @Test
  public void testVerifyMaxDeathAge() {
    currentAge = ValidateInputs.MAX_AGE-2;
    deathAge = ValidateInputs.MAX_AGE;
    
    pension = new Pension(monthlyAmount, startAge, inflationAdjusted, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(monthlyAmount, inflationAdjusted, inflation, numberOfYears) * MONTHS_IN_YEAR;
    assertEquals("MaxDeathAge", annualAmount, pension.getBeginningValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyTaxability() {
    pension = new Pension(monthlyAmount, startAge, inflationAdjusted, inflation, currentAge, currentYear, deathAge); 
      
    assertTrue("pension.isTaxable() is true", pension.isTaxable());
  }
  
  @Test
  public void testVerifyPensionIsZero() {
    pension = new Pension(monthlyAmount, startAge, inflationAdjusted, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = 1;
    assertEquals("pension is zero", 0.0, pension.getBeginningValue(currentYear+numberOfYears), EPSILON);
  }
}
