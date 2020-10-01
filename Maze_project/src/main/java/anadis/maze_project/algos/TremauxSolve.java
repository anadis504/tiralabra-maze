package anadis.maze_project.algos;

import anadis.maze_project.domain.Maze;

/**
 *
 * @author anadis
 */
public class TremauxSolve {

    private Maze maze;
    private int[][][] markedPath;
    private int cols, rows, amount;
    private int startx, starty, finnishx, finnishy;
    private char[][] grid;
    private boolean[] dir;
    private int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public TremauxSolve(Maze lab) {
        this.maze = lab;
        this.grid = maze.getGrid();
        this.rows = grid.length;
        this.cols = (grid[0].length + 1) / 2;
        System.out.println("int x: " + rows + ", int y: " + cols);
        this.markedPath = new int[rows][cols][3];
        this.dir = new boolean[5];
    }

    public void go(int r, int c) {
        this.startx = r;
        this.starty = c;
        int x = startx;
        int y = starty;
        getDirections(0, x, y);
        markedPath[x][y][0] = 1;


    }

    public void getDirections(int from, int r, int c) {
        for (int i = 0; i < 5; i++) {
            dir[i] = false;
        }
        this.amount = 0;
        if (from != 1 && grid[r - 1][c * 2 - 1] == ' ') {
            dir[1] = true;
            amount++;
        }
        if (from != 2 && grid[r][c * 2] == ' ') {
            dir[2] = true;
            amount++;
        }
        if (from != 3 && grid[r][c * 2 - 1] == ' ') {
            dir[3] = true;
            amount++;
        }
        if (from != 4 && grid[r][c * 2 - 2] == ' ') {
            dir[4] = true;
            amount++;
        }
    }

//    For testing
    public int getCols() {
        return this.cols;
    }

    public int getRows() {
        return this.rows;
    }
    
    public int getAmount() {
        return this.amount;
    }
}
