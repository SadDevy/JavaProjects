package Trees;

import java.util.Comparator;

public class GeneralComparator<TItem extends Comparable<TItem>>
        implements Comparator<TItem> {
    private boolean isForward;
    public boolean isForward() {
        return isForward;
    }

    private Comparator<TItem> comparator;

    public GeneralComparator(boolean isDirect) {
        isForward = isDirect;

        comparator = Comparator.naturalOrder();
    }

    public GeneralComparator(Comparator<TItem> comparator, boolean isDirect) {
        this(isDirect);

        if (comparator != null)
            this.comparator = comparator;
    }

    @Override
    public int compare(TItem o1, TItem o2) {
        int result;
        if (o1 == null)
            result = (o2 == null) ? 0 : -1;
        else if (o2 == null)
            result = 1;
        else
            result = comparator.compare(o1, o2);

        result *= isForward ? 1 : -1;
        return result;
    }
}

