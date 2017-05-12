package uk.co.craftsmanshiplimited.maze;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Henrik on 12/05/2017.
 */
@RunWith(JUnit4.class)
public class PointTest {

    private final Point point = new Point(2,3);

    @Test
    public void testContructor() throws Exception {
        assertEquals(2, point.width);
        assertEquals(3, point.height);
    }

    @Test
    public void testEqualsHashcode() throws Exception {
        assertFalse(point.equals(new Object()));
        assertTrue(point.equals(point));
        assertTrue(point.equals(new Point(2,3)));
        assertEquals(point.hashCode(), new Point(2,3).hashCode());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("(2,3)", point.toString());
    }
}
