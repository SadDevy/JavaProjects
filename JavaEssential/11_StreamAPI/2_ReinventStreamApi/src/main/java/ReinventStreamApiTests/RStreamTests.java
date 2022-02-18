package ReinventStreamApiTests;

import ReinventStreamApi.RStream;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

@RunWith(DataProviderRunner.class)
public class RStreamTests {
    @Test
    @UseDataProvider("getTestRepeatTestCases")
    public <TResult> void testRepeat(TResult element, int count, Stream<TResult> expected) {
        Stream<TResult> actual = RStream.repeat(element, count);

        Assert.assertEquals(expected.toList(), actual.toList());
    }

    @DataProvider
    public static Object[][] getTestRepeatTestCases() {
        return new Object[][]{
                {'X', 0, Stream.of()},
                {1, 1, Stream.of(1)},
                {2, 2, Stream.of(2, 2)},
                {3, 3, Stream.of(3, 3, 3)},
                {"Abc", 5, Stream.of("Abc", "Abc", "Abc", "Abc", "Abc")},
        };
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testRepeat_NegativeCount_Exception() {
        exceptionRule.expect(IllegalArgumentException.class);

        Stream<Character> __ = RStream.repeat('X', -1);
    }

    @Test
    @UseDataProvider("getTestConcatTestCases")
    public <TSource> void testConcat(Iterable<TSource> first,
                                     Iterable<TSource> second,
                                     Stream<TSource> expected) {
        Stream<TSource> actual = RStream.concat(first, second);

        Assert.assertEquals(expected.toList(), actual.toList());
    }

    @DataProvider
    public static Object[][] getTestConcatTestCases() {
        return new Object[][]{
                {Arrays.asList(), Arrays.asList(), Stream.of()},
                {Arrays.asList(1), Arrays.asList(), Stream.of(1)},
                {Arrays.asList(1, 2, 3), Arrays.asList(), Stream.of(1, 2, 3)},
                {Arrays.asList(), Arrays.asList(10), Stream.of(10)},
                {Arrays.asList(), Arrays.asList(10, 20, 30), Stream.of(10, 20, 30)},
                {Arrays.asList(1), Arrays.asList(10), Stream.of(1, 10)},
                {Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(10, 20, 30), Stream.of(1, 2, 3, 4, 5, 10, 20, 30)},
                {Arrays.asList('A', 'B'), Arrays.asList('x', 'y', 'z'), Stream.of('A', 'B', 'x', 'y', 'z')}
        };
    }

    @Test
    public void testConcat_FirstNull_Exception() {
        exceptionRule.expect(NullPointerException.class);

        Stream<Integer> __ = RStream.concat(null, Arrays.asList());
    }

    @Test
    public void testConcat_SecondNull_Exception() {
        exceptionRule.expect(NullPointerException.class);

        Stream<Integer> __ = RStream.concat(Arrays.asList(), null);
    }

    @Test
    @UseDataProvider("getTestJoinTestCases")
    public void testJoin(
            StudentTestResult[] results,
            AbstractMap.SimpleEntry<String, String>[] teachers,
            Function<StudentTestResult, String> studentTestResultStringSelector,
            Function<AbstractMap.SimpleEntry<String, String>, String> teacherTestSubjectSelector,
            BiFunction<StudentTestResult, AbstractMap.SimpleEntry<String, String>, AbstractMap.SimpleEntry<String, Date>> teacherTestDatesSelector,
            Stream<AbstractMap.SimpleEntry<String, Date>> expected
    ) {
        Stream<AbstractMap.SimpleEntry<String, Date>> actual =
                RStream.join(
                        Arrays.asList(results),
                        Arrays.asList(teachers),
                        studentTestResultStringSelector,
                        teacherTestSubjectSelector,
                        teacherTestDatesSelector
                );

        Assert.assertEquals(expected.toList(), actual.toList());
    }

    @DataProvider
    public static Object[][] getTestJoinTestCases() {
        StudentTestResult[] results = new StudentTestResult[]{
                new StudentTestResult("James", "Smith", "SQL-100", 4, new Date(2020, 04, 22)),
                new StudentTestResult("Anthony", "Brown", "SQL-200", 3, new Date(2017, 12, 01)),
                new StudentTestResult("John", "Lee", "SQL-200", 2, new Date(2017, 12, 02)),
                new StudentTestResult("Robert", "Johnson", ".NET-100", 5, new Date(2020, 01, 11)),
                new StudentTestResult("Michael", "King", "HTML-150", 4, new Date(2019, 07, 21))
        };

        AbstractMap.SimpleEntry<String, String>[] teachers = new AbstractMap.SimpleEntry[]{
                new AbstractMap.SimpleEntry("Victor", "SQL-200"),
                new AbstractMap.SimpleEntry("Oleg", ".NET-100"),
                new AbstractMap.SimpleEntry("Sergey", "HTML-100")
        };

        AbstractMap.SimpleEntry<String, Date>[] expectedResults = new AbstractMap.SimpleEntry[]{
                new AbstractMap.SimpleEntry("Victor", new Date(2017, 12, 01)),
                new AbstractMap.SimpleEntry("Victor", new Date(2017, 12, 02)),
                new AbstractMap.SimpleEntry("Oleg", new Date(2020, 01, 11))
        };

        Function<StudentTestResult, String> studentTestResultStringSelector =
                studentTestResult -> studentTestResult.getTestSubject();

        Function<AbstractMap.SimpleEntry<String, String>, String> teacherTestSubjectSelector =
                stringStringSimpleEntry -> stringStringSimpleEntry.getValue();

        BiFunction<StudentTestResult, AbstractMap.SimpleEntry<String, String>, AbstractMap.SimpleEntry<String, Date>> teacherTestDatesSelector =
                (studentTestResult, stringStringSimpleEntry) -> new AbstractMap.SimpleEntry<>(stringStringSimpleEntry.getKey(), studentTestResult.date);

        return new Object[][]{
                {results, new AbstractMap.SimpleEntry[0], studentTestResultStringSelector, teacherTestSubjectSelector, teacherTestDatesSelector, Stream.of(new AbstractMap.SimpleEntry[0])},
                {new StudentTestResult[0], teachers, studentTestResultStringSelector, teacherTestSubjectSelector, teacherTestDatesSelector, Stream.of(new AbstractMap.SimpleEntry[0])},
                {results, teachers, studentTestResultStringSelector, teacherTestSubjectSelector, teacherTestDatesSelector, Stream.of(expectedResults)},
        };
    }

    @Test
    public void testPrepend() {
        Stream<StudentTestResult> expected = Stream.of(
                new StudentTestResult("Finley", "Brooks", "SQL-200", 3, new Date(2018, 01, 21)),
                new StudentTestResult("James", "Smith", "SQL-100", 4, new Date(2020, 04, 22)),
                new StudentTestResult("John", "Lee", "SQL-200", 2, new Date(2017, 12, 01)),
                new StudentTestResult("Robert", "Johnson", ".NET-100", 5, new Date(2020, 01, 11)),
                new StudentTestResult("Michael", "King", "HTML-100", 4, new Date(2019, 07, 21))
        );

        List<StudentTestResult> results = Arrays.asList(
                new StudentTestResult("James", "Smith", "SQL-100", 4, new Date(2020, 04, 22)),
                new StudentTestResult("John", "Lee", "SQL-200", 2, new Date(2017, 12, 01)),
                new StudentTestResult("Robert", "Johnson", ".NET-100", 5, new Date(2020, 01, 11)),
                new StudentTestResult("Michael", "King", "HTML-100", 4, new Date(2019, 07, 21))
        );

        Stream<StudentTestResult> actual = RStream.prepend(results, new StudentTestResult("Finley", "Brooks", "SQL-200", 3, new Date(2018, 01, 21)));

        Assert.assertEquals(expected.toList(), actual.toList());
    }

    @Test
    public void testTake() {
        final Stream<StudentTestResult> expected = Stream.of(
                new StudentTestResult("Finley", "Brooks", "SQL-200", 3, new Date(2018, 01, 21)),
                new StudentTestResult("James", "Smith", "SQL-100", 4, new Date(2020, 04, 22)),
                new StudentTestResult("John", "Lee", "SQL-200", 2, new Date(2017, 12, 01))
        );

        final List<StudentTestResult> results = Arrays.asList(
                new StudentTestResult("Finley", "Brooks", "SQL-200", 3, new Date(2018, 01, 21)),
                new StudentTestResult("James", "Smith", "SQL-100", 4, new Date(2020, 04, 22)),
                new StudentTestResult("John", "Lee", "SQL-200", 2, new Date(2017, 12, 01)),
                new StudentTestResult("Robert", "Johnson", ".NET-100", 5, new Date(2020, 01, 11)),
                new StudentTestResult("Michael", "King", "HTML-100", 4, new Date(2019, 07, 21))
        );

        final int count = 3;

        Stream<StudentTestResult> actual = RStream.take(results, count);

        Assert.assertEquals(expected.toList(), actual.toList());
    }

    @Test
    public void testSkipWhile() {
        final Integer[] numbers = { 10, 20, 30, 1, 2, 3 };
        final List<Integer> expected = Arrays.asList(1, 2, 3);

        Stream<Integer> actual = RStream.skipWhile(Arrays.asList(numbers), (n, index) -> n > 9);

        Assert.assertEquals(expected, actual.toList());
    }
    

}
