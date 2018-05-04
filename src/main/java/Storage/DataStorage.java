package Storage;

import Storage.Types.Data;

import java.util.ArrayList;

public class DataStorage {
    private static DataStorage dataStorage = new DataStorage();

    public static DataStorage getInstance() {
        return dataStorage;
    }

    private ArrayList<Data> storage = new ArrayList<>();

    public void save(Data data) {
        storage.add(data);
    }

    public ArrayList<Data> getStorage() {
        return storage;
    }
}
