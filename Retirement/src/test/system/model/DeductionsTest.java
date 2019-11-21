package test.system.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import application.main.ValidateInputs;
import application.model.Deductions;

public class DeductionsTest {
  private static final double EPSILON = 1e-2;
  private transient Deductions deductions;
  private transient double deduction;
  private transient double growthRate;
  private transient int    currentAge;
  private transient int    currentYear;
  private transient int    deathAge;
  private transient final UtilsTest utils = new UtilsTest();
    
  @Before
  public void setUp() {
      deduction = 100.0;
      growthRate = 0.05;
      currentAge = 57;
      currentYear = 2016;
      deathAge = 95;
  } 
  
  @Test
  public void testHappyCase() {
      deductions = new Deductions(deduction, growthRate, currentAge, currentYear, deathAge); 
      
      final int numberOfYears = 1;
      assertEquals(utils.computeEndingValue(deduction, growthRate, numberOfYears), deductions.getEndingValue(currentYear), EPSILON);
  }
  
  @Test
  public void testVerifyMinAge() {
    currentAge = ValidateInputs.MIN_AGE;
    
    deductions = new Deductions(deduction, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utils.computeEndingValue(deduction, growthRate, numberOfYears), deductions.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxAge() {
    currentAge = ValidateInputs.MAX_AGE-2;
    deathAge = ValidateInputs.MAX_AGE;
    
    deductions = new Deductions(deduction, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utils.computeEndingValue(deduction, growthRate, numberOfYears), deductions.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }

  @Test
  public void testVerifyMinGrowthRate() {
    growthRate = ValidateInputs.MIN_GROWTH_RATE;
    
    deductions = new Deductions(deduction, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utils.computeEndingValue(deduction, growthRate, numberOfYears), deductions.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxGrowthRate() {
    growthRate = ValidateInputs.MAX_GROWTH_RATE;
    
    deductions = new Deductions(deduction, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utils.computeEndingValue(deduction, growthRate, numberOfYears), deductions.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyCurrentYear() {
    currentYear = 3000;
    
    deductions = new Deductions(deduction, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utils.computeEndingValue(deduction, growthRate, numberOfYears), deductions.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  } 
  
  @Test
  public void testVerifyMinDeathAge() {
    currentAge = ValidateInputs.MIN_AGE;
    deathAge = ValidateInputs.MIN_AGE + 2;
    
    deductions = new Deductions(deduction, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    assertEquals(utils.computeEndingValue(deduction, growthRate, numberOfYears), deductions.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyDeathAge() {
    currentAge = ValidateInputs.MAX_AGE - 2;
    deathAge = ValidateInputs.MAX_AGE;
    
    deductions = new Deductions(deduction, growthRate, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    assertEquals(utils.computeEndingValue(deduction, growthRate, numberOfYears), deductions.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
}
