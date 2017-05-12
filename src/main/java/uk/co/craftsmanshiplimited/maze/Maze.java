package uk.co.craftsmanshiplimited.maze;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henrik on 11/05/2017.
 */
public class Maze {

    private List<List<MazeType>> coordinates;
    private Point start;
    private Point finish;

    public Maze(final List<String> rows) {
        this.coordinates = new ArrayList<>();
        rows.forEach(row -> this.addRow(row));

        if (this.start == null) {
            throw new IllegalArgumentException("Maze needs one start point");
        }
        if (this.finish == null) {
            throw new IllegalArgumentException("Maze needs one finish point");
        }
    }

    public final Point getStart() {
        return start;
    }

    public final MazeType getType(final Point point) {
        return this.coordinates.get(point.height).get(point.width);
    }

    public final boolean isEmpty(final Point point) {
        return getType(point) == MazeType.EMPTY;
    }

    public final boolean isFinish(final Point point) {
        return getType(point) == MazeType.FINISH;
    }

    private void addRow(final String row) {
        final List<MazeType> mazeTypeRow = new ArrayList<>();
        for (char c : row.toCharArray()) {
            switch (c) {
                case 'X':
                    mazeTypeRow.add(MazeType.WALL);
                    break;
                case ' ':
                    mazeTypeRow.add(MazeType.EMPTY);
                    break;
                case 'S':
                    if (this.start != null) {
                        throw new IllegalArgumentException(
                                "Encountered multiple start points");
                    }
                    this.start = new Point(mazeTypeRow.size(), getHeight());
                    mazeTypeRow.add(MazeType.START);
                    break;
                case 'F':
                    if (this.finish != null) {
                        throw new IllegalArgumentException(
                                "Encountered multiple finish points");
                    }
                    this.finish = new Point(mazeTypeRow.size(), getHeight());
                    mazeTypeRow.add(MazeType.FINISH);
                    break;
                default: throw new IllegalArgumentException(
                        "Unknown structure in Maze in row: '" + row + "'");
            }
        }
        this.coordinates.add(mazeTypeRow);
    }

    private int getHeight() {
        return coordinates.size();
    }
}
