import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		//int x = 0;
		Scanner in = new Scanner(new File("src\\data.csv"));
		
		List<Double> names = new ArrayList<Double>();
		in.useDelimiter(",");
		
		while(in.hasNext()) {
			names.add(Double.parseDouble(in.nextLine()));	
		}
		for(int i=0; i<names.size();i++) {
			System.out.println("Sample number:      " +(i+1) + ";                  Sample value: " + names.get(i));
		}
		in.close();
		
	}

}
 