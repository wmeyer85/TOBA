package NewCustomer;

import java.io.Serializable;

public class NewCustomer implements Serializable {

    private String userName;
    private String password;

    public NewCustomer() {
        userName = "jsmith@toba.com";
        password = "letmein";
    }

    public NewCustomer(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setFirstName(String firstName) {
        this.userName = userName;
    }

    public String getpassword() {
        return password;
    }

}