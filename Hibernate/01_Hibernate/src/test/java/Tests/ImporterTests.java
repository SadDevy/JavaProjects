package Tests;

import Entities.StudentTest;
import Utilities.Importer;
import Utilities.Serializer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ImporterTests {
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
    public void testImportTest()
            throws IOException, ClassNotFoundException {
        final int id = 1;
        final String filePath = "test.out";

        final String name = "Test";
        final String theory = "Theory";
        final String theoryUrl = "TheoryUrl";
        final boolean theoryIsShown = true;
        final int questionsCount = 10;
        final int rightCount = 10;

        final StudentTest expected = new StudentTest(id, name, theory, theoryUrl, theoryIsShown, questionsCount, rightCount);
        Serializer.serialize(expected, filePath);

        final Importer importer = new Importer(factory);
        importer.importTestFromFile(filePath);

        StudentTest actual;
        try (Session session = factory.openSession()) {
            actual = session.get(StudentTest.class, id);
        }

        Assert.assertEquals(expected, actual);
    }
}
