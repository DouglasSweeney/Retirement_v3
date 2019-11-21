package application.view.inputs;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.toedter.calendar.JDateChooser;

import application.main.ExecuteObject;
import application.main.XmlReader;
import application.main.XmlWriter;
import application.system.ApplicationLogger;

/**
 * Creates the GUI tab for the Personal inputs.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class Personal extends InputObject implements PropertyChangeListener {
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
   * Set the string for logging.
   * 
   * @var String XMLREADER_EQUALS
   */
  private static final String XMLREADER_EQUALS = "    xmlReader=<";

  /**
   * Set the current year property to its value.
   * 
   * @var String CURRENT_YEAR_PROPERTY
   */
  public static final String CURRENT_YEAR_PROPERTY = "personalCurrentYear";
  
  /**
   * Set the birth year property to its value.
   * 
   * @var String BIRTH_DAY_PROPERTY
   */
  public static final String BIRTH_DAY_PROPERTY = "personalBirthDay";
  
  /**
   * Set the birth year property to its value.
   * 
   * @var String BIRTH_MONTH_PROPERTY
   */
  public static final String BIRTH_MONTH_PROPERTY = "personalBirthMonth";
   
  /**
   * Set the birth year property to its value.
   * 
   * @var String BIRTH_YEAR_PROPERTY
   */
  public static final String BIRTH_YEAR_PROPERTY = "personalBirthYear";
  
  /**
   * Set the retirement age property to its value.
   * 
   * @var String RETIREMENT_AGE_PROPERTY
   */
  public static final String RETIREMENT_AGE_PROPERTY = "personalRetirementAge";
  
  /**
   * Set the death age property to its value.
   * 
   * @var String DEATH_AGE_PROPERTY
   */
  public static final String DEATH_AGE_PROPERTY = "personalDeathAge";
  
  /**
   * Set the inflation property to its value.
   * 
   * @var String INFLATION_PROPERTY
   */
  public static final String INFLATION_PROPERTY = "personalInflation";

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
  private static final String NAME = Header.PERSONAL.toString();;
  
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
   * Get an instance of the Property.
   * 
   * @var Property props
   */
  private static JDateChooser simulationDatePicker;
       
  /**
   * Get an instance of the Property.
   * 
   * @var Property props
   */
  private static JDateChooser dobDatePicker;
       
  /**
   * Set the input for the retirement age.
   * 
   * @var ComboBoxList retirementAge
   */
  private static ComboBoxList retirementAge;
  
  /**
   * Set the input for the death age.
   * 
   * @var JFormattedTextField deathAge
   */
  private static ComboBoxList deathAge;
  
  /**
   * Set the input for the inflation.
   * 
   * @var JFormattedTextField inflation
   */
  private static ComboBoxList inflation;


  /**
   * Set the input field VERIFIER.
   * 
   * @var Verifier VERIFIER
   */
  private static final Verifier VERIFIER = new Verifier();

  /** 
   * No properties are passed in(as normal). This allows
   * the model to get access to the inputs.
   * 
   * @since  1.0
   */
  public Personal() {
    super();

    LOGGER.trace("Called the Personal constructor with no parameters.");

    LOGGER.trace("Leaving the Personal constructor.");
  }

  /** 
   * Get the properties for the Personal account.
   * 
   * @param  props     The properties for the application
   * @since  1.0
   */
  public Personal(final XmlReader xmlReader) {
    super();

    LOGGER.trace("Called the Personal constructor.");
    LOGGER.trace(XMLREADER_EQUALS + xmlReader + ">.");

    this.xmlReader = xmlReader;

    LOGGER.trace("Leaving the Personal constructor.");
  }

  /** 
   * Get the input tab panel for the tab.
   * 
   * @since  1.0
   * 
   * @return JPanel
   */
  public JPanel createPanel() {
    final Date date = new Date();
    LOGGER.trace("Entering the Personal createPanel() with no parameters.");
    
    final JPanel jPanel = new JPanel(new GridLayout(6, 2));

    JLabel label = new JLabel("Simulation Date:");
    jPanel.add(label);
    simulationDatePicker = new JDateChooser();
    simulationDatePicker.setDate(date); // set todays date
    simulationDatePicker.addPropertyChangeListener(this);
    jPanel.add(simulationDatePicker);

    label = new JLabel("Birth Date:");
    jPanel.add(label);
    dobDatePicker = new JDateChooser();
    final Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.YEAR, xmlReader.getIntegerProperty("personalBirthYear"));
    calendar.set(Calendar.MONTH, xmlReader.getIntegerProperty("personalBirthMonth") - 1); // 0 based
    calendar.set(Calendar.DAY_OF_MONTH, xmlReader.getIntegerProperty("personalBirthDay"));

    dobDatePicker.setCalendar(calendar);
    dobDatePicker.addPropertyChangeListener(this);
    jPanel.add(dobDatePicker);

    label = new JLabel("Retirement Age:");
    jPanel.add(label);
    retirementAge = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_95, 
        UTILS.getWholeIntegerFormat(xmlReader.getIntegerProperty(RETIREMENT_AGE_PROPERTY)));
    jPanel.add(retirementAge.getComboBox());

    label = new JLabel("Life Expectancy Age:");
    jPanel.add(label);
    deathAge = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_95,
        UTILS.getIntegerFormat(xmlReader.getIntegerProperty(DEATH_AGE_PROPERTY)));
    jPanel.add(deathAge.getComboBox());

    label = new JLabel("Inflation:");
    jPanel.add(label);
    inflation = new ComboBoxList(ComboBoxItems.ITEMS.ITEMS_1_TO_30_BY_ZERO_POINT_FIVE,
        UTILS.getPercentFormat(xmlReader.getDoubleProperty(INFLATION_PROPERTY)));
    inflation.getComboBox().setToolTipText("The typical value is 2.8%");
    jPanel.add(inflation.getComboBox());

    LOGGER.trace("Leaving the Personal createPanel().");
    LOGGER.trace("    returning jPanel=<" + jPanel + ">.");

    return jPanel;
  }

  /** 
   * Get the name of the tab.
   * 
   * @since  1.0
   */
  public String getName() {
    LOGGER.trace("Entering the Personal getName() with no parameters.");

    LOGGER.trace("Leaving the Personal getName().");
    LOGGER.trace(RETURNING + NAME + ">");

    return NAME;
  }

  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return int
   */
  public int getCurrentYear() {
    LOGGER.trace("Entering the Personal getCurrentYear() with no parameters.");

    Date date = new Date();
    date = simulationDatePicker.getDate();
    final Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    LOGGER.trace("Leaving the Personal getCurrentYear().");
    LOGGER.trace("     returning <" + cal.get(Calendar.YEAR) + ">");
    
    return cal.get(Calendar.YEAR);
  }

  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return int
   */
  public int getCurrentMonth() {
    LOGGER.trace("Entering the Personal getCurrentMonth() with no parameters.");

    Date date = new Date();
    date = simulationDatePicker.getDate();
    final Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    
    LOGGER.trace("Leaving the Personal getCurrenMonth().");
    LOGGER.trace("     returning <" + (cal.get(Calendar.MONTH) + 1) + ">");
    
    return cal.get(Calendar.MONTH) + 1;
  }

  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return int
   */
  public int getCurrentDay() {
    LOGGER.trace("Entering the Personal getCurrentDay() with no parameters.");

    Date date = new Date();
    date = simulationDatePicker.getDate();
    final Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    
    LOGGER.trace("Leaving the Personal getCurrentDay().");
    LOGGER.trace("     returning <" + cal.get(Calendar.DAY_OF_MONTH) + ">");

    return cal.get(Calendar.DAY_OF_MONTH);
  }

  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return int
   */
  public int getBirthYear() {
    LOGGER.trace("Entering the Personal getBirthYear() with no parameters.");

    final Date date = dobDatePicker.getDate();
    final Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    
    LOGGER.trace("Leaving the Personal getBirthYear().");
    LOGGER.trace("     returning <" + cal.get(Calendar.YEAR) + ">");
    
    return cal.get(Calendar.YEAR);
  }

  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return int
   */
  public int getBirthMonth() {
    LOGGER.trace("Entering the Personal getBirthMonth() with no parameters.");

    final Date date = dobDatePicker.getDate();
    final Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    
    LOGGER.trace("Leaving the Personal getBirthMonth().");
    LOGGER.trace("     returning <" + (cal.get(Calendar.MONTH) + 1) + ">");
    
    return cal.get(Calendar.MONTH) + 1;
  }

  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return int
   */
  public int getBirthDay() {
    LOGGER.trace("Entering the Personal getBirthDay() with no parameters.");

    final Date date = dobDatePicker.getDate();
    final Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    LOGGER.trace("Leaving the Personal getBirthDay().");
    LOGGER.trace("     returning <" + cal.get(Calendar.DAY_OF_MONTH) + ">");
    return cal.get(Calendar.DAY_OF_MONTH);
  }

  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return int
   */
  public int getRetirementAge() {
    LOGGER.trace("Entering the Personal getRetirementAge() with no parameters.");

    String inputString = retirementAge.getValue();
    Integer value = -987; // -987.0 for invalid field

    // valid input field
    inputString = inputString.replaceAll(",", "");
    if (VERIFIER.checkField(inputString, UTILS.getIntegerName())) {
      try {
        value = Integer.valueOf(inputString);
      } catch (NumberFormatException e) {
        UTILS.invalidNumber("Invalid Personal Retirement Age(" + inputString + ").");
      }
    }

    LOGGER.trace("Leaving the Personal getRetirementAge().");
    LOGGER.trace("     returning <" + value + ">");

    return value.intValue();
  }

  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return int
   */
  public int getDeathAge() {
    LOGGER.trace("Entering the Personal getDeathAge() with no parameters.");

    String inputString = deathAge.getValue();
    Integer value = -987; // -987.0 for invalid field

    // valid input field
    inputString = inputString.replaceAll(",", "");
    if (VERIFIER.checkField(inputString, UTILS.getIntegerName())) {
      try {
        value = Integer.valueOf(inputString);
      } catch (NumberFormatException e) {
        UTILS.invalidNumber("Invalid Personal Death Age(" + inputString + ").");
      }
    }

    LOGGER.trace("Leaving the Personal getDeathAge().");
    LOGGER.trace("     returning <" + value + ">");

    return value.intValue();
  }

  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return double
   */
  public double getInflationRate() {
    LOGGER.trace("Entering the Brokerage getGrowthRate() with no parameters.");

    final String inputString = inflation.getValue();
    final String inputString2 = inputString.replaceAll("%", "");
    final String inputString3 = inputString2.replaceAll(",", "");
    double value = -987.0; // -987.0 for invalid field
 
    // valid input field
    if (VERIFIER.checkField(inputString3, UTILS.getPercentName())) {
      try {
        value = new Double(inputString3);
      } catch (NumberFormatException e) {
        UTILS.invalidNumber("Invalid Personal Inflation Rate(" + inputString + ").");
      }
      value /= 100;
    }

    LOGGER.trace("Leaving the Personal getInflationRate().");
    LOGGER.trace("     returning <" + value + ">");

    return value;
  }

  /** 
   * Get the user input.
   * 
   * @since  1.0
   * 
   * @return int
   */
  public int getCurrentAge() {
    final int simulationYear = getCurrentYear();
    final int birthYear = getBirthYear();
    int value = simulationYear - birthYear - 1;

    final int simulationMonth = getCurrentMonth();
    final int birthMonth = getBirthMonth();
    final int simulationDay = getCurrentDay();
    final int birthDay = getBirthDay();
    
    if (simulationMonth == birthMonth && simulationDay >= birthDay) {
      value++;
    } else {
      if (simulationMonth > birthMonth) {
        value++;
      }
    }
    
    return value;
  }

  /** 
   * Save the properties of this object.
   * 
   * @since  1.0
   * 
   */
  public void saveProperties(final XmlWriter xmlWriter) {
    LOGGER.trace("Entering the Personal saveProperties() with no parameters.");
    
    xmlWriter.putNode("Personal");
    
    xmlWriter.putIntegerAttribute(CURRENT_YEAR_PROPERTY, getCurrentYear());
    
    xmlWriter.putIntegerAttribute(BIRTH_DAY_PROPERTY, getBirthDay());
    xmlWriter.putIntegerAttribute(BIRTH_MONTH_PROPERTY, getBirthMonth());
    xmlWriter.putIntegerAttribute(BIRTH_YEAR_PROPERTY, getBirthYear());
    
    xmlWriter.putIntegerAttribute(RETIREMENT_AGE_PROPERTY, getRetirementAge());
    xmlWriter.putIntegerAttribute(DEATH_AGE_PROPERTY, getDeathAge());
    xmlWriter.putDoubleAttribute(INFLATION_PROPERTY, getInflationRate());

    xmlWriter.putClosingNode("Personal");
      
    LOGGER.trace("Leaving the Personal saveProperties().");
 }
  
  public void writePdf(final String name, final PDDocument doc) {
	  writePdf(doc, Entry.SIMULATION_DATE.toString(), simulationDatePicker.getDate().toString(),
		            Entry.BIRTH_DATE.toString(), dobDatePicker.getDate().toString(),
	                Entry.RETIREMENT_AGE.toString(), UTILS.getWholeIntegerFormat(getRetirementAge()),
                    Entry.LIFE_EXPECTANCY_AGE.toString(), UTILS.getWholeIntegerFormat(getDeathAge()),
                    Entry.INFLATION.toString(), UTILS.getPercentFormat(getInflationRate()));
  }

  @Override
  public void propertyChange(final PropertyChangeEvent arg0) {
      final ExecuteObject execute = new ExecuteObject();
      execute.runSimulation(Focus.getInputsTabbedPane(), Focus.getResultsTabbedPane());    
  }
}
