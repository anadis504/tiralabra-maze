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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        
//        EllersAlgo ellers = new EllersAlgo(26,18);
//        ellers.generateRow(1);
        
        WilsonsAlgo wa= new WilsonsAlgo(6,6);
        wa.start();
    }
    
}
