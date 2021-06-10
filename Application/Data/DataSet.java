package Application.Data;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class DataSet<T> {
    String dataTitle;
    private List<T> items;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    DataSet(String dataTitle) {
        this.dataTitle = dataTitle;
        updateDataSet();
    }

    private void updateDataSet() {
        try {
            JsonReader reader = new JsonReader(new FileReader("Persistency/" + dataTitle + ".json"));
            items = gson.fromJson(reader, new TypeToken<ArrayList<T>>() {}.getType());
            reader.close();
        } catch (IOException e) {
            items = new ArrayList<T>();
        }
    }

    public List<T> getAll() {
        updateDataSet();
        if (items.size() == 0 || items == null) {
            return null;
        }
        return items;
    }

    public T getOne(T item) {
        updateDataSet();
        if (items.size() == 0 || items == null) {
            return null;
        }
        for (T i : items) {
            if (i == item) {
                return i;
            }
        }
        return null;
    }

    public void add(T item) {
        items.add(item);
    }

    public void remove(T item) {
        items.remove(item);
    }

    void saveChanges() {
        //save changes from items to json db
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Persistency/" + dataTitle + ".json"));
            gson.toJson(items, new TypeToken<ArrayList<T>>() {}.getType(), new JsonWriter(bw));
            bw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
