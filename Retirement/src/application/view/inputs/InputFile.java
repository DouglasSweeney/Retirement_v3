package application.view.inputs;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import application.system.ApplicationLogger;

/**
 * The user gets the properties file for the input data.
 * 
 * @author      Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class InputFile {
  /**
   * Set the LOGGER property to its value.
   * 
   * @var Application_LOGGER LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();
  
  /**
   * Create a variable to hold the choosen file.
   * 
   * @var File chosenFile
   */
  private static File chosenFile;

    /** 
     * Get the system file from the user.
     * 
     * @since  1.0
     */
  public InputFile (final String title)
  {
    LOGGER.trace ("Entering the InputFile constructor with no parameters.");

    final JFileChooser chooser = new JFileChooser ("input_xmls");

    final FileNameExtensionFilter filter =
      new FileNameExtensionFilter ("Input Data Files", "xml");
      chooser.setFileFilter (filter);
      chooser.setDialogTitle(title);
    final int returnVal = chooser.showOpenDialog (null);

    if (returnVal == JFileChooser.APPROVE_OPTION)
      {
	chosenFile = chooser.getSelectedFile ();
      }
    else {
        System.exit (2);
    }

    LOGGER.trace ("Leaving the InputFile constructor.");
  }

   /** 
     * Get the file that was chosen by the user.
     * 
     * @since  1.0
     * 
     * @return String
     */
  public String getChosenFile ()
  {
    String returnValue = "";
    LOGGER.trace ("Entering the InputFile getChosenFile() with no parameters.");

    if (chosenFile != null) {
	     LOGGER.trace ("Leaving the InputFile getChosenFile with no parameters.");
	     LOGGER.trace ("    Returning <" + chosenFile.getAbsolutePath () + ">");

       returnValue = chosenFile.getAbsolutePath ();
    }

    if (returnValue.equals("") == true) {
       LOGGER.trace ("Leaving the InputFile getChosenFile() with no parameters.");
       LOGGER.trace ("    Returning <" + "." + ">");

       returnValue = ".";
    }
  
    return returnValue;
  }
}
