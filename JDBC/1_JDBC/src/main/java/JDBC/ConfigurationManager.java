package JDBC;

import java.io.*;
import java.util.Properties;

public class ConfigurationManager {
    private static final String configurationFileName = "src/main/resources/config.properties";
    private static Properties properties;

    static {
        try {
            properties = new Properties();

            properties.load(new FileInputStream(configurationFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConnectionStringProvider getConnectionString() {
        if (properties == null)
            return null;

        String providerName = getProviderName();
        String url = getUrl();
        String userName = getUserName();
        String password = getPassword();

        return new ConnectionStringProvider(providerName, url, userName, password);
    }

    private String getProviderName() {
        if (properties == null)
            return null;

        return properties.getProperty("PROVIDER_NAME");
    }

    private String getUrl() {
        if (properties == null)
            return null;

        return properties.getProperty("URL");
    }

    private String getUserName() {
        if (properties == null)
            return null;

        return properties.getProperty("USER_NAME");
    }

    private String getPassword() {
        if (properties == null)
            return null;

        return properties.getProperty("PASSWORD");
    }
}
