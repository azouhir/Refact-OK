package RefactOK;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class DialogWait {
	
	private JDialog dialog;

	//Method to create new dialog box that will tell users to wait while analysis is happening
	public void makeWait(String msg, ActionEvent evt) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

	    Window win = SwingUtilities.getWindowAncestor((AbstractButton) evt.getSource());
	    //set dialog box window's settings
	    dialog = new JDialog(win, msg, JDialog.ModalityType.APPLICATION_MODAL);
	    
	    //Don't allow users to close dialog box
	    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 
	    
	    //set dialog box options (running progress baar, title, message, ...)
	    JProgressBar progressBar = new JProgressBar();
	    progressBar.setIndeterminate(true);
	    JPanel panel = new JPanel(new BorderLayout());
	    panel.add(progressBar, BorderLayout.CENTER);
	    panel.add(new JLabel("Analysing Classes..."+"\n"+"This process may take a while!"), BorderLayout.PAGE_START);
	    panel.setBackground(Color.decode("#57596D"));
		panel.setBorder(new LineBorder(Color.decode("#58D9FF")));
		panel.setForeground(Color.decode("#58D9FF"));
		panel.setOpaque(false);
	    dialog.add(panel);
	    dialog.pack();
	    dialog.setLocationRelativeTo(win);
	    dialog.setVisible(true);
	   }

	   //method to automatically close dialog box
	   public void close() {
	       dialog.dispose();
	   }
}
