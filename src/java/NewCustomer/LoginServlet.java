package NewCustomer;

import java.io.Serializable;
import NewCustomer.User;

public class User implements Serializable {

    private String userName;
    private String password;

    public User() {
        userName = "lastName" + zipcode;
        password = "welcome1";
    }

    public User(String userName, String password) {
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
