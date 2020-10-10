package anadis.maze_project.algos;

import anadis.maze_project.domain.Maze;
import anadis.maze_project.utils.IndexTree;

/**
 * Perfect maze generating algorithm
 * <p>
 * Generates maze in O(?) time and o(n*m) space one row at a time by keeping
 * track of sets of cells to avoid loops and isolated ares.
 * </p>
 * Data table arr[][] 'directions' contain coordinates for moving around in the
 * maze [1][] up [2][] right [3][] down [4][] left, [][0] x-coordinate, [][1] y-
 * coordinate. arr[] 'dirs' contains info on which directions 1:4 are presently
 * available arr[] 'froms' contain the opposite directions for storing cell
 * arrival info arr[][][] 'path' contain data of the grid: [x][y][0] path has
 * been in cell [x][y][1] stores direction the path came from, [x][y][2] stores
 * direction path continues.
 */
public class WilsonsAlgo {

    private Maze maze;
    private int rows, cols, x, y, unvisited;
    private boolean[][] visited;
    private int[][] direction = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][][] paths;
    private boolean[] dirs = {false, false, false, false, false};
    private int[] froms = {0, 3, 4, 1, 2};
    private IndexTree indexTree;

    /**
     * Perfect maze generating algorithm
     * <p>
     * Generates maze in O(?) time and o(n*m) space one row at a time by keeping
     * track of sets of cells to avoid loops and isolated ares.
     * </p>
     * Data table arr[][] 'directions' contain coordinates for moving around in
     * the maze [1][] up [2][] right [3][] down [4][] left, [][0] x-coordinate,
     * [][1] y- coordinate. arr[] 'dirs' contains info on which directions 1:4
     * are presently available arr[] 'froms' contain the opposite directions for
     * storing cell arrival info arr[][][] 'path' contain data of the grid:
     * [x][y][0] path has been in cell [x][y][1] stores direction the path came
     * from, [x][y][2] stores direction path continues.
     * 
     * @param r is the amount of rows. 
     * @param c the amount of columns.
     */
    public WilsonsAlgo(int r, int c) {
        this.cols = c;
        this.rows = r;
        this.unvisited = c * r;
        this.maze = new Maze(rows, cols);
        this.visited = new boolean[rows + 1][cols + 1];
        this.paths = new int[rows + 1][cols + 1][3];
        this.indexTree = new IndexTree(rows, cols);
        this.start();
    }

    /**
     * Loop-erased random walk. 
     * If the walk crosses its path, it erases the loop using arr[][][] path
     * to to keep track of the directions and cells on the path.
     * The earliest random walk take the longest time to come across the few 
     * visited cells. 
     */
    public void randomWalk() {
        this.pickACell();
        this.paths[x][y][0] = 1;
        this.paths[x][y][1] = 0;
        this.paths[x][y][2] = 0;
        int nx = x;
        int ny = y;
        
        //random walk until a visited cell is encountered
        while (!visited[nx][ny]) {

            int dir = this.chooseDir(nx, ny);
            this.paths[nx][ny][0] = 1;
            this.paths[nx][ny][2] = dir;
            nx = nx + this.direction[dir][0];
            ny = ny + this.direction[dir][1];
            this.paths[nx][ny][1] = froms[dir];

            // Erase loop if path crosses its own path
            if (this.paths[nx][ny][0] == 1) {
                int fromx = nx + this.direction[this.paths[nx][ny][2]][0];
                int fromy = ny + this.direction[this.paths[nx][ny][2]][1];
                paths[nx][ny][0] = 2;

                while (paths[fromx][fromy][0] == 1) {
                    paths[fromx][fromy][0] = 0;
                    fromx = fromx + this.direction[paths[fromx][fromy][2]][0];
                    fromy = fromy + this.direction[paths[fromx][fromy][2]][1];

                }
                this.paths[nx][ny][0] = 1;
            }
        }
        this.carvePath();
    }

    /**
     * 
     */
    public void carvePath() {
        while (!visited[this.x][this.y]) {
            this.visited[this.x][this.y] = true;
            this.indexTree.markVisited(this.x, this.y);
            this.unvisited--;
            int dir = paths[this.x][this.y][2];
            int mazex = this.x;
            int mazey = this.y * 2 - 1;
            if (dir == 1) {
                this.maze.carve(mazey, mazex - 1);
            }
            if (dir == 2) {
                this.maze.carve(mazey + 1, mazex);
            }
            if (dir == 3) {
                this.maze.carve(mazey, mazex);
            }
            if (dir == 4) {
                this.maze.carve(mazey - 1, mazex);
            }
            this.x = this.x + this.direction[dir][0];
            this.y = this.y + this.direction[dir][1];
        }

        if (this.unvisited > 0) {
            this.randomWalk();
        }
    }

    /**
     * Calls for available directions and chooses one of the available 
     * directions at random.
     * Coordinates in the maze:
     * 
     * @param nx row number 
     * @param ny column number
     * @return the direction chosen. 1:up, 2:right, 3:down, 4:left.
     */
    public int chooseDir(int nx, int ny) {

        this.ifBorder(nx, ny);
        int from = this.paths[nx][ny][1];
        int dir = 0;
        while (dir == 0) {
            int r = (random() % 4) + 1;
            if (this.dirs[r] && r != from) {
                dir = r;
            }
        }
        return dir;
    }

    /**
     * Initials arr[] 'dirs' by checking for borders around coordinate (x, y).
     * 
     * @param x row number
     * @param y column number
     */
    public void ifBorder(int x, int y) {

        for (int i = 1; i < 5; i++) {
            this.dirs[i] = true;
        }
        if (x == 1) {
            this.dirs[1] = false;
        }
        if (x == rows) {
            this.dirs[3] = false;
        }
        if (y == 1) {
            this.dirs[4] = false;
        }
        if (y == cols) {
            this.dirs[2] = false;
        }

    }

    /**
     * Picks a random cell on the grid by calling the random number generator.
     * Uses the IndexTree to find closest available cell according to the chosen 
     * coordinates in O(log n) time.
     */
    public void pickACell() {
        if (this.unvisited > 0) {
            int rx = (random() % this.rows) + 1;
            int ry = (random() % this.cols) + 1;
            int[] cell = this.indexTree.getFreeIndex(rx, ry);
            this.x = cell[0];
            this.y = cell[1];
        }
    }

    /**
     * Initials the generator.
     * If the maze is of size 1 in length or width it carves a tube, to avoid 
     * the random walk to be stuck in a forever loop.
     * Picks a starting cell by calling pickACell() method, then calls for 
     * randomWalk().
     */
    public void start() {
        if (this.rows == 1 || this.cols == 1) {
            this.carveTube();
            return;
        }
        this.pickACell();
        this.visited[this.x][this.y] = true;
        this.indexTree.markVisited(x, y);
        this.unvisited--;
        this.randomWalk();
    }

    /**
     * If the maze is of size 1 in length or width it carves a tube, to avoid 
     * the random walk to be stuck in a forever loop.
     */
    public void carveTube() {
        for (int i = 1; i <= this.rows; i++) {
            for (int j = 1; j <= this.cols * 2; j++) {
                this.maze.carve(j, i);
            }
        }
    }

    /**
     * 
     * @return the grid
     */
    public char[][] getGrid() {
        return this.maze.getGrid();
    }

    /**
     * 
     * @return the Maze object
     */
    public Maze getMaze() {
        return this.maze;
    }

    /**
     * Random number initiator.
     * @return the generated number
     */
    public int random() {
        return (int) (System.nanoTime() % 10000);
    }

    /**
     * 
     * @return the amount of rows of the maze
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * 
     * @return the amount of columns of the maze.
     */
    public int gerCols() {
        return this.cols;
    }
}
