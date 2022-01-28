package UI;

import Calculator.GCDCalculator;

import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] numbers = inputIntegers("Ввод чисел:");

        AtomicReference<Duration> euclideanDurationReference = new AtomicReference<Duration>();
        int gcdEuclidean = GCDCalculator.calculateEuclidean(euclideanDurationReference, numbers);
        AtomicReference<Duration> steinDurationReference = new AtomicReference<Duration>();
        int gcdStein = GCDCalculator.calculateStein(steinDurationReference, numbers);

        showResult(gcdEuclidean, gcdStein, euclideanDurationReference.get(), steinDurationReference.get());
    }

    private static int[] inputIntegers(String inputMessage) {
        System.out.println(inputMessage);

        int numbersCount = inputInteger("Введите количество чисел:");

        int[] numbers = new int[numbersCount];
        for (int i = 0; i < numbers.length; i++) {
            String numberInput = String.format("%d - е число:", i);
            numbers[i] = inputInteger(numberInput);
        }

        return numbers;
    }

    private static int inputInteger(String inputMessage) {
        System.out.println(inputMessage);

        AtomicReference<Integer> valueReference = new AtomicReference<Integer>();
        while (!tryParseInteger(scanner.nextLine(), valueReference) || valueReference.get() <= 0) {
            System.out.println("Введите целое положительное число.");
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

    private static void showResult(int gcdEuclidean, int gcdStein, Duration euclideanDuration, Duration steinDuration) {
        String euclideanOutput = String.format("НОД по алгоритму Евклида: %d, время работы алгоритма: %d мс.", gcdEuclidean, euclideanDuration.toMillis());
        System.out.println(euclideanOutput);

        String steinOutput = String.format("НОД по алгоритму Стейна: %d, времяработы алгоритма: %d мс.", gcdStein, steinDuration.toMillis());
        System.out.println(steinOutput);

        String equalityMessage = (gcdEuclidean == gcdStein) ? "Результаты совпадают." : "Результаты не совпадают.";
        System.out.println(equalityMessage);
    }
}
