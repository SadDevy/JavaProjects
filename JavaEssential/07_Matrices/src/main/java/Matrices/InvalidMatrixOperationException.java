package Matrices;

public class InvalidMatrixOperationException extends Exception {
    private String message;

    private int aRowCount;
    public int getARowCount() {
        return aRowCount;
    }

    private int aColumnCount;
    public int getAColumnCount() {
        return aColumnCount;
    }

    private int bRowCount;
    public int getBRowCount() {
        return bRowCount;
    }

    private int bColumnCount;
    public int getBColumnCount() {
        return bColumnCount;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public InvalidMatrixOperationException(MatrixSize a, MatrixSize b) {
        super();

        Initialize(a, b, null);
    }

    public InvalidMatrixOperationException(String message, MatrixSize a, MatrixSize b) {
        super(message);

        Initialize(a, b, message);
    }

    public InvalidMatrixOperationException(String message, Throwable innerException, MatrixSize a, MatrixSize b) {
        super(message, innerException);

        Initialize(a, b, message);
    }

    private void Initialize(MatrixSize a, MatrixSize b, String message) {
        aRowCount = a.getRowCount();
        aColumnCount = a.getColumnCount();
        bRowCount = b.getRowCount();
        bColumnCount = b.getColumnCount();

        this.message = String.format("%s %dx%d и %dx%d", message, aRowCount, aColumnCount, bRowCount, bColumnCount);
    }
}
