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
public class MazeReaderImplTest {

    private final MazeReaderImpl reader = new MazeReaderImpl();

    @Test
    public void testOneRowMaze() throws Exception {
        final Maze maze = reader.read(new StringReader("S F"));
        assertEquals(MazeType.START, maze.getType(new Point(0,0)));
        assertEquals(MazeType.EMPTY, maze.getType(new Point(1,0)));
        assertEquals(MazeType.FINISH, maze.getType(new Point(2,0)));
    }

}
