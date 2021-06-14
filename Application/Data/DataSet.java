package Application.Data;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import Application.Model.BaseModel;

public class DataSet<T extends BaseModel> {
    private String dataTitle;
    private List<T> items;
    private Gson gson;
    private TypeToken<List<T>> typeToken;

    DataSet(String dataTitle, TypeToken<List<T>> typeToken) {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .setLenient()
                .create();
        this.typeToken = typeToken;
        this.dataTitle = dataTitle;
        updateDataSet();
    }

    private void updateDataSet() {
        try {
            JsonReader reader = new JsonReader(new FileReader("Persistency/" + dataTitle + ".json"));
            items = gson.fromJson(reader, typeToken.getType());
            reader.close();
        } catch (IOException e) {
            items = new ArrayList<T>();
        }
        items = items.stream().sorted().collect(Collectors.toList());
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

    public T getOneById(int Id) {
        updateDataSet();
        if (items.size() == 0 || items == null) {
            return null;
        }
        for (T i : items) {
            if (i.getId() == Id) {
                return i;
            }
        }
        return null;
    }

    public int getIndex(T item) {
        updateDataSet();
        if (items.size() == 0 || items == null) {
            return -1;
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) == item)
                return i;
        }
        return -1;
    }

    public void add(T item) {
        if (items.size() == 0 || items == null) {
            item.setId(1);
            items.add(item);
            return;
        }
        T last = items.get(items.size() - 1);
        item.setId(last.getId() + 1);
        items.add(item);
    }

    public void update(T newer) {
        updateDataSet();
        for(int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == newer.getId()) {
                items.set(i, newer);
                break;
            }
        }
    }

    public void remove(int id) {
        T item = getAll()
                .stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
        items.remove(item);
    }

    void saveChanges() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Persistency/" + dataTitle + ".json"));
            gson.toJson(items, typeToken.getType(), new JsonWriter(bw));
            bw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
