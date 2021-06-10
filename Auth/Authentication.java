package Auth;

import Application.Data.DataContext;
import Application.Model.User;
import Auth.AuthExceptions.IllegalPasswordException;
import Auth.AuthExceptions.IllegalUsernameException;
import Auth.AuthExceptions.UnmatchedCredentialsException;
import Auth.AuthExceptions.UsernameExistsException;

abstract class Authentication {
    static boolean authenticate(User user) throws UnmatchedCredentialsException  {
        DataContext context = new DataContext();
        if (context.getUsers().getAll()
            .stream()
            .noneMatch(u -> u.getUsername() == user.getUsername() && u.getPassword() == user.getPassword()))
            throw new UnmatchedCredentialsException();
        else
            return true;
    }

    static boolean validateUsername(String username) throws IllegalUsernameException {
        DataContext context = new DataContext();
        String allowed = "[^\\s+][a-zA-Z0-9_.\\-]*";
        if (username.matches(allowed) || username.length() < 4)
            throw new IllegalUsernameException();
        else if (context.getUsers().getAll().stream().anyMatch(u -> u.getUsername() == username))
            throw new UsernameExistsException();
        else
            return true;
    }

    static boolean validatePassword(String password) throws IllegalPasswordException {
        String allowed = "[^\\s+][a-zA-Z0-9@#?+=\\-]*";
        if (password.matches(allowed) || password.length() < 4)
            throw new IllegalPasswordException();
        return true;
    }
}
