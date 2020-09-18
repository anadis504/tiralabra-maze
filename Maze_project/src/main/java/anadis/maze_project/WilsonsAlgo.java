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
        this.paths = new int[cols][rows][4];
    
    }
    
    
    public void randomWalk() {
        pickACell();
        ArrayList<Integer> path = new ArrayList<>();
        paths[x][y][0] = 1;         //paths[][][0]=1 tells that path has been in 
        paths[x][y][1] = 0;         //this cell. [][][1] tells path arrived by
        paths[x][y][2] = 0;         //0=start, going: (1)L (2)R (3)Up (4)Down
        paths[x][y][3] = 0;         //[][][2] where is goes from that cell
        path.add(0, x*100+y);       //[][][3]=index in path.
        int loops = 0;
        int[][] loopi = new int[100][2];
        int nx = x;
        int ny = y;
        
        while (!visited[nx][ny]) {
            
            int dir = chooseDir(nx, ny);
            paths[nx][ny][2] = dir;         //where path goes
            nx = nx + direction[dir][0];
            ny = ny + direction[dir][1];
            paths[nx][ny][1] = dir;         //how it came here
            if (paths[nx][ny][0] == 1) {
                loops++;                    //remove the loop
                int fromx = nx+direction[paths[nx][ny][2]][0];
                int fromy = ny+direction[paths[nx][ny][2]][1];
                
                while (fromx != nx && fromy != ny) {
                    paths[fromx][fromy][0] = 0;     //remove cell from path
                    fromx = fromx+direction[paths[fromx][fromy][2]][0];
                    fromy = fromy+direction[paths[fromx][fromy][2]][1];
                }
                
            }
        }
        carvePath();
        System.out.println("Got here");
    }
    
    public void carvePath() {
        int fromx = this.x;
        int fromy = this.y;
        while (!visited[fromx][fromy]) {
            visited[fromx][fromy] = true;
            int dir = paths[fromx][fromy][2];
            int mazex = fromx*2+1;
            int mazey = fromy+1;
            if (dir == 0) {
                maze.carve(mazex-1, mazey);
            }
            if (dir == 1) {
                maze.carve(mazex+1, mazey);
            }
            if (dir == 2) {
                maze.carve(mazex, mazey-1);
            }
            if (dir == 3) {
                maze.carve(mazex, mazey);
            }
            maze.printMaze();
            fromx = fromx+direction[dir][0];
            fromy = fromy+direction[dir][1];
        }
    }
    
    public int chooseDir(int nx, int ny) {
        
        int poss = ifBorder(nx,ny);
        int noGo = paths[nx][ny][0];
        if (noGo%2==0) noGo =-2;
            
        if (poss == 0) {
            int r = random()%3;
            if (paths[nx][ny][0] == 0) r = random()%4;
            if (r == noGo) r++;
            return r;
        }
        if (poss < 100) {
            int a = poss/10-1;
            int b = poss%10-1;
            if (noGo == a) return b;
            if (noGo == b) return a;
            int r = random()%2;
            if (r == 0) return a;
            return b;
        }    
        int r = random()%2;
        int a = poss/100-1;
        int b = (poss/10)%10-1;
        int c = poss%10-1;
        if (a == noGo) {
            if (r==0) return b;
            return c;
        }
        if (b == noGo) {
            if (r==0) return a;
            return c;
        }
        if (c == noGo) {
            if (r==0) return a;
            return b;
        }
        r = random()%3;
        if (r==0) return a;
        if (r==1) return b;
        return c;
    }
    
    public int ifBorder(int x, int y) {                 //1 L  2 R  3 U  4 D
        if (x == 0 && y == 0) return 24;         
        if (x == cols-1 && y == 0) return 14;
        if (x == cols-1 && y == rows-1) return 13;
        if (x == 0 && y == rows-1) return 23;
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
        randomWalk();
    }
    
    //this limitates the maze to rows <= 100, cols <= 100. Can be modified for 
    //bigger maze later
    public int random() {
        return (int)(System.nanoTime()%100);
    }
}
