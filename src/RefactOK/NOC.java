package RefactOK;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;

public class NOC {
	
	static StringBuffer nocput = new StringBuffer();
	static StringBuffer ditput = new StringBuffer();

	//method to calculate NOC, loops through all classes in the folder and checks for classes with parent class name equal current class name
	public static int noc(File file[], File checkfile, int children)
	        throws IOException {
		
			for(int i = 0; i < file.length; i++) {
				
				//visit each file in the folder except for current file
				if(!file[i].getName().equals(checkfile.getName())) {
					InputStream in = new FileInputStream(file[i]);
					//Create new parser - specify a class
					ClassParser parser = new ClassParser(in, file[i].getName());
					//parse given class to retrieve info
					JavaClass clazz = parser.parse();
	        
					//find all references to current class when a superclass is declared in class being analysed
	        		if(clazz.getSuperclassName().equals(checkfile.getName().replaceFirst("[.][^.]+$", "")) || clazz.getSuperclassName().endsWith("."+checkfile.getName().replaceFirst("[.][^.]+$", ""))) {
					
	        			String temp = "Class " + checkfile.getName() + " is the parent of:" + "\n" + file[i].getName();
	        			nocput.append(temp);
	    				nocput.append("\n");
	    				nocput.append("");
	        			children++;
	        		}
			}
			}
			
			if(children > 5) {
				Output.setnocoutput(nocput.toString());
			}
		return children;
	}
	
	//SAME PRINCIPLE AS NOC APPLY
	//Check if current class has a parent class and if so change the 
	//current class being analysed to the parent of the class.
	//repeat the process until you find class 0 the one that doesn't inherit from
	//any other class
	public static int DIT(File checkfile, int dit, File[] fil)
	        throws IOException {
	
		InputStream sin = new FileInputStream(checkfile);       
        ClassParser sparser = new ClassParser(sin, checkfile.getName());        
        JavaClass sclazz = sparser.parse();

        int ldit = 0;
        
		for(int i = 0; i < fil.length; i++) {
		
		//Find if class analysed has a parent class
		if(sclazz.getSuperclassName().equals(fil[i].getName().replaceFirst("[.][^.]+$", "")) || sclazz.getSuperclassName().endsWith("."+fil[i].getName().replaceFirst("[.][^.]+$", ""))) {
			String temp = sclazz.getClassName() + " is the child of ";
			//if so change the current class to father class and repeat the process to find DIT level
			sin = new FileInputStream(fil[i]);
			sparser = new ClassParser(sin, fil[i].getName());
			sclazz = sparser.parse();
			ldit++;
			String temp1 = sclazz.getClassName();
			ditput.append(temp + temp1);
			ditput.append("\n");
			ditput.append("");
			i=-1;
		}
		}
		
		dit = ldit;
		
		if(dit > 5) {
			Output.setditoutput(ditput.toString());
		}
		
		return dit;
		
	}
	
	
}
