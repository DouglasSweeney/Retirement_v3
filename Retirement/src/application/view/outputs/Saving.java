package application.view.outputs;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import application.main.Main;
import application.main.OutputsLab;
import application.system.ResultsDataNode;

/**
 * Creates the GUI tab for the savings.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */

public class Saving extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Create a constant for ZERO_DOUBLE.
   * 
   * @var ZERO_DOUBLE
   */
  private static final Double ZERO_DOUBLE = 0.0;
  
  /**
   * Create a name for this tabbed panel.
   * 
   * @var String NAME
   */
  private static final String NAME = Header.SAVINGS_GRAPH.toString();
    
  /** 
   * Write the data to the textArea in the tab.
   * 
   * @param  ages         The data to write labels
   * @parms  Expenses     The data to write a graph
   * @since  1.0
   * 
   */
  public void draw(final ArrayList<Integer> ages, final Account401K account401k, 
                   final Account403B account403b, final AccountCashBalance accountCashBalance, 
                   final AccountRoth accountRoth, final AccountIra accountIra, 
                   final Brokerage brokerage, final Savings savings) {
    
    // Remove the previous chart
    this.removeAll();
    this.revalidate(); 
    
    final CategoryDataset dataset = createDataset(ages, account401k, account403b,
        accountCashBalance, accountRoth, accountIra, 
        brokerage, savings);
    final JFreeChart chart = createChart(dataset, ages);
    OutputsLab.setSavingChart(chart);
    final ChartPanel chartPanel = new ChartPanel(chart);
        
    add(chartPanel);
  }
  
  /**
   * Creates a jPanel.
   * 
   * @return JPanel this
   */
  public JPanel createPanel() {
    return this;
  }
    
  /**
   * Creates a sample dataset.
   * 
   * @return A sample dataset.
   */
  private CategoryDataset createDataset(final ArrayList<Integer> ages, 
                                        final Account401K account401k, 
                                        final Account403B account403b,
                                        final AccountCashBalance accountCashBalance, 
                                        final AccountRoth accountRoth,
                                        final AccountIra accountIra, 
                                        final Brokerage brokerage,
                                        final Savings savings) {
    final DefaultCategoryDataset result = new DefaultCategoryDataset();

    ResultsDataNode node;
        
    for (int i = 0; i < ages.size(); i++) {
      node = account401k.getList().get(i);
      if (node.getEndingValue() > ZERO_DOUBLE) {
        result.addValue(node.getEndingValue(), "401K", ages.get(i));
      }
          
      node = account403b.getList().get(i);
      if (node.getEndingValue() > ZERO_DOUBLE) {
        result.addValue(node.getEndingValue(), "403B", ages.get(i));
      }
          
      node = accountCashBalance.getList().get(i);
      if (node.getEndingValue() > ZERO_DOUBLE) {
        result.addValue(node.getEndingValue(), "Cash Balance", ages.get(i));
      }
          
      node = accountRoth.getList().get(i);
      if (node.getEndingValue() > ZERO_DOUBLE) {
        result.addValue(node.getEndingValue(), "Roth", ages.get(i));
      }
          
      node = accountIra.getList().get(i);
      if (node.getEndingValue() > ZERO_DOUBLE) {
        result.addValue(node.getEndingValue(), "IRA", ages.get(i));
      }
          
      node = brokerage.getList().get(i);
      if (node.getEndingValue() > ZERO_DOUBLE) {
        result.addValue(node.getEndingValue(), "Brokerage", ages.get(i));
      }
      
      node = savings.getList().get(i);
      if (node.getEndingValue() > ZERO_DOUBLE) {
        result.addValue(node.getEndingValue(), "Savings", ages.get(i));
      }
    }
        
    return result;
  }
    
  /**
   * Creates a sample chart.
   * 
   * @param dataset  the dataset for the chart.
   * 
   * @return A sample chart.
   */
  private JFreeChart createChart(final CategoryDataset dataset, final ArrayList<Integer> ages) {
    
    final StandardCategoryItemLabelGenerator standardcategoryitemlabelgenerator = 
                                       new StandardCategoryItemLabelGenerator();
    final BarRenderer barrenderer = new StackedBarRenderer();

    barrenderer.setBaseItemLabelGenerator(standardcategoryitemlabelgenerator);
    barrenderer.setBaseItemLabelsVisible(true);
    final CategoryPlot categoryplot = new CategoryPlot();
    categoryplot.setDataset(dataset);
    categoryplot.setRenderer(barrenderer);
    categoryplot.setDomainAxis(new CategoryAxis("Age"));     
    categoryplot.setRangeAxis(new NumberAxis(" "));
    categoryplot.setOrientation(PlotOrientation.VERTICAL);
    categoryplot.setRangeGridlinesVisible(true);
    categoryplot.setDomainGridlinesVisible(true);
        
    final DefaultCategoryDataset defaultcategorydataset1 = new DefaultCategoryDataset();
    final LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();

    categoryplot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);       
   
    final CategoryAxis domainAxis = categoryplot.getDomainAxis();
    
    final int size = ages.size() / 10;
    for (int i = 0; i < ages.size(); i++) {
      if (i % size > 0) {
        domainAxis.setTickLabelPaint(ages.get(i), Main.getDefaultColor());
      }
    }

    final NumberFormat currency = NumberFormat.getCurrencyInstance();
    final NumberAxis rangeAxis = (NumberAxis) categoryplot.getRangeAxis();
    rangeAxis.setNumberFormatOverride(currency);
   
    categoryplot.setDataset(1, defaultcategorydataset1);        
    categoryplot.setRenderer(1, lineandshaperenderer);
  
    return new JFreeChart(categoryplot);
  }

  /** 
   * Get the name of the tab.
   * 
   * @since  1.0
   * 
   * @return String
   */
  public String getName() {
    return NAME;
  }
}
