/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.maze_project;

import anadis.maze_project.algos.EllersAlgo;
import anadis.maze_project.algos.TremauxSolve;
import anadis.maze_project.algos.WilsonsAlgo;
import anadis.maze_project.domain.Maze;

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
        EllersAlgo ellers = new EllersAlgo(15, 30);
        ellers.start();
        TremauxSolve ts = new TremauxSolve(ellers.getMaze());
        ts.go(1, 1);
//
//        TremauxSolve tr = new TremauxSolve(new Maze(4, 3));
//        tr.go(4, 3);
//        WilsonsAlgo wa = new WilsonsAlgo(15, 30);
//        wa.start();
    }

}
