package application.main;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import application.main.pdf.CreatePdf;
import application.system.ApplicationLogger;
import application.view.inputs.Focus;
import application.view.inputs.InputFile;
import application.view.inputs.InputsTabbedPane;

public class Menu implements ActionListener {

  private static final String SAVE = "Save";
  private static final String SAVEAS = "Save As";
  private static final String EXIT ="Exit";
  private static final String CREATE = "Create";
    
  /**
   * Set the LOGGER property to its value.
   * 
   * @var Application_Logger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();

  public JMenuBar createMethod() {
    final JMenu file = new JMenu("File");
    file.setMnemonic(KeyEvent.VK_F);
    
      final JMenuItem exit = new JMenuItem(EXIT);
      exit.setMnemonic(KeyEvent.VK_E);
      exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
      exit.addActionListener(this);
      file.add(exit);
    
    final JMenu inputs = new JMenu("Inputs");
    inputs.setMnemonic(KeyEvent.VK_I);
    
      final JMenuItem save = new JMenuItem(SAVE);
      save.setMnemonic(KeyEvent.VK_S);
      save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
      save.addActionListener(this);
      inputs.add(save);
    
      final JMenuItem saveas = new JMenuItem(SAVEAS);
      saveas.setMnemonic(KeyEvent.VK_A);
      saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
      saveas.addActionListener(this);
      inputs.add(saveas);
    
    final JMenu pdf = new JMenu("Pdf");
    pdf.setMnemonic(KeyEvent.VK_P);
    
      final JMenuItem create = new JMenuItem(CREATE);
      create.setMnemonic(KeyEvent.VK_C);
      create.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
      create.addActionListener(this);
      pdf.add(create);
    
    final JMenuBar menuBar = new JMenuBar();
    menuBar.add(file);
    menuBar.add(inputs);
    menuBar.add(pdf);
    
    return menuBar;
  }
  
  public String getFilename(final String title) {
    String filename;
    final InputFile fc = new InputFile(title);
     
    filename = fc.getChosenFile();
    
    return filename;
  }
  
  private void saveMethod() {
    final InputsTabbedPane inputsTabbedPane = Focus.getInputsTabbedPane();
    final String filename = InputsTabbedPane.getInputFilename();
    final File file = new File(filename);
    if (file.exists() && !file.delete()) {
       LOGGER.trace("Could not delete: " + filename);
    }
    
    inputsTabbedPane.saveProperties(filename);
  }
  
  private void saveasMethod() {
    final String filename = getFilename(SAVEAS);
    if (filename != null && !filename.equals("")) {
      final File file = new File(filename);
      int answer = JOptionPane.CANCEL_OPTION;
      
      if (file.exists()) {
        answer = JOptionPane.showConfirmDialog(null, "File " + filename + " exists." 
                                               + " Overwrite?");
      } 

      if (!file.exists() || answer == JOptionPane.OK_OPTION) {
        if (file.exists() && !file.delete()) {
             LOGGER.trace("Could not delete: " + filename);
        }
        final InputsTabbedPane inputsTabbedPane = Focus.getInputsTabbedPane();
      
        inputsTabbedPane.saveProperties(filename);
      }
    }
  }
    
  private void quit() {
    System.exit(0);
  }
  
  private void pdf() {
    try {
      final CreatePdf createPdf = new CreatePdf();
      
      createPdf.create(InputsTabbedPane.getInputFilename());
    } catch (IOException e) {
        LOGGER.trace(e.getMessage());
    }
    
    // JOptionPane.showMessageDialog(null, "PDF created!");
    JOptionPane.showMessageDialog(null, "PDF created!", "Informational Message", JOptionPane.INFORMATION_MESSAGE, null);
    
  }
  
  @Override
  public void actionPerformed(final ActionEvent arg0) {
    final JMenuItem menuItem = (JMenuItem)arg0.getSource();
    
    if (menuItem.getText().equals(SAVE)) {
      saveMethod();
    } else {
      if (menuItem.getText().equals(SAVEAS)) {
        saveasMethod();
      } else {
        if (menuItem.getText().equals(EXIT)) {
          quit();
        } else {
          if (menuItem.getText().equals(CREATE)) {
            pdf();
          }
        }
      }
    }
  }
}
