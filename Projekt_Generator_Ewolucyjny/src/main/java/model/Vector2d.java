package model;

import java.util.Objects;

public class Vector2d{
    final int x;
    final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + Integer.toString(x) + "," + Integer.toString(y) + ")";
    }

    public boolean precedes(Vector2d other) {
        return x <= other.x && y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return x >= other.x && y >= other.y;
    }

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(x, other.x), Math.max(y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(x, other.x), Math.min(y, other.y));
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(x - other.x, y - other.y);
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;

        if (this.x == that.x && this.y == that.y)
            return true;
        else
            return false;

    }

    public Vector2d wrapBy(Vector2d lowerLeftCorner, Vector2d upperRightCorner){
        int x = this.x,y = this.y;
        if(x == lowerLeftCorner.x-1) x = upperRightCorner.x;
        if(x == upperRightCorner.x+1) x = lowerLeftCorner.x;
        if(y == lowerLeftCorner.y-1) y = upperRightCorner.y;
        if(y == upperRightCorner.y+1) y = lowerLeftCorner.y;

        return new Vector2d(x,y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector2d opposite() {
        return new Vector2d(-x, -y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}


