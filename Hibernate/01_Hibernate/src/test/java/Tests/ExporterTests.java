package Tests;

import DAO.StudentTestDAO;
import Entities.StudentTest;
import Utilities.Exporter;
import Utilities.Serializer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.sql.*;

public class ExporterTests {
    private SessionFactory factory;

    @Before
    public void setUp() {
        factory = new Configuration().configure("testInMemory.cfg.xml")
                .buildSessionFactory();
    }

    @BeforeTest
    public void removeAllData()
            throws ClassNotFoundException, SQLException {
        final String removeAnswersSqlQuery = """
                DELETE FROM "AnswerVariants"
                WHERE "Id" > 0""";
        final String removeQuestionsSqlQuery = """
                DELETE FROM "Questions"
                WHERE "Id" > 0""";
        final String removeTestsSqlQuery = """
                DELETE FROM "Tests"
                WHERE "Id" > 0""";

        Class.forName("org.h2.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:StudentTestsImMemory", "user", "password")) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(removeAnswersSqlQuery);
                statement.executeUpdate(removeQuestionsSqlQuery);
                statement.executeUpdate(removeTestsSqlQuery);
            }
        }
    }

    @Test
    public void testExportTest_HasTest_Success()
            throws IOException, ClassNotFoundException {
        final String filePath = "test.out";
        final int testId = 1;

        final String name = "Test";
        final String theory = "Theory";
        final String theoryUrl = "TheoryUrl";
        final boolean theoryIsShown = true;
        final int questionsCount = 10;
        final int rightCount = 10;

        final StudentTest expected = new StudentTest(name, theory, theoryUrl, theoryIsShown, questionsCount, rightCount);

        final StudentTestDAO testDao = new StudentTestDAO(factory);
        testDao.add(expected);

        final Exporter exporter = new Exporter(factory);
        exporter.exportTestToFile(testId, filePath);

        final StudentTest actual = Serializer.deserialize(filePath);

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void testExportTest_HasNotTest_Exception()
            throws IllegalStateException, IOException, ClassNotFoundException {
        final String filePath = "test.out";
        final int testId = 1;

        final Exporter exporter = new Exporter(factory);
        exporter.exportTestToFile(testId, filePath);
    }
}
