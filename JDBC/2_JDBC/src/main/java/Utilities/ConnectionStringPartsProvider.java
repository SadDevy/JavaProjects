package Utilities;

public class ConnectionStringPartsProvider {
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

    public ConnectionStringPartsProvider(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
}
