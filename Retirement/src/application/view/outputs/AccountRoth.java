package application.view.outputs;

import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import application.system.ApplicationLogger;
import application.system.ResultsDataNode;

/**
 * Creates the GUI tab for the Roth results.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class AccountRoth extends OutputObject {

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
   * Create some utilities for this class.
   * 
   * @var Utils UTILS
   */
  private static final Utils UTILS = new Utils();
    
  /**
   * Create a name for this tabbed panel.
   * 
   * @var String NAME
   */
  private static final String NAME = Header.ROTH.toString();
  
  /**
   * Create a variable for output.
   * 
   * @var JTextArea textArea
   */
  private static JTextArea textArea;
  
  /** 
   * Get the data for the Roth account.
   * 
   * @since  1.0
   */
  public AccountRoth() {
    super();
      
    LOGGER.trace("Called the Roth constructor.");
      
    LOGGER.trace("Leaving the Roth constructor.");
  }
  
  /** 
   * Get the results tab panel for the tab.
   * 
   * @since  1.0
   * 
   * @return JPanel
   */
  public JPanel createPanel() {
    LOGGER.trace("Entering the Roth createPanel() with no parameters.");
        
    Font font;
    JScrollPane scroll;
    final JPanel jPanel = new JPanel(new FlowLayout());
      
    textArea = new JTextArea(27, 90);
    font = new Font("Monospaced", Font.PLAIN, 12);        
    textArea.setFont(font);
    textArea.setLineWrap(true);
    textArea.setEditable(false);
    jPanel.add(textArea);
      
    scroll = new JScrollPane (textArea);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jPanel.add(scroll);
        
    LOGGER.trace("Leaving the Roth createPanel().");
    LOGGER.trace("    returning jPanel=<" + jPanel + ">.");
        
    return jPanel;
  }
          
  /** 
   * Get the name of the tab.
   * 
   * @since  1.0
   * 
   * @return The name of the tab
   */
  public String getName() {
    LOGGER.trace("Entering the Roth getName() with no parameters.");
        
    LOGGER.trace("Leaving the Roth getName().");
    LOGGER.trace("     returning <" + NAME + ">");
        
    return NAME;
  }
  
  /** 
   * Write the headers to the textArea in the tab.
   * 
   * @since  1.0
   * 
   */
  private void writeHeaders() {//    accountRoth = new AccountRoth();

    LOGGER.trace("Entering the Roth writeHeaders().");
   
    textArea.append(String.format("%5.5s%8.8s%18.18s%15.15s%15.15s%15.15s\n",  
                "Year", 
                "Age", 
                "Beginning Balance",
                "Deposits",
                "Withdrawals", 
                "Ending Balance"));
        
    LOGGER.trace("Leaving the Roth writeHeaders().");
  }
  
  /** 
   * Write the data to the textArea in the tab.
   * 
   * @param  node         The data to write
   * @since  1.0
   * 
   */
  private void writeData(final ResultsDataNode node) {
    LOGGER.trace("Entering the Roth writeData().");
    LOGGER.trace("    node=" + node.getEndingValue());
        
    final String string = String.format("%5s%8s%15.15s%15.15s%15.15s%15.15s\n",
                              node.getYear(),
                              node.getAge(), 
                              UTILS.getDollarFormat(node.getBeginningValue()),
                              UTILS.getDollarFormat(node.getDeposit()),
                              UTILS.getDollarFormat(node.getWithdrawal()),
                              UTILS.getDollarFormat(node.getEndingValue()));
    textArea.append(string);
      
    LOGGER.trace("Leaving the Roth writeData().");
  }
  
  /** 
   * Write the list of data to the textArea in the tab.
   * 
   * @param  values       The data to write
   * 
   * @since  1.0
   * 
   */
  public void writeListOfData(final List<ResultsDataNode> values) {
    LOGGER.trace("Entering the Roth writeListOfData().");
    LOGGER.trace("    ArrayList=" + values);
      
    setList(values);
    
    writeHeaders();
      
    for (final ResultsDataNode node : values) {
      writeData(node);
    }    
        
    textArea.setCaretPosition(0);
        
    LOGGER.trace("Leaving the Roth writeListOfData().");
  }
    
  /** 
   * Clear the data to the textArea in the tab.
   * 
   * @since  1.0
   * 
   */
  public void clear() {
    LOGGER.trace("Entering the AccountRoth clear().");
        
    textArea.setText(null);
      
    LOGGER.trace("Leaving the AccountRoth clear().");
  }
    
  
  /** 
   * Retrieve the JTextArea.
   * 
   * @since  1.0
   * 
   * @return The list of values 
   */
  public JTextArea getTextArea() {
    return textArea;
  }
}
