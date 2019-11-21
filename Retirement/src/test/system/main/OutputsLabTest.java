package test.system.main;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import application.main.OutputsLab;
import application.view.outputs.Account401K;
import application.view.outputs.Account403B;
import application.view.outputs.AccountCashBalance;
import application.view.outputs.AccountIra;
import application.view.outputs.AccountRoth;
import application.view.outputs.Brokerage;
import application.view.outputs.Deductions;
import application.view.outputs.Expenses;
import application.view.outputs.Pension;
import application.view.outputs.Salary;
import application.view.outputs.Savings;
import application.view.outputs.SocialSecurity;
import application.view.outputs.Taxes;

public class OutputsLabTest {
	@Test
	public void testGetAccount401K() {
		final Account401K account401k = OutputsLab.getAccount401K();
		
		assertNotNull("Ensure that not null", account401k);
	}
	
	@Test
	public void testGetAccount403B() {
		final Account403B account403b = OutputsLab.getAccount403B();
		
		assertNotNull("Ensure that not null", account403b);
	}
	
	@Test
	public void testGetAccountCashBalance() {
		final AccountCashBalance accountCashBalance = OutputsLab.getAccountCashBalance();
		
		assertNotNull("Ensure that not null", accountCashBalance);
	}
	
	@Test
	public void testGetAccountRoth() {
		final AccountRoth accountRoth = OutputsLab.getAccountRoth();
		
		assertNotNull("Ensure that not null", accountRoth);
	}
	
	@Test
	public void testGetAccountIra() {
		final AccountIra accountIra = OutputsLab.getAccountIra();
		
		assertNotNull("Ensure that not null", accountIra);
	}
	
	@Test
	public void testGetBrokerage() {
		final Brokerage brokerage = OutputsLab.getBrokerage();
		
		assertNotNull("Ensure that not null", brokerage);
	}
	
	@Test
	public void testGetDeductions() {
		final Deductions deductions = OutputsLab.getDeductions();
		
		assertNotNull("Ensure that not null", deductions);
	}
	
	@Test
	public void testGetExpenses() {
		final Expenses expenses = OutputsLab.getExpenses();
		
		assertNotNull("Ensure that not null", expenses);
	}
	
	@Test
	public void testGetPension() {
		final Pension pension = OutputsLab.getPension();
		
		assertNotNull("Ensure that not null", pension);
	}
	
	@Test
	public void testGetSalary() {
		final Salary salary = OutputsLab.getSalary();
		
		assertNotNull("Ensure that not null", salary);
	}
	
	@Test
	public void testGetSavings() {
		final Savings savings = OutputsLab.getSavings();
		
		assertNotNull("Ensure that not null", savings);
	}
	
	@Test
	public void testGetSocialSecurity() {
		final SocialSecurity socialSecurity = OutputsLab.getSocialSecurity();
		
		assertNotNull("Ensure that not null", socialSecurity);
	}
		
	@Test
	public void testGgetTaxes() {
		final Taxes taxes = OutputsLab.getTaxes();
	
		assertNotNull("Ensure that not null", taxes);
	}
}
