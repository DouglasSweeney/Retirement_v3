package test.system.view.outputs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import application.system.ResultsDataNode;
import application.view.outputs.AccountRoth;

public class AccountRothTest {
  private static final double EPSILON = 0.01;
  
  /**
   * Declare the Software Under Test (SUT).
   * 
   * @var AccountRoth accountRoth
   */

  private transient AccountRoth accountRoth;
  /** 
   * Do this method before each test.
   * 
   * @since  1.0
   */
  @Before
  public void setUp() throws InterruptedException {
    accountRoth = new AccountRoth();
  }

 /** 
  * Ensure that the input 401K tab is created.
  * 
  * @since  1.0
  */
  @Test
  public void ensureTheInputTabIsCreated() {
    
    final JPanel jPanel = accountRoth.createPanel();
    
    assertEquals("", jPanel.getComponentCount(), 1);    
  }  

  /** 
   * Ensure the name is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void ensureGetNameIsAccurate() {
    
    final String result = accountRoth.getName();
    
    assertEquals("", result, "Roth");
    
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
    
    accountRoth.clear();
    
    accountRoth.writeListOfData(list);
    
    final int numberOfRows = accountRoth.getTextArea().getLineCount();
    
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
    
    accountRoth.createPanel();   
    accountRoth.writeListOfData(list);
    
    List<ResultsDataNode> list2 = new ArrayList<>();
      
    list2 = accountRoth.getList();
    
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
    
    accountRoth.createPanel();   
    accountRoth.writeListOfData(list);
    
    List<ResultsDataNode> list2 = new ArrayList<>();
      
    list2 = accountRoth.getList();
    
    final double value = list2.get(0).getBeginningValue();

    assertEquals("", value, 12.34, EPSILON);  
  } 
}
