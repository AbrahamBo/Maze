# 迷宫创建
## 项目简述
本项目用于构建一个迷宫，用道路网格(Road Grid) 来表示迷宫的道路，那么 3 x 3的道路网格可以对应一个 7 x 7 的渲染网格(Render Grid)(该渲染网格尺寸即为迷宫尺寸)。用户给定输入字符串：道路网格尺寸和迷宫路径，来构建迷宫。同时，要求检测输入的网格尺寸和迷宫路径字符串是否符合输入数据规范，及字符串是否可以转化为数字，数字是否符合规范，字符串是否符合预定义格式，以及输入的路径是否可以连通等4个先验条件。判断满足条件后，将迷宫通路点打印为"**[R]**",墙点打印为"**[W]**"。
## 项目开发环境
本项目基于java语言编写，开发环境JDK1.7，采用MyEclips作为开发工具。
## 项目使用手册
本项目包含`README.md`说明文档和java工程项目`src`文件夹，`src`文件夹中包含一级包`maze`，二级包`entity`、`service`和`test`。`entity`为持久层,其实例仅可被`service`业务逻辑层调用，而`service`层实例仅可被`test`层调用。
* `entity` 包中存放迷宫实体类`Maze`,包括迷宫的长宽等基本属性。
* `service` 包中存放`MazeImpl`类，其中的方法均为构建迷宫以及判断输入格式的主要业务逻辑。
* `test` 包中存放`MazeImplTest`测试类，应用**Junit**标签`@Test`进行单元测试。测试迷宫业务逻辑是否实现完整功能。
----
在`Maze`实体类中：
* 参数roadGridR 和 roadGridC：为构建迷宫的道路网格行数和列数，及其get和set方法。
* 参数int[][] maze: 为二维初始化迷宫数组
* 构造方法Maze(int r, int c)： 通过道路网格行列，创建初始化迷宫对象，道路网格点置为**1**，墙点置为**0**。
----
在MazeImpl类中：
* 全局参数maze： 构建maze对象。
* creMazeRoad(String gridSize, String gridPath)方法： 输入正确格式的字符串，构建迷宫，并存于maze对象中。其中，gridSize为道路网格尺寸，gridPath为连通路径。
* printMaze()方法：打印迷宫对象，迷宫二维数组中存为0的点打印为 **[W]** ,存为1的点打印为 **[R]** 。
* isNumFormat(String gridSize, String gridPath)方法：输入道路网格和连通路径字符串，判断是否可转化为数字。
* isCommFormat(String gridSize, String gridPath)方法：判断输入字符串是否符合输入格式。
* isOutRange(String gridSize, String gridPath)方法： 判断输入字符串是否有数字超过允许范围。
* isMazeFormat(String gridPath)方法： 判断输入字符串路径信息是否可以连通，如0,1 0,2可以连通,o,1 0,3不能连通。
* isCorrectInput(String gridSize, String gridPath)方法： 先判断输入字符串是否为空，若为空则返回，若不为空，则依次执行上述4个判断输入数据是否符合规范的方法。在程序中仅需执行该函数，即可判断输入是否符合规范。
----
在MazeImplTest类中，应用Junit标签@Test来进行测试，测试时鼠标右键点击MazeImplTest的不同方法，选择Run as->Junit Test，进行单元测试。
单元测试中通过给定测试字符串，测试程序完整性。也可通过获取键盘输入进行测试数据的输入，该代码在程序中被注释。
## 单元测试
* testCreMazeRoad() :对执行构建迷宫的方法进行测试。输入`String gridSize = "3 3"; String gridPath = "0,1 0,2;0,0 1,0;0,1 1,1;0,2 1,2;1,0 1,1;1,1 1,2;1,1 2,1;1,2 2,2;2,0 2,1";`可得到正确字符串矩阵。
* testIsNumFormat()：判断是否可转化为数字。输入`String gridSize = "- 3";
		String gridPath = "0,a x,2;0,0 1,0;0,1 1,1;0,2 1,2;1,0 1,1;1,1 1,2";`,报错”Invalid number format .“。
* testIsCommFormat()：输入`String gridSize = "3 3";
		String gridPath = "0 1;0 2   0,0 1,0   0 1;1 1";`,报错“Incorrect command format ”。
* testIsOutRange()： 输入`String gridSize = "3 3";
		String gridPath = "6,1 5,1;0,0 1,0";` ，报错"Number out of range ."。
* testIsMazeFormat()：输入`String gridSize = "3 3";
		String gridPath = "0,1 2,2;0,3 1,0";`报错"Maze format error."。
* testIsCorrectInput()： 输入`String gridSize = "4 [[";
		String gridPath = "0,100 2,2;0,3 1,0;;";` ，仅报错”Invalid number format .“。
## 作者
刘彦伯 
电话：15910572286
邮箱: 1264803611@qq.com
