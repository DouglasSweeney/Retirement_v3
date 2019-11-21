package application.view.inputs;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;

import application.main.XmlReader;
import application.main.XmlWriter;
import application.system.ApplicationLogger;

/**
 * Creates the GUI tab for the Brokerage inputs.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class Taxes extends InputObject {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Set the string for logging.
   * 
   * @var String RETURNING
   */
  private static final String RETURNING = "     returning <";

  /**
   * Set the federal tax rate property to its value.
   * 
   * @var String FEDERAL_TAX_RATE_PROPERTY
   */
  public static final String FEDERAL_TAX_RATE_PROPERTY = "taxRateFederal";

  /**
   * Set the state tax rate property to its value.
   * 
   * @var String STATE_TAX_RATE_PROPERTY
   */
  public static final String STATE_TAX_RATE_PROPERTY = "taxRateState";

  /**
   * Set the LOGGER property to its value.
   * 
   * @var Application_Logger LOGGER
   */
  private static final ApplicationLogger LOGGER = new ApplicationLogger();

  /**
   * Set the name of the tab.
   * 
   * @var String NAME
   */
  private static final String NAME = Header.TAXES.toString();;
  
  /**
   * Create an instance of the utils.
   * 
   * @var Utils utils
   */
  private static final Utils UTILS = new Utils();

  /**
   * Get an instance of the Property.
   * 
   * @var XmlReader xmlReader
   */
  private transient XmlReader xmlReader;

  /**
   * Set the input for the federal tax rate.
   * 
   * @var JFormattedTextField federalTaxRate
   */
  private static ComboBoxList federalTaxRate;
   
  /**
   * Set the input for the state tax rate.
   * 
   * @var JFormattedTextField stateTaxRate
   */
  private static ComboBoxList stateTaxRate;  

  /** 
   * No properties are passed in(as normal). This allows
   * the model to get access to the inputs.
   * 
   * @since  1.0
   */
  public Taxes() {
    super();

    LOGGER.trace("Called the Taxes constructor with no parameters.");

    LOGGER.trace("Leaving the Taxes constructor.");
  }

  /** 
   * Get the properties for the Taxes account.
   * 
   * @param  props     The properties for the application
   * @since  1.0
   */
  public Taxes(final XmlReader xmlReader) {
    super();

    LOGGER.trace("Called the Taxes constructor.");
    LOGGER.trace("    xmlReader=<" + xmlReader + ">.");

    this.xmlReader = xmlReader;

    LOGGER.trace("Leaving the Taxes constructor.");
  }

  /** 
   * Get the input tab panel for the tab.
   * 
   * @since  1.0
   * 
   * @return JPanel
   */
  public JPanel createPanel() {
    LOGGER.trace("Entering the Taxes createPanel() with no parameters.");

    final JPanel jPanel = new JPanel(new GridLayout(2, 2));

    JLabel label = new JLabel(Entry.EFFECTIVE_FEDERAL_TAX_RATE.toString());
    jPanel.add(label);
    federalTaxRate  = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_50_BY_ZERO_POINT_FIVE,
        UTILS.getPercentFormat(xmlReader.getDoubleProperty(FEDERAL_TAX_RATE_PROPERTY)));
    jPanel.add(federalTaxRate.getComboBox());

    label = new JLabel(Entry.EFFECTIVE_STATE_TAX_RATE.toString());
    jPanel.add(label);
    stateTaxRate = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_50_BY_ZERO_POINT_FIVE,
        UTILS.getPercentFormat(xmlReader.getDoubleProperty(STATE_TAX_RATE_PROPERTY)));
    jPanel.add(stateTaxRate.getComboBox());

    LOGGER.trace("Leaving the Taxes createPanel().");
    LOGGER.trace("    returning jPanel=<" + jPanel + ">.");

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
    LOGGER.trace("Entering the Taxes getName() with no parameters.");

    LOGGER.trace(RETURNING + NAME + ">");

    return NAME;
  }

  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return double
   */
  public double getFederalTaxRate() {
    LOGGER.trace("Entering the Taxes getFederalTaxRate() with no parameters.");

    final String inputString = federalTaxRate.getValue();
    final String inputString2 = inputString.replaceAll("%", "");
    final String inputString3 = inputString2.replaceAll(",", "");
    double value = -987.0;  // -987.0 for invalid field

    value = Double.valueOf(inputString3);
    value /= 100;

    LOGGER.trace("Leaving the Taxes getFederalTaxRate().");
    LOGGER.trace(RETURNING + value + ">");

    return value;
  }


  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return double
   */
  public double getStateTaxRate() {
    LOGGER.trace("Entering the Taxes getStateTaxRate() with no parameters.");

    final String inputString = stateTaxRate.getValue();
    final String inputString2 = inputString.replaceAll("%", "");
    final String inputString3 = inputString2.replaceAll(",", "");
    double value = -987.0;  // -987.0 for invalid field

    value = new Double(inputString3);
    value /= 100;

    LOGGER.trace("Leaving the Taxes getStateTaxRate().");
    LOGGER.trace(RETURNING + value + ">");

    return value;
  }

  /** 
   * Save the properties of this object.
   * 
   * @since  1.0
   * 
   */
  public void saveProperties(final XmlWriter xmlWriter) {
    LOGGER.trace("Entering the Taxes saveProperties() with no parameters.");
    
    xmlWriter.putNode("Taxes");
    
    xmlWriter.putDoubleAttribute(FEDERAL_TAX_RATE_PROPERTY, getFederalTaxRate());   
    xmlWriter.putDoubleAttribute(STATE_TAX_RATE_PROPERTY, getStateTaxRate());
 
    xmlWriter.putClosingNode("Taxes");
      
    LOGGER.trace("Leaving the Taxes saveProperties().");
  }
  
  public void writePdf(final String name, final PDDocument doc) {
	  writePdf(doc, 
			  Entry.EFFECTIVE_FEDERAL_TAX_RATE.toString(), UTILS.getPercentFormat(getFederalTaxRate()),
		      Entry.EFFECTIVE_STATE_TAX_RATE.toString(), UTILS.getPercentFormat(getStateTaxRate()));
  }
}
