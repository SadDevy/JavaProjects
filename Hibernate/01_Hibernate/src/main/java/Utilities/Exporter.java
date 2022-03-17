package Utilities;

import DAO.TestDAO;
import Entities.Test;

import java.io.IOException;

public class Exporter {
    private final TestDAO testContext = new TestDAO();

    public void exportTestToFile(int id, String filePath)
            throws IOException, IllegalArgumentException {
        Test test = exportTest(id);

        Serializer.Serialize(test, filePath);
    }

    private Test exportTest(int id)
            throws IllegalStateException {
        return testContext.get(id);
    }
}
