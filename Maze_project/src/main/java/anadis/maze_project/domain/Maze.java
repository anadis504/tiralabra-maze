/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.maze_project.domain;

/**
 *
 * @author anadis
 */
public class Maze {

    private char[][] grid;
    private int cols, rows;

    public Maze(int c, int r) {
        this.cols = c * 2 + 1;
        this.rows = r + 1;
        this.grid = new char[rows][cols];
//        System.out.println("y = " + grid[0].length + ", x = " + grid.length);
        for (int y = 0; y < grid[0].length; y++) {
            for (int x = 0; x < grid.length; x++) {
                if (x == 0) {
                    grid[x][y] = '_';

                } else if (y == 0 && x != 0) {
                    grid[x][y] = '|';

                } else if (y % 2 != 0) {
                    grid[x][y] = '_';
                } else if (y % 2 == 0) {
                    grid[x][y] = '|';
                }
            }
        }
    }

    public void printMaze() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println("");
        }
    }

    public char[][] getGrid() {
        return this.grid;
    }

    public void carve(int colnr, int rownr) {
        if (colnr <= 0 || rownr <= 0 || colnr >= cols - 1 || rownr >= rows) {
            return;
        }
        this.grid[rownr][colnr] = ' ';
    }
}
