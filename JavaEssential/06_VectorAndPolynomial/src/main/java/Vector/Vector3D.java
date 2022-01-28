package Vector;

public class Vector3D {
    private double x;

    public double getX() {
        return x;
    }

    private double y;

    public double getY() {
        return y;
    }

    private double z;

    public double getZ() {
        return z;
    }

    public Vector3D() {
        this(0, 0, 0);
    }

    public Vector3D(double x) {
        this(x, 0, 0);
    }

    public Vector3D(double x, double y) {
        this(x, y, 0);
    }

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || !isVector3D(o))
            return false;

        Vector3D vector = (Vector3D) o;
        return x == vector.getX()
                && y == vector.getY()
                && z == vector.getZ();
    }

    private boolean isVector3D(Object o) {
        try {
            Vector3D __ = (Vector3D) o;

            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 33 * (Double.hashCode(x) >> 1)
                + 17 * (Double.hashCode(y) >> 2)
                + (Double.hashCode(z) >> 3);
    }

    @Override
    public String toString() {
        return String.format("{{%f; %f; %f}}", x, y, z);
    }

    public static Vector3D add(Vector3D a, Vector3D b)
            throws NullPointerException {
        if (a == null)
            throw new NullPointerException("a");

        if (b == null)
            throw new NullPointerException("b");

        return new Vector3D(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    }

    public static Vector3D subtract(Vector3D a, Vector3D b)
            throws NullPointerException {
        if (a == null)
            throw new NullPointerException("a");

        if (b == null)
            throw new NullPointerException("b");

        return new Vector3D(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
    }

    public static double multiply(Vector3D a, Vector3D b)
            throws NullPointerException {
        if (a == null)
            throw new NullPointerException("a");

        if (b == null)
            throw new NullPointerException("b");

        return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
    }

    public static Vector3D cross(Vector3D a, Vector3D b)
            throws NullPointerException {
        if (a == null)
            throw new NullPointerException("a");

        if (b == null)
            throw new NullPointerException("b");

        double x = a.getY() * b.getZ() - a.getZ() * b.getY();
        double y = a.getX() * b.getZ() - a.getZ() * b.getX();
        double z = a.getX() * b.getY() - a.getY() * b.getX();

        return new Vector3D(x, -y, z);
    }

    public static boolean equals(Vector3D a, Vector3D b) {
        if (a == b)
            return true;

        if (a == null)
            return false;

        return a.equals(b);
    }
}
