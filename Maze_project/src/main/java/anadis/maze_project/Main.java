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
import anadis.maze_project.utils.IndexTree;
import anadis.maze_project.utils.UI;

/**
 *
 * @author anadis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        WilsonsAlgo willi = new WilsonsAlgo(50,50);
        willi.start();
        UI ui = new UI();

        ui.run();
    }

}
