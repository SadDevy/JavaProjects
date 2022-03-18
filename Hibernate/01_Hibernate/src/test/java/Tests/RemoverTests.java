package Tests;

import Entities.StudentTest;
import Utilities.Remover;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RemoverTests {
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
    public void testRemoveTest() {
        final int id = 1;

        final String name = "Test";
        final String theory = "Theory";
        final String theoryUrl = "TheoryUrl";
        final boolean theoryIsShown = true;
        final int questionsCount = 10;
        final int rightCount = 10;

        final StudentTest test = new StudentTest(id, name, theory, theoryUrl, theoryIsShown, questionsCount, rightCount);
        try (Session session = factory.openSession()) {
            session.save(test);
        }

        Remover remover = new Remover(factory);
        remover.removeTestFromDb(id);

        StudentTest actual;
        try (Session session = factory.openSession()) {
            actual = session.get(StudentTest.class, id);
        }

        Assert.assertNull(actual);
    }
}
