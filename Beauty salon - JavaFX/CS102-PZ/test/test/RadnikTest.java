package test;

import entities.Radnik;
import entities.Usluga;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrijana Jovanovic
 */
public class RadnikTest {
    
    public RadnikTest() {
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

    /**
     * Test of racunajZaradu method, of class Radnik.
     */
    
    @Test
    public void testRacunajZaradu() {
        System.out.println("racunajZaradu");
        Usluga usluga = new Usluga(1, "Sminkanje", 2000);
        Radnik radnik = new Radnik();
        int expResult = 1000;
        int result = radnik.racunajZaradu(usluga);
        assertEquals(expResult, result);
       
    }
    
}
