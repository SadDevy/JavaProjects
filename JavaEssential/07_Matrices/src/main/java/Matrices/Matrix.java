package Matrices;

import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicReference;

public class Matrix {
    private double[][] elements;

    public int getColumnCount() {
        return DoubleExtensions.getColumnCount(elements);
    }

    public int getRowCount() {
        return DoubleExtensions.getRowCount(elements);
    }

    private MatrixSize size;

    public MatrixSize getSize() {
        return new MatrixSize(getRowCount(), getColumnCount());
    }

    private double element;

    public double getElement(int row, int column)
            throws IllegalStateException {
        checkRow(row);
        checkColumn(column);

        return elements[row][column];
    }

    public void setElement(int row, int column, double value)
            throws IllegalStateException {
        checkRow(row);
        checkColumn(column);

        elements[row][column] = value;
    }

    private void checkRow(int row)
            throws IllegalStateException {
        if (row < 0 || row >= getRowCount())
            throw new IllegalStateException(String.format("Строка %d не существует.", row));
    }

    private void checkColumn(int column)
            throws IllegalStateException {
        if (column < 0 || column >= getColumnCount())
            throw new IllegalStateException(String.format("Столбец %d не существует.", column));
    }

    public Matrix(int rowCount, int columnCount)
            throws IllegalStateException {
        if (rowCount == 0 && columnCount == 0)
            throw new IllegalStateException(String.format("Матрица %dx%d не может существовать.", rowCount, columnCount));

        elements = new double[rowCount][columnCount];
    }

    public Matrix(double[][] values)
            throws NullPointerException, IllegalStateException {
        if (values == null)
            throw new NullPointerException("values");

        if (values.length == 0)
            throw new IndexOutOfBoundsException("values");

        int rowCount = DoubleExtensions.getRowCount(values);
        int columnCount = DoubleExtensions.getColumnCount(values);
        elements = new double[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                elements[i][j] = values[i][j];
            }
        }
    }

    @Override
    public boolean equals(Object o)
            throws IllegalStateException {
        if ((o == null) || !(isMatrix(o)))
            return false;

        Matrix a = (Matrix) o;
        return equals(a);
    }

    private boolean equals(Matrix a)
            throws IllegalStateException {
        if (a == null)
            return false;

        if (getRowCount() != a.getRowCount() || getColumnCount() != a.getColumnCount())
            return false;

        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                if (a.getElement(i, j) != elements[i][j])
                    return false;
            }
        }

        return true;
    }

    private boolean isMatrix(Object o) {
        try {
            Matrix __ = (Matrix) o;

            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                result ^= (result << 5) + (result >> 2) + (i * j) + Double.hashCode(elements[i][j]);
            }
        }

        return result;
    }

    public static Matrix add(Matrix a, Matrix b)
            throws NullPointerException, InvalidMatrixOperationException {
        checkNull(a, "a", b, "b");

        if (a.getRowCount() != b.getRowCount() || a.getColumnCount() != b.getColumnCount())
            throw new InvalidMatrixOperationException("Нельзя сложить матрицы: ", a.getSize(), b.getSize());

        Matrix result = new Matrix(a.getRowCount(), a.getColumnCount());
        for (int i = 0; i < a.getRowCount(); i++) {
            for (int j = 0; j < a.getColumnCount(); j++) {
                double value = a.getElement(i, j) + b.getElement(i, j);
                result.setElement(i, j, value);
            }
        }

        return result;
    }

    private static void checkNull(Matrix a, String aName)
            throws NullPointerException {
        if (a == null)
            throw new NullPointerException(aName);
    }

    private static void checkNull(Matrix a, String aName, Matrix b, String bName)
            throws NullPointerException {
        checkNull(a, aName);
        checkNull(b, bName);
    }

    public static Matrix subtract(Matrix a, Matrix b)
            throws InvalidMatrixOperationException {
        checkNull(a, "a", b, "b");

        if (a.getRowCount() != b.getRowCount() || a.getColumnCount() != b.getColumnCount())
            throw new InvalidMatrixOperationException("Нельзя вычесть матрицы: ", a.getSize(), b.getSize());

        return Matrix.add(a, multiply(b, -1));
    }

    public static Matrix multiply(Matrix a, double value) {
        checkNull(a, "a");

        Matrix result = new Matrix(a.getRowCount(), a.getColumnCount());
        for (int i = 0; i < a.getRowCount(); i++) {
            for (int j = 0; j < a.getColumnCount(); j++) {
                result.setElement(i, j, a.getElement(i, j) * value);
            }
        }

        return result;
    }

    public static Matrix multiply(Matrix a, Matrix b)
            throws InvalidMatrixOperationException {
        checkNull(a, "a", b, "b");

        if (a.getColumnCount() != b.getRowCount())
            throw new InvalidMatrixOperationException("Нельзя перемножить матрицы: ", a.getSize(), b.getSize());

        Matrix result = new Matrix(a.getRowCount(), b.getColumnCount());
        for (int i = 0; i < a.getRowCount(); i++) {
            for (int j = 0; j < b.getColumnCount(); j++) {
                for (int k = 0; k < b.getRowCount(); k++) {
                    double value = result.getElement(i, j) + (a.getElement(i, k) * b.getElement(k, j));
                    result.setElement(i, j, value);
                }
            }
        }

        return result;
    }

    public static boolean equals(Matrix a, Matrix b) {
        if (a == b)
            return true;

        if (a == null)
            return false;

        return a.equals(b);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        double integerPart;
        AtomicReference<Double> fractionalPartReference = new AtomicReference<>();
        for (int i = 0; i < getRowCount(); i++) {
            result.append("|");
            for (int j = 0; j < getColumnCount(); j++) {
                integerPart = getIntegerPart(elements[i][j], fractionalPartReference);
                result.append(MessageFormat.format("    {0, number,###0}{1, number,.0###}    ", integerPart, fractionalPartReference.get()));
            }

            result.append("|");
            result.append("\n");
        }

        return result.toString();
    }

    private double getIntegerPart(double value, AtomicReference<Double> fractionalPartReference) {
        double integerPart = Math.floor(value);
        double fractionalPart = value - integerPart;

        fractionalPartReference.set(fractionalPart);
        return integerPart;
    }
}
