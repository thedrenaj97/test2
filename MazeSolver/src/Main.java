import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
	
	
	public static void main(String[] args) {
		
		//0 - wall
		//1 - path
		//2 - destination
				
		// Declaring the list of mazes
		ArrayList<Maze> mazes = new ArrayList<Maze>();
		
		// Declaring Maze 1
		Maze m = new Maze();
				
		int[][] maze = {
			{1,1,1,1,0,1,1,1,0,1,0},
			{0,0,1,1,1,1,0,0,0,1,0},
			{0,0,0,1,0,1,1,0,1,1,1},
			{1,1,1,2,0,1,1,1,0,1,0},
			{0,0,0,1,0,0,0,0,0,1,0},
			{0,0,0,1,1,1,1,1,1,0,1}
		};
		m.maze = maze;
		m.start = new Position(4,8);
		m.path = new LinkedList<Position>();
		
		// Declaring Maze 2
		Maze n = new Maze();
		
		int[][] n_maze = {
			{1,1,1,1,0,1,1,1,0,1,0},
			{0,0,1,1,1,1,0,0,0,1,0},
			{0,0,0,1,0,1,1,0,1,1,1},
			{1,1,1,2,0,1,1,1,0,1,0},
			{0,0,0,1,0,0,0,0,0,1,0},
			{0,0,0,1,1,1,1,1,1,0,1}
		};
		n.maze = n_maze;
		n.start = new Position(4,8);
		n.path = new LinkedList<Position>();
		
		// Adding Mazes
		mazes.add(m);
		mazes.add(n);
		
		
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
