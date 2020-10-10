package anadis.maze_project.algos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class TestEllersAlgo {

    public TestEllersAlgo() {
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
    public void testLastRowSetMembers() {
        EllersAlgo ela = new EllersAlgo(100, 100);
        ela.start();
        int[] cr = ela.getCurrentRow();
        int set1 = cr[0];
        int set2 = cr[1];
        for (int i = 0; i < cr.length; i++) {
            if (cr[i] != set1) {
                set2 = cr[i];
            }
        }
        int freeSets = ela.getFreeSets();
        assertEquals("Last row concists of more than one set", set1, set2);
        assertEquals("The amount of sets not correct", 99, freeSets);
    }

    @Test
    public void testOneRow() {
        EllersAlgo ea = new EllersAlgo(1, 100);
        TremauxSolve t = new TremauxSolve(ea.getMaze().getGrid(), 1, 1, 1, 100);
        assertEquals(true,t.getSolved());
    }
    
    @Test
    public void testLastRowSameSet() {
        EllersAlgo ela = new EllersAlgo(50,50);
        int[] row = ela.getCurrentRow();
        for (int i = 1; i < row.length; i++) {
            assertEquals(row[i], row[i-1]);
        }
    }

}
