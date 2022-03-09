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

/*
OK, let's define a class to represent a group of fields and methods:
*/

public class Group {

	static StringBuffer lcomut = new StringBuffer();
	private final Set<String> fields = new HashSet<>();
    private final Set<String> methods = new HashSet<>();
    
	public void main(String[] args) throws IOException {

		File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Whatever\\bin\\BS.class");
		File file1 = new File("C:\\Users\\anasz\\eclipse-workspace\\ScalesProblem\\bin\\ScPb\\ScalesSolution.class");
//		System.out.println(loadGroups(file1));
	}
	
    public Group addFields(String...fields) {
        for (String field: fields) {
            this.fields.add(field);
        }
        return this;
    }

    public Group addMethods(String... methods) {
        for (String method: methods) {
            this.methods.add(method);
        }
        return this;
    }

    public int fields() {
        return fields.size();
    }

    public int methods() {
        return methods.size();
    }

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

    public void merge(Group other) {
        fields.addAll(other.fields);
        methods.addAll(other.methods);
    }

    @Override
    public String toString() {
        return "Group{" + "fields=" + fields + ", methods=" + methods + '}';
    }
    
    
    
/*  
We start the process by filling the list of groups with a group for each field defined in the class, 
then, for each method we build a group with the fields and the methods referenced in the code, 
then with reduce the list of group by merging and removing each group that intersects the group of the method.

Here's the java code to load the groups of the class. LCOM4 is groups.size():      
*/    
    
    static List<Group> loadGroups(File file) throws IOException {
        
    	int i = 0;
    	lcomut = new StringBuffer();
    	
    	try (InputStream in = new FileInputStream(file)) {
            ClassParser parser = new ClassParser(in, file.getName());
            JavaClass clazz = parser.parse();
            String className = clazz.getClassName();
            ConstantPoolGen cp = new ConstantPoolGen(clazz.getConstantPool());
            List<Group> groups = new ArrayList<Group>();
            for (Field field: clazz.getFields()) {
                groups.add(new Group().addFields(field.getName()));
            }
            for (org.apache.bcel.classfile.Method method: clazz.getMethods()) {
                Group group = new Group().addMethods(method.getName());
                Code code = method.getCode();
                InstructionList instrs = new InstructionList(code.getCode());
                for (InstructionHandle ih: instrs) {
                    Instruction instr = ih.getInstruction();
                    if (instr instanceof FieldInstruction) {
                        FieldInstruction fld = (FieldInstruction)instr;
                        if (fld.getClassName(cp).equals(className)) {
                            group.addFields(fld.getFieldName(cp));
                            
                        }
                    } else if (instr instanceof InvokeInstruction) {
                        InvokeInstruction inv = (InvokeInstruction)instr;
                        if (inv.getClassName(cp).equals(className)) {
                            group.addMethods(inv.getMethodName(cp));
                        }
                    }
                }
                if (group.fields() > 0 || group.methods() > 1) {
                    i = groups.size();
          //          System.out.println(groups.size());
                    while (i > 0) {
                        --i;
                        Group g = groups.get(i);
                        if (g.intersects(group)) {
                            group.merge(g);
                            groups.remove(i);
                        }
                    }
                    groups.add(group);
                }
            }
//           System.out.println(groups.size());
    //       System.out.println(groups);
            
            if(groups.size() > 1) {
            	for(int j = 0; j < groups.size(); j++) {
            		lcomut.append("");
            		lcomut.append(groups.get(i).toString());
            		lcomut.append("\n");
            	}           	
            	Output.setlcomoutput(lcomut.toString());
            }
            
            return groups;
        }
    }
}

