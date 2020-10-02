/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.maze_project.algos;

import anadis.maze_project.domain.Maze;
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
public class TestTremauxSolve {

    public TestTremauxSolve() {
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
    public void testUncarvedMaze() {
        Maze laby = new Maze(15, 30);
        TremauxSolve tr = new TremauxSolve(laby.getGrid(), 1, 1, 15, 30);
        int amount = 0;
        for (int i = 1; i < tr.getCols(); i++) {
            for (int j = 1; j < tr.getRows(); j++) {
                tr.getDirections(0, j, i);
                amount += tr.getAmount();
            }
        }
        assertEquals("Uncarved maze shouldn't have paths", 0, amount);
    }
}
