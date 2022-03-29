package RefactOK;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.FieldInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InvokeInstruction;

public class Group {

	static StringBuffer lcomut = new StringBuffer();
	private final Set<String> fields = new HashSet<>();
    private final Set<String> methods = new HashSet<>();
    
	public void main(String[] args) throws IOException {

		File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Whatever\\bin\\BS.class");
		File file1 = new File("C:\\Users\\anasz\\eclipse-workspace\\ScalesProblem\\bin\\ScPb\\ScalesSolution.class");
	}
	
	//method to add fields to HashSet
    public Group addFields(String...fields) {
        for (String field: fields) {
            this.fields.add(field);
        }
        return this;
    }

    //method to add methods to HashSet
    public Group addMethods(String... methods) {
        for (String method: methods) {
            this.methods.add(method);
        }
        return this;
    }

    //method to get number of fields
    public int fields() {
        return fields.size();
    }

    //method to get number of methods
    public int methods() {
        return methods.size();
    }

    //method to check if fields and methods intersect
    public boolean intersects(Group other) {
        for (String field: other.fields) {
            if (fields.contains(field)) {
                return true;
            }
        }
        for (String method: other.methods) {
            if (methods.contains(method)) {
                return true;
            }
        }
        return false;
    }

    //method to merge fields and methods together
    public void merge(Group other) {
        fields.addAll(other.fields);
        methods.addAll(other.methods);
    }

    //method to override toString() method to return a string representation of all fields and methods found
    @Override
    public String toString() {
        return "Group{" + "fields=" + fields + ", methods=" + methods + '}';
    }
    
   public  static List<Group> loadGroups(File file) throws IOException {
        
    	int i = 0;
    	lcomut = new StringBuffer();
    	
    	try (InputStream in = new FileInputStream(file)) {
    		//Create new parser - specify a class
            ClassParser parser = new ClassParser(in, file.getName());
            //parse given class to retrieve info
            JavaClass clazz = parser.parse();
            String className = clazz.getClassName();
            //Get class constant pool (contains byte code info on class methods, attributes, ...)
            ConstantPoolGen cp = new ConstantPoolGen(clazz.getConstantPool());
            List<Group> groups = new ArrayList<Group>();
            //loop through fields in the class
            for (Field field: clazz.getFields()) {
            	//Fill list of groups with a group for each field defined in the class
                groups.add(new Group().addFields(field.getName()));
            }
            //loop through methods in the class
            for (org.apache.bcel.classfile.Method method: clazz.getMethods()) {
            	//add methods found in the list
                Group group = new Group().addMethods(method.getName());
                Code code = method.getCode();
                //create intruction list to find instances of fields and methods
                InstructionList instrs = new InstructionList(code.getCode());
                for (InstructionHandle ih: instrs) {
                    Instruction instr = ih.getInstruction();
                    //look for isntances of fields
                    if (instr instanceof FieldInstruction) {
                        FieldInstruction fld = (FieldInstruction)instr;
                        //catch all fields in the class
                        if (fld.getClassName(cp).equals(className)) {
                        	//Fill the list of groups with a group for each reference made to the fields and methods in the class
                            group.addFields(fld.getFieldName(cp));
                            
                        }
                    } 
                    //catch all methods in the class
                    else if (instr instanceof InvokeInstruction) {
                        InvokeInstruction inv = (InvokeInstruction)instr;
                        if (inv.getClassName(cp).equals(className)) {
                            group.addMethods(inv.getMethodName(cp));
                        }
                    }
                }
                if (group.fields() > 0 || group.methods() > 1) {
                    i = groups.size();
                    while (i > 0) {
                        --i;
                        Group g = groups.get(i);
                        if (g.intersects(group)) {
                        	//merge groups that intersect list of methods
                            group.merge(g);
                            //remove groups that intersect list of methods
                            groups.remove(i);
                        }
                    }
                    
                    //add each group of intersected fields and methods 
                    groups.add(group);
                }
            }
            
            //store all warning values in stringbuffer
            if(groups.size() > 1) {
            	for(int j = 0; j < groups.size(); j++) {
            		lcomut.append("");
            		lcomut.append(groups.get(i).toString());
            		lcomut.append("\n");
            	}           	
            	Output.setlcomoutput(lcomut.toString());
            }
            
            //return size of the groups list that is the number of intersected methods and fields thus LCOM4
            return groups;
        }
    }
}

