import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.FieldInstruction;
import org.apache.bcel.generic.FieldOrMethod;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;

public class CBO {
	
	public static void main(String[] args) throws IOException {

		
	}
	
	

//First let's get all the classes
	public static ArrayList fanout(File file, Map<String, Set<String>> refMap, File[] fil)
	        throws IOException {
	    
		int fanout = 0;
		int fanin = 0;
		ArrayList<String> ref1 = new ArrayList<>();
		
		ArrayList<Integer> CBO = new ArrayList<>();
		
		try (InputStream in = new FileInputStream(file)) {
	        ClassParser parser = new ClassParser(in, file.getName());
	        JavaClass clazz = parser.parse();
	        String className = clazz.getClassName();
	        Set<String> referees = new HashSet<>();
	        ConstantPoolGen cp = new ConstantPoolGen(clazz.getConstantPool());
	        for (org.apache.bcel.classfile.Method method: clazz.getMethods()) {
	            Code code = method.getCode();
	            InstructionList instrs = new InstructionList(code.getCode());
	            for (InstructionHandle ih: instrs) {
	                Instruction instr = ih.getInstruction();
	                if (instr instanceof FieldOrMethod) {
	                	FieldOrMethod ref = (FieldOrMethod) instr;
	              //    FieldOrMethod ref = (FieldInstruction)instr;
	                    String cn = ref.getClassName(cp);
	                    if (!cn.equals(className)) {
	                        referees.add(cn);
	                        ref1.add(cn);
	                    }
	                }
	            }
	        }
	        
			for(int i = 0; i < fil.length; i++) {
				fanout = fanout + Collections.frequency(ref1, fil[i].getName().replaceFirst("[.][^.]+$", ""));
			}
	        	        
			for(int y = 0; y < fil.length; y++) {
				Map<String,Set <String>> map = new HashMap<String, Set<String>>();
				if(fanin(file,fil[y],map) > fanin) {
				fanin = fanin + fanin(file,fil[y],map);	
				}	
			}
			
/*		    Set<String> classes = new TreeSet<>(refMap.keySet());
	        for (String className1: classes) {
	            Set<String> others = refMap.get(className);
	            others.retainAll(classes);
	            for (String other: others) {
	                refMap.get(other).add(className);
	                System.out.println(refMap);
	            }
	        }	        */
	        refMap.put(className, referees); 
	    }
		
		int tot = fanin + fanout;
		
		CBO.add(fanin);
		CBO.add(fanout);
		CBO.add(tot);
//		CBO.add(ref1.size());
		
        return CBO;
	}

	public static int numberofmethods(File file) throws IOException{
		
		int count = -1;
		
		try (InputStream in = new FileInputStream(file)) {
		
			ClassParser parser = new ClassParser(in, file.getName());
			JavaClass clazz = parser.parse();
			for (org.apache.bcel.classfile.Method method: clazz.getMethods()) {
				Code code = method.getCode();

				count++;
			} 
		} 
		return count;
	}
	
	
	private static int fanin(File checkfile, File file, Map<String, Set<String>> refMap) throws IOException {
		
		int fanin = 0;
		ArrayList<String> ref1 = new ArrayList<>();
		
		try (InputStream in = new FileInputStream(file)) {	
			
			ClassParser parser = new ClassParser(in, file.getName());
	    	JavaClass clazz = parser.parse();
	    	String className = clazz.getClassName();
	    	Set<String> referees = new HashSet<>();
	    	ConstantPoolGen cp = new ConstantPoolGen(clazz.getConstantPool());
	    	
	    	for (org.apache.bcel.classfile.Method method: clazz.getMethods()) {
	    		Code code = method.getCode();
	    		InstructionList instrs = new InstructionList(code.getCode());
	    		for (InstructionHandle ih: instrs) {
	    			Instruction instr = ih.getInstruction();
	    			if (instr instanceof FieldOrMethod) {
	    				
	    				FieldOrMethod ref = (FieldOrMethod) instr;
	    		//		FieldOrMethod ref = (FieldInstruction)instr;
	    				String cn = ref.getClassName(cp);
	    				if (!cn.equals(className)) {
	    					referees.add(cn);
	    					ref1.add(cn);
	    				}
	    			}
	    		}
	    	}
	    	
	    	fanin = fanin + Collections.frequency(ref1, checkfile.getName().replaceFirst("[.][^.]+$", ""));
	    			
	    	refMap.put(className, referees);
		}
	    
			Set<String> classes = new TreeSet<>(refMap.keySet());
	        for (String className: classes) {
	            Set<String> others = refMap.get(className);
	            others.retainAll(classes);
	            for (String other: others) {
	                refMap.get(other).add(className);
	            }
	        } 	
		return fanin;
	}
	
}
