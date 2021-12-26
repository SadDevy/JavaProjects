package PointProcessor;

public class Point {
    private double x;
    public double getX() {
        return x;
    }

    private double y;
    public double getY() {
        return y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Point point = (Point)o;
        if (point == null) //!!!
            return false;

        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return (int)(x + y);
    }
}
