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
 * Creates the GUI tab for the annual expenses results.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class Expenses extends OutputObject {
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
    
  /**
   * Create some utilities for this class.
   * 
   * @var Utils UTILS
   */
  private static final Utils UTILS = new Utils();
    
  /**
   * Create some utilities for this class.
   * 
   * @var Utils UTILS
   */
  private static final Util PDF_UTILS = new Util();
    
  /**
   * Create a name for this tabbed panel.
   * 
   * @var String NAME
   */
  private static final String NAME = Header.EXPENSES.toString();
  
  /**
   * Create a variable for output.
   * 
   * @var JTextArea textArea
   */
  private static JTextArea textArea;
  
  /** 
   * Get the data for the Expenses account.
   * 
   * @since  1.0
   */
  public Expenses() {
    super();
      
    LOGGER.trace("Called the Expenses constructor.");
      
    LOGGER.trace("Leaving the Expenses constructor.");
  }
  
  /** 
   * Create the results tab panel for the tab.
   * 
   * @since  1.0
   * 
   * @return JPanel
   */
  public JPanel createPanel() {
    LOGGER.trace("Entering the Expenses createPanel() with no parameters.");
        
    Font font;
    JScrollPane scroll;
    final JPanel jPanel = new JPanel(new FlowLayout());
      
    textArea = new JTextArea(27, 90);
    font = new Font("Monospaced", Font.PLAIN, 12);
    textArea.setFont(font);
    textArea.setLineWrap(true);
    textArea.setEditable(false); 
    jPanel.add(textArea);
      
    scroll = new JScrollPane(textArea); 
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jPanel.add(scroll);
        
    LOGGER.trace("Leaving the Expenses createPanel().");
    LOGGER.trace("    returning jPanel=<" + jPanel + ">.");
        
    return jPanel;
  }
          
  /**  
   * Get the name of the tab.
   * 
   * @since  1.0
   * 
   * @return The name of the tab
   */
  public String getName() {
    LOGGER.trace("Entering the Expenses getName() with no parameters.");
        
    LOGGER.trace("Leaving the Expenses getName().");
    LOGGER.trace("     returning <" + NAME + ">");
        
    return NAME;
  }
  
  /** 
   * Write the headers to the textArea in the tab.
   * 
   * @since  1.0
   * 
   */
  private void writeHeaders() {
    LOGGER.trace("Entering the Expenses writeHeaders().");
       
    textArea.append(String.format("%5.5s%8.8s%19.19s%17.17s\n", 
                                  "Year", 
                                  "Age", 
                                  "Beginning Expenses", 
                                  "Ending Expenses"));
        
    LOGGER.trace("Leaving the Expenses writeHeaders().");
  }
    
  /** 
   * Write the data to the textArea in the tab.
   * 
   * @param  node         The data to write
   * @since  1.0
   * 
   */
  private void writeData(final ResultsDataNode node) {
    LOGGER.trace("Entering the Expenses writeData().");
    LOGGER.trace("    node=" + node.getEndingValue());
        
    final String string = String.format("%5d%8d%15.15s%20.20s\n",
                            node.getYear(),
                            node.getAge(), 
                            UTILS.getDollarFormat(node.getBeginningValue()),
                            UTILS.getDollarFormat(node.getEndingValue()));
    textArea.append(string);
        
    LOGGER.trace("Leaving the Expenses writeData().");
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
    LOGGER.trace("Entering the Expenses writeListOfData().");
    LOGGER.trace("    ArrayList=" + values);
        
    setList(values);
       
    writeHeaders();

    for (final ResultsDataNode node : values) {
      writeData(node);
    }
        
    textArea.setCaretPosition(0);
        
    LOGGER.trace("Leaving the Expenses writeListOfData().");
  }    
    
  /** 
   * Clear the data to the textArea in the tab.
   * 
   * @since  1.0
   * 
   */
  public void clear() {
    LOGGER.trace("Entering the Expenses clear().");
        
    textArea.setText(null);
      
    LOGGER.trace("Leaving the Expenses clear().");
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
    final PDPage page = new PDPage();
    boolean returnValue;
    
    doc.addPage(page);
           
    try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
      createPdf.outputHeader(contents, getName());
 
      // Print out the header for the output text area values
      final String str = String.format(CreatePdf.FORMAT_YEAR_AGE_TWO_DOLLAR_AMOUNTS,  
          "Year", 
          "Age", 
          "Beginning Balance",
          "Ending Balance");
      createPdf.outputString(contents, str, CreatePdf.HEADER_LINE_POSITION);

      if (getList() != null) {
    	  // Print out the data in the output text area (page 1 - possibly 2)
    	  for (int i=0; i <= CreatePdf.LIST_SIZE_PER_PAGE && i < getList().size(); i++) {
    		  final ResultsDataNode node = getList().get(i);
    		  final String string = String.format(CreatePdf.FORMAT_YEAR_AGE_TWO_DOLLAR_AMOUNTS, 
    				  node.getYear(),
    				  node.getAge(), 
    				  createPdf.leftPad(UTILS.getDollarFormat(node.getBeginningValue()), CreatePdf.LEN),
    				  createPdf.leftPad(UTILS.getDollarFormat(node.getEndingValue()), CreatePdf.LEN));
    		  createPdf.outputString(contents, string, yLocation);

    		  yLocation -= CreatePdf.LINE_HEIGHT;
    	  }
    	  contents.close();
      }
    } catch (IOException e) {
      LOGGER.error("Received an error in Expenses writePdf()");
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
   * , String... strings 
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
        final String str2 = String.format(CreatePdf.FORMAT_YEAR_AGE_TWO_DOLLAR_AMOUNTS,  
            "Year", 
            "Age", 
            "Beginning Balance",
            "Ending Balance");
        createPdf.outputString(contents2, str2, CreatePdf.HEADER_LINE_POSITION);
        // Print out the data in the output text area
        for (int i=CreatePdf.LIST_SIZE_PER_PAGE+1; i < getList().size(); i++) {
          final ResultsDataNode node = getList().get(i);
          final String string = String.format(CreatePdf.FORMAT_YEAR_AGE_TWO_DOLLAR_AMOUNTS, 
              node.getYear(),
              node.getAge(), 
              createPdf.leftPad(UTILS.getDollarFormat(node.getBeginningValue()), CreatePdf.LEN),
              createPdf.leftPad(UTILS.getDollarFormat(node.getEndingValue()), CreatePdf.LEN));
           createPdf.outputString(contents2, string, yLocation);

           yLocation -= CreatePdf.LINE_HEIGHT;
         } 
      } catch (IOException e) {
        LOGGER.error("Received an error in Expenses writePdf2()");
      }
    }
  }
}