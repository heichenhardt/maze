package uk.co.craftsmanshiplimited.maze;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Henrik on 11/05/2017.
 */
public interface MazeReader  {
    Maze read(Reader input) throws IOException;
}
