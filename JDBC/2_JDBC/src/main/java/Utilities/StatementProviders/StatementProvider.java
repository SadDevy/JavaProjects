package Utilities.CommandProviders;

import Entities.AnswerVariant;
import Entities.Question;
import Entities.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementProvider {
    public static PreparedStatement getStatement(Connection connection, String query, int id)
            throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        if (query == null)
            throw new IllegalArgumentException("query");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            return statement;
        }
    }

    public static PreparedStatement getTestImportStatement(Connection connection, String query, Test test)
            throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        if (query == null)
            throw new IllegalArgumentException("query");

        if (test == null)
            throw new IllegalArgumentException("test");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, test.getName());
            statement.setString(2, test.getTheory());
            statement.setString(3, test.getTheoryUrl());
            statement.setBytes(4, test.getImage());
            statement.setInt(5, test.getTestTime());
            statement.setInt(6, test.getQuestionsCount());
            statement.setInt(7, test.getRightCount());
            statement.setBoolean(8, test.theoryIsShown());

            return statement;
        }
    }

    public static PreparedStatement getQuestionImportStatement(Connection connection, String query, Question question, int testId)
            throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        if (query == null)
            throw new IllegalArgumentException("query");

        if (question == null)
            throw new IllegalArgumentException("question");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, testId);
            statement.setString(2, question.getDescription());

            return statement;
        }
    }

    public static PreparedStatement getAnswerVariantImportStatement(Connection connection, String query, AnswerVariant answerVariant, int questionId) throws SQLException {
        if (connection == null)
            throw new IllegalArgumentException("connection");

        if (query == null)
            throw new IllegalArgumentException("query");

        if (answerVariant == null)
            throw new IllegalArgumentException("answerVariant");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, questionId);
            statement.setString(2, answerVariant.getDescription());

            return statement;
        }
    }
}
