import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class StatusColumnCellRenderer extends DefaultTableCellRenderer {

			@Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	            if(isSelected) {
	            	setForeground(Color.decode("#57596D"));
	                setBackground(Color.decode("#58D9FF"));
	            }
	            
	            else if ((int) value > 100 && column == 1) { 
//	            	setForeground(Color.decode("#57596D"));
	                setForeground(Color.RED);
	            } 
	            
	            else if ((int) value > 10 && column == 6) {
	            	setForeground(Color.RED);
	            }
	            
	            else if ((int) value > 5 && column == 7) {
	            	setForeground(Color.RED);
	            }
	            
	            else if ((int) value > 5 && column == 8) {
	            	setForeground(Color.RED);
	            }
	            
	            else if ((int) value > 10 && column == 9) {
	            	setForeground(Color.RED);
	            }
	            
	            else if ((int) value > 15 && column == 10) {
	            	setForeground(Color.RED);
	            }
	            
	            else if ((int) value != 1 && column == 11) {
	            	setForeground(Color.RED);
	            }
	            
	            else if ((int) value > 5 && column == 12) {
	            	setForeground(Color.RED);
	            }
	            
	            else if ((int) value > 5 && column == 13) {
	            	setForeground(Color.RED);
	            }
	            
	            else if((int) value > 5 && column == 14) {
	            	setForeground(Color.RED);
	            }
	            
	            else {
	            	setBackground(Color.decode("#57596D"));
	        		setForeground(Color.decode("#58D9FF"));
	                
	            }
	            return this;
	        }
	    }

