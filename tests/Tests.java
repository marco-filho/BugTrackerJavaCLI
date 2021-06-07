package tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.ArrayList;
import java.io.Writer;
import java.io.FileWriter;
import com.google.gson.reflect.TypeToken;

import Application.Data.DataContext;
import Application.Model.User;

public class Tests {
    public static void testSaveChangesAndReadJsonFile() {
        DataContext context = new DataContext();
        context.getUsers().add(new User("aaaa", "bbb", "sssenha"));;
        context.saveChanges();
    }
    
    public static void testGSONstuff() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        User user = new User("Boboca Bobo", "boboca","senhateste");
        
        String json = gson.toJson(user);

        List<User> users = new ArrayList<User>();
        
        users.add(user);
        users.add(new User("Boboca Bobo Junior", "bobocajun","senhatestee"));
        users.add(new User("Bobocao Boboo", "bobocao","senhatest"));

        try{
            Writer writer = new FileWriter("Output.json");
            gson.toJson(users, new TypeToken<ArrayList<User>>() {}.getType(), writer);
            json = gson.toJson(users, users.getClass());
            System.out.println("been here");
            writer.close();
        } catch(Exception e) {
            System.out.println("well didnt work");
            System.out.println(e);
        }

        users.clear();

        users = gson.fromJson(json, new TypeToken<ArrayList<User>>() {}.getType());

        for (User userrrr : users) {
            System.out.println(userrrr.toString());
        }
    }
}
