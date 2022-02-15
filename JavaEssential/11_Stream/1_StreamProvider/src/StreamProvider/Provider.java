package StreamProvider;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
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
}

