package application.view.inputs;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import application.system.ApplicationLogger;

/**
 * The user saves the properties file of the input data.
 * 
 * @author      Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class OutputFile {
  /**
   * Set the LOGGER property to its value.
   * 
   * @var Application_Logger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();

  /** 
   * Prompt the user for the output filename.
   * 
   * @since  1.0
   * 
   * @return String
   */
  public String getFileName() {
    LOGGER.trace("Entering the OutputFile getFilename() with no parameters.");

    String filename = "";

    try {
      filename = JOptionPane.showInputDialog(
          "Please enter a filename (your name) to save the data.");
      if (filename == null || filename.equals("")) {
        JOptionPane.showMessageDialog(null, "Error while saving properties (saving to temp.xml)",
            "Possible invalid filename", JOptionPane.ERROR_MESSAGE);
        filename = "temp.xml";
      } else {
        filename += ".xml";

        final File file = new File(filename);

        // if file doesn't exist, then create it
        if (!file.exists() && !file.createNewFile()) {
            LOGGER.trace("File (" + filename + ") not created.");
        }
      } 
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Error while saving properties",
          "Possible invalid filename", JOptionPane.ERROR_MESSAGE);
    }

    LOGGER.trace("Leaving the OutputFile getFilename().");
    LOGGER.trace("    filename=<" + filename + ">.");

    return filename;
  }
}
