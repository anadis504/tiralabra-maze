/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.maze_project.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anadis
 */
public class TestMaze {

    public TestMaze() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void test_maze_size() {
        Maze maze = new Maze(50, 50);
        char[][] grid = maze.getGrid();
        assertEquals("Amount of rows not 50", 51, grid.length);
        assertEquals("Amount of columns is not 50", 101, grid[0].length);
    }

    @Test
    public void testFirstAndLastChar() {
        Maze maze = new Maze(50, 50);
        char[][] grid = maze.getGrid();
        assertEquals("Upper left corner not '_'", '_', grid[0][0]);
        assertEquals("Lower right corner not '|'", '|', grid[50][100]);
    }

    public void testCarve() {
        Maze maze = new Maze(50, 50);
        char[][] grid = maze.getGrid();
        char a = grid[0][0];
        char b = grid[50][50];
        char c = grid[25][25];
        maze.carve(0, 0);
        assertEquals("Carved in the framewall", a, grid[0][0]);
        assertEquals("Carved in the framewall", b, grid[50][50]);
        assertEquals("Did not carve when it should", ' ', grid[25][25]);
    }

    @Test
    public void invalidLTest() {
        Maze maze = new Maze(10, 10);
        char[][] grid1 = maze.getGrid();
        maze.carve(-1, 0);
        char[][] grid2 = maze.getGrid();
        maze.printMaze();
        assertEquals("Carved outside grids index", grid1, grid2);
    }
}
