package Utilities;

import Entities.AnswerVariant;
import Entities.Question;
import Entities.Test;
import Utilities.StatementProviders.AnswerVariantsStatementsProvider;
import Utilities.StatementProviders.QuestionStatementsProvider;
import Utilities.StatementProviders.TestStatementsProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Importer {
    private String url;
    private String userName;
    private String password;

    private Importer(ConnectionStringPartsProvider connectionStringParts)
            throws IllegalStateException {
        url = connectionStringParts.getUrl();
        userName = connectionStringParts.getUserName();
        password = connectionStringParts.getPassword();
    }

    public static Importer createImporter()
            throws IllegalStateException {
        ConnectionStringPartsProvider connectionStringParts = DbProvider.getConnectionStringParts();

        return new Importer(connectionStringParts);
    }

    public void importFromFile(String filePath)
            throws IOException, ClassNotFoundException, SQLException {
        Test test = getTestFromFile(filePath);
        importTest(test);
    }

    private void importTest(Test test)
            throws SQLException {
        try (Connection connection = DbProvider.createDbConnection(url, userName, password)) {
            int testId = importTest(connection, test);
            importQuestions(connection, test.getQuestions(), testId);
        }
    }

    private Test getTestFromFile(String filePath)
            throws IOException, ClassNotFoundException {
        return Serializer.deserialize(filePath);
    }

    private int importTest(Connection connection, Test test)
            throws SQLException {
        boolean testExists = TestStatementsProvider.testExists(connection, test.getId());
        if (testExists) {
            String errorMessage = String.format("Тест с id = %d находится в БД.", test.getId());
            throw new IllegalStateException(errorMessage);
        }

        return TestStatementsProvider.importToDb(connection, test);
    }

    private void importQuestions(Connection connection, List<Question> questions, int testId)
            throws SQLException {
        for (Question question : questions) {
            int questionId = QuestionStatementsProvider.importToDb(connection, question, testId);
            importAnswerVariants(connection, question.getAnswerVariants(), questionId);
        }
    }

    private void importAnswerVariants(Connection connection, List<AnswerVariant> answerVariants, int questionId)
            throws SQLException {
        for (AnswerVariant answerVariant : answerVariants)
            AnswerVariantsStatementsProvider.importToDb(connection, answerVariant, questionId);
    }
}
