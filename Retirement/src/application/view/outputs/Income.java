package application.view.outputs;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
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
 * Creates the GUI tab for the income.
 * 
 * @author Doug Sweeney
 * @version     1.0
 * @since       1.0
 */
public class Income extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Create a variable for the chart.
   * 
   * @var JFreeChart chart
   */
  protected transient JFreeChart chart;

  /**
   * Create a variable for the colors.
   * 
   * @var LegendItemCollection colorPalette
   */
  protected LegendItemCollection colorPalette = new LegendItemCollection();
  
  /** 
   * Get the name of the tab.
   * 
   * @since  1.0
   * 
   */
  public String getName() {
    return Header.INCOME_GRAPH.toString();
  }
    
  /** 
   * Get the panel of the tab.
   * 
   * @since  1.0
   * 
   */
  public JPanel createPanel() {
    return this;
  }
    
  /** 
   * Write the data to the textArea in the tab.
   * 
   * @param  ages         The data to write labels
   * @parms  expenses     The data to write a graph 1
   * @since  1.0
   * 
   */
  public void draw(final ArrayList<Integer> ages, final Expenses expenses,
      final Pension pension, final SocialSecurity socialSecurity, final Account401K account401k, 
      final Account403B account403b, final AccountCashBalance accountCashBalance, 
      final AccountRoth accountRoth, final AccountIra accountIra, final Salary salary,
      final Brokerage brokerage, final Savings savings) {
    
    // Remove the previous chart
    this.removeAll();
    this.revalidate(); 
        
    final CategoryDataset dataset = createDataset(ages, expenses, pension, socialSecurity, 
                                                  account401k, account403b, accountCashBalance, 
                                                  accountRoth, accountIra, salary, brokerage,
                                                  savings);
    chart = createChart(dataset, ages, expenses);
    OutputsLab.setIncomeChart(chart);
    final ChartPanel chartPanel = new ChartPanel(chart);
        
    add(chartPanel);
  }

  /** 
   * Creates a sample dataset.
   * 
   * @return A sample dataset.
   */
  private CategoryDataset createDataset(final ArrayList<Integer> ages, final Expenses expenses,
      final Pension pension, final SocialSecurity socialSecurity, final Account401K account401k, 
      final Account403B account403b, final AccountCashBalance accountCashBalance, 
      final AccountRoth accountRoth, final AccountIra accountIra, final Salary salary,
      final Brokerage brokerage, final Savings savings) {
    
    final DefaultCategoryDataset result = new DefaultCategoryDataset();
    ResultsDataNode node;
        
    for (int i = 0; i < ages.size(); i++) {
      node = pension.getList().get(i);
      if (node.getEndingValue() > 0.0) {
        result.addValue(node.getEndingValue(), "Pension", ages.get(i));
      }
          
      node = socialSecurity.getList().get(i);
      if (node.getEndingValue() > 0.0) {
        result.addValue(node.getEndingValue(), "Social Security", ages.get(i));
      }
          
      node = account401k.getList().get(i);
      if (node.getWithdrawal() > 0.0) {
        result.addValue(node.getWithdrawal(), "401K", ages.get(i));
      }
          
      node = account403b.getList().get(i);
      if (node.getWithdrawal() > 0.0) {
        result.addValue(node.getWithdrawal(), "403B", ages.get(i));
      }
          
      node = accountCashBalance.getList().get(i);
      if (node.getWithdrawal() > 0.0) {
        result.addValue(node.getWithdrawal(), "Cash Balance", ages.get(i));
      }
         
      node = accountRoth.getList().get(i);
      if (node.getWithdrawal() > 0.0) {
        result.addValue(node.getWithdrawal(), "Roth", ages.get(i));
      }
          
      node = accountIra.getList().get(i);
      if (node.getWithdrawal() > 0.0) {
        result.addValue(node.getWithdrawal(), "IRA", ages.get(i));
      }
          
      node = salary.getList().get(i);
      if (node.getEndingValue() > 0.0) {
        result.addValue(node.getEndingValue(), "Salary", ages.get(i));
      }
        
      node = brokerage.getList().get(i);
      if (node.getWithdrawal() > 0.0) {
        result.addValue(node.getWithdrawal(), "Brokerage", ages.get(i));
      }
    }
        
    return result;
  }
    
  /**
   * Creates chart.
   * 
   * @param dataset  the dataset for the chart.
   * 
   * @return A chart.
   */
  private JFreeChart createChart(final CategoryDataset dataset, final ArrayList<Integer> ages, 
      final Expenses expenses) {

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

    categoryplot.setDataset(1, defaultcategorydataset1);

    categoryplot.setRenderer(1, lineandshaperenderer);

    final DefaultCategoryDataset defaultcategorydataset2 = new DefaultCategoryDataset();

    for (int i = 0; i < ages.size(); i++) {
      defaultcategorydataset2.addValue(expenses.getList().get(i).getBeginningValue(), 
                                       "Expenses", ages.get(i));
    }
        
    categoryplot.setDataset(2, defaultcategorydataset2);

    final LineAndShapeRenderer lineandshaperenderer1 = new LineAndShapeRenderer();

    categoryplot.setRenderer(2, lineandshaperenderer1);

    categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

    categoryplot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);       
        
    // Display odd labels - to many looks crowded
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
        
    return new JFreeChart(categoryplot); 
  }
}