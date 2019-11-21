package test.system.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import application.main.ValidateInputs;
import application.model.SocialSecurity;

public class SocialSecurityTest {
  private static final double EPSILON = 0.001;
  private static final int    MONTHS_IN_YEAR = 12;
  
  private transient SocialSecurity socialSecurity;
  private transient double        monthlyAmount;
  private transient int           startAge;
  private transient double        inflation;
  private transient int           currentAge;
  private transient int           currentYear;
  private transient int           deathAge;
  private transient final UtilsTest         utils = new UtilsTest();
  private transient double        annualAmount;
  private transient double        utilsAnnualAmount;
    
  @Before
  public void setUp() {
    monthlyAmount = 100.0;
    startAge = 62;
    inflation = 0.05;
    currentAge = 62;
    currentYear = 2016;
    deathAge = 95;
  } 
  
  @Test
  public void testHappyCase() {
    currentAge = 62;

    socialSecurity = new SocialSecurity(monthlyAmount, startAge, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = 10;
    utilsAnnualAmount = utils.computeEndingValue(monthlyAmount, inflation, numberOfYears) * MONTHS_IN_YEAR;
    annualAmount = socialSecurity.getEndingValue(currentYear+numberOfYears-1);
    assertEquals("getEndingValue()", annualAmount, utilsAnnualAmount, EPSILON);
  }
  
  @Test
  public void testVerifyMinStartAge() {
    currentAge = ValidateInputs.MIN_AGE;
    startAge = ValidateInputs.MIN_AGE;
    deathAge = ValidateInputs.MIN_AGE + 2;
    
    socialSecurity = new SocialSecurity(monthlyAmount, startAge, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge;
    utilsAnnualAmount = utils.computeEndingValue(monthlyAmount, inflation, numberOfYears) * MONTHS_IN_YEAR;
    annualAmount = socialSecurity.getEndingValue(currentYear+1);
    assertEquals("getEndingValue()", utilsAnnualAmount, annualAmount, EPSILON);
  }
  
  @Test
  public void testVerifyMaxStartAge() {
    currentAge = ValidateInputs.MAX_AGE;
    startAge = ValidateInputs.MAX_AGE;
    deathAge = ValidateInputs.MAX_AGE + 2;
    
    socialSecurity = new SocialSecurity(monthlyAmount, startAge, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(monthlyAmount, inflation, numberOfYears) * MONTHS_IN_YEAR;
    assertEquals("getEndingValue()", annualAmount, socialSecurity.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }

  @Test
  public void testVerifyMinInflation() {
    inflation  = ValidateInputs.MIN_GROWTH_RATE;
    
    socialSecurity = new SocialSecurity(monthlyAmount, startAge, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(monthlyAmount, inflation, numberOfYears) * MONTHS_IN_YEAR;
    assertEquals("getEndingValue()", annualAmount, socialSecurity.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyMinCurrentAge() {
    currentAge = ValidateInputs.MIN_AGE;
    startAge = currentAge;
    
    socialSecurity = new SocialSecurity(monthlyAmount, startAge, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = 10;
    utilsAnnualAmount = utils.computeEndingValue(monthlyAmount, inflation, numberOfYears) * MONTHS_IN_YEAR;
    annualAmount = socialSecurity.getEndingValue(currentYear + numberOfYears - 1);
    assertEquals("getEndingValue()", utilsAnnualAmount, annualAmount, EPSILON);
  }
  
  @Test
  public void testVerifyMaxCurrentAge() {
    deathAge = ValidateInputs.MAX_AGE;   
    currentAge = ValidateInputs.MAX_AGE-2;
    
    socialSecurity = new SocialSecurity(monthlyAmount, startAge, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge - currentAge - 1;
    currentYear = currentYear + numberOfYears;
    utilsAnnualAmount = utils.computeEndingValue(monthlyAmount, inflation, numberOfYears) * MONTHS_IN_YEAR;
    annualAmount = socialSecurity.getBeginningValue(currentYear);
    assertEquals("getBeginningValue()", utilsAnnualAmount, annualAmount, EPSILON);
  }
  
  @Test
  public void testVerifyCurrentYear() {
    currentYear = 3000;
    
    socialSecurity = new SocialSecurity(monthlyAmount, startAge, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = deathAge-currentAge - 1;
    annualAmount = utils.computeEndingValue(monthlyAmount, inflation, numberOfYears) * MONTHS_IN_YEAR;
    assertEquals("getEndingValue()", annualAmount, socialSecurity.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  } 
  
  @Test
  public void testVerifyMinSocialSecurityAge() {
    startAge = ValidateInputs.MIN_SOCIAL_SECURITY_AGE;
    currentAge = startAge;
    socialSecurity = new SocialSecurity(monthlyAmount, startAge, inflation, currentAge, currentYear, deathAge); 
    
    final int numberOfYears = 1;
    utilsAnnualAmount = utils.computeEndingValue(monthlyAmount, inflation, numberOfYears) * MONTHS_IN_YEAR;
    annualAmount = socialSecurity.getEndingValue(currentYear);
    assertEquals("getEndingValue()", utilsAnnualAmount, annualAmount, EPSILON);
  }
  
  @Test
  public void testVerifyMaxSocialSecurityAge() {
    startAge = ValidateInputs.MAX_SOCIAL_SECURITY_AGE;
    currentAge= startAge;
    
    socialSecurity = new SocialSecurity(monthlyAmount, startAge, inflation, currentAge, currentYear, deathAge); 
      
    final int numberOfYears = startAge - currentAge + 1;
    annualAmount = utils.computeEndingValue(monthlyAmount * MONTHS_IN_YEAR, inflation, numberOfYears);
    assertEquals("getEndingValue()", annualAmount, socialSecurity.getEndingValue(currentYear + numberOfYears - 1), EPSILON);
  }
  
  @Test
  public void testVerifyTaxability() {
    socialSecurity = new SocialSecurity(monthlyAmount, startAge, inflation, currentAge, currentYear, deathAge); 
      
    assertTrue("socialSecurity.isTaxable() is true", socialSecurity.isTaxable());
  }
  
  @Test
  public void testVerifySocialSecurityIsZero() {
    currentAge = startAge;
    final int deathYear = currentYear + (deathAge - currentAge);
    
    socialSecurity = new SocialSecurity(monthlyAmount, startAge, inflation, currentAge, currentYear, deathAge); 
      
    assertEquals("getEndingValue()", 0.0, socialSecurity.getEndingValue(deathYear), EPSILON);
  }
}
