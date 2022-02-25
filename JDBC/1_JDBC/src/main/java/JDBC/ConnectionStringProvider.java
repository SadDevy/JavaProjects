package JDBC;

import java.util.Objects;

public class ConnectionStringProvider {
    private String providerName;

    public String getProviderName() {
        return providerName;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    private String userName;

    public String getUserName() {
        return userName;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public ConnectionStringProvider(
            String providerName,
            String url,
            String userName,
            String password
    ) {
        this.providerName = providerName;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !isType(o))
            return false;

        ConnectionStringProvider a = (ConnectionStringProvider) o;
        return url == a.getUrl()
                && userName == a.getUserName()
                && password == a.getPassword();
    }

    private boolean isType(Object o) {
        try {
            ConnectionStringProvider __ = (ConnectionStringProvider) o;

            return true;
        } catch (ClassCastException ex) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(providerName)
                ^ Objects.hashCode(url)
                ^ Objects.hashCode(userName)
                ^ Objects.hashCode(password);
    }
}
