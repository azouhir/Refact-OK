package RefactOK;
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
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.lang3.StringUtils;
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

import javax.swing.BorderFactory;
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
	private static double avgcc;
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
	private String mainbackgroundcolour = "#feeafa";
	private String textcolour = "#11151c";
	private String componentsbgcolour = "#efd3d7";
	private String bordercolour = "#11151c";
	private String buttoncolours = "#cbc0d3";
	private String buttontext = "#11151c";
	private String selected = "#d66853";
	private String selectedtxtcolour = "#FFFFFF";

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
		btnbinfolder.setBackground(Color.decode(componentsbgcolour));
		btnbinfolder.setForeground(Color.decode(textcolour));
///		btnbinfolder.setBorder(new LineBorder(Color.decode(bordercolour)));
		btnbinfolder.setBorder(BorderFactory.createLineBorder(Color.decode(bordercolour), 4));
//		btnbinfolder.setBackground(Color.decode(componentsbgcolour));
//		btnbinfolder.setBorder(new MatteBorder (0,0,2,0, Color.decode(bordercolour)));
		btnbinfolder.setOpaque(true);
		btnbinfolder.setBorderPainted(false);
//		btnbinfolder.setContentAreaFilled(true);
		
		btnsrcfolder = new JButton("Chose the 'source' folder of your project");
		btnsrcfolder.addActionListener(this);
		btnsrcfolder.setBackground(Color.decode(componentsbgcolour));
		btnsrcfolder.setForeground(Color.decode(textcolour));
		btnsrcfolder.setBorder(new LineBorder(Color.decode(bordercolour)));
		btnsrcfolder.setOpaque(true);
		btnsrcfolder.setBorderPainted(false);
		
		bintextpane = new JTextPane();
		bintextpane.setBackground(Color.decode(componentsbgcolour));
		bintextpane.setForeground(Color.decode(textcolour));
		bintextpane.setBorder(new LineBorder(Color.decode(bordercolour)));
		
		srctextpane = new JTextPane();
		srctextpane.setBackground(Color.decode(componentsbgcolour));
		srctextpane.setForeground(Color.decode(textcolour));
		srctextpane.setBorder(new LineBorder(Color.decode(bordercolour)));
		
		//CREATE TABLE TO DISPLAY DATA
				table = new JTable();
				Object[] columns = {"Class", "LOC", "Blank Lines", "Single Line Comments", 
						"Multi Line Comments", "Identation Count", "AVGCC", "Fan In", "Fan Out", 
						"CBO", "WMC", "LCOM4", "NOC", "DIT", "BUGS"};
				
				DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(columns);
				table.setModel(model);
				table.setRowHeight(20);
				table.setAutoCreateRowSorter(true);
				table.setRowSelectionAllowed(false);
				table.setColumnSelectionAllowed(true);
				
		
		JTextPane bugs = new JTextPane();
		bugs.setBackground(Color.decode(componentsbgcolour));
		bugs.setForeground(Color.decode(textcolour));
		bugs.setBorder(new LineBorder(Color.decode(bordercolour)));
		
		bugs.setEditable(false);
		bugs.setFont(new Font(null, Font.PLAIN, 10));
		bugs.setAlignmentX(JTextPane.CENTER_ALIGNMENT);
		bugs.setAlignmentY(JTextPane.CENTER_ALIGNMENT);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new LineBorder(Color.decode(bordercolour)));
		scrollPane.getViewport().setBackground(Color.decode(componentsbgcolour));
		table.setBackground(Color.decode(componentsbgcolour));
		table.setForeground(Color.decode(textcolour));
		table.getTableHeader().setBackground(Color.decode(componentsbgcolour));
		table.getTableHeader().setForeground(Color.decode(textcolour));
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
		

		instructions.setBackground(Color.decode(componentsbgcolour));
		instructions.setForeground(Color.decode(textcolour));
		Font font = new Font("Dialog", Font.PLAIN, 10);
		instructions.setFont(font);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(scrollPane_1.getSize());
		scrollPane_1.setViewportView(instructions);

		scrollPane_1.setHorizontalScrollBarPolicy(scrollPane_1.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setVerticalScrollBarPolicy(scrollPane_1.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JButton metricsinfo = new JButton("info");
		metricsinfo.setContentAreaFilled(false);
		metricsinfo.setForeground(Color.decode(textcolour));
		metricsinfo.setBorder(new MatteBorder (2,2,2,2, Color.decode(bordercolour)));
		metricsinfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "enter a value in any one of the text fields! Enter any number you wish \n "
						+ "[  ][100      ]         [  ][5.5          ] \n "
						+ "[  ][WMC   ]         [  ][Fan In     ] \n "
						+ "[  ][CBO     ]         [  ][Fan Out  ] \n "
						+ "[  ][LCOM  ]         [  ][NOC       ] \n "
						+ "[  ][DIT      ]         [  ][10           ] \n "
						+ " \n"
						+ "Once done tick the checkbox on the left hand side to lock the value in [v]! for example: \n "
						+ "[V][100      ]         [V][5.5          ] \n "
						+ "[  ][WMC   ]         [  ][Fan In     ] \n "
						+ "[  ][CBO     ]         [  ][Fan Out  ] \n "
						+ "[  ][LCOM  ]         [  ][NOC       ] \n "
						+ "[  ][DIT      ]         [V][10           ] ", "How to enter a threshold", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		btnanalyser = new JButton("Analyse");
		btnanalyser.setBackground(Color.decode(buttoncolours));
		btnanalyser.setForeground(Color.decode(buttontext));
		btnanalyser.setOpaque(true);
		btnanalyser.setBorderPainted(false);
		btnanalyser.addActionListener(new ActionListener() {		
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(srctextpane.getText().toCharArray().length >= 120 || bintextpane.getText().toCharArray().length >= 120) {
					JOptionPane.showMessageDialog(new JFrame(), "Error: Out of Boundary! Over 120 characters entered", "Error",
					        JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(srctextpane.getText().isEmpty() || bintextpane.getText().isEmpty() || StringUtils.isBlank(srctextpane.getText()) || StringUtils.isBlank(bintextpane.getText())) {
					JOptionPane.showMessageDialog(new JFrame(), "Error: Folder Paths Missing! Enter folder paths", "Error",
					        JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!WeMovin.validFiles(srctextpane.getText(), bintextpane.getText())) {
					JOptionPane.showMessageDialog(new JFrame(), "Error: Wrong folder selected! One of your folders containg wrong files", "Error",
					        JOptionPane.ERROR_MESSAGE);
					return;
				}
				
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
						} catch (Exception e) {
							JOptionPane.showMessageDialog(new JFrame(), "Error: Ops something went wrong! Check the folders you selected", "Error",
							        JOptionPane.ERROR_MESSAGE);
							System.out.println(e);
							wait.close();
							return null;
						}		
						
						if(row == null) {
							JOptionPane.showMessageDialog(new JFrame(), "Error: Ops something went wrong! Check the folders you selected", "Error",
							        JOptionPane.ERROR_MESSAGE);
							return null;
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
		btncorrel.setBackground(Color.decode(buttoncolours));
		btncorrel.setForeground(Color.decode(buttontext));
		btncorrel.setOpaque(true);
		btncorrel.setBorderPainted(false);
		btncorrel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedColumnCount() == 1) {
					JOptionPane.showMessageDialog(new JFrame(), "Error: Missing Column! Only one column selected, select another one", "Error",
					        JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(table.getSelectedColumnCount() <= 0) {
					JOptionPane.showMessageDialog(new JFrame(), "Error: Missing Columns! No columns selected, select two columns", "Error",
					        JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(table.getSelectedColumn() == 0) {
					JOptionPane.showMessageDialog(new JFrame(), "Error: String column! You can't calculate correlation between String and int, select another column", "Error",
					        JOptionPane.ERROR_MESSAGE);
					return;
				}
				
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
		btnexcelfile.setBackground(Color.decode(componentsbgcolour));
		btnexcelfile.setForeground(Color.decode(textcolour));
		btnexcelfile.setBorder(new LineBorder(Color.decode(bordercolour)));
		btnexcelfile.setOpaque(true);
		btnexcelfile.setBorderPainted(false);
		btnexcelfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				WeMovin wm = new WeMovin();
				if(!Excel.validateExcel(table)) {
					
					JOptionPane.showMessageDialog(new JFrame(), "Error: Table is empty! Analyse files before generating file", "Error",
					        JOptionPane.ERROR_MESSAGE);
					return;
				}
				Excel.CreateFile2(table);
			}	
		});
		
		LOCcheckbox = new JCheckBox();
		LOCcheckbox.setBackground(Color.decode(mainbackgroundcolour));
		
		WMCcheckbox = new JCheckBox();
		WMCcheckbox.setBackground(Color.decode(mainbackgroundcolour));
		
		CBOcheckbox = new JCheckBox();
		CBOcheckbox.setBackground(Color.decode(mainbackgroundcolour));
		
		LCOMcheckbox = new JCheckBox();
		LCOMcheckbox.setBackground(Color.decode(mainbackgroundcolour));
		
		AVGCCcheckbox = new JCheckBox();
		AVGCCcheckbox.setBackground(Color.decode(mainbackgroundcolour));
		
		FanIncheckbox = new JCheckBox();
		FanIncheckbox.setBackground(Color.decode(mainbackgroundcolour));
		
		FanOutcheckbox = new JCheckBox();
		FanOutcheckbox.setBackground(Color.decode(mainbackgroundcolour));
		
		NOCcheckbox = new JCheckBox();
		NOCcheckbox.setBackground(Color.decode(mainbackgroundcolour));
		
		DITcheckbox = new JCheckBox();
		DITcheckbox.setBackground(Color.decode(mainbackgroundcolour));
		
		BUGScheckbox = new JCheckBox();
		BUGScheckbox.setBackground(Color.decode(mainbackgroundcolour));
		
		LOCtextfield = new JTextField();
		LOCtextfield.setBackground(Color.decode(componentsbgcolour));
		LOCtextfield.setBorder(new LineBorder(Color.decode(bordercolour)));
		LOCtextfield.setToolTipText("Enter max value for LOC");
		LOCtextfield.setColumns(10);
		LOCtextfield.setText("LOC");
		LOCtextfield.setForeground(Color.decode(textcolour));
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
		WMCtextfield.setBorder(new LineBorder(Color.decode(bordercolour)));
		WMCtextfield.setColumns(10);
		WMCtextfield.setBackground(Color.decode(componentsbgcolour));
		WMCtextfield.setText("WMC");
		WMCtextfield.setForeground(Color.decode(textcolour));
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
		CBOtextfield.setBorder(new LineBorder(Color.decode(bordercolour)));
		CBOtextfield.setColumns(10);
		CBOtextfield.setBackground(Color.decode(componentsbgcolour));
		CBOtextfield.setText("CBO");
		CBOtextfield.setForeground(Color.decode(textcolour));
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
		LCOMtextfield.setBorder(new LineBorder(Color.decode(bordercolour)));
		LCOMtextfield.setColumns(10);
		LCOMtextfield.setBackground(Color.decode(componentsbgcolour));
		LCOMtextfield.setText("LCOM");
		LCOMtextfield.setForeground(Color.decode(textcolour));
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
		AVGCCtextfield.setBorder(new LineBorder(Color.decode(bordercolour)));
		AVGCCtextfield.setColumns(10);
		AVGCCtextfield.setBackground(Color.decode(componentsbgcolour));
		AVGCCtextfield.setText("AVGCC");
		AVGCCtextfield.setForeground(Color.decode(textcolour));
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
		FanIntextfield.setBorder(new LineBorder(Color.decode(bordercolour)));
		FanIntextfield.setColumns(10);
		FanIntextfield.setBackground(Color.decode(componentsbgcolour));
		FanIntextfield.setText("Fan In");
		FanIntextfield.setForeground(Color.decode(textcolour));
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
		FanOutfieldtext.setBorder(new LineBorder(Color.decode(bordercolour)));
		FanOutfieldtext.setColumns(10);
		FanOutfieldtext.setBackground(Color.decode(componentsbgcolour));
		FanOutfieldtext.setText("Fan Out");
		FanOutfieldtext.setForeground(Color.decode(textcolour));
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
		NOCtextfield.setBorder(new LineBorder(Color.decode(bordercolour)));
		NOCtextfield.setColumns(10);
		NOCtextfield.setBackground(Color.decode(componentsbgcolour));
		NOCtextfield.setText("NOC");
		NOCtextfield.setForeground(Color.decode(textcolour));
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
		DITtextfield.setBackground(Color.decode(componentsbgcolour));
		DITtextfield.setBorder(new LineBorder(Color.decode(bordercolour)));
		DITtextfield.setToolTipText("Enter max value for DIT");
		DITtextfield.setColumns(10);
		DITtextfield.setText("DIT");
		DITtextfield.setForeground(Color.decode(textcolour));
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
		BUGStextfield.setBackground(Color.decode(componentsbgcolour));
		BUGStextfield.setBorder(new LineBorder(Color.decode(bordercolour)));
		BUGStextfield.setToolTipText("Enter max value for BUGS");
		BUGStextfield.setColumns(10);
		BUGStextfield.setText("BUGS");
		BUGStextfield.setForeground(Color.decode(textcolour));
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
					
					if(LOCtextfield.getText().toCharArray().length >= 120) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Out of Boundary! Over 120 characters entered", "Error",
						        JOptionPane.ERROR_MESSAGE);
						lines = 0;
						LOCtextfield.setText("LOC"); 
						LOCcheckbox.setSelected(false);
						return;
					}
					
					if(LOCtextfield.getText().equals(null) || LOCtextfield.getText().isEmpty() || StringUtils.isBlank(LOCtextfield.getText()) || LOCtextfield.getText().equals("LOC")) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Value is missing! Enter a threshold value before ticking", "Error",
						        JOptionPane.ERROR_MESSAGE);
						lines = 0;
						LOCtextfield.setText("LOC"); 
						LOCcheckbox.setSelected(false);
						return;
					}
					
					try {
					lines = Integer.parseInt(LOCtextfield.getText());
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Wrong type entered! Enter an Integer and try again", "Error",
						        JOptionPane.ERROR_MESSAGE);
						lines = 0;
						LOCtextfield.setText("LOC"); 
						LOCcheckbox.setSelected(false);
						return;
					}
					
					LOCtextfield.setEditable(false);
					LOCtextfield.setBackground(Color.decode(selected));
					LOCtextfield.setForeground(Color.decode(selectedtxtcolour));
					WeMovin.SetLOC(lines);
				}
				else {
					lines = 0;
					LOCtextfield.setEditable(true);
					LOCtextfield.setBackground(Color.decode(componentsbgcolour));
					LOCtextfield.setForeground(Color.decode(textcolour));
					LOCtextfield.setText("LOC"); 
					WeMovin.SetLOC(lines);
				}
			}			
		});
		
		WMCcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(WMCcheckbox.isSelected()){
					if(WMCtextfield.getText().toCharArray().length >= 120) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Out of Boundary! Over 120 characters entered", "Error",
						        JOptionPane.ERROR_MESSAGE);
						methods = 0;
						WMCtextfield.setText("WMC"); 
						WMCcheckbox.setSelected(false);
						return;
					}
					
					if(WMCtextfield.getText().equals(null) || WMCtextfield.getText().isEmpty() || StringUtils.isBlank(WMCtextfield.getText()) || WMCtextfield.getText().equals("WMC")) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Value is missing! Enter a threshold value before ticking", "Error",
						        JOptionPane.ERROR_MESSAGE);
						methods = 0;
						WMCtextfield.setText("WMC"); 
						WMCcheckbox.setSelected(false);
						return;
					}
					
					try {
						methods = Integer.parseInt(WMCtextfield.getText());
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Wrong type entered! Enter an Integer and try again", "Error",
						        JOptionPane.ERROR_MESSAGE);
						methods = 0;
						WMCtextfield.setText("WMC"); 
						WMCcheckbox.setSelected(false);
						return;
					}
					
					WMCtextfield.setEditable(false);
					WMCtextfield.setBackground(Color.decode(selected));
					WMCtextfield.setForeground(Color.decode(selectedtxtcolour));
					WeMovin.SetWMC(methods);
				}
				else {
					methods = 0;
					WMCtextfield.setEditable(true);
					WMCtextfield.setBackground(Color.decode(componentsbgcolour));
					WMCtextfield.setForeground(Color.decode(textcolour));
					WMCtextfield.setText("WMC");
					WeMovin.SetWMC(methods);
				}
			}		
		});
		
		CBOcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CBOcheckbox.isSelected()){
					if(CBOtextfield.getText().toCharArray().length >= 120) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Out of Boundary! Over 120 characters entered", "Error",
						        JOptionPane.ERROR_MESSAGE);
						cbo = 0;
						CBOtextfield.setText("CBO"); 
						CBOcheckbox.setSelected(false);
						return;
					}
					
					if(CBOtextfield.getText().equals(null) || CBOtextfield.getText().isEmpty() || StringUtils.isBlank(CBOtextfield.getText()) || CBOtextfield.getText().equals("CBO")) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Value is missing! Enter a threshold value before ticking", "Error",
						        JOptionPane.ERROR_MESSAGE);
						cbo = 0;
						CBOtextfield.setText("CBO"); 
						CBOcheckbox.setSelected(false);
						return;
					}
					
					try {
						cbo = Integer.parseInt(CBOtextfield.getText());
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Wrong type entered! Enter an Integer and try again", "Error",
						        JOptionPane.ERROR_MESSAGE);
						cbo = 0;
						CBOtextfield.setText("CBO"); 
						CBOcheckbox.setSelected(false);
						return;
					}
					
					CBOtextfield.setEditable(false);
					CBOtextfield.setBackground(Color.decode(selected));
					CBOtextfield.setForeground(Color.decode(selectedtxtcolour));
					WeMovin.SetCBO(cbo);
				}
				else {
					cbo = 0;
					CBOtextfield.setEditable(true);
					CBOtextfield.setBackground(Color.decode(componentsbgcolour));
					CBOtextfield.setForeground(Color.decode(textcolour));
					CBOtextfield.setText("CBO");
					WeMovin.SetCBO(cbo);
				}
			}			
		});

		LCOMcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(LCOMcheckbox.isSelected()){
					
						if(LCOMtextfield.getText().toCharArray().length >= 120) {
							JOptionPane.showMessageDialog(new JFrame(), "Error: Out of Boundary! Over 120 characters entered", "Error",
							        JOptionPane.ERROR_MESSAGE);
							lcom = 0;
							LCOMtextfield.setText("LCOM"); 
							LCOMcheckbox.setSelected(false);
							return;
						}
						
						if(LCOMtextfield.getText().equals(null) || LCOMtextfield.getText().isEmpty() || StringUtils.isBlank(LCOMtextfield.getText()) || LCOMtextfield.getText().equals("LCOM")) {
							JOptionPane.showMessageDialog(new JFrame(), "Error: Value is missing! Enter a threshold value before ticking", "Error",
							        JOptionPane.ERROR_MESSAGE);
							lcom = 0;
							LCOMtextfield.setText("LCOM"); 
							LCOMcheckbox.setSelected(false);
							return;
						}
						
						try {
							lcom = Integer.parseInt(LCOMtextfield.getText());
						}
						catch(Exception ex) {
							JOptionPane.showMessageDialog(new JFrame(), "Error: Wrong type entered! Enter an Integer and try again", "Error",
							        JOptionPane.ERROR_MESSAGE);
							lcom = 0;
							LCOMtextfield.setText("LCOM"); 
							LCOMcheckbox.setSelected(false);
							return;
						}
					
					LCOMtextfield.setEditable(false);
					LCOMtextfield.setBackground(Color.decode(selected));
					LCOMtextfield.setForeground(Color.decode(selectedtxtcolour));
					WeMovin.SetLCOM(lcom);
				}
				else {
					lcom = 0;
					LCOMtextfield.setEditable(true);
					LCOMtextfield.setBackground(Color.decode(componentsbgcolour));
					LCOMtextfield.setForeground(Color.decode(textcolour));
					LCOMtextfield.setText("LCOM");
					WeMovin.SetLCOM(lcom);
				}
			}
		});

		AVGCCcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(AVGCCcheckbox.isSelected()){
					
					if(AVGCCtextfield.getText().toCharArray().length >= 120) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Out of Boundary! Over 120 characters entered", "Error",
						        JOptionPane.ERROR_MESSAGE);
						avgcc = 0.0;
						AVGCCtextfield.setText("AVGCC"); 
						AVGCCcheckbox.setSelected(false);
						return;
					}
					
					if(AVGCCtextfield.getText().equals(null) || AVGCCtextfield.getText().isEmpty() || StringUtils.isBlank(AVGCCtextfield.getText()) || AVGCCtextfield.getText().equals("AVGCC")) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Value is missing! Enter a threshold value before ticking", "Error",
						        JOptionPane.ERROR_MESSAGE);
						avgcc = 0.0;
						AVGCCtextfield.setText("AVGCC"); 
						AVGCCcheckbox.setSelected(false);
						return;
					}
					
					try {
						avgcc = Double.parseDouble(AVGCCtextfield.getText());
						System.out.println("In the MainFrame is: "+avgcc);
//						avgcc = Integer.parseInt(AVGCCtextfield.getText());
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Wrong type entered! Enter an Integer and try again", "Error",
						        JOptionPane.ERROR_MESSAGE);
						avgcc = 0.0;
						AVGCCtextfield.setText("AVGCC"); 
						AVGCCcheckbox.setSelected(false);
						return;
					}
					
					AVGCCtextfield.setEditable(false);
					AVGCCtextfield.setBackground(Color.decode(selected));
					AVGCCtextfield.setForeground(Color.decode(selectedtxtcolour));
					WeMovin.SetAVGCC(avgcc);
				}
				else {
					avgcc = 0.0;
					AVGCCtextfield.setEditable(true);
					AVGCCtextfield.setBackground(Color.decode(componentsbgcolour));
					AVGCCtextfield.setForeground(Color.decode(textcolour));
					AVGCCtextfield.setText("AVGCC");
					WeMovin.SetAVGCC(avgcc);
				}
			}	
		});

		FanIncheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(FanIncheckbox.isSelected()){
					
					if(FanIntextfield.getText().toCharArray().length >= 120) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Out of Boundary! Over 120 characters entered", "Error",
						        JOptionPane.ERROR_MESSAGE);
						fanin = 0;
						FanIntextfield.setText("Fan In"); 
						FanIncheckbox.setSelected(false);
						return;
					}
					
					if(FanIntextfield.getText().equals(null) || FanIntextfield.getText().isEmpty() || StringUtils.isBlank(FanIntextfield.getText()) || FanIntextfield.getText().equals("Fan In")) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Value is missing! Enter a threshold value before ticking", "Error",
						        JOptionPane.ERROR_MESSAGE);
						fanin = 0;
						FanIntextfield.setText("Fan In"); 
						FanIncheckbox.setSelected(false);
						return;
					}
					
					try {
						fanin = Integer.parseInt(FanIntextfield.getText());
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Wrong type entered! Enter an Integer and try again", "Error",
						        JOptionPane.ERROR_MESSAGE);
						fanin = 0;
						FanIntextfield.setText("Fan In"); 
						FanIncheckbox.setSelected(false);
						return;
					}
					
					FanIntextfield.setEditable(false);
					FanIntextfield.setBackground(Color.decode(selected));
					FanIntextfield.setForeground(Color.decode(selectedtxtcolour));
					WeMovin.SetFanIn(fanin);
				}
				else {
					fanin = 0;
					FanIntextfield.setEditable(true);
					FanIntextfield.setBackground(Color.decode(componentsbgcolour));
					FanIntextfield.setForeground(Color.decode(textcolour));
					FanIntextfield.setText("FanIn");
					WeMovin.SetFanIn(fanin);
				}
			}	
		});

		FanOutcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(FanOutcheckbox.isSelected()){
					
					if(FanOutfieldtext.getText().toCharArray().length >= 120) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Out of Boundary! Over 120 characters entered", "Error",
						        JOptionPane.ERROR_MESSAGE);
						fanout = 0;
						FanOutfieldtext.setText("Fan Out"); 
						FanOutcheckbox.setSelected(false);
						return;
					}
					
					if(FanOutfieldtext.getText().equals(null) || FanOutfieldtext.getText().isEmpty() || StringUtils.isBlank(FanOutfieldtext.getText()) || FanOutfieldtext.getText().equals("Fan Out")) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Value is missing! Enter a threshold value before ticking", "Error",
						        JOptionPane.ERROR_MESSAGE);
						fanout = 0;
						FanOutfieldtext.setText("Fan Out"); 
						FanOutcheckbox.setSelected(false);
						return;
					}
					
					try {
						fanout = Integer.parseInt(FanOutfieldtext.getText());
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Wrong type entered! Enter an Integer and try again", "Error",
						        JOptionPane.ERROR_MESSAGE);
						fanout = 0;
						FanOutfieldtext.setText("Fan Out"); 
						FanOutcheckbox.setSelected(false);
						return;
					}
					
					FanOutfieldtext.setEditable(false);
					FanOutfieldtext.setBackground(Color.decode(selected));
					FanOutfieldtext.setForeground(Color.decode(selectedtxtcolour));
					WeMovin.SetFanOut(fanout);
				}
				else {
					fanout = 0;
					FanOutfieldtext.setEditable(true);
					FanOutfieldtext.setBackground(Color.decode(componentsbgcolour));
					FanOutfieldtext.setForeground(Color.decode(textcolour));
					FanOutfieldtext.setText("FanOut");
					WeMovin.SetFanOut(fanout);
				}
			}
			
		});

		NOCcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(NOCcheckbox.isSelected()){
					
					if(NOCtextfield.getText().toCharArray().length >= 120) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Out of Boundary! Over 120 characters entered", "Error",
						        JOptionPane.ERROR_MESSAGE);
						noc = 0;
						NOCtextfield.setText("NOC"); 
						NOCcheckbox.setSelected(false);
						return;
					}
					
					if(NOCtextfield.getText().equals(null) || NOCtextfield.getText().isEmpty() || StringUtils.isBlank(NOCtextfield.getText()) || NOCtextfield.getText().equals("NOC")) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Value is missing! Enter a threshold value before ticking", "Error",
						        JOptionPane.ERROR_MESSAGE);
						noc = 0;
						NOCtextfield.setText("NOC"); 
						NOCcheckbox.setSelected(false);
						return;
					}
					
					try {
						noc = Integer.parseInt(NOCtextfield.getText());
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Wrong type entered! Enter an Integer and try again", "Error",
						        JOptionPane.ERROR_MESSAGE);
						noc = 0;
						NOCtextfield.setText("NOC"); 
						NOCcheckbox.setSelected(false);
						return;
					}
					
					NOCtextfield.setEditable(false);
					NOCtextfield.setBackground(Color.decode(selected));
					NOCtextfield.setForeground(Color.decode(selectedtxtcolour));
					WeMovin.SetNOC(noc);
				}
				else {
					noc = 0;
					NOCtextfield.setEditable(true);
					NOCtextfield.setBackground(Color.decode(componentsbgcolour));
					NOCtextfield.setForeground(Color.decode(textcolour));
					NOCtextfield.setText("NOC");
					WeMovin.SetNOC(noc);
				}
			}	
		});
		
		DITcheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(DITcheckbox.isSelected()){
					
					if(DITtextfield.getText().toCharArray().length >= 120) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Out of Boundary! Over 120 characters entered", "Error",
						        JOptionPane.ERROR_MESSAGE);
						dit = 0;
						DITtextfield.setText("DIT"); 
						DITcheckbox.setSelected(false);
						return;
					}
					
					if(DITtextfield.getText().equals(null) || DITtextfield.getText().isEmpty() || StringUtils.isBlank(DITtextfield.getText()) || DITtextfield.getText().equals("DIT")) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Value is missing! Enter a threshold value before ticking", "Error",
						        JOptionPane.ERROR_MESSAGE);
						dit = 0;
						DITtextfield.setText("DIT"); 
						DITcheckbox.setSelected(false);
						return;
					}
					
					try {
						dit = Integer.parseInt(DITtextfield.getText());
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Wrong type entered! Enter an Integer and try again", "Error",
						        JOptionPane.ERROR_MESSAGE);
						dit = 0;
						DITtextfield.setText("DIT"); 
						DITcheckbox.setSelected(false);
						return;
					}
					
					DITtextfield.setEditable(false);
					DITtextfield.setBackground(Color.decode(selected));
					DITtextfield.setForeground(Color.decode(selectedtxtcolour));
					WeMovin.SetDIT(dit);
				}
				else {
					dit = 0;
					DITtextfield.setEditable(true);
					DITtextfield.setBackground(Color.decode(componentsbgcolour));
					DITtextfield.setForeground(Color.decode(textcolour));
					DITtextfield.setText("DIT"); 
					WeMovin.SetDIT(dit);
				}
			}			
		});
		
		BUGScheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(BUGScheckbox.isSelected()){
					
					if(BUGStextfield.getText().toCharArray().length >= 120) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Out of Boundary! Over 120 characters entered", "Error",
						        JOptionPane.ERROR_MESSAGE);
						bug1 = 0;
						BUGStextfield.setText("BUGS"); 
						BUGScheckbox.setSelected(false);
						return;
					}
					
					if(BUGStextfield.getText().equals(null) || BUGStextfield.getText().isEmpty() || StringUtils.isBlank(BUGStextfield.getText()) || BUGStextfield.getText().equals("BUGS")) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Value is missing! Enter a threshold value before ticking", "Error",
						        JOptionPane.ERROR_MESSAGE);
						bug1 = 0;
						BUGStextfield.setText("BUGS"); 
						BUGScheckbox.setSelected(false);
						return;
					}
					
					try {
						bug1 = Integer.parseInt(BUGStextfield.getText());
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(new JFrame(), "Error: Wrong type entered! Enter an Integer and try again", "Error",
						        JOptionPane.ERROR_MESSAGE);
						bug1 = 0;
						BUGStextfield.setText("BUGS"); 
						BUGScheckbox.setSelected(false);
						return;
					}
					
					BUGStextfield.setEditable(false);
					BUGStextfield.setBackground(Color.decode(selected));
					BUGStextfield.setForeground(Color.decode(selectedtxtcolour));
					WeMovin.SetB(bug1);
				}
				else {
					bug1 = 0;
					BUGStextfield.setEditable(true);
					BUGStextfield.setBackground(Color.decode(componentsbgcolour));
					BUGStextfield.setForeground(Color.decode(textcolour));
					BUGStextfield.setText("BUGS"); 
					WeMovin.SetB(bug1);
				}
			}			
		});
		
		panel = new JPanel(); 
		panel.setBackground(Color.decode(componentsbgcolour));
		panel.setBorder(new LineBorder(Color.decode(bordercolour)));
		
//		scrollPane_1 = new JScrollPane();
		scrollPane_1.getViewport().setBackground(Color.decode(componentsbgcolour));
		scrollPane_1.getViewport().setForeground(Color.decode(textcolour));
		scrollPane_1.setBorder(new LineBorder(Color.decode(bordercolour)));
		
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnbinfolder, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
										.addComponent(btnsrcfolder, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
									.addGap(18))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(bintextpane, GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
								.addComponent(srctextpane, GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(165)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(DITcheckbox, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
														.addComponent(CBOcheckbox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(LOCcheckbox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(WMCcheckbox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(LCOMcheckbox))
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(WMCtextfield, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
														.addComponent(LOCtextfield, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
														.addComponent(DITtextfield, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
														.addComponent(LCOMtextfield, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
														.addComponent(CBOtextfield, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
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
																.addComponent(NOCtextfield, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
																.addComponent(FanOutfieldtext, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
																.addComponent(FanIntextfield, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
																.addComponent(AVGCCtextfield, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
															.addGap(65))
														.addComponent(FanIncheckbox)
														.addComponent(FanOutcheckbox)))
												.addComponent(metricsinfo)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnexcelfile, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
											.addGap(98)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(bugs, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
										.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
										.addComponent(btncorrel)))))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
						.addComponent(btnanalyser, Alignment.LEADING))
					.addContainerGap())
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
					.addGap(42)
					.addComponent(metricsinfo)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(AVGCCtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(bugs, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
									.addComponent(AVGCCcheckbox))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(FanIntextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(FanIncheckbox))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(FanOutcheckbox)
									.addComponent(FanOutfieldtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(NOCtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(NOCcheckbox))
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
									.addComponent(DITcheckbox)))
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnanalyser, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnexcelfile, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btncorrel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		contentPane.setBackground(Color.decode(mainbackgroundcolour));

		
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
	    		  instructions.setText("The following classes:"+"\n"+classnames+"\n"+"Have bad cohesion, this can mean that some methods are not needed in these classes"+"\n"+"If the value is 0: It means the cohesion is low and bad"+"\n"+"If the value is 2: It means that the number of attributes are higher than the methods in the class"+"\n"+Output.getlcomoutput());
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
