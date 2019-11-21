package application.view.outputs;

import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import application.main.pdf.CreatePdf;
import application.main.pdf.Util;
import application.system.ApplicationLogger;
import application.system.ResultsDataNode;

/**
 * Creates the GUI tab for the Social Security results.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class SocialSecurity extends OutputObject {
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    private transient final ApplicationLogger logger = new ApplicationLogger();
    
    private transient final Utils utils = new Utils();
    
    /**
     * Create some utilities for this class.
     * 
     * @var Utils UTILS
     */
    private static final Util PDF_UTILS = new Util();
    private transient final String name = Header.SOCIAL_SECURITY.toString();
    private transient Font font;
    private static JTextArea textArea;
    private transient JScrollPane scroll;

  //private static List<ResultsDataNode> values;
  
    /** 
     * Get the data for the Social Security account.
     * 
     * @since  1.0
     */
    public SocialSecurity() {
      super();
      
        logger.trace("Called the Social Security constructor.");
      
        logger.trace("Leaving the Social Security constructor.");
    }
  
    /** 
     * Get the results tab panel for the tab.
     * 
     * @since  1.0
     * 
     * @return JPanel
     */
    public JPanel createPanel() {
        logger.trace("Entering the Social Security createPanel() with no parameters.");
        
        final JPanel jPanel = new JPanel(new FlowLayout());
      
        textArea = new JTextArea(27, 90);
        font = new Font("Monospaced", Font.PLAIN, 12);        
        textArea.setFont( font );        
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        jPanel.add(textArea);
      
        scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanel.add(scroll);
        
        logger.trace("Leaving the Social Security createPanel().");
        logger.trace("    returning jPanel=<" + jPanel + ">.");
        
        return jPanel;
    }
          
    /** 
     * Get the name of the tab.
     * 
     * @since  1.0
     * 
     * @return String
     */
    public String getName() {
        logger.trace("Entering the Social Security getName() with no parameters.");
        
        logger.trace("Leaving the Social Security getName().");
        logger.trace("     returning <" + name + ">");
        
        return name;
    }
  
    /** 
     * Write the header to the textArea in the tab.
     * 
     * @since  1.0
     * 
     * @return void
     */
    private void writeHeaders() {
        logger.trace("Entering the Social Security writeHeaders().");
        
        textArea.append(String.format("%5.5s%8.8s%18.18s\n", 
                                        "Year", 
                                        "Age", 
                                        "Annual Amount"));
        
        logger.trace("Leaving the Social Security writeHeaders().");
    }
  
    /** 
     * Write the data to the textArea in the tab.
     * 
     * @param  node         The data to write
     * @since  1.0
     * 
     * @return void
     */
    private void writeData(final ResultsDataNode node) {
        logger.trace("Entering the Social Security writeData().");
        logger.trace("    node=" + node.getEndingValue());
        
        final String string = String.format("%5s%8s%18.18s\n",
                              node.getYear(),
                              node.getAge(), 
                              utils.getDollarFormat(node.getEndingValue()));
       textArea.append(string);
      
       logger.trace("Leaving the Social Security writeData().");
    }
  
    /** 
     * Write the list of data to the textArea in the tab.
     * 
     * @param  values       The data to write
     * 
     * @since  1.0
     * 
     */
  public void writeListOfData(final List<ResultsDataNode> values) {
        logger.trace("Entering the Social Security writeListOfData().");
        logger.trace("    ArrayList=" + values);
        
        setList(values);
        
        writeHeaders();
      
        for (final ResultsDataNode node : values) {
          writeData(node);
        }   
        
        textArea.setCaretPosition(0);
        
        logger.trace("Leaving the Social Security writeListOfData().");
    }
    
    /** 
     * Clear the data to the textArea in the tab.
     * 
     * @since  1.0
     * 
     */
    public void clear() {
        logger.trace("Entering the Social Security clear() with no parameters.");

        textArea.setText(null);
        
        logger.trace("Leaving the Social Security clear().");
    }
   
  /** 
   * Retrieve the JTextArea.
   * 
   * @since  1.0
   * 
   * @return The list of values 
   */
  public JTextArea getTextArea() {
    return textArea;
  }
  
  /** 
   * Print out the Pdf first page of the output object.
   * 
   * @since  1.0
   * 
   * @return An indication that another page is required. 
   */
  public boolean writePdf(final PDDocument doc) {
    final CreatePdf createPdf = new CreatePdf();
    Integer yLocation = CreatePdf.LIST_LINE_POSITION;
    boolean returnValue;
    final PDPage page = new PDPage();      
    doc.addPage(page);
           
    try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
      createPdf.outputHeader(contents, getName());
 
      // Print out the header for the output text area values
      final String str = String.format(CreatePdf.FORMAT_YEAR_AGE_ONE_DOLLAR_AMOUNTS,  
          "Year", 
          "Age", 
          "Annual Amount");
      createPdf.outputString(contents, str, CreatePdf.HEADER_LINE_POSITION);

      if (getList() != null) {
    	  // Print out the data in the output text area (page 1 - possibly 2)
    	  for (int i=0; i <= CreatePdf.LIST_SIZE_PER_PAGE && i < getList().size(); i++) {
    		  final ResultsDataNode node = getList().get(i);
    		  final String string = String.format(CreatePdf.FORMAT_YEAR_AGE_ONE_DOLLAR_AMOUNTS, 
    				  node.getYear(),
    				  node.getAge(), 
    				  createPdf.leftPad(utils.getDollarFormat(node.getEndingValue()), CreatePdf.LEN));
    		  createPdf.outputString(contents, string, yLocation);

    		  yLocation -= CreatePdf.LINE_HEIGHT;
    	  }
      }
      contents.close();
    } catch (IOException e) {
      logger.error("Received an error in SocialSecurity writePdf()");
    }
    
    if (getList() == null) {
    	returnValue = false;
    }
    else {
    	returnValue = PDF_UTILS.isAnotherPageRequired(getList().size(), CreatePdf.LIST_SIZE_PER_PAGE);
    }
    
    return returnValue;
  }

  /** 
   * Print out the Pdf second page of the output object (if required).
   * 
   * @since  1.0
   * 
   * @return void // Printout the second page of the report
   */
  public void writePdf2(final PDDocument doc) {
    final CreatePdf createPdf = new CreatePdf();
    Integer yLocation = CreatePdf.LIST_LINE_POSITION; 
    
    if (getList().size() > CreatePdf.LIST_SIZE_PER_PAGE) {
      final PDPage page2 = new PDPage();
        
      doc.addPage(page2);
             
      try (PDPageContentStream contents2 = new PDPageContentStream(doc, page2)) {
  
        // Print out the header for the output text area values
        final String str2 = String.format(CreatePdf.FORMAT_YEAR_AGE_ONE_DOLLAR_AMOUNTS,  
            "Year", 
            "Age", 
            "Annual Amount");
        createPdf.outputString(contents2, str2, CreatePdf.HEADER_LINE_POSITION);
        // Print out the data in the output text area
        for (int i=CreatePdf.LIST_SIZE_PER_PAGE+1; i < getList().size(); i++) {
          final ResultsDataNode node = getList().get(i);
          final String string = String.format(CreatePdf.FORMAT_YEAR_AGE_ONE_DOLLAR_AMOUNTS, 
              node.getYear(),
              node.getAge(), 
              createPdf.leftPad(utils.getDollarFormat(node.getEndingValue()), CreatePdf.LEN));
           createPdf.outputString(contents2, string, yLocation);

           yLocation -= CreatePdf.LINE_HEIGHT;
         } 
      } catch (IOException e) {
        logger.error("Received an error in SocialSecurity writePdf2()");
      }
    }
  }
}
