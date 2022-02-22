import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.math3.geometry.Point;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextPane;
import java.awt.ScrollPane;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
public class MainFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnsrcfolder;
	private JButton btnbinfolder;
	private JScrollPane output;
	private JTextField correlation;
	private JButton btncorrel;
	private JTextPane srctextpane;
	private JTextPane bintextpane;
	private JButton btnanalyser;
	private JButton btnexcelfile;
	private JTextField LOCtextfield;
	private JTextField WMCtextfield;
	private JTextField CBOtextfield;
	private JTextField LCOMtextfield;
	private JTextField AVGCCtextfield;
	private JTextField FanIntextfield;
	private JTextField FanOutfieldtext;
	private JTextField NOCtextfield;
	private JTextField DITtextfield;
	private JTextField BUGStextfield;
	private JCheckBox WMCcheckbox;
	private JCheckBox CBOcheckbox;
	private JCheckBox LCOMcheckbox;
	private JCheckBox AVGCCcheckbox;
	private JCheckBox FanIncheckbox;
	private JCheckBox FanOutcheckbox;
	private JCheckBox NOCcheckbox;
	private JCheckBox LOCcheckbox;
	private JCheckBox DITcheckbox;
	private JCheckBox BUGScheckbox;
	private static int lines;
	private static int methods;
	private static int cbo;
	private static int fanin;
	private static int fanout;
	private static int lcom;
	private static int noc;
	private static int avgcc;
	private static int dit;
	private static int bug1;
	private static String coly;
	private static String colx;
	private JPanel panel;
	private JScrollPane scrollPane_1;
	private List points = new ArrayList<>();
	private int[] Xi;
	private int[] Yi;
	private JTable table;
	private JTextArea instructions;

	
	// Launch the application. 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.setResizable(false);
					ImageIcon icon = new ImageIcon("C:\\Users\\anasz\\eclipse-workspace\\FYP\\logo.png");
					frame.setIconImage(icon.getImage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//Create the frame. 
	public MainFrame() {
		setTitle("refactOK!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1007, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		
		btnbinfolder = new JButton("Chose the 'bin' folder of your project");
		btnbinfolder.addActionListener(this);
		btnbinfolder.setBackground(Color.decode("#57596D"));
		btnbinfolder.setForeground(Color.decode("#58D9FF"));
		btnbinfolder.setBorder(new LineBorder(Color.decode("#58D9FF")));
		btnbinfolder.setOpaque(true);
		btnbinfolder.setBorderPainted(false);
		
		btnsrcfolder = new JButton("Chose the 'source' folder of your project");
		btnsrcfolder.addActionListener(this);
		btnsrcfolder.setBackground(Color.decode("#57596D"));
		btnsrcfolder.setForeground(Color.decode("#58D9FF"));
		btnsrcfolder.setBorder(new LineBorder(Color.decode("#58D9FF")));
		btnsrcfolder.setOpaque(true);
		btnsrcfolder.setBorderPainted(false);
		
		bintextpane = new JTextPane();
		bintextpane.setBackground(Color.decode("#57596D"));
		bintextpane.setForeground(Color.decode("#58D9FF"));
		bintextpane.setBorder(new LineBorder(Color.decode("#58D9FF")));
		
		srctextpane = new JTextPane();
		srctextpane.setBackground(Color.decode("#57596D"));
		srctextpane.setForeground(Color.decode("#58D9FF"));
		srctextpane.setBorder(new LineBorder(Color.decode("#58D9FF")));
		
		//CREATE TABLE TO DISPLAY DATA
				table = new JTable();
				Object[] columns = {"Class", "LOC", "Blank Lines", "Single Line Comments", "Multi Line Comments", "Identation Count", "AVGCC", "Fan In", "Fan Out", "CBO", "WMC", "LCOM4", "NOC", "DIT", "BUGS"};
				
				DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(columns);
				table.setModel(model);
				table.setRowHeight(20);
				table.setAutoCreateRowSorter(true);
				table.setRowSelectionAllowed(false);
				table.setColumnSelectionAllowed(true);
				
		
		JTextPane bugs = new JTextPane();
		bugs.setBackground(Color.decode("#57596D"));
		bugs.setForeground(Color.decode("#58D9FF"));
		bugs.setBorder(new LineBorder(Color.decode("#58D9FF")));
		
		bugs.setEditable(false);
		bugs.setFont(new Font(null, Font.PLAIN, 10));
		bugs.setAlignmentX(JTextPane.CENTER_ALIGNMENT);
		bugs.setAlignmentY(JTextPane.CENTER_ALIGNMENT);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.decode("#57596D"));
		table.setBackground(Color.decode("#57596D"));
		table.setForeground(Color.decode("#58D9FF"));
		table.getTableHeader().setBackground(Color.decode("#57596D"));
		table.getTableHeader().setForeground(Color.decode("#58D9FF"));
		table.getTableHeader().setOpaque(false);
		
		instructions = new JTextArea();
//		instructions.setBounds(scrollPane_1.getBounds());
//		instructions.setMaximumSize(scrollPane_1.getSize());
		instructions.setText("LOC = returns Lines of Code in the class"+"\n"+
							"WMC = returns number of methods in each class"+"\n"+
							"CBO = returns the number of connections in each class"+"\n"+
							"Fan In = returns the number of incoming connections in each class"+"\n"+
							"Fan Out =  returns the number of outgoing connections from each class"+"\n"+
							"LCOM = returns the cohesion value in each class"+"\n"+
							"AVGCC = returns the value of cyclomatic complexity in each class"+"\n"+
							"NOC = returns the number of children each class has"+"\n"+
							"DIT = returns the level of the class in the depth inheritance tree of the system"+"\n"+
							"BUGS = returns the number of bugs and violations contained in each class"+"\n"+
							"Blank Lines = returns the number of blank lines present in each class"+"\n"+
							"Single Line comments = returns number of single line comments in the class \\"+"\n"+
							"Multi line comments = returns number of multi line comments in the class /**/");
		

		instructions.setBackground(Color.decode("#57596D"));
		instructions.setForeground(Color.decode("#58D9FF"));
		Font font = new Font("Dialog", Font.PLAIN, 8);
		instructions.setFont(font);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(scrollPane_1.getSize());
		scrollPane_1.setViewportView(instructions);

		scrollPane_1.setHorizontalScrollBarPolicy(scrollPane_1.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setVerticalScrollBarPolicy(scrollPane_1.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		btnanalyser = new JButton("Analyse");
		btnanalyser.setBackground(Color.decode("#58D9FF"));
		btnanalyser.setOpaque(true);
		btnanalyser.setBorderPainted(false);
		btnanalyser.addActionListener(new ActionListener() {		
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				DialogWait wait = new DialogWait();

				SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {

				    @Override
				    protected Void doInBackground() throws Exception {

				    	Object[] row = new Object[14];
						
				    	Output.trigger();
				    	
						model.setRowCount(0);
						
						WeMovin wm = new WeMovin();
						wm.setFiles(srctextpane.getText(), bintextpane.getText());
						
						try {
							row = wm.BuildTable(model);
						} catch (InterruptedException | IOException e) {
							e.printStackTrace();
						}
						
						
						
						for(int i = 1; i < table.getColumnCount();i++) {
							table.getColumnModel().getColumn(i).setCellRenderer(new StatusColumnCellRenderer());
						}
						
						table.repaint();
						table.setColumnSelectionAllowed(true);
						
						
						
				        wait.close();
				        return null;
				    }
				};

				mySwingWorker.execute();
				wait.makeWait("Analysing", arg0);

				bugs.setText("Select 2 columns and click to get correlation!");
			}
		});
		
		btncorrel = new JButton("Correlation");
		btncorrel.setBackground(Color.decode("#58D9FF"));
		btncorrel.setOpaque(true);
		btncorrel.setBorderPainted(false);
		btncorrel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				panel.removeAll();
				XYSeriesCollection dataset = new XYSeriesCollection();
		        XYSeries data = new XYSeries("data");
		        
				int[] columns = table.getSelectedColumns();
				int rowcount = model.getRowCount();
				int[] column1 = new int[rowcount];
				int[] column2 = new int[rowcount];
				double[] column3 = new double[rowcount];
				boolean first = false;
				boolean second = false;
				coly = table.getColumnName(columns[0]);
				colx = table.getColumnName(columns[1]);

				for (int i = 0; i < columns.length; i++) {
					for(int row = 0; row < rowcount; row++) {
									
//						System.out.println(columns[i]);
					
					if(columns[i] == 6 && i == 0) {
						int column = table.convertColumnIndexToModel(columns[i]);
				        column3[row] = (double) model.getValueAt(row, column);;
				        first = true;
					}
					else if(columns[i] == 6 && i == 1){
						int column = table.convertColumnIndexToModel(columns[i]);
				        column3[row] = (double) model.getValueAt(row, column);;
				        second = true;
					}
					
					if(i == 0 && columns[i] != 6) {
				        int column = table.convertColumnIndexToModel(columns[i]);
				        column1[row] = (int) model.getValueAt(row, column);
				    }
					else if(i == 1 && columns[i] != 6) {
						int column = table.convertColumnIndexToModel(columns[i]);
						column2[row] = (int) model.getValueAt(row, column);
					}
					}
				}	
				
					if(first) {
						bugs.setText("Correl:"+WeMovin.Calculatecorrelation(column3, column2));
					
					}
					else if(second) {
						bugs.setText("Correl:"+WeMovin.Calculatecorrelation(column3, column1));
					}
					else {
						bugs.setText("Correl:"+WeMovin.Calculatecorrelation(column1, column2));	
					}
				
				
				for(int i = 0; i < column1.length;i++) {
					if(first) {
						data.add(column3[i], column2[i]);
					}
					else if(second) {
						data.add(column3[i], column1[i]);
					}
					else {
						data.add(column1[i], column2[i]);
					}
				}
				dataset.addSeries(data);

				JFreeChart chart = createChart(dataset);
		        ChartPanel chartPanel = new ChartPanel(chart);
		        chartPanel.setPreferredSize(panel.getSize());
		        panel.add(chartPanel);
			}			
		});
		
		btnexcelfile = new JButton("Generate Excel");
		btnexcelfile.setBackground(Color.decode("#57596D"));
		btnexcelfile.setForeground(Color.decode("#58D9FF"));
		btnexcelfile.setBorder(new LineBorder(Color.decode("#58D9FF")));
		btnexcelfile.setOpaque(true);
		btnexcelfile.setBorderPainted(false);
		btnexcelfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				WeMovin wm = new WeMovin();
				Excel.CreateFile2(table);
			}	
		});
		
		LOCcheckbox = new JCheckBox();
		LOCcheckbox.setBackground(Color.decode("#1B1D38"));
		
		WMCcheckbox = new JCheckBox();
		WMCcheckbox.setBackground(Color.decode("#1B1D38"));
		
		CBOcheckbox = new JCheckBox();
		CBOcheckbox.setBackground(Color.decode("#1B1D38"));
		
		LCOMcheckbox = new JCheckBox();
		LCOMcheckbox.setBackground(Color.decode("#1B1D38"));
		
		AVGCCcheckbox = new JCheckBox();
		AVGCCcheckbox.setBackground(Color.decode("#1B1D38"));
		
		FanIncheckbox = new JCheckBox();
		FanIncheckbox.setBackground(Color.decode("#1B1D38"));
		
		FanOutcheckbox = new JCheckBox();
		FanOutcheckbox.setBackground(Color.decode("#1B1D38"));
		
		NOCcheckbox = new JCheckBox();
		NOCcheckbox.setBackground(Color.decode("#1B1D38"));
		
		DITcheckbox = new JCheckBox();
		DITcheckbox.setBackground(Color.decode("#1B1D38"));
		
		BUGScheckbox = new JCheckBox();
		BUGScheckbox.setBackground(Color.decode("#1B1D38"));
		
		LOCtextfield = new JTextField();
		LOCtextfield.setBackground(Color.decode("#57596D"));
		LOCtextfield.setToolTipText("Enter max value for LOC");
		LOCtextfield.setColumns(10);
		LOCtextfield.setText("LOC");
		LOCtextfield.setForeground(Color.decode("#58D9FF"));
		LOCtextfield.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				LOCtextfield.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (LOCtextfield.getText().length() == 0) {  
					LOCtextfield.setText("LOC"); 
				}
			}	
		});
		
		WMCtextfield = new JTextField();
		WMCtextfield.setToolTipText("Enter max value for WMC");
		WMCtextfield.setColumns(10);
		WMCtextfield.setBackground(Color.decode("#57596D"));
		WMCtextfield.setText("WMC");
		WMCtextfield.setForeground(Color.decode("#58D9FF"));
		WMCtextfield.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				WMCtextfield.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (WMCtextfield.getText().length() == 0) {  
					WMCtextfield.setText("WMC"); 
				}
			}	
		});
		
		CBOtextfield = new JTextField();
		CBOtextfield.setToolTipText("Enter max value for CBO");
		CBOtextfield.setColumns(10);
		CBOtextfield.setBackground(Color.decode("#57596D"));
		CBOtextfield.setText("CBO");
		CBOtextfield.setForeground(Color.decode("#58D9FF"));
		CBOtextfield.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				CBOtextfield.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (CBOtextfield.getText().length() == 0) {  
					CBOtextfield.setText("CBO"); 
				}
			}	
		});
		
		LCOMtextfield = new JTextField();
		LCOMtextfield.setToolTipText("Enter max value for LCOM");
		LCOMtextfield.setColumns(10);
		LCOMtextfield.setBackground(Color.decode("#57596D"));
		LCOMtextfield.setText("LCOM");
		LCOMtextfield.setForeground(Color.decode("#58D9FF"));
		LCOMtextfield.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				LCOMtextfield.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (LCOMtextfield.getText().length() == 0) {  
					LCOMtextfield.setText("LCOM"); 
				}
			}	
		});
		
		AVGCCtextfield = new JTextField();
		AVGCCtextfield.setToolTipText("Enter max value for AVGCC");
		AVGCCtextfield.setColumns(10);
		AVGCCtextfield.setBackground(Color.decode("#57596D"));
		AVGCCtextfield.setText("AVGCC");
		AVGCCtextfield.setForeground(Color.decode("#58D9FF"));
		AVGCCtextfield.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				AVGCCtextfield.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (AVGCCtextfield.getText().length() == 0) {  
					AVGCCtextfield.setText("AVGCC"); 
				}
			}	
		});
		
		FanIntextfield = new JTextField();
		FanIntextfield.setToolTipText("Enter max value for Fan In");
		FanIntextfield.setColumns(10);
		FanIntextfield.setBackground(Color.decode("#57596D"));
		FanIntextfield.setText("Fan In");
		FanIntextfield.setForeground(Color.decode("#58D9FF"));
		FanIntextfield.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				FanIntextfield.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (FanIntextfield.getText().length() == 0) {  
					FanIntextfield.setText("Fan In"); 
				}
			}	
		});
		
		FanOutfieldtext = new JTextField();
		FanOutfieldtext.setToolTipText("Enter max value for Fan Out");
		FanOutfieldtext.setColumns(10);
		FanOutfieldtext.setBackground(Color.decode("#57596D"));
		FanOutfieldtext.setText("Fan Out");
		FanOutfieldtext.setForeground(Color.decode("#58D9FF"));
		FanOutfieldtext.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				FanOutfieldtext.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (FanOutfieldtext.getText().length() == 0) {  
					FanOutfieldtext.setText("Fan Out"); 
				}
			}	
		});
		
		NOCtextfield = new JTextField();
		NOCtextfield.setToolTipText("Enter max value for NOC");
		NOCtextfield.setColumns(10);
		NOCtextfield.setBackground(Color.decode("#57596D"));
		NOCtextfield.setText("NOC");
		NOCtextfield.setForeground(Color.decode("#58D9FF"));
		NOCtextfield.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				NOCtextfield.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (NOCtextfield.getText().length() == 0) {  
					NOCtextfield.setText("NOC"); 
				}
			}	
		});
		
		DITtextfield = new JTextField();
		DITtextfield.setBackground(Color.decode("#57596D"));
		DITtextfield.setToolTipText("Enter max value for DIT");
		DITtextfield.setColumns(10);
		DITtextfield.setText("DIT");
		DITtextfield.setForeground(Color.decode("#58D9FF"));
		DITtextfield.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				DITtextfield.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (DITtextfield.getText().length() == 0) {  
					DITtextfield.setText("DIT"); 
				}
			}	
		});
		
		BUGStextfield = new JTextField();
		BUGStextfield.setBackground(Color.decode("#57596D"));
		BUGStextfield.setToolTipText("Enter max value for BUGS");
		BUGStextfield.setColumns(10);
		BUGStextfield.setText("BUGS");
		BUGStextfield.setForeground(Color.decode("#58D9FF"));
		BUGStextfield.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				BUGStextfield.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (BUGStextfield.getText().length() == 0) {  
					BUGStextfield.setText("BUGS"); 
				}
			}	
		});
		
		LOCcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(LOCcheckbox.isSelected()){
					lines = Integer.parseInt(LOCtextfield.getText());
					LOCtextfield.setEditable(false);
					LOCtextfield.setBackground(Color.decode("#58D9FF"));
					LOCtextfield.setForeground(Color.decode("#57596D"));
					WeMovin.SetLOC(lines);
				}
				else {
					lines = 0;
					LOCtextfield.setEditable(true);
					LOCtextfield.setBackground(Color.decode("#57596D"));
					LOCtextfield.setForeground(Color.decode("#58D9FF"));
					LOCtextfield.setText("LOC"); 
					WeMovin.SetLOC(lines);
				}
			}			
		});
		
		WMCcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(WMCcheckbox.isSelected()){
					methods = Integer.parseInt(WMCtextfield.getText());
					WMCtextfield.setEditable(false);
					WMCtextfield.setBackground(Color.decode("#58D9FF"));
					WMCtextfield.setForeground(Color.decode("#57596D"));
					WeMovin.SetWMC(methods);
				}
				else {
					methods = 0;
					WMCtextfield.setEditable(true);
					WMCtextfield.setBackground(Color.decode("#57596D"));
					WMCtextfield.setForeground(Color.decode("#58D9FF"));
					WMCtextfield.setText("WMC");
					WeMovin.SetWMC(methods);
				}
			}		
		});
		
		CBOcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CBOcheckbox.isSelected()){
					cbo = Integer.parseInt(CBOtextfield.getText());
					CBOtextfield.setEditable(false);
					CBOtextfield.setBackground(Color.decode("#58D9FF"));
					CBOtextfield.setForeground(Color.decode("#57596D"));
					WeMovin.SetCBO(cbo);
				}
				else {
					cbo = 0;
					CBOtextfield.setEditable(true);
					CBOtextfield.setBackground(Color.decode("#57596D"));
					CBOtextfield.setForeground(Color.decode("#58D9FF"));
					CBOtextfield.setText("CBO");
					WeMovin.SetCBO(cbo);
				}
			}			
		});

		LCOMcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(LCOMcheckbox.isSelected()){
					lcom = Integer.parseInt(LCOMtextfield.getText());
					LCOMtextfield.setEditable(false);
					LCOMtextfield.setBackground(Color.decode("#58D9FF"));
					LCOMtextfield.setForeground(Color.decode("#57596D"));
					WeMovin.SetLCOM(lcom);
				}
				else {
					lcom = 0;
					LCOMtextfield.setEditable(true);
					LCOMtextfield.setBackground(Color.decode("#57596D"));
					LCOMtextfield.setForeground(Color.decode("#58D9FF"));
					LCOMtextfield.setText("LCOM");
					WeMovin.SetLCOM(lcom);
				}
			}
		});

		AVGCCcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(AVGCCcheckbox.isSelected()){
					avgcc = Integer.parseInt(AVGCCtextfield.getText());
					AVGCCtextfield.setEditable(false);
					AVGCCtextfield.setBackground(Color.decode("#58D9FF"));
					AVGCCtextfield.setForeground(Color.decode("#57596D"));
					WeMovin.SetAVGCC(avgcc);
				}
				else {
					avgcc = 0;
					AVGCCtextfield.setEditable(true);
					AVGCCtextfield.setBackground(Color.decode("#57596D"));
					AVGCCtextfield.setForeground(Color.decode("#58D9FF"));
					AVGCCtextfield.setText("AVGCC");
					WeMovin.SetAVGCC(avgcc);
				}
			}	
		});

		FanIncheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(FanIncheckbox.isSelected()){
					fanin = Integer.parseInt(FanIntextfield.getText());
					FanIntextfield.setEditable(false);
					FanIntextfield.setBackground(Color.decode("#58D9FF"));
					FanIntextfield.setForeground(Color.decode("#57596D"));
					WeMovin.SetFanIn(fanin);
				}
				else {
					fanin = 0;
					FanIntextfield.setEditable(true);
					FanIntextfield.setBackground(Color.decode("#57596D"));
					FanIntextfield.setForeground(Color.decode("#58D9FF"));
					FanIntextfield.setText("FanIn");
					WeMovin.SetFanIn(fanin);
				}
			}	
		});

		FanOutcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(FanOutcheckbox.isSelected()){
					fanout = Integer.parseInt(FanOutfieldtext.getText());
					FanOutfieldtext.setEditable(false);
					FanOutfieldtext.setBackground(Color.decode("#58D9FF"));
					FanOutfieldtext.setForeground(Color.decode("#57596D"));
					WeMovin.SetFanOut(fanout);
				}
				else {
					fanout = 0;
					FanOutfieldtext.setEditable(true);
					FanOutfieldtext.setBackground(Color.decode("#57596D"));
					FanOutfieldtext.setForeground(Color.decode("#58D9FF"));
					FanOutfieldtext.setText("FanOut");
					WeMovin.SetFanOut(fanout);
				}
			}
			
		});

		NOCcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(NOCcheckbox.isSelected()){
					noc = Integer.parseInt(NOCtextfield.getText());
					NOCtextfield.setEditable(false);
					NOCtextfield.setBackground(Color.decode("#58D9FF"));
					NOCtextfield.setForeground(Color.decode("#57596D"));
					WeMovin.SetNOC(noc);
				}
				else {
					noc = 0;
					NOCtextfield.setEditable(true);
					NOCtextfield.setBackground(Color.decode("#57596D"));
					NOCtextfield.setForeground(Color.decode("#58D9FF"));
					NOCtextfield.setText("NOC");
					WeMovin.SetNOC(noc);
				}
			}	
		});
		
		DITcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(DITcheckbox.isSelected()){
					dit = Integer.parseInt(DITtextfield.getText());
					DITtextfield.setEditable(false);
					DITtextfield.setBackground(Color.decode("#58D9FF"));
					DITtextfield.setForeground(Color.decode("#57596D"));
					WeMovin.SetDIT(dit);
				}
				else {
					dit = 0;
					DITtextfield.setEditable(true);
					DITtextfield.setBackground(Color.decode("#57596D"));
					DITtextfield.setForeground(Color.decode("#58D9FF"));
					DITtextfield.setText("DIT"); 
					WeMovin.SetDIT(dit);
				}
			}			
		});
		
		BUGScheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(BUGScheckbox.isSelected()){
					bug1 = Integer.parseInt(BUGStextfield.getText());
					BUGStextfield.setEditable(false);
					BUGStextfield.setBackground(Color.decode("#58D9FF"));
					BUGStextfield.setForeground(Color.decode("#57596D"));
					WeMovin.SetB(bug1);
				}
				else {
					bug1 = 0;
					BUGStextfield.setEditable(true);
					BUGStextfield.setBackground(Color.decode("#57596D"));
					BUGStextfield.setForeground(Color.decode("#58D9FF"));
					BUGStextfield.setText("BUGS"); 
					WeMovin.SetB(bug1);
				}
			}			
		});
		
		panel = new JPanel(); 
		panel.setBackground(Color.decode("#57596D"));
		panel.setBorder(new LineBorder(Color.decode("#58D9FF")));
		
//		scrollPane_1 = new JScrollPane();
		scrollPane_1.getViewport().setBackground(Color.decode("#57596D"));
		scrollPane_1.getViewport().setForeground(Color.decode("#58D9FF"));
		scrollPane_1.setBorder(new LineBorder(Color.decode("#58D9FF")));
		
		/*
		DITcheckbox = new JCheckBox("New check box");
		
		BUGScheckbox = new JCheckBox("New check box");
		
		DITtextfield = new JTextField();
		DITtextfield.setColumns(10);
		
		BUGStextfield = new JTextField();
		BUGStextfield.setColumns(10);
		*/
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
								.addComponent(btnbinfolder, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
								.addComponent(btnsrcfolder, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(bintextpane, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
								.addComponent(srctextpane, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(165)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(DITcheckbox, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
												.addComponent(CBOcheckbox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(LOCcheckbox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(WMCcheckbox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(LCOMcheckbox))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addComponent(WMCtextfield, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
													.addComponent(LOCtextfield, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
													.addComponent(DITtextfield, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
													.addComponent(LCOMtextfield, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
													.addComponent(CBOtextfield, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)))
											.addGap(34)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(AVGCCcheckbox)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(BUGScheckbox, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
														.addComponent(NOCcheckbox, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(BUGStextfield, 0, 0, Short.MAX_VALUE)
														.addComponent(NOCtextfield, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
														.addComponent(FanOutfieldtext, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
														.addComponent(FanIntextfield, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
														.addComponent(AVGCCtextfield, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
													.addGap(65))
												.addComponent(FanIncheckbox)
												.addComponent(FanOutcheckbox)))
										.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
											.addComponent(btnexcelfile, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
											.addGap(98)))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(bugs, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
												.addComponent(panel, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btncorrel)
											.addGap(18)))))
							.addGap(0))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnanalyser)
							.addContainerGap(906, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnbinfolder)
						.addComponent(bintextpane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnsrcfolder)
						.addComponent(srctextpane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(72)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(bugs, 0, 0, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(AVGCCtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(AVGCCcheckbox))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(FanIntextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(FanIncheckbox))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(FanOutcheckbox)
										.addComponent(FanOutfieldtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(NOCtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(NOCcheckbox))))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(BUGScheckbox)
								.addComponent(BUGStextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(LOCcheckbox)
								.addComponent(LOCtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(WMCcheckbox)
								.addComponent(WMCtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(CBOcheckbox)
								.addComponent(CBOtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(LCOMcheckbox)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(2)
									.addComponent(LCOMtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(DITtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(DITcheckbox))))
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnanalyser, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btncorrel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnexcelfile, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		contentPane.setBackground(Color.decode("#1B1D38"));

		
	    ListSelectionModel ColumnSelectionModel = table.getSelectionModel();
	    ColumnSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	    	  
	    	  int colindex = table.getSelectedColumn();
	    	  ArrayList<String> classnames = new ArrayList<>();
	    	  
	    	  if(colindex == 1) {
	    		  for(int i = 0; i < table.getRowCount(); i++) {
	    			  if((int) table.getValueAt(i, colindex) > 100) {
	    				  classnames.add(table.getValueAt(i, 0).toString());
	    			  }
	    		  }
	  			instructions.setText("The following classes:"+"\n"+classnames+"\n"+"Have too many lines of code,"+"\n"+"this can result in higher complexity and bugs."+"\n"+"Revist these classes!");
	  			}
	    	 
	    	  else if (colindex == 6) {
	    		  for(int i = 0; i < table.getRowCount(); i++) {
	    			  if(((double) table.getValueAt(i, colindex) > 5)) {
	    				  classnames.add(table.getValueAt(i, 0).toString());
	    			  }
	    		  }
	    		  instructions.setText("The following classes:"+"\n"+classnames+"\n"+"Have a high value of complexity"+"\n"+"This can result in a higher number of bugs"+"\n"+"please ensure there are not many branches of if statements!" + "\n"+Output.getccoutput());
	    	  } 
	    	  else if (colindex == 7) {
	    		  for(int i = 0; i < table.getRowCount(); i++) {
	    			  if((int) table.getValueAt(i, colindex) > 5) {
	    				  classnames.add(table.getValueAt(i, 0).toString());
	    			  }
	    		  }
	    		  instructions.setText("The following classes:"+"\n"+classnames+"\n"+"Have a high number of Fan In, this can result in bugs"+"\n"+"Ensure that the classes calling this class can operate on their own!"+"\n"+Output.getfioutput());
	    	  }
	    	  else if (colindex == 8) {
	    		  for(int i = 0; i < table.getRowCount(); i++) {
	    			  if((int) table.getValueAt(i, colindex) > 5) {
	    				  classnames.add(table.getValueAt(i, 0).toString());
	    			  }
	    		  }
	    		  instructions.setText("The following classes:"+"\n"+classnames+"\n"+"Have a high number of Fan Out, this can result in bugs"+"\n"+"Ensure that the classes can operate on their own, otherwise move the methods to the called classes" + "\n" + Output.getfooutput());
	    	  }
	    	  else if (colindex == 9) {
	    		  for(int i = 0; i < table.getRowCount(); i++) {
	    			  if((int) table.getValueAt(i, colindex) > 10) {
	    				  classnames.add(table.getValueAt(i, 0).toString());
	    			  }
	    		  }
	    		  instructions.setText("The following classes:"+"\n"+classnames+"\n"+"Have a high number of coupling, this can lead to bugs and complexity"+"\n"+"Avoid SPAGHETTI CODE!");
	    	  }
	    	  else if (colindex == 10) {
	    		  for(int i = 0; i < table.getRowCount(); i++) {
	    			  if((int) table.getValueAt(i, colindex) > 15) {
	    				  classnames.add(table.getValueAt(i, 0).toString());
	    			  }
	    		  }
	    		  instructions.setText("The following classes:"+"\n"+classnames+"\n"+"Have a high number of methods, this can lead to bugs and higher complexity"+"\n"+"if the same class has a high value of CBO, you can move some methods to other classes"+"\n"+"A class wiht too many methods can be dangerous for the system!"+"\n"+Output.getmethoutput());
	    	  }
	    	  else if (colindex == 11) {
	    		  for(int i = 0; i < table.getRowCount(); i++) {
	    			  if((int) table.getValueAt(i, colindex) != 1) {
	    				  classnames.add(table.getValueAt(i, 0).toString());
	    			  }
	    		  }
	    		  instructions.setText("The following classes:"+"\n"+classnames+"\n"+"Have bad cohesion, this can mean that some methods are not needed in these classes"+"\n"+"If the value is 0: It means the cohesion is low and bad"+"\n"+"If the value is 2: It means that the number of attributes are higher than the methods in the class");
	    	  }
	    	  else if (colindex == 12) {
	    		  for(int i = 0; i < table.getRowCount(); i++) {
	    			  if((int) table.getValueAt(i, colindex) > 5) {
	    				  classnames.add(table.getValueAt(i, 0).toString());
	    			  }
	    		  }
	    		  instructions.setText("The following classes:"+"\n"+classnames+"\n"+"Have a high number of children, this can lower maintainability and durability"+"\n"+"Please avoid extending too many classes this can make it harder for troubleshoot!"+"\n"+Output.getnocoutput());
	    	  }
	    	  else if (colindex == 13) {
	    		  for(int i = 0; i < table.getRowCount(); i++) {
	    			  if((int) table.getValueAt(i, colindex) > 5) {
	    				  classnames.add(table.getValueAt(i, 0).toString());
	    			  }
	    		  }
	    		  instructions.setText("The following classes:"+"\n"+classnames+"\n"+"Are very low in the depth inheritance tree of the class!"+"\n"+"Check the other metrics and try to solve the issues within your system to lower the inheritance index."+"\n"+"The more classes are extended from one class the harder it is to trace back and troubleshoot!"+"\n"+Output.getditoutput());
	    	  }
	    	  else if (colindex == 14) {
	    		  for(int i = 0; i < table.getRowCount(); i++) {
	    			  if((int) table.getValueAt(i, colindex) > 5) {
	    				  classnames.add(table.getValueAt(i, 0).toString());
	    			  }
	    		  }
	    		  instructions.setText("The following classes:"+"\n"+classnames+"\n"+"Have a high number of Bugs and errors registered!"+"\n"+"Check the other metrics and try to solve the issues within your system to lower the number of bugs."+"\n"+Output.getbugoutput());
	    	  }
	    	  else {
	    		  instructions.setText("LOC = returns Lines of Code in the class"+"\n"+
							"WMC = returns number of methods in each class"+"\n"+
							"CBO = returns the number of connections in each class"+"\n"+
							"Fan In = returns the number of incoming connections in each class"+"\n"+
							"Fan Out =  returns the number of outgoing connections from each class"+"\n"+
							"LCOM = returns the cohesion value in each class"+"\n"+
							"AVGCC = returns the value of cyclomatic complexity in each class"+"\n"+
							"NOC = returns the number of children each class has"+"\n"+
							"DIT = returns the level of the class in the depth inheritance tree of the system"+"\n"+
							"BUGS = returns the number of bugs and violations contained in each class"+"\n"+
							"Blank Lines = returns the number of blank lines present in each class"+"\n"+
							"Single Line comments = returns number of single line comments in the class \\"+"\n"+
							"Multi line comments = returns number of multi line comments in the class /**/");
	    	  }
	      }
	      });
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	 
		
		if(e.getSource()==btnsrcfolder) {
			JFileChooser jfilechooser = new JFileChooser();
			jfilechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			int response = jfilechooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				File file = new File(jfilechooser.getSelectedFile().getAbsolutePath());
				srctextpane.setText(file.toString());
			}
		}
		
		if(e.getSource()==btnbinfolder) {
			JFileChooser jfilechooser = new JFileChooser();
			jfilechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			int response = jfilechooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				File file = new File(jfilechooser.getSelectedFile().getAbsolutePath());
				bintextpane.setText(file.toString());
			}
		}
	}
		
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createScatterPlot(
            "Scatterplot",             // chart title
            colx,                      // x axis label
            coly,                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            false,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        plot.setRenderer(renderer);
        return chart;
    }
}
