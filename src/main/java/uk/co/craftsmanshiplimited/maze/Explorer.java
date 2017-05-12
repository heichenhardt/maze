package uk.co.craftsmanshiplimited.maze;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static uk.co.craftsmanshiplimited.maze.MazeType.EMPTY;
import static uk.co.craftsmanshiplimited.maze.MazeType.FINISH;

/**
 * Created by Henrik on 11/05/2017.
 */
public class Explorer {

    private Maze maze;
    private Orientation currentOrientation;
    private Deque<Point> stack;
    private Deque<Point> takenPath;
    private Set<Point> discovered;

    public Explorer() {
        this.stack = new ArrayDeque<>();
        this.takenPath = new ArrayDeque<>();
        this.discovered = new HashSet<>();
    }

    public final void dropToStartOf(final Maze maze) {
        this.maze = maze;
        this.stack.addLast(this.maze.getStart());
        this.currentOrientation = Orientation.NORTH;
    }

    public final void solve(final Maze maze) {
        this.dropToStartOf(maze);
        while (this.getCurrentPosition() != null) {
            final Point current = this.stack.pollLast();
            if (!discovered.contains(current)) {
                discovered.add(current);
                this.takenPath.addLast(current);
                if (maze.isFinish(current)) {
                    //Terminate if we find the end
                    break;
                }
                for (final Orientation orientation : Orientation.values()) {
                    final Point next =
                            calculateNextMove(current, orientation);
                    if ((maze.isEmpty(next) || maze.isFinish(next))
                            && !discovered.contains(next)) {
                        stack.addLast(next);
                    }
                }
            }
        }
    }

    public final Point getCurrentPosition() {
        return this.stack.peekLast();
    }

    public final Orientation getCurrentOrientation() {
        return this.currentOrientation;
    }

    public final void move() {
        if (lookAhead() == EMPTY || lookAhead() == FINISH) {
            final Point nextPoint =
                    calculateNextMove(getCurrentPosition(), currentOrientation);
            takenPath.addLast(stack.pollLast());
            stack.addLast(nextPoint);
        } else {
           throw new IllegalStateException("Cannot move into given direction");
        }
    }

    public final boolean canMoveTo(final Orientation orientation) {
        final Point point =
                calculateNextMove(getCurrentPosition(), orientation);
        final MazeType type = maze.getType(point);
        return type == MazeType.EMPTY || type == MazeType.FINISH;
    }

    public final MazeType lookAhead() {
        final Point point =
                calculateNextMove(getCurrentPosition(), currentOrientation);
        return maze.getType(point);
    }

    public final void turnLeft() {
        switch (currentOrientation) {
            case NORTH: currentOrientation = Orientation.WEST; break;
            case WEST: currentOrientation = Orientation.SOUTH; break;
            case SOUTH: currentOrientation = Orientation.EAST; break;
            case EAST: currentOrientation = Orientation.NORTH; break;
            default: throw new IllegalStateException(
                    "Unknown currentOrientation: " + currentOrientation);
        }
    }

    public final void turnRight() {
        switch (currentOrientation) {
            case NORTH: currentOrientation = Orientation.EAST; break;
            case EAST: currentOrientation = Orientation.SOUTH; break;
            case SOUTH: currentOrientation = Orientation.WEST; break;
            case WEST: currentOrientation = Orientation.NORTH; break;
            default: throw new IllegalStateException(
                    "Unknown currentOrientation: " + currentOrientation);
        }
    }

    public final Point getLastPosition() {
        return takenPath.getLast();
    }

    private Point calculateNextMove(
            final Point current, final Orientation orientation) {
        switch (orientation) {
            case NORTH: return new Point(current.width, current.height - 1);
            case EAST: return new Point(current.width + 1, current.height);
            case SOUTH: return new Point(current.width, current.height + 1);
            case WEST: return new Point(current.width - 1, current.height);
            default: throw new IllegalStateException(
                    "Unknown orientation: " + currentOrientation);
        }
    }

}
