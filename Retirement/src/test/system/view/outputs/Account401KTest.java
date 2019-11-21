package test.system.view.outputs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import application.system.ResultsDataNode;
import application.view.outputs.Account401K;

public class Account401KTest {
  private static final double EPSILON = 0.01;
  
  /**
   * Declare the Software Under Test (SUT).
   * 
   * @var Account401K account401k
   */

  private transient Account401K account401k;
  
  /** 
   * Do this method before each test.
   * 
   * @since  1.0
   */
  @Before
  public void setUp() throws InterruptedException {
    account401k = new Account401K();
  }

 /** 
  * Ensure that the input 401K tab is created.
  * 
  * @since  1.0
  */
  @Test
  public void ensureTheInputTabIsCreated() {
    
    final JPanel jPanel = account401k.createPanel();
    
    assertEquals("", jPanel.getComponentCount(), 1);    
  }  

  /** 
   * Ensure the name is retrieved properly.
   * 
   * @since  1.0
   */
  @Test
  public void ensureGetNameIsAccurate() {
    
    final String result = account401k.getName();
    
    assertEquals("", result, "401K");    
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
    
    account401k.clear();
    
    account401k.writeListOfData(list);
    
    final int numberOfRows = account401k.getTextArea().getLineCount();
    
    assertEquals("", 3, numberOfRows);  // header; data; new line
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
    
    account401k.createPanel();   
    account401k.writeListOfData(list);
    
    List<ResultsDataNode> list2 = new ArrayList<>();

    list2 = account401k.getList();
    
    final int size = list2.size();

    assertEquals("", 1, size);  
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
    
    account401k.createPanel();   
    account401k.writeListOfData(list);
    
    List<ResultsDataNode> list2 = new ArrayList<>();
      
    list2 = account401k.getList();
    
    final double value = list2.get(0).getBeginningValue();

    assertEquals("", value, 12.34, EPSILON);  
  }  
}
