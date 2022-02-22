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
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

public class Cyclomatic {
	
	static double cc = 0;
	static StringBuffer output = new StringBuffer();
	
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
		
		try {
			double result = cc / CBO.numberofmethods(file, false);
			if(result > 5) {
				Output.setccoutput(output.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cc;
	}
}
