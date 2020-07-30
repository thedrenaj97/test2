public class Main {

	public static void main(String[] args) {
		
		int [] [] data = {
				{4, 6, 3, 10},
				{2, 2, 40, 1, 99, 77},
				{0, 34, 1, 5, 0}
		};
 		
		for(int i=0; i<data.length;i++) {
			for(int j=0;j<data[i].length;j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}
 