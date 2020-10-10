/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.maze_project.utils;

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
public class TestIndexTree {

    public TestIndexTree() {
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
    public void returnFreeIndexWhenFree() {
        IndexTree it = new IndexTree(4, 5);
        int[] cell = it.getFreeIndex(3, 4);
//        System.out.println(cell[0] + ": " + cell[1]);
        assertEquals(cell[0], 3);
        assertEquals(cell[1], 4);
    }

    @Test
    public void returnNewIndexWhenVisited() {
        IndexTree it = new IndexTree(4, 5);
        it.markVisited(3, 4);
        int[] cell = it.getFreeIndex(3, 4);
        System.out.println(cell[0] + ": " + cell[1]);
        assertEquals(3, cell[0]);
        assertEquals(3, cell[1]);
    }

    @Test
    public void returnFirstIndex() {
        IndexTree it = new IndexTree(4, 5);
        it.markVisited(1, 1);
        int[] cell = it.getFreeIndex(1, 1);
        System.out.println(cell[0] + ": " + cell[1]);
        assertEquals(1, cell[0]);
        assertEquals(2, cell[1]);
    }

    @Test
    public void returnLastIndex() {
        IndexTree it = new IndexTree(4, 5);
        it.markVisited(4, 5);
        int[] cell = it.getFreeIndex(4, 5);
        System.out.println(cell[0] + ": " + cell[1]);
        assertEquals(4, cell[0]);
        assertEquals(4, cell[1]);
    }

    @Test
    public void testTinyTree() {
        IndexTree it = new IndexTree(2, 2);
        int cell[] = it.getFreeIndex(2, 2);

        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 2; j++) {
                it.markVisited(i, j);
                cell = it.getFreeIndex(i, j);
                System.out.println(cell[0] + ": " + cell[1]);
            }
        }
        assertEquals(0, cell[0]);
        assertEquals(0, cell[1]);
    }

    @Test
    public void returnZeroCell() {
        IndexTree it = new IndexTree(4, 5);
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 5; j++) {
                it.markVisited(i, j);
                int cell[] = it.getFreeIndex(i, j);
                System.out.println(cell[0] + ": " + cell[1]);
            }
        }
        int[] cell = it.getFreeIndex(4, 3);
        System.out.println(cell[0] + ": " + cell[1]);
        assertEquals(0, cell[0]);
        assertEquals(0, cell[1]);
    }

}
