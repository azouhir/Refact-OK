
public class Output {

	static StringBuffer ccoutput = new StringBuffer();
	static StringBuffer fooutput = new StringBuffer();
	static StringBuffer fioutput = new StringBuffer();
	static StringBuffer methoutput = new StringBuffer();
	static StringBuffer nocoutput = new StringBuffer();
	static StringBuffer ditoutput = new StringBuffer();
	static StringBuffer bugoutput = new StringBuffer();
	
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
