package Application.Data;

import Application.Model.*;

public class DataContext {
    private DataSet<User> usersData;
    private DataSet<Ticket> ticketsData;

    public DataContext() {
        usersData = new DataSet<User>("Users");
        ticketsData = new DataSet<Ticket>("Tickets");
    }

    public DataSet<User> getUsers() {
        return usersData;
    }

    public DataSet<Ticket> getTickets() {
        return ticketsData;
    }

    public void saveChanges() {
        usersData.saveChanges();
        ticketsData.saveChanges();
    }
}
