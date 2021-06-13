package Application.View;

import java.util.List;
import java.util.Scanner;

import Application.Controller.UsersController;
import Application.Model.User;
import Auth.Session;
import Auth.AuthExceptions.UserNotAuthenticatedException;
import Auth.Authorization.Role;

public class ManageUsers {
    private static Scanner scanner = new Scanner(System.in);
    private static Session session = new Session();
    private static User user;

    public static void index() {
        try {
            user = session.getAuthenticatedUser();
        } catch (UserNotAuthenticatedException e) {
            if (e.getErrorType() == UserNotAuthenticatedException.ErrorType.NULL_RETURN) {
                e.getMessage();
                System.out.println("A autenticação retornou nula. É necessário entrar novamente.");
            } else if (e.getErrorType() == UserNotAuthenticatedException.ErrorType.NOT_AUTHENTICATED) {
                e.getMessage();
                System.out.println("Usuário não autenticado.");
            } else if (e.getErrorType() == UserNotAuthenticatedException.ErrorType.NOT_ESPECIFIED) {
                e.getMessage();
                System.out.println("Erro não especificado.");
            }
            return;
        }

        if (user.getRole() != Role.PROJECTMANAGER && user.getRole() != Role.ADMINISTRATOR) {
            System.out.println("Você não devia estar aqui.... retornando.");
            return;
        }

        do {
            System.out.println("=== GERENCIAMENTO DE USUÁRIOS ===");
            int opts = 1;
            System.out.println("0. Retornar");
            System.out.println("1. Imprimir usuários");
            // if (user.getRole() == Role.ADMINISTRATOR) {
            //     System.out.println("2. Gerenciar times");
            //     opts = 2;
            // }
            int input = UITools.inputValidation(opts);

            switch (input) {
                case 0:
                    return;
                case 1:
                    readUsersUI();
                    continue;
                case 2:
                    //ManageTeams.index();
                    continue;
            }

            UITools.clearConsole();
        } while(true);
    }

    private static void readUsersUI() {
        List<User> users = UsersController.readUsers();

        System.out.println("=== USUÁRIOS ===");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + users.get(i));
        }
        
        System.out.println("Insira o número correspondente ao usuário a ser visualizado.");
        System.out.println("0 para retornar.");

        int input = UITools.inputValidation(users.size());
        
        if (input == 0) return;

        printUserUI(users.get(input - 1));
    }

    private static void printUserUI(User user) {
        UITools.clearConsole();
        int opts = 1;

        UsersController.printUser(user);

        System.out.println("(0) Retornar");
        System.out.println("(1) Editar");
        if (ManageUsers.user.getRole() == Role.ADMINISTRATOR) {
            System.out.println("(2) Excluir");
            opts = 2;
        }

        int input = UITools.inputValidation(opts);

        switch (input) {
            case 0:
                return;
            case 1:
                if (ManageUsers.user.getRole() == Role.PROJECTMANAGER)
                    updateUserTeamOnlyUI(user);
                else
                    updateUserUI(user);
                return;
            case 2:
                deleteUserUI(user);
                return;
        }
    }

    private static void updateUserTeamOnlyUI(User user) {
        System.out.println("=== ATUALIZAR USUÁRIO ===");
        System.out.println("Insira novo Time:");
        user.setTeam(scanner.nextLine());
        System.out.println("Salvar mudanças?");
        System.out.println("(0) Cancelar e retornar\n(1) Salvar");
        int input = UITools.inputValidation(1);
        if (input == 1)
            UsersController.updateUser(user);
    }

    private static void updateUserUI(User user) {
        do {
            System.out.println("=== ATUALIZAR USUÁRIO ===");
            System.out.println("Escolha o item a ser editado");

            System.out.println("(1) Nome");
            System.out.println("(2) Nome de Usuário");
            System.out.println("(3) Senha");
            System.out.println("(4) Time");
            System.out.println("(5) Função");

            System.out.println("\n(0) Cancelar mudanças e retornar");
            System.out.println("\n(6) Salvar mudanças");

            int input = UITools.inputValidation(6);

            switch (input) {
                case 0:
                    return;
                case 1:
                    System.out.println("Insira novo Nome:");
                    user.setName(scanner.nextLine());
                    continue;
                case 2:
                    System.out.println("Insira novo Nome de Usuário:");
                    user.setUsername(scanner.nextLine());
                    continue;
                case 3:
                    System.out.println("Insira nova Senha:");
                    user.setPassword(scanner.nextLine());
                    continue;
                case 4:
                    System.out.println("Insira novo Time:");
                    user.setTeam(scanner.nextLine());
                    continue;
                case 5:
                    System.out.println("Insira nova Função:");
                    System.out.println("(0) Solicitante");
                    System.out.println("(1) Desenvolvedor");
                    System.out.println("(2) Gerente de Projeto");
                    System.out.println("(3) Administrador");
                    input = UITools.inputValidation(4);
                    switch (input) {
                        case 0:
                            user.setRole(Role.SUBMITTER);
                            break;
                        case 1:
                            user.setRole(Role.DEVELOPER);
                            break;
                        case 2:
                            user.setRole(Role.PROJECTMANAGER);
                            break;
                        case 3:
                            user.setRole(Role.ADMINISTRATOR);
                            break;
                    }
                    continue;
                case 6:
                    UsersController.updateUser(user);
                    return;
                default:
                    System.out.println("Algum erro fatal ocorreu. Fechando aplicação.");
                    scanner.next();
                    System.exit(1);
            }
            UITools.clearConsole();
        } while(true);
    }

    private static void deleteUserUI(User user) {
        System.out.println("=== EXCLUIR USUÁRIO ===");
        System.out.println(user);
        System.out.println("Confirme a exclusão do usuário (permanente)");
        System.out.println("(0) Cancelar e retornar\n(1) Confirmar");
        
        int input = UITools.inputValidation(1);

        if (input != 1) return;

        UsersController.deleteUser(user.getId());
    }
}
