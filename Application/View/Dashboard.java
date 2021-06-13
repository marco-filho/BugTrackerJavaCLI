package Application.View;

import java.util.List;
import java.util.Scanner;

import Application.Controller.TicketsController;
import Application.Model.Ticket;
import Application.Model.User;
import Auth.Session;
import Auth.AuthExceptions.UserNotAuthenticatedException;
import Auth.Authorization.Role;

public class Dashboard {
    private static Scanner scanner = new Scanner(System.in);
    private static Session session = new Session();

    public static void index() {
        User user = null;
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

        do {
            System.out.println("=== PAINEL DE CONTROLE ===");
            int opts = 1;
            System.out.println("(0) Retornar");
            System.out.println("(1) Criar demanda");
            if (user.getRole() != Role.SUBMITTER) {
                System.out.println("(2) Imprimir demandas");
                opts = 2;
            }
            if (user.getRole() == Role.PROJECTMANAGER || user.getRole() == Role.ADMINISTRATOR) {
                System.out.println("(3) Gerenciar usuários");
                opts = 3;
            }
            int input = UITools.inputValidation(opts);
            
            switch (input) {
                case 0:
                    return;
                case 1:
                    createTicketUI();
                    break;
                case 2:
                    readTicketsUI();
                    break;
                case 3:
                    ManageUsers.index();
                    index();
                    break;
            }

            UITools.clearConsole();
        } while(true);
    }

    private static void createTicketUI() {
        System.out.println("=== NOVA DEMANDA ===");
        
        System.out.println("Título: ");
        String title = scanner.nextLine();
        System.out.println("Descrição: ");
        String description = scanner.nextLine();
        System.out.println("Time: ");
        String team = scanner.nextLine();
        System.out.println("Prioridade: ");
        String priority = scanner.nextLine();

        Ticket ticket = new Ticket(title, description, team, priority);
        TicketsController.createTicket(ticket);
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }

    private static void readTicketsUI() {
        List<Ticket> userTickets = TicketsController.readTickets();

        System.out.println("=== DEMANDAS ASSOCIADAS AO USUÁRIO ===");
        
        if(userTickets == null || userTickets.size() == 0) {
            System.out.println("Não há demandas associadas ao usuário.");
            System.out.println("Pressione Enter para retornar...");
            scanner.nextLine();
            return;
        }

        for (int i = 0; i < userTickets.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + userTickets.get(i));
        }
        
        System.out.println("Insira o número correspondente a demanda a ser visualizada.");
        System.out.println("(0) Retornar.");

        int input = UITools.inputValidation(userTickets.size());

        if (input == 0) return;

        printTicketUI(userTickets.get(input - 1));
    }

    private static void printTicketUI(Ticket ticket) {
        UITools.clearConsole();
        TicketsController.printTicket(ticket);
        System.out.println("(0) Retornar\n(1) Editar\n(2) Excluir");
        int input = UITools.inputValidation(2);

        switch (input) {
            case 0:
                return;
            case 1:
                updateTicketUI(ticket);
                return;
            case 2:
                deleteTicketUI(ticket);
                return;
        }
    }

    private static void updateTicketUI(Ticket ticket) {
        do {
            System.out.println("=== ATUALIZAR DEMANDA ===");
            System.out.println("Escolha o item a ser editado");

            System.out.println("(1) Título");
            System.out.println("(2) Descrição");
            System.out.println("(3) Time");
            System.out.println("(4) Prioridade");

            System.out.println("\n(0) Cancelar mudanças e retornar");
            System.out.println("\n(5) Salvar mudanças");

            int input = UITools.inputValidation(5);

            switch (input) {
                case 0:
                    return;
                case 1:
                    System.out.println("Insira novo Título:");
                    ticket.setTitle(scanner.nextLine());
                    continue;
                case 2:
                    System.out.println("Insira nova Descrição:");
                    ticket.setDescription(scanner.nextLine());
                    continue;
                case 3:
                    System.out.println("Insira novo Time:");
                    ticket.setTeam(scanner.nextLine());
                    continue;
                case 4:
                    System.out.println("Insira nova Prioridade:");
                    ticket.setPriority(scanner.nextLine());
                    continue;
                case 5:
                    TicketsController.updateTicket(ticket);
                    System.out.println("Pressione Enter para retornar...");
                    scanner.nextLine();
                    return;
                default:
                    System.out.println("Algum erro fatal ocorreu. Fechando aplicação.");
                    scanner.next();
                    System.exit(1);
            }
        } while(true);
    }

    private static void deleteTicketUI(Ticket ticket) {
        System.out.println("=== EXCLUIR DEMANDA ===");
        System.out.println(ticket);
        System.out.println("Confirme a exclusão da demanda (permanente)");
        System.out.println("(0) Cancelar e retornar\n(1) Confirmar");
        
        int input = UITools.inputValidation(1);

        if (input != 1) return;

        TicketsController.deleteTicket(ticket.getId());
    }
}
