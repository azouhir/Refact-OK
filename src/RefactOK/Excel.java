package RefactOK;
import  java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JTable;

import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;

public class Excel {
	    
		//ensure table is filled with values before attempting to produce an excel file
	    public static boolean validateExcel(JTable table) {
	    	
	    	if(table.getRowCount() <= 0) {
	    		return false;
	    	}
	    	
	    	return true;
	    }
	    
	    //create excel file
	    public static void CreateFile2(JTable table) {
	    	
	    	try {
	    	
	    	//set file's name and location
	    	String filename = "C:\\Users\\Public\\Documents\\Metrics.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            
            //create new spreadsheet
            HSSFSheet sheet = workbook.createSheet("FirstSheet");
            
            //assign headers
            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Class Name");
            rowhead.createCell(1).setCellValue("LOC");
            rowhead.createCell(2).setCellValue("Blank Lines");
            rowhead.createCell(3).setCellValue("Single Line Comments");
            rowhead.createCell(4).setCellValue("Multi Line Comments");
            rowhead.createCell(5).setCellValue("Identation Count");
            rowhead.createCell(6).setCellValue("AVGCC");
            rowhead.createCell(7).setCellValue("Fan In");
            rowhead.createCell(8).setCellValue("Fan Out");
            rowhead.createCell(9).setCellValue("CBO");
            rowhead.createCell(10).setCellValue("WMC");
            rowhead.createCell(11).setCellValue("LCOM4");
            rowhead.createCell(12).setCellValue("NOC");
            rowhead.createCell(13).setCellValue("DIT");
            rowhead.createCell(14).setCellValue("BUGS");
            
            int loop = 1;
            
            //loop through table fo metrics
            for(int i = 0; i < table.getRowCount(); i++) {
            	
            	//assign values to each row
            	 HSSFRow row = sheet.createRow((short)loop);
 	            row.createCell(0).setCellValue(""+table.getValueAt(i, 0));
 	            row.createCell(1).setCellValue(""+table.getValueAt(i, 1));
 	            row.createCell(2).setCellValue(""+table.getValueAt(i, 2));
 	            row.createCell(3).setCellValue(""+table.getValueAt(i, 3));
 	            row.createCell(4).setCellValue(""+table.getValueAt(i, 4));
 	            row.createCell(5).setCellValue(""+table.getValueAt(i, 5));
 	            row.createCell(6).setCellValue(""+table.getValueAt(i, 6));
 	            row.createCell(7).setCellValue(""+table.getValueAt(i, 7));
 	            row.createCell(8).setCellValue(""+table.getValueAt(i, 8));
 	            row.createCell(9).setCellValue(""+table.getValueAt(i, 9));
 	            row.createCell(10).setCellValue(""+table.getValueAt(i, 10));
 	            row.createCell(11).setCellValue(""+table.getValueAt(i, 11));
 	            row.createCell(12).setCellValue(""+table.getValueAt(i, 12));
 	           row.createCell(13).setCellValue(""+table.getValueAt(i, 13));
 	          row.createCell(14).setCellValue(""+table.getValueAt(i, 14));
 	            loop++; 	            
            }
            
            //output the stream and close the workbook
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Your excel file has been generated!");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename);
	    	

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
	    }
	    }

