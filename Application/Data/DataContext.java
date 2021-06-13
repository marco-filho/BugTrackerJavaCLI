package Application.Data;

import java.util.List;

import com.google.gson.reflect.TypeToken;

import Application.Model.*;

public class DataContext {
    private DataSet<User> usersData;
    private DataSet<Ticket> ticketsData;

    public DataContext() {
        usersData = new DataSet<User>("Users", new TypeToken<List<User>>() {});
        ticketsData = new DataSet<Ticket>("Tickets", new TypeToken<List<Ticket>>() {});
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
