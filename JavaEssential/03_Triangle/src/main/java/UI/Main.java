package UI;

import Models.Triangle;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        double a = inputSide("Сторона А:");
        double b = inputSide("Сторона B:");
        double c = inputSide("Сторона C:");

        try {
            Triangle triangle = Triangle.createTriangle(a, b, c);

            double perimeter = triangle.calculatePerimeter();
            double area = triangle.calculateArea();

            showResult(triangle, perimeter, area);
        }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private static double inputSide(String inputMessage) {
        System.out.println(inputMessage);

        AtomicReference<Double> valueReference = new AtomicReference<Double>();
        while (!tryParseSide(scanner.nextLine(), valueReference) || valueReference.get() <= 0) {
            System.out.println("Введите положительное вещественное число.");
        }

        return valueReference.get();
    }

    private static boolean tryParseSide(String line, AtomicReference<Double> result) {
        try {
            Double value = Double.parseDouble(line);
            result.set(value);

            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private static void showResult(Triangle triangle, double perimeter, double area) {
        String triangleSides = String.format("Треугольник со сторонами: %f, %f, %f.", triangle.getA(), triangle.getB(), triangle.getC());
        System.out.println(triangleSides);

        String trianglePerimeter = String.format("Периметер: %f.", perimeter);
        System.out.println(trianglePerimeter);

        String triangleArea = String.format("Площадь: %f.", area);
        System.out.println(triangleArea);
    }
}
