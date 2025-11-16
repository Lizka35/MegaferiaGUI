
package core.models.storage;

import core.models.Manager;
import java.util.ArrayList;

public class StorageManager {
    private static StorageManager instance;
    ArrayList<Manager> managers;

    public StorageManager() {
        this.managers = new ArrayList<>();
    }
    
    public static StorageManager getInstance() {
        if (instance == null) {
            instance = new StorageManager();
        }
        return instance;
    }
    
    public boolean addManager(Manager manager){
        for (Manager man : this.managers) {
            if(man.getId() == manager.getId()){
                return false;
            }
        }
        this.managers.add(manager);
        return true;
    }
    
    public Manager getManager(long id){
        for (Manager manager : this.managers) {
            if(manager.getId() == id){
                return manager;
            }
        }
        return null;
    }

    public ArrayList<Manager> getManagers() {
        return managers;
    }
    
}
