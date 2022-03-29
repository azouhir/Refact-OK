package RefactOK;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

public class Cyclomatic {
	
	static double cc = 0;
	static int wmc = 0;
	static StringBuffer output = new StringBuffer();
	static StringBuffer wmcoutput = new StringBuffer();
	
	//Temp method to calculate WMC by visiting all method nodes in the class
	public static int wmc(String line, String classname, boolean produce) {
		
		//create new AST parser
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		//set the source of the parser to a character array compose by the .java file analysed
		parser.setSource(line.toCharArray());
		//set parser kind, compilation unit will allow to visit different nodes
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
		//create new compilation unit
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		wmc = 0;
		wmcoutput = new StringBuffer();
		
		wmcoutput.append(classname);
		wmcoutput.append("\n");
		wmcoutput.append("");
		
		//accept new ASTVisitor to visit each node in the class
		cu.accept(new ASTVisitor() {
			public boolean visit(MethodDeclaration node) {
				
				//count nodes visited
				wmc++;
				wmcoutput.append(node);
				wmcoutput.append("\n");
				wmcoutput.append("");
				return false;
			}
		});
		
		if(produce && wmc > 15) {
		Output.setmethoutput(wmcoutput.toString());
		}
		return wmc;
	}
	
	//method to calcualte AVGCC
	//SAME PRINCIPLES AS WMC APPLY
	public static double complexity(String line, String classname, File file) {
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(line.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
 
		cc = 0;
		output = new StringBuffer();
		
		output.append(classname);
		output.append("\n");
		output.append("");
		
		cu.accept(new ASTVisitor() {
			
			//visit all relevant nodes needed to calculate AVGCC
			
			public boolean visit(SwitchCase node) {				
				cc++;
				return true;
			}
			
			public boolean visit(SwitchStatement node) {
				cc++;
				return true;
			}
			
			public boolean visit(IfStatement node) {
				cc++;
				return true;
			}
			
			public boolean visit(ForStatement node) {
				cc++;
				return true;
			}
			
			public boolean visit(WhileStatement node) {
				cc++;
				return true;
			}
			
			public boolean visit(CatchClause node) {
				cc++;
				return true;
			}
			
			public boolean visit(BreakStatement node) {
				cc++;
				return true;
			}
			
			public boolean visit(ThrowStatement node) {
				cc++;
				return true;
			}
			
			public boolean visit(DoStatement node) {
				cc++;
				return true;
			}

		});
		
		//create new visitor to store nodes affecting AVGCC in a string
		//this is needed because if return is true then same node will be repeated multiple times
		//therefore we return false to only store one node declaration avoiding redundancy
		cu.accept(new ASTVisitor() {
			
			public boolean visit(SwitchCase node) {				
				output.append(node);
				output.append("\n");
				output.append("");
				return false;
			}
			
			public boolean visit(SwitchStatement node) {
				output.append(node);
				output.append("\n");
				output.append("");
				return false;
			}
			
			public boolean visit(IfStatement node) {
				output.append(node);
				output.append("\n");
				output.append("");
				return false;
			}
			
			public boolean visit(ForStatement node) {
				output.append(node);
				output.append("\n");
				output.append("");
				return false;
			}
			
			public boolean visit(WhileStatement node) {
				output.append(node);
				output.append("\n");
				output.append("");
				return false;
			}
			
			public boolean visit(CatchClause node) {
				output.append(node);
				output.append("\n");
				output.append("");
				return false;
			}
			
			public boolean visit(BreakStatement node) {
				output.append(node);
				output.append("\n");
				output.append("");
				return false;
			}
			
			public boolean visit(ThrowStatement node) {
				output.append(node);
				output.append("\n");
				output.append("");
				return false;
			}
			
			public boolean visit(DoStatement node) {
				output.append(node);
				output.append("\n");
				output.append("");
				return false;
			}

		});

		//divide complexity by number of methods
		double result = cc/ wmc(line, classname, false);
		if(result > 5) {
			Output.setccoutput(output.toString());
		}
		return cc;
	}
}
