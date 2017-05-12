package uk.co.craftsmanshiplimited.maze;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Created by Henrik on 11/05/2017.
 */
@RunWith(JUnit4.class)
public class MazeTest {

    private final MazeReaderImpl reader = new MazeReaderImpl();

    @Test
    public void testMultiRowMaze() throws Exception {
        final StringBuilder builder =
                new StringBuilder()
                        .append("S X").append(System.lineSeparator())
                        .append("X F");
        final Maze maze = reader.read(new StringReader(builder.toString()));
        assertEquals(MazeType.START, maze.getType(new Point(0,0)));
        assertEquals(MazeType.EMPTY, maze.getType(new Point(1,0)));
        assertEquals(MazeType.WALL, maze.getType(new Point(2,0)));
        assertEquals(MazeType.WALL, maze.getType(new Point(0,1)));
        assertEquals(MazeType.EMPTY, maze.getType(new Point(1,1)));
        assertEquals(MazeType.FINISH, maze.getType(new Point(2,1)));
    }

    @Test
    public void testGetStart() throws Exception {
        final Maze maze = reader.read(new StringReader("XFS"));
        assertEquals(new Point(2, 0), maze.getStart());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithMultipleStartPoints() throws Exception {
        reader.read(new StringReader("SXS"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithNoStartPoint() throws Exception {
        reader.read(new StringReader("XXF"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithMultipleFinishPoints() throws Exception {
        reader.read(new StringReader("XFF"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithNoFinishPoints() throws Exception {
        reader.read(new StringReader("XXS"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnknownCharacter() throws Exception {
        reader.read(new StringReader("W"));
    }
}
