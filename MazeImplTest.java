package maze.test;

import static org.junit.Assert.*;

import java.util.Scanner;

import maze.service.MazeImpl;

import org.junit.Test;

public class MazeImplTest {
	MazeImpl mazeImpl = new MazeImpl();
	@Test
	public void testCreMazeRoad() {
		//Scanner s = new Scanner(System.in);
		//System.out.println("Please input the Road Grid Size:");
		//String gridSize = s.nextLine();
		//System.out.println("Please input the Grid Path:");
		//String gridPath = s.nextLine();
		String gridSize = "3 3";
		String gridPath = "0,1 0,2;0,0 1,0;0,1 1,1;0,2 1,2;1,0 1,1;1,1 1,2;1,1 2,1;1,2 2,2;2,0 2,1";
		mazeImpl.creMazeRoad(gridSize, gridPath);
		mazeImpl.printMaze();
	}

	@Test
	public void testIsNumFormat() {
		String gridSize = "- 3";
		String gridPath = "0,a x,2;0,0 1,0;0,1 1,1;0,2 1,2;1,0 1,1;1,1 1,2";
		mazeImpl.isNumFormat(gridSize, gridPath);
	}

	@Test
	public void testIsCommFormat() {
		String gridSize = "3 3";
		String gridPath = "0 1;0 2   0,0 1,0   0 1;1 1";
		mazeImpl.isCommFormat(gridSize, gridPath);
	}

	@Test
	public void testIsOutRange() {
		String gridSize = "3 3";
		String gridPath = "6,1 5,1;0,0 1,0";
		mazeImpl.isOutRange(gridSize, gridPath);
	}
	
	@Test
	public void testIsMazeFormat() {
		String gridSize = "3 3";
		String gridPath = "0,1 2,2;0,3 1,0";
		mazeImpl.isMazeFormat(gridPath);
	}
	
	@Test
	public void testIsCorrectInput() {
		String gridSize = "4 [[";
		String gridPath = "0,100 2,2;0,3 1,0;;";
		mazeImpl.isCorrectInput(gridSize, gridPath);
	}
}
