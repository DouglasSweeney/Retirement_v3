package test.system.model;

public class UtilsTest {
  
  public double computeEndingValue(final double balance, final double growthRate, 
      final int numberOfYears, final double annualContribution) {
    double value = balance;

    for (int i=0; i<numberOfYears; i++) {
      value += annualContribution;
      value += value * growthRate;
    }

    return value;
  }
  
  public double computeEndingValue(final double balance, final double growthRate, 
      final int numberOfYears) {
    double value = balance;

    for (int i=0; i<numberOfYears; i++) {
      value += value * growthRate;
    }

    return value;
  }
  
  public double computeEndingValue(final double amount, final boolean inflationAdjusted,
      final double inflation, final int numberOfYears) {
    double value = amount;

    for (int i=0; i<numberOfYears; i++) {
      if (inflationAdjusted) {
        value += value * inflation;
      }
    }

    return value;
  }
  
  public double computeTaxes(final double taxableIncome, final double federalTaxRate, 
      final double stateTaxRate) {
    double totalTaxes = 0.0;
    
    totalTaxes += taxableIncome * federalTaxRate;
    totalTaxes += taxableIncome * stateTaxRate;
    
    return totalTaxes;
  }
}