package RefactOK;
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

public class Controller {
	
	//all attributes used in current class
	static int lines = 0;
	static int methods = 0;
	static int cbo = 0;
	static int fanin = 0;
	static int fanout = 0;
	static int lcom = 0;
	static int noc = 0;
	static double avgcc = 0.0;
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
	
	//Metric threshold setters
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
	public static void SetAVGCC(double a) {
		avgcc = a;
		System.out.println("In WeMovin is: "+avgcc);
	}
	public static void SetDIT(int d) {
		dit = d;
	}
	public static void SetB(int b) {
		bug1 = b;
	}
	
	//check files set in the front end to find erroneous entries
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
			
			//get only class name with no extension
			srcnames[i] = javafiles[i].getName().replaceFirst("[.][^.]+$", "");
			
			//catch any file that is not a java class
			if(!FilenameUtils.getExtension(javafiles[i].getName()).equals("java")) {
				nojava = true;
			}
		}
		
		//find all byte code classes of the respective java classes
		for(int j = 0; j < tempclassfiles.length; j++) {
			if(ArrayUtils.contains(srcnames, tempclassfiles[j].getName().replaceFirst("[.][^.]+$", ""))) {					
				classfiles.add(tempclassfiles[j]);
			}	
		}
		
		//catch any non byte code class in the folder
		for(int i = 0; i < classfiles.size(); i++) {
			if(!FilenameUtils.getExtension(classfiles.get(i).getName()).equals("class")) {
				noclass = true;
			}
		}
		
		//find if any folder path doesn't contain the right files
		if(noclass || nojava) {
			System.out.println("error is here! One of the folders contains non java or non class files");
			return false;
		}
		
		//find if number of classes is not the same in both folders
		if(javafiles.length != classfiles.size()) {
			System.out.println("error is here! lenght not equal");
			return false;	
		}
		}
		//catch any possible exception
		catch(Exception e) {
			System.out.println("error is here! Exception");
			System.out.println(e);
			return false;
		}
		
		return true;
	}
	
	//set files set in the frontend after validation from previous method
	public static void setFiles(String src, String bin) {
		
		file2 = new File(src);
		file3 = new File(bin);
		
		javafiles = file2.listFiles();
		tempclassfiles = file3.listFiles();
		ArrayList<File> classfiles = new ArrayList<>();
		String[] srcnames = new String[file2.listFiles().length];
		
		//get all java classes with no extension in the variable file2
		for(int i = 0; i < javafiles.length; i++) {
			srcnames[i] = javafiles[i].getName().replaceFirst("[.][^.]+$", "");
		}

		//get all byte code classes with no extension in the variable file3
			for(int j = 0; j < tempclassfiles.length; j++) {
				if(ArrayUtils.contains(srcnames, tempclassfiles[j].getName().replaceFirst("[.][^.]+$", ""))) {					
					classfiles.add(tempclassfiles[j]);
				}
			}
			
		tempclassfiles = classfiles.toArray(new File[classfiles.size()]);	
	}
	
	//file getters
	public static File getFile() {
		return file2;
	}
	
	public static String Src () {		
		return file2.toString();
	}
	
	public static String Bin() {
		return file3.toString();
	}
	
	//bugs getters and setters
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
	
	//correlation setter and getter
	public static void SetXY(List xy) {
		XY = xy;
	}
	
	public static List getXY() {
		return XY;
	}
	
	//method to build the table containing metric values
	public static Object[] BuildTable(DefaultTableModel model) throws InterruptedException, IOException {
				
		Object[] row = new Object[15];
		
		try {	
		Map<String,Set <String>> map = new HashMap<String, Set<String>>();
		
		File[] listOfFiles = javafiles;
        File[] listofFiles = tempclassfiles;
        
        //loop trhough every class in the folder path to calculate metrics
        for(int i = 0; i < listOfFiles.length; i++) {
        	       
        	Scanner scanner = new Scanner(listOfFiles[i]);
        	StringBuffer line0 = new StringBuffer();
        	
        	setbin(listofFiles[i]);
        	
        	//get content of java class in a stringbuffer
        	while(scanner.hasNextLine()) {
        		line0.append(scanner.nextLine());
        		line0.append("\n");}
        		
        		StringBuffer line = removecomments(line0);
        		
        		//calculate all metrics by calling respective methods
        		String zero = listOfFiles[i].getName();
        		int one = countlines(listOfFiles[i]);
        		int two = countblanklines(line.toString());
        		int three = countsinglelinecomments(line.toString());
        		int four = countmultilinecomments(line0.toString());
        		int five = countidentation(listOfFiles[i]);
        		double six = Cyclomatic.complexity(line0.toString(), listOfFiles[i].getName(), listofFiles[i]);
        		int seven = (int) CBO.fanout(listofFiles[i], map, listofFiles, false).get(0);
        		int eight = (int) CBO.fanout(listofFiles[i], map, listofFiles, false).get(1);
        		int nine = (int) CBO.fanout(listofFiles[i], map, listofFiles, true).get(2);
        		//temprarily not used due to methods being calcualted in AST 
//        		int ten = CBO.numberofmethods(listofFiles[i], true);
        		int ten = Cyclomatic.wmc(line0.toString(), listOfFiles[i].getName(), true);
        		int eleven = Group.loadGroups(listofFiles[i]).size();
        		int twelve = NOC.noc(listofFiles,listofFiles[i],0);
        		int thirteen = NOC.DIT(listofFiles[i], 0, listofFiles);
        		Application.setdir(listofFiles[i].toString());
        		new Application().findBugs();
        		int fourteen = bugs;
        		
        		//threshold filtering to ensure only classes with right amount of metric are included in the table
        		if(one >= lines) {
        			if(six/ten >= avgcc) {
        				if(seven >= fanin) {
        					if(eight >= fanout) {
        						if(nine >= cbo) {
        							if(ten >= methods) {
        								if(eleven >= lcom) {
        									if(twelve >= noc) {
        										if(thirteen >= dit) {
        											if(fourteen >= bug1) {
        												
        												//enter values in the table if meet threshold
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
        										
        										//add object to model in the frontend
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
		
		return row;
	}
	
	//method to calculate correlation between 2 metric values
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
	  
	  //method to calculate correlation between AVGCC and any other metric value
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
	
	//method to count lines in a class
	public static int countlines (File file) {
		int classes = 0;		
		try {
			Scanner scanner = new Scanner(file);
			while(classes >= 0) {
				if(scanner.nextLine() != null) {
					
					//calculate lines if next line is not null
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
	
	//method to output indentation count in a class
	public static int countidentation(File file) {
		
		int identation = 0;
		
		try {
		Scanner scanner = new Scanner(file);
		
		while(scanner.hasNext()) {
					final StringBuilder sb = new StringBuilder(scanner.nextLine());
					//find white spaces in the line and count them in indentation
					while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
				        sb.deleteCharAt(0);
						identation++;
				}		
			}
		}catch(Exception e) {			
		}
		
		return identation;
	}
	
	//method to remove comments from any given class file
	public static StringBuffer removecomments(StringBuffer line) {
		
        String line2 = line.toString();
        
        //replace all comment lines with empy "" thus remove them all
        String newline = line2.replaceAll("\\/\\*([\\S\\s]+?)\\*\\/", "");
        String lastline = newline.replaceAll("(?s)/\\*.*?\\*/", "");
        
        StringBuffer lline = new StringBuffer();
        lline.append(lastline);
        
        return lline;
	}
	
	//method to count multi line comments in a class
	public static int countmultilinecomments(String line) {
		
		int mlcomments = 0;
		
		//regex for chunk of multiline comments
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
	
	//method to count single line comments in a class
	public static int countsinglelinecomments (String line) {
		
		int comments  = 0;
		
		//regex of single line comments
		Pattern singlecomments = Pattern.compile("[/][/].*", Pattern.MULTILINE);
		
		CharSequence charsequence = line;
		
		Matcher scomments = singlecomments.matcher(charsequence);
		
		while(scomments.find()) {
			comments++;
		}
		
		return comments;
	}
	
	//Method to count number of blank lines in a class
	public static int countblanklines (String line) {
		
		int blanks = 0;
		
		//regex to find empy line
		Pattern blanklines = Pattern.compile("^\\s*$", Pattern.MULTILINE);

		CharSequence charSequence = line;
		
		Matcher blines = blanklines.matcher(charSequence);
		
		while (blines.find()) {
			//add 1 to counter for every blank line found
			blanks++;
		}

		return blanks;
	}
	
}

