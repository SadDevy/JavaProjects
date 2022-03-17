package Utilities;

import DAO.TestDAO;
import Entities.Question;
import Entities.Test;

import java.io.IOException;
import java.util.Arrays;

public class Importer {
    private final TestDAO testContext = new TestDAO();

    public void importTestFromFile(String filePath)
            throws IOException, ClassNotFoundException, IllegalArgumentException {
        Test test = Serializer.Deserialize(filePath);

        importTest(test);
    }

    private void importTest(Test test) {
        testContext.add(test);
    }
}