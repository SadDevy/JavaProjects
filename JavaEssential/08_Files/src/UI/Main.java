package UI;

import File.FileArray;

import java.io.IOException;

public class Main {
    private static final String filePath = "..\\..\\..\\Something.txt";
    private static final String greeting = "[01] Привет мир!";
    private static final int symbolIndex = 2;
    private static final char changedSymbol = '2';

    public static void main(String[] args)
            throws IllegalStateException, IOException, Exception {
        write(filePath, greeting);
        show(filePath);
        changeSymbol(filePath, symbolIndex, changedSymbol);
        show(filePath);
    }

    private static void write(String filePath, String value)
            throws Exception {
        FileArray symbols = null;
        try {
            symbols = FileArray.create(filePath, value.length());
            for (int i = 0; i < value.length(); i++) {
                symbols.setSymbol(i, value.charAt(i));
            }
        } finally {
            symbols.close();
        }
    }

    private static void show(String filePath)
            throws IllegalStateException, IOException, Exception {
        try (FileArray symbols = FileArray.read(filePath)) {
            for (int i = 0; i < symbols.getLength(); i++) {
                System.out.print(symbols.getSymbol(i));
            }
        }
    }

    private static void changeSymbol(String filePath, int index, char symbol)
            throws IllegalStateException, IOException, Exception {
        try (FileArray symbols = FileArray.read(filePath)) {
            symbols.setSymbol(index, symbol);
        }
    }
}
