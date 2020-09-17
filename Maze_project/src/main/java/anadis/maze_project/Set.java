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
public class Set {

    private int id;
    private boolean openDown;
    private boolean joined;
    
    public Set(int id) {
        this.id = id;
        this.joined = false;
        this.openDown = false;
    }
    
    public int getId() {
        return this.id;
    }
    
    public boolean getJoined() {
        return this.joined;
    }
    
    public void setId(int newId) {
        this.id = newId;
    }
    
    
}
