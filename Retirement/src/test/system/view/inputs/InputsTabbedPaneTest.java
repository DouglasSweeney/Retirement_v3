package test.system.view.inputs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Component;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import application.view.inputs.InputsTabbedPane;

@RunWith(MockitoJUnitRunner.class)
public class InputsTabbedPaneTest {
  
  private static final String FILENAME = "input_xmls/test.xml";
  
  private transient final InputsTabbedPane inputsTabbedPane = new InputsTabbedPane(FILENAME);
  
  private boolean ensureStr1IsLessThanStr2(final String str1, final String str2) {
    boolean returnValue;
    
    final int i = str1.compareTo(str2);
    if (i < 0) {
      returnValue = true;
    }
    else {
      returnValue = false;
    }
    
    return returnValue;
  }
  /** 
   * Ensure that the inputs tabbed pane is created.
   * 
   * @since  1.0
   */
  @Test
  public void ensureTheInputsTabbedPaneIsCreated() {
    assertNotNull("", inputsTabbedPane);    
  }   
   
  /** 
   * Ensure that the first tab name is alphabetically before the second tab name.
   * 
   * @since  1.0
   */
  @Test
  public void ensureTheFirstTabNameIsBeforeTheSecondName () {
    if (inputsTabbedPane.getTabCount() >= 2) {
      assertTrue("", ensureStr1IsLessThanStr2(
            inputsTabbedPane.getTitleAt(0),
            inputsTabbedPane.getTitleAt(1)));
    }
    else {
      fail("");
    }
  }   
  
  /** 
   * Ensure that all the tab names are alphabetically before the next tab name.
   * 
   * @since  1.0
   */
  @Test
  public void ensureTheAllTabNamesAreBeforeTheNextTabName() {
    final int numberOfTabs = inputsTabbedPane.getTabCount();

    for (int i=0; i<numberOfTabs-1; i++) {
      assertTrue("", ensureStr1IsLessThanStr2(
            inputsTabbedPane.getTitleAt(i),
            inputsTabbedPane.getTitleAt(i+1)));
    }
  }  
  
  /** 
   * Ensure that all the tab names are alphabetically before the next tab name.
   * 
   * @since  1.0
   */
  @Test
  public void ensureTheAllTabObjectsAreJPanels() {
    final int numberOfTabs = inputsTabbedPane.getTabCount();
    Component component;
    String name;

    for (int i=0; i<numberOfTabs; i++) {
      component = inputsTabbedPane.getComponentAt(i);
      name = component.getClass().getName();
      assertEquals("", name, "javax.swing.JPanel");
    }
  }   
  
  /** 
   * Ensure that the number of tabs is expected number.
   * 
   * @since  1.0
   */
  @Test
  public void ensureThatTheNumberOfTabs() {
    final int numberOfTabs = inputsTabbedPane.getTabCount();
    
    assertEquals("", numberOfTabs, 14);
  }     
 }
