package test.system.view.outputs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import application.system.ResultsDataNode;
import application.view.outputs.AccountCashBalance;

public class AccountCashBalanceTest {
  private static final double EPSILON = 0.01;
  /**
   * Declare the Software Under Test (SUT).
   * 
   * @var Account403B accountCashBalance
   */

  private transient AccountCashBalance accountCashBalance;
  /** 
   * Do this method before each test.
   * 
   * @since  1.0
   */
  @Before
  public void setUp() throws InterruptedException {
    accountCashBalance = new AccountCashBalance();
  }

 /** 
  * Ensure that the output tab is created.
  * 
  * @since  1.0
  */
  @Test
  public void ensureTheInputTabIsCreated() {
    
    final JPanel jPanel = accountCashBalance.createPanel();
    
    assertEquals("", jPanel.getComponentCount(), 1);    
  }  

  /** 
   * Ensure the name is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void ensureGetNameIsAccurate() {
    
    final String result = accountCashBalance.getName();
    
    assertEquals("", result, "Cash Balance");
    
  }  

  /** 
   * Ensure the name is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void ensureTextAreaIsAccurate() {
    final ResultsDataNode value = new ResultsDataNode();
    value.setBeginningValue(12.34);
    
    final  List<ResultsDataNode> list = new ArrayList<>();
    list.add(value);   
    
    accountCashBalance.clear();
    
    accountCashBalance.writeListOfData(list);
    
    final int numberOfRows = accountCashBalance.getTextArea().getLineCount();
    
    assertEquals("", numberOfRows, 3);  // header; data; new line
  }  
  
  /** 
   * Ensure the values list is set properly.
   * 
   * @since  1.0
   */
  @Test
  public void ensureTheValuesListIsAccurate() {
    final ResultsDataNode value = new ResultsDataNode();
    value.setBeginningValue(12.34);
    
    final  List<ResultsDataNode> list = new ArrayList<>();
    list.add(value);
    
    accountCashBalance.createPanel();   
    accountCashBalance.writeListOfData(list);
    
    List<ResultsDataNode> list2 = new ArrayList<>();
      
    list2 = accountCashBalance.getList();
    
    final int size = list2.size();

    assertEquals("", size, 1);  
  } 
  
  /** 
   * Ensure the first item in the values list is set properly.
   * 
   * @since  1.0
   */
  @Test
  public void ensureTheFirstValueIsCorrect() {
    final ResultsDataNode node = new ResultsDataNode();
    node.setBeginningValue(12.34);
    
    final  List<ResultsDataNode> list = new ArrayList<>();
    list.add(node);
    
    accountCashBalance.createPanel();   
    accountCashBalance.writeListOfData(list);
    
    List<ResultsDataNode> list2 = new ArrayList<>();
      
    list2 = accountCashBalance.getList();
    
    final double value = list2.get(0).getBeginningValue();

    assertEquals("", value, 12.34, EPSILON);  
  } 
}
