package application.view.inputs;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

import application.main.InputsLab;
import application.main.XmlReader;
import application.main.XmlWriter;
import application.system.ApplicationLogger;

/**
 * Creates the tabbed panel of the user input.
 * 
 * @author      Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class InputsTabbedPane extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private static boolean initting = true;
  
  /**
   * Set the LOGGER property to its value.
   * 
   * @var Application_LOGGER LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();

  /**
   * Will create the user input object.
   * 
   * @var Personal personal
   */
  private transient Personal personal;

  /**
   * Will create the user input object.
   * 
   * @var Expenses expenses
   */
  private transient Expenses expenses;

  /**
   * Will create the user input object.
   * 
   * @var Brokerage brokerage
   */
  private transient Brokerage brokerage;

  /**
   * Will create the user input object.
   * 
   * @var Salary salary
   */
  private transient Salary salary;

  /**
   * Will create the user input object.
   * 
   * @var Savings
   */
  private transient Savings savings;

  /**
   * Will create the user input object.
   * 
   * @var Account401K account401K
   */
  private transient Account401K account401k;

  /**
   * Will create the user input object.
   * 
   * @var Account403B account403B
   */
  private transient Account403B account403B;

  /**
   * Will create the user input object.
   * 
   * @var AccountCashBalance accountCashBalance
   */
  private transient AccountCashBalance accountCashBalance;

  /**
   * Will create the user input object.
   * 
   * @var AccountRoth accountRoth
   */
  private transient AccountRoth accountRoth;

  /**
   * Will create the user input object.
   * 
   * @var AccountIra accoutIra
   */
  private transient AccountIra accountIra;

  /**
   * Will create the user input object.
   * 
   * @var Pension pension
   */
  private transient Pension pension;

  /**
   * Will create the user input object.
   * 
   * @var SocialSecurity socialSecurity
   */
  private transient SocialSecurity socialSecurity;

  /**
   * Will create the user input object.
   * 
   * @var Deductions deductions
   */
  private transient Deductions deductions;

  /**
   * Will create the user input object.
   * 
   * @var Taxes taxes
   */
  private transient Taxes taxes;
  
  private static JTabbedPane tabbedPane;
  
  private static String filename;
  
  public int getTabCount() {
    return tabbedPane.getTabCount();
  }
  
  public Component getComponentAt(final int index) {
    return tabbedPane.getComponentAt(index);

  }
  
  public String getTitleAt(final int index) {
    return tabbedPane.getTitleAt(index);
  }
 
  public InputsTabbedPane() {
    super();  
  }
  
  /** 
   * The constructor for the class.
   * 
   * @since           1.0
   */
  public InputsTabbedPane(String filename) {
    super();
 
    LOGGER.trace("Entering the InputsTabbedPane constructor with no parameters.");

    final XmlReader xmlReader = new XmlReader(); 

    xmlReader.create(filename);
    InputsTabbedPane.filename = filename;
    setLayout(new GridLayout(13, 1));
    tabbedPane = new JTabbedPane();

    account401k = InputsLab.getInstance().getAccount401K();
    final String h4 = account401k.getName();
    final JPanel panel4 = account401k.createPanel(xmlReader, Account401K.BALANCE_PROPERTY, 
        Account401K.GROWTH_RATE_PROPERTY, Account401K.ANNUAL_CONTRIBUTIONS_PROPERTY,
        Account401K.ONLY_WHILE_SALARY_PROPERTY, Account401K.START_WITHDRAWAL_AGE_PROPERTY,
        Account401K.NUMBER_OF_WITHDRAWALS_PROPERTY);
    panel4.setFocusable(false);
    tabbedPane.addTab(h4, panel4);

    account403B = InputsLab.getInstance().getAccount403B();
    final String h11 = account403B.getName();
    final JPanel panel11 = account403B.createPanel(xmlReader, Account403B.BALANCE_PROPERTY, 
        Account403B.GROWTH_RATE_PROPERTY, Account403B.ANNUAL_CONTRIBUTIONS_PROPERTY,
        Account403B.ONLY_WHILE_SALARY_PROPERTY, Account403B.START_WITHDRAWAL_AGE_PROPERTY,
        Account403B.NUMBER_OF_WITHDRAWALS_PROPERTY);
    tabbedPane.addTab(h11, panel11);

    brokerage = InputsLab.getInstance().getBrokerage();
    final String h2 = brokerage.getName();
    final JPanel panel2 = brokerage.createPanel();
    tabbedPane.addTab(h2, panel2);

    accountCashBalance = InputsLab.getInstance().getAccountCashBalance();
    final String h12 = accountCashBalance.getName();
    final JPanel panel12 = accountCashBalance.createPanel(xmlReader, AccountCashBalance.BALANCE_PROPERTY, 
        AccountCashBalance.GROWTH_RATE_PROPERTY, AccountCashBalance.ANNUAL_CONTRIBUTIONS_PROPERTY,
        AccountCashBalance.ONLY_WHILE_SALARY_PROPERTY, AccountCashBalance.START_WITHDRAWAL_AGE_PROPERTY,
        AccountCashBalance.NUMBER_OF_WITHDRAWALS_PROPERTY);
    tabbedPane.addTab(h12, panel12);

    deductions = InputsLab.getInstance().getDeductions();
    final String h9 = deductions.getName();
    final JPanel panel9 = deductions.createPanel();
    tabbedPane.addTab(h9, panel9);

    expenses = InputsLab.getInstance().getExpenses();
    final String h1 = expenses.getName();
    final JPanel panel1 = expenses.createPanel();
    tabbedPane.addTab(h1, panel1);

    accountIra = InputsLab.getInstance().getAccountIra();
    final String h13 = accountIra.getName();
    final JPanel panel13 = accountIra.createPanel(xmlReader, AccountIra.BALANCE_PROPERTY, 
        AccountIra.GROWTH_RATE_PROPERTY, AccountIra.ANNUAL_CONTRIBUTIONS_PROPERTY,
        AccountIra.ONLY_WHILE_SALARY_PROPERTY, AccountIra.START_WITHDRAWAL_AGE_PROPERTY,
        AccountIra.NUMBER_OF_WITHDRAWALS_PROPERTY);
    tabbedPane.addTab(h13, panel13);

    pension = InputsLab.getInstance().getPension();
    final String h7 = pension.getName();
    final JPanel panel7 = pension.createPanel();
    tabbedPane.addTab(h7, panel7);

    personal = InputsLab.getInstance().getPersonal();
    final String h5 = personal.getName();
    final JPanel panel5 = personal.createPanel();
    tabbedPane.addTab(h5, panel5);

    accountRoth = InputsLab.getInstance().getAccountRoth();
    final String h6 = accountRoth.getName();
    final JPanel panel6 = accountRoth.createPanel(xmlReader, AccountRoth.BALANCE_PROPERTY, 
        AccountRoth.GROWTH_RATE_PROPERTY, AccountRoth.ANNUAL_CONTRIBUTIONS_PROPERTY,
        AccountRoth.ONLY_WHILE_SALARY_PROPERTY, AccountRoth.START_WITHDRAWAL_AGE_PROPERTY,
        AccountRoth.NUMBER_OF_WITHDRAWALS_PROPERTY);
    tabbedPane.addTab(h6, panel6);

    salary = InputsLab.getInstance().getSalary();
    final String h3 = salary.getName();
    final JPanel panel3 = salary.createPanel();
    tabbedPane.addTab(h3, panel3);
    
    savings = InputsLab.getInstance().getSavings();
    final String h14 = savings.getName();
    final JPanel panel14 = savings.createPanel();
    tabbedPane.addTab(h14, panel14);

    socialSecurity = InputsLab.getInstance().getSocialSecurity();
    final String h8 = socialSecurity.getName();
    final JPanel panel8 = socialSecurity.createPanel();
    tabbedPane.addTab(h8, panel8);

    taxes = InputsLab.getInstance().getTaxes();
    final String h10 = taxes.getName();
    final JPanel panel10 = taxes.createPanel();
    tabbedPane.addTab(h10, panel10);

    final Path p = Paths.get(filename);
    if (p != null) {
      filename = p.getFileName().toString();
      if (filename != null) {
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        final JLabel label = new JLabel("Inputs: " + filename);
        label.setFont(font);
        label.setFocusable(false);

        add(label);
      }
    }
    
    tabbedPane.setFocusable(false);
    add(tabbedPane);
    
    setInitting(false);
    
    LOGGER.trace("Leaving the InputsTabbedPane constructor.");
  }

  /** 
   * Save the properties of this object.
   * 
   * @param filename  "null" means prompt for a filename (i.e. Save As vs. Save)
   * 
   */
  public void saveProperties(String filename) {
    LOGGER.trace("Entering the InputsTabbedPane saveProperties() with filename parameters.");

    final XmlWriter xmlWriter = new XmlWriter();
    final OutputFile outputFile = new OutputFile();
    
    if (filename == null) {
      filename = outputFile.getFileName();
    }
    
    xmlWriter.save(filename);
    xmlWriter.putHeader();
    xmlWriter.putTopLevelNode();
  
    account401k.saveProperties(xmlWriter, Account401K.BALANCE_PROPERTY, 
        Account401K.GROWTH_RATE_PROPERTY, Account401K.ANNUAL_CONTRIBUTIONS_PROPERTY,
        Account401K.ONLY_WHILE_SALARY_PROPERTY, Account401K.START_WITHDRAWAL_AGE_PROPERTY,
        Account401K.NUMBER_OF_WITHDRAWALS_PROPERTY);
    account403B.saveProperties(xmlWriter, Account403B.BALANCE_PROPERTY, 
        Account403B.GROWTH_RATE_PROPERTY, Account403B.ANNUAL_CONTRIBUTIONS_PROPERTY,
        Account403B.ONLY_WHILE_SALARY_PROPERTY, Account403B.START_WITHDRAWAL_AGE_PROPERTY,
        Account403B.NUMBER_OF_WITHDRAWALS_PROPERTY);
    accountCashBalance.saveProperties(xmlWriter, AccountCashBalance.BALANCE_PROPERTY, 
        AccountCashBalance.GROWTH_RATE_PROPERTY, AccountCashBalance.ANNUAL_CONTRIBUTIONS_PROPERTY,
        AccountCashBalance.ONLY_WHILE_SALARY_PROPERTY, AccountCashBalance.START_WITHDRAWAL_AGE_PROPERTY,
        AccountCashBalance.NUMBER_OF_WITHDRAWALS_PROPERTY);
    accountIra.saveProperties(xmlWriter, AccountIra.BALANCE_PROPERTY, 
        AccountIra.GROWTH_RATE_PROPERTY, AccountIra.ANNUAL_CONTRIBUTIONS_PROPERTY,
        AccountIra.ONLY_WHILE_SALARY_PROPERTY, AccountIra.START_WITHDRAWAL_AGE_PROPERTY,
        AccountIra.NUMBER_OF_WITHDRAWALS_PROPERTY);
    accountRoth.saveProperties(xmlWriter, AccountRoth.BALANCE_PROPERTY, 
        AccountRoth.GROWTH_RATE_PROPERTY, AccountRoth.ANNUAL_CONTRIBUTIONS_PROPERTY,
        AccountRoth.ONLY_WHILE_SALARY_PROPERTY, AccountRoth.START_WITHDRAWAL_AGE_PROPERTY,
        AccountRoth.NUMBER_OF_WITHDRAWALS_PROPERTY);
    brokerage.saveProperties(xmlWriter);
    deductions.saveProperties(xmlWriter);
    expenses.saveProperties(xmlWriter);
    pension.saveProperties(xmlWriter);
    personal.saveProperties(xmlWriter);
    salary.saveProperties(xmlWriter);
    savings.saveProperties(xmlWriter);
    socialSecurity.saveProperties(xmlWriter);
    taxes.saveProperties(xmlWriter);

    xmlWriter.putCloseTopLevelNode();
    xmlWriter.close();
    
    LOGGER.trace("Leaving the InputsTabbedPane saveProperties().");
  }

  /** 
   * Create the Gui.
   * 
   * @since  1.0
   * 
   */
  private static void createAndShowGui() {
    //Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);

    //Create and set up the window.
    final JFrame frame = new JFrame("Retirement - Inputs & Results");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final InputsTabbedPane app = new InputsTabbedPane("input_xmls/test.xml");
    frame.add(app);

    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    //Display the window.
    frame.pack();
    frame.setSize(500, 500);
    frame.setVisible(true);
  }

  /** 
   * Create the entry point.
   * 
   * @since  1.0
   * 
   */
  public static void main(final String[]args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGui();
      }
    });
  }

  public boolean isInitting() {
    return initting;
  }

  private void setInitting(final boolean initting) {
    InputsTabbedPane.initting = initting;
  }
  
  public static String getInputFilename() {
    return filename;
  }

  public static void setInputFilename(final String filename2) {
    filename = filename2;
  }
}
