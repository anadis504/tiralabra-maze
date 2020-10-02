/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.maze_project.utils;

import anadis.maze_project.algos.EllersAlgo;
import anadis.maze_project.algos.TremauxSolve;
import anadis.maze_project.algos.WilsonsAlgo;
import anadis.maze_project.domain.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author anadis
 */
public class UI {

    private Scanner reader = new Scanner(System.in);
    private static final String[] errors = {"Invalid input", "Your maze is quite"
        + " big, this can take some time", "No maze to solve yet"};
    private static final String[] commands = {"Welcome to the maze-generation!",
        "Would you like to generate a perfect maze?", "Here are your options:",
        "(1) Generate labyrinth by Eller's Algorithm", "(2) Generate labyrinth "
        + "by Wilson's Algoritm", "(3) See the solution", "(9) See your options",
        "Number of rows: ", "Number of columns: ", "(-1) Quit"
    };
    boolean mazeGenerated = false;
    private Maze maze;

    public UI() {

    }

    public void run() {
        while (true) {

            for (int i = 0; i < 7; i++) {
                System.out.println(commands[i]);
            }
            System.out.println(commands[commands.length - 1]);
            int opt = Integer.valueOf(reader.nextLine());
            if (opt > 0 && opt < 3) {
                System.out.print(commands[7]);
                int r = Integer.valueOf(reader.nextLine());
                while (r < 0) {
                    System.out.println(errors[0]);
                    System.out.print(commands[7]);
                    r = Integer.valueOf(reader.nextLine());
                }
                System.out.print(commands[8]);
                int c = Integer.valueOf(reader.nextLine());
                while (c < 0) {
                    System.out.println(errors[0]);
                    System.out.print(commands[7]);
                    c = Integer.valueOf(reader.nextLine());
                }
                if (r + c > 200) {
                    System.out.println(errors[1]);
                }
                generate(opt, r, c);
                maze.printMaze();
                mazeGenerated = true;
                continue;
            }
            if (opt == 3 && !mazeGenerated) {
                System.out.println(errors[2]);
            }
            if (opt == 3 && mazeGenerated) {
                int fx = maze.getRows()-1;
                int fy = maze.getCols()-1;
                System.out.println(fx + " as fx, as fy " + fy);
                TremauxSolve tr = new TremauxSolve(maze.getGrid(), 1, 1, fx, fy);
                tr.printSolution();
            }
        }
    }

    public void generate(int algo, int r, int c) {
        if (algo == 1) {
            this.maze = new EllersAlgo(r, c).getMaze();
        }
        if (algo == 2) {
            this.maze = new WilsonsAlgo(r, c).getMaze();
        }
    }
}
