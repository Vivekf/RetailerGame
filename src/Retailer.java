import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.jfree.data.xy.XYSeries;

import com.sun.medialib.mlib.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.event.*;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.table.TableModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Panel;
import javax.swing.UIManager;
import java.awt.Font;
import java.text.NumberFormat;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Retailer extends JPanel implements ActionListener{

	private JFrame frame;
	private JTextField textField_1;
	private JTextField txtHelllo;
	public JButton btnDropPrice10;
	public JButton btnDropPrice20;
	public JButton btnDropPrice40;
	private JTable table;
	private RetailerTableModel tableModel;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JButton btnMaintainPrice;
	private SalesSeason thisSeason;
	private JTextPane textPane;
	private JButton btnRestart;
	private JMenuBar menuBar;
	private JMenu mnSeed;
	private JMenuItem mntmSetseed;
	private JButton button;
	private JButton button_1;
	private JMenuItem mntmPrint;
	private JMenuItem mntmAbout;
	/**
	 * @wbp.nonvisual location=643,1
	 */
	private final Panel panel = new Panel();
	private Panel panel_1;
	public CapacityChart newChart;
	public ChartPanel chartPanel;
	public Boolean seedMode=false;
	public double seedEntered;
	private JLabel lblSloanLogo;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Retailer window = new Retailer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Retailer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		
		//Setup Frame
		frame = new JFrame("Sloan Retailer");
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/vivekfarias/Dropbox/Code/RetailerGame/images/Sloan.jpeg"));
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.setBounds(100, 100, 1156, 695);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow][left][][][][grow 600][grow 600][][grow]", "[grow][][][][][grow][grow][][][]"));
		
		

		//Setup Table
		scrollPane_1 = new JScrollPane();
		scrollPane_1.getViewport().setBackground(Color.white);
		frame.getContentPane().add(scrollPane_1, "cell 0 0,grow");
		tableModel = new RetailerTableModel();
		table_1 = new JTable(tableModel);
		table_1.setRowSelectionAllowed(false);
		table_1.setShowVerticalLines(false);
		table_1.setShowHorizontalLines(false);
		table_1.setShowGrid(false);
		scrollPane_1.setViewportView(table_1);
		
		// Add Maintian Price Button
		btnMaintainPrice = new JButton("Maintain Price");
		btnMaintainPrice.setFont(new Font("SansSerif", Font.PLAIN, 13));
		btnMaintainPrice.setForeground(new Color(0, 0, 128));
		btnMaintainPrice.addActionListener(this);
		
		panel_1 = new Panel();
		panel_1.setForeground(new Color(245, 245, 245));
		panel_1.setBackground(new Color(245, 245, 245));
		
		frame.getContentPane().add(panel_1, "cell 2 0 7 1");
		frame.getContentPane().add(btnMaintainPrice, "flowx,cell 0 2");
		
		
		//Add Sloan Image
		 try {   			 
			//URL imgURL = getResource("/images/Sloan.png");
			//BufferedImage sloanImage = ImageIO.read(new File("/Sloan.jpeg"));	
			BufferedImage sloanImage = ImageIO.read(getClass().getResource("/images/Sloan.jpeg"));	
			lblSloanLogo = new JLabel(new ImageIcon(getScaledImage(sloanImage, 0.56)));
			frame.getContentPane().add(lblSloanLogo, "cell 8 7 1 3,alignx left,aligny center");
	     } catch (IOException ex) {
	    	   System.out.println("Couldn't Find Image");
	     }

		
		textPane = new JTextPane();
		textPane.setEditable(false);
		frame.getContentPane().add(textPane, "cell 0 5 7 5,grow");
		
		
		// Add Drop Price Button
		btnDropPrice10 = new JButton("10%");
		btnDropPrice10.setFont(new Font("SansSerif", Font.PLAIN, 13));
		btnDropPrice10.setForeground(new Color(220, 20, 60));
		btnDropPrice10.addActionListener(this); 
		frame.getContentPane().add(btnDropPrice10, "cell 0 2");
		
		btnDropPrice20 = new JButton("20%");
		btnDropPrice20.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnDropPrice20.setForeground(new Color(220, 20, 60));
		btnDropPrice20.addActionListener(this);
		frame.getContentPane().add(btnDropPrice20, "cell 0 2");
		
		btnDropPrice40 = new JButton("40%");
		btnDropPrice40.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnDropPrice40.setForeground(new Color(220, 20, 60));
		btnDropPrice40.addActionListener(this);
		frame.getContentPane().add(btnDropPrice40, "cell 0 2");
		
		menuBar = new JMenuBar();
		menuBar.setFont(new Font("SansSerif", Font.BOLD, 14));
		menuBar.setBackground(Color.ORANGE);
		frame.setJMenuBar(menuBar);
		
		mnSeed = new JMenu("Retailer");
		mnSeed.setFont(new Font("SansSerif", Font.BOLD, 14));
		mnSeed.setBackground(new Color(255, 140, 0));
		menuBar.add(mnSeed);
		
		mntmSetseed = new JMenuItem("SetSeed");
		mntmSetseed.addActionListener(this);
		mnSeed.add(mntmSetseed);
		
		mntmPrint = new JMenuItem("Print");
		mntmPrint.addActionListener(new PrintUIWindow(frame));
		mnSeed.add(mntmPrint);
		
		mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(this);
		mnSeed.add(mntmAbout);
		
		// Add The Chart
        
        newChart = new CapacityChart();
        JFreeChart chart = newChart.buildChart();
        ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.setForeground(new Color(245, 245, 245));
        chartPanel.setBackground(new Color(245, 245, 245));
        panel_1.add( chartPanel );
        
        	btnRestart = new JButton("Restart");
        	btnRestart.setForeground(new Color(0, 0, 0));
        	btnRestart.addActionListener(this);
        	frame.getContentPane().add(btnRestart, "cell 0 2,alignx right,aligny baseline");
		
		
		
		// Create the sales season
		thisSeason = new SalesSeason();
		tableModel.addRow(new Object[]{thisSeason.getCurrentWeek(),thisSeason.getPriceLevel(),thisSeason.getCurrentDemand(),thisSeason.getCapacity()});
		newChart.update(thisSeason.getInventoryTrajectory(),thisSeason.getRevenueTrajectory());
		//panel_1.repaint();
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		int new_level=-1;
		if(arg0.getSource() == btnDropPrice10 || arg0.getSource() == btnDropPrice20 || arg0.getSource() == btnDropPrice40){
			if(arg0.getSource() == btnDropPrice10){
				new_level=2;
				btnDropPrice10.setEnabled(false);
			}
			else if(arg0.getSource() == btnDropPrice20){
				new_level=3;
				btnDropPrice10.setEnabled(false);
				btnDropPrice20.setEnabled(false);
			}
			else{
				btnDropPrice10.setEnabled(false);
				btnDropPrice20.setEnabled(false);
				btnDropPrice40.setEnabled(false);
				new_level=4;
			}			
			
			if(thisSeason.ended() == false){				
				thisSeason.lower_price(new_level);
				tableModel.addRow(new Object[]{thisSeason.getCurrentWeek(),thisSeason.getPriceLevel(),thisSeason.getCurrentDemand(),thisSeason.getCapacity()});		
				table_1.revalidate();
				table_1.repaint();
				newChart.update(thisSeason.getInventoryTrajectory(),thisSeason.getRevenueTrajectory());
				//panel_1.repaint();
				
			}
			
			while(thisSeason.ended() == false && new_level==4){				
				thisSeason.lower_price(new_level);
				tableModel.addRow(new Object[]{thisSeason.getCurrentWeek(),thisSeason.getPriceLevel(),thisSeason.getCurrentDemand(),thisSeason.getCapacity()});		
				table_1.revalidate();
				table_1.repaint();
				newChart.update(thisSeason.getInventoryTrajectory(),thisSeason.getRevenueTrajectory());
				
			}
			
		}
		else if(arg0.getSource() == btnMaintainPrice){
			if(thisSeason.ended() == false){

				thisSeason.same_price();
				tableModel.addRow(new Object[]{thisSeason.getCurrentWeek(),thisSeason.getPriceLevel(),thisSeason.getCurrentDemand(),thisSeason.getCapacity()});
				table_1.revalidate();
				table_1.repaint();	
				newChart.update(thisSeason.getInventoryTrajectory(),thisSeason.getRevenueTrajectory());
				//panel_1.repaint();
			}
		}
		else if(arg0.getSource() == btnRestart){
			restart();
			seedMode = false;
		}
		else if(arg0.getSource() == mntmSetseed){
			String s = (String)JOptionPane.showInputDialog(
					frame,
                    "Enter A Seed (or leave blank):\n",
                    "Enter Seed",
                    JOptionPane.QUESTION_MESSAGE);
			if(s!=null){
				restart(Double.parseDouble(s));
			}

		}
		else if(arg0.getSource() == mntmAbout){
			JOptionPane.showMessageDialog(frame, "Sloan Retailer V.0.1 by V. F. Farias, 07.2012"+'\n'+"Original Idea: Mark Broadie and Dev Joneja");	
		}
		else{
			
		}
				
		if(thisSeason.ended()){
			Font font = new Font("SansSerif", Font.BOLD, 13);
	        textPane.setFont(font);
	        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance();
	        
	        String message = "The Season is Over!"+'\n'
					+"You Earned "+numberFormatter.format((int)thisSeason.getRevenue())+"!" +'\n'+
					"You could have earned "+ numberFormatter.format((int)thisSeason.computeMax())+".";
	        if(seedMode){
	    		message = "The Season is Over!"+'\n'
						+"You Earned "+numberFormatter.format((int)thisSeason.getRevenue())+"!" +'\n'+
						"You could have earned "+ numberFormatter.format((int)thisSeason.computeMax())+".\n"+
						"Seed Mode: Seed Entered Was "+(int)seedEntered+".";						
	        }
	        
			textPane.setText(message);
		}
		
		
		
	}
	
	public void restart(double seed){
		seedMode = true;
		seedEntered = seed;
		thisSeason = new SalesSeason(seed);
		tableModel.clearData();
		tableModel.addRow(new Object[]{thisSeason.getCurrentWeek(),thisSeason.getPriceLevel(),thisSeason.getCurrentDemand(),thisSeason.getCapacity()});	
		newChart.update(thisSeason.getInventoryTrajectory(),thisSeason.getRevenueTrajectory());
		table_1.revalidate();	
		textPane.setText("");
		btnDropPrice10.setEnabled(true);
		btnDropPrice20.setEnabled(true);
		btnDropPrice40.setEnabled(true);
		
	}
	
	public void restart(){
		seedMode = false;
		thisSeason = new SalesSeason();
		tableModel.clearData();
		tableModel.addRow(new Object[]{thisSeason.getCurrentWeek(),thisSeason.getPriceLevel(),thisSeason.getCurrentDemand(),thisSeason.getCapacity()});		
		newChart.update(thisSeason.getInventoryTrajectory(),thisSeason.getRevenueTrajectory());
		table_1.revalidate();	
		textPane.setText("");
		btnDropPrice10.setEnabled(true);
		btnDropPrice20.setEnabled(true);
		btnDropPrice40.setEnabled(true);
	}
	
	private BufferedImage getScaledImage(BufferedImage image, double scale)  
    {  
        int w = (int)(image.getWidth() * scale);  
        int h = (int)(image.getHeight() * scale);  
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);  
        Graphics2D g2 = bi.createGraphics();  
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,  
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
        //                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);  
        AffineTransform at = AffineTransform.getScaleInstance(scale, scale);  
        g2.drawRenderedImage(image, at);  
        g2.dispose();  
        return bi;  
    }  

	
	

}
