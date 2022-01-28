package TaskB;

import NumberConverter.BaseSystem;
import NumberConverter.Converter;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int number = inputInteger("Исходное число:", "Введите целое положительное число:", 0);

        for (BaseSystem baseSystem : getBaseSystems()) {
            String baseValue = Converter.toBase(number, baseSystem);
            String baseValueStandard = Converter.toBase(number, baseSystem);

            showResult(baseSystem, baseValue, baseValueStandard);
        }
    }

    private static BaseSystem[] getBaseSystems() {
        return BaseSystem.values();
    }

    private static int inputInteger(String inputMessage, String failureMessage, int minBorder) {
        System.out.println(inputMessage);

        AtomicReference<Integer> valueReference = new AtomicReference<Integer>();
        while (!tryParseInteger(scanner.nextLine(), valueReference) || valueReference.get() < minBorder) {
            System.out.println(failureMessage);
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

    private static void showResult(BaseSystem toBase, String value, String standardValue) {
        String toBaseLine = String.format("Система счисления: %s", toBase);
        System.out.println(toBaseLine);
        String toBaseByAlgorithmLine = String.format("По алгоритму: %s", value);
        System.out.println(toBaseByAlgorithmLine);
        if (standardValue != null) {
            String toBaseByStandardLine = String.format("Стандартными средствами: %s", standardValue);
            System.out.println(toBaseByStandardLine);

            String equalityMessage = (value.equals(standardValue)) ? "Результаты совпадают." : "Результаты не совпадают.";
            System.out.println(equalityMessage);
        }

        System.out.println();
    }
}
