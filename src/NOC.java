import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;

public class NOC {

	public static int noc(File file[], File checkfile, int children)
	        throws IOException {
		
			for(int i = 0; i < file.length; i++) {
				if(!file[i].getName().equals(checkfile.getName())) {
					InputStream in = new FileInputStream(file[i]);
					ClassParser parser = new ClassParser(in, file[i].getName());	        
					JavaClass clazz = parser.parse();
	        
	        		if(clazz.getSuperclassName().equals(checkfile.getName().replaceFirst("[.][^.]+$", ""))) {
					children++;
	        		}
			}
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
		if(sclazz.getSuperclassName().equals(fil[i].getName().replaceFirst("[.][^.]+$", ""))) {
			sin = new FileInputStream(fil[i]);
			sparser = new ClassParser(sin, fil[i].getName());
			sclazz = sparser.parse();
			ldit++;
			i=-1;
		}
		}
		
		dit = ldit;
		
		return dit;
		
	}
	
	
}
