package RefactOK;
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
	            
	            else if (column == 1 && (int) value > 100) { 
//	            	setForeground(Color.decode("#57596D"));
	                setForeground(Color.RED);
	                setBackground(Color.decode("#57596D"));
	            } 
	            
	            
	            else if (column == 6 && (double) value > 5) {
	            	setForeground(Color.RED);
	            	setBackground(Color.decode("#57596D"));
	            }
	            
	            else if (column == 7 && (int) value > 5) {
	            	setForeground(Color.RED);
	            	setBackground(Color.decode("#57596D"));
	            }
	            
	            else if (column == 8 && (int) value > 5) {
	            	setForeground(Color.RED);
	            	setBackground(Color.decode("#57596D"));
	            }
	            
	            else if (column == 9 && (int) value > 10) {
	            	setForeground(Color.RED);
	            	setBackground(Color.decode("#57596D"));
	            }
	            
	            else if (column == 10 && (int) value > 15) {
	            	setForeground(Color.RED);
	            	setBackground(Color.decode("#57596D"));
	            }
	            
	            else if (column == 11 && (int) value != 1) {
	            	setForeground(Color.RED);
	            	setBackground(Color.decode("#57596D"));
	            }
	            
	            else if (column == 12 && (int) value > 5) {
	            	setForeground(Color.RED);
	            	setBackground(Color.decode("#57596D"));
	            }
	            
	            else if (column == 13 && (int) value > 5) {
	            	setForeground(Color.RED);
	            	setBackground(Color.decode("#57596D"));
	            }
	            
	            else if(column == 14 && (int) value > 5) {
	            	setForeground(Color.RED);
	            	setBackground(Color.decode("#57596D"));
	            }
	            else if (!isSelected){
	            	setForeground(Color.decode("#58D9FF"));
	                setBackground(Color.decode("#57596D"));
	            }

	            return this;
	        }
			
	    }

