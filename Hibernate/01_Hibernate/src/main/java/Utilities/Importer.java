package Utilities;

import DAO.StudentTestDAO;
import Entities.StudentTest;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class Importer {
    private StudentTestDAO testContext;

    public Importer(SessionFactory factory) {
        testContext = new StudentTestDAO(factory);
    }

    public void importTestFromFile(String filePath)
            throws IOException, ClassNotFoundException, IllegalArgumentException {
        StudentTest test = Serializer.deserialize(filePath);

        importTest(test);
    }

    private void importTest(StudentTest test) {
        testContext.add(test);
    }
}