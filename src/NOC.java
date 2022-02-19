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

	public static int noc(File file[], File checkfile, int children)
	        throws IOException {
		
			for(int i = 0; i < file.length; i++) {
				if(!file[i].getName().equals(checkfile.getName())) {
					InputStream in = new FileInputStream(file[i]);
					ClassParser parser = new ClassParser(in, file[i].getName());	        
					JavaClass clazz = parser.parse();
	        
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
	
	public static int DIT(File checkfile, int dit, File[] fil)
	        throws IOException {
	
		InputStream sin = new FileInputStream(checkfile);       
        ClassParser sparser = new ClassParser(sin, checkfile.getName());        
        JavaClass sclazz = sparser.parse();

        int ldit = 0;
        
		for(int i = 0; i < fil.length; i++) {
		if(sclazz.getSuperclassName().equals(fil[i].getName().replaceFirst("[.][^.]+$", "")) || sclazz.getSuperclassName().endsWith("."+fil[i].getName().replaceFirst("[.][^.]+$", ""))) {
			String temp = sclazz.getClassName() + " is the child of ";
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
