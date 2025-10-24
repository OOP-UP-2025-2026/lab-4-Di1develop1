import ua.opnu.java.inheritance.Point;

public class Point3D extends Point {
    private int z;

    public Point3D() {
        this(0, 0, 0);
    }

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    // сеттери
    public void setLocation(int x, int y, int z) {
        super.setLocation(x, y);
        this.z = z;
    }

    @Override
    public void setLocation(int x, int y) {   // скидає z до 0
        super.setLocation(x, y);
        this.z = 0;
    }

    // потрібні геттери для тестів
    public int getX() { return x; }  // x,y — protected у Point
    public int getY() { return y; }
    public int getZ() { return z; }

    // відстань до іншої 3D точки
    public double distance(Point3D p) {
        int dx = x - p.x;
        int dy = y - p.y;
        int dz = z - p.z;
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    @Override
    public double distanceFromOrigin() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}