package application.view.inputs;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFormattedTextField;

import application.main.ExecuteObject;
import application.view.outputs.ResultsTabbedPane;

public class Focus implements FocusListener {

  private static String fieldData = "";
  private static InputsTabbedPane inputsTabbedPane;
  private static ResultsTabbedPane resultsTabbedPane;
  
  /**
   * A way to call a new focus without setting the TabbedPane fields
   */
  public Focus() {
  }
  
  public Focus(final InputsTabbedPane inputsTabbedPane, final ResultsTabbedPane resultsTabbedPane) {
    Focus.inputsTabbedPane = inputsTabbedPane;
    Focus.resultsTabbedPane = resultsTabbedPane;
  }
  
  @Override
  public void focusGained(final FocusEvent arg0) {
//    if (arg0.getComponent().getClass().getName().equals("javax.swing.JFormattedTextField")) {
    if (arg0.getComponent() instanceof JFormattedTextField) {
      fieldData = ((JFormattedTextField)arg0.getComponent()).getText();
    }
  }

  @Override
  public void focusLost(final FocusEvent arg0) {
    if (arg0.getComponent() instanceof JFormattedTextField &&
       !((JFormattedTextField)arg0.getComponent()).getText().equals(fieldData)) {
        final ExecuteObject execute = new ExecuteObject();
        
        execute.runSimulation(inputsTabbedPane, resultsTabbedPane);
    } 
  }
  
  public void setFieldData(final String data) { 
    fieldData = data;
  }
  
  public String getFieldData() {
    return fieldData;
  }
  
  public static InputsTabbedPane getInputsTabbedPane() { 
    return inputsTabbedPane;
  }
  
  public static ResultsTabbedPane getResultsTabbedPane() {
    return resultsTabbedPane;
  }
  
}
