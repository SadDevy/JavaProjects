package Utilities;

import Entities.Test;

import java.io.*;

public class Serializer {
    public static void serialize(Test test, String filePath)
            throws IOException {
        if (filePath == "" || filePath == null)
            throw new IllegalArgumentException("filePath");

        if (test == null)
            throw new IllegalArgumentException("test");

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(test);
        }
    }

    public static Test deserialize(String filePath)
            throws IOException, ClassNotFoundException {
        if (filePath == "" || filePath == null)
            throw  new IllegalArgumentException("filePath");

        File file = new File(filePath);
        if (!file.exists())
            throw new IllegalArgumentException("filePath");

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Test) inputStream.readObject();
        }
    }
}
