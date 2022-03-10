package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import RefactOK.*;
import edu.umd.cs.findbugs.BugInstance;
import junit.framework.Assert;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class RefactOK_tests {
	
	//Folder path selection testing STARTS HERE
	
	@Test
	public void Test1enternullfilepaths() {
		String bin = null;
		String src = null;
		Assert.assertEquals(false, WeMovin.validFiles(src, bin));
	}

	@Test
	public void Test2enterrandomstring() {
		String bin = "Hello";
		String src = "World";
		Assert.assertEquals(false, WeMovin.validFiles(src, bin));
	}
	
	@Test
	public void Test3entermixedpaths() {
		String bin = "C:\\Users\\anasz\\eclipse-workspace\\Testing\\src";
		String src = "C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin";
		Assert.assertEquals(false, WeMovin.validFiles(src, bin));
	}
	
	@Test
	public void Test4enterrandomintegers() {
		String bin = "1234";
		String src = "5678";
		Assert.assertEquals(false, WeMovin.validFiles(src, bin));
	}
	
	@Test
	public void Test5enteremptyspaces() {
		String bin = " ";
		String src = " ";
		Assert.assertEquals(false, WeMovin.validFiles(src, bin));
	}

	@Test
	public void Test6enterrightpath() {
		String bin = "C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin";
		String src = "C:\\Users\\anasz\\eclipse-workspace\\Testing\\src";
		Assert.assertEquals(true, WeMovin.validFiles(src, bin));
	}
	
	//Cyclomatic Complexity Class Tests start HERE
	
	@Test
	public void Test7complexityiszero() {
		File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\src\\test3.java");
		File file2 = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin\\test3.class");
		Scanner scanner;
		StringBuffer line = new StringBuffer();
		try {
			scanner = new Scanner(file);
     
		while(scanner.hasNextLine()) {
			line.append(scanner.nextLine());
			line.append("\n");}
		} catch (FileNotFoundException e) {
		}
		Assert.assertEquals(0.0, Cyclomatic.complexity(line.toString(), file.getName(), file2));
		}
		
	
	@Test
	public void Test8complexityishigherthanzero() {
		File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\src\\test1.java");
		File file2 = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin\\test1.class");
		Scanner scanner;
		StringBuffer line = new StringBuffer();
		try {
			scanner = new Scanner(file);
     
		while(scanner.hasNextLine()) {
			line.append(scanner.nextLine());
			line.append("\n");}
		} catch (FileNotFoundException e) {
		}
		Assert.assertEquals(30.0, Cyclomatic.complexity(line.toString(), file.getName(), file2));
		}
	
	//Coupling, Fan In & Fan Out tests start HERE
	
	@Test
	public void Test9FanOutZero() {
	Map<String,Set <String>> map = new HashMap<String, Set<String>>();
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\tryin.class");
	File files = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin");
	File[] listofFiles = files.listFiles();
	
	try {
		Assert.assertEquals(0, (int) CBO.fanout(file, map, listofFiles, false).get(1));
	} catch (IOException e) {
	}
	}
	
	@Test
	public void Test10FanInZero() {
	Map<String,Set <String>> map = new HashMap<String, Set<String>>();
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\tryin.class");
	File files = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin");
	File[] listofFiles = files.listFiles();
	
	try {
		Assert.assertEquals(0, (int) CBO.fanout(file, map, listofFiles, false).get(0));
	} catch (IOException e) {
	}
	}
	
	@Test
	public void Test11CBOZero() {
	Map<String,Set <String>> map = new HashMap<String, Set<String>>();
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\tryin.class");
	File files = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin");
	File[] listofFiles = files.listFiles();
	
	try {
		Assert.assertEquals(0, (int) CBO.fanout(file, map, listofFiles, false).get(2));
	} catch (IOException e) {
	}
	}
	
	@Test
	public void Test12FanOutHigherThanZero() {
	Map<String,Set <String>> map = new HashMap<String, Set<String>>();
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\CBO.class");
	File files = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin");
	File[] listofFiles = files.listFiles();
	
	try {
		Assert.assertEquals(3, (int) CBO.fanout(file, map, listofFiles, false).get(1));
	} catch (IOException e) {
	}
	}
	
	@Test
	public void Test13FanInHigherThanZero() {
	Map<String,Set <String>> map = new HashMap<String, Set<String>>();
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\CBO.class");
	File files = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin");
	File[] listofFiles = files.listFiles();
	
	try {
		Assert.assertEquals(17, (int) CBO.fanout(file, map, listofFiles, false).get(0));
	} catch (IOException e) {
	}
	}
	
	@Test
	public void Test14CBOHigherThanZero() {
	Map<String,Set <String>> map = new HashMap<String, Set<String>>();
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\CBO.class");
	File files = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin");
	File[] listofFiles = files.listFiles();
	
	try {
		Assert.assertEquals(20, (int) CBO.fanout(file, map, listofFiles, false).get(2));
	} catch (IOException e) {
	}
	}
	
	//Cohesion tests start HERE
	
	@Test
	public void Test15CohesionHigh() {
	
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\MainFrame.class");
	
	try {
		Assert.assertEquals(16,Group.loadGroups(file).size());
	} catch (IOException e) {
	}
	}
	
	@Test
	public void Test16CohesionGood() {
	
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\NOC.class");
	
	try {
		Assert.assertEquals(1,Group.loadGroups(file).size());
	} catch (IOException e) {
	}
	}
	
	@Test
	public void Test17CohesionZero() {
	
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\tryin.class");
	
	try {
		Assert.assertEquals(0,Group.loadGroups(file).size());
	} catch (IOException e) {
	}
	}
	
	//BUGS tests start HERE
	
	@Test
	public void Test18BugsHighNumberofBugs() {
		File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\WeMovin.class");
		Application.setdir(file.toString());
		try {
			Collection<BugInstance> app = new Application().findBugs();

		Assert.assertEquals(49, app.size());
		} catch (Exception e) {
		}
	}
	
	@Test
	public void Test19BugsLowNumberofBugs() {
		File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin\\test2.class");
		Application.setdir(file.toString());
		try {
			Collection<BugInstance> app = new Application().findBugs();

		Assert.assertEquals(2, app.size());
		} catch (Exception e) {
		}
	}
	
}