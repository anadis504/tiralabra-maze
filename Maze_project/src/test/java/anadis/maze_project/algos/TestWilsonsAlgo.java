/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.maze_project.algos;

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
public class TestWilsonsAlgo {

    public TestWilsonsAlgo() {
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
    public void testTen() {
        WilsonsAlgo willy = new WilsonsAlgo(10, 10);
        TremauxSolve ts = new TremauxSolve(willy.getGrid(), 1, 1, 10, 10);
        assertEquals(true, ts.getSolved());
    }

    @Test
    public void testOne() {
        WilsonsAlgo willy = new WilsonsAlgo(1, 1);
        assertEquals(1, willy.gerCols());
        assertEquals(1, willy.getRows());
    }

    @Test
    public void testVectorMaze() {
        WilsonsAlgo willyR = new WilsonsAlgo(1, 10);
        WilsonsAlgo willyC = new WilsonsAlgo(10, 1);
        TremauxSolve trR = new TremauxSolve(willyR.getGrid(), 1, 1, 1, 10);
        assertEquals(1, willyR.getRows());
        assertEquals(1, willyC.gerCols());
        assertEquals(true, trR.getSolved());
    }

//    @Test
//    public void testBiggestMaze() {
//        WilsonsAlgo willy = new WilsonsAlgo(100, 100);
//        TremauxSolve tremso = new TremauxSolve(willy.getGrid(), 1, 1, 100, 100);
//        assertEquals(true, tremso.getSolved());
//    }
}
