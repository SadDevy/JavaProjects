package Utilities;

import Entities.Test;

import java.io.*;

public class Serializer {
    public static final void Serialize(Test test, String filePath)
            throws IOException, IllegalArgumentException {
        if (filePath == "" || filePath == null)
            throw new IllegalArgumentException("filePath");

        if (test == null)
            throw new IllegalArgumentException("test");

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            stream.writeObject(test);
        }
    }

    public static final Test Deserialize(String filePath)
        throws IOException, ClassNotFoundException, IllegalArgumentException {
        if (filePath == "" || filePath == null)
            throw new IllegalArgumentException("filePath");

        File file = new File(filePath);
        if (!file.exists())
            throw new IllegalArgumentException("filePath");

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Test) stream.readObject();
        }
    }
}
