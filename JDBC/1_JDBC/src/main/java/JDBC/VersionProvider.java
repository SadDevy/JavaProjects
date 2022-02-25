package JDBC;

import java.sql.*;

public class VersionProvider {
    private static final String query = "SELECT version()";

    static {
        try {
            ConfigurationManager configurationManager = new ConfigurationManager();
            String provider = configurationManager
                    .getConnectionString()
                    .getProviderName();

            Class.forName(provider);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getServerVersion(
            String url,
            String userName,
            String password
    ) throws SQLException {
        if (url == null)
            throw new IllegalArgumentException("url");

        if (userName == null)
            throw new IllegalArgumentException("userName");

        if (password == null)
            throw new IllegalArgumentException("password");

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);) {
            while(resultSet.next()) {
                return resultSet.getString(1);
            }
        }

        return "";
    }
}
