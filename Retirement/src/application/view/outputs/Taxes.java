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
 * Creates the GUI tab for the Taxes results.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class Taxes extends OutputObject {
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
    private transient final String name = Header.TAXES.toString();
    private transient Font font;
    private static JTextArea textArea2;
    private transient JScrollPane scroll;
    
    /**
     * Create a name for this tabbed panel.
     * 
     * @var List values
     */
//    public static List<ResultsDataNode> values;

    /** 
     * Get the data for the Brokerage account.
     * 
     * @since  1.0
     */
    public Taxes() {
      super();
      
        logger.trace("Called the Taxes constructor.");
      
        logger.trace("Leaving the Taxes constructor.");
    }
  
    /** 
     * Get the results tab panel for the tab.
     * 
     * @since  1.0
     * 
     * @return JPanel
     */
    public JPanel createPanel() {
        logger.trace("Entering the Taxes createPanel() with no parameters.");
        
        final JPanel jPanel = new JPanel(new FlowLayout());
      
        textArea2 = new JTextArea(27, 90);
        font = new Font("Monospaced", Font.PLAIN, 12);        
        textArea2.setFont( font );
        textArea2.setLineWrap(true);
        textArea2.setEditable(false);
        jPanel.add(textArea2);
      
        scroll = new JScrollPane (textArea2);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanel.add(scroll);
        
        logger.trace("Leaving the Taxes createPanel().");
        logger.trace("    returning jPanel=<" + jPanel + ">.");
        
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
        logger.trace("Entering the Taxes getName() with no parameters.");
        
        logger.trace("Leaving the Taxes getName().");
        logger.trace("     returning <" + name + ">");
        
        return name;
    }
  
    /** 
     * Write the headers to the textArea in the tab.
     * 
     * @since  1.0
     * 
     */
    private void writeHeaders() {
        logger.trace("Entering the Taxes writeHeaders().");
        
        textArea2.append(String.format("%5.5s%8.8s%15.15s%13.13s%13.13s\n", 
                                        "Year", 
                                        "Age", 
                                        "Federal Taxes", 
                                        "State Taxes",  
                                        "Total Taxes"));
        
        logger.trace("Leaving the Taxes writeHeaders().");
    }
  
    /** 
     * Write the data to the textArea in the tab.
     * 
     * @param  node         The data to write
     * 
     * @since  1.0
     */
    private void writeData(final ResultsDataNode node) {
        logger.trace("Entering the Taxes writeData().");
        logger.trace("    node=" + node.getEndingValue());
        
        final String string = String.format("%5s%8s%12.12s%13.13s%13.13s\n",
                              node.getYear(),
                              node.getAge(), 
                              utils.getDollarFormat(node.getFederalTaxes()),
                              utils.getDollarFormat(node.getStateTaxes()),
                              utils.getDollarFormat(node.getEndingValue()));
       textArea2.append(string);
      
       logger.trace("Leaving the Taxes writeData().");
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
        logger.trace("Entering the Taxes writeListOfData().");
        logger.trace("    ArrayList=" + values);
        
        setList(values);
        
        writeHeaders();
      
        for (final ResultsDataNode node : values) {
          writeData(node);
        }   
        
        textArea2.setCaretPosition(0);
        
        logger.trace("Leaving the Taxes writeListOfData().");
    }
    
    /** 
     * Clear the data to the textArea in the tab.
     * 
     * @since  1.0
     * 
     */
    public void clear() {
        logger.trace("Entering the Taxes clear() with no parameters.");

        textArea2.setText(null);
        
        logger.trace("Leaving the Taxes clear().");
     }
    
    /** 
     * Retrieve the JTextArea.
     * 
     * @since  1.0
     * 
     * @return The list of values 
     */
    public JTextArea getTextArea() {
      return textArea2;
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
        final String str = String.format(CreatePdf.FORMAT_YEAR_AGE_THREE_DOLLAR_AMOUNTS,  
            "Year", 
            "Age", 
            "Federal Taxes", 
            "State Taxes",  
            "Total Taxes");
        createPdf.outputString(contents, str, CreatePdf.HEADER_LINE_POSITION);

        if (getList() != null) {
        	// Print out the data in the output text area (page 1 - possibly 2)
        	for (int i=0; i <= CreatePdf.LIST_SIZE_PER_PAGE && i < getList().size(); i++) {
        		final ResultsDataNode node = getList().get(i);
        		final String string = String.format(CreatePdf.FORMAT_YEAR_AGE_THREE_DOLLAR_AMOUNTS, 
        				node.getYear(),
        				node.getAge(), 
        				createPdf.leftPad(utils.getDollarFormat(node.getFederalTaxes()), CreatePdf.LEN),
        				createPdf.leftPad(utils.getDollarFormat(node.getStateTaxes()), CreatePdf.LEN),
        				createPdf.leftPad(utils.getDollarFormat(node.getEndingValue()), CreatePdf.LEN));
        		createPdf.outputString(contents, string, yLocation);

        		yLocation -= CreatePdf.LINE_HEIGHT;
        	}
        }
        
        contents.close();
      } catch (IOException e) {
        logger.error("Received an error in Account401K writePdf()");
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
          final String str2 = String.format(CreatePdf.FORMAT_YEAR_AGE_THREE_DOLLAR_AMOUNTS,  
              "Year", 
              "Age", 
              "Federal Taxes", 
              "State Taxes",  
              "Total Taxes");
          createPdf.outputString(contents2, str2, CreatePdf.HEADER_LINE_POSITION);
          // Print out the data in the output text area
          for (int i=CreatePdf.LIST_SIZE_PER_PAGE+1; i < getList().size(); i++) {
            final ResultsDataNode node = getList().get(i);
            final String string = String.format(CreatePdf.FORMAT_YEAR_AGE_THREE_DOLLAR_AMOUNTS, 
                node.getYear(),
                node.getAge(), 
                createPdf.leftPad(utils.getDollarFormat(node.getBeginningValue()), CreatePdf.LEN),
                createPdf.leftPad(utils.getDollarFormat(node.getDeposit()), CreatePdf.LEN),
                createPdf.leftPad(utils.getDollarFormat(node.getWithdrawal()), CreatePdf.LEN),
                createPdf.leftPad(utils.getDollarFormat(node.getEndingValue()), CreatePdf.LEN));
             createPdf.outputString(contents2, string, yLocation);

             yLocation -= CreatePdf.LINE_HEIGHT;
           } 
        } catch (IOException e) {
          logger.error("Received an error in Taxes writePdf2()");
        }
      }
    }
}