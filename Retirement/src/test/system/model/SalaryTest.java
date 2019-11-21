package test.system.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import application.main.ValidateInputs;
import application.model.Salary;

public class SalaryTest {
  private static final double EPSILON = 1e-2;
  
  private transient Salary  salary;
  private transient double        annualAmount;
  private transient double        meritIncrease;
  private transient int           currentAge;
  private transient int           currentYear;
  private transient int           deathAge;
  private transient int           retirementAge;
  private transient final UtilsTest         utils = new UtilsTest();
    
  @Before
  public void setUp() {
    annualAmount = 100.0;
    meritIncrease = 0.05;
    currentAge = 57;
    currentYear = 2016;
    deathAge = 95;
    retirementAge = 62;
  } 
  
  @Test
  public void testHappyCase() {
    salary = new Salary(annualAmount, meritIncrease, currentAge, currentYear, deathAge, retirementAge); 
      
    final int numberOfYears = 1;
    annualAmount = utils.computeEndingValue(annualAmount, meritIncrease, numberOfYears);
    assertEquals("getEndingValue()", annualAmount, salary.getEndingValue(currentYear), EPSILON);
  }
  
  @Test
  public void testVerifyMinRetirementAge() {
    currentAge = ValidateInputs.MIN_AGE + 1;
    retirementAge = ValidateInputs.MIN_AGE + 2;
    deathAge = ValidateInputs.MIN_AGE + 3;
    
    salary = new Salary(annualAmount, meritIncrease, currentAge, currentYear, deathAge, retirementAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(annualAmount, meritIncrease, numberOfYears);
    assertEquals("getEndingValue()", annualAmount, salary.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMaxRetirementAge() {
    currentAge = ValidateInputs.MAX_AGE + 1;
    retirementAge = ValidateInputs.MAX_AGE + 2 ;
    deathAge = ValidateInputs.MAX_AGE + 3;
    
    salary = new Salary(annualAmount, meritIncrease, currentAge, currentYear, deathAge, retirementAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(annualAmount, meritIncrease, numberOfYears);
    assertEquals("getEndingValue()", annualAmount, salary.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }

  @Test
  public void testVerifyMinMeritIncrease() {
    meritIncrease  = ValidateInputs.MIN_GROWTH_RATE;
    
    salary = new Salary(annualAmount, meritIncrease, currentAge, currentYear, deathAge, retirementAge); 
      
    final int numberOfYears = 1;
    annualAmount = utils.computeEndingValue(annualAmount, meritIncrease, numberOfYears);
    assertEquals("getEndingValue()", annualAmount, salary.getEndingValue(currentYear), EPSILON);
  }
  
  @Test
  public void testVerifyMaxMeritIncrease() {
    meritIncrease = ValidateInputs.MAX_GROWTH_RATE;
    
    salary = new Salary(annualAmount, meritIncrease, currentAge, currentYear, deathAge, retirementAge); 
      
    final int numberOfYears = 1;
    annualAmount = utils.computeEndingValue(annualAmount, meritIncrease, numberOfYears);
    assertEquals("getEndingValue()", annualAmount, salary.getEndingValue(currentYear), EPSILON);
  }
  
  @Test
  public void testVerifyMinCurrentAge() {
    currentAge = ValidateInputs.MIN_AGE;
    
    salary = new Salary(annualAmount, meritIncrease, currentAge, currentYear, deathAge, retirementAge); 
      
    final int numberOfYears = 1;
    annualAmount = utils.computeEndingValue(annualAmount, meritIncrease, numberOfYears);
    assertEquals("getEndingValue()", annualAmount, salary.getEndingValue(currentYear), EPSILON);
  }
  
  @Test
  public void testVerifyMaxCurrentAge() {
    currentAge = ValidateInputs.MAX_AGE;
    retirementAge = ValidateInputs.MAX_AGE+1;
    deathAge = ValidateInputs.MAX_AGE+2;
    
    salary = new Salary(annualAmount, meritIncrease, currentAge, currentYear, deathAge, retirementAge); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    annualAmount = utils.computeEndingValue(annualAmount, meritIncrease, numberOfYears);
    assertEquals("getEndingValue()", annualAmount, salary.getEndingValue(currentYear), EPSILON);
  }
  @Test
  public void testVerifyCurrentYear() {
    currentYear = 3000;
    
    salary = new Salary(annualAmount, meritIncrease, currentAge, currentYear, deathAge, retirementAge); 
      
    final int numberOfYears = 1;
    annualAmount = utils.computeEndingValue(annualAmount, meritIncrease, numberOfYears);
    assertEquals("getEndingValue()", annualAmount, salary.getEndingValue(currentYear), EPSILON);
  } 
  
  @Test
  public void testVerifyMinDeathAge() {
    currentAge = ValidateInputs.MIN_AGE;
    deathAge = ValidateInputs.MIN_AGE + 2;
    
    salary = new Salary(annualAmount, meritIncrease, currentAge, currentYear, deathAge, retirementAge); 
      
    final int numberOfYears = 1;
    annualAmount = utils.computeEndingValue(annualAmount, meritIncrease, numberOfYears);
    assertEquals("getEndingValue()", annualAmount, salary.getEndingValue(currentYear), EPSILON);
  }
  
  @Test
  public void testVerifyMaxDeathAge() {
    currentAge = ValidateInputs.MAX_AGE;
    deathAge = ValidateInputs.MAX_AGE+1;
    
    salary = new Salary(annualAmount, meritIncrease, currentAge, currentYear, deathAge, retirementAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(annualAmount, meritIncrease, numberOfYears);
    assertEquals("getEndingValue()", 0.0, salary.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifySalaryIsZero() {
    salary = new Salary(annualAmount, meritIncrease, currentAge, currentYear, deathAge, retirementAge); 
      
    assertEquals("getEndingValue()", 0.0, salary.getEndingValue(currentYear+(retirementAge-currentAge)), EPSILON);
  }
}
