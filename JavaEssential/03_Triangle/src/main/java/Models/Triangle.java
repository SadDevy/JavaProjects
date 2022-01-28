package Models;

public class Triangle {
    private double A;
    public double getA() {
        return A;
    }

    private double B;
    public double getB() {
        return B;
    }

    private double C;
    public double getC() {
        return C;
    }

    private Triangle(double a, double b, double c) {
        A = a;
        B = b;
        C = c;
    }

    public static Triangle createTriangle(double a, double b, double c)
            throws IllegalStateException {
        if (couldExist(a, b, c))
            return new Triangle(a, b, c);

        throw new IllegalStateException(String.format("Треугольник не может существовать со сторонами: %f, %f, %f.", a, b, c));
    }

    public static boolean couldExist(double a, double b, double c) {
        return (a > 0) && (b > 0) && (c > 0) &&
                (a < b + c) && (b < c + a) && (c < a + b);
    }

    public double calculatePerimeter() {
        return A + B + C;
    }

    public double calculateArea() {
        double halfPerimeter = calculatePerimeter() * 0.5;
        return Math.pow(halfPerimeter * (halfPerimeter - A) * (halfPerimeter - B) * (halfPerimeter - C), 0.5);
    }
}
