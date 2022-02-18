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
	
	private static final String FINDSECBUGS_JAR_PATH = "C:\\Users\\anasz\\Downloads\\FindSec\\findsecbugs-plugin-1.9.0.jar";
//	private static final String TARGET_DIR_PATH = WeMovin.Bin();
	private static String TARGET_DIR_PATH;
	
	public static void setdir(String DIR) {
		TARGET_DIR_PATH = DIR;
	}
	
	private FindBugs2 findBugs;
	
	Application(){
		
		this.findBugs = new FindBugs2();
	}
	
	Collection<BugInstance> findBugs() throws IOException, InterruptedException{
		
		Project project = new Project();
		
		addPlugin();
		setupFiles(project);
		
		BugCollection bugs = new SortedBugCollection();
		BugReporter bugReporter = new MyReporter(bugs);
		findBugs.setProject(project);
		
		findBugs.setDetectorFactoryCollection(DetectorFactoryCollection.instance());
		findBugs.setBugReporter(bugReporter);
		final UserPreferences defaultUserPreferences = UserPreferences.createDefaultUserPreferences();
		defaultUserPreferences.setEffort(UserPreferences.EFFORT_MAX);
		findBugs.setUserPreferences(defaultUserPreferences);
		
		findBugs.execute();
		
		return Collections.unmodifiableCollection(findBugs.getBugReporter().getBugCollection().getCollection());
		
	}
	
	private void addPlugin() {
		try {
			Path pluginPath = Paths.get(FINDSECBUGS_JAR_PATH);
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			Plugin plugin = Plugin.addCustomPlugin(pluginPath.toUri(), contextClassLoader);
			System.out.println("Custom plugin aded: " + plugin);
		} catch(PluginException e) {
			System.err.println("Could not add plugin " + e.getMessage());
		}
	}

	/*
	private void setupFiles(Project project) {
		project.addFile(TARGET_DIR_PATH);
		
		File file = new File(TARGET_DIR_PATH);
		File[] files = file.listFiles();
		
		for(int i = 0; i < files.length; i++) {
			File next = files[i];
			if(!files[i].getName().equals(new File(TARGET_DIR_PATH).getName()))
				project.addAuxClasspathEntry(files[i].getAbsolutePath());
		}
	}
	*/
	
	private void setupFiles(Project project) {
		project.addFile(TARGET_DIR_PATH);
		
		File file = new File(TARGET_DIR_PATH);
		File[] files = file.getParentFile().listFiles();
		
		for(int i = 0; i < files.length; i++) {
			File next = files[i];
			if(!files[i].getName().equals(file.getName())) {
				project.addAuxClasspathEntry(files[i].getAbsolutePath());
			}
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException{
		
		System.out.println("Running Analyzer");
		
		new Application().findBugs();
	}

}
