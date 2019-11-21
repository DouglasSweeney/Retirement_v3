package test.system.view.inputs;

import static org.junit.Assert.assertEquals;

import javax.swing.JFrame;

import org.junit.Test;

import application.view.inputs.Focus;

public class FocusTest {
  /**
   * Create an instance of FOCUS.
   * 
   * @var Focus FOCUS
   */
  private static final Focus FOCUS = new Focus();
  
  /** 
   * Cause the focus to be in the first text item & check coverage.
   * 
   * @since  1.0
   */
   @Test
   public void ensureFocusGainedProcessesProperly() {
     final TestDialog dialog = new TestDialog(new JFrame(), "Invalid Number", "message");
     
     // set the size of the window
     dialog.setSize(600, 250);
     try {
       Thread.sleep(250);
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
     
     // Check the first field.
     assertEquals("dialog", "5.00", FOCUS.getFieldData());
     
     dialog.doClick();
   }   

   /** 
    * Cause the focus to be in the first text item & check coverage.
    * 
    * @since  1.0
    */
    @Test
    public void ensureFocusGainedProcessesProperly2() {
      final TestDialog dialog = new TestDialog(new JFrame(), "Invalid Number", "message");
      
       // Leave textField1 & set focus to textField2
      dialog.requestFocusInTextField2();
      try {
        Thread.sleep(250);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      // Check the second field.
      assertEquals("dialog", "2.00", FOCUS.getFieldData());
      
      dialog.doClick();

    }
   
   /** 
    * Manipulate a few FOCUS routines.
    * 
    * @since  1.0
    */
    @Test
    public void ensureCanSetAndGet() {
      FOCUS.setFieldData("test");
      assertEquals("", "test", FOCUS.getFieldData());
    }
}
