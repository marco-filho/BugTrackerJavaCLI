package Auth;

import Application.Data.DataContext;
import Application.Model.User;

abstract class Authentication {
    static boolean authenticate(User user) {
        DataContext context = new DataContext();
        return !context.getUsers().getAll()
            .stream()
            .noneMatch(u -> u.getUsername() == user.getUsername() && u.getPassword() == user.getPassword());
    }


}
