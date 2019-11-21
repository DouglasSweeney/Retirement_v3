package application.view.outputs;

import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import application.main.pdf.CreatePdf;
import application.main.pdf.Util;
import application.system.ApplicationLogger;
import application.system.ResultsDataNode;

public abstract class OutputObject extends JPanel {
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
  */
  private transient List<ResultsDataNode> values; 
    
  /** 
   * Retrieve the list of values.
   * 
   * @since  1.0
   * 
   * @return The list of values
   */
  public List<ResultsDataNode> getList() {
    return values;
  }
  
  /** 
   * Retrieve the list of values.
   * 
   * @since  1.0
   * 
   * @return The list of values
   */
  public void setList(final List<ResultsDataNode> values) {
    this.values = values;
  }
  
  /** 
   * Print out the Pdf first pag    final OutputsLab outputsLab = new OutputsLab();
    
e of the output object.
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

    try {
      final PDPageContentStream contents = new PDPageContentStream(doc, page);
      createPdf.outputHeader(contents, getName());
      
      // Print out the header for the output text area values
      final String str = String.format(CreatePdf.FORMAT_YEAR_AGE_FOUR_DOLLAR_AMOUNTS,  
          "Year", 
          "Age", 
          "Beginning Balance",
          "Deposits",
          "Withdrawals", 
          "Ending Balance");
    		
      createPdf.outputString(contents, str, CreatePdf.HEADER_LINE_POSITION);

      if (values != null) {
    	  // Print out the data in the output text area (page 1 - possibly 2)
    	  for (int i=0; i <= CreatePdf.LIST_SIZE_PER_PAGE && i < values.size(); i++) {
    		  final ResultsDataNode node = getList().get(i);
    		  final String string = String.format(CreatePdf.FORMAT_YEAR_AGE_FOUR_DOLLAR_AMOUNTS, 
    				  node.getYear(),
    				  node.getAge(), 
    				  createPdf.leftPad(UTILS.getDollarFormat(node.getBeginningValue()), CreatePdf.LEN),
    				  createPdf.leftPad(UTILS.getDollarFormat(node.getDeposit()), CreatePdf.LEN),
    				  createPdf.leftPad(UTILS.getDollarFormat(node.getWithdrawal()), CreatePdf.LEN),
    				  createPdf.leftPad(UTILS.getDollarFormat(node.getEndingValue()), CreatePdf.LEN));
    		  createPdf.outputString(contents, string, yLocation);

    		  yLocation -= CreatePdf.LINE_HEIGHT;
    	  }
      }
      
      contents.close();
      
    } catch (IOException e) {
      LOGGER.error("Received an error in OutputObject writePdf()");
    }
    
    if (values == null) {
      returnValue = false;
    }
    else {
      returnValue = PDF_UTILS.isAnotherPageRequired(values.size(), CreatePdf.LIST_SIZE_PER_PAGE);
    }
    
    return returnValue;
  }
  
  /** 
   * Print out the Pdf second page of the output object (if required).
   * 
   * @since  1.0
   * 
   */
  public void writePdf2(final PDDocument doc) {
    final CreatePdf createPdf = new CreatePdf();
    Integer yLocation = CreatePdf.LIST_LINE_POSITION; 
    
    if (values.size() > CreatePdf.LIST_SIZE_PER_PAGE) {
      final PDPage page2 = new PDPage();
        
      doc.addPage(page2);
             
      try ( PDPageContentStream contents2 = new PDPageContentStream(doc, page2)) {
  
        // Print out the header for the output text area values
        final String str2 = String.format(CreatePdf.FORMAT_YEAR_AGE_FOUR_DOLLAR_AMOUNTS,  
            "Year", 
            "Age", 
            "Beginning Balance",
            "Deposits",
            "Withdrawals", 
            "Ending Balance");
        createPdf.outputString(contents2, str2, CreatePdf.HEADER_LINE_POSITION);
        // Print out the data in the output text area
        for (int i=CreatePdf.LIST_SIZE_PER_PAGE+1; i < getList().size(); i++) {
          final ResultsDataNode node = getList().get(i);
          final String string = String.format(CreatePdf.FORMAT_YEAR_AGE_FOUR_DOLLAR_AMOUNTS, 
              node.getYear(),
              node.getAge(), 
              createPdf.leftPad(UTILS.getDollarFormat(node.getBeginningValue()), CreatePdf.LEN),
              createPdf.leftPad(UTILS.getDollarFormat(node.getDeposit()), CreatePdf.LEN),
              createPdf.leftPad(UTILS.getDollarFormat(node.getWithdrawal()), CreatePdf.LEN),
              createPdf.leftPad(UTILS.getDollarFormat(node.getEndingValue()), CreatePdf.LEN));
           createPdf.outputString(contents2, string, yLocation);

           yLocation -= CreatePdf.LINE_HEIGHT;
         } 
      } catch (IOException e) {
        LOGGER.error("Received an error in OutputObject writePdf2()");
      }
    }
  }
}
