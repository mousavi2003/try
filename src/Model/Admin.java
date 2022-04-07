package Model;

public class Admin {
    private String username;
    private String password;
    boolean haveAdmin;

    public Admin(String username, String password, boolean haveAdmin) {
        this.username = username;
        this.password = password;
        this.haveAdmin = haveAdmin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setHaveAdmin(boolean haveAdmin) {
        this.haveAdmin = haveAdmin;
    }

    public boolean HaveAdmin() {
        return haveAdmin;
    }
}
