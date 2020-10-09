/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.maze_project.algos;

import anadis.maze_project.domain.Maze;

/**
 * Perfect maze generating algorithm
 * <p>
 * Generates maze in O(n²m) time and o(n) space one row at a time by keeping 
 * track of sets of cells to avoid loops and isolated ares.
 * </p>
 */


public class EllersAlgo {

    private int[] currentRow;
    private boolean[] walls, availables;
    private int rows, cols, free;
    private Maze maze;
    private boolean lastRow;
    private int[] setsMembers, openBottoms, seen;

    /**
     * Generates maze in O(n²m) time and o(n) space one row at a time by keeping 
     * track of sets of cells to avoid loops and isolated ares.
     * @param r is the amount of rows. 
     * @param c the amount of columns.
     */
    public EllersAlgo(int r, int c) {
        this.cols = c;
        this.rows = r;
        this.walls = new boolean[cols * 2 - 1];
        this.setsMembers = new int[cols + 1];
        this.openBottoms = new int[cols + 1];
        this.seen = new int[cols + 1];
        this.currentRow = new int[cols];
        this.availables = new boolean[cols + 1];
        this.free = 0;
        this.lastRow = false;

        for (int i = 0; i < cols; i++) {
            this.setsMembers[i + 1] = 1;
            this.currentRow[i] = i + 1;
            this.walls[i] = false;
            this.walls[i * 2] = false;
        }

        this.maze = new Maze(rows, cols);
        start();
    }

    /**
     * Generates current row.
     * Assigns cells without a set to their own unique set.
     * Randomly adding right-walls between cells according to the rules.
     * Unions adjacent sets of a right wall is not put up.
     * Calls to addBottoms method before completing the row.
     * If current row is the last row, all sets have to be in union.
     * arr[] walls indicates whether there is a wall.
     * 
     * @param rownr for keeping track of the number of rows.
     */
    public void generateRow(int rownr) {
        if (rownr > rows) {
            return;
        }
        if (rownr == rows) {
            lastRow = true;
        }
        for (int i = 0; i < currentRow.length; i++) {

            if (currentRow[i] == 0) {
                int set = this.getAvailableSet();        
                currentRow[i] = set;                    
                setsMembers[set]++;
            }

        }                                         

        if (lastRow) {
            completeMaze();
        }
        for (int i = 0; i < currentRow.length - 1; i++) {
            if (currentRow[i] == currentRow[i + 1]) {
                walls[i * 2 + 1] = true;            
            } 
            else {
                int r = random();               
                if (r % 2 == 0) {                   
                    walls[i * 2 + 1] = true;       
                } else {
                    joinSets(currentRow[i], currentRow[i + 1]);
                }
            }
        }
        addBottoms();
        completeRow(rownr);
    }

    /**
     * When last row has come maze is completed by joining all sets into one.
     */
    public void completeMaze() {
        for (int i = 0; i < currentRow.length - 1; i++) {
            if (currentRow[i] == currentRow[i + 1]) {
                walls[i * 2 + 1] = true;             
            } 
            else {
                joinSets(currentRow[i], currentRow[i + 1]);
            }
        }
        completeRow(rows);
        return;
    }

    /**
     * Adds bottom walls to the current row by following the rules or at random
     * if allowed.
     * arr[] 'openBottoms' keeps track of set being open downwards together with
     * arr[] 'seen' and 'setsMembers' to know how many of a given set's existing
     * cells have been seen out so far.
     */
    public void addBottoms() {
        for (int i = 0; i < this.seen.length; i++) {
            this.openBottoms[i] = 0;        
            this.seen[i] = 0;               
        }
        for (int i = 0; i < currentRow.length; i++) {
            int set = this.currentRow[i];
            this.seen[set]++;
            if (this.openBottoms[set] == 0) {
                if (this.setsMembers[set] == this.seen[set]) {
                    walls[i * 2] = false;
                    continue;
                }
            }
            int r = random();
            if (r % 2 == 0) {
                walls[i * 2] = true;
                continue;
            }
            this.openBottoms[set]++;
        }
    }

    /**
     * Merges to different sets into.
     * Adds all the cells from the second set to the first in the current row.
     * Releasing the freed set to be available.
     * 
     * @param first the id of the first set to be merged.
     * @param second the id of the second set to be merged.
     */
    public void joinSets(int first, int second) {

        for (int i = 0; i < currentRow.length; i++) {
            if (currentRow[i] == second) {
                currentRow[i] = first;
                this.setsMembers[first]++;
                this.setsMembers[second]--;
            }
        }
        this.availables[second] = true;
        free++;
    }

    /**
     * Completes the given row by carving it into the maze template.
     * 
     * @param rownr to keep track of the number of rows.
     */
    public void completeRow(int rownr) {
        if (rownr > rows) {
            return;
        }
        for (int i = 0; i < walls.length; i++) {
            if (walls[i]) {
                continue;
            } else if (!walls[i] && rownr == rows && i % 2 == 0) {
                continue;
            } else {
                maze.carve(i + 1, rownr);
            }
        }
        if (rownr == rows) {
            return;
        }
        this.prepareNextRow(rownr + 1);
    }

    /**
     * Prepares for next row by first removing all right walls (in odd indexes 
     * of arr[] walls).
     * Removes all cells with a bottom wall from their sets.
     * Finally removes all bottom walls.
     * 
     * @param rownr for keeping track of the row number.
     */
    public void prepareNextRow(int rownr) {
        for (int i = 0; i < walls.length; i++) {
            if (i % 2 != 0) {
                walls[i] = false;         
            } else if (i % 2 == 0 && walls[i]) {
                int id = currentRow[i / 2];
                this.setsMembers[id]--;
                currentRow[i / 2] = 0;            
                walls[i] = false;               
            }
        }

        generateRow(rownr);

    }

    public int random() {
        return (int) (System.nanoTime() % 100);
    }

    /**
     * Starts from row 1.
     */
    public void start() {
        generateRow(1);
    }

    /**
     * Return the id of a set that is currently available
     * 
     * @return set id or zero if no set is available
     */
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

    /**
     * 
     * @return the amount of currently available sets.
     */
    public int getFreeSets() {
        return this.free;
    }

    /**
     * 
     * @return an array of set-ids of the current row indicating which cells are
     * belong to which set.
     */
    public int[] getCurrentRow() {
        return this.currentRow;
    }

    /**
     * 
     * @return the generated maze.
     */
    public Maze getMaze() {
        return this.maze;
    }
}
