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
        if (context.getUsers()
                .getAll()
                .stream()
                .noneMatch(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())))
            throw new UnmatchedCredentialsException();
        else
            return true;
    }

    static boolean validateUsername(User user) throws IllegalUsernameException {
        DataContext context = new DataContext();
        String allowed = "[^\\s+][a-zA-Z0-9_.\\-]*";
        if (!user.getUsername().matches(allowed) || user.getUsername().length() < 4)
            throw new IllegalUsernameException();
        else if (context.getUsers().getAll() != null &&
                context.getUsers().getAll()
                    .stream()
                    .anyMatch(u -> u.getId() != user.getId() && u.getUsername().equals(user.getUsername())))
            throw new UsernameExistsException();
        else
            return true;
    }

    static boolean validatePassword(User user) throws IllegalPasswordException {
        String allowed = "[^\\s+][a-zA-Z0-9@#?+=\\-]*";
        if (!user.getPassword().matches(allowed) || user.getPassword().length() < 4)
            throw new IllegalPasswordException();
        return true;
    }
}
