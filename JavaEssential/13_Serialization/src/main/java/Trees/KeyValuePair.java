package Trees;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Objects;

public class KeyValuePair<TKey extends Comparable<TKey>, TValue>
        implements Comparable<KeyValuePair<TKey, TValue>>, Serializable {
    private TKey key;

    public TKey getKey() {
        return key;
    }

    public void setKey(TKey value) {
        key = value;
    }

    private TValue value;

    public TValue getValue() {
        return value;
    }

    public void setValue(TValue value) {
        this.value = value;
    }

    public KeyValuePair() {
        this(null, null);
    }

    public KeyValuePair(TKey key) {
        this(key, null);
    }

    public KeyValuePair(TKey key, TValue value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[{0}, {1}]", key, value);
    }

    @Override
    public int compareTo(KeyValuePair<TKey, TValue> o) {
        return key.compareTo(o.getKey());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !isType(o))
            return false;

        return this.compareTo((KeyValuePair<TKey, TValue>) o) == 0;
    }

    private boolean isType(Object o) {
        try {
            KeyValuePair<TKey, TValue> __ = (KeyValuePair<TKey, TValue>) o;

            return true;
        } catch (ClassCastException ex) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }
}
