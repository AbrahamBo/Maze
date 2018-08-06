package maze.service;

import maze.entity.Maze;
import java.lang.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MazeImpl {
	Maze maze = null;
	public Maze creMazeRoad(String gridSize, String gridPath){
		//ͨ����·����ߴ繹��Maze����ʵ��Maze��ʼ��
		String[] arrSize = gridSize.split("\\s+");
		int row = Integer.parseInt(arrSize[0]);
		int col = Integer.parseInt(arrSize[1]);
		maze = new Maze(row,col);
		int[][] arrTemp = maze.getMaze();
		//�����������ݸ�ʽ ��·���ַ�������Ϊ����
		String gridPathNew = gridPath.replaceAll(",","").replaceAll(" ","").replaceAll(";","");
		int[] gridPathCode = new int[gridPathNew.length()];
		for(int i=0;i<gridPathNew.length();i++){
			gridPathCode[i]=Integer.parseInt(String.valueOf(gridPathNew.charAt(i)));
		}
		//�߼��жϣ��������������ǰ�����ֵ���1������һ������ͬ���ж�Ϊ�����Թ�·������������������Ϊͨ·��1��
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
				//���������������������ж�Ϊ��ͨ�Դ�������·���񲻷�����ͨ�淶
				System.out.println("Maze format error.");
				System.exit(0);
			}
		}
		maze.setMaze(arrTemp);
		return maze;
	}
	//��ӡ�Թ�·����maze��Ϊ1��ͨ·���ӡ[R]����Ϊ0��ǽ���ӡΪ[W]
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
	//�ж����������Ƿ���Ϲ淶
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
	//�ж����������Ƿ�Ϊ��Ч����
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
	
	//�ж����������Ƿ���ϸ�ʽ
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
		//����ߴ�ӦΪ�����ո���������� && ����;���ָ�ľ�������ӦΪ �ֺ�����+1
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
	//�ж����������Ƿ񳬹�����Χ
	public void isOutRange(String gridSize, String gridPath){
		//�õ���·�����к���
		String[] arrSize = gridSize.split("\\s+");
		int row = Integer.parseInt(arrSize[0]);
		int col = Integer.parseInt(arrSize[1]);
		//�õ�·������
		String gridPathNew = gridPath.replaceAll(";","").replaceAll(" ","").replaceAll(",","");
		int[] gridPathCode = new int[gridPathNew.length()];
		for(int i=0;i<gridPathNew.length();i++){
			gridPathCode[i]=Integer.parseInt(String.valueOf(gridPathNew.charAt(i)));
		}
		//�ж�����ߴ��Ƿ���ϱ�׼
		if(row <= 0 || col <= 0){
			System.out.println("Number out of range.");
			System.exit(0);
		}
		//�ж�·����ֵ�Ƿ񳬹�����Χ
		for(int i=0; i <= gridPathCode.length-2; i++){
			if(gridPathCode[i]<0 || gridPathCode[i]>=row || gridPathCode[i+1]<0 || gridPathCode[i+1]>=col){
				System.out.println("Number out of range.");
				System.exit(0);
			}
		}
		return;
	}
	public void isMazeFormat(String gridPath){
		//�����������ݸ�ʽ ��·���ַ�������Ϊ����
		String gridPathNew = gridPath.replaceAll(",","").replaceAll(" ","").replaceAll(";","");
		int[] gridPathCode = new int[gridPathNew.length()];
		for(int i=0;i<gridPathNew.length();i++){
			gridPathCode[i]=Integer.parseInt(String.valueOf(gridPathNew.charAt(i)));
		}
		//�߼��жϣ��������������ǰ�����ֵ���1������һ������ͬ���ж�Ϊ�����Թ�·������������������Ϊͨ·��1��
		for(int j = 0; j <= gridPathCode.length-1; j = j+4){
			if(gridPathCode[j]==gridPathCode[j+2] && Math.abs(gridPathCode[j+1]-gridPathCode[j+3])==1){
				break;
			}else if(gridPathCode[j+1]==gridPathCode[j+3] && Math.abs(gridPathCode[j]-gridPathCode[j+2])==1){
				break;
			}else{
				//���������������������ж�Ϊ��ͨ�Դ�������·���񲻷�����ͨ�淶
				System.out.println("Maze format error.");
				System.exit(0);
			}
		}
		return;
	}
}