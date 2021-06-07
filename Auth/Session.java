package Auth;

import java.util.List;

import Application.Model.User;

public class Session {
    private User user;
    private List<User> users;
    //db

    public Session() {
        user = new User();
    }

    public boolean isUserAuthenticated() {
        return user.isValid();
    }

    public void signIn() {

    }
    
    public void signOut() {
        //print
    }

    public void signUp() {
        //print
    }
}
