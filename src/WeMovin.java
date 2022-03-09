import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.swing.table.DefaultTableModel;
import javax.xml.stream.events.Characters;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;

public class WeMovin {
	
	static int lines = 0;
	static int methods = 0;
	static int cbo = 0;
	static int fanin = 0;
	static int fanout = 0;
	static int lcom = 0;
	static int noc = 0;
	static int avgcc = 0;
	static int mlcomments = 0;
	static int dit = 0;
	static int bug1 = 0;
	static int bugs;
	static int totbugs;
	static File file2;
	static File file3;
	static String current;
	static List XY = new ArrayList();
	static File[] javafiles;
	static File[] tempclassfiles;
	
	
	
	static boolean print = false;
	
	public static void main(String[] args) {
		
		Welcome();
		
	}
	
	public static void SetLOC(int l) {
		lines = l;
	}
	public static void SetWMC(int w) {
		methods = w;
	}
	public static void SetCBO(int c) {
		cbo = c;
	}
	public static void SetFanIn(int fi) {
		fanin = fi;
	}
	public static void SetFanOut(int fo) {
		fanout = fo;
	}
	public static void SetLCOM(int lc) {
		lcom = lc;
	}
	public static void SetNOC(int n) {
		noc = n;
	}
	public static void SetAVGCC(int a) {
		avgcc = a;
	}
	public static void SetDIT(int d) {
		dit = d;
	}
	public static void SetB(int b) {
		bug1 = b;
	}
	
	public static void Welcome() {
		
		System.out.println("Welcome to RefactOK!");
		System.out.println(" ");
		System.out.println("Would you like to specify any threshold values? (y or n)");
		
		Scanner thresholds = new Scanner(System.in);
		String Thresholds = thresholds.nextLine();
		
		if(Thresholds.equals("y")) {
			
			System.out.println("Would you like to specify a value for WMC?");
			Scanner chose = new Scanner(System.in);
			String yn = chose.nextLine();
			
			if(yn.equals("y")) {
			System.out.println("Specify minimum number of methods in a class to be considered:");
			Scanner meth = new Scanner(System.in);
			methods = meth.nextInt();
			}
			
			System.out.println("Would you like to specify a value for LOC?");
			Scanner chose1 = new Scanner(System.in);
			String yn1 = chose1.nextLine();
			
			if(yn1.equals("y")){
			System.out.println("Specify minimum number of lines in a class to be considered: ");
			Scanner lin = new Scanner(System.in);
			lines = lin.nextInt();
			}
			
			System.out.println("Would you like to specify a value for CBO?");
			Scanner chose2 = new Scanner(System.in);
			String yn2 = chose2.nextLine();
			
			if(yn2.equals("y")) {
			System.out.println("Specify minimum number of coupling between objects in a class to be considered: ");
			Scanner cb = new Scanner(System.in);
			cbo = cb.nextInt();
			}
			
			System.out.println("Would you like to specify a value for Fan-In?");
			Scanner chose3 = new Scanner(System.in);
			String yn3 = chose3.nextLine();
			
			if(yn3.equals("y")) {
			System.out.println("Specify minimum number of fan-in connections in a class to be considered: ");
			Scanner fi = new Scanner(System.in);
			fanin = fi.nextInt();
			}
			
			System.out.println("Would you like to specify a value for Fan-Out?");
			Scanner chose4 = new Scanner(System.in);
			String yn4 = chose4.nextLine();
			
			if(yn4.equals("y")) {
			System.out.println("Specify minimum number of fan-out connections in a class to be considered: ");
			Scanner fo = new Scanner(System.in);
			fanout = fo.nextInt();
			}
			
			/*
			System.out.println("Specify value for lcom4 in a class to be considered: ");
			Scanner lc = new Scanner(System.in);
			lcom = lc.nextInt();
			
			System.out.println("Specify minimum number of children in a class to be considered: ");
			Scanner nc = new Scanner(System.in);
			noc = nc.nextInt();
			
			System.out.println("Specify minimum average cyclomatic complexity in a class to be considered: ");
			Scanner acc = new Scanner(System.in);
			avgcc = acc.nextInt();
			*/
			
			GetStringFile();
			
			System.out.println("");
			System.out.println("Would you like to print your results?");
			Scanner pr = new Scanner(System.in);
			String pri = pr.nextLine();
			
			if(pri.equals("y")) {
				print = true;
				GetStringFile();
			}
			else {
				System.out.println("bye bye!");
			}
		}
		else if (Thresholds.equals("print")){
			print = true;
			GetStringFile();
		}
		
		else if (Thresholds.equals("n")) {
			GetStringFile();
			
			System.out.println("");
			System.out.println("Would you like to print your results?");
			Scanner pr = new Scanner(System.in);
			String pri = pr.nextLine();
			
			if(pri.equals("y")) {
				print = true;
				GetStringFile();
			}
			else {
				System.out.println("bye bye!");
			}
		}
	}
	
	public static boolean validFiles(String src, String bin) {
		
		try {
		file2 = new File(src);
		file3 = new File(bin);
		
		javafiles = file2.listFiles();
		tempclassfiles = file3.listFiles();
		ArrayList<File> classfiles = new ArrayList<>();
		String[] srcnames = new String[file2.listFiles().length];
		
		boolean noclass = false;
		boolean nojava = false;
		
		for(int i = 0; i < javafiles.length; i++) {
			srcnames[i] = javafiles[i].getName().replaceFirst("[.][^.]+$", "");
			
			if(!FilenameUtils.getExtension(javafiles[i].getName()).equals("java")) {
				nojava = true;
			}
		}
		
		for(int j = 0; j < tempclassfiles.length; j++) {
			if(ArrayUtils.contains(srcnames, tempclassfiles[j].getName().replaceFirst("[.][^.]+$", ""))) {					
				classfiles.add(tempclassfiles[j]);
			}	
		}
		
		for(int i = 0; i < classfiles.size(); i++) {
			if(!FilenameUtils.getExtension(classfiles.get(i).getName()).equals("class")) {
				noclass = true;
			}
		}
		
		if(noclass || nojava) {
			System.out.println("error is here! One of the folders contains non java file");
			return false;
		}
		
		if(javafiles.length != classfiles.size()) {
			System.out.println("error is here! lenght not equal");
			return false;	
		}
		}
		catch(Exception e) {
			System.out.println("error is here! Exception");
			System.out.println(e);
			return false;
		}
		
		return true;
	}
	
	public static void setFiles(String src, String bin) {
		
		file2 = new File(src);
		file3 = new File(bin);
		
//		ArrayUtils.removeAll(javafiles, javafiles.length);
//		ArrayUtils.removeAll(tempclassfiles, tempclassfiles.length);
		
		javafiles = file2.listFiles();
		tempclassfiles = file3.listFiles();
		ArrayList<File> classfiles = new ArrayList<>();
		String[] srcnames = new String[file2.listFiles().length];
		
		for(int i = 0; i < javafiles.length; i++) {
			srcnames[i] = javafiles[i].getName().replaceFirst("[.][^.]+$", "");
		}

			for(int j = 0; j < tempclassfiles.length; j++) {
				if(ArrayUtils.contains(srcnames, tempclassfiles[j].getName().replaceFirst("[.][^.]+$", ""))) {					
					classfiles.add(tempclassfiles[j]);
				}
			}
			
		tempclassfiles = classfiles.toArray(new File[classfiles.size()]);	
	}
	
	public static File getFile() {
		return file2;
	}
	
	public static String Src () {		
		return file2.toString();
	}
	
	public static String Bin() {
		return file3.toString();
	}
	
	public static void SetBugs(int nofbugs) {
		bugs =  nofbugs;
	}
	
	public static int GetBugs() {
		return totbugs;
	}
	
	public static void setbin(File file) {
		current =  file.toString();
	}
	
	public static String getbin() {
		return current;
	}
	
	public static void SetXY(List xy) {
		XY = xy;
	}
	
	public static List getXY() {
		return XY;
	}
	
	public static void GetStringFile() {
		
		try {
		
	  	ArrayList<Integer> h = new ArrayList<>();
	  	Map<String,Set <String>> map = new HashMap<String, Set<String>>();
	  	
//	  	File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Whatever\\src\\BS.java");
//	  	File file = new File("C:\\Users\\anasz\\eclipse-workspace\\Whatever\\src\\BS.java");
	  	File file2 = new File("C:\\Users\\anasz\\eclipse-workspace\\Whatever\\src");
	  	File file3 = new File("C:\\Users\\anasz\\eclipse-workspace\\Whatever\\bin");
//        File file3 = new File("C:\\Users\\anasz\\eclipse-workspace\\ScalesProblem\\bin\\ScPb");
//        File file2 = new File("C:\\Users\\anasz\\eclipse-workspace\\ScalesProblem\\src\\ScPb");
        
        File[] listOfFiles = file2.listFiles();
        File[] listofFiles = file3.listFiles();
        
        for(int i = 0; i < listOfFiles.length; i++) {
        	       
        Scanner scanner = new Scanner(listOfFiles[i]);
        StringBuffer line0 = new StringBuffer();
        
        while(scanner.hasNextLine()) {
        line0.append(scanner.nextLine());
        line0.append("\n");}
        
        StringBuffer line = removecomments(line0);
	    
        if(print == false) {
        if(countlines(listOfFiles[i]) >= lines) {
        	if(CBO.numberofmethods(listofFiles[i], false) >= methods) {
        		if((Integer)CBO.fanout(listofFiles[i], map, listofFiles, false).get(2) >= cbo) {
        			if((Integer)CBO.fanout(listofFiles[i], map, listofFiles, false).get(0) >= fanin) {
        				if((Integer)CBO.fanout(listofFiles[i], map, listofFiles, false).get(1) >= fanout) {        					
        		System.out.println("Class: " + listOfFiles[i].getName());
        		Printing(line0.toString(),line,listOfFiles[i],listofFiles[i],listofFiles, listOfFiles);
        		System.out.println("");
        		
        				}}}
        	}
        }
        }
        
        if(print == true) {
//        	Excel.CreateFile(file2, file3, lines, methods, cbo);
        }
        }
        
		} catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void createExcel() {
//		Excel.CreateFile(file2, file3);
//		Excel.CreateFile2(row);
	}
	
	public static Object[] BuildTable(DefaultTableModel model) throws InterruptedException, IOException {
				
		Object[] row = new Object[15];
		
		try {	
		Map<String,Set <String>> map = new HashMap<String, Set<String>>();
		
/*		File[] listOfFiles = file2.listFiles();
        File[] listofFiles = file3.listFiles(); */
		
		File[] listOfFiles = javafiles;
        File[] listofFiles = tempclassfiles;
        
        for(int i = 0; i < listOfFiles.length; i++) {
        	       
        	Scanner scanner = new Scanner(listOfFiles[i]);
        	StringBuffer line0 = new StringBuffer();
        	
        	setbin(listofFiles[i]);
        	
        	while(scanner.hasNextLine()) {
        		line0.append(scanner.nextLine());
        		line0.append("\n");}
        		
        		StringBuffer line = removecomments(line0);
        		
        		String zero = listOfFiles[i].getName();
        		int one = countlines(listOfFiles[i]);
        		int two = countblanklines(line.toString());
        		int three = countsinglelinecomments(line.toString());
        		int four = countmultilinecomments(line0.toString());
        		int five = countidentation(listOfFiles[i]);
        		double six = Cyclomatic.complexity(line0.toString(), listOfFiles[i].getName(), listofFiles[i]);
//        		int six = cyclomaticcomplexity(line);
        		int seven = (int) CBO.fanout(listofFiles[i], map, listofFiles, false).get(0);
        		int eight = (int) CBO.fanout(listofFiles[i], map, listofFiles, false).get(1);
        		int nine = (int) CBO.fanout(listofFiles[i], map, listofFiles, true).get(2);
        		int ten = CBO.numberofmethods(listofFiles[i], true);
        		int eleven = Group.loadGroups(listofFiles[i]).size();
        		int twelve = NOC.noc(listofFiles,listofFiles[i],0);
        		int thirteen = NOC.DIT(listofFiles[i], 0, listofFiles);
//        		int twelve = NOC(listOfFiles[i], listOfFiles);
        		Application.setdir(listofFiles[i].toString());
        		new Application().findBugs();
        		int fourteen = bugs;
        		
        		if(one >= lines) {
        			if(six >= avgcc) {
        				if(seven >= fanin) {
        					if(eight >= fanout) {
        						if(nine >= cbo) {
        							if(ten >= methods) {
        								if(eleven >= lcom) {
        									if(twelve >= noc) {
        										if(thirteen >= dit) {
        											if(fourteen >= bug1) {
        												
//        												double sixteen = six/ ten;
        												row[0] = zero;
        												row[1] = one;
        												row[2] = two;
        												row[3] = three;
        												row[4] = four;
        												row[5] = five;
        												row[6] = six / ten;
        												row[7] = seven;
        												row[8] = eight;
        												row[9] = nine;
        												row[10] = ten;
        												row[11] = eleven;
        												row[12] = twelve;
        												row[13] = thirteen;
        												row[14] = fourteen;
        			
        										model.addRow(row);
        											}
        										}
        									}
        								}
        							}
        						}
        					}
        				}
        			}
        		}
        		totbugs = totbugs+bugs;
        }
        
        
        
		} catch (Exception e) {
	        return null;
	    }
		
//		Application.main(null);
		
		return row;
	}
	
	public static void Files (File file) {
		
		File[] listOfFiles = file.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
			    System.out.println("File " + listOfFiles[i].getName());
			  } else if (listOfFiles[i].isDirectory()) {
			    System.out.println("Directory " + listOfFiles[i].getName());
			  }
			}
	}
	
	  public static double Calculatecorrelation(int[] c1, int[] c2) {
		  
		    double sx = 0.0;
		    double sy = 0.0;
		    double sxx = 0.0;
		    double syy = 0.0;
		    double sxy = 0.0;

		    int n = c1.length;

		    for(int i = 0; i < n; ++i) {
		      double x = c1[i];
		      double y = c2[i];

		      sx += x;
		      sy += y;
		      sxx += x * x;
		      syy += y * y;
		      sxy += x * y;
		    }

		    // covariation
		    double cov = sxy / n - sx * sy / n / n;
		    // standard error of x
		    double sigmax = Math.sqrt(sxx / n -  sx * sx / n / n);
		    // standard error of y
		    double sigmay = Math.sqrt(syy / n -  sy * sy / n / n);

		    // correlation is just a normalized covariation
		    return cov / sigmax / sigmay;
		  }
	  
	  public static double Calculatecorrelation(double[] c1, int[] c2) {
		  
		    double sx = 0.0;
		    double sy = 0.0;
		    double sxx = 0.0;
		    double syy = 0.0;
		    double sxy = 0.0;

		    int n = c1.length;

		    for(int i = 0; i < n; ++i) {
		      double x = c1[i];
		      double y = c2[i];

		      sx += x;
		      sy += y;
		      sxx += x * x;
		      syy += y * y;
		      sxy += x * y;
		    }

		    // covariation
		    double cov = sxy / n - sx * sy / n / n;
		    // standard error of x
		    double sigmax = Math.sqrt(sxx / n -  sx * sx / n / n);
		    // standard error of y
		    double sigmay = Math.sqrt(syy / n -  sy * sy / n / n);

		    // correlation is just a normalized covariation
		    return cov / sigmax / sigmay;
		  }
	
	public static void Printing (String line0, StringBuffer line, File file, File file2, File[] file3, File[] file4) throws IOException {

		Map<String,Set <String>> map = new HashMap<String, Set<String>>();
		
        System.out.println("LOC: " + countlines(file));
        
        System.out.println("Blank Lines: " + countblanklines(line.toString()));
        
        System.out.println("Single Lines Comments: " + countsinglelinecomments(line.toString()));
        
        System.out.println("Multi Line Comments: " + countmultilinecomments(line0));
        
        System.out.println("Identation count: " + countidentation(file));
        
        System.out.println("CC: " + cyclomaticcomplexity(line));
        
        //TEST
        
        System.out.println("Fan In: " +  CBO.fanout(file2, map, file3, false).get(0));
        
        System.out.println("Fan Out: " +  CBO.fanout(file2, map, file3, false).get(1));
        
        System.out.println("CBO: " +  CBO.fanout(file2, map, file3, false).get(2));
        
        System.out.println("WMC: " + CBO.numberofmethods(file2, false));
        
        System.out.println("LCOM4: " + Group.loadGroups(file2).size());
        
        System.out.println("NOC: " + NOC(file, file4));

	}
	
	public static int countlines (File file) {
		int classes = 0;		
		try {
			Scanner scanner = new Scanner(file);
			while(classes >= 0) {
				if(scanner.nextLine() != null) {
					classes += 1;
			}
			else {
				scanner.close();
				break;
			}
			}
		} catch (Exception e) {	

		}
		return classes;
	}
	
	public static int countidentation(File file) {
		
		int identation = 0;
		
		try {
		Scanner scanner = new Scanner(file);
		
		while(scanner.hasNext()) {
					final StringBuilder sb = new StringBuilder(scanner.nextLine());
					while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
				        sb.deleteCharAt(0);
						identation++;
				}		
			}
		}catch(Exception e) {			
		}
		
		return identation;
	}
		
	public static int countmethods (StringBuffer line) {
		
		int count = 0;
		
		try {
			
		Pattern METHOD_PATTERN = Pattern.compile("(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])");
		
		CharSequence charSequence = line.toString();
		
		Matcher countEmailMatcher = METHOD_PATTERN.matcher(charSequence);
		
		while (countEmailMatcher.find()) {
		    count++;
		}
		
		
		} catch (Exception hello) {
			
			System.out.println(hello);
			System.out.println("Fucked up");
			return 0;
		}
	
		return count;
	}
	
	public static int cyclomaticcomplexity (StringBuffer line) {
		int count = 0;
		try {

			Pattern if_PATTERN = Pattern.compile("^[^/][^/].*(if|else if|else)\\s*\\(*.\\)*\\s*", Pattern.MULTILINE);
			Pattern switch_PATTERN = Pattern.compile("^[^/][^/].*(case|default)(.|\\w|\\s|\\d|:)", Pattern.MULTILINE);
		
			CharSequence charSequence = line.toString();
			
			Matcher countswitch = switch_PATTERN.matcher(charSequence);
			Matcher countif = if_PATTERN.matcher(charSequence);
			
			while (countswitch.find()) {
			    count++;
			}
			
			while (countif.find()) {
			    count++;
			}
			
			
			} catch (Exception hello) {
				
				System.out.println(hello);
				System.out.println("Fucked up");
				return 0;
			}
		return count;
		}
	
	public static StringBuffer removecomments(StringBuffer line) {
		
        String line2 = line.toString();
        String newline = line2.replaceAll("\\/\\*([\\S\\s]+?)\\*\\/", "");
        String lastline = newline.replaceAll("(?s)/\\*.*?\\*/", "");
        
        StringBuffer lline = new StringBuffer();
        lline.append(lastline);
        
        return lline;
	}
	
	public static int countmultilinecomments(String line) {
		
		int mlcomments = 0;
		
		Pattern mlc = Pattern.compile("\\/\\*([\\S\\s]+?)\\*\\/");
		
		CharSequence charSequence = line.toString();
		
		Matcher mlmatcher = mlc.matcher(charSequence);
		
		while (mlmatcher.find()) {

            String lines[] = mlmatcher.group(0).split("\n");
            for (String string : lines) {
                mlcomments++;
            }
        }
		
		return mlcomments;
	}
	
	public static int countsinglelinecomments (String line) {
		
		int comments  = 0;
		
		Pattern singlecomments = Pattern.compile("[/][/].*", Pattern.MULTILINE);
		
		CharSequence charsequence = line;
		
		Matcher scomments = singlecomments.matcher(charsequence);
		
		while(scomments.find()) {
			comments++;
		}
		
		return comments;
	}
	
	public static int NOC (File checkfile, File[] file) {
		
		int NOC = 0;
		
		StringBuffer nocomments = null;
		
		try {

		for(int i = 0; i < file.length; i++) {
		
		Scanner	scanner = new Scanner(file[i]);
	    StringBuffer line = new StringBuffer();
	    
	    while(scanner.hasNextLine()) {
	    line.append(scanner.nextLine());
	    line.append("\n");}
	    
	    nocomments = removecomments(line);

	    Pattern METHOD_PATTERN = Pattern.compile("extends " + checkfile.getName().replaceFirst("[.][^.]+$", ""));
		
		CharSequence charSequence = nocomments.toString();
		
		Matcher countEmailMatcher = METHOD_PATTERN.matcher(charSequence);
		
		while (countEmailMatcher.find()) {
			NOC++;
		}
		}
		
		} catch (FileNotFoundException e) {
		}
		
		return NOC;
	}
	
	public static int countblanklines (String line) {
		
		int blanks = 0;
		
		Pattern blanklines = Pattern.compile("^\\s*$", Pattern.MULTILINE);

		CharSequence charSequence = line;
		
		Matcher blines = blanklines.matcher(charSequence);
		
		while (blines.find()) {
			blanks++;
		}

		return blanks;
	}
	
}

