package application.view.outputs;

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
  },     
  INCOME_GRAPH {
	@Override
	public String toString() {return "Income Graph"; }
  },
  SAVINGS_GRAPH {
	@Override
    public String toString() {return "Savings Graph"; }
  };

}
