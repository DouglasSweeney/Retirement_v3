package application.view.inputs;

import java.io.IOException;

import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import application.main.pdf.CreatePdf;
import application.system.ApplicationLogger;

public abstract class InputObject extends JPanel {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Write out trace execution methods via this variable.
   * 
   * @var ApplicationLogger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();
  
  public abstract void writePdf(String name, PDDocument doc); 
  
  public void writePdf(final PDDocument doc, final String... strings) {
    final CreatePdf createPdf = new CreatePdf();
    
    final PDPage page = new PDPage();
    doc.addPage(page);
           
     try {
      final PDPageContentStream contents = new PDPageContentStream(doc, page); 
      createPdf.inputHeader(contents, getName());
 
      int xLocation;
      int yLocation = 650;
      boolean firstField = true;
      for (final String string : strings) {
    	 if (firstField) {
    		xLocation = 150;
    		
            createPdf.entry(contents, string, xLocation, yLocation);
            
            firstField = false;
    	 }
    	 else {
    		xLocation = 350;
    		createPdf.entry(contents, string, xLocation, yLocation);
 			yLocation -= 25;
    		firstField = true;
          }
      }      
	  contents.close();
    } catch (IOException e) {
      LOGGER.trace(e.getMessage());
    } 
		
  }
}
