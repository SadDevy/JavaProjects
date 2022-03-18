package Utilities;

import DAO.StudentTestDAO;
import Entities.StudentTest;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class Exporter {
    private StudentTestDAO testContext;

    public Exporter(SessionFactory factory) {
        testContext = new StudentTestDAO(factory);
    }

    public void exportTestToFile(int id, String filePath)
            throws IOException, IllegalArgumentException {
        StudentTest test = exportTest(id);

        Serializer.serialize(test, filePath);
    }

    private StudentTest exportTest(int id)
            throws IllegalStateException {
        return testContext.get(id);
    }
}
