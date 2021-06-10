package Auth;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Application.Data.DataContext;
import Application.Model.User;
import Auth.AuthExceptions.IllegalPasswordException;
import Auth.AuthExceptions.IllegalUsernameException;
import Auth.AuthExceptions.UnmatchedCredentialsException;

public class Session {
    private static String path = "Session/session.txt";
    private DataContext context;

    public Session() {
        context = new DataContext();
    }

    public boolean isUserAuthenticated() {
        File file = new File(path);
        return file.exists();
    }

    public boolean signIn(User user) throws UnmatchedCredentialsException {
        FileWriter fw = null;
        if (Authentication.authenticate(user)) {
            try {
                fw = new FileWriter(path);
                fw.write(user.getUsername());
            } catch (IOException e) {
                System.out.println("Ocorreu um erro: \n" + e.getMessage());
            } finally {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.out.println("Ocorreu um erro: \n" + e.getMessage());
                }
            }
            return true;
        }
        else
            return false;
    }
    
    public boolean signOut() {
        File file = new File(path);
        return file.delete();
    }

    public boolean signUp(User user) throws IllegalUsernameException, IllegalPasswordException {
        Authentication.validateUsername(user.getUsername());
        Authentication.validatePassword(user.getPassword());
        context.getUsers().add(user);
        return true;
    }
}