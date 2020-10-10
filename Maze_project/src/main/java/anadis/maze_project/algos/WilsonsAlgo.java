package anadis.maze_project.algos;

import anadis.maze_project.domain.Maze;
import anadis.maze_project.utils.IndexTree;

/**
 * Perfect maze generating algorithm
 * <p>
 * Generates maze in O(?) time and o(n*m) space one row at a time by keeping 
 * track of sets of cells to avoid loops and isolated ares.
 * </p>
 */
public class WilsonsAlgo {

    private Maze maze;
    private int rows, cols, x, y, unvisited, amount;
    private boolean[][] visited;
    private int[][] direction = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][][] paths;
    private boolean[] dirs = {false, false, false, false, false};
    private int[] froms = {0, 3, 4, 1, 2};
    private IndexTree indexTree;

    public WilsonsAlgo(int r, int c) {
        this.cols = c;
        this.rows = r;
        this.unvisited = c * r;
        this.maze = new Maze(rows, cols);
        this.visited = new boolean[rows + 1][cols + 1];
        this.paths = new int[rows + 1][cols + 1][3];
        this.indexTree = new IndexTree(rows, cols);
        start();
    }

    public void randomWalk() {
        pickACell();

        paths[x][y][0] = 1;         //paths[][][0]=1 tells that path has been in 
        paths[x][y][1] = 0;         //this cell. [][][1] tells path arrived by
        paths[x][y][2] = 0;         //[][][2] where it continues from that cell
        int nx = x;
        int ny = y;

        while (!visited[nx][ny]) {

            int dir = chooseDir(nx, ny);
            paths[nx][ny][0] = 1;
            paths[nx][ny][2] = dir;         //where path goes
            nx = nx + direction[dir][0];
            ny = ny + direction[dir][1];
            paths[nx][ny][1] = froms[dir];         //how it came here

            if (paths[nx][ny][0] == 1) {
                //remove the loop
                int fromx = nx + direction[paths[nx][ny][2]][0];
                int fromy = ny + direction[paths[nx][ny][2]][1];
                paths[nx][ny][0] = 2;

                while (paths[fromx][fromy][0] == 1) {
                    paths[fromx][fromy][0] = 0;     //remove cell from path
                    fromx = fromx + direction[paths[fromx][fromy][2]][0];
                    fromy = fromy + direction[paths[fromx][fromy][2]][1];

                }
                paths[nx][ny][0] = 1;
            }
        }
        carvePath();
    }

    public void carvePath() {
        int fromx = this.x;
        int fromy = this.y;
        while (!visited[fromx][fromy]) {
            visited[fromx][fromy] = true;
            indexTree.markVisited(fromx, fromy);
            unvisited--;
            int dir = paths[fromx][fromy][2];
            int mazex = fromx;
            int mazey = fromy * 2 - 1;
            if (dir == 1) {
                maze.carve(mazey, mazex - 1);
            }
            if (dir == 2) {
                maze.carve(mazey + 1, mazex);
            }
            if (dir == 3) {
                maze.carve(mazey, mazex);
            }
            if (dir == 4) {
                maze.carve(mazey - 1, mazex);
            }
            fromx = fromx + direction[dir][0];
            fromy = fromy + direction[dir][1];
        }
//        System.out.println("Unvisited: " + unvisited);
//        maze.printMaze();
        if (unvisited > 0) {
            randomWalk();
        }
    }

    public int chooseDir(int nx, int ny) {

        ifBorder(nx, ny);
        int from = paths[nx][ny][1];
        int dir = 0;
        while (dir == 0) {
            int r = (random() % 4) + 1;
            if (dirs[r] && r != from) {
                dir = r;
            }
        }
//        System.out.println("Choosing dir: " + dir);
        return dir;
    }

    public void ifBorder(int x, int y) {                 //1 L  2 R  3 U  4 D

        amount = 4;
        for (int i = 1; i < 5; i++) {
            dirs[i] = true;
        }
        if (x == 1) {
            dirs[1] = false;
            amount--;
        }
        if (x == rows) {
            dirs[3] = false;
            amount--;
        }
        if (y == 1) {
            dirs[4] = false;
            amount--;
        }
        if (y == cols) {
            dirs[2] = false;
            amount--;
        }

    }

    public void pickACell() {        
        if (unvisited > 0) {            
            int rx = (random() % rows) + 1;
            int ry = (random() % cols) + 1;
//            System.out.println("picking cell " + rx + " " + ry);
            int[] cell = indexTree.getFreeIndex(rx, ry);
            this.x = cell[0];
            this.y = cell[1];
//            if (!visited[rx][ry]) {
//                this.x = rx;
//                this.y = ry;
//                break;
//            }
        }
    }

    public void start() {
        if (rows == 1 || cols == 1) {
            carveTube();
            maze.printMaze();
            return;
        }
        pickACell();
        visited[this.x][this.y] = true;
        indexTree.markVisited(x, y);
        unvisited--;
//        System.out.println("Start: unvisited " + unvisited );
//        System.out.println("Strartcell: " + x + " " + y);
        randomWalk();
    }

    public void carveTube() {
        for (int i = 1; i <= rows; i++) {
            for ( int j = 1; j <= cols*2; j++) {
                maze.carve(j, i);
            }
        }
    }
    
    public char[][] getGrid() {
        return maze.getGrid();
    }

    public Maze getMaze() {
        return this.maze;
    }

    public int random() {
        return (int) (System.nanoTime() % 100);
    }

    public int getRows() {
        return this.rows;
    }

    public int gerCols() {
        return this.cols;
    }
}
