package Application.Data;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import Application.Model.User;

public class DataSet {
    private List<User> users;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //remove later
    public DataSet() {
        updateDataSet();
    }

    private void updateDataSet() {
        try {
            JsonReader reader = new JsonReader(new FileReader("Persistency/Users.json"));
            users = gson.fromJson(reader, new TypeToken<ArrayList<User>>() {}.getType());
            reader.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public List<User> getUsers() {
        if (users.size() == 0 || users == null) {
            return null;
        } else
            return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void saveChanges() {
        //save changes from users to json db
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Persistency/Users.json"));
            gson.toJson(users, new TypeToken<ArrayList<User>>() {}.getType(), new JsonWriter(bw));
            bw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
