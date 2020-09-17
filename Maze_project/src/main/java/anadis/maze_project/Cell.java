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
public class Cell {

    private int x;
    private int y;
    private boolean visited;
    private Cell parent;
    
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = false;
    }
    
    
    
}
