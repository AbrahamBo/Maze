package maze.service;

import maze.entity.Maze;
import java.lang.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MazeImpl {
	Maze maze = null;
	public Maze creMazeRoad(String gridSize, String gridPath){
		//通过道路网格尺寸构建Maze，并实现Maze初始化
		String[] arrSize = gridSize.split("\\s+");
		int row = Integer.parseInt(arrSize[0]);
		int col = Integer.parseInt(arrSize[1]);
		maze = new Maze(row,col);
		int[][] arrTemp = maze.getMaze();
		//按照输入数据格式 将路径字符串处理为整数
		String gridPathNew = gridPath.replaceAll(",","").replaceAll(" ","").replaceAll(";","");
		int[] gridPathCode = new int[gridPathNew.length()];
		for(int i=0;i<gridPathNew.length();i++){
			gridPathCode[i]=Integer.parseInt(String.valueOf(gridPathNew.charAt(i)));
		}
		//逻辑判断，横坐标或纵坐标前后绝对值相差1，且另一坐标相同，判断为存在迷宫路径，将两点间的网格置为通路‘1’
		for(int j = 0; j <= gridPathCode.length-1; j = j+4){
			if(gridPathCode[j]==gridPathCode[j+2] && Math.abs(gridPathCode[j+1]-gridPathCode[j+3])==1){
				if(gridPathCode[j+1]<gridPathCode[j+3]){
					arrTemp[2*gridPathCode[j]+1][2*gridPathCode[j+1]+2] = 1;
				}else{
					arrTemp[2*gridPathCode[j]+1][(2*gridPathCode[j+3]+1)+1] = 1;
				}
			}else if(gridPathCode[j+1]==gridPathCode[j+3] && Math.abs(gridPathCode[j]-gridPathCode[j+2])==1){
				if(gridPathCode[j]<gridPathCode[j+2]){
					arrTemp[2*gridPathCode[j]+2][2*gridPathCode[j+1]+1] = 1;
				}else{
					arrTemp[2*gridPathCode[j+2]+2][2*gridPathCode[j+1]+1] = 1;
				}
			}else{
				//若不符合上述条件，则判断为连通性错误，两道路网格不符合连通规范
				System.out.println("Maze format error.");
				System.exit(0);
			}
		}
		maze.setMaze(arrTemp);
		return maze;
	}
	//打印迷宫路径，maze存为1的通路点打印[R]，存为0的墙点打印为[W]
	public void printMaze(){
		int[][] temp = maze.getMaze();
		for(int i = 0; i<=temp.length-1; i++){
			for(int j = 0; j<=temp[1].length-1; j++){
				if(temp[i][j] == 1){
					System.out.print("[R]");
				}else if(temp[i][j]==0){
					System.out.print("[W]");
				}
			}
			System.out.print("\n");
		}
		return;
	}
	//判断输入数据是否符合规范
	public void isCorrectInput(String gridSize, String gridPath){
		if(gridSize.isEmpty()){
			System.out.println("Input Grid Size is empty.");
			System.exit(0);
		}else if(gridPath.isEmpty()){
			System.out.println("Input Grid Path is empty.");
			System.exit(0);
		}
		this.isNumFormat(gridSize, gridPath);
		this.isCommFormat(gridSize, gridPath);
		this.isOutRange(gridSize, gridPath);
		this.isMazeFormat(gridPath);
	}
	//判断输入数据是否为无效数字
	public void isNumFormat(String gridSize, String gridPath){
		String grid = gridSize + gridPath;
		String gridPathCode = grid.replaceAll(",","").replaceAll(" ","").replaceAll(";","");
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(gridPathCode);
        if( !isNum.matches() ){
            System.out.println("Invalid number format.");
            System.exit(0);
        }else{
        	return;
        }
	}
	
	//判断输入命令是否符合格式
	public void isCommFormat(String gridSize, String gridPath){
		String[] arrSize = gridSize.split("\\s+");
		String[] pathFormat1 = null;
		String[] pathFormat2 = null;
		String[] pathFormat3 = null;
		pathFormat1 = gridPath.split(";");
		int index = 0;
		for(int i = 0; i <= gridPath.length()-1; i++){
			if(gridPath.substring(i,(i+1)).indexOf(';') != -1){
				index++;
			}
		}
		//网格尺寸应为两个空格隔开的整数 && 被“;”分割开的句子数量应为 分号数量+1
		if(arrSize.length != 2 || pathFormat1.length != (index+1)){
			System.out.println("Incorrect command format.");
			System.exit(0);
		}
		
		for(int i = 0; i <= pathFormat1.length-1; i++){
			pathFormat2 = pathFormat1[i].split("\\s+");
			if(pathFormat2.length != 2){
				System.out.println("Incorrect command format.");
				System.exit(0);
			}
			for(int j = 0; j <= pathFormat2.length-1; j++){
				pathFormat3 = pathFormat2[j].split("\\,");
				if(pathFormat3.length == 2){
					break;
				}else{
					System.out.println("Incorrect command format.");
					System.exit(0);
				}
			}
		}
		
		return;
	}
	//判断输入数字是否超过允许范围
	public void isOutRange(String gridSize, String gridPath){
		//得到道路网格行和列
		String[] arrSize = gridSize.split("\\s+");
		int row = Integer.parseInt(arrSize[0]);
		int col = Integer.parseInt(arrSize[1]);
		//得到路径编码
		String gridPathNew = gridPath.replaceAll(";","").replaceAll(" ","").replaceAll(",","");
		int[] gridPathCode = new int[gridPathNew.length()];
		for(int i=0;i<gridPathNew.length();i++){
			gridPathCode[i]=Integer.parseInt(String.valueOf(gridPathNew.charAt(i)));
		}
		//判断网格尺寸是否符合标准
		if(row <= 0 || col <= 0){
			System.out.println("Number out of range.");
			System.exit(0);
		}
		//判断路径数值是否超过允许范围
		for(int i=0; i <= gridPathCode.length-2; i++){
			if(gridPathCode[i]<0 || gridPathCode[i]>=row || gridPathCode[i+1]<0 || gridPathCode[i+1]>=col){
				System.out.println("Number out of range.");
				System.exit(0);
			}
		}
		return;
	}
	public void isMazeFormat(String gridPath){
		//按照输入数据格式 将路径字符串处理为整数
		String gridPathNew = gridPath.replaceAll(",","").replaceAll(" ","").replaceAll(";","");
		int[] gridPathCode = new int[gridPathNew.length()];
		for(int i=0;i<gridPathNew.length();i++){
			gridPathCode[i]=Integer.parseInt(String.valueOf(gridPathNew.charAt(i)));
		}
		//逻辑判断，横坐标或纵坐标前后绝对值相差1，且另一坐标相同，判断为存在迷宫路径，将两点间的网格置为通路‘1’
		for(int j = 0; j <= gridPathCode.length-1; j = j+4){
			if(gridPathCode[j]==gridPathCode[j+2] && Math.abs(gridPathCode[j+1]-gridPathCode[j+3])==1){
				break;
			}else if(gridPathCode[j+1]==gridPathCode[j+3] && Math.abs(gridPathCode[j]-gridPathCode[j+2])==1){
				break;
			}else{
				//若不符合上述条件，则判断为连通性错误，两道路网格不符合连通规范
				System.out.println("Maze format error.");
				System.exit(0);
			}
		}
		return;
	}
}