package Application.Model;

import Auth.Roles.*; 

public class User {
    private String name;
    private String username;
    private String password;
    //private Role role; // dont know

    //remove later
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    //remove later
    @Override
    public String toString() {
        return name + "(@" + username + ")";
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return name;
    }

    public String getPassword() {
        return name;
    }
}
