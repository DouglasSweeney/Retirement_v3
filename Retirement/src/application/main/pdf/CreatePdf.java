package application.main.pdf;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage; 
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import application.main.InputsLab;
import application.main.OutputsLab;
import application.system.ApplicationLogger;
import application.view.inputs.Account401K;
import application.view.inputs.Account403B;
import application.view.inputs.AccountCashBalance;
import application.view.inputs.AccountIra;
import application.view.inputs.AccountRoth;
import application.view.inputs.InputObject;
import application.view.inputs.InputsTabbedPane;
import application.view.outputs.OutputObject;
  
public class CreatePdf {
  public final static String DIRECTORY = "logs/pdf/"; 
  public final static Integer LEN = 12; // Length of field (including padding)
  public final static Integer LIST_SIZE_PER_PAGE = 65; // Number of entries on first page of list
  public final static Integer LINE_HEIGHT = 10; // Line height in the Pdf
  public final static Integer HEADER_LINE_POSITION = 720; // The Y location of the header 
  public final static Integer LIST_LINE_POSITION = 710; // The Y location of the list (first node)
  public final static String  FORMAT_YEAR_AGE_FOUR_DOLLAR_AMOUNTS = 
      "%5.5s%8.8s %18.18s %13.13s    %13.13s   %15.15s";
  public final static String  FORMAT_YEAR_AGE_THREE_DOLLAR_AMOUNTS = 
      "%5.5s%8.8s %18.18s %13.13s    %13.13s";
  public final static String  FORMAT_YEAR_AGE_TWO_DOLLAR_AMOUNTS = 
      "%5.5s%8.8s %18.18s %15.15s";
  public final static String  FORMAT_YEAR_AGE_ONE_DOLLAR_AMOUNTS = 
      "%5.5s%8.8s %18.18s";
  
  /**
   * Write out trace execution methods via this variable.
   * 
   * @var ApplicationLogger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();
  
  public String getPdfFilename(final String directory, final String inputFilename) {
    int i = 0;
    boolean found;
    String baseFilename = "";
    String pdfFilename = "";
    
    // Scan backwards for a slash in the absolute directory
    found = false;
    for (i = inputFilename.length()-1; i>=0 && !found; i--) {
      if (inputFilename.charAt(i) == File.separatorChar) {
        found = true;
        i += 2; // skip over the slash && account for i-- in for loop
      }
    }
   
    // Scan forwards & build the basename
    found = false;
    for (; i < inputFilename.length() && !found; i++) {
      if (inputFilename.charAt(i) == '.') {
        found = true;
      }
      else {
        baseFilename += inputFilename.charAt(i);
      }
    }
    
    found = false;
    for (i=0; i < Integer.MAX_VALUE && !found; i++) {
      final String fileNumber = String.format("%010d", i);
      pdfFilename = String.format("%s%S.pdf", baseFilename, fileNumber);
      final File file = new File(directory + pdfFilename);
      if (!file.exists()) {
        found = true;
      }
    }
    
    return directory + pdfFilename;
  }
  
public void create(final String filename) throws IOException {
	String inputFilename;
	if (InputsTabbedPane.getInputFilename() == null) {
      inputFilename = filename;
	}
	else {
      inputFilename = InputsTabbedPane.getInputFilename();
    }
    final String pdfFilename = getPdfFilename(DIRECTORY, inputFilename);
    		
    final PDDocument doc = new PDDocument();
    	
    // write 401k input
    final Account401K account401k = InputsLab.getInstance().getAccount401K();
    String name = account401k.getName();
    writeAccount401k(name, doc, account401k);
      
    // write 403b input
    final Account403B account403b = InputsLab.getInstance().getAccount403B();
    name = account403b.getName();
    writeAccount403b(name, doc, account403b);
      
    // write Brokerage input
    List<InputObject> list2 = InputsLab.getInstance().getBrokerageInputs();
    writeInputObjectList(doc, list2);

    // write Cash Balance input
    final AccountCashBalance accountCashBalance = InputsLab.getInstance().getAccountCashBalance();
    name = accountCashBalance.getName();
    writeAccountCashBalance(name, doc, accountCashBalance);
            
    // write Deductions & Expenses inputs
    list2 = InputsLab.getInstance().getDeductionsAndExpensesInputs();
    writeInputObjectList(doc, list2);
      
      // write IRA input
    final AccountIra accountIra = InputsLab.getInstance().getAccountIra();
    name = accountIra.getName();
    writeAccountIra(name, doc, accountIra);
 
    // write Pension & Personal inputs
    list2 = InputsLab.getInstance().getPensionAndPersonalInputs();
    writeInputObjectList(doc, list2);

    // write Roth input
    final AccountRoth accountRoth = InputsLab.getInstance().getAccountRoth();
    name = accountRoth.getName();
    writeAccountRoth(name, doc, accountRoth);
      
    // write the rest of the inputs
    list2 = InputsLab.getInstance().getRestInputs();
    writeInputObjectList(doc, list2);

    // write 401K->Expenses output
    final OutputsLab outputsLab = new OutputsLab();
    final List<OutputObject> leftList = outputsLab.getLeftOutputs();
    writeOutputObjectList(doc, leftList);
      
    final JFreeChart chart = OutputsLab.getIncomeChart();
    saveIncomeChartImage("Income", chart);
    createPdfFromImage(doc, "Income Graph Output", DIRECTORY + "Income.png");
            
    // write Ira->Savings output
    final List<OutputObject> middleList = outputsLab.getMiddleOutputs();
    writeOutputObjectList(doc, middleList);
      
    final JFreeChart chart2 = OutputsLab.getSavingChart();
    saveSavingChartImage("Saving", chart2);
    createPdfFromImage(doc, "Savings Graph", DIRECTORY + "Saving.png");

    // write Social Security->Taxes output
    final List<OutputObject> rightList = outputsLab.getRightOutputs();
    writeOutputObjectList(doc, rightList);
      
    doc.save(pdfFilename);
    doc.close();
      
//PDMetadata metadata = new PDMetadata(doc);
//OutputStream osMeta = metadata.createOutputStream();
//new XmpSerializer().serialize(xmp, osMeta, true);
//osMeta.close();

    // Delete both image files
    final File file1 = new File(DIRECTORY + "Income.png");
    final File file2 = new File(DIRECTORY + "Saving.png");
      
    if (!file1.delete()) {
      LOGGER.trace("Failure on delete() of Income.png");
    }
      
    if (!file2.delete()) {
      LOGGER.trace("Failure on delete() of Saving.png");
    }
  }
  
  private void writeAccount401k(final String name, final PDDocument doc, 
                                final Account401K account401k) {
    account401k.writePdf(name, doc);
  }
  
  private void writeAccount403b(final String name, final PDDocument doc, 
                                final Account403B account403b) {
    account403b.writePdf(name, doc);
  }
  
  private void writeAccountCashBalance(final String name, final PDDocument doc, 
                                       final AccountCashBalance accountCashBalance) {
    accountCashBalance.writePdf(name, doc);
  }

  private void writeAccountIra(final String name, final PDDocument doc, 
                               final AccountIra accountIra) {
    accountIra.writePdf(name, doc);
  }

  private void writeAccountRoth(final String name, final PDDocument doc, 
                                final AccountRoth accountRoth) {
    accountRoth.writePdf(name, doc);
  }

  private void writeInputObjectList(final PDDocument doc, final List<InputObject> list) {
    final Iterator<InputObject> it = list.iterator();
  
    while (it.hasNext()) {
      final InputObject input = it.next();
      final String name = input.getName(); // no syntax error
      input.writePdf(name, doc);
    }
  }
  
    private void writeOutputObjectList(final PDDocument doc, final List<OutputObject> list) {
    final Iterator<OutputObject> it = list.iterator();
    OutputObject output;
    boolean twoPages;
    while (it.hasNext()) {
      output = it.next();
      twoPages = output.writePdf(doc);
      if (twoPages) {
        output.writePdf2(doc);
      }
    }
  }
  
  public void inputHeader(final PDPageContentStream contents, final String name) throws IOException {
    final PDFont font = PDType1Font.HELVETICA_BOLD;
  
    contents.beginText();
    contents.setFont(font, 12);    
    contents.newLineAtOffset(200, 700);
    contents.showText(name + " Input");
    contents.endText();
  }
  
  public void outputHeader(final PDPageContentStream contents, final String name) throws IOException {
    final PDFont font = PDType1Font.HELVETICA_BOLD;
  
    contents.beginText();
    contents.setFont(font, 12);  
    contents.newLineAtOffset(200, 750);
    contents.showText(name + " Output");
    contents.endText();
  }
  
  public void entry(final PDPageContentStream contents, final String prompt, 
                    final String userEntry, final Integer yLocation) throws IOException {
    final PDFont font = PDType1Font.HELVETICA_BOLD;
  
    // Print out the balance
    contents.beginText();
    contents.setFont(font, 12);
    contents.newLineAtOffset(150, yLocation);
    contents.showText(prompt); 
    contents.endText();
 
    contents.beginText();
    contents.setFont(font, 12);
    contents.newLineAtOffset(350, yLocation);
    contents.showText(userEntry);

    contents.endText();
  }
  
  public void entry(final PDPageContentStream contents, final String outputString,
		            final int xLocation, final int yLocation) throws IOException {
     final PDFont font = PDType1Font.HELVETICA_BOLD;

	  
     // Print out the balance
     contents.beginText();
     contents.setFont(font, 12);
	 contents.newLineAtOffset(xLocation, yLocation);
     contents.showText(outputString);
     contents.endText();
  }
  
  public void outputString(final PDPageContentStream contents, final String string, 
		                   final Integer yLocation) 
                                                                                                throws IOException {
    final PDFont font = PDType1Font.COURIER;
  
    // Print out the balance
    contents.beginText();
    contents.setFont(font, 10);
    contents.newLineAtOffset(50, yLocation);
    contents.showText(string); 
    contents.endText();
  }
  
  public String leftPad(final String input, final int len) {
    StringBuilder str = new StringBuilder(input);
    for (int i=0; i<len-input.length() + 1; i++) {
       str = str.insert(0,  ' '); //
    }

    return str.toString();
  }

  public void saveIncomeChartImage(final String filename, final JFreeChart chart) {
	if (chart == null) {
		return;
	}
			
    try {
      ChartUtilities.saveChartAsPNG(new File("logs/pdf/" + filename + ".png"), chart, 400, 300);
    } catch (IOException e) {
      LOGGER.error(e.toString());
    }
  }

  public void saveSavingChartImage(final String filename, final JFreeChart chart) {
	if (chart == null) {
		return;
	}
			
    try {
      ChartUtilities.saveChartAsPNG(new File("logs/pdf/" + filename + ".png"), chart, 400, 300);
    } catch (IOException e) {
      LOGGER.error(e.toString());
    }
  }
  
  public void createPdfFromImage(final PDDocument doc, final String header, final String imagePath)
              throws IOException {
	final File file = new File(imagePath);
	if (file.exists() == false) {
		return;
	}
			
    final PDFont font = PDType1Font.HELVETICA_BOLD;
    final PDPage page = new PDPage();      
    doc.addPage(page);
  
    // createFromFile is the easiest way with an image file
    // if you already have the image in a BufferedImage, 
    // call LosslessFactory.createFromImage() instead
    final PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, doc);
  
    try (PDPageContentStream contentStream = new PDPageContentStream(doc, page)) {
      // Print out the balance
      contentStream.beginText();
      contentStream.setFont(font, 10);
      contentStream.newLineAtOffset(200, 720);
      contentStream.showText(header); 
      contentStream.endText();
      
       contentStream.drawImage(pdImage, 20, 200, pdImage.getWidth() * 1.5f, pdImage.getHeight() * 1.5f);
    }
  }
}