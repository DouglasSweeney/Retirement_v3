package application.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.system.ApplicationLogger;
import application.view.inputs.Focus;
import application.view.inputs.InputFile;
import application.view.inputs.InputsTabbedPane;
import application.view.outputs.ResultsTabbedPane;

/**
 * Create the GUI.
 *
 * @version release-1.0.0 Initial version
 * @author D.K.Sweeney 1/21/2016
 *
 */
@SuppressWarnings("serial")
public class Main extends JPanel {
  /**
   * The class LOGGER (log4j2).
   * 
   * @var ApplicationLogger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();
    
  /**
   * The instance of the inputs gui.
   * 
   * @var InputsTabbedPane inputsTabbedPane
   */
  private final InputsTabbedPane inputsTabbedPane;

  /**
   * The instance of the results gui.
   * 
   * @var ResultsTabbedPane resultsTabbedPane
   */
  private final ResultsTabbedPane resultsTabbedPane;

  private static Color defaultBackgroundColor;  

  /**
   * Get the input filename.
   */      
  public String getFilename() {
    String filename;
    final InputFile fc = new InputFile("Open");
     
    filename = fc.getChosenFile();
    
    return filename;
  }
  
  /**
   * Initialize the GUI system.
   */
  private Main() {
    super();
    
    final JLabel  textScreenBottom = new JLabel("For educational purposes only.");  
      
    final GridBagConstraints constraints = new GridBagConstraints();
        
    
    LOGGER.trace("Entering the Main constructor with no parameters.");
    
    final String filename = getFilename();

    setLayout(new GridBagLayout());

    // Add the Input Pane to the left side
    constraints.gridx = 1;
    constraints.gridy = 4;
    constraints.insets.top = -450;
    inputsTabbedPane = new InputsTabbedPane(filename);
    inputsTabbedPane.setFocusable(false);
    
    add(inputsTabbedPane, constraints);
        
    // Add the Results Pane to the right side
    constraints.gridx = 5;
    constraints.gridy = 2;
    constraints.insets.top = 0;
    constraints.insets.right = 0;
    constraints.ipadx = 50;
    constraints.ipady = 50;

    resultsTabbedPane = new ResultsTabbedPane();
    resultsTabbedPane.setFocusable(false);
    add(resultsTabbedPane, constraints);

    // Add text at the bottom of the screen
    constraints.gridx = 1;
    constraints.gridy = 5;
    constraints.insets.top = 0;
    constraints.insets.left = -170;
    constraints.ipadx = 0;
    constraints.ipady = 0;
    textScreenBottom.setFocusable(false);
    add(textScreenBottom, constraints);
    
    new Focus(inputsTabbedPane, resultsTabbedPane);
        
    final ExecuteObject execute = new ExecuteObject();
    
    // Run from the current inputs
    execute.runSimulation(inputsTabbedPane, resultsTabbedPane);
    
    LOGGER.trace("Leaving the Main constructor.");
  }
 
  /**
   * Refresh the graphic of the results tabbed pane.
   * 
   * @param  Graphics    g
   * 
   */
  @Override
  public void paint(final Graphics g) {
    super.paint(g);
    
    resultsTabbedPane.invalidate();
  }
  
  public InputsTabbedPane getInputsTabbedPane() {
    return inputsTabbedPane;
  }
  
  public ResultsTabbedPane getResultsTabbedPane() {
    return resultsTabbedPane;
  }
  
  /**
   * Set the default background color.
   * 
   * @param  defaultColor    g
   * 
   */
  public static void setDefaultColor(final Color defaultBackgroundColor) {
	  Main.defaultBackgroundColor = defaultBackgroundColor;
  }
  
  /**
   * Get the default background color.
   */
  public static Color getDefaultColor() {
	  return Main.defaultBackgroundColor;
  }
  
  /**
   * Create the applications gui.
   * 
   * @param  void 
   * 
   */
  private static void createAndShowGui() {
    //Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);

    //Create and set up the window.
    final JFrame frame = new JFrame("Yearly Income Estimator");
    final Main app = new Main();
    frame.add(app);
    
    final Menu menuBar = new Menu();
    frame.setJMenuBar(menuBar.createMethod());
        
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
    //Display the window.
    frame.setSize(1380, 800);
    frame.setVisible(true);
  }
    
  /**
   * The main method to start the application.
   */
  public static void main(final String[] args) {
    //Schedule a job for the event dispatch thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGui();
      }
    });
  }
}
