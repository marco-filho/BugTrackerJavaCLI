package Application.Controller;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import Application.Data.DataContext;
import Application.Model.Ticket;
import Application.Model.User;
import Auth.Session;
import Auth.AuthExceptions.UserNotAuthenticatedException;

public abstract class TicketsController {
    private static Session session = new Session();
    private static DataContext context = new DataContext();

    public static List<Ticket> readTickets() {
        try {
			User user = session.getAuthenticatedUser();

            return context.getTickets().getAll() == null ? null : 
            context.getTickets().getAll().stream()
                .filter(t -> t.getSubmitter().equals(user.getUsername()) ||
                            t.getTeam().equals(user.getTeam())).collect(Collectors.toList());
                            
		} catch (UserNotAuthenticatedException e) {
			e.getMessage();
            return null;
		}
    }

    public static void printTicket(Ticket ticket) {
        System.out.println("=== " + ticket.getTitle() + " ===\n");
        System.out.println(ticket.getDescription());
        System.out.println("Id: " + ticket.getId() + " | Time: " + ticket.getTeam() + " | Autor: " + ticket.getSubmitter());
        System.out.println("Criação: " + ticket.printCreation() + " | Prioridade: " + ticket.getPriority());
    }

    public static void createTicket(Ticket ticket) {
        ticket.setCreation(Instant.now());
        try {
            ticket.setSubmitter(session.getAuthenticatedUser().getUsername());
        } catch (UserNotAuthenticatedException e) {
            e.getMessage();
            return;
        }
        context.getTickets().add(ticket);
        context.saveChanges();
        System.out.println("Demanda adicionada com sucesso!");
    }

    public static void updateTicket(Ticket ticket) {
        context.getTickets().update(ticket);
        context.saveChanges();
        System.out.println("Demanda atualizada com sucesso!");
    }

    public static void deleteTicket(int id) {
        context.getTickets().remove(id);
        context.saveChanges();
        System.out.println("Demanda excluída com sucesso!");
    }
}
