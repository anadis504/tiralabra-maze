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

        UI ui = new UI();

//        ui.generate(1, 60, 60);
//        ui.generate(2, 1, 1);
//        ui.generate(2, 3, 2);
//        ui.generate(2, 5, 7);
        ui.generate(2, 1, 10);
//        ui.run();
    }

}
