package RefactOK;
import edu.umd.cs.findbugs.*;
import edu.umd.cs.findbugs.classfile.ClassDescriptor;
import org.apache.bcel.classfile.JavaClass;

public class MyReporter extends TextUIBugReporter {
	
	private final BugCollection bugs;
	
	MyReporter(BugCollection bugs){
		
		//create new collection
		this.bugs = bugs;
		setPriorityThreshold(Detector.IGNORE_PRIORITY);
	}
	
	@Override
	protected void doReportBug(BugInstance bugInstance) {
		
		//add each bug isntance found to the collection
		bugs.add(bugInstance);
	}
	
	//method called when analysis is finished
	public void finish() {
		System.out.println("Finished SpotBugs analysis");
		System.out.println("Results: ");
		
		//return no bugs if collection is empty 
		if(bugs.getCollection().isEmpty()) {
			System.out.println("No bugs found");
		}
		
		//return collection size if otherwise
		else {
			System.out.println(String.format("%d bugs found", bugs.getCollection().size()));
			Controller.SetBugs(bugs.getCollection().size());
		}
	}
	
	//method needed to run spotbugs but not used here
	public void observeClass(JavaClass javaClass) {
		
	}
	
	//method needed to run spotbugs but not used here
	public void observeClass(ClassDescriptor classDecsriptor) {
		
	}
	
	public BugCollection getBugCollection() {
		return bugs;
	}
}
