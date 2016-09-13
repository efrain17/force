/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entrenamiento;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entrenamiento.Entrenamiento;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.chart.XYChart;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.demo.TimeSeriesChartDemo1;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
/**
 *
 * @author CarlosEfrain
 */
public class grafica {
   public static TimeSeries s1 = new TimeSeries("Entrenamiento Actual");
   public static  JFreeChart grafica; 
   
   public static  JFreeChart graficaxy;
   public double[] x;
   public double[] y;
   
   public static  XYSeriesCollection datosxy=new XYSeriesCollection();
  
    public static  TimeSeriesCollection datos=new TimeSeriesCollection();
    String titulo; 
    String etiquetax; 
    String etiquetay; 
    public  grafica(){ 
        XYSeries sxy=new XYSeries("pesos");
       datosxy.removeAllSeries();  
       //sxy.add(x[0], y[0]);
        y=new double[Entrenamiento.valor.length];
       y=Entrenamiento.valor;
       int n=y.length;
        for (int i = 0; i < n; i++) {
           sxy.add(i, y[i]); 
          // System.out.print(x[i]+"-"+i+" ");
       } 
         datosxy.addSeries(sxy);
        graficaxy=ChartFactory.createXYLineChart("Grafica de Progreso",  "tiempo", "RM-pesos",datosxy, PlotOrientation.VERTICAL, true, true, true);
        graficaxy.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) graficaxy.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        
        final NumberAxis domainAxis = (NumberAxis)plot.getDomainAxis();
        configurarDomainAxis(domainAxis);
         
        

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }}
    public grafica(double[] peso){
        y=new double[peso.length];
        y=peso;
        
    }
    
    
// configuramos el contenido del gráfico (damos un color a las líneas que sirven de guía)
   
     
    // configuramos el eje X de la gráfica (se muestran números enteros y de uno en uno)
    private void configurarDomainAxis (NumberAxis domainAxis) {
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        domainAxis.setTickUnit(new NumberTickUnit(1));
    }
    // configuramos el eje y de la gráfica (números enteros de dos en dos y rango entre 120 y 135)
    private void configurarRangeAxis (NumberAxis rangeAxis) {
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setTickUnit(new NumberTickUnit(2));
        rangeAxis.setRange(0, 100);
    }
    
    public  void grafica1(){ 
        s1.add(new Month(2, 2001), 181.8);
        //s1.add(new Week(1, 1), null);
        s1.add(new Month(3, 2001), 167.3);
        s1.add(new Month(4, 2001), 153.8);
        s1.add(new Month(5, 2001), 167.6);
        s1.add(new Month(6, 2001), 158.8);
        s1.add(new Month(7, 2001), 148.3);
        s1.add(new Month(8, 2001), 153.9);
        s1.add(new Month(9, 2001), 142.7);
        s1.add(new Month(10, 2001), 123.2);
        s1.add(new Month(11, 2001), 131.8);
        s1.add(new Month(12, 2001), 139.6);
        s1.add(new Month(1, 2002), 142.9);
        s1.add(new Month(2, 2002), 138.7);
        s1.add(new Month(3, 2002), 137.3);
        s1.add(new Month(4, 2002), 143.9);
        s1.add(new Month(5, 2002), 139.8);
        s1.add(new Month(6, 2002), 137.0);
        s1.add(new Month(7, 2002), 132.8);
      
        TimeSeries s2 = new TimeSeries("L&G UK Index Trust");
        s2.add(new Month(2, 2001), 129.6);
        s2.add(new Month(3, 2001), 123.2);
        s2.add(new Month(4, 2001), 117.2);
        s2.add(new Month(5, 2001), 124.1);
        s2.add(new Month(6, 2001), 122.6);
        s2.add(new Month(7, 2001), 119.2);
        s2.add(new Month(8, 2001), 116.5);
        s2.add(new Month(9, 2001), 112.7);
        s2.add(new Month(10, 2001), 101.5);
        s2.add(new Month(11, 2001), 106.1);
        s2.add(new Month(12, 2001), 110.3);
        s2.add(new Month(1, 2002), 111.7);
        s2.add(new Month(2, 2002), 111.0);
        s2.add(new Month(3, 2002), 109.6);
        s2.add(new Month(4, 2002), 113.2);
        s2.add(new Month(5, 2002), 111.6);
        s2.add(new Month(6, 2002), 108.8);
        s2.add(new Month(7, 2002), 101.6);
        
         TimeSeries s3 = new TimeSeries("andres2255");
        s3.add(new Month(2, 2001), 129.6);
      
        s3.add(new Month(10, 2001), 106.1);
        s3.add(new Month(1, 2002), 111.7);
        s3.add(new Month(2, 2002), 111.0);
        datos.addSeries(s1);
        datos.addSeries(s2);
        datos.addSeries(s3);
        
        grafica=ChartFactory.createTimeSeriesChart("titulo", "etiquetas X", "etiquetaY", datos, true, true, false);
        /// 
        grafica.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) grafica.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        
    }
    
    public JPanel obtieneGrafica(){
        return new ChartPanel(graficaxy);}//////////para el panel 
    
    public void llenas1(){//// 2 
        //s1.add(new Week(1, 1), null);
        
       
    }
    
    public void borrars1(){///1
    s1.clear();}
    
    public void graficarEntreno(){///3  
       
    }
 /////////////////////////////
    public void iejmplo(){
       
       
        s1.add(new Month(2, 2001), 181.8);
        //s1.add(new Week(1, 1), null);
        s1.add(new Month(3, 2001), 167.3);
        s1.add(new Month(4, 2001), 153.8);
        s1.add(new Month(5, 2001), 167.6);
        s1.add(new Month(6, 2001), 158.8);
        s1.add(new Month(7, 2001), 148.3);
        s1.add(new Month(8, 2001), 153.9);
        s1.add(new Month(9, 2001), 142.7);
        s1.add(new Month(10, 2001), 123.2);
        s1.add(new Month(11, 2001), 131.8);
        s1.add(new Month(12, 2001), 139.6);
        s1.add(new Month(1, 2002), 142.9);
        s1.add(new Month(2, 2002), 138.7);
        s1.add(new Month(3, 2002), 137.3);
        s1.add(new Month(4, 2002), 143.9);
        s1.add(new Month(5, 2002), 139.8);
        s1.add(new Month(6, 2002), 137.0);
        s1.add(new Month(7, 2002), 132.8);
      
        TimeSeries s2 = new TimeSeries("L&G UK Index Trust");
        s2.add(new Month(2, 2001), 129.6);
        s2.add(new Month(3, 2001), 123.2);
        s2.add(new Month(4, 2001), 117.2);
        s2.add(new Month(5, 2001), 124.1);
        s2.add(new Month(6, 2001), 122.6);
        s2.add(new Month(7, 2001), 119.2);
        s2.add(new Month(8, 2001), 116.5);
        s2.add(new Month(9, 2001), 112.7);
        s2.add(new Month(10, 2001), 101.5);
        s2.add(new Month(11, 2001), 106.1);
        s2.add(new Month(12, 2001), 110.3);
        s2.add(new Month(1, 2002), 111.7);
        s2.add(new Month(2, 2002), 111.0);
        s2.add(new Month(3, 2002), 109.6);
        s2.add(new Month(4, 2002), 113.2);
        s2.add(new Month(5, 2002), 111.6);
        s2.add(new Month(6, 2002), 108.8);
        s2.add(new Month(7, 2002), 101.6);
        
         TimeSeries s3 = new TimeSeries("andres2255");
        s3.add(new Month(2, 2001), 129.6);
      
        s3.add(new Month(10, 2001), 106.1);
        s3.add(new Month(1, 2002), 111.7);
        s3.add(new Month(2, 2002), 111.0);
        datos.addSeries(s1);
        datos.addSeries(s2);
        datos.addSeries(s3);
        
        grafica=ChartFactory.createTimeSeriesChart("titulo", "etiquetas X", "etiquetaY", datos, true, true, false);
        /// 
        grafica.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) grafica.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        }
}
