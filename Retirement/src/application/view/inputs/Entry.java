package application.view.inputs;

public enum Entry {
  CURRENT_BALANCE { 
    public String toString() {return "Current Balance:"; }
  },
  ANNUAL_GROWTH_RATE { 
    public String toString() {return "Annual Growth Rate:"; }
  },
  ANNUAL_CONTRIBUTIONS {
    public String toString() {return "Annual Contributions:"; }
  },
  ONLY_WITH_A_SALARY {
    public String toString() {return "    Only With A Salary:"; }
  },
  START_WITHDRAWL_AGE {
    public String toString() {return "Start Withdrawal Age:"; }
  },
  NUMBER_OF_WITHDRAWALS {
    public String toString() {return "Number Of Withdrawals:"; }
  },   
  ANNUAL_DEDUCTIONS {
    public String toString() {return "Annual Deductions:"; }
  },   
  ANNUAL_EXPENSES {
    public String toString() {return "Annual Expenses:"; }
  },   
  STARTING_AGE {
    public String toString() {return "Starting Age:"; }
  },   
  MONTHLY_AMOUNT {
    public String toString() {return "Monthly Amount:"; }
  },   
  INFLATION_ADJUSTED {
    public String toString() {return "Inflation Adjusted:"; }
  },   
  SIMULATION_DATE {
    public String toString() {return "Simulation Date:"; }
  },   
  BIRTH_DATE {
    public String toString() {return "Birth Date:"; }
  },   
  RETIREMENT_AGE {
    public String toString() {return "Retirement Age:"; }
  },   
  LIFE_EXPECTANCY_AGE {
    public String toString() {return "Life Expectancy Age:"; }
  },   
  INFLATION {
    public String toString() {return "Inflation:"; }
  },  
  CURRENT_SALARY {
    public String toString() {return "Current Annual Salary:"; }
  },   
  AVERAGE_MERIT_INCREASE {
    public String toString() {return "Average Merit Increase:"; }
  },   
  EFFECTIVE_FEDERAL_TAX_RATE {
    public String toString() {return "Effective Federal Tax Rate:"; }
  },   
  EFFECTIVE_STATE_TAX_RATE {
    public String toString() {return "Effective State Tax Rate:"; }
  };   
}
