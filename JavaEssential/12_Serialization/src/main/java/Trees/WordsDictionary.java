package Trees;

import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;

public class WordsDictionary
extends KeyValuePairTree<String, KeyValuePairTree<String, String[]>>{
    public WordsDictionary(Comparator<KeyValuePair<String, KeyValuePairTree<String, String[]>>> comparator) {
        super(comparator);
    }

    public WordsDictionary(Collection<KeyValuePair<String, KeyValuePairTree<String, String[]>>> collection) {
        super(collection);
    }

    public WordsDictionary(Collection<KeyValuePair<String, KeyValuePairTree<String, String[]>>> collection, Comparator<KeyValuePair<String, KeyValuePairTree<String, String[]>>> comparator) {
        super(collection, comparator);
    }

    public boolean tryGetWords(String key, String language, AtomicReference<String[]> wordsReference) {
        wordsReference.set(null);

        if (key == null || language == null)
            return false;

        AtomicReference<KeyValuePairTree<String, String[]>> valuesReference = new AtomicReference<>();
        if (!tryGetValue(key, valuesReference))
            return false;

        if (!valuesReference.get().tryGetValue(language, wordsReference))
            return false;

        return true;
    }
}
