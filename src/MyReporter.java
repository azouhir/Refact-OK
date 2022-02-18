import edu.umd.cs.findbugs.*;
import edu.umd.cs.findbugs.classfile.ClassDescriptor;
import org.apache.bcel.classfile.JavaClass;

public class MyReporter extends TextUIBugReporter {
	
	private final BugCollection bugs;
	
	MyReporter(BugCollection bugs){
		
		this.bugs = bugs;
//		setPriorityThreshold(Detector.NORMAL_PRIORITY);
		setPriorityThreshold(Detector.IGNORE_PRIORITY);
	}
	
	@Override
	protected void doReportBug(BugInstance bugInstance) {
		bugs.add(bugInstance);
	}
	
	public void finish() {
		System.out.println("Finished SpotBugs analysis");
		System.out.println("Results: ");
		if(bugs.getCollection().isEmpty()) {
			System.out.println("No bugs found");
		}
		else {
			System.out.println(String.format("%d bugs found", bugs.getCollection().size()));
			WeMovin.SetBugs(bugs.getCollection().size());
		}
	}
	
	public void observeClass(JavaClass javaClass) {
		
	}
	
	public void observeClass(ClassDescriptor classDecsriptor) {
		
	}
	
	public BugCollection getBugCollection() {
		return bugs;
	}
}
