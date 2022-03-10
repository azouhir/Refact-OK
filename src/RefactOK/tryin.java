package RefactOK;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class tryin {

	public static void main(String[] args) {
		
		StringBuffer sb = new StringBuffer();
		String s = null;

        
		try {
			BufferedReader file = new BufferedReader(new FileReader("C:\\Users\\anasz\\eclipse-workspace\\Whatever\\src\\BS.java"));
			Scanner file2 = new Scanner(file);
			
//		    file = new BufferedReader(new FileReader(args[2]));
		    int i;
		    while ((i=file.read())!=-1) {
		            sb.append(file2.nextLine());
		    }

		    System.out.println(sb.toString());

		 } catch (IOException e) {
			 System.out.println("Error!");
		        e.printStackTrace();
		 }

	}

}
