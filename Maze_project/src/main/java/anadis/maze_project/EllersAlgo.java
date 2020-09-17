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
public class EllersAlgo {
    
    private int[] currentRow;
    private boolean[] walls;
    private int rows;
    private int cols;
    private Maze maze;

    private int[][] setsTable;
    private boolean[] availables;
    private int free;
    
    public EllersAlgo(int cols, int rows) {
        this.cols = cols+1;
        this.rows = rows;
        this.walls = new boolean[cols*2-1];
        this.setsTable = new int[cols+1][4];
        this.currentRow = new int[cols];
        this.availables = new boolean[cols+1];
        this.free = 0;
        
        
        for (int i = 0; i < cols; i++) {
            System.out.print(i + " i on nyt: ");
            this.setsTable[i+1][0] = i+1;       //idea of setsTable is to keep track
            this.setsTable[i+1][1] = 1;         //of what set another set has joined
            this.setsTable[i+1][2] = 1;         //into. [i+1][0] tells set i+1 has joined
            this.currentRow[i] = i+1;           //set that is found [i+1][0]. [*][1] =amount of members
            this.walls[i] = false;
            this.walls[i*2] = false;
            System.out.println(i);
        }
        
        this.maze = new Maze(cols, rows);
    }
    
    /*the walls[index]= true indicates that there is a wall. Index which is 
    dividable by two represent a bottom-wall, the others represent a right-wall    
    */
    public void generateRow(int rownr) {
        if (rownr > rows) return;
        for (int i = 0; i < currentRow.length; i++) {
            System.out.print(currentRow[i] + " ");
            if (currentRow[i] == 0) {
                int set = this.getAvailableSet();            //designating new set for cells without a set;
                currentRow[i] = set;

            }
        }
        System.out.println("");
        for (int i = 0; i < currentRow.length-1; i++) {
            if (currentRow[i] == currentRow[i+1]) {
                walls[i*2+1] = true;            //adding right-walls between cells of same set
            }
            else {
                int r = random();               //randomly putting up walls between adjacent
                if (r%2==0) {                   //cells of different sets
                    walls[i*2+1] = true;
                }
                else {
                    joinSets(currentRow[i], currentRow[i+1], i);
                }
            }
        }
        completeRow(rownr);
    }
    
    public void joinSets(int first, int second, int index) {
        
        for (int i = index; i < currentRow.length; i++) {
            if (currentRow[i] == second) {
                currentRow[i] = first;
                this.setsTable[first][1]++;
            }
        }
        this.availables[second] = true;
        free++;
        
    }
    
    public void completeRow(int rownr) {
        if (rownr > rows) return;
        for (int i = 0; i < walls.length; i++) {
            if (walls[i]) continue;
            else if (!walls[i] && rownr==rows && i%2==0) continue;
            else maze.carve(i+1,rownr);
        }
        this.prepareNextRow(rownr+1);
    }
    
    public void prepareNextRow(int rownr) {
        for (int i = 0; i < walls.length; i++) {
            if (i%2!=0) walls[i]=false;         //removing all the right walls
            else if (i%2==0 && walls[i]) {
                int id = currentRow[i/2+1];
                this.setsTable[id][1]--;
                currentRow[i/2+1] = 0;          //removing cells with a bottom-wall from their set
                walls[i] = false;               //removing the bottom-wall
            }
        }
        this.maze.printMaze();
        generateRow(rownr);
       
    }
    
    public int random() {
        return (int)(System.nanoTime()%100);
    }
    
    public int getAvailableSet() {
        int freeset = 0;
        for (int i = 1; i < this.availables.length; i++) {
            if (this.availables[i]) {
                freeset = i;
                this.availables[i] = false;
                free--;
                return freeset;
            }
        }
        return 0;
    }
}
