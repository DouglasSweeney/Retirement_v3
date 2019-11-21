package application.view.inputs;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.main.ExecuteObject;

public class ComboBoxList implements ActionListener {
  private JComboBox<String> comboBox;
  public ComboBoxList(final ComboBoxItems.ITEMS items, final String defaultValue) {
    final ComboBoxItems comboBoxItems = new ComboBoxItems();
    comboBox = new JComboBox<>(comboBoxItems.getItems(items));
    comboBox.setEditable(true);
    comboBox.setToolTipText("Select an entry from the dropdown list.");
    comboBox.addActionListener(this);
    final Dimension dimension = new Dimension(30, 35);
    comboBox.setPreferredSize(dimension);
    setValue(defaultValue);
  }

  @Override
  public void actionPerformed(final ActionEvent e) {
    
    final JComboBox<?> cb = (JComboBox<?>)e.getSource();
    
    if (cb.equals(comboBox) && e.getActionCommand().equals("comboBoxChanged")) {
      final Integer index = comboBox.getSelectedIndex();
    
      if (index == -1) {
        JOptionPane.showMessageDialog(null, "Invalid Number - please select from the dropdown list", null, JOptionPane.OK_OPTION);
        cb.requestFocusInWindow();
      }
      else {
        final InputsTabbedPane inputsTabbedPane = new InputsTabbedPane();
        if (!inputsTabbedPane.isInitting()) {
          final ExecuteObject execute = new ExecuteObject();
          if (Focus.getInputsTabbedPane() != null && Focus.getResultsTabbedPane() != null) {
            execute.runSimulation(Focus.getInputsTabbedPane(), 
            		              Focus.getResultsTabbedPane());
          
            final KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            keyboardFocusManager.focusNextComponent();
          }
        }
      }
    }
  }
  

  public JComboBox<String> getComboBox() {
    return comboBox;
  }

  public void setComboBox(final JComboBox<String> comboBox) {
    this.comboBox = comboBox;
  }

  public String getValue() {
    return (String)comboBox.getSelectedItem();
  }

  public void setValue(final String value) {
    comboBox.setSelectedItem(value);
  }
  
  private static JPanel createComboBoxes() {
    ComboBoxList ageComboBoxList;
    ComboBoxList numberOfWithdrawalsComboBoxList;
    ComboBoxList growthRateComboBoxList;
    ComboBoxList meritIncreaseComboBoxList;
    ComboBoxList taxRateComboBoxList;
    
    final JPanel jPanel = new JPanel(new GridLayout(5, 2));
    
    jPanel.add(new JLabel("Age:"));
    ageComboBoxList = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_95,"65");
    jPanel.add(ageComboBoxList.getComboBox());
    
    jPanel.add(new JLabel("Number of Withdrawals:"));
    numberOfWithdrawalsComboBoxList = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_50, "15");
    jPanel.add(numberOfWithdrawalsComboBoxList.getComboBox());
    
    jPanel.add(new JLabel("Growth Rate:"));
    growthRateComboBoxList = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_30_BY_ZERO_POINT_FIVE, "7.0");
    jPanel.add(growthRateComboBoxList.getComboBox());
    
    jPanel.add(new JLabel("Merit Increase:"));
    meritIncreaseComboBoxList = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_7_BY_ZERO_POINT_FIVE, "3.5");
    jPanel.add(meritIncreaseComboBoxList.getComboBox());
    
    jPanel.add(new JLabel("Federal Tax Rate:"));
    taxRateComboBoxList = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_50_BY_ZERO_POINT_FIVE, "20.0");
    jPanel.add(taxRateComboBoxList.getComboBox());
    
    return jPanel;
  }
  
  public static void main(final String[] args) {
    final JFrame frame = new JFrame("JComboBox");
    
    frame.add(createComboBoxes());
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    frame.setSize(380, 120); // width=380, height=120
    frame.setVisible(true);
  }
}
