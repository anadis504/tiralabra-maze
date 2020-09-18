/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.maze_project;

import java.util.ArrayList;

/**
 *
 * @author anadis
 */
public class WilsonsAlgo {
    
    private Maze maze;
    private int rows;
    private int cols;
    private boolean[][] visited;
    private int x;
    private int y;             // L 0    R 1   Up 2  Down 3
    private int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
    private int[][][] paths;
    
    public WilsonsAlgo(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.maze = new Maze(cols, rows);
        this.visited = new boolean[cols][rows];
        this.paths = new int[cols][rows][3];
    
    }
    
    
    public void randomWalk() {
        pickACell();
        ArrayList<Integer> path = new ArrayList<>();
        paths[x][y][0] = 1;         //paths[][][0]=1 tells that path has been in 
        paths[x][y][1] = 0;       //this cell. [][][1] tells path arrived by 
        path.add(0, x*100+y);       //0=start, going: (1)L (2)R (3)Up (4)Down
        int loops = 0;
        int[][] loopi = new int[100][2];
        
        for (int i = 0; i < path.size(); i++) {
            int nx = path.get(i)/100;
            int ny = path.get(i)%100;
            int dir = 99;
            int poss = ifBorder(nx,ny);
            int noGo = paths[nx][ny][0];
            if (noGo%2==0) noGo =-2;
            
            if (poss == 0) {
                int r = random()%3;
                if (r == noGo) r++;
                dir = r;
                System.out.println("OK");
            }
            else if (poss < 100) {
                int a = poss/10;
                int b = poss%10;
                if (noGo == a-1) dir = b-1;
                else dir = a-1;
                System.out.println("OK2");
            }    
            System.out.println(dir);
                
            
            
        }
    }
    
    public int ifBorder(int x, int y) {                 //1 L  2 R  3 U  4 D
        if (x == 0 && y == 0) return 24;         
        if (x == cols-1 && y == 0) return 14;
        if (x == cols-1 && y == rows) return 13;
        if (x == 0 && y == rows) return 23;
        if (x == 0) return 234;
        if (y == 0) return 124;
        if (x == cols-1) return 134;
        if (y == rows-1) return 123;
        
        return 0;
    }
    
    public void pickACell() {
        boolean found = false;
        while (!found) {
            int rx = random()%cols;
            int ry = random()%rows;
            if (!visited[rx][ry]) {
                this.x = rx;
                this.y = ry;
                found = true;
            }
        }
    }
    
    public void start() {
        pickACell();
        visited[this.x][this.y] = true;
    }
    
    //this limitates the maze to rows <= 100, cols <= 100. Can be modified for 
    //bigger maze later
    public int random() {
        return (int)(System.nanoTime()%100);
    }
}
