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
 * Creates the GUI tab for the Account401K results.
 * 
 * @author      Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class Account401K extends OutputObject {
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
  private static final String NAME = Header.ACCOUNT_401K.toString();
  
  /**
   * Create a variable for output.
   * 
   * @var JTextArea textArea2
   */
  private static JTextArea textArea2;
  
  /** 
   * Get the data for the 401K account.
   * 
   * @since  1.0
   */
  public Account401K() {
    super();
      
    LOGGER.trace("Called the Account401K constructor.");
      
    LOGGER.trace("Leaving the Account401K constructor.");
  }
  
  /** 
   * Get the results tab panel for the tab.
   * 
   * @since  1.0
   * 
   * @return JPanel
   */
  public JPanel createPanel() {
    LOGGER.trace("Entering the Account401K createPanel() with no parameters.");
        
    final JPanel jPanel = new JPanel(new FlowLayout());
    JScrollPane scroll;
    Font font;
    
    textArea2 = new JTextArea(27, 90);
    font = new Font("Monospaced", Font.PLAIN, 12);       
    textArea2.setFont(font);
    textArea2.setLineWrap(true);
    textArea2.setEditable(false);
    jPanel.add(textArea2);
        
    scroll = new JScrollPane(textArea2);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jPanel.add(scroll);
        
    LOGGER.trace("Leaving the Account401K createPanel().");
    LOGGER.trace("    returning jPanel=<" + jPanel + ">.");
        
    return jPanel;
  }
          
  /** 
   * Get the NAME of the tab.
   * 
   * @since  1.0
   * 
   * @return The NAME of the tab
   */
  public String getName() {
    LOGGER.trace("Entering the Account401K getName() with no parameters.");
        
    LOGGER.trace("Leaving the Account401K getName().");
    LOGGER.trace("     returning <" + NAME + ">");
        
    return NAME;
  }
  
  /** 
   * Write the headers to the textArea in the tab.
   * 
   * @since  1.0
   * 
   */
  private void writeHeaders() {
    LOGGER.trace("Entering the Account401K writeHeaders().");
        
    textArea2.append(String.format("%5.5s%8.8s%18.18s%15.15s%15.15s%15.15s\n",  
                                        "Year", 
                                        "Age", 
                                        "Beginning Balance",
                                        "Deposits",
                                        "Withdrawals", 
                                        "Ending Balance"));
        
    LOGGER.trace("Leaving the Account401K writeHeaders().");
  }
  
  /** 
   * Write the data to the textArea in the tab.
   * 
   * @param  node         The data to write
   * @since  1.0
   * 
   */
  private void writeData(final ResultsDataNode node) {
    LOGGER.trace("Entering the Account401K writeData().");
    LOGGER.trace("    node=" + node.getEndingValue());
        
    final String string = String.format("%5s%8s%15.15s%15.15s%15.15s%15.15s\n",
                              node.getYear(),
                              node.getAge(), 
                              UTILS.getDollarFormat(node.getBeginningValue()),
                              UTILS.getDollarFormat(node.getDeposit()),
                              UTILS.getDollarFormat(node.getWithdrawal()),
                              UTILS.getDollarFormat(node.getEndingValue()));
    textArea2.append(string);
      
    LOGGER.trace("Leaving the Account401K writeData().");
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
    LOGGER.trace("Entering the Account401K writeListOfData().");
    LOGGER.trace("    ArrayList=" + values);
    for (final ResultsDataNode node: values) {
      LOGGER.trace("    ArrayList[]=" + node.getBeginningValue());      
    }
    
    setList(values);
        
    writeHeaders();
              
    for (final ResultsDataNode node2: values) {
      writeData(node2);
    }   
        
    textArea2.setCaretPosition(0);
        
    LOGGER.trace("Leaving the Account401K writeListOfData().");
  }
    
  /** 
   * Clear the data to the textArea in the tab.
   * 
   * @since  1.0
   * 
   */
  public void clear() {
    LOGGER.trace("Entering the Account401K clear().");

    textArea2.setText(null);

    LOGGER.trace("Leaving the Account401K clear().");
  }
    
  /** 
   * Retrieve the JTextArea.
   * 
   * @since  1.0
   * 
   * @return The list of values 
   */
  public JTextArea getTextArea() {
    return textArea2;
  }
}