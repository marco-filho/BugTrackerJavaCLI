package Application.Controller;

import Application.View.View;
import Auth.Session;

public abstract class TicketsController {
    public static void showTickets() {
        if (!Session.isUserAuthenticated()) {
            View.index();
            return;
        }

    }

    public static void newTicket() {

    }

    public static void updateTicket() {
        
    }

    public static void deleteTicket() {
        
    }
}
