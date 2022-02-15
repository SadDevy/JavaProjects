package File;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.charset.Charset;

public class FileArray implements AutoCloseable {
    private RandomAccessFile file;
    private InputStreamReader fileReader;
    private OutputStreamWriter fileWriter;

    private boolean _isClosed = false;

    private final int symbolsCount = 1;
    private final int symbolIndex = 0;

    public char getSymbol(int index)
            throws IOException {
        file.seek(index);

        char[] buffer = new char[symbolsCount];
        if (fileReader.read(buffer, 0, buffer.length) == 0) {
            throw new IllegalStateException("Символ не считан.");
        }

        return buffer[symbolIndex];
    }

    public void setSymbol(int index, char value)
            throws IOException {
        file.seek(index);

        fileWriter.write(value);
        fileWriter.flush();
    }

    public long getLength()
            throws IOException {
        return file.length();
    }

    private FileArray(String filePath, int length)
            throws IOException {
        this(filePath);

        file.setLength(length);
    }

    private FileArray(String filePath)
            throws IOException {
        final String fileMode = "rw";
        openStream(filePath, fileMode);
    }

    private void openStream(String filePath, @NotNull String fileMode)
            throws IOException {
        final String encoding = "windows-1251";
        final Charset charset = Charset.forName(encoding);

        file = new RandomAccessFile(filePath, fileMode);

        InputStream inputStream = Channels.newInputStream(file.getChannel());
        fileReader = new InputStreamReader(inputStream, charset);

        OutputStream outputStream = Channels.newOutputStream(file.getChannel());
        fileWriter = new OutputStreamWriter(outputStream, charset);
    }

    public static FileArray create(String filePath, int length)
            throws IllegalStateException, IOException {
        if (length < 0)
            throw new IllegalStateException("Размер файла не может быть отрицательным.");

        return new FileArray(filePath, length);
    }

    public static FileArray read(String filePath)
            throws IllegalStateException, IOException {
        File file = new File(filePath);
        if (!file.exists())
            throw new IllegalStateException(String.format("Файл %s не существует.", filePath));

        return new FileArray(filePath);
    }

    @Override
    public void close()
            throws Exception {
        close(true);
    }

    protected void close(boolean closing)
            throws IOException {
        if (_isClosed)
            return;

        if (closing) {
            fileWriter.close();
            fileReader.close();
            file.close();
        }

        _isClosed = true;
    }
}
