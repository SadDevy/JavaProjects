package UI;

import Utilities.Exporter;
import Utilities.Importer;
import Utilities.Remover;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int id = inputId("Введите id теста.", "Введите целое число.");
        String fileName = inputFileName("Введите название файла.");

        try {
            exportToFile(id, fileName);
            importFromFile(fileName);
            removeTest(id);
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private static void showError(Exception ex) {
        String message = "";
        if (ex.getClass() == SQLException.class)
            message = String.format("Ошибка работы с БД: %s", ex.getMessage());
        else if (ex.getClass() == IllegalArgumentException.class)
            message = String.format("Ошибка выполнения операции: %s", ex.getMessage());
        else
            message = ex.getMessage();

        System.out.println(message);
    }

    private static void exportToFile(int id, String fileName)
            throws SQLException, IOException {
        Exporter exporter = Exporter.createExporter();
        exporter.exportTestToFile(id, fileName);
    }

    private static void importFromFile(String fileName)
            throws SQLException, IOException, ClassNotFoundException {
        Importer importer = Importer.createImporter();
        importer.importFromFile(fileName);
    }

    private static void removeTest(int id)
            throws SQLException {
        Remover remover = Remover.createRemover();
        remover.removeTest(id);
    }

    private static String inputFileName(String inputMessage) {
        System.out.println(inputMessage);

        return scanner.nextLine();
    }

    private static int inputId(String inputMessage, String failureMessage) {
        System.out.println(inputMessage);

        AtomicInteger resultReference = new AtomicInteger();
        while (!tryParseInt(scanner.nextLine(), resultReference)) {
            System.out.println(failureMessage);
        }

        return resultReference.get();
    }

    private static boolean tryParseInt(String line, AtomicInteger resultReference) {
        try {
            int value = Integer.parseInt(line);
            resultReference.set(value);

            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
