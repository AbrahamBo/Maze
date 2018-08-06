package maze.test;

import java.util.*;
import maze.service.MazeImpl;
import maze.entity.*;
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		//int index = 1;
		//while(index == 1){
		System.out.println("Please input the Road Grid Size:");
		String gridSize = s.nextLine();
		System.out.println("Please input the Grid Path:");
		String gridPath = s.nextLine();
		MazeImpl mazeImpl = new MazeImpl();
		mazeImpl.isCorrectInput(gridSize, gridPath);
		mazeImpl.creMazeRoad(gridSize, gridPath);
		mazeImpl.printMaze();	
			
			//System.out.println("Whether to continue the program:Please input '1'.");
			//index = s.nextInt();
		//}
		
		
		//mazeImpl.isCommFormat(gridSize, gridPath);
	}
}
