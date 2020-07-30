import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	
	static int[][] maze = {
		{2,1,1,1},
		{0,0,0,1},
		{1,1,1,1}
	};
	//0 - wall
	//1 - path
	//2 - destination
	
	static LinkedList<Position> path = new LinkedList<Position>();
	
	public static void main(String[] args) {
		
		
		Position p = new Position(0,3);
		path.push(p); 						//push needed for FILO
	
		while(true) {
			
			int x_p = path.peek().x;
			int y_p = path.peek().y;
			
			maze[y_p][x_p] = 0;
			
			//down
			if(isValid(y_p+1,x_p)) {
				if(maze[y_p+1][x_p] == 2) {
					System.out.println("Moved down - You WIN");
					return;
				} else if(maze[y_p+1][x_p] == 1) {
					System.out.println("Moved down");
					path.push(new Position(y_p+1, x_p));
					continue;
				}
			}
			
			//left
			if(isValid(y_p, x_p-1)) {
				if(maze[y_p][x_p-1] == 2) {
					System.out.println("Moved left - You WIN");
					return;
				} else if(maze[y_p][x_p-1] == 1) {
					System.out.println("Moved left");
					path.push(new Position(y_p, x_p-1));
					continue;
				}
			}
		
			//up
			if(isValid(y_p-1, x_p)) {
				if(maze[y_p-1][x_p] == 2) {
					System.out.println("Moved up - You WIN");
					return;
				} else if(maze[y_p-1][x_p] == 1) {
					System.out.println("Moved up");
					path.push(new Position(y_p-1, x_p));
					continue;
				}
			}
			
			//right
			if(isValid(y_p, x_p+1)) {
				if(maze[y_p][x_p+1] == 2) {
					System.out.println("Moved right - You WIN");
					return;
				} else if(maze[y_p][x_p+1] == 1) {
					System.out.println("Moved right");
					path.push(new Position(y_p, x_p+1));
					continue;
				}
			}

			path.pop();
			System.out.println("Moved Back");
			if(path.size()<=0) {
				System.out.println("No path");
				return;
			}
		}
	}

	public static boolean isValid(int y, int x) {
		if(y < 0 || 	
			y >= maze.length || 
			x < 0 || 
			x >= maze[y].length
		 ) {
			return false;
		}
		return true;
	}
	
}
