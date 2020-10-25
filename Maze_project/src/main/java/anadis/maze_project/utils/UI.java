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
    private static final String[] errors = {"Invalid input", 
        "No maze to solve yet..."};
    private static final String[] commands = {"Welcome to the maze-generation!",
        "Would you like to generate a perfect maze?", "Here are your options:",
        "(1) Generate labyrinth by Eller's Algorithm", "(2) Generate labyrinth "
        + "by Wilson's Algoritm", "(3) Solve maze", "(9) Run performance tests",
        "Number of rows: ", "Number of columns: ", "(-1) Quit"
    };
    boolean mazeGenerated = false;
    private Maze maze;
    private int[] proportions = new int[2];
    private PerformanceTester pt = new PerformanceTester();

    public UI() {

    }

    public void run() {
        System.out.println("\n------------------------------------\n");
        for (int i = 0; i < 2; i++) {
            System.out.println(commands[i]);
        }

        while (true) {
            
            System.out.println("\n------------------------------------\n");
            for (int i = 2; i < 7; i++) {
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
                    System.out.print(commands[8]);
                    c = Integer.valueOf(reader.nextLine());
                }

                this.proportions[0] = r;
                this.proportions[1] = c;
                generate(opt, r, c);
                maze.printMaze();
                mazeGenerated = true;
                continue;
            }
            if (opt == 3 && !mazeGenerated) {
                System.out.println(errors[1]);
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
                System.out.println("");
                TremauxSolve t = new TremauxSolve(maze.getGrid(), exitCoords[0],
                        exitCoords[1], exitCoords[2], exitCoords[3]);
                t.printSolution();
                System.out.println("");
            }
            
            if (opt == -1) {
                break;
            }
            
            if (opt == 9) {
                PerformanceTester pt = new PerformanceTester();
                System.out.println("\nRunning tests...\n");
                pt.run();
                System.out.println(pt.toString());
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
