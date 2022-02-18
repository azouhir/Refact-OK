import java.io.File;
import java.io.FileNotFoundException;
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
	
	static int cc = 0;	
	
	public static int complexity(String line) {
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(line.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
 
		
		cu.accept(new ASTVisitor() {
			
			public boolean visit(SwitchCase node) {				
				cc++;
				return false;
			}
			
			public boolean visit(SwitchStatement node) {
				cc++;
				return false;
			}
			
			public boolean visit(IfStatement node) {
				cc++;
				return false;
			}
			
			public boolean visit(ForStatement node) {
				cc++;
				return false;
			}
			
			public boolean visit(WhileStatement node) {
				cc++;
				return false;
			}
			
			public boolean visit(CatchClause node) {
				cc++;
				return false;
			}
			
			public boolean visit(BreakStatement node) {
				cc++;
				return false;
			}
			
			public boolean visit(ThrowStatement node) {
				cc++;
				return false;
			}
			
			public boolean visit(DoStatement node) {
				cc++;
				return false;
			}

		});
		
		return cc;
	}
}
