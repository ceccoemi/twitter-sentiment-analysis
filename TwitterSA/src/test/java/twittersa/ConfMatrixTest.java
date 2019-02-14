package twittersa;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import twittersa.ConfMatrix;


public class ConfMatrixTest {

    @Test
    public void testAccuracy()
    {
        ConfMatrix matrix = new ConfMatrix(1, 1, 1, 1);
        assertEquals(0.5, matrix.accuracy(), 0);
    }

    @Test
    public void testToString()
    {
        ConfMatrix matrix = new ConfMatrix(123, 32, 200, 20);
        assertTrue(matrix.toString().length() >= 50);
    }

}
