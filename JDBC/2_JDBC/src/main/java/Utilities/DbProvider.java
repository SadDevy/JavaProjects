package Utilities;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbProvider {
    private static final String configurationFileName = "src/main/resources/configuration.properties";
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(configurationFileName));

            String providerName = getProviderName();
            Class.forName(providerName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection createDbConnection(String url, String userName, String password)
            throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    public static ConnectionStringPartsProvider getConnectionStringParts()
            throws IllegalStateException {
        if (properties == null)
            throw new IllegalStateException("properties");

        String url = getUrl();
        String userName = getUserName();
        String password = getPassword();

        return new ConnectionStringPartsProvider(url, userName, password);
    }

    private static String getProviderName() {
        if (properties == null)
            throw new IllegalStateException("properties");

        return properties.getProperty("PROVIDER_NAME");
    }

    private static String getUrl() {
        if (properties == null)
            throw new IllegalStateException("properties");

        return properties.getProperty("URL");
    }

    private static String getUserName() {
        if (properties == null)
            throw new IllegalStateException("properties");

        return properties.getProperty("USER_NAME");
    }

    private static String getPassword() {
        if (properties == null)
            throw new IllegalStateException("properties");

        return properties.getProperty("PASSWORD");
    }
}
