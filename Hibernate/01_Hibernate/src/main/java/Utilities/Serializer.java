package Utilities;

import Entities.StudentTest;

import java.io.*;

public class Serializer {
    public static final void serialize(StudentTest test, String filePath)
            throws IOException, IllegalArgumentException {
        if (filePath == "" || filePath == null)
            throw new IllegalArgumentException("filePath");

        if (test == null)
            throw new IllegalArgumentException("test");

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            stream.writeObject(test);
        }
    }

    public static final StudentTest deserialize(String filePath)
        throws IOException, ClassNotFoundException, IllegalArgumentException {
        if (filePath == "" || filePath == null)
            throw new IllegalArgumentException("filePath");

        File file = new File(filePath);
        if (!file.exists())
            throw new IllegalArgumentException("filePath");

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (StudentTest) stream.readObject();
        }
    }
}
