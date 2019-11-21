package application.view.inputs;

public enum Header {
    ACCOUNT_401K {
      @Override
      public String toString() {return "401K"; }
    },
    ACCOUNT_403B {
      @Override
      public String toString() {return "403B"; }
    },
    BROKERAGE {
      @Override
      public String toString() {return "Brokerage"; }
    },
    CASH_BALANCE {
      @Override	
      public String toString() {return "Cash Balance"; }
    },
    DEDUCTIONS {
      @Override
      public String toString() {return "Deductions"; }
    },
    EXPENSES {
      @Override
      public String toString() {return "Expenses"; }
    },    
    IRA {
      @Override
      public String toString() {return "IRA"; }
    },
    PENSION {
      @Override
      public String toString() {return "Pension"; }
    },
    PERSONAL {
      @Override
      public String toString() {return "Personal"; }
    },
    ROTH {
      @Override
      public String toString() {return "Roth"; }
    },
    SALARY {
      @Override
      public String toString() {return "Salary"; }
    },
    SAVINGS {
      @Override
      public String toString() {return "Savings"; }
    },
    SOCIAL_SECURITY {
      @Override
      public String toString() {return "Social Security"; }
    },
    TAXES {
      @Override
      public String toString() {return "Taxes"; }
    };      
}
