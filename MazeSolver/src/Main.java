import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//0 - wall
		//1 - path
		//2 - destination
				
		// Declaring the list of mazes
		ArrayList<Maze> mazes = readMazes(); 
		
		// Looping through the list of mazes
		int i = 0;
		while( i<mazes.size()) {
			if(solveMaze(mazes.get(i))) {
				System.out.println("YOU WIN");
			}else {
				System.out.println("No path");
			}
			System.out.println();
			i++;
		}
	}

	
	
	
	private static ArrayList<Maze> readMazes() throws FileNotFoundException {
		
		ArrayList<Maze> mazes = new ArrayList<Maze>();
		
		Scanner in = new Scanner(new File ("src/mazes.txt"));
		
		while(in.hasNext()) {
			
			Maze m = new Maze();
			int rows = Integer.parseInt(in.nextLine());
			
			m.maze = new int[rows][];
			
			
			// loop
			for(int i=0; i<rows; i++) {
				String line = in.nextLine();
				m.maze[i] = Arrays.stream(line.split(", ")).mapToInt(Integer::parseInt).toArray();
			}
			
			m.start = new Position(Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine()));
			in.nextLine();
			
			mazes.add(m);
		}
			
		in.close();
		return mazes;
	}

	// Function to solve the current maze
	private static boolean solveMaze(Maze m) {
	
		Position p = m.start;				// taking position from the object
		m.path.push(p); 					// push needed for FILO - stack
		
		// Moving through the maze
		while(true) {
			
			// Getting current position
			int x_p = m.path.peek().x;
			int y_p = m.path.peek().y;
			
			// Changing current position to 0, so we do not go back in loops
			m.maze[y_p][x_p] = 0;
			
			// Moving down, if possible
			if(isValid(y_p+1,x_p,m)) {
				if(m.maze[y_p+1][x_p] == 2) {
					System.out.println("Moved down");
					return true;						// 2 found - you win!
				} else if(m.maze[y_p+1][x_p] == 1) {
					System.out.println("Moved down");
					m.path.push(new Position(y_p+1, x_p));
					continue;							// 1 found - keep moving
				}
			}
			
			// Moving left, if possible
			if(isValid(y_p, x_p-1,m)) {
				if(m.maze[y_p][x_p-1] == 2) {
					System.out.println("Moved left");
					return true;						// 2 found - you win!
				} else if(m.maze[y_p][x_p-1] == 1) {
					System.out.println("Moved left");
					m.path.push(new Position(y_p, x_p-1));
					continue;							// 1 found - keep moving
				}
			}
		
			// Moving up, if possible
			if(isValid(y_p-1, x_p,m)) {
				if(m.maze[y_p-1][x_p] == 2) {
					System.out.println("Moved up");
					return true;						// 2 found - you win!
				} else if(m.maze[y_p-1][x_p] == 1) {
					System.out.println("Moved up");
					m.path.push(new Position(y_p-1, x_p));
					continue;							// 1 found - keep moving
				}	
			}
			
			// Moving right, if possible
			if(isValid(y_p, x_p+1,m)) {
				if(m.maze[y_p][x_p+1] == 2) {
					System.out.println("Moved right");
					return true;						// 2 found - you win!
				} else if(m.maze[y_p][x_p+1] == 1) {
					System.out.println("Moved right");
					m.path.push(new Position(y_p, x_p+1));
					continue;							// 1 found - keep moving
				}
			}

			// If we cannot move anywhere, we are stuck, so go back
			m.path.pop();						// removing current position from the stack
			System.out.println("Moved Back");	// declaring back move
			if(m.path.size()<=0) {
				return false;					// if there are no more places to backtrack
			}									// there is no path to find
		}		
	}


	// Function to check for out of bounds
	public static boolean isValid(int y, int x, Maze m) {
		if(y < 0 || 	
			y >= m.maze.length || 
			x < 0 || 
			x >= m.maze[y].length
		 ) {
			return false;	// out of bounds
		}
		return true;		// not out of bounds
	}
	
}
