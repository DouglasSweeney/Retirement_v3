package test.system.view.outputs;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import application.view.outputs.Utils;

public class UtilsTest {

  /** 
   * Declare a variable.
   * 
   * @since  1.0
   */
  private transient Utils utils;
  
  /** 
   * Do this method before each test.
   * 
   * @since  1.0
   */
  @Before
  public void setUp() throws InterruptedException {
    utils = new Utils();
  }

  /** 
   * Ensure the input integer is formatted as an integer.
   * 
   * @since  1.0
   */
  @Test
  public void ensureThatAnIntegerIsFormattedAsInteger() {
    final String string = utils.getIntegerFormat(1_111);
    
    assertEquals("", "1,111", string);
  }
  
  /** 
   * Ensure the input integer is formatted as an integer.
   * 
   * @since  1.0
   */
  @Test
  public void ensureThatAnIntegerIsFormattedAsInteger2() {
    final String string = utils.getIntegerFormat(1_111_111);
    
    assertEquals("", "1,111,111", string);
  }
  
  /** 
   * Ensure the input integer is formatted as an money.
   * 
   * @since  1.0
   */
  @Test
  public void ensureThatADoubleIsFormattedAsMoney() {
    final String string = utils.getDollarFormat(1111);
    
    assertEquals("", "1,111.00", string);
  }
  
  /** 
   * Ensure the input integer is formatted as an money.
   * 
   * @since  1.0
   */
  @Test
  public void ensureThatADoubleIsFormattedAsMoney2() {
    final String string = utils.getDollarFormat(1_111_111);
    
    assertEquals("", "1,111,111.00", string);
  }
  /** 
   * Ensure the input integer is formatted as percent.
   * 
   * @since  1.0
   */
  @Test
  public void ensureThatADoubleIsFormattedAsPercent() {
    final String string = utils.getPercentFormat(0.03);
    
    assertEquals("", string, "3%");
  }
  
  /** 
   * Ensure the input integer is formatted as an money.
   * 
   * @since  1.0
   */
  @Test
  public void ensureThatADoubleIsFormattedAsPercent2() {
    final String string = utils.getPercentFormat(0.99);
    
    assertEquals("", string, "99%");
  }
}
