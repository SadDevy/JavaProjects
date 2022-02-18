package StreamProvider;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Provider {
    public static void getWorkingDays(LocalDate start) {
        Stream.iterate(start, n -> n.plusDays(1)).filter(n -> n.getDayOfWeek() != DayOfWeek.SUNDAY && n.getDayOfWeek() != DayOfWeek.SATURDAY).forEach(System.out::println);
    }

    public static String getCommonestWord(String line) {
        checkNull(line, "line");

        String lineSeparator = " |,|[.]|[(]|[)]|!|[?]";
        String[] words = line.split(lineSeparator);

        Map<String, Long> wordCountPairs = Stream.of(words).collect(Collectors.groupingBy(n -> n, Collectors.counting()));

        Collection<Long> values = wordCountPairs.values();
        Long maxValue = values.stream().max(Long::compareTo).get();

        return wordCountPairs.keySet()
                .stream()
                .filter(n -> wordCountPairs.get(n) == maxValue).findFirst()
                .get();
    }

    private static <AItem> void checkNull(AItem a, String aName) {
        if (a == null)
            throw new NullPointerException(aName);
    }

    private static <AItem, BItem> void checkNull(AItem a, String aName, BItem b, String bName) {
        checkNull(a, "a");
        checkNull(b, "b");
    }

    private static <AItem, BItem, CItem> void checkNull(AItem a, String aName, BItem b, String bName, CItem c, String cName) {
        checkNull(a, "a", b, "b");
        checkNull(c, "c");
    }

    public static double summarize(double[] values) {
        checkNull(values, "values");

        if (values.length == 0)
            return 0;

        return Arrays.stream(values).sum();
    }

    public static String join(String separator, String[] values) {
        checkNull(separator, "separator", values, "values");

        if (values.length == 0)
            return null;

        return Arrays.stream(values).collect(Collectors.joining(separator));
    }

    public static String reverse(String line) {
        checkNull(line, "line");

        final String separator = " ";

        String[] lineParts = line.split(separator);
        return IntStream.range(0, lineParts.length)
                .mapToObj(n -> lineParts[lineParts.length - n - 1])
                .collect(Collectors.joining(separator));
    }

    public static boolean pinIsValid(String line) {
        checkNull(line, "line");

        final int elementsCount = 4;
        if (line.length() != elementsCount)
            return false;

        AtomicInteger resultReference = new AtomicInteger();
        if (!tryParseInteger(line, resultReference))
            return false;

        final String patternLine = "(.)\\1{2}";
        if (Pattern.matches(patternLine, line))
            return false;

        long inRowCount = line
                .chars()
                .filter(n -> Math.abs(n - line.charAt(0)) == line.indexOf((char) n))
                .count();
        return !(inRowCount == elementsCount);
    }

    private static boolean tryParseInteger(String line, AtomicInteger valueReference) {
        try {
            int value = Integer.parseInt(line);
            valueReference.set(value);

            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }

    public static Consumer<Double> createCounter() {

        return new Consumer<Double>() {
            double count = 0;

            @Override
            public void accept(Double aDouble) {
                count += aDouble;
                System.out.println(String.format("Count = %f", count));
            }
        };
    }
}

