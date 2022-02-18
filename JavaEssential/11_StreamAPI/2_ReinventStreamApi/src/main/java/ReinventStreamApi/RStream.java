package ReinventStreamApi;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.*;

public class RStream {
    public static <TResult> Stream<TResult> repeat(TResult element, int count) {
        if (count < 0)
            throw new IllegalArgumentException("count");

        ArrayList<TResult> result = new ArrayList<>();
        for (int i = 0; i < count; i++)
            result.add(element);

        return result.stream();
    }

    public static <TSource> Stream<TSource> concat(Iterable<TSource> first, Iterable<TSource> second) {
        checkNull(first, "first");
        checkNull(second, "second");

        ArrayList<TSource> result = new ArrayList<>();
        for (TSource value : first)
            result.add(value);

        for (TSource value : second)
            result.add(value);

        //Stream.concat(StreamSupport.stream(first.spliterator(), false), StreamSupport.stream(second.spliterator(), false));
        return result.stream();
    }

    private static <TSource> void checkNull(TSource value, String name) {
        if (value == null)
            throw new NullPointerException(name);
    }

    public static <TSource> Stream<TSource> prepend(Iterable<TSource> source, TSource element) {
        checkNull(source, "source");

        ArrayList<TSource> result = new ArrayList<>(Arrays.asList(element));
        for (TSource item : source)
            result.add(item);

        //Stream.concat(Stream.of(element), StreamSupport.stream(source.spliterator(), false));
        return result.stream();
    }

    public static <TSource> Stream<TSource> take(Iterable<TSource> source, int count) {
        if (count < 0)
            throw new IllegalArgumentException("count");

        checkNull(source, "source");

        ArrayList<TSource> result = new ArrayList<>();
        for (TSource value : source) {
            result.add(value);

            if (--count == 0)
                break;
        }

        return result.stream();
    }

    public static <TSource> Stream<TSource> skipWhile(Iterable<TSource> source, BiFunction<TSource, Integer, Boolean> predicate) {
        checkNull(source, "source");
        checkNull(predicate, "predicate");

        ArrayList<TSource> result = new ArrayList<>();
        int index = -1;
        for (TSource value : source) {
            if (predicate.apply(value, ++index))
                continue;

            result.add(value);
        }

        return result.stream();
    }

    public static <TSource> TSource firstOrDefault(Iterable<TSource> source, Function<TSource, Boolean> predicate) {
        checkNull(source, "source");
        checkNull(predicate, "predicate");

        for (TSource value : source)
            if (predicate.apply(value))
                return value;

        return null;
    }

    public static <TResult> Stream<TResult> ofType(Iterable source) {
        checkNull(source, "source");

        ArrayList<TResult> result = new ArrayList<>();
        for (Object value : source)
            if (RStream.<TResult>isType(value))
                result.add((TResult) value);

        return result.stream();
    }

    private static <TResult> boolean isType(Object element) {
        try {
            TResult __ = (TResult) element; //!!!

            return true;
        } catch (ClassCastException ex) {
            return false;
        }
    }


    public static <TResult> Stream<TResult> cast(Iterable source) { //!!!
        checkNull(source, "source");

        if (!RStream.<Iterable<TResult>>isType(source))
            return StreamSupport.stream(((Iterable<TResult>) source).spliterator(), false);

        return castIterator(source);
    }

    private static <TResult> Stream<TResult> castIterator(Iterable source) {
        ArrayList<TResult> result = new ArrayList<>();
        for (Object element : source)
            result.add((TResult) element);

        return result.stream();
    }

    public static <TSource> List<TSource> toList(Iterable<TSource> source) {
        checkNull(source, "source");

        ArrayList<TSource> result = new ArrayList<>();
        source.forEach(n -> result.add(n));
        return result;
    }

    public static <TKey, TSource, TElement> Map<TKey, TElement> toDictionary(Iterable<TSource> source,
                                                                             Function<TSource, TKey> keySelector,
                                                                             Function<TSource, TElement> elementSelector,
                                                                             Comparator<TKey> comparator) {
        checkNull(source, "source");
        checkNull(keySelector, "keySelector");
        checkNull(elementSelector, "elementSelector");
        checkNull(comparator, "comparator");

        HashMap<TKey, TElement> result = new HashMap<>();
        for (TSource value : source)
            result.put(keySelector.apply(value), elementSelector.apply(value));

        return result;
    }

    public static <TSource> Stream<TSource> where(Iterable<TSource> source,
                                                  BiFunction<TSource, Integer, Boolean> predicate) {
        checkNull(source, "source");
        checkNull(predicate, "predicate");

        int index = -1;
        ArrayList<TSource> result = new ArrayList<>();
        for (TSource value : source)
            if (predicate.apply(value, ++index))
                result.add(value);

        return result.stream();
    }

    public static <TSource> boolean any(Iterable<TSource> source, Function<TSource, Boolean> predicate) {
        checkNull(source, "source");
        checkNull(predicate, "predicate");

        for (TSource value : source)
            if (predicate.apply(value))
                return true;

        return false;
    }

    public static <TSource> boolean all(Iterable<TSource> source, Function<TSource, Boolean> predicate) {
        checkNull(source, "source");
        checkNull(predicate, "predicate");

        for (TSource value : source)
            if (!predicate.apply(value))
                return false;

        return true;
    }

    public static <TSource> boolean contains(Iterable<TSource> source, TSource value) {
        checkNull(source, "source");

        for (TSource element : source)
            if (element == value)
                return true;

        return false;
    }

    public static <TSource> boolean contains(Iterable<TSource> source, TSource value, Comparator<TSource> comparator) {
        checkNull(source, "source");
        checkNull(comparator, "comparator");

        for (TSource element : source)
            if (comparator.compare(value, element) == 0)
                return true;

        return false;
    }

    public static <TSource, TResult> Stream<TResult> select(Iterable<TSource> source, Function<TSource, TResult> selector) {
        checkNull(source, "source");
        checkNull(selector, "selector");

        ArrayList<TResult> result = new ArrayList<>();
        for (TSource value : source)
            result.add(selector.apply(value));

        return result.stream();
    }

    public static <TSource, TResult> Stream<TResult> select(Iterable<TSource> source,
                                                            BiFunction<TSource, Integer, TResult> selector) {
        checkNull(source, "source");
        checkNull(selector, "selector");

        int index = -1;
        ArrayList<TResult> result = new ArrayList<>();
        for (TSource value : source)
            result.add(selector.apply(value, ++index));

        return result.stream();
    }

    public static <TSource, TResult> Stream<TResult> selectMany(Iterable<TSource> source,
                                                                Function<TSource, Iterable<TResult>> selector) {
        checkNull(source, "source");
        checkNull(selector, "selector");

        ArrayList<TResult> result = new ArrayList<>();
        for (TSource value : source)
            for (TResult element : selector.apply(value))
                result.add(element);

        return result.stream();
    }

    public static <TSource, TCollection, TResult> Stream<TResult> selectMany(Iterable<TSource> source,
                                                                             Function<TSource, Iterable<TCollection>> collectionSelector,
                                                                             BiFunction<TSource, TCollection, TResult> resultSelector) {
        checkNull(source, "source");
        checkNull(collectionSelector, "collectionSelector");
        checkNull(resultSelector, "resultSelector");

        ArrayList<TResult> result = new ArrayList<>();
        for (TSource value : source)
            for (TCollection collection : collectionSelector.apply(value))
                result.add(resultSelector.apply(value, collection));

        return result.stream();
    }

    public static <TSource> int count(Iterable<TSource> source) {
        checkNull(source, "source");

        int count = 0;
        for (TSource value : source)
            count++;

        return count;
    }

    public static <TSource> int count(Iterable<TSource> source, Function<TSource, Boolean> predicate) {
        checkNull(predicate, "predicate");

        int count = 0;
        for (TSource value : source)
            if (predicate.apply(value))
                count++;

        return count;
    }

    public static <TSource> TSource aggregate(Iterable<TSource> source, BiFunction<TSource, TSource, TSource> function) {
        checkNull(source, "source");
        checkNull(function, "function");

        TSource result;
        Iterator<TSource> iterator = source.iterator();
        if (!iterator.hasNext())
            throw new IllegalStateException("Пустая последовательность.");

        result = iterator.next();
        while (iterator.hasNext())
            result = function.apply(result, iterator.next());

        return result;
    }

    public static <TSource, TAccumulate, TResult> TResult aggregate(Iterable<TSource> source,
                                                                    TAccumulate seed,
                                                                    BiFunction<TAccumulate, TSource, TAccumulate> function,
                                                                    Function<TAccumulate, TResult> resultSelector) {
        checkNull(source, "source");
        checkNull(function, "function");
        checkNull(resultSelector, "resultSelector");

        TAccumulate result = seed;
        for (TSource value : source)
            result = function.apply(result, value);

        return resultSelector.apply(result);
    }

    public static <TSource> Stream<TSource> distinct(Iterable<TSource> source) {
        checkNull(source, "source");

        ArrayList<TSource> result = new ArrayList<>();
        for (TSource value : source)
            if (!result.contains(value))
                result.add(value);

        return result.stream();
    }

    public static <TSource> Stream<TSource> intersect(Iterable<TSource> first,
                                                      Iterable<TSource> second) {
        checkNull(first, "first");
        checkNull(second, "second");

        HashSet<TSource> hashSet = new HashSet<>();
        for (TSource value : first)
            hashSet.add(value);

        ArrayList<TSource> result = new ArrayList<>();
        for (TSource value : second)
            if (hashSet.remove(value))
                result.add(value);

        return result.stream();
    }

    public static <TSource> boolean sequenceEqual(Iterable<TSource> first, Iterable<TSource> second) {
        checkNull(first, "first");
        checkNull(second, "second");

        Comparator comparator = Comparator.naturalOrder();
        Iterator<TSource> a = first.iterator();
        Iterator<TSource> b = second.iterator();

        while (a.hasNext())
            if (!b.hasNext() || comparator.compare(a.next(), b.next()) != 0)
                return false;

        if (b.hasNext())
            return false;

        return true;
    }

    public static <TFirst, TSecond, TResult> Stream<TResult> zip(Iterable<TFirst> first,
                                                                 Iterable<TSecond> second,
                                                                 BiFunction<TFirst, TSecond, TResult> resultSelector) {
        checkNull(first, "first");
        checkNull(second, "second");

        Iterator<TFirst> a = first.iterator();
        Iterator<TSecond> b = second.iterator();

        ArrayList<TResult> result = new ArrayList<>();
        while (a.hasNext() && b.hasNext())
            result.add(resultSelector.apply(a.next(), b.next()));

        return result.stream();
    }

    public static <TSource, TKey, TElement, TResult> Stream<TResult> groupBy(Iterable<TSource> source,
                                                                             Function<TSource, TKey> keySelector,
                                                                             Function<TSource, TElement> elementSelector,
                                                                             BiFunction<TKey, Iterable<TElement>, TResult> resultSelector) {
        checkNull(source, "source");
        checkNull(keySelector, "keySelector");
        checkNull(elementSelector, "elementSelector");
        checkNull(resultSelector, "resultSelector");

        ArrayList<TResult> result = new ArrayList<>();
        groupBy(source, keySelector, elementSelector)
                .forEach((key, value) -> result.add(resultSelector.apply(key, value)));

        return result.stream();
    }

    private static <TSource, TKey, TElement> HashMap<TKey, List<TElement>> groupBy(Iterable<TSource> source,
                                                                                   Function<TSource, TKey> keySelector,
                                                                                   Function<TSource, TElement> elementSelector) {
        checkNull(source, "source");
        checkNull(keySelector, "keySelector");
        checkNull(elementSelector, "elementSelector");

        HashMap<TKey, List<TElement>> result = new HashMap<>();
        for (TSource value : source) {
            if (!result.containsKey(keySelector.apply(value)))
                result.put(keySelector.apply(value), new ArrayList<>());

            if (result.get(keySelector.apply(value)) != null) {
                List<TElement> list = result.get(keySelector.apply(value));
                list.add(elementSelector.apply(value));
            }
        }

        return result;
    }

    public static <TOuter, TInner, TKey, TResult> Stream<TResult> join(Iterable<TOuter> outer,
                                                                       Iterable<TInner> inner,
                                                                       Function<TOuter, TKey> outerKeySelector,
                                                                       Function<TInner, TKey> innerKeySelector,
                                                                       BiFunction<TOuter, TInner, TResult> resultSelector) {
        checkNull(outer, "outer");
        checkNull(inner, "inner");
        checkNull(outerKeySelector, "outerKeySelector");
        checkNull(innerKeySelector, "innerKeySelector");
        checkNull(resultSelector, "resultSelector");

        Comparator comparator = Comparator.naturalOrder();
        ArrayList<TResult> result = new ArrayList<>();
        for (TOuter outerValue : outer) {
            for (TInner innerValue : inner) {
                if (comparator.compare(outerKeySelector.apply(outerValue), innerKeySelector.apply(innerValue)) == 0)
                    result.add(resultSelector.apply(outerValue, innerValue));
            }
        }

        return result.stream();
    }
}
