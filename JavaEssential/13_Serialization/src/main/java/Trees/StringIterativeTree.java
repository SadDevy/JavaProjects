package Trees;

import java.io.Serializable;
import java.util.Comparator;

public class StringIterativeTree
        extends IterativeTree<String>
        implements Serializable {
    public StringIterativeTree(Comparator<String> comparator) {
        super(comparator);
    }

    public StringIterativeTree(Iterable<String> collection) {
        super(collection);
    }

    public StringIterativeTree(Iterable<String> collection, Comparator<String> comparator) {
        super(collection, comparator);
    }
}
