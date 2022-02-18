package MatrixException;

import java.io.*;

public class InvalidMatrixOperationException
        extends Exception
        implements Serializable {
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

    public InvalidMatrixOperationException(MatrixSize a, MatrixSize b) {
        super();

        Initialize(a, b);
    }

    public InvalidMatrixOperationException(MatrixSize a, MatrixSize b, String message) {
        super(message);

        Initialize(a, b);
    }

    public InvalidMatrixOperationException(MatrixSize a, MatrixSize b, String message, Throwable cause) {
        super(message, cause);

        Initialize(a, b);
    }

    public InvalidMatrixOperationException(MatrixSize a, MatrixSize b, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

        Initialize(a, b);
    }

    public InvalidMatrixOperationException(MatrixSize a, MatrixSize b, Throwable cause) {
        super(cause);

        Initialize(a, b);
    }

    private void Initialize(MatrixSize a, MatrixSize b) {
        this.aRowCount = a.getRowCount();
        this.aColumnCount = a.getColumnCount();
        this.bRowCount = b.getRowCount();
        this.bColumnCount = b.getColumnCount();
    }
}
