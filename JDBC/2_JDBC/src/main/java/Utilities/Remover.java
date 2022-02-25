package Utilities;

import Utilities.StatementProviders.AnswerVariantsStatementsProvider;
import Utilities.StatementProviders.QuestionStatementsProvider;
import Utilities.StatementProviders.TestStatementsProvider;

import java.sql.Connection;
import java.sql.SQLException;

public class Remover {
    private String url;
    private String userName;
    private String password;

    private Remover(ConnectionStringPartsProvider connectionStringParts)
            throws IllegalStateException {
        url = connectionStringParts.getUrl();
        userName = connectionStringParts.getUserName();
        password = connectionStringParts.getPassword();
    }

    public static Remover createRemover()
            throws IllegalStateException {
        ConnectionStringPartsProvider connectionStringParts = DbProvider.getConnectionStringParts();

        return new Remover(connectionStringParts);
    }

    public void removeTest(int id)
            throws SQLException {
        try (Connection connection = DbProvider.createDbConnection(url, userName, password)) {
            AnswerVariantsStatementsProvider.removeFromDb(connection, id);
            QuestionStatementsProvider.removeFromDb(connection, id);
            TestStatementsProvider.removeFromDb(connection, id);
        }
    }
}
