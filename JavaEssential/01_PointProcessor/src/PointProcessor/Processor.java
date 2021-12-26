package PointProcessor;

import java.io.*;
import java.util.Scanner;

public class Processor {
    public static void processFiles(String[] fileNames) throws IOException {
        for (String fileName : fileNames) {
            processFile(fileName);
        }
    }

    private static void processFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists() || file.isDirectory())
            return;

        try (InputStream reader = new FileInputStream(fileName)) {
            processAllLines(reader);
        }
    }

    public static void processConsole() {
        processAllLines(System.in);
    }

    private static void processAllLines(InputStream reader) {
        try (Scanner scanner = new Scanner(reader)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String processedLine = processLine(line);
                if (processedLine != null)
                    System.out.println(processedLine);
            }
        }
    }

    private static String processLine(String line) {
        ValueWrapper<Point> pointWrapper = new ValueWrapper<Point>();
        if (Parser.tryParsePoint(line, pointWrapper)) {
            Point point = pointWrapper.getValue();
            return Formatter.format(point);
        }

        return null;
    }
}
