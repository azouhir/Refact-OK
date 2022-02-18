import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;

public class MainScreen extends JFrame {
	private JTextField srctextpane;
	private JTextField bintextpane;
	private JTextField textField_2;
	private JTextField LOCtextfield;
	private JTextField WMCtextfield;
	private JTextField CBOtextfield;
	private JTextField LCOMtextfield;
	private JTextField AVGCCtextfield;
	private JTextField FanIntextfield;
	private JTextField FanOutfieldtext;
	private JTextField NOCtextfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 862, 460);
		
		JButton btnsrcfolder = new JButton("New button");
		
		JButton btnbinfolder = new JButton("New button");
		
		srctextpane = new JTextField();
		srctextpane.setColumns(10);
		
		bintextpane = new JTextField();
		bintextpane.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnanalyser = new JButton("New button");
		
		JButton btnexcelfile = new JButton("New button");
		
		JButton btncorrel = new JButton("New button");
		
		JCheckBox AVGCCcheckbox = new JCheckBox("New check box");
		
		JCheckBox FanIncheckbox = new JCheckBox("New check box");
		
		JCheckBox FanOutcheckbox = new JCheckBox("New check box");
		
		JCheckBox NOCcheckbox = new JCheckBox("New check box");
		
		JCheckBox LOCcheckbox = new JCheckBox("New check box");
		
		JCheckBox WMCcheckbox = new JCheckBox("New check box");
		
		JCheckBox CBOcheckbox = new JCheckBox("New check box");
		
		JCheckBox LCOMcheckbox = new JCheckBox("New check box");
		
		LOCtextfield = new JTextField();
		LOCtextfield.setColumns(10);
		
		WMCtextfield = new JTextField();
		WMCtextfield.setColumns(10);
		
		CBOtextfield = new JTextField();
		CBOtextfield.setColumns(10);
		
		LCOMtextfield = new JTextField();
		LCOMtextfield.setColumns(10);
		
		AVGCCtextfield = new JTextField();
		AVGCCtextfield.setColumns(10);
		
		FanIntextfield = new JTextField();
		FanIntextfield.setColumns(10);
		
		FanOutfieldtext = new JTextField();
		FanOutfieldtext.setColumns(10);
		
		NOCtextfield = new JTextField();
		NOCtextfield.setColumns(10);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnsrcfolder)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(srctextpane, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnbinfolder)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(bintextpane, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(LCOMcheckbox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(LCOMtextfield, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(CBOcheckbox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(CBOtextfield, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(WMCcheckbox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(WMCtextfield, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(LOCcheckbox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(LOCtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(btnanalyser)
									.addGap(246)
									.addComponent(btnexcelfile)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(NOCcheckbox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(NOCtextfield, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(AVGCCcheckbox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(FanIncheckbox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(AVGCCtextfield, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
											.addComponent(FanIntextfield, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(FanOutcheckbox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(FanOutfieldtext, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))))
							.addGap(68)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btncorrel, Alignment.TRAILING)
								.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(textField_2)
									.addComponent(panel, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnsrcfolder)
						.addComponent(srctextpane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnbinfolder)
						.addComponent(bintextpane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(AVGCCcheckbox)
								.addComponent(LOCcheckbox)
								.addComponent(LOCtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(AVGCCtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(FanIncheckbox)
										.addComponent(WMCcheckbox)
										.addComponent(WMCtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(FanIntextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(CBOcheckbox)
										.addComponent(FanOutcheckbox)
										.addComponent(CBOtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(FanOutfieldtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
											.addComponent(LCOMcheckbox)
											.addComponent(NOCcheckbox)
											.addComponent(NOCtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(LCOMtextfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnanalyser)
						.addComponent(btnexcelfile)
						.addComponent(btncorrel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
}
