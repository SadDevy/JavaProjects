package Utilities.CommandProviders;

import Entities.AnswerVariant;
import Entities.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionStatementsProvider {
    private static final String exportQuestionsQuery =
"""
SELECT
Q."Id", Q."TestId", Q."Description"
FROM "Questions" AS Q
WHERE Q."TestId" = ?
""";

    private static final String importQuestionsQuery =
"""
INSERT INTO "Questions"("TestId", "Description")
VALUES(?, ?)
""";

    private static final String getMaxQuestionId =
"""
SELECT
    MAX(Q."Id")
FROM "Questions" AS Q
""";

    private static final String removeQuestionsQuery =
"""
DELETE FROM "Questions"
WHERE "TestId" = 
    (SELECT
        T."Id"
     FROM "Tests" AS T
     WHERE T."Id" = ?)
""";

    public static List<Question> exportFromDb(Connection connection, List<AnswerVariant> answers, int id)
            throws SQLException {
        List<Question> questions = new ArrayList<>();
        try (PreparedStatement statement = getExportStatement(connection, id);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int questionId = resultSet.getInt("Id");

                Question question = new Question(questionId, resultSet.getInt("TestId"), resultSet.getString("Description"));
                List<AnswerVariant> answerVariants = answers.stream().filter(n -> n.getQuestionId() == questionId).toList();

                question.setAnswerVariants(answerVariants);

                questions.add(question);
            }
        }

        return questions;
    }

    private static PreparedStatement getExportStatement(Connection connection, int id)
            throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        return StatementProvider.getStatement(connection, exportQuestionsQuery, id);
    }

    public static int importToDb(Connection connection, Question question, int testId)
            throws SQLException {
        try (PreparedStatement statement = getImportQuestionStatement(connection, question, testId)) {
            statement.executeQuery();

            return getMaxQuestionId(connection);
        }
    }

    private static int getMaxQuestionId(Connection connection)
            throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getMaxQuestionId)) {
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }

        return 0;
    }

    private static PreparedStatement getImportQuestionStatement(Connection connection, Question question, int testId)
            throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        if (question == null)
            throw new IllegalArgumentException("question");

        return StatementProvider.getQuestionImportStatement(connection, importQuestionsQuery, question, testId);
    }

    public static void removeFromDb(Connection connection, int id)
            throws SQLException {
        try (PreparedStatement statement = getRemoveStatement(connection, id)) {
            statement.executeQuery();
        }
    }

    private static PreparedStatement getRemoveStatement(Connection connection, int id)
            throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        return StatementProvider.getStatement(connection, removeQuestionsQuery, id);
    }
}
