package UI;

import JDBC.ConfigurationManager;
import JDBC.ConnectionStringProvider;
import JDBC.VersionProvider;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            printSqlVersion();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printSqlVersion()
            throws SQLException {
        String sqlVersion = getSqlVersion();
        System.out.println("Sql version: " + sqlVersion);
    }

    private static String getSqlVersion()
            throws SQLException {
        ConfigurationManager configurationManager = new ConfigurationManager();
        ConnectionStringProvider connectionStringProvider = configurationManager.getConnectionString();

        return VersionProvider.getServerVersion(
                connectionStringProvider.getUrl(),
                connectionStringProvider.getUserName(),
                connectionStringProvider.getPassword()
        );
    }
}
