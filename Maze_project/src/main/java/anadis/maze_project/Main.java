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
        
        /* TODO code application logic here
        System.out.println(" ______________");
        System.out.println("|_|_|_|_|_|_|_|");
        System.out.println("|   |     | | |");
        System.out.println("|1 2 3 4 5 6 7|");
        */
        
        Maze laby = new Maze(5,7);
        laby.printMaze();
        laby.carve(1, 2);
        laby.carve(3, 4);
        laby.printMaze();
        
        EllersAlgo ellers = new EllersAlgo(8,5);
        ellers.generateRow(1);
//        ellers.completeRow(3);
        for (int i = 0; i < 10; i++)
        System.out.println(ellers.random());
    }
    
}
