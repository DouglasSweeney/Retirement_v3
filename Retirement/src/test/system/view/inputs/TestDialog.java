package test.system.view.inputs;
  import java.awt.BorderLayout;
  import java.awt.Point;
  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
  import javax.swing.Action;
  import javax.swing.InputMap;
  import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
  import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
  import javax.swing.JLabel;
  import javax.swing.JPanel;
  import javax.swing.JRootPane;
  import javax.swing.KeyStroke;

import application.view.inputs.Focus;
   
  public class TestDialog extends JDialog {
  
      private static final long serialVersionUID = 1L;
      
      /**
       * Create an instance of FOCUS.
       * 
       * @var Focus FOCUS
       */
      private static final Focus FOCUS = new Focus();
      
      @SuppressWarnings("unused")
      private static boolean dialogShown;
      private static JFormattedTextField textField1 = new JFormattedTextField("5.00");
      private static JFormattedTextField textField2 = new JFormattedTextField("2.00");
      private static final String[] ITEMS = {"1", "2", "3"};
      private static JComboBox<String> comboBox = new JComboBox<>(ITEMS);
      private static JButton jButton = new JButton("OK");
      
      public TestDialog() {
        super();
      }
      
      public TestDialog(final JFrame parent, final String title, final String message) {
          super(parent, title);

          // set the position of the window
          final Point p = new Point(400, 400);
          setLocation(p.x, p.y);
   
          // Create a message
          final JPanel messagePane = new JPanel();
          messagePane.add(new JLabel(message));
          // get content pane, which is usually the
          // Container of all the dialog's components.
          getContentPane().add(messagePane);
                    
          textField1.addFocusListener(FOCUS);
          
          textField2.addFocusListener(FOCUS);
          
          comboBox.setEditable(true);
          
          final JPanel jPanel = new JPanel();
          jPanel.add(textField1);
          jPanel.add(textField2);
          jPanel.add(comboBox);
          jPanel.add(jButton);
          
          // set action listener on the button
          jButton.addActionListener(new MyActionListener());
          
          getContentPane().add(jPanel, BorderLayout.PAGE_END);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          pack();
          setVisible(true);
          dialogShown = true;
      }
  
      // override the createRootPane inherited by the JDialog, to create the rootPane.
      // create functionality to close the window when "Escape" button is pressed
      public JRootPane createRootPane() {
          final JRootPane rootPane = new JRootPane();
          final KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
          final Action action = new AbstractAction() {
               
              private static final long serialVersionUID = 1L;
   
              public void actionPerformed(final ActionEvent e) {
                  setVisible(false);
                  dialogShown = false;
                  dispose();
              }
          };
          final InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
          inputMap.put(stroke, "ESCAPE");
          rootPane.getActionMap().put("ESCAPE", action);
          return rootPane;
      }
  
      // an action listener to be used when an action is performed
      // (e.g. button is pressed)
      class MyActionListener implements ActionListener {
          //close and dispose of the window.
          public void actionPerformed(final ActionEvent e) {
              setVisible(false);
              dialogShown = false;
              dispose();
          }
      }  

      public void requestFocusInTextField2() {
        textField2.requestFocusInWindow();
      }
      
      public void doClick() {
          jButton.doClick();
      }
      
      public static void main(final String[] args) {
          final TestDialog dialog = new TestDialog(new JFrame(), "Invalid Number", "message");
          // set the size of the window
          dialog.setSize(600, 250);
          dialog.doClick();
      }
  }
