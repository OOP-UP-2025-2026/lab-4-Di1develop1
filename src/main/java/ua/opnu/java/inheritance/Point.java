package ua.opnu.java.inheritance;

public class Point {
    protected int x, y;

    public Point() {
        this(0,0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p) {
        int dx = x - p.x;
        int dy = y - p.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double distanceFromOrigin() {
        return Math.sqrt(x*x + y*y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}