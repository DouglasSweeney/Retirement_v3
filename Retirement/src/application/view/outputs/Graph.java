package application.view.outputs;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JComponent;

import application.system.ApplicationLogger;
import application.system.ResultsDataNode;

/**
* Creates the GUI tab for the Graph.
* 
* @author Doug Sweeney
* @version     1.0
* @since       1.0
*/

public class Graph extends JComponent {
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
   * Set the X value of the label.
   * 
   * @var int startX
   */
  private transient int startX;
  
  /**
   * Set the Y value of the label.
   * 
   * @var int startY
   */
  private transient int startY;
  
  /**
   * Set the width value of the label.
   * 
   * @var int width
   */
  private transient int width;
  
  /**
   * Set the height value of the label.
   * 
   * @var int height
   */
  private transient int height;

  /**
   * Remember the color for the labels.
   * 
   * @var ArrayList<LabelColorNode> labelColors
   */
  private transient final ArrayList<LabelColorNode> labelColors = new ArrayList<>();
  
  /**
   * Remember the maximum height for the bar.
   * 
   * @var ArrayList<MaxHeightNode> maxHeights
   */
  private transient final ArrayList<MaxHeightNode> maxHeights = new ArrayList<>();
  
  /**
   * Used to display the name & color.
   * 
   * @author      Doug Sweeney
   * @version     1.0
   * @since       1.0
   */
  private class LabelColorNode {
    
    /**
     * Create a name for this label.
     * 
     * @var String name
     */
    private final String name;
    
    /**
     * Create a color for this label.
     * 
     * @var Color color
     */
    private final Color  color;
        
    private LabelColorNode(final String tabName, final Color tabColor) {
      name = tabName;
      color = tabColor;
    }
    
    public String getName() {
      return name;
    }
    
    public Color getColor() {
      return color;
    }
  }
   
  /**
   * Used to remember the maximum number (the total or expense) for each age.
   * 
   * @author      Doug Sweeney
   * @version     1.0
   * @since       1.0
   */
  private class MaxHeightNode {
    /**
     * Create a max height for this age.
     * 
     * @var int age
     */
    private final int    age;
    
    /**
     * Remember the max height for this age.
     * 
     * @var double numberValue
     */
    private final double numberValue;
        
    private MaxHeightNode(final int age, final double numberValue) {
      this.age = age;
      this.numberValue = numberValue;
    }
    
    public int getAge() {
      return age;
    }
    
    public double getNumberValue() {
      return numberValue;
    }
    
  }
          
  /** 
   * The constructor for this class.
   * 
   * @since  1.0
   */
  public Graph() {
    super();
      
    LOGGER.trace("Called the Graph constructor.");
      
    LOGGER.trace("Leaving the Graph constructor.");
  }
    
  /** 
   * The system paint method calls the "extends" class.
   * 
   * @since  1.0
   */
//  @Override
//  public void paint(final Graphics graphics) {
//    super.paint(graphics);    
//  }
    
  /** 
   * Remember the label and its associated color.
   * 
   * @param tabName  The name of the tab
   * @param tabColor The color of the tab
   * @since  1.0
   * 
   */
  protected void setLabelColor(final String tabName, final Color tabColor) {
    boolean found = false;
    
    for (final LabelColorNode node : labelColors) {
      if (node.getName().equals(tabName)) {
        found = true;
      }
    }
    
    LabelColorNode node;
    if (!found) {
      node = new LabelColorNode(tabName, tabColor);
    
      labelColors.add(node);
    }
  }
  
  /** 
   * Remember the maximum height of a age bar. 
   * 
   * @param age    The current age of the graph
   * @param number The maximum number of the age.
   * 
   * @since  1.0
   * 
   */
  protected void setPixelHeight(final int age, final double number) {
    MaxHeightNode node = null;
    
    node = new MaxHeightNode(age, number);
    
    maxHeights.add(node);     
  }
  
  /** 
   * Determine the width of a string.
   * 
   * @param g       Current graphics context.
   * @param name    The string to get the width of.
   * 
   * @return The string width in pixels
   * 
   * @since  1.0
   */
  protected int getStringWidth(final Graphics g, final String name) {
    int width;
    FontMetrics fontMetrics;
      
    fontMetrics = g.getFontMetrics();
    width = fontMetrics.stringWidth(name);
      
    return width;
  }
  
  /** 
   * Draw the graph key. Display the label and its associated color.
   * 
   * @param g    The current context to draw the labels     
   * 
   * @since  1.0
   * 
   */
  protected void drawLabelColors(final Graphics graphics) {
    int x;
    int y;
    final int padding = 4;
    
    x = startX;
    y = startY - 4;
    for (final LabelColorNode node : labelColors) {
      drawString(graphics, node.getColor(), node.getName(), x, y);
      
      x += getStringWidth(graphics, node.getName()) + padding;
    }
  }
  
  /** 
   * Display tick marks on the Y Axis (Dollar amounts).
   * 
   * @param   g    The current context to draw the labels.
   * @param   x    The X location to draw the tick marks.
   * @param   y    The Y location to draw the tick marks. 
   * @param length The pixel length of a tick mark.
   * 
   * @since  1.0
   * 
   */
  protected void drawTickMark(final Graphics graphics, final int x, final int y, final int length) {
    drawLine(graphics, Color.BLACK, x, y, x + length - 3, y);
  }
  
  /** 
   * Add commas to an integer dollar value.
   * 
   * @param intNum    The integer to add the commas to.
   *
   * @return The number with commas added 
   */
  protected String addCommas(final Integer intNum) {
    return UTILS.getDollarFormat(intNum);
  }

  
  /** 
   * Add commas and display the result.
   * 
   * @param g       The current Graphics context.
   * @param intNumber The integer (dollar) value to add commas.
   * @param x         The X location to draw the result of adding commas.
   * @param y         The Y location to draw the result of adding commas.
   */
  protected void positionNumberAndAddCommas(final Graphics graphics, 
                                            final Integer intNumber, 
                                            final int x, final int y) {
    final String str = addCommas(intNumber);
    int   strWidth;
    final int   padding = 5;
    
    if (str.length() > 0) {
      strWidth = getStringWidth(graphics, str);
      drawString(graphics, Color.BLACK, str, x - strWidth - padding, y);
    }
  }

  /** 
   * Returns the maximum value determined by the age.
   * 
   * @param age Returns value associated with the age.
   *
   * @return double
   *  
   * @since  1.0
   */
  protected double getAgeValue(final int age) {
    double returnValue;
    
    returnValue = 0;
    for (final MaxHeightNode node : maxHeights) {
       if (node.getAge() == age) {
          returnValue =  node.getNumberValue();
       }
    }
    
    return returnValue;
    
  }
    
  /** 
   * Looks thru all the bars on the graph to find the largest value.
   * 
   * @param ages    A list of ages (for each bar).
   *
   * @return The largest value
   *  
   * @since  1.0
   */
  protected double findLargestNumber(final ArrayList<Integer> ages) {
    double  largestNumber = 0;
    
    for (final Integer number : ages) {
      if (number > largestNumber) {
        largestNumber = number;
      }
    }
    
    return largestNumber;
  }

  /** 
   * Set some class parameters (location).
   * 
   * @param start    The X and Y location that the jPanel starts
   * @param size     The width and height of the jPanel
   *  
   * @since  1.0
   */
  protected void computeScreenCoordinates(final  Point start, final Dimension size) {
    // LocationOnScreen()
    startX = start.x + 100;
    startY = start.y + 60;
      
    // getSize();
    width = size.width + 100;
    height = size.height + 60;    
  }
  
  /** 
   * Draw the horizontal and vertical axes and the ages.
   * 
   * @param g        The current graphics context.
   * @param ages     A list of ages to be displayed
   *
   * @return integer
   *  
   * @since  1.0
   */
  protected int drawAxes(final Graphics graphics, final ArrayList<Integer> ages) {
    /* Draw the horizontal axis */
    drawLine(graphics, Color.BLACK, startX, startY, startX, height - startY);
      
    /* Draw the vertical axis */
    drawLine(graphics, Color.BLACK, startX, height - startY, 
             width - startX + 82, height - startY);  
        
    /* Draw the ages along the x-Axis (Remember: size() / 2 displayed */
    final int XAXIS_LABEL_SPACING = (width - startX) / ages.size();
    for (int i = 0; i < ages.size(); i++) {
      // Display every other age
      if (i % 2 == 0) {
        drawString(graphics, Color.BLACK, ages.get(i).toString(), 
                   startX + (i * XAXIS_LABEL_SPACING), height - startY + 20);
      }
    } 
        
    return XAXIS_LABEL_SPACING;
  }

  /** 
   * Determine the value of the bar.
   * 
   * @param object          A list of nodes/values.
   * @param withdrawal      The type of value to get.
   * @param i               The index  into the object list
   * @param objectMaxHeight The maximum value found so far
   *
   * @return The height of the bar in dollars
   */
  protected double determineHeightOfBar(final ArrayList<ResultsDataNode> object, 
                                        final boolean withdrawal, 
                                        final int i, double objectMaxHeight) {
    double number;
    
    if (withdrawal) {
      number = object.get(i).getEndingValue();
    } else {
      number = object.get(i).getWithdrawal();
    }
    
    if (number > objectMaxHeight) {
      objectMaxHeight = number;
    }
    
    return objectMaxHeight;
  }
  
  /** 
   * Displays a rectangle.
   * 
   * @param g               The current graphics context.
   * @param x               The X location for the upper left of the rectangle
   * @param y               The Y location for the upper left of the rectangle
   * @param width           The width of the rectangle
   * @param height          The height of the rectangle
   * @param color           The color of the rectangle
   *
   */
  protected void drawBar(final Graphics graphics, final int x, final int y, 
                         final int width, final int height, final Color color) {
    graphics.setColor(color);
    graphics.fillRect(x, y, width, height);
  }

  /** 
   * Displays a line.
   * 
   * @param g               The current graphics context.
   * @param color           The color of the line
   * @param startX          The X location for the start of the line
   * @param startY          The Y location for the start of the line
   * @param endX            The X location for the start of the line
   * @param endY            The Y location for the start of the line
   * 
   */
  protected void drawLine(final Graphics graphics, final Color color, 
                          final int startX, final int startY, 
                          final int endX, final int endY) {
    graphics.setColor(color);
    graphics.drawLine(startX, startY, endX, endY);   
  }

  /** 
   * Displays a text string.
   * 
   * @param g          The current graphics context.
   * @param color      The color of the line
   * @param string     The text string to display
   * @param x          The X location for the start of the text
   * @param y          The Y location for the start of the text
   */
  protected void drawString(final Graphics graphics, final Color color, 
                            final String string, final int x, final int y) {
    final Font font = new Font("Serif", Font.PLAIN, 10);
    graphics.setFont(font);
    
    graphics.setColor(color);
    graphics.drawString(string, x, y);
  }
}