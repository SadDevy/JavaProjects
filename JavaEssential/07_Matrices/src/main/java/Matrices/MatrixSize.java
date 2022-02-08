package Matrices;

public class MatrixSize {
    private int rowCount;
    public int getRowCount() {
        return rowCount;
    }

    private int columnCount;
    public int getColumnCount() {
        return columnCount;
    }

    public MatrixSize(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }
}
