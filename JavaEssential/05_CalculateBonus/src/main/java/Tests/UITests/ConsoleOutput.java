package Tests.UITests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleOutput implements AutoCloseable {
    private ByteArrayOutputStream outputStream;

    public ConsoleOutput() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    public String getOutput() {
        return outputStream.toString();
    }

    @Override
    public void close() {
        try {
            System.setOut(null);
            outputStream.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
