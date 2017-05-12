package uk.co.craftsmanshiplimited.maze;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static uk.co.craftsmanshiplimited.maze.Orientation.*;

/**
 * Created by Henrik on 11/05/2017.
 */
@RunWith(JUnit4.class)
public class ExplorerTest {

    public static final String EXAMPLE_MAZE = "src/test/resources/ExampleMaze.txt";
    private Explorer explorer = new Explorer();
    private Maze maze;

    @Before
    public void setup() throws Exception {
        this.maze = new MazeReaderImpl().read(new FileReader(EXAMPLE_MAZE));
        this.explorer.dropToStartOf(maze);
    }

    @Test
    public void testDropAtStart() throws Exception {
        assertEquals(NORTH, this.explorer.getCurrentOrientation());
        assertEquals(maze.getStart(), this.explorer.getCurrentPosition());
        assertEquals(MazeType.WALL, this.explorer.lookAhead());
    }

    @Test
    public void testTurnLeft() throws Exception {
        assertEquals(NORTH, this.explorer.getCurrentOrientation());
        this.explorer.turnLeft();
        assertEquals(WEST, this.explorer.getCurrentOrientation());
        this.explorer.turnLeft();
        assertEquals(SOUTH, this.explorer.getCurrentOrientation());
        this.explorer.turnLeft();
        assertEquals(EAST, this.explorer.getCurrentOrientation());
        this.explorer.turnLeft();
        assertEquals(NORTH, this.explorer.getCurrentOrientation());
    }

    @Test
    public void testTurnRight() throws Exception {
        assertEquals(NORTH, this.explorer.getCurrentOrientation());
        this.explorer.turnRight();
        assertEquals(EAST, this.explorer.getCurrentOrientation());
        this.explorer.turnRight();
        assertEquals(SOUTH, this.explorer.getCurrentOrientation());
        this.explorer.turnRight();
        assertEquals(WEST, this.explorer.getCurrentOrientation());
        this.explorer.turnRight();
        assertEquals(NORTH, this.explorer.getCurrentOrientation());
    }

    @Test
    public void testLookAhead() throws  Exception {
        assertEquals(MazeType.WALL, this.explorer.lookAhead());
        this.explorer.turnRight();
        assertEquals(MazeType.EMPTY, this.explorer.lookAhead());
        this.explorer.turnRight();
        assertEquals(MazeType.WALL, this.explorer.lookAhead());
        this.explorer.turnRight();
        assertEquals(MazeType.WALL, this.explorer.lookAhead());
    }

    @Test
    public void testMove() throws  Exception {
        this.explorer.turnRight();
        this.explorer.move();
        assertEquals(new Point(4, 3), this.explorer.getCurrentPosition());
    }

    @Test(expected = IllegalStateException.class)
    public void testIllegalMove() throws  Exception {
        this.explorer.move();
    }

    @Test
    public void testCanMoveTo() throws Exception {
        assertFalse(this.explorer.canMoveTo(NORTH));
        assertTrue(this.explorer.canMoveTo(EAST));
        assertFalse(this.explorer.canMoveTo(WEST));
        assertFalse(this.explorer.canMoveTo(SOUTH));
    }

    @Test
    public void testSolve() throws  Exception {
        this.explorer.solve(maze);
        assertTrue(maze.isFinish(this.explorer.getLastPosition()));
    }

}
