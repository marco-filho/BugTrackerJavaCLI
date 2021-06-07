package Auth;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Application.Data.DataContext;
import Application.Model.User;

public abstract class Session {
    private static String path = "Session/session.txt";

    public static boolean isUserAuthenticated() {
        File file = new File(path);
        return file.exists();
    }

    public static boolean signIn(User user) {
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
    
    public static boolean signOut() {
        File file = new File(path);
        return file.delete();
    }

    public static boolean signUp(User user) {
        DataContext context = new DataContext();
        if (context.getUsers().getAll().stream().anyMatch(u -> u.getUsername() == user.getUsername()))
            return false;
        else
            context.getUsers().add(user);
        return true;
    }
}
