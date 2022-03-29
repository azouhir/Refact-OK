package RefactOK;

public class Output {

	static StringBuffer ccoutput = new StringBuffer();
	static StringBuffer fooutput = new StringBuffer();
	static StringBuffer fioutput = new StringBuffer();
	static StringBuffer methoutput = new StringBuffer();
	static StringBuffer nocoutput = new StringBuffer();
	static StringBuffer ditoutput = new StringBuffer();
	static StringBuffer bugoutput = new StringBuffer();
	static StringBuffer lcomoutput = new StringBuffer();
	
	//method that empties all the strings, called when new analysis is run
	public static void trigger() {
		ccoutput.delete(0, ccoutput.length());
		fooutput.delete(0, fooutput.length());
		fioutput.delete(0, fioutput.length());
		methoutput.delete(0, methoutput.length());
		nocoutput.delete(0, nocoutput.length());
		ditoutput.delete(0, ditoutput.length());
		bugoutput.delete(0, bugoutput.length());
		lcomoutput.delete(0, lcomoutput.length());
	}
	
	//setters to set the string with the affected code areas (called by model classes)	
	//getters to get string with affected code areas to display in instruction (called by frontend)
	
	public static void setlcomoutput(String lcom) {
		lcomoutput.append(lcom);
		lcomoutput.append("\n");
		lcomoutput.append("");
	}
	
	public static String getlcomoutput() {		
		return lcomoutput.toString();
	}
	
	public static void setccoutput(String cc) {
		ccoutput.append(cc);
		ccoutput.append("\n");
		ccoutput.append("");
	}
	
	public static String getccoutput() {		
		return ccoutput.toString();
	}
	
	public static void setfooutput(String fo) {
		fooutput.append(fo);
		fooutput.append("\n");
		fooutput.append("");
	}
	
	public static String getfooutput() {
		return fooutput.toString();
	}
	
	public static void setfioutput(String fi) {
		fioutput.append(fi);
		fioutput.append("\n");
		fioutput.append("");
	}
	
	public static String getfioutput() {
		return fioutput.toString();
	}
	
	public static void setmethoutput(String meth) {
		methoutput.append(meth);
		methoutput.append("\n");
		methoutput.append("");
	}
	
	public static String getmethoutput() {
		return methoutput.toString();
	}
	
	public static void setnocoutput(String noc) {
		nocoutput.append(noc);
		nocoutput.append("\n");
		nocoutput.append("");
	}
	
	public static String getnocoutput() {
		return nocoutput.toString();
	}
	
	public static void setditoutput(String dit) {
		ditoutput.append(dit);
		ditoutput.append("\n");
		ditoutput.append("");
	}
	
	public static String getditoutput() {
		return ditoutput.toString();
	}
	
	public static void setbugoutput(String bug) {
		bugoutput.append(bug);
		bugoutput.append("\n");
		bugoutput.append("");
	}
	
	public static String getbugoutput() {
		return bugoutput.toString();
	}
}
