package application.view.outputs;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import application.main.OutputsLab;
import application.system.ApplicationLogger;
import application.system.ResultsDataNode;

/**
 * Creates the tabbed panel of the processing results.
 * 
 * @author      Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class ResultsTabbedPane extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Write out trace execution methods via this variable.
   * 
   * @var ApplicationLogger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();
    
  /**
   * The tabbed results pane.
   * 
   * @var JTabbedPane tabbedPane
   */
  private final transient JTabbedPane  tabbedPane;
  
  /**
   * An object in the results pane.
   * 
   * @var Brokerage BROKERAGE
   */
  private static Brokerage brokerage = new Brokerage();

  /**
   * An object in the results pane.
   * 
   * @var Expenses EXPENSES
   */
  private static Expenses expenses = new Expenses();
  
  /**
   * An object in the results pane.
   * 
   * @var Salary SALARY
   */
  private static Salary salary = new Salary();

  /**
   * An object in the results pane.
   * 
   * @var Account401K ACCOUNT_401K
   */
  private static Account401K account401K = new Account401K();

  /**
   * An object in the results pane.
   * 
   * @var Account403B ACCOUNT_403B
   * 
   */
  private static Account403B account403B = new Account403B();

  /**
   * An object in the results pane.
   * 
   * @var AccountCashBalance ACCOUNT_CASH_BALANCE
   */
  private static AccountCashBalance accountCashBalance = new AccountCashBalance();

  /**
   * An object in the results pane.
   * 
   * @var AccountRoth ACCOUNT_ROTH
   */
  private static AccountRoth accountRoth = new AccountRoth();

  /**
   * An object in the results pane.
   * 
   * @var AccountIra ACCOUNT_IRA
   */
  private static AccountIra accountIra = new AccountIra();

  /**
   * An object in the results pane.
   * 
   * @var Pension PENSION
   */
  private static Pension pension = new Pension();

  /**
   * An object in the results pane.
   * 
   * @var SocialSecurity SOCIAL_SECURITY
   */
  private static SocialSecurity socialSecurity = new SocialSecurity();

  /**
   * An object in the results pane.
   * 
   * @var Deductions DEDUCTIONS
   */
  private static Deductions deductions = new Deductions();

  /**
   * An object in the results pane.
   * 
   * @var Taxes TAXES
   */
  private static Taxes taxes = new Taxes();

  /**
   * An object in the results pane.
   * 
   * @var Savings savings
   */
  private static Savings savings = new Savings();

  /**
   * An object in the results pane.
   * 
   * @var Income INCOME
   */
  private static Income income = new Income();

  /**
   * An object in the results pane.
   * 
   * @var Savings SAVINGS
   */
  private static Saving saving = new Saving();

  /**
   * The drawing area for the Income tab.
   * 
   * @var JPanel jjPanel10
   */
  private transient final JPanel             jjPanel10;

  /**
   * The drawing area for the Savings tab.
   * 
   * @var JPanel jjPanel13;
   */
  private transient final JPanel             jjPanel13;
  
  /** 
   * The constructor for the class.
   *
   * @since           1.0
   */
  public ResultsTabbedPane() {
    super();
    
    LOGGER.trace("Entering the ResultsTabbedPane constructor with no parameters.");
      
    setLayout(new GridLayout(3, 1));
    
    tabbedPane = new JTabbedPane();

    final Dimension preferredSize = new Dimension();
    preferredSize.height = 445;
    preferredSize.width = 600;
    tabbedPane.setPreferredSize(preferredSize);
    tabbedPane.setBounds(0, 0, 445, 600);
   
    // Create the 401K tab
    final String header4 = account401K.getName();
    final JPanel panel4 = account401K.createPanel();
    tabbedPane.addTab(header4, panel4);
        
    // Create the 403B tab
    final String header11 = account403B.getName();
    final JPanel panel11 = account403B.createPanel();
    tabbedPane.addTab(header11, panel11);
        
    // Create the BROKERAGE tab
    final String header2 = brokerage.getName();
    final JPanel panel2 = brokerage.createPanel();
    tabbedPane.addTab(header2, panel2);
        
    // Create the Cash Balance tab
    final String header12 = accountCashBalance.getName();
    final JPanel panel12 = accountCashBalance.createPanel();
    tabbedPane.addTab(header12, panel12);
        
    // Create the Deductions tab
    final String header8 = deductions.getName();
    final JPanel panel8 = deductions.createPanel();
    tabbedPane.addTab(header8, panel8);    
    
    // Create the EXPENSES tab
    final String header1 = expenses.getName();
    final JPanel panel1 = expenses.createPanel();
    tabbedPane.addTab(header1, panel1);
        
    // Create the INCOME graph
    final String header10 = income.getName();
    jjPanel10 = income.createPanel();
    tabbedPane.addTab(header10, jjPanel10);

    // Create the Ira tab
    final String header14 = accountIra.getName();
    final JPanel panel14 = accountIra.createPanel();
    tabbedPane.addTab(header14, panel14);
        
    // Create the Pension tab
    final String header6 = pension.getName();
    final JPanel panel6 = pension.createPanel();
    tabbedPane.addTab(header6, panel6);
        
    // Create the Roth tab
    final String header5 = accountRoth.getName();
    final JPanel panel5 = accountRoth.createPanel();
    tabbedPane.addTab(header5, panel5);
        
    // Create the SALARY tab
    final String header3 = salary.getName();
    final JPanel panel3 = salary.createPanel();
    tabbedPane.addTab(header3, panel3);    
    
    // Create the SAVINGS tab
    final String header20 = savings.getName();
    final JPanel panel20 = savings.createPanel();
    tabbedPane.addTab(header20, panel20);
        
    // Create the Saving Graph
    final String header13 = saving.getName();
    jjPanel13 = saving.createPanel();
    tabbedPane.addTab(header13, jjPanel13);

    // Create the Social Security tab
    final String header7 = socialSecurity.getName();
    final JPanel panel7 = socialSecurity.createPanel();
    tabbedPane.addTab(header7, panel7);
        
    // Create the Taxes tab
    final String header9 = taxes.getName();
    final JPanel panel9 = taxes.createPanel();
    tabbedPane.addTab(header9, panel9);
        
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
    final JLabel label = new JLabel("Outputs");
    label.setFont(font);
    label.setFocusable(false);
    
    add(label);
    
    tabbedPane.setFocusable(false);

    add(tabbedPane);
    
    LOGGER.trace("Leaving the ResultsTabbedPane constructor.");
  }
    
  /** 
   * Set the focus to the parameter tab.
   * 
   * @param index    The valid values start at 0
   * 
   */
  public void setSelectedIndex(final int index) {
    tabbedPane.setSelectedIndex(index);
  }
    
  /** 
   * Gets the selected tab index.
   * 
   * @param index    The valid values start at 0
   * 
   */
  public int getSelectedIndex() {
    return tabbedPane.getSelectedIndex();
  }
    
  /** 
   * Get the index to the JTabbedPane parameter tab.
   * 
   * @param name    The displayed name of the tab
   * 
   * @return The index of the tab
   */
  public int indexOfTab(final String name) {
    return tabbedPane.indexOfTab(name);
  }
    
    
  /** 
   * Update the JTabbedPane via the system method. 
   * 
   * @param graphics      The graphics properties used to update
   * 
   */
  @Override
  public void paint(final Graphics graphics) {
    super.paint(graphics);
    
    ResultsDataNode node;
      
    final ArrayList<Integer> ages = new ArrayList<>();
    expenses = OutputsLab.getExpenses();
    for (int i = 0; i < expenses.getList().size(); i++) {
        node = expenses.getList().get(i);
        ages.add(node.getAge());
    }
    
    account401K = OutputsLab.getAccount401K();
    account403B = OutputsLab.getAccount403B();
    accountCashBalance = OutputsLab.getAccountCashBalance();
    accountIra = OutputsLab.getAccountIra();
    accountRoth = OutputsLab.getAccountRoth();
    pension = OutputsLab.getPension();
    socialSecurity= OutputsLab.getSocialSecurity();
    salary = OutputsLab.getSalary();
    brokerage = OutputsLab.getBrokerage();
    savings = OutputsLab.getSavings();
    
    // Draw the Income tab (chart)
    income.draw(ages, expenses, pension, socialSecurity, account401K, account403B, 
                accountCashBalance, accountRoth,  accountIra, salary, brokerage, savings);

    // Draw the Saving tab (chart)
    saving.draw(ages, account401K, account403B, accountCashBalance, accountRoth, 
                accountIra, brokerage, savings);
    }
}
