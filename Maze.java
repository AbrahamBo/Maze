package maze.entity;

public class Maze {
	private final int roadGridR;
	private final int roadGridC;
	private int[][] maze;  
	//Maze���췽��������road grid���� render grid
	public Maze(int r, int c){
		this.roadGridR = r;
		this.roadGridC = c;
		this.maze = new int[2*r+1][2*c+1];
		//�Թ����ݳ�ʼ������·����ֵΪ��[R]������Ⱦ������Ϊ��[W]��
		for(int i = 0; i < r ; i++){
			for(int j = 0; j < c; j++){
				this.maze[2*i+1][2*j+1] = 1;
			}
		}
		
	}
	
	public int[][] getMaze() {
		return maze;
	}

	public void setMaze(int[][] maze) {
		this.maze = maze;
	}

	public int getRoadGridR() {
		return roadGridR;
	}

	public int getRoadGridC() {
		return roadGridC;
	}
}
