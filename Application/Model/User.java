package Application.Model;

import Auth.Roles.*; 

public class User {
    private String name;
    private String username;
    private String password;
    //private Role role; // dont know
    private boolean valid;
    
    public User() {
        valid = false;
    }

    //remove later
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        valid = true;
    }

    @Override
    public String toString() {
        return name + ", " + username + ", " + password + ", " + valid;
    }

    //

    // public User() { //requires all information to register
    //     valid = true;
    // }

    public boolean isValid() {
        return valid;
    }
}
