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
    private int[][] direction = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[] froms = {0, 3, 4, 1, 2};
    private char[] path = {' ', 'v', '<', '^', '>', 's', 'y'};

    public TremauxSolve(Maze lab) {
        this.maze = lab;
        this.grid = maze.getGrid();
        this.rows = grid.length;
        this.cols = (grid[0].length + 1) / 2;
        System.out.println("int x: " + rows + ", int y: " + cols);
        this.markedPath = new int[rows][cols][2];
        this.dir = new boolean[5];
        this.finnishx = rows - 1;
        this.finnishy = cols - 1;
        System.out.println("fxy: " + finnishx + " " + finnishy);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println("");
        }
    }

    public void go(int r, int c) {
        this.startx = r;
        this.starty = c;
        int x = startx;
        int y = starty;
//        getDirections(0, x, y);
        markedPath[x][y][0] = 1;
        markedPath[x][y][1] = 5;
        boolean noSol = false;

        while (!noSol) {
            if (x == finnishx && y == finnishy) {
                break;
            }
            int from = markedPath[x][y][1];
            getDirections(from, x, y);

            if (amount == 0) {
                while (amount == 0) {
                    if (x == startx && y == starty) {
                        System.out.println("No solution!");
                        noSol = true;
                        break;
                    }
                    markedPath[x][y][0] += 1;
                    x += direction[from][0];
                    y += direction[from][1];
                    from = markedPath[x][y][1];
                    getDirections(from, x, y);
                    //System.out.println(" Now in x: " + x + " y: " + y + " going back " + from);

                }
            }

            for (int i = 1; i < 5; i++) {
                if (dir[i]) {
                    if (markedPath[x + direction[i][0]][y + direction[i][1]][0] == 0) {
//                        System.out.println(x + "," + y + " ");
                        x += direction[i][0];
                        y += direction[i][1];
                        markedPath[x][y][0] += 1;
                        markedPath[x][y][1] = froms[i];
//                        System.out.println(x + "," + y + " Now in x: " + x + " y: " + y + " came by " + i);
                        continue;
                    }
                }
            }
        }

        System.out.println("Solved it!");
        printSolution();
    }

    public void getDirections(int from, int r, int c) {
        for (int i = 0; i < 5; i++) {
            dir[i] = false;
        }
        this.amount = 0;
        if (from != 1 && grid[r - 1][c * 2 - 1] == ' ' && markedPath[r - 1][c][1] == 0) {
//            System.out.println(grid[r - 1][c * 2 - 1]);
            dir[1] = true;
            amount++;
        }
        if (from != 2 && grid[r][c * 2] == ' ' && markedPath[r][c + 1][1] == 0) {
            dir[2] = true;
//            System.out.println(grid[r][c * 2]);
            amount++;
        }
        if (from != 3 && grid[r][c * 2 - 1] == ' ' && markedPath[r + 1][c][1] == 0) {
            dir[3] = true;
//            System.out.println(grid[r][c * 2 - 1]);
            amount++;
        }
        if (from != 4 && grid[r][c * 2 - 2] == ' ' && markedPath[r][c - 1][1] == 0) {
            dir[4] = true;
//            System.out.println(grid[r][c * 2 - 2]);
            amount++;
        }
    }

    public void printSolution() {
        for (int j = 1; j < rows; j++) {
            for (int i = 1; i < cols; i++) {
                if (markedPath[j][i][0] == 0) {
                    System.out.print(path[0] + " ");
                } else if (markedPath[j][i][0] == 1) {
                    System.out.print(path[markedPath[j][i][1]] + " ");
                } else {
                    System.out.print(path[0] + " ");
                }
            }
            System.out.println("");
        }
        for (int j = 1; j < rows; j++) {
            for (int i = 1; i < cols; i++) {
                
                    System.out.print(markedPath[j][i][0] + " ");
                
                
            }
            System.out.println("");
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
