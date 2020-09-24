/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.maze_project;

/**
 *
 * @author anadis
 */
public class Maze {
    private char[][] grid;
    
    public Maze(int cols, int rows) {
        this.grid = new char[rows+1][cols*2+1];
//        System.out.println("y = " + grid[0].length + ", x = " + grid.length);
        for (int y = 0; y < grid[0].length; y++) {
            for (int x = 0; x < grid.length; x++) {
                if (x == 0) {
                    grid[x][y] = '_';
                    
                } else if (y==0 && x!=0){
                    grid[x][y] = '|';
                        
                } else if (y%2!=0) { grid[x][y] = '_'; }
                else if (y%2==0) { grid[x][y] = '|';
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
    
    public void carve(int colnr, int rownr) {
        this.grid[rownr][colnr] = ' ';
    }
}
