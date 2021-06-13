package Application.Controller;

import java.util.List;

import Application.Data.DataContext;
import Application.Model.User;
import Auth.Session;

public abstract class UsersController {
    private static DataContext context = new DataContext();

    public static List<User> readUsers() {
        return context.getUsers().getAll();
    }

    public static void printUser(User user) {
        System.out.println("=== " + user.getName() + " ===\n");
        System.out.println("Nome de usuário: " + user.getUsername() + " | Time: " + user.getTeam());
        System.out.println("Função: " + user.getRole());
    }

    public static void updateUser(User user) {
        if (!Session.validateUser(user)) {
            System.out.println("As informações inseridas não são válidas.");
            System.out.println("Verifique as regras para validação de nomes de usuário e senhas.");
            return;
        }
        context.getUsers().update(user);
        context.saveChanges();
        System.out.println("Usuário atualizado com sucesso!");
    }

    public static void deleteUser(int id) {
        context.getUsers().remove(id);
        context.saveChanges();
        System.out.println("Usuário excluído com sucesso!");
    }
}
