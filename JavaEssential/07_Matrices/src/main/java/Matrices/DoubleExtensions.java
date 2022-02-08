package Matrices;

public class DoubleExtensions {
    private static final int rowIndex = 0;

    public static int getRowCount(double[][] array) {
        return array.length;
    }

    public static int getColumnCount(double[][] array) {
        int count;
        try {
            count = array[rowIndex].length;
        } catch (IndexOutOfBoundsException ex) {
            count = 0;
        }

        return count;
    }
}
