package anadis.maze_project.algos;

/**
 * Maze solving algorithm based on the Tremaux labyrinth solving method.
 * <p>
 * Solves a perfect labyrinth with no loops by keeping track of the path gone by
 * marking the path and always choosing unmarked path or go back. Time O(n*m).
 * </p>
 * int[][][] 'markedPath' table to keep track of the marked paths. The first 2
 * dimensions are x, y coordinates on the grid. The 3rd keeps track of path
 * being marked and the direction of arrival to the cell. The table 'directions'
 * is for changing x, y coordinates while moving in the maze and table 'froms'
 * is for converting the direction for storing. char[] 'path' for printing the
 * solution indicating the direction of the path. boolean[] 'dir' indicates
 * which directions are currently available.
 */
public class TremauxSolve {

    private int[][][] markedPath;
    private int cols, rows, amount;
    private int startx, starty, finnishx, finnishy;
    private char[][] grid;
    private boolean[] dir;             // 1. Up  2. Right 3. Down 4. Left
    private int[][] direction = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[] froms = {0, 3, 4, 1, 2};
    private char[] path = {' ', 'v', '<', '^', '>', 's', 'f'};
    private boolean solved = false;

    /**
     * Maze solving algorithm based on the Tremaux labyrinth solving method.
     * <p>
     * Solves a perfect labyrinth with no loops by keeping track of the path
     * gone by marking the path and always choosing unmarked path or go back.
     * Time O(n*m).
     * </p>
     * int[][][] 'markedPath' table to keep track of the marked paths. The first
     * 2 dimensions are x, y coordinates on the grid. The 3rd keeps track of
     * path being marked and the direction of arrival to the cell. The table
     * 'directions' is for changing x, y coordinates while moving in the maze
     * and table 'froms' is for converting the direction for storing. char[]
     * 'path' for printing the solution indicating the direction of the path.
     * boolean[] 'dir' indicates which directions are currently available.
     *
     * @param g is the grid of the maze to be solved
     * @param stx is the start coordinate, row number
     * @param sty is the start coordinate, column number
     * @param finx is the finish coordinate, row number
     * @param finy is the finish coordinate, column number
     */
    public TremauxSolve(char[][] g, int stx, int sty, int finx, int finy) {
        this.startx = stx;
        this.starty = sty;
        this.finnishx = finx;
        this.finnishy = finy;
        this.grid = g;
        this.rows = this.grid.length;
        this.cols = (this.grid[0].length + 1) / 2;
        this.markedPath = new int[this.rows][this.cols][2];
        this.dir = new boolean[5];
        solve();
    }

    /**
     * Initiates the solving of the maze. If the maze is unsolvable method
     * prints "No solution".
     */
    public void solve() {
        int x = this.startx;
        int y = this.starty;
        this.markedPath[x][y][0] = 1;
        this.markedPath[x][y][1] = 5;
        boolean noSol = false;

        while (!noSol) {
            if (x == this.finnishx && y == this.finnishy) {
                break;
            }
            int from = this.markedPath[x][y][1];
            this.getDirections(from, x, y);
            if (this.amount == 0) {
                while (this.amount == 0) {
                    if (x == this.startx && y == this.starty) {
                        System.out.println("No solution!");
                        noSol = true;
                        break;
                    }
                    this.markedPath[x][y][0] += 1;
                    x += this.direction[from][0];
                    y += this.direction[from][1];
                    from = this.markedPath[x][y][1];
                    this.getDirections(from, x, y);
                }
            }

            for (int i = 1; i < 5; i++) {
                if (this.dir[i]) {
                    if (this.markedPath[x + this.direction[i][0]][y
                            + this.direction[i][1]][0] == 0) {
                        x += this.direction[i][0];
                        y += this.direction[i][1];
                        this.markedPath[x][y][0] += 1;
                        this.markedPath[x][y][1] = this.froms[i];
                        break;
                    }
                }
            }
        }
        solved = true;
    }

    /**
     * Initials the 'dir' table with the available directions for given 
     * coordinates and amount variable indicating the amount of possible dirs.
     * 
     * @param from indicates direction for arriving at the given coordinate
     * @param r row coordinate
     * @param c column coordinate
     */
    public void getDirections(int from, int r, int c) {
        for (int i = 0; i < 5; i++) {
            this.dir[i] = false;
        }
        this.amount = 0;
        if (from != 1 && this.grid[r - 1][c * 2 - 1] == ' '
                && this.markedPath[r - 1][c][1] == 0) {
            this.dir[1] = true;
            this.amount++;
        }
        if (from != 2 && this.grid[r][(c) * 2] == ' '
                && this.markedPath[r][c + 1][1] == 0) {
            this.dir[2] = true;
            this.amount++;
        }
        if (from != 3 && this.grid[r][c * 2 - 1] == ' '
                && this.markedPath[r + 1][c][1] == 0) {
            this.dir[3] = true;
            this.amount++;
        }
        if (from != 4 && this.grid[r][c * 2 - 2] == ' '
                && this.markedPath[r][c - 1][1] == 0) {
            this.dir[4] = true;
            this.amount++;
        }
    }

    /**
     * Outputs the solution to the screen
     */
    public void printSolution() {
        this.markedPath[this.finnishx][this.finnishy][1] = 6;
        for (int j = 1; j < this.rows; j++) {
            for (int i = 1; i < this.cols; i++) {
                if (this.markedPath[j][i][0] == 0) {
                    System.out.print(this.path[0] + " ");
                } else if (markedPath[j][i][0] == 1) {
                    System.out.print(this.path[this.markedPath[j][i][1]] + " ");
                } else {
                    System.out.print(this.path[0] + " ");
                }
            }
            System.out.println("");
        }
    }
    
    //    For testing
    /**
     * 
     * @return amount of column in the maze
     */
    public int getCols() {
        return this.cols;
    }

    /**
     * 
     * @return amount of rows
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * 
     * @return amount of directions currently available
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * 
     * @return info of solved or not
     */
    public boolean getSolved() {
        return this.solved;
    }
}
