import org.jfree.chart.ChartFactory;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;  
import org.jfree.data.xy.XYSeriesCollection;  
import org.jfree.chart.ChartUtilities;  
  
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;  
import java.io.FileOutputStream;  
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
  
class CapacityChart{
	public XYSeriesCollection dataset = new XYSeriesCollection(); 
	public XYSeries xySeries = new XYSeries("inventory");
	
	public XYSeriesCollection dataset2 = new XYSeriesCollection(); 
	public XYSeries xySeries2 = new XYSeries("revenues");
	
	
	
	public int no_weeks=16;
	public JFreeChart thisChart;
	
	
  
public JFreeChart buildChart() {  
	for(int i=0; i<no_weeks; i++){
		xySeries.add( i+1, null );
	}

	for(int i=0; i<no_weeks; i++){
		xySeries2.add( i+1, null );
	}

   
   dataset.addSeries( xySeries );
   dataset2.addSeries( xySeries2 );      
  
   thisChart = ChartFactory.createXYLineChart( "Inventory and Revenue Over Time",  
                                                      "Week",  
                                                      "Units Unsold",  
                                                      dataset,  
                                                      PlotOrientation.VERTICAL,  
                                                      false,  
                                                      false,  
                                                      false ); 
   thisChart.setBackgroundPaint(new Color(245, 245, 245));
   thisChart.setTitle(new TextTitle("Inventory and Revenue Over Time", new java.awt.Font("SansSerif", Font.PLAIN, 14)));

   thisChart.getXYPlot().getRangeAxis().setRange(0,2000);
   thisChart.getXYPlot().getRangeAxis().setLabelFont(new java.awt.Font("SansSerif", Font.PLAIN, 14));
   thisChart.getXYPlot().getDomainAxis().setLabelFont(new java.awt.Font("SansSerif", Font.PLAIN, 14));
   thisChart.getXYPlot().getRenderer().setSeriesStroke(0, new BasicStroke(3.0f));
   thisChart.getXYPlot().getRenderer().setSeriesPaint(0,new Color(255, 140, 0));
   
   
   //Create Revenue Plot
   final XYPlot plot = thisChart.getXYPlot();
   final NumberAxis axis2 = new NumberAxis("Revenue ($ 1000)");
   axis2.setRange(0, 200);
   axis2.setTickUnit(new NumberTickUnit(25));
   axis2.setLabelFont(new java.awt.Font("SansSerif", Font.PLAIN, 14));
   axis2.setAutoRangeIncludesZero(false);
   plot.setRangeAxis(1, axis2);
   plot.setDataset(1, dataset2);
   plot.mapDatasetToRangeAxis(1, 1);

   final StandardXYItemRenderer renderer2 = new StandardXYItemRenderer();
   renderer2.setSeriesPaint(0, new Color(0, 0, 128));
   renderer2.setSeriesStroke(0, new BasicStroke(3.0f));
   plot.setRenderer(1, renderer2);
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   thisChart.setBorderVisible(false);
   return thisChart;
  
}  



public JFreeChart update(int[] inventory, int[] revenue){
	xySeries.clear();
	xySeries2.clear();
	
	int no_points = inventory.length;	
	
	for(int i=0; i<no_weeks; i++){
		if(i<no_points){
			xySeries.add( i+1, inventory[i] );	
		}
		else{
			xySeries.add( i+1, null );
		}		
	}

	int no_points2 = revenue.length;	
	
	for(int i=0; i<no_weeks; i++){
		if(i<no_points){
			xySeries2.add( i+1, revenue[i] );	
		}
		else{
			xySeries2.add( i+1, null );
		}		
	}
	
	
	
	return thisChart;
}






}  