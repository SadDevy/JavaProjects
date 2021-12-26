package UI;

import PointProcessor.Processor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0)
            processFiles(args);
        else
            Processor.processConsole();
    }

    private static void processFiles(String[] fileNames) {
        try {
            Processor.processFiles(fileNames);
        }
        catch (IOException ex) {
            System.out.println("Error of handling file");
        }
    }
}
