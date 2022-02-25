package Utilities.StatementProviders;

import Entities.AnswerVariant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerVariantsStatementsProvider {
    private static final String exportAnswerVariantQuery =
"""
SELECT
    A."Id", A."QuestionId", A."Description", A."IsCorrected"
FROM "AnswerVariants" AS A
WHERE A."QuestionId" IN (
    SELECT
        Q."Id"
    FROM "Questions" AS Q
    WHERE Q."TestId" = ?)
""";

    private static String importAnswerVariantsQuery =
"""
INSERT INTO "AnswerVariants"("QuestionId", "Description", "IsCorrected")
VALUES(?, ?, ?)
""";

    private static String removeAnswerVariantsQuery =
"""
DELETE FROM "AnswerVariants"
WHERE "QuestionId" IN (
    SELECT Q."Id"
    FROM "Questions" AS Q
    WHERE Q."TestId" = 
        (SELECT T."Id"
         FROM "Tests" AS T
         WHERE T."Id" = ?)
)
""";

    public static List<AnswerVariant> getExportedAnswerVariants(Connection connection, int id)
            throws SQLException {
        List<AnswerVariant> answerVariants = new ArrayList<>();
        try (PreparedStatement statement = getExportAnswerVariantsStatement(connection, id);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                AnswerVariant answerVariant = new AnswerVariant(
                        resultSet.getInt("Id"),
                        resultSet.getInt("QuestionId"),
                        resultSet.getString("Description"),
                        resultSet.getBoolean("IsCorrected")
                );

                answerVariants.add(answerVariant);
            }
        }

        return answerVariants;
    }

    private static PreparedStatement getExportAnswerVariantsStatement(Connection connection, int id)
            throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        return StatementProvider.getStatement(connection, exportAnswerVariantQuery, id);
    }

    public static void importToDb(Connection connection, AnswerVariant answerVariant, int questionId)
            throws SQLException {
        try (PreparedStatement statement = getImportStatement(connection, answerVariant, questionId)) {
            statement.executeUpdate();
        }
    }

    private static PreparedStatement getImportStatement(Connection connection, AnswerVariant answerVariant, int questionId)
            throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        if (answerVariant == null)
            throw new IllegalArgumentException("answerVariant");

        return StatementProvider.getAnswerVariantImportStatement(connection, importAnswerVariantsQuery, answerVariant, questionId);
    }

    public static void removeFromDb(Connection connection, int id)
            throws SQLException {
        try (PreparedStatement statement = getRemoveStatement(connection, id)) {
            statement.executeUpdate();
        }
    }

    private static PreparedStatement getRemoveStatement(Connection connection, int id)
            throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        return StatementProvider.getStatement(connection, removeAnswerVariantsQuery, id);
    }
}
