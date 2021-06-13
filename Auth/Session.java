package Auth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Application.Data.DataContext;
import Application.Model.User;
import Auth.AuthExceptions.IllegalPasswordException;
import Auth.AuthExceptions.IllegalUsernameException;
import Auth.AuthExceptions.UnmatchedCredentialsException;
import Auth.AuthExceptions.UserNotAuthenticatedException;

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

    public User getAuthenticatedUser() throws UserNotAuthenticatedException {
        FileReader reader = null;
        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new UserNotAuthenticatedException(0);
        }
        Scanner scanner = new Scanner(reader);
        String authenticated = scanner.nextLine();
        User user = context
                        .getUsers()
                        .getAll()
                        .stream()
                        .filter(u -> u.getUsername().equals(authenticated))
                        .findFirst()
                        .get();
        scanner.close();
        if (user == null) {
            throw new UserNotAuthenticatedException(1);
        }
        return user;
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
        Authentication.validateUsername(user);
        Authentication.validatePassword(user);
        user.setRole(Authorization.Role.SUBMITTER);
        user.setTeam("Sem time");
        context.getUsers().add(user);
        context.saveChanges();
        return true;
    }

    public static boolean validateUser(User user) {
        try {
            Authentication.validateUsername(user);
            Authentication.validatePassword(user);
        } catch (IllegalUsernameException | IllegalPasswordException e) {
            return false;
        }
        return true;
    }
}