package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.internal.MethodSorter;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import RefactOK.*;
import edu.umd.cs.findbugs.BugInstance;
import junit.framework.Assert;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RefactOK_tests {
	
	//Folder path selection testing STARTS HERE
	
	@Test
	@Order(1)
	public void Test1enternullfilepaths() {
		String bin = null;
		String src = null;
		Assert.assertEquals(false, Controller.validFiles(src, bin));
	}

	@Test
	@Order(2)
	public void Test2enterrandomstring() {
		String bin = "Hello";
		String src = "World";
		Assert.assertEquals(false, Controller.validFiles(src, bin));
	}
	
	@Test
	@Order(3)
	public void Test3entermixedpaths() {
		String bin = "C:\\Users\\anasz\\eclipse-workspace\\Testing\\src";
		String src = "C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin";
		Assert.assertEquals(false, Controller.validFiles(src, bin));
	}
	
	@Test
	@Order(4)
	public void Test4enterrandomintegers() {
		String bin = "1234";
		String src = "5678";
		Assert.assertEquals(false, Controller.validFiles(src, bin));
	}
	
	@Test
	@Order(5)
	public void Test5enteremptyspaces() {
		String bin = " ";
		String src = " ";
		Assert.assertEquals(false, Controller.validFiles(src, bin));
	}

	@Test
	@Order(6)
	public void Test6enterrightpath() {
		String bin = "C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin";
		String src = "C:\\Users\\anasz\\eclipse-workspace\\Testing\\src";
		Assert.assertEquals(true, Controller.validFiles(src, bin));
	}
	
	//Cyclomatic Complexity Class Tests start HERE
	
	@Test
	@Order(7)
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
	@Order(8)
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
	@Order(9)
	public void Test9FanOutZero() throws IOException {
	Map<String,Set <String>> map = new HashMap<String, Set<String>>();
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\tryin.class");
	File files = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin");
	File[] listofFiles = files.listFiles();
	
		Assert.assertEquals(0, (int) CBO.fanout(file, map, listofFiles, false).get(1));
	}
	
	@Test
	@Order(10)
	public void Test10FanInZero() throws IOException {
	Map<String,Set <String>> map = new HashMap<String, Set<String>>();
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\tryin.class");
	File files = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin");
	File[] listofFiles = files.listFiles();

		Assert.assertEquals(0, (int) CBO.fanout(file, map, listofFiles, false).get(0));
	}
	
	@Test
	@Order(11)
	public void Test11CBOZero() throws IOException {
	Map<String,Set <String>> map = new HashMap<String, Set<String>>();
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\tryin.class");
	File files = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin");
	File[] listofFiles = files.listFiles();

		Assert.assertEquals(0, (int) CBO.fanout(file, map, listofFiles, false).get(2));
	}
	
	@Test
	@Order(12)
	public void Test12FanOutHigherThanZero() throws IOException {
	Map<String,Set <String>> map = new HashMap<String, Set<String>>();
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\CBO.class");
	File files = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin");
	File[] listofFiles = files.listFiles();

		Assert.assertEquals(3, (int) CBO.fanout(file, map, listofFiles, true).get(1));
	}
	
	@Test
	@Order(13)
	public void Test13FanInHigherThanZero() throws IOException {
	Map<String,Set <String>> map = new HashMap<String, Set<String>>();
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\CBO.class");
	File files = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin");
	File[] listofFiles = files.listFiles();

		Assert.assertEquals(17, (int) CBO.fanout(file, map, listofFiles, true).get(0));
	}
	
	@Test
	@Order(14)
	public void Test14CBOHigherThanZero() throws IOException {
	Map<String,Set <String>> map = new HashMap<String, Set<String>>();
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\CBO.class");
	File files = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin");
	File[] listofFiles = files.listFiles();

		Assert.assertEquals(20, (int) CBO.fanout(file, map, listofFiles, true).get(2));

	}
	
	//Cohesion tests start HERE
	
	@Test
	@Order(15)
	public void Test15CohesionHigh() throws IOException {
	
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\MainFrame.class");

		Assert.assertEquals(16,Group.loadGroups(file).size());

	}
	
	@Test
	@Order(16)
	public void Test16CohesionGood() throws IOException {
	
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\NOC.class");

		Assert.assertEquals(1,Group.loadGroups(file).size());
	}
	
	@Test
	@Order(17)
	public void Test17CohesionZero() throws IOException {
	
	File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\tryin.class");
	
		Assert.assertEquals(0,Group.loadGroups(file).size());
	}
	
	//BUGS tests start HERE
	
	@Test
	@Order(18)
	public void Test18BugsHighNumberofBugs() throws IOException, InterruptedException {
		File file = new File("C:\\Users\\anasz\\OneDrive\\Desktop\\BackUp FEBRUARY\\Backup 6\\FYP\\bin\\WeMovin.class");
		Application.setdir(file.toString());

		Collection<BugInstance> app = new Application().findBugs();
		Assert.assertEquals(45, app.size());
	}
	
	@Test
	@Order(19)
	public void Test19BugsLowNumberofBugs() throws IOException, InterruptedException {
		File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin\\test2.class");
		Application.setdir(file.toString());
		
		Collection<BugInstance> app = new Application().findBugs();
		Assert.assertEquals(2, app.size());
	}
	
	//NOC tests start HERE
	
	@Test
	@Order(20)
	public void Test20ChildrenHigherthanZero() throws IOException {
		File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin\\test1.class");
		File files = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin");
		
		File[] listofFiles = files.listFiles();
		
		Assert.assertEquals(2, NOC.noc(listofFiles, file, 0));
	}
	
	@Test
	@Order(21)
	public void Test21ChildrenequalZero() throws IOException {
		File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin\\test3.class");
		File files = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin");
		
		File[] listofFiles = files.listFiles();
		
		Assert.assertEquals(0, NOC.noc(listofFiles, file, 0));
	}
	
	//DIT tests start HERE
	
	@Test
	@Order(22)
	public void Test22DITequalZero() throws IOException {
		File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin\\test1.class");
		File files = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin");
		
		File[] listofFiles = files.listFiles();

		Assert.assertEquals(0, NOC.DIT(file, 0, listofFiles));
	}
	
	@Test
	@Order(23)
	public void Test23DITHigherthanZero() throws IOException {
		File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin\\test4.class");
		File files = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin");
		
		File[] listofFiles = files.listFiles();

		Assert.assertEquals(2, NOC.DIT(file, 0, listofFiles));
	}
	
	//WMC tests start HERE
	
	@Test
	@Order(24)
	public void Test24WMCequalzero() throws IOException, ClassNotFoundException {
		File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin\\test5.class");

		Assert.assertEquals(0, CBO.numberofmethods(file, false));
	}
	
	@Test
	@Order(25)
	public void Test25WMCHigherthanZero() throws IOException, ClassNotFoundException {
//		File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Testing\\bin\\test4.class");
		File file = new File("C:\\Users\\anasz\\eclipse-workspace\\FYP\\bin\\RefactOK\\WeMovin.class");
		
		Assert.assertEquals(40, CBO.numberofmethods(file, true));
	}
	
	//Output tests start HERE
	
	@Test
	@Order(26)
	public void Test26LCOMOutput() {
		Assert.assertFalse(Output.getlcomoutput().isEmpty());
	}
	
	@Test
	@Order(27)
	public void Test27AVGCCOutput() {
		Assert.assertFalse(Output.getccoutput().isEmpty());
	}
	
	@Test
	@Order(28)
	public void Test28FanOutOutput() {
		Assert.assertTrue(Output.getfooutput().isEmpty());
	}
	
	@Test
	@Order(29)
	public void Test29FanInOutput() {
		Assert.assertFalse(Output.getfioutput().isEmpty());
	}
	
	@Test
	@Order(30)
	public void Test30WMCOutput() throws IOException {
		Assert.assertFalse(Output.getmethoutput().isEmpty());
	}
	
	@Test
	@Order(31)
	public void Test31NOCOutput() {
		Assert.assertTrue(Output.getnocoutput().isEmpty());
	}
	
	@Test
	@Order(32)
	public void Test32DITOutput() {
		Assert.assertTrue(Output.getditoutput().isEmpty());
	}
	
	@Test
	@Order(33)
	public void Test33BUGOutput() {
		Assert.assertFalse(Output.getbugoutput().isEmpty());
	}
	
	@Test
	@Order(34)
	public void Test34Trigger() {
		Output.trigger();
		Assert.assertEquals("", Output.getlcomoutput());
		Assert.assertEquals("", Output.getccoutput());
		Assert.assertEquals("", Output.getfooutput());
		Assert.assertEquals("", Output.getfioutput());
		Assert.assertEquals("", Output.getnocoutput());
		Assert.assertEquals("", Output.getditoutput());
		Assert.assertEquals("", Output.getmethoutput());
		Assert.assertEquals("", Output.getbugoutput());
	}
	
}