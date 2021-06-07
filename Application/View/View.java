package Application.View;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Application.Model.User;
import Auth.Session;

public class View {
    private static Scanner scanner = new Scanner(System.in);

    static public void index() {
        int input = 0;
        System.out.println("1. Entrar");
        System.out.println("2. Registrar");

        try {
            input = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida, tente novamente.");
            index();
            return;
        }

        switch (input) {
            case 1:
                signInUI();
                break;
            case 2:
                signUpUI();
                break;
            default:
                System.out.println("Entrada inválida, tente novamente.");
                index();
                return;
        }
    }

    private static void signInUI() {
        System.out.println("Nome de usuário: ");
        String username = scanner.nextLine();
        System.out.println("Senha: ");
        String password = scanner.nextLine();

        clearConsole();
        
        User user = new User("", username, password);

        if (Session.signIn(user))
            System.out.println("Usuário " + username + " entrou no sistema.");
        else
            System.out.println("Ocorreu um erro, tente novamente.");
            signInUI();
    }

    private static void signUpUI() {
        String name = scanner.nextLine();
        String username = scanner.nextLine();
        String password = scanner.nextLine();

        clearConsole();
        
        User user = new User(name, username, password);

        if (Session.signUp(user))
            System.out.println("Usuário registrado com sucesso.");
        else {
            System.out.println("Ocorreu um erro:\nO nome de usuário " + user.getUsername() + " já existe.\nTente novamente");
            signInUI();
        }
    }

    private static void signOutUI() {
        if (Session.signOut())
            System.out.println("Você saiu.");
        else
            System.out.println("Ocorreu um erro.");
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (InterruptedException | IOException e) {
        }
    }
}
