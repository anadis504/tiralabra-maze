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
import java.util.Arrays;

/**
 *
 * The actual array sizes increase by a factor of num².
 */
public class PerformanceTester {

    private final int[] nums = {3, 10, 32, 71, 100, 180, 320, 710, 1000};
    private final int numberOfRuns = nums.length;
    private final double[] wilsonsGen = new double[numberOfRuns];
    private final double[] ellersGen = new double[numberOfRuns];
    private final double[] tremauxSolve = new double[numberOfRuns];
    private final double[] mazeGen = new double[numberOfRuns];

    public PerformanceTester() {

    }

    public void run() {
        int n = 100;
        for (int run = 0; run < nums.length; run++) {
            int num = nums[run];
            long[] times = new long[n];
            long[] solveTimes = new long[n];
            long t;

            // Measure median generation time for Maze template
            Maze laby = new Maze(num, num);
            for (int i = 0; i < n; i++) {
                t = System.nanoTime();
                laby = new Maze(num, num);
                t = System.nanoTime() - t;
                times[i] = t;
            }
            Arrays.sort(times);
            mazeGen[run] = times[times.length / 2] / 1000000.0;

            /**
             * Measure median generation time for WilsonsAlgo for array size
             * less than 180*180 due to memory limitations (StackOverFlow)
             */
            if (num < 200) {
                WilsonsAlgo willy = new WilsonsAlgo(num, num);
                for (int i = 0; i < n; i++) {
                    t = System.nanoTime();
                    willy = new WilsonsAlgo(num, num);
                    t = System.nanoTime() - t;
                    times[i] = t;
                }
                Arrays.sort(times);
                wilsonsGen[run] = times[times.length / 2] / 1000000.0;
            }

            /**
             * Measure the median generation time of EllersAlgo and Measure
             * median solving time for Tremaux solving Algo
             */
            EllersAlgo elly = new EllersAlgo(num, num);
            char[][] grid = elly.getMaze().getGrid();
            TremauxSolve tremsy = new TremauxSolve(grid, 1, 1, num, num);
            for (int i = 0; i < n; i++) {
                t = System.nanoTime();
                elly = new EllersAlgo(num, num);
                t = System.nanoTime() - t;
                times[i] = t;
                grid = elly.getMaze().getGrid();
                t = System.nanoTime();
                tremsy = new TremauxSolve(grid, 1, 1, num, num);
                t = System.nanoTime() - t;
                solveTimes[i] = t;

            }
            Arrays.sort(times);
            ellersGen[run] = times[times.length / 2] / 1000000.0;
            Arrays.sort(solveTimes);
            tremauxSolve[run] = solveTimes[solveTimes.length / 2] / 1000000.0;

        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Maze template generation times:\n");
        appendResults(sb, mazeGen);
        
        sb.append("\nWilson's Algo generation times:\n");
        appendResults(sb, wilsonsGen);
        
        sb.append("\nEller's Algo generation times:\n");
        appendResults(sb, ellersGen);
        
        sb.append("\nTremaux solving algo generation times:\n");
        appendResults(sb, tremauxSolve);
        
        return sb.toString();
    }
    
    private void appendResults(StringBuilder sb, double[] results) {
        for (int i = 0; i < nums.length; i++) {
            String num = Integer.toString(nums[i]*nums[i]);
            for (int j = 0; j < 8 - num.length(); j++) {
                sb.append(" ");
            }
            sb.append(num);
            sb.append(": ");
            sb.append(results[i]);
            sb.append("ms");
            sb.append("\n");
        }
    }
}
