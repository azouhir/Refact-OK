package RefactOK;
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
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.FieldInstruction;
import org.apache.bcel.generic.FieldOrMethod;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.*;

public class CBO {

	//set string buffers to store output
	static StringBuffer out = new StringBuffer();
	static StringBuffer inp = new StringBuffer();
	static StringBuffer meth = new StringBuffer();


	public static ArrayList fanout(File file, Map<String, Set<String>> refMap, File[] fil, boolean produce)
	        throws IOException {
	    
		int fanout = 0;
		int fanin = 0;
		out = new StringBuffer();
		inp = new StringBuffer();
		
		ArrayList<String> ref1 = new ArrayList<>();
		
		ArrayList<Integer> CBO = new ArrayList<>();
		CBO.clear();
		
		try (InputStream in = new FileInputStream(file)) {
			//Create new parser - specify a class
	        ClassParser parser = new ClassParser(in, file.getName());
	        //parse given class to retrieve info
	        JavaClass clazz = parser.parse();
	        String className = clazz.getClassName();
	        //new HashSet to store referees (calls to other objects)
	        Set<String> referees = new HashSet<>();
	        //Get class constant pool (contains byte code info on class methods, attributes, ...)
	        ConstantPoolGen cp = new ConstantPoolGen(clazz.getConstantPool());
	        //loop through methods
	        for (org.apache.bcel.classfile.Method method: clazz.getMethods()) {
	            Code code = method.getCode();
	            //create instructionlist contains all class instructions therefore calls made to objects
	            InstructionList instrs = new InstructionList(code.getCode());
	            //loop through instructions
	            for (InstructionHandle ih: instrs) {
	                Instruction instr = ih.getInstruction();
	                //ensure instruction is an instance of a field or a method to find if that instance refers to another object
	                if (instr instanceof FieldOrMethod) {
	                	FieldOrMethod ref = (FieldOrMethod) instr;
	                	//for instance of field or method found, get the class name contained in the constant pool
	                    String cn = ref.getClassName(cp);
	                    //if the class name is not equal to the current class therefore is external, include it in the lists
	                    if (!cn.equals(className)) {
	                    	
	                    	//add class name found to referees list
	                        referees.add(cn);
	                        ref1.add(cn);
	                    }
	                }
	            }
	        }
	        
	        /*
	         the list referees contains ALL external calls therefore even those made to built in methods such as Random();
	         we want to calculate only user's made objects (thus other classes in the system) this is why we loop again
	         through the list to find names that match the ones in the files list 
	         */
			
	        //Get all classes called by this class from referees list
			for(int i = 0; i < fil.length; i++) {
				for(int j = 0; j < ref1.size(); j++) {
					//check if referee exists in the files folder (user created)
					if((ref1.get(j).endsWith("."+fil[i].getName().replaceFirst("[.][^.]+$", "")) || ref1.get(j).equals(fil[i].getName().replaceFirst("[.][^.]+$", "")))) {
						String temp = "Class " + file.getName() + " is calling class: " + fil[i].getName() + "\n";
						out.append(temp);
						out.append("\n");
						out.append("");
						//if class found add 1 to fan out counter
						fanout++;
					}
				}
			}
			
			//get all classes and check for a reference to the current class
			for(int y = 0; y < fil.length; y++) {
				Map<String,Set <String>> map = new HashMap<String, Set<String>>();
				//file is the class being analysed. We want to find calls made to this class in other classes fil[y]
				if(fanin(file,fil[y],map) > fanin) {
				fanin = fanin + fanin(file,fil[y],map);	
				}	
			}
			
			refMap.put(className, referees); 
	    }
		
		//Calculate CBO by adding fan in and fan out together
		int tot = fanin + fanout;
			
		if(produce && fanout > 5) {
			Output.setfooutput(out.toString());
		}
		
		if(produce && fanin > 5) {
			Output.setfioutput(inp.toString());
		}
		
		//Add results to list so that they can be retrieved from the controller
		CBO.add(fanin);
		CBO.add(fanout);
		CBO.add(tot);
		
        return CBO;
	}

	//method to get all methods in a class through BCEL. not used currently because it gets hidden methods thus affecting AVGCC
	//Temporarily replaced with method in AST
	//SAME PRINCIPLES AS FANOUT METHOD APPLY
	public static int numberofmethods(File file, boolean produce) throws IOException, ClassNotFoundException{
		
		meth = new StringBuffer();
		int count = 0;
		try (InputStream in = new FileInputStream(file)) {
		
			meth.append(file.getName());
			ClassParser parser = new ClassParser(in, file.getName());
			JavaClass clazz1 = parser.parse();
			ClassGen cg = new ClassGen(clazz1);
			ConstantPoolGen cp = new ConstantPoolGen(clazz1.getConstantPool());
			for (org.apache.bcel.classfile.Method method: clazz1.getMethods()) {
				MethodGen methodgen = new MethodGen(method, clazz1.getClassName(), cp);
				
				//Avoid collection of inner classes and constructors
				if(!methodgen.getName().matches("<init>|<clinit>")) {
				ConstantPoolGen methgen = new ConstantPoolGen(method.getConstantPool());
				Code code = method.getCode();
				String temp = method.getName();
				System.out.println(methodgen.getType());
				meth.append(temp);
				meth.append("\n");
				meth.append("");
				count++;
				}
			}			
		} 

		if(produce && count > 15) {
			Output.setmethoutput(meth.toString());
		}
		
		return count;
	}
	
	//SAME PRINCIPLES AS FANOUT METHOD APPLY
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
	    				String cn = ref.getClassName(cp);
	    				if (!cn.equals(className)) {
	    					
	    					//add all classes called in class being analysed to referees list
	    					referees.add(cn);
	    					ref1.add(cn);
	    				}
	    			}
	    		}
	    	}

	    		//Get all classes where a call to current class is made from referees list
				for(int j = 0; j < ref1.size(); j++) {
					if(ref1.get(j).endsWith("."+checkfile.getName().replaceFirst("[.][^.]+$", "")) || ref1.get(j).equals(checkfile.getName().replaceFirst("[.][^.]+$", ""))) {
						String temp = "Class " + checkfile.getName() + " is being called by class: " + file.getName() + "\n";
						inp.append(temp);
						inp.append("\n");
						inp.append("");
						fanin++;
					}
				}
	    			
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
