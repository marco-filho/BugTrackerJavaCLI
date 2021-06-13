package Application.View;

import java.util.Scanner;

import Application.Model.User;
import Auth.Session;
import Auth.AuthExceptions.IllegalPasswordException;
import Auth.AuthExceptions.IllegalUsernameException;
import Auth.AuthExceptions.UnmatchedCredentialsException;

public class Home {
    private static Scanner scanner = new Scanner(System.in);
    private static Session session = new Session();

    static public void index() {
        System.out.println("=== Bem vindo ao Bug Tracker! ===");
        int input = 0;
        while (!session.isUserAuthenticated()) {
            System.out.println("0. Fechar aplicação");
            System.out.println("1. Entrar");
            System.out.println("2. Registrar");
            
            input = UITools.inputValidation(2);

            switch (input) {
                case 0:
                    System.exit(0);
                case 1:
                    signInUI();
                    break;
                case 2:
                    signUpUI();
                    break;
            }
        }
        
        if(session.isUserAuthenticated()) {
            System.out.println("(0) Sair da conta e da aplicação");
            System.out.println("(1) Sair da aplicação");
            System.out.println("(2) Abrir Painel de Controle");

            input = UITools.inputValidation(2);

            switch (input) {
                case 0:
                    signOutUI();
                    System.exit(0);
                case 1:
                    System.exit(0);
                    break;
                case 2:
                    Dashboard.index();
                    break;
            }

        } else {
            System.out.println("Ocorreu algo errado, tente novamente.");
            System.exit(1);
        }
    }

    private static void signInUI() {
        System.out.println("Nome de usuário: ");
        String username = scanner.nextLine();
        System.out.println("Senha: ");
        String password = scanner.nextLine();

        UITools.clearConsole();
        
        User user = new User("", username, password);

        try {
            if (session.signIn(user))
                System.out.println("Usuário " + username + " entrou no sistema.");
            else
                System.out.println("Ocorreu um erro, tente novamente.");
        } catch (UnmatchedCredentialsException e) {
            System.out.println(e.getMessage());
            System.out.println("Tente novamente.");
            return;
        }
    }

    private static void signUpUI() {
        System.out.println("=== NOVO USUÁRIO ===");
        System.out.println("Insira seu Nome: ");
        String name = scanner.nextLine();
        System.out.println("Insira seu Nome de Usuário: ");
        String username = scanner.nextLine();
        System.out.println("Insira sua Senha: ");
        String password = scanner.nextLine();

        UITools.clearConsole();
        
        User user = new User(name, username, password);

        try {
            if (session.signUp(user))
                System.out.println("Usuário registrado com sucesso.");
            else {
                System.out.println("Ocorreu um erro.\nTente novamente");
                signUpUI();
            }
        } catch (IllegalUsernameException | IllegalPasswordException e) {
            System.out.println(e.getMessage());
            System.out.println("Tente novamente");
            signUpUI();
        }
    }

    private static void signOutUI() {
        UITools.clearConsole();
        
        if (session.signOut())
            System.out.println("Você saiu.");
        else
            System.out.println("Ocorreu um erro.");

        System.exit(0);
    }
}