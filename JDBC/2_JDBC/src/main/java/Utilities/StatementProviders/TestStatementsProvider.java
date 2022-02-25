package Utilities.StatementProviders;

import Entities.Test;

import java.sql.*;

public class TestStatementsProvider {
    private static final String exportTestQuery =
"""
SELECT 
    T."Id", T."Name", T."Theory",
    T."TheoryUrl", T."Image", T."TheoryIsShown",
    T."TestTime", T."QuestionsCount", T."RightCount"
FROM "Tests" AS T
WHERE T."Id" = ?        
""";

    private static final String importTestQuery =
"""
INSERT INTO "Tests"("Name", "Theory", "TheoryUrl", "Image", "TestTime", 
"QuestionsCount", "RightCount", "TheoryIsShown")
VALUES (?, ?, ?, ?, ?, ?, ?, ?)
""";

    private static final String getMaxTestIdQuery =
"""
SELECT
    MAX(T."Id")
FROM "Tests" AS T
""";

    private static final String removeTestQuery =
"""
DELETE FROM "Tests"
WHERE "Id" = ?
""";

    private static final String testExistsQuery =
"""
SELECT
    COUNT(T."Id") > 0
FROM "Tests" AS T
WHERE T."Id" = ?
""";

    public static Test exportFromDb(Connection connection, int id)
            throws SQLException {
        Test test = null;
        try (PreparedStatement statement = getExportStatement(connection, id);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String theory = resultSet.getString("Theory");
                String theoryUrl = resultSet.getString("TheoryUrl");
                byte[] image = resultSet.getBytes("Image");
                boolean theoryIsShown = resultSet.getBoolean("TheoryIsShown");
                int testTime = resultSet.getInt("TestTime");
                int questionsCount = resultSet.getInt("QuestionsCount");
                int rightCount = resultSet.getInt("RightCount");

                test = new Test(id, name, theory, theoryUrl, image, theoryIsShown, testTime, questionsCount, rightCount);
            }
        }

        return test;
    }

    private static PreparedStatement getExportStatement(Connection connection, int id)
            throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        return StatementProvider.getStatement(connection, exportTestQuery, id);
    }

    public static int importToDb(Connection connection, Test test)
            throws SQLException {
        try (PreparedStatement statement = getImportStatement(connection, test)) {
            statement.executeUpdate();

            return getMaxTestId(connection);
        }
    }

    private static PreparedStatement getImportStatement(Connection connection, Test test)
            throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        if (test == null)
            throw new IllegalArgumentException("test");

        return StatementProvider.getTestImportStatement(connection, importTestQuery, test);
    }

    private static int getMaxTestId(Connection connection)
            throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getMaxTestIdQuery)) {
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }

        return 0;
    }

    public static boolean testExists(Connection connection, int id)
            throws SQLException {
        try (PreparedStatement statement = getTestExistsStatement(connection, id);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return resultSet.getBoolean(1);
            }
        }

        return false;
    }

    private static PreparedStatement getTestExistsStatement(Connection connection, int id)
            throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        return StatementProvider.getStatement(connection, testExistsQuery, id);
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

        return StatementProvider.getStatement(connection, removeTestQuery, id);
    }
}
