package RefactOK;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class StatusColumnCellRenderer extends DefaultTableCellRenderer {

	private String bgcolour = "#efd3d7";
	private String frontcolour = "#da1e37";
	private String textcolour = "#FFFFFF";
	private String selected = "#d66853";
			@Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	            Font font = new Font("Dialog", Font.BOLD, 10);
	            
	            if(isSelected) {
	            	setForeground(Color.decode(textcolour));
	                setBackground(Color.decode(selected));
	            }
	            
	            else if (column == 1 && (int) value > 100) { 
//	            	setForeground(Color.decode(frontcolour));
	                setForeground(Color.decode(frontcolour));
	                setBackground(Color.decode(bgcolour));
	                setFont(font);
	            } 
	            
	            
	            else if (column == 6 && (double) value > 5) {
	            	setForeground(Color.decode(frontcolour));
	            	setBackground(Color.decode(bgcolour));
	            	setFont(font);
	            }
	            
	            else if (column == 7 && (int) value > 5) {
	            	setForeground(Color.decode(frontcolour));
	            	setBackground(Color.decode(bgcolour));
	            	setFont(font);
	            }
	            
	            else if (column == 8 && (int) value > 5) {
	            	setForeground(Color.decode(frontcolour));
	            	setBackground(Color.decode(bgcolour));
	            	setFont(font);
	            }
	            
	            else if (column == 9 && (int) value > 10) {
	            	setForeground(Color.decode(frontcolour));
	            	setBackground(Color.decode(bgcolour));
	            	setFont(font);
	            }
	            
	            else if (column == 10 && (int) value > 15) {
	            	setForeground(Color.decode(frontcolour));
	            	setBackground(Color.decode(bgcolour));
	            	setFont(font);
	            }
	            
	            else if (column == 11 && (int) value != 1) {
	            	setForeground(Color.decode(frontcolour));
	            	setBackground(Color.decode(bgcolour));
	            	setFont(font);
	            }
	            
	            else if (column == 12 && (int) value > 5) {
	            	setForeground(Color.decode(frontcolour));
	            	setBackground(Color.decode(bgcolour));
	            	setFont(font);
	            }
	            
	            else if (column == 13 && (int) value > 5) {
	            	setForeground(Color.decode(frontcolour));
	            	setBackground(Color.decode(bgcolour));
	            	setFont(font);
	            }
	            
	            else if(column == 14 && (int) value > 5) {
	            	setForeground(Color.decode(frontcolour));
	            	setBackground(Color.decode(bgcolour));
	            	setFont(font);
	            }
	            else if (!isSelected){
	            	setForeground(Color.decode("#11151c"));
	                setBackground(Color.decode(bgcolour));
	            }

	            return this;
	        }
			
	    }

