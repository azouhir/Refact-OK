package RefactOK;
import edu.umd.cs.findbugs.*;
import edu.umd.cs.findbugs.config.UserPreferences;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Application {
	
	private static String TARGET_DIR_PATH;
	StringBuffer repo = new StringBuffer();
	
	//Set directory path coming from controller
	public static void setdir(String DIR) {
		TARGET_DIR_PATH = DIR;
	}
	
	private FindBugs2 findBugs;
	//Run Spotbugs
	public Application(){
		
		this.findBugs = new FindBugs2();
	}
	
	public Collection<BugInstance> findBugs() throws IOException, InterruptedException{
		
		Project project = new Project();
		
		repo = new StringBuffer();
		
		//call method to set files in the project for analysis
		setupFiles(project);
		
		BugCollection bugs = new SortedBugCollection();
		
		//call reporter class to set a new report where bug instances will be collected
		BugReporter bugReporter = new MyReporter(bugs);
		findBugs.setProject(project);
		
		findBugs.setDetectorFactoryCollection(DetectorFactoryCollection.instance());
		findBugs.setBugReporter(bugReporter);
		final UserPreferences defaultUserPreferences = UserPreferences.createDefaultUserPreferences();
		
		//change this to different effort levels to get specific bug types
		defaultUserPreferences.setEffort(UserPreferences.EFFORT_MAX);
		findBugs.setUserPreferences(defaultUserPreferences);
		
		//execute findbugs to collect bug instances
		findBugs.execute();
		
		File file = new File(TARGET_DIR_PATH);
		
		//Create output string to show in instruction panel
		Object[] temp = bugReporter.getBugCollection().getCollection().toArray();
		repo.append(file.getName());
		repo.append("\n");
		for(int i = 0; i < bugReporter.getBugCollection().getCollection().size(); i++) {
		String temp1 = temp[i].toString();
		repo.append(temp1);
		repo.append("\n");
		repo.append("");
		}
		
		//create output string to go in instruction panel if number of bugs higher 5
		if(bugReporter.getBugCollection().getCollection().size() > 5) {
			Output.setbugoutput(repo.toString());
		}
		
		//return the bug collection containing all the bugs found
		return Collections.unmodifiableCollection(findBugs.getBugReporter().getBugCollection().getCollection());		
	}
		
	//Method to set main class file being analysed and auxiliary files path to allow analysis
	private void setupFiles(Project project) {
		
		//add string classpath to project path as this is the main class being analysed
		project.addFile(TARGET_DIR_PATH);
		
		File file = new File(TARGET_DIR_PATH);
		File[] files = file.getParentFile().listFiles();
		
		for(int i = 0; i < files.length; i++) {
			File next = files[i];
			
			//make sure current class doesn't go into auxiliary path
			if(!files[i].getName().equals(file.getName())) {
				
				//add rest of the classes to auxiliary path
				project.addAuxClasspathEntry(files[i].getAbsolutePath());
			}
		}
	}

	//main run findbugs method to run Spotbus
	public static void main(String[] args) throws InterruptedException, IOException{
		
		System.out.println("Running Analyzer");
		
		new Application().findBugs();
	}

}
