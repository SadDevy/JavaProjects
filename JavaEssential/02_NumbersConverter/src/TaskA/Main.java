package TaskA;

import Calculator.RootCalculator;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final double accuracyMaxBorder = 0.11;

    public static void main(String[] args) {
        double number = inputDouble("Число:", "Введите вещественное число:", 0, null);
        int n = inputInteger("Степерь корня:", "Введите целое число:", 0, null);
        double accuracy = inputDouble("Точность:", "Введите вещественное число:", 0, accuracyMaxBorder);

        double newtonNRoot = RootCalculator.calculateRootNewton(number, n, accuracy);
        double standardNRoot = RootCalculator.calculateRoot(number, n);

        showResult(newtonNRoot, standardNRoot, accuracy);
    }

    private static double inputDouble(String inputMessage, String failureMessage, double minBorder, Double maxBorder) {
        System.out.println(inputMessage);

        AtomicReference<Double> valueReference = new AtomicReference<Double>();
        while (!tryParseDouble(scanner.nextLine(), valueReference) || valueReference.get() < minBorder || (maxBorder != null && valueReference.get() > maxBorder)) {
            System.out.println(failureMessage);
            ShowBorderMessage(minBorder, maxBorder);
        }

        return valueReference.get();
    }

    private static boolean tryParseDouble(String line, AtomicReference<Double> result) {
        try {
            Double value = Double.parseDouble(line);
            result.set(value);

            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private static int inputInteger(String inputMessage, String failureMessage, int minBorder, Integer maxBorder) {
        System.out.println(inputMessage);

        AtomicReference<Integer> valueReference = new AtomicReference<Integer>();
        while (!tryParseInteger(scanner.nextLine(), valueReference) || valueReference.get() < minBorder || (maxBorder != null && valueReference.get() > maxBorder)) {
            System.out.println(failureMessage);
            ShowBorderMessage(minBorder, maxBorder);
        }

        return valueReference.get();
    }

    private static boolean tryParseInteger(String line, AtomicReference<Integer> result) {
        try {
            Integer value = Integer.parseInt(line);
            result.set(value);

            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private static <T> void ShowBorderMessage(double minBorder, T maxBorder) {
        String borderMessageFormat = maxBorder == null ? "Число больше %f:" : "Число больше %f и меньше %f:";

        System.out.printf(borderMessageFormat, minBorder, maxBorder);
        System.out.println();
    }

    private static void showResult(double newtonRoot, double standardRoot, double accuracy) {
        String newtonResult = String.format("Результат методом Ньютона: %f", newtonRoot);
        System.out.println(newtonResult);
        String standardResult = String.format("Результат стандартным методом: %f", standardRoot);
        System.out.println(standardResult);

        boolean areEqual = RootCalculator.equals(newtonRoot, standardRoot, accuracy);

        String equalityMessageFormat = areEqual ? "Результаты равны c точностью %f." : "Результаты не равны с точностью %f.";
        String result = String.format(equalityMessageFormat, accuracy);

        System.out.println(result);
    }
}
