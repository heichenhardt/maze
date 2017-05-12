package uk.co.craftsmanshiplimited.maze;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by Henrik on 11/05/2017.
 */
public class Point {

    final int width;
    final int height;

    public Point(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        final Point that  = (Point) o;
        return new EqualsBuilder()
                .append(that.width, this.width)
                .append(that.height, this.height)
                .isEquals();
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .append(this.width)
                .append(this.height)
                .hashCode();
    }

    @Override
    public final String toString() {
        return String.format("(%d,%d)", width, height);
    }
}
