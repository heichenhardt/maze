package uk.co.craftsmanshiplimited.maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import static java.util.stream.Collectors.toList;

/**
 * Created by Henrik on 11/05/2017.
 */
public class MazeReaderImpl implements MazeReader {
    @Override
    public final Maze read(final Reader input) throws IOException {
        return new Maze(new BufferedReader(input).lines().collect(toList()));
    }
}
