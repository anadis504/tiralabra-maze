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
    private static final String[] errors = {"Invalid input", "Your maze is"
        + " large, this can take some time...", "No maze to solve yet..."};
    private static final String[] commands = {"Welcome to the maze-generation!",
        "Would you like to generate a perfect maze?", "Here are your options:",
        "(1) Generate labyrinth by Eller's Algorithm", "(2) Generate labyrinth "
        + "by Wilson's Algoritm", "(3) Solve maze", "Number of rows: ",
        "Number of columns: ", "(-1) Quit"
    };
    boolean mazeGenerated = false;
    private Maze maze;
    private int[] proportions = new int[2];

    public UI() {

    }

    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.println(commands[i]);
        }

        while (true) {
            for (int i = 2; i < 6; i++) {
                System.out.println(commands[i]);
            }
            System.out.println(commands[commands.length - 1]);

            int opt = Integer.valueOf(reader.nextLine());
            if (opt > 0 && opt < 3) {
                System.out.print(commands[6]);
                int r = Integer.valueOf(reader.nextLine());
                while (r < 0) {
                    System.out.println(errors[0]);
                    System.out.print(commands[6]);
                    r = Integer.valueOf(reader.nextLine());
                }
                System.out.print(commands[7]);
                int c = Integer.valueOf(reader.nextLine());
                while (c < 0) {
                    System.out.println(errors[0]);
                    System.out.print(commands[7]);
                    c = Integer.valueOf(reader.nextLine());
                }
                if (r + c > 100 && opt == 2) {
                    System.out.println(errors[1]);
                }
                this.proportions[0] = r;
                this.proportions[1] = c;
                generate(opt, r, c);
                maze.printMaze();
                mazeGenerated = true;
                continue;
            }
            if (opt == 3 && !mazeGenerated) {
                System.out.println(errors[2]);
            }
            if (opt == 3 && mazeGenerated) {
                int[] exitCoords = new int[4];
                while (true) {
                    System.out.println("Plot coordinates for the start and "
                            + "finnish cells: \n (Format: startrow, startcol, "
                            + "finnishrow, finnishcolumn)");

                    String ans = reader.nextLine();
                    String[] xy = ans.split(",");
                    if (xy.length != 4) {
                        System.out.println(errors[0]);
                        continue;
                    }
                    int[] coords = new int[4];
                    for (int i = 0; i < xy.length; i++) {
                        String trim = xy[i].trim();
                        coords[i] = Integer.valueOf(trim);
                        System.out.println(coords[i]);
                    }
                    boolean error = false;
                    for (int i = 0; i < 4; i++) {
                        if (coords[i] > proportions[i % 2] || coords[i] < 1) {
                            System.out.println(errors[0]);
                            error = true;
                            return;
                        }
                        exitCoords[i] = coords[i];
                    }
                    if (error) {
                        continue;
                    }
                    break;
                }
                TremauxSolve t = new TremauxSolve(maze.getGrid(), exitCoords[0],
                        exitCoords[1], exitCoords[2], exitCoords[3]);
                t.printSolution();
            }
            if (opt == -1) {
                break;
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
