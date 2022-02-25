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

public class Exporter {
    private String url;
    private String userName;
    private String password;

    private Exporter(ConnectionStringPartsProvider connectionStringParts)
            throws IllegalStateException {
        this.url = connectionStringParts.getUrl();
        this.userName = connectionStringParts.getUserName();
        this.password = connectionStringParts.getPassword();
    }

    public static Exporter createExporter()
            throws IllegalStateException {
        ConnectionStringPartsProvider connectionStringParts = DbProvider.getConnectionStringParts();

        return new Exporter(connectionStringParts);
    }

    public void exportTestToFile(int id, String filePath)
            throws SQLException, IOException {
        Test test = getTest(id);

        Serializer.serialize(test, filePath);
    }

    private Test getTest(int id)
            throws SQLException {
        try (Connection connection = DbProvider.createDbConnection(url, userName, password)) {
            Test test = TestStatementsProvider.exportFromDb(connection, id);
            if (test == null)
                throw new IllegalStateException("Теста с таким id не существует.");

            List<Question> questions = getQuestions(connection, id);
            test.setQuestions(questions);

            return test;
        }
    }

    private List<Question> getQuestions(Connection connection, int id)
            throws SQLException {
        List<AnswerVariant> answers = AnswerVariantsStatementsProvider.getExportedAnswerVariants(connection, id);

        return QuestionStatementsProvider.exportFromDb(connection, answers, id);
    }
}
