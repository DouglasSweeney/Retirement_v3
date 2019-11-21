package test.system.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import application.model.Account;
import application.system.ResultsDataNode;

public class AccountTest extends Account {
  private static final double EPSILON = 1e-5;
    
  @Test
  public void initializeBalanceSet() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("getBalance()", balance, getBalance(), EPSILON);
  }
  
  @Test
  public void initializeSetGrowthRate() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("GrowthRate", growthRate, getGrowthRate(), EPSILON);
  }
  
  @Test
  public void initializeSetCurrentAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("currentAge", currentAge, getCurrentAge(), EPSILON);
  }

  @Test
  public void initializeSetCurrentYear() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("currentYear", currentYear, getCurrentYear(), EPSILON);
  }

  @Test
  public void initializeGetDeathAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("deathAge", deathAge, getDeathAge(), EPSILON);
  }
  
  @Test
  public void initializeDataSize() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("getSize()", deathAge-currentAge, getSize(), EPSILON);
  }
  
  @Test
  public void initializeListFirstNodeCurrentAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    // Check first element
    assertEquals("getListOfValues()", currentAge, getListOfValues().get(0).getAge(), EPSILON);
  }
    
  @Test
  public void initializeListLastNodeDeathAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    // Check last element
    assertEquals("getListOfValues.get()", deathAge-1, getListOfValues().get(deathAge-currentAge-1).getAge(), EPSILON);
  }
  
  @Test(expected=IndexOutOfBoundsException.class)
  public void initializeListClearException() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    clearListOfValues();
    
    // Check first element
    getListOfValues().get(0);
  }
    
  @Test
  public void initializeGetSmallestYear() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("getSmallestYear()", currentYear, getSmallestYear());
  }
  
  
  @Test
  public void initializeGetSmallestYearWithoutList() {
    // Check no elements
    assertEquals("getSmallestYear()", 0, getSmallestYear());
  }
  
  @Test
  public void initializeGetLargestYear() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("getLargestYear()", currentYear+(deathAge-currentAge-1), getLargestYear());
  }
  
  @Test
  public void initializeGetLargestYearWithoutList() {
    // Check no elements
    assertEquals("getLargestYear()", 0, getLargestYear());
  }
  
  @Test
  public void initializeConvertAgeToYearZeroAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("convertAgeToYear()", 0, convertAgeToYear(0));
  }
  
  @Test
  public void initializeConvertAgeToYearCurrentAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("", currentYear, convertAgeToYear(currentAge));
  }
  
  @Test
  public void initializeConvertAgeToYearDeathAgeMinusOne() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("", currentYear+(deathAge-currentAge-1), convertAgeToYear(deathAge-1));
  }
  
  @Test
  public void initializeConvertAgeToYearDeathAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("", 0, convertAgeToYear(deathAge));
  }
  
  @Test
  public void initializeConvertYearToAgeCurrentYear() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("", currentAge, convertYearToAge(currentYear));
  }
  
  @Test
  public void initializeConvertYearToAgeCurrentYearMinusOne() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("", 0, convertYearToAge(currentYear-1));
  }
  
  @Test
  public void initializeConvertYearToAgeDeathYearMinusOne() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("", deathAge-1, convertYearToAge(currentYear+(deathAge-currentAge-1)));
  }
  
  @Test
  public void initializeConvertYearToAgeInvalidYear() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);
    
    assertEquals("", 0, convertYearToAge(0));
  }
  
  @Test
  public void initializeRecomputeGrowthWithDeposit() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithDeposit(currentAge, 10.00);
    
    assertEquals("", 10.0, getDeposits(currentYear), EPSILON);
  }
  
  @Test
  public void initializeRecomputeGrowthWithDepositGetEndingValue() {
    final double balance = 100.0;
    final double growthRate = 0.10;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithDeposit(currentAge, 10.00);
    
    assertEquals("", 121.0, getEndingValue(currentYear), EPSILON);
  }
  
  @Test
  public void initializeRecomputeGrowthWithDepositGetBeginningValue() {
    final double balance = 100.0;
    final double growthRate = 0.10;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithDeposit(currentAge, 10.00);
    
    assertEquals("", 100.0, getBeginningValue(currentYear), EPSILON);
  }
  
  // Valid Year to getBeginningValue
  @Test
  public void initializeRecomputeGrowthWithDepositDeathYear() {
    final double balance = 100.0;
    final double growthRate = 0.0;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithDeposit(currentAge, 10.00);
    
    assertEquals("", 110.0, getBeginningValue(currentYear+(deathAge-currentAge-1)), EPSILON);
  }
  
  // Invalid year to getBeginningValue()
  @Test
  public void initializeRecomputeGrowthWithDepositDeathYearPlusOne() {
    final double balance = 100.0;
    final double growthRate = 0.0;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithDeposit(currentAge, 10.00);
    
    assertEquals("", 0.0, getBeginningValue(currentYear+(deathAge-currentAge)), EPSILON);
  }
  
  @Test
  public void initializeRecomputeGrowthWithWithdrawal() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithWithdrawal(currentAge, 10.00);
    
    assertEquals("", 10.0, getWithdrawals(currentYear), EPSILON);
  }
  
  @Test
  public void initializeRecomputeGrowthWithWithdrawalGetEndingValue() {
    final double balance = 100.0;
    final double growthRate = 0.10;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithWithdrawal(currentAge, 10.00);
    
    assertEquals("", 99.0, getEndingValue(currentYear), EPSILON);
  }
  
  @Test
  public void initializeRecomputeGrowthWithWithdrawalGetBeginningValue() {
    final double balance = 100.0;
    final double growthRate = 0.10;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithWithdrawal(currentAge, 10.00);
    
    assertEquals("", 100.0, getBeginningValue(currentYear), EPSILON);
  }
  
  // Valid Year to getBeginningValue
  @Test
  public void initializeRecomputeGrowthWithWithdrawalDeathYear() {
    final double balance = 100.0;
    final double growthRate = 0.0;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithWithdrawal(currentAge, 10.00);
    
    assertEquals("", 90.0, getBeginningValue(currentYear+(deathAge-currentAge-1)), EPSILON);
  }
  
  // Invalid year to getBeginningValue()
  @Test
  public void initializeRecomputeGrowthWithWithdrawalDeathYearPlusOne() {
    final double balance = 100.0;
    final double growthRate = 0.0;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithWithdrawal(currentAge, 10.00);
    
    assertEquals("", 0.0, getBeginningValue(currentYear+(deathAge-currentAge)), EPSILON);
  }
  
  // Valid Year to getBeginningValue
  @Test
  public void initializeRecomputeGrowthWithWithdrawalZeroBalance() {
    final double balance = 0.0;
    final double growthRate = 0.0;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithWithdrawal(currentAge, 10.00);
    
    assertEquals("", 0.0, getBeginningValue(currentYear+(deathAge-currentAge-1)), EPSILON);
  }
  
  // Valid Year to getBeginningValue
  @Test
  public void initializeRecomputeGrowthWithWithdrawalInvalidYear() {
    final double balance = 100.0;
    final double growthRate = 0.0;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithWithdrawal(currentAge-1, 10.00);
    
    assertEquals("", 0.0, getBeginningValue(currentYear-1), EPSILON);
  }
  
  // Valid Age to getBeginningValue
  @Test
  public void initializeZeroBeginningValuesValidAge() {
    final double balance = 100.0;
    final double growthRate = 0.0;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    zeroBeginningValues(currentAge);
    
    assertEquals("", 0.0, getBeginningValue(currentYear), EPSILON);
  }
  
  // Invalid Age to getBeginningValue (Below current age)
  @Test
  public void initializeZeroBeginningValuesBelowCurrentAge() {
    final double balance = 100.0;
    final double growthRate = 0.0;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    zeroBeginningValues(currentAge-1);
    
    assertEquals("", 0.0, getBeginningValue(currentAge), EPSILON);
  }
  
  // Invalid Age to getBeginningValue (Above death age)
  @Test
  public void initializeZeroBeginningValuesAboveDeathAge() {
    final double balance = 100.0;
    final double growthRate = 0.0;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    zeroBeginningValues(currentAge-1);
    
    assertEquals("", 0.0, getBeginningValue(currentYear-1), EPSILON);
  }
//
  // Valid Age to getEndingValue
  @Test
  public void initializeZeroEndingValuesValidAge() {
    final double balance = 100.0;
    final double growthRate = 0.0;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    zeroEndingValues(currentAge);
    
    assertEquals("", 0.0, getEndingValue(currentYear), EPSILON);
  }
  
  // Invalid Year to getEndingValue (Below current year)
  @Test
  public void initializeZeroEndingValuesBelowCurrentAge() {
    final double balance = 100.0;
    final double growthRate = 0.0;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    zeroEndingValues(currentAge-1);
    
    assertEquals("", 0.0, getEndingValue(currentYear-1), EPSILON);
  }
  
  // Invalid Age to getBeginningValue (Above death age)
  @Test
  public void initializeZeroEndingValuesAboveDeathAge() {
    final double balance = 100.0;
    final double growthRate = 0.0;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    zeroEndingValues(currentAge-1);
    
    assertEquals("", 0.0, getEndingValue(currentYear-1), EPSILON);
  }
  
  @Test
  public void initializeGetDeposits() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithDeposit(currentAge+1, 10.00);
    
    assertEquals("", 10.0, getDeposits(currentYear+1), EPSILON);
  }
  
  @Test
  public void initializeGetDepositsDeathAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithDeposit(deathAge, 10.00);
    
    assertEquals("", 0.0, getDeposits(currentYear+(deathAge-currentAge-1)), EPSILON);
  }
  
  @Test
  public void initializeGetDepositsAboveDeathAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithDeposit(deathAge, 10.00);
    
    assertEquals("", 0.0, getDeposits(currentYear+(deathAge-currentAge)), EPSILON);
  }
  @Test
  public void initializeGetWithdrawals() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithWithdrawal(currentAge+1, 10.00);
    
    assertEquals("", 10.0, getWithdrawals(currentYear+1), EPSILON);
  }
  
  @Test
  public void initializeGetWithdrawalsDeathAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithWithdrawal(deathAge, 10.00);
    
    assertEquals("", 0.0, getWithdrawals(currentYear+(deathAge-currentAge-1)), EPSILON);
  }
  
  @Test
  public void initializeGetWithdrawalsAboveDeathAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    recomputeGrowthWithWithdrawal(deathAge, 10.00);
    
    assertEquals("", 0.0, getWithdrawals(currentYear+(deathAge-currentAge)), EPSILON);
  }
  
  @Test
  public void initializesetZeroEndingValueCurrentAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    setZeroEndingValue(currentAge);
    
    assertEquals("", 0.0, getEndingValue(currentYear), EPSILON);
  }
  
  @Test
  public void initializeSetZeroEndingValueCurrentAgePlusOne() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    setZeroEndingValue(currentAge+1);
    
    assertEquals("", 0.0, getEndingValue(currentYear+1), EPSILON);
  }
  
  @Test
  public void initializeSetZeroEndingValueDeathAgeMinusOne() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    setZeroEndingValue(deathAge-1);
    
    assertEquals("", 0.0, getEndingValue(currentYear+(deathAge-currentAge-1)), EPSILON);
  }
  
  @Test
  public void initializeSetZeroEndingValueDeathAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    setZeroEndingValue(deathAge);
    
    assertEquals("", 0.0, getEndingValue(currentYear+(deathAge-currentAge)), EPSILON);
  }
  
  @Test
  public void initializeGetNodeBasedOnYearCurrentAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    ResultsDataNode node;
        
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    node = getNodeBasedOnYear(currentYear);
    
    assertEquals("", currentYear, node.getYear());
  }
  
  @Test
  public void initializeGetNodeBasedOnYearCurrentAgePlusOne() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    ResultsDataNode node;
        
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    node = getNodeBasedOnYear(currentYear+1);
    
    assertEquals("", currentYear+1, node.getYear());
  }
  
  @Test
  public void initializeGetNodeBasedOnYearDeathAgeMinusOne() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    ResultsDataNode node;
        
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    node = getNodeBasedOnYear(currentYear+(deathAge-currentAge-1));
    
    assertEquals("", currentYear+(deathAge-currentAge-1), node.getYear());
  }
  
  @Test
  public void initializeGetNodeBasedOnYearDeathAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
    ResultsDataNode node;
        
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    node = getNodeBasedOnYear(currentYear+(deathAge-currentAge));
    
    assertNull("assertNull()", node);
  }
  
  @Test
  public void initializeZeroAllEndingValuesCurrentAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
        
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    zeroAllEndingValues();
    
    assertEquals("", 0.0, getEndingValue(currentYear), EPSILON);
  }
  
  @Test
  public void initializeZeroAllEndingValuesDeathAge() {
    final double balance = 100.0;
    final double growthRate = 0.05;
    final int currentAge = 57;
    final int currentYear = 2016;
    final int deathAge = 95;
         
    initialize(balance, growthRate, currentAge, currentYear, deathAge);

    zeroAllEndingValues();
    
    assertEquals("", 0.0, getEndingValue(currentYear+(deathAge-currentAge)), EPSILON);
  }
}